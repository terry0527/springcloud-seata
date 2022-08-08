package demo.seata.tcc.product.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @description: 仓库服务
 **/
@LocalTCC
public interface ProductService {

    @TwoPhaseBusinessAction(name = "productService", commitMethod = "productConfirm", rollbackMethod = "productCancel")
    boolean productTry(BusinessActionContext actionContext,
                       @BusinessActionContextParameter(paramName = "productId") Long productId,
                       @BusinessActionContextParameter(paramName = "count") Integer count);

    boolean productConfirm(BusinessActionContext actionContext);

    boolean productCancel(BusinessActionContext actionContext);

    Integer getPriceById(Long productId);
}
