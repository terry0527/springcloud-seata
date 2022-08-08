package demo.seata.at.order.dao;


import demo.seata.common.dto.OrderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface OrderDao {

    /**
     * 插入订单记录
     *
     * @param order 订单
     * @return 影响记录数量
     */
    @Insert("INSERT INTO orders (id, user_id, product_id, count, pay_amount, status) VALUES (#{id}, #{userId}, #{productId}, #{count}, #{payAmount}, 1)")
    int saveOrder(OrderDTO order);

}
