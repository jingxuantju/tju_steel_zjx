package tju.steel.zjx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"tju.steel"})
public class ZjxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZjxApplication.class, args);
    }

}

