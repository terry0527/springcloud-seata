package demo.seata.at.business.fegin;


import demo.seata.common.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: OrderService
 **/
@FeignClient("at-order-service")
@RequestMapping("/order")
public interface OrderService {

    @PostMapping("/create-order")
    boolean createOrder(@RequestBody OrderDTO orderDTO);

}
