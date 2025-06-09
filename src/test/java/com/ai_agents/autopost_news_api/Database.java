package com.ai_agents.autopost_news_api;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.*;
import java.io.*;
public class Database {
    //Создание экземляра JdbcTemplate, с помощью которого происходит взаимодействие с бд. 
    //Значение присваивается во время создания подключения. function: connect
    private static JdbcTemplate db;

    /**
     * Функция позволяет подключить базу данных. Данные для подключения находятся в файле config.ini
     * @param None
    */
    public void connect() throws IOException {
        
        // Получение данных из config.ini
        String[] config = get_config();
        final String Username = config[0];
        final String Userpass = config[1];
        final String Server_ip = config[2];
        final String DB_name = config[3];

        // Подключение к БД 
        DataSource dataSource = new DriverManagerDataSource(
                "jdbc:postgresql://"+Server_ip+"/"+DB_name,
                Username,
                Userpass
        );
        // Создание объекта JdbcTemplate
        this.db = new JdbcTemplate(dataSource);
        System.out.println("Таблица успешно подключена!");
    }


    /**
     * Функция для получения данный из config.ini
     * @param None
     * @return Массив данных, необходимых для подключения к бд
     * @throws IOException
     */
    private static String[] get_config() throws IOException {
        Properties properties = new Properties();    
        // Открываем файл config.ini для чтения
        InputStream inputStream = new FileInputStream("src/test/java/com/ai_agents/autopost_news_api/config.ini");
        // Читаем данные из файла
        properties.load(inputStream);
        final String Username = properties.getProperty("username", "postgresql");
        final String Userpass = properties.getProperty("userpassword", "postgresql");
        final String Server_ip = properties.getProperty("ip", "postgresql");
        final String DB_name = properties.getProperty("name_db", "postgresql");
        String[] out = {Username, Userpass, Server_ip, DB_name};
        return out;
    }


    /**
    Позволяет отправлять запросы в таблицу, не ожидая обратные данные
    @param execute<String> - запрос, передаваемый в таблицу(в формате posgresql)
    @return true - запрос успешно выполнен, false - во время выполнения произошла ошибка
    */
    public boolean send(String execute) {
        try {
            this.db.execute(execute);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    /**
     * Позволяет отправлять запросы в таблицу, ожидая обратные данные
     * @param execute - запрос, передаваемый в таблицу(в формате posgresql)
     * @return List<Map<String, Object>> - запрашиваемые данные, null в случае ошибки
     */
    public List<Map<String, Object>> get(String execute) { 
        try{
        List<Map<String, Object>> rows = this.db.queryForList("SELECT * FROM students");
        return rows;
        } catch (Exception e) {
            System.out.println("|ERROR| "+e);
            return null;
        }
    }
}

