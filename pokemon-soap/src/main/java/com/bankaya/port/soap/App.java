package com.bankaya.port.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.ws.config.annotation.WsConfigurationSupport;

@SpringBootApplication
@ImportResource(locations = {"applicationContext.xml"})
public class App extends WsConfigurationSupport {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
