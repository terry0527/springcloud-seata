package demo.seata.tcc.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 账户服务启动类
 **/
@SpringBootApplication
public class TCCAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(TCCAccountApplication.class, args);
    }
}
