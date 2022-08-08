package demo.seata.saga.business.proxy;

import demo.seata.saga.business.config.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * AccountService
 *
 */
@FeignClient(value = "saga-account-service", configuration = {FeignErrorDecoder.class})
@RequestMapping("/account")
public interface AccountService {

    @RequestMapping("/reduceBalance")
    Boolean reduceBalance(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount) throws Exception;

    @RequestMapping("/compensateBalance")
    Boolean compensateBalance(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount) throws Exception;
}
