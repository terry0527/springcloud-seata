package demo.seata.at.order.service.impl;

import demo.seata.at.order.dao.OrderDao;
import demo.seata.at.order.fegin.AccountService;
import demo.seata.at.order.service.OrderService;
import demo.seata.common.dto.AccountDTO;
import demo.seata.common.dto.OrderDTO;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountService accountService;

    @Override
    public boolean createOrder(OrderDTO orderDTO) throws Exception {
        logger.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // 扣减余额
        boolean reduceBalance = accountService.reduceBalance(new AccountDTO(orderDTO.getUserId(), orderDTO.getPayAmount()));

        if(!reduceBalance){
            throw new RuntimeException("扣减余额失败");
        }

        // 保存订单
        logger.info("[createOrder] 开始创建订单: {}", orderDTO.toString());
        int saveOrder = orderDao.saveOrder(orderDTO);
        if(saveOrder == 0){
            logger.warn("[createOrder] 创建订单 {} 失败", orderDTO.toString());
            throw new Exception("创建订单失败");
        }
        logger.info("[createOrder] 保存订单: {}", orderDTO.getId());

        return true;
    }

}
