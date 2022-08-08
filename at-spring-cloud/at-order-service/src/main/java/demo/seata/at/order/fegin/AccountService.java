package demo.seata.at.order.fegin;

import demo.seata.common.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "at-account-service")
@RequestMapping("/account")
public interface AccountService {

    @PostMapping("/reduce-balance")
    boolean reduceBalance(@RequestBody AccountDTO accountDTO);

}
