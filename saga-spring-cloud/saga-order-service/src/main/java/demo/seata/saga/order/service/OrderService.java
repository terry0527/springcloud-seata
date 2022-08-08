package demo.seata.saga.order.service;


/**
 * OrderService
 *
 */
public interface OrderService {

    Boolean createOrder(Long orderId, Long userId, Long productId, Integer amount, Integer count) throws Exception;

    Boolean revokeOrder(Long orderId) throws Exception;

}
