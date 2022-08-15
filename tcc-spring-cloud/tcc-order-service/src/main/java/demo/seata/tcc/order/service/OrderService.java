package demo.seata.tcc.order.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @description: 订单服务
 **/
@LocalTCC
public interface OrderService {

    /**
     * @TwoPhaseBusinessAction 注解try方法，其中name为当前tcc方法的bean名称，写方法名便可（记得全局唯一），
     * commitMethod指向提交方法，rollbackMethod指向事务回滚方法。
     * 指定好三个方法之后，seata会根据全局事务的成功或失败，去帮我们自动调用提交方法或者回滚方法。
     * @param actionContext
     * @param orderId
     * @param userId
     * @param productId
     * @param count
     * @param payAmount
     * @return
     */
    @TwoPhaseBusinessAction(name = "orderService", commitMethod = "orderConfirm", rollbackMethod = "orderCancel")
    boolean orderTry(BusinessActionContext actionContext,
                     @BusinessActionContextParameter(paramName = "orderId") Long orderId,
                     @BusinessActionContextParameter(paramName = "userId") Long userId,
                     @BusinessActionContextParameter(paramName = "productId") Long productId,
                     @BusinessActionContextParameter(paramName = "count") Integer count,
                     @BusinessActionContextParameter(paramName = "payAmount") Integer payAmount);

    /**
     * Commit boolean.
     * 这个方法需要保持幂等和防悬挂
     * @param actionContext
     * @return
     */
    boolean orderConfirm(BusinessActionContext actionContext);

    /**
     * Rollback boolean.
     * 这个方法需要保持幂等和防悬挂
     * @param actionContext
     * @return
     */
    boolean orderCancel(BusinessActionContext actionContext);
}
