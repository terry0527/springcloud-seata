package demo.seata.tcc.order.fegin;


import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: tcc-account-service 服务的 Feign 客户端
 **/
@FeignClient("tcc-account-service")
@RequestMapping("/account")
public interface AccountService {

    @PostMapping("/try")

    boolean accountTry(@RequestBody BusinessActionContext actionContext,
                       @RequestParam("userId") Long userId,
                       @RequestParam("price") Integer price);

    @PostMapping("/confirm")
    boolean accountConfirm(@RequestBody BusinessActionContext actionContext);

    @PostMapping("/cancel")
    boolean accountCancel(@RequestBody BusinessActionContext actionContext);

}
