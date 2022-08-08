package demo.seata.at.account.controller;



import demo.seata.at.account.service.AccountService;
import demo.seata.common.dto.AccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: AccountController
 **/
@RestController
@RequestMapping("/account")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/reduce-balance")
    public boolean reduceBalance(@RequestBody AccountDTO accountDTO) throws Exception {
        logger.info("[reduceBalance] 收到扣减余额请求, 用户:{}, 金额:{}", accountDTO.getUserId(), accountDTO.getAmount());
        return accountService.reduceBalance(accountDTO.getUserId(), accountDTO.getAmount());
    }

}
