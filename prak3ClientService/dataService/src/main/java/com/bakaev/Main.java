package com.bakaev;

import com.bakaev.service.MessageListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

}
