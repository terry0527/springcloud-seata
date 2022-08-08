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

    @TwoPhaseBusinessAction(name = "orderService", commitMethod = "orderConfirm", rollbackMethod = "orderCancel")
    boolean orderTry(BusinessActionContext actionContext,
                     @BusinessActionContextParameter(paramName = "orderId") Long orderId,
                     @BusinessActionContextParameter(paramName = "userId") Long userId,
                     @BusinessActionContextParameter(paramName = "productId") Long productId,
                     @BusinessActionContextParameter(paramName = "count") Integer count,
                     @BusinessActionContextParameter(paramName = "payAmount") Integer payAmount);

    boolean orderConfirm(BusinessActionContext actionContext);

    boolean orderCancel(BusinessActionContext actionContext);
}
