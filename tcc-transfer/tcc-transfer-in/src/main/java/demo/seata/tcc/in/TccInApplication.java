package demo.seata.tcc.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 收钱方启动类
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class TccInApplication {

    public static void main(String[] args) {

        SpringApplication.run(TccInApplication.class, args);
    }
}
