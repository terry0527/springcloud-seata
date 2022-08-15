package demo.seata.saga.order.dao;


import demo.seata.common.dto.OrderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * OrderDao
 *
 */
@Mapper
@Repository
public interface OrderDao {

    @Insert("insert into orders (id, user_id, product_id, count, pay_amount, status) values (#{id}, #{userId}, #{productId}, #{count}, #{payAmount}, 1)")
    int createOrder(OrderDTO order);

    @Update("update orders set status = -1 where id = #{orderId}")
    int revokeOrder(@Param("orderId") Long orderId);
}
