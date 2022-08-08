package demo.seata.saga.account.service;

/**
 * AccountService
 *
 */
public interface AccountService {

    Boolean reduceBalance(Long userId, Integer amount) throws Exception;

    Boolean compensateBalance(Long userId, Integer amount) throws Exception;
}
