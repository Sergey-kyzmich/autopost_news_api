package com.ai_agents.autopost_news_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Api {

    public static void main(String[] args) {
        SpringApplication.run(api.class, args);
    }


    // Эхо для GET запроса
    @GetMapping("/echo")
    public String echoGet(@RequestParam(required = false) String message) {
        return "GET Echo: "+ (message != null ? message : "No message provided");
    }

    // Эхо для POST запроса
    @PostMapping("/echo")
    public String echoPost(@RequestBody(required = false) String body) {
        return "POST Echo: " + (body != null ? body : "No body provided");
    }

    // Эхо для PUT запроса
    @PutMapping("/echo")
    public String echoPut(@RequestBody(required = false) String body) {
        return "PUT Echo: " + (body != null ? body : "No body provided");
    }
}