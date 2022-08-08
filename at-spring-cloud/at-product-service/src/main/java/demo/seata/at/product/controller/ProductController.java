package demo.seata.at.product.controller;

import demo.seata.at.product.service.ProductService;
import demo.seata.common.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/getPrice")
    public Integer getPrice(@RequestParam("productId") Long productId){
        return productService.getPriceById(productId);
    }

    @PostMapping("/reduce-stock")
    public boolean reduceStock(@RequestBody ProductDTO productDTO) throws Exception {
        logger.info("[reduceStock] 收到减少库存请求, 商品:{}, 数量:{}", productDTO.getProductId(), productDTO.getCount());
        return productService.reduceStock(productDTO.getProductId(), productDTO.getCount());
    }

}
