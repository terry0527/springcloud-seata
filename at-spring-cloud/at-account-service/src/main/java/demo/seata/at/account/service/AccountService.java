package demo.seata.at.account.service;

/**
 * @description: AccountService
 **/
public interface AccountService {

    /**
     * 扣除余额
     *
     * @param userId 用户编号
     * @param amount  扣减金额
     * @throws Exception 失败时抛出异常
     */
    boolean reduceBalance(Long userId, Integer amount) throws Exception;

}
