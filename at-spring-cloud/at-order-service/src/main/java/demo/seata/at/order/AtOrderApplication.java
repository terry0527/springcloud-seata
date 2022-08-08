package demo.seata.at.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AtOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtOrderApplication.class, args);
    }

}
