package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        /**
         * Todo: Set up database on servlet startup
         **/
        SpringApplication.run(Application.class, args);
    }

}
