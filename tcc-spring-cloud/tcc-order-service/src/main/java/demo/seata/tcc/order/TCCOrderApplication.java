package demo.seata.tcc.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 订单服务
 **/
@SpringBootApplication
@EnableFeignClients
public class TCCOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TCCOrderApplication.class, args);
    }
}
