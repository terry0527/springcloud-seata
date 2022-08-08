package demo.seata.tcc.product.controller;

import demo.seata.tcc.product.service.ProductService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 仓库服务
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getPrice")
    public Integer getPrice(@RequestParam("productId") Long productId){
        return productService.getPriceById(productId);
    }

    @PostMapping("/try")
    public boolean productTry(@RequestBody BusinessActionContext actionContext,
                              @RequestParam("productId") Long productId,
                              @RequestParam("count") Integer count){
        return productService.productTry(actionContext, productId, count);
    }

    @PostMapping("/confirm")
    public boolean productConfirm(@RequestBody BusinessActionContext actionContext){
        return productService.productConfirm(actionContext);
    }

    @PostMapping("/cancel")
    public boolean productCancel(@RequestBody BusinessActionContext actionContext){
        return productService.productCancel(actionContext);
    }

}
