package demo.seata.tcc.account.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @description: 账户服务 Interface
 **/
@LocalTCC
public interface AccountService {

    @TwoPhaseBusinessAction(name = "accountService", commitMethod = "accountConfirm", rollbackMethod = "accountCancel")
    boolean accountTry(BusinessActionContext actionContext,
                       @BusinessActionContextParameter(paramName = "userId") Long userId,
                       @BusinessActionContextParameter(paramName = "price") Integer price);

    boolean accountConfirm(BusinessActionContext actionContext);

    boolean accountCancel(BusinessActionContext actionContext);
}
