
package com.ai_agents.autopost_news_api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.*;
// import java.io.*;

import chat.giga.client.GigaChatClient;
import chat.giga.client.auth.AuthClient;
import chat.giga.client.auth.AuthClientBuilder;
import chat.giga.http.client.HttpClientException;
import chat.giga.model.ModelName;
import chat.giga.model.Scope;
import chat.giga.model.completion.ChatMessage;
import chat.giga.model.completion.CompletionRequest;
import chat.giga.model.completion.CompletionResponse;
import chat.giga.model.completion.ChatMessageRole;

public class CompletionConversationExample {

    public static void main(String[] args) throws IOException {
        
        // Получение данных из config.ini
        String[] config = get_config();
        final String KEY = config[0];
        final String ID = config[1];
        System.out.println(KEY);
        GigaChatClient client = GigaChatClient.builder()
                .logRequests(true)
                .logResponses(true)
                .authClient(AuthClient.builder()
                        .withOAuth(AuthClientBuilder.OAuthBuilder.builder()
                                .scope(Scope.GIGACHAT_API_PERS)
                                .clientId(ID)
                                .clientSecret(KEY)
                                .build())
                        .build())
                .build();

        CompletionRequest.CompletionRequestBuilder builder = CompletionRequest.builder()
                .model(ModelName.GIGA_CHAT_PRO)
                .message(ChatMessage.builder()
                        .content("Отвечай как программист")
                        .role(ChatMessageRole.SYSTEM)
                        .build())
                .message(ChatMessage.builder()
                        .content("Что мне не надо делать, чтобы случайно не захватить мир?")
                        .role(ChatMessageRole.USER).build());

        try {
            for (int i = 0; i < 4; i++) {
                CompletionRequest request = builder.build();
                CompletionResponse response = client.completions(request);
                System.out.println(response);

                response.choices().forEach(e -> builder.message(e.message().ofAssistantMessage()));

                builder.message(ChatMessage.builder()
                        .content("А почему так? Будь еще более точным в формулировках")
                        .role(ChatMessageRole.USER).build());
            }
        } catch (HttpClientException ex) {
            System.out.println(ex.statusCode() + " " + ex.bodyAsString());
        }
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