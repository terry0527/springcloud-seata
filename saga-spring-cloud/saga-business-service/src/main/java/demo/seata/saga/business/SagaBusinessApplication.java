package demo.seata.saga.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SagaBusinessApplication
 *
 */
@EnableFeignClients
@SpringBootApplication
public class SagaBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(SagaBusinessApplication.class, args);
    }
}
