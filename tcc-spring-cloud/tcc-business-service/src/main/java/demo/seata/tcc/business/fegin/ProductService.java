package demo.seata.tcc.business.fegin;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @description: tcc-product-service 服务的 Feign 客户端
 **/
@FeignClient("tcc-product-service")
@RequestMapping("/product")
public interface ProductService {

    @PostMapping("/try")
    boolean productTry(@RequestBody BusinessActionContext actionContext,
                       @RequestParam("productId") Long productId,
                       @RequestParam("count") Integer count);

    @PostMapping("/confirm")
    boolean productConfirm(@RequestBody BusinessActionContext actionContext);

    @PostMapping("/cancel")
    boolean productCancel(@RequestBody BusinessActionContext actionContext);

    @GetMapping("/getPrice")
    Integer getPrice(@RequestParam("productId") Long productId);
}
