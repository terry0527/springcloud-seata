package demo.seata.saga.account.controller;

import demo.seata.saga.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/reduceBalance")
    Boolean reduceBalance(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount) throws Exception {
        return accountService.reduceBalance(userId, amount);
    }

    @RequestMapping("/compensateBalance")
    Boolean compensateBalance(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount) throws Exception {
        return accountService.compensateBalance(userId, amount);
    }
}
