package com.tedu.server;

import com.tedu.server.controller.WeelkPointController;
import com.tedu.server.service.WeeklyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}

