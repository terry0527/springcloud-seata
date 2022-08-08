package demo.seata.tcc.in.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @description: 收钱方
 **/
@LocalTCC
public interface IInAccountService {

    @TwoPhaseBusinessAction(name = "IInAccountService", commitMethod = "inConfirm", rollbackMethod = "inCancel", useTCCFence = true)
    boolean inTry(@BusinessActionContextParameter(paramName = "inId") String inId,
                  @BusinessActionContextParameter(paramName = "amount") String amount);

    @TwoPhaseBusinessAction(name = "IInAccountService", commitMethod = "inConfirm", rollbackMethod = "inCancel", useTCCFence = true, isDelayReport = true)
    boolean inTry2(String inId, String amount);

    boolean inConfirm( BusinessActionContext actionContext);

    boolean inCancel( BusinessActionContext actionContext);

    boolean reset(int number);

}
