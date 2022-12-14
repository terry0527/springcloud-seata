package demo.seata.at.order.controller;


import demo.seata.at.order.service.OrderService;
import demo.seata.common.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public boolean createOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        logger.info("[createOrder] 收到下单请求, 用户:{}, 商品:{}, 数量:{}", orderDTO.getUserId(), orderDTO.getProductId(), orderDTO.getCount());
        return orderService.createOrder(orderDTO);
    }

}
