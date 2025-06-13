package com.ai_agents.autopost_news_api;
import java.io.IOException;
// import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;
// import java.io.*;
// import java.sql.SQLException;

public class Test_database {
    public static void main(String[] args) throws IOException {
        Database db = new Database();
        db.connect();
    //     db.send("INSERT INTO users (login, password, token) VALUES('ksks3@mail.ru', '$2a$10$ixJCYxZXMHFhe4sF0SjXSuhYgKX2BlPxZntuTjbuhVVkE72ewFMYa', 'best token');");
        List<Map<String, Object>> res = db.get("SELECT * FROM users WHERE login = 'ksks2@mail.ru';");
        System.out.println(res);
    //     for (Map<String, Object> row : res) {
    //         System.out.println("login: " + row.get("login"));
    //         System.out.println("Name: " + row.get("name"));
    //         System.out.println("Age: " + row.get("age"));
    //         System.out.println("Grade: " + row.get("grade"));
    //     }
    }
}
