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
        db.send("INSERT INTO users (login, password, token) VALUES('ksks@mail.ru', 'my pass', 'best token');");
        List<Map<String, Object>> res = db.get("SELECT * FROM users;");
        System.out.println(res);
        // for (Map<String, Object> row : res) {
        //     System.out.println("ID: " + row.get("id"));
        //     System.out.println("Name: " + row.get("name"));
        //     System.out.println("Age: " + row.get("age"));
        //     System.out.println("Grade: " + row.get("grade"));
        // }
    }
}
