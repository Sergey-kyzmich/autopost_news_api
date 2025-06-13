package com.ai_agents.autopost_news_api;
import java.util.*;
public class StartApi {
    public void createTable(Database db) {
        String execute = "CREATE TABLE IF NOT EXISTS users (login TEXT PRIMARY KEY, password TEXT, token TEXT);";
        db.send(execute);
    }
}
