package demo.seata.at.business.service.impl;

import demo.seata.at.business.fegin.OrderService;
import demo.seata.at.business.fegin.ProductService;
import demo.seata.at.business.service.BusinessService;
import demo.seata.common.dto.BusinessDTO;
import demo.seata.common.dto.OrderDTO;
import demo.seata.common.dto.ProductDTO;
import demo.seata.common.util.IDUtils;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: BusinessServiceImpl
 **/
@Service
public class BusinessServiceImpl implements BusinessService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Override
    @GlobalTransactional
    public String handleBusiness(BusinessDTO businessDTO) {

        logger.info("[handleBusiness] 开始下单");
        logger.info("[handleBusiness] 当前 XID: {}", RootContext.getXID());

        // 扣减库存
        boolean reduceStock = productService.reduceStock(new ProductDTO(businessDTO.getProductId(), businessDTO.getCount()));

        // 查询 商品单价
        Integer price = productService.getPrice(businessDTO.getProductId());
        Integer payAmount = price * businessDTO.getCount();

        // 生成订单 ID
        Long orderId = IDUtils.nextId();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderId);
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setProductId(businessDTO.getProductId());
        orderDTO.setCount(businessDTO.getCount());
        orderDTO.setPayAmount(payAmount);
        // 创建订单
        boolean createOrder = orderService.createOrder(orderDTO);

        if(!reduceStock || !createOrder){
            throw new RuntimeException("下单失败");
        }

        logger.info("[handleBusiness] 下单成功, 订单Id: "+ orderId);
        return "Place Order Success";
    }
}
