package demo.seata.tcc.business.fegin;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @description: tcc-order-service 服务的 Feign 客户端
 **/
@FeignClient("tcc-order-service")
@RequestMapping("/order")
public interface OrderService {

    @PostMapping("/try")

    boolean orderTry(@RequestBody BusinessActionContext actionContext,
                     @RequestParam("orderId") Long orderId,
                     @RequestParam("userId") Long userId,
                     @RequestParam("productId") Long productId,
                     @RequestParam("count") Integer count,
                     @RequestParam("payAmount") Integer payAmount);

    @PostMapping("/confirm")
    boolean orderConfirm(@RequestBody BusinessActionContext actionContext);

    @PostMapping("/cancel")
    boolean orderCancel(@RequestBody BusinessActionContext actionContext);

}
