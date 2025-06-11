
package com.ai_agents.autopost_news_api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.*;
// import java.io.*;

import chat.giga.langchain4j.GigaChatChatModel;
import chat.giga.client.auth.AuthClient;
import chat.giga.client.auth.AuthClientBuilder;
import chat.giga.langchain4j.GigaChatChatRequestParameters;
import chat.giga.model.ModelName;
import chat.giga.model.Scope;
public class CompletionConversationExample {

    public static void main(String[] args) throws IOException {
        
        // Получение данных из config.ini
        String[] config = get_config();
        final String KEY = config[0];

        GigaChatChatModel model = GigaChatChatModel.builder()
        .defaultChatRequestParameters(GigaChatChatRequestParameters.builder()
                .modelName(ModelName.GIGA_CHAT_PRO)
                .build())
        .authClient(AuthClient.builder()
                .withOAuth(AuthClientBuilder.OAuthBuilder.builder()
                        .scope(Scope.GIGACHAT_API_PERS)
                        .authKey(KEY)
                        .build())
                .build())
        .logRequests(true)
        .logResponses(true)
        .build();


      String answer = model.chat("Что мне не надо делать, чтобы случайно не захватить мир?");
      System.out.println(answer); // Pulp Fiction, Kill Bill, etc.
    }


     private static String[] get_config() throws IOException {
        Properties properties = new Properties();    
        // Открываем файл config.ini для чтения
        InputStream inputStream = new FileInputStream("src/test/java/com/ai_agents/autopost_news_api/config.ini");
        // Читаем данные из файла
        properties.load(inputStream);
        final String key = properties.getProperty("key", "gigachat");
        final String id = properties.getProperty("client_id", "gigachat");
        String[] out = {key,id};
        return out;
    }


}