# springcloud-seata-tcc

SpringCloud 下的 Seata 分布式事务TCC 模式

## 场景说明

用户购买商品的业务逻辑。整个业务逻辑由3个微服务提供支持：

- 仓储服务：对给定的商品扣除仓储数量。
- 订单服务：根据采购需求创建订单。
- 帐户服务：从用户帐户中扣除余额。

### 重要参数说明
@LocalTCC:一定需要注解在接口上，否则不生效，此接口可以是寻常的业务接口，只要实现了TCC的两阶段提交对应方法便可，适用于SpringCloud+Feign模式下的TCC。
@TwoPhaseBusinessAction:注解try方法，其中name为当前tcc方法的bean名称，写方法名便可（全局唯一），commitMethod指向提交方法，rollbackMethod指向事务回滚方法。指定好三个方法之后，seata会根据全局事务的成功或失败，自动调用提交方法或者回滚方法。
@BusinessActionContextParameter:使用该注解可以将参数传递到二阶段commit或者rollback的方法中，方便调用。
BusinessActionContext:TCC事务上下文，使用BusinessActionContext.getActionContext("params")便可以得到一阶段try中定义的参数，在二阶段参考此参数进行业务回滚操作。
建议:可以在try方法中使用@Transational，直接通过spring来控制关系型数据库的事务，进行回滚的操作，而非关系型数据库等中间件的回滚操作可以交给rollbackMethod方法处理。
建议:try接口不可以捕获异常，否则TCC将识别该操作为成功，直接执行二阶段commit方法。



### SEATA 的分布式交易解决方案
1、其实TCC模式和AT流程上来说是一样的，只是AT是自动根据undo_log来进行事务回滚和补偿，而TCC则需要我们提供相应的接口，官方也都表明了 Seata TCC 模式，可以看到TCC的第一阶段和第二阶段都是自定义的逻辑，seata只管在特定情况下调用。而AT就是全靠undo_log，然后seata判断来帮你处理。

2、这里需要介绍几个后面需要用到的基础注解和参数
@TwoPhaseBusinessAction 注解try方法，其中name为当前tcc方法的bean名称，写方法名便可（记得全局唯一），commitMethod指向提交方法，rollbackMethod指向事务回滚方法。指定好三个方法之后，seata会根据全局事务的成功或失败，去帮我们自动调用提交方法或者回滚方法。

@BusinessActionContextParameter 注解可以将参数传递到二阶段（commitMethod/rollbackMethod）的方法，这个也是下面提到的问题，第二阶段获取的参数只能是第一阶段的一开始通过注解定义的参数值，即使你在第一阶段修改，添加，也没法在第二阶段获取到最新的参数值。

BusinessActionContext 便是指TCC事务上下文，可以通过该参数获取xid、branchId、actionName，以及一些参数，注意，这里有个问题就是 于prepare阶段，也就是try阶段代码的数据添加参数，或者修改参数，在confrim和cancel阶段的方法里面是接受不到你修改后的数据的。

3、TCC 参与者需要实现三个方法，分别是一阶段 Try 方法、二阶段 Confirm 方法以及二阶段 Cancel 方法。在 TCC 参与者的接口中需要先加上 @TwoPhaseBusinessAction 注解，并声明这个三个方法

public interface TccAction {
    @TwoPhaseBusinessAction(name = "yourTccActionName", commitMethod = "confirm", rollbackMethod = "cancel")
    public boolean try(BusinessActionContext businessActionContext, int a, int b);

    public boolean confirm(BusinessActionContext businessActionContext);

    public boolean cancel(BusinessActionContext businessActionContext);
}

@TwoPhaseBusinessAction 注解属性说明：

name ：TCC参与者的名称，可自定义，但必须全局唯一。

commitMethod：指定二阶段 Confirm 方法的名称，可自定义。

rollbackMethod：指定二阶段 Cancel 方法的名称，可自定义。

4、TCC 方法参数说明：

Try：第一个参数类型必须是BusinessActionContext，后续参数的个数和类型可以自定义。

Confirm：有且仅有一个参数，参数类型必须是 BusinessActionContext，后续为相应的参数名（businessActionContext）。

Cancel：有且仅有一个参数，参数类型必须是 BusinessActionContext，后续为相应的参数名（businessActionContext）。

5、TCC 方法返回类型说明：

一阶段的 Try 方法可以为 boolean 类型，也可以自定义返回值。

二阶段的 Confirm 和 Cancel 方法的返回类型必须为 boolean 类型。

6、各接口作用：(下面的demo实际上并没有严格按照这个方式来执行，建议生产环境按照如下步骤保证，要建立一张资源预留表用于锁住资源，可以参考这篇文章，原生TCC实现)

可以参考demo，原生TCC实现 https://github.com/prontera/spring-cloud-rest-tcc/tree/readme-img，里面就建立了一张资源表，用于try阶段，预留资源。

Try：初步操作。完成所有业务检查，预留必须的业务资源。（比如select for update 锁住某条记录，预留指定资源）

Confirm：确认操作。真正执行的业务逻辑（比如根据try的数据，更新库存之类的操作），不作任何业务检查，只使用 Try 阶段预留的业务资源。因此，只要 Try 操作成功， Confirm 必定能成功。另外，Confirm 操作需满足幂等性，保证一笔分布式事务能且只能成功一次。

Cancel：取消操作。释放 Try 阶段预留的业务资源。同样的，Cancel 操作也需要满足幂等性


## 环境说明
```yaml
mysql: 8.0.12
nacos: 2.0.3
seata: 1.3.0
```

## 工程目录结构

```
springcloud-seata
├── common-parent              // 父POM工程
├── common-service             // 公共服务
├── at-spring-cloud            // AT 模式工程示例
│  ├── at-account-service      // AT-账户服务
│  ├── at-business-service     // AT-业务服务
│  ├── at-order-service        // AT-订单服务
│  └── at-product-service      // AT-仓库服务
├── saga-spring-cloud          // SAGA 模式工程示例 
│  ├── saga-account-service    // SAGA-账户服务
│  ├── saga-business-service   // SAGA-业务服务
│  ├── saga-order-service      // SAGA-订单服务
│  └── saga-product-service    // SAGA-仓库服务
├── tcc-spring-cloud           // TCC 模式工程示例 
│  ├── tcc-account-service     // TCC-账户服务
│  ├── tcc-business-service    // TCC-业务服务
│  ├── tcc-order-service       // TCC-订单服务
│  └── tcc-product-service     // TCC-仓库服务
├── tcc-transfer               // TCC 模式转账工程示例
│  ├── sql                     // 转账示例 sql 脚本
│  ├── tcc-transfer-in         // 收钱方服务 
│  └── tcc-trnasfer-out        // 转账方服务
└── sql                       // 订单库存示例 sql
```



### SAGA 模式下的流程图
