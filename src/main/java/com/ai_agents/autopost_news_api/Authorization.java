package com.ai_agents.autopost_news_api;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.util.*;

public class Authorization {
    public static void main(String[] arg) {
        try {
        Database db = new Database();
        db.connect();
        String res = auth("", "", db);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static String auth(String login, String password, Database db) {
        
        String execute = "SELECT password, token FROM users WHERE login = "+login;
        List<Map<String, Object>> res = db.get(execute);
        return "";
    }
}
