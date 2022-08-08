package demo.seata.at.business.fegin;

import demo.seata.common.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @className: ProductService
 **/
@FeignClient("at-product-service")
@RequestMapping("/product")
public interface ProductService {

    @PostMapping("/reduce-stock")
    boolean reduceStock (@RequestBody ProductDTO productDTO);

    @GetMapping("/getPrice")
    Integer getPrice(@RequestParam("productId") Long productId);

}
