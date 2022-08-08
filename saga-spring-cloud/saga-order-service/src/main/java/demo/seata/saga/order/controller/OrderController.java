package demo.seata.saga.order.controller;

import demo.seata.saga.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * OrderController
 *
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/createOrder")
    public Boolean createOrder(@RequestParam("orderId") Long orderId,
                               @RequestParam("userId") Long userId,
                               @RequestParam("productId") Long productId,
                               @RequestParam("amount") Integer amount,
                               @RequestParam("count") Integer count) throws Exception {

        return orderService.createOrder(orderId, userId, productId, amount, count);
    }

    @RequestMapping("/revokeOrder")
    public Boolean revokeOrder(@RequestParam("orderId") Long orderId) throws Exception {
        return orderService.revokeOrder(orderId);
    }

}
