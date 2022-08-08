package demo.seata.at.order.service;

import demo.seata.common.dto.OrderDTO;


public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO 订单DTO
     * @return 订单编号
     * @throws Exception 创建订单失败，抛出异常
     */
    boolean createOrder(OrderDTO orderDTO) throws Exception;

}
