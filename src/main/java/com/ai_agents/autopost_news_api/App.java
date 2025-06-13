package com.ai_agents.autopost_news_api;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class App {
    public static Database db = new Database();


    public static void main(String[] args) throws IOException{
        StartApi StApi = new StartApi();
        db.connect();
        System.out.println("Dir = "+System.getProperty("user.dir"));
        StApi.createTable(db);
        SpringApplication.run(App.class, args);
    }


    @PostMapping("/auth")
    public String auth(@RequestParam(value = "login", required = false) String login,@RequestParam(value = "password", required = false) String password) {
        String token = Authorization.auth(login, password, db);
        System.out.println(login+"|"+password+"|"+token);
        if (token != null){
            return token;
        } else {
            throw new ResponseStatusException(HttpStatus.valueOf(403), "Ошибка авторизации. Неверный логин или пароль.");
        }
    }

    // Эхо для POST запроса
    @PostMapping("/echo")
    public String echoPost(@RequestBody(required = false) String body) {
        return "POST Echo: " + (body != null ? body : "No body provided");
    }

    // Эхо для POST запроса
    @GetMapping("/echo")
    public String echoGet(@RequestBody(required = false) String body) {
        return "Get Echo: " + (body != null ? body : "No body provided");
    }

    // Эхо для PUT запроса
    @PutMapping("/echo")
    public String echoPut(@RequestBody(required = false) String body) {
        return "PUT Echo: " + (body != null ? body : "No body provided");
    }
} 