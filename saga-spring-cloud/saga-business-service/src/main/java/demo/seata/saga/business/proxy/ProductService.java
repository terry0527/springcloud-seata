package demo.seata.saga.business.proxy;

import demo.seata.saga.business.config.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ProductService
 *
 */
@FeignClient(value = "saga-product-service", configuration = {FeignErrorDecoder.class})
@RequestMapping("/product")
public interface ProductService {

    @RequestMapping("/reduceStock")
    Boolean reduceStock(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) throws Exception;

    @RequestMapping("/compensateStock")
    Boolean compensateStock(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) throws Exception;

    @GetMapping("/getPrice")
    Integer getPrice(@RequestParam("productId") Long productId);

}
