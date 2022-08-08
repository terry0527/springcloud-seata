package demo.seata.saga.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SagaOrderApplication
 *
 */
@EnableFeignClients
@SpringBootApplication
public class SagaOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SagaOrderApplication.class, args);
    }

}
