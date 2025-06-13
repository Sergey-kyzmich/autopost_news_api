package com.ai_agents.autopost_news_api;
import org.mindrot.jbcrypt.BCrypt;
import java.util.*;

public class Authorization {
    // public static void main(String[] arg) {
    //     try {
    //     Database db = new Database();
    //     db.connect();
    //     String res = auth("ksks2@mail.ru", "my pass", db);
    //     if (res != null) {
    //         System.out.println(true);
    //     } else {
    //         System.out.println(false);
    //     }
    //     } catch (IOException e) {
    //         System.out.println(e);
    //     }
    // }

    /**
     * Функция проверки данных, переданных пользователем
     * @param login String
     * @param password String
     * @param db Database - Объект класса Database(с установленым подключением. см Database.connect() )
     * @return token - В случае если данные верны, иначе None
    */
    public static String auth(String login, String password, Database db) {
        // Формирование запроса
        String execute = String.format("SELECT password, token FROM users WHERE login = '%s'", login);
        // Отправка запроса/получение данных из бд
        List<Map<String, Object>> response = db.get(execute);
        // Проверка на совпадение 
        if (response != null && response.size()==1) {
            // Получение данных пользователя 
            Map<String, Object> res = response.get(0);
            // Получение пароля, который сохранен в таблице
            String hashPasswordFromTable = res.get("password").toString();
            //сравнивание паролей
            if (BCrypt.checkpw(password, hashPasswordFromTable)) {
                // Возвращятся токен пользователя в случае успешной авторизации
                return res.get("token").toString();
            } else {
                // Возвращается null в случае неудачной авторизации
                return null;
            }
        }
        return null;
    }
}
