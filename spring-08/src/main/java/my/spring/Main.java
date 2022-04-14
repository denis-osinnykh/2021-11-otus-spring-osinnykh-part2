package my.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.Console;

@EnableMongock
@SpringBootApplication
@EnableConfigurationProperties
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
//        Console.main(args);
    }
}


