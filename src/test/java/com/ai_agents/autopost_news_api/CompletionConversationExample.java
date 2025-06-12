
package com.ai_agents.autopost_news_api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.*;

import chat.giga.client.GigaChatClient;
import chat.giga.client.auth.AuthClient;
import chat.giga.client.auth.AuthClientBuilder.OAuthBuilder;
import chat.giga.model.ModelName;
import chat.giga.model.Scope;
import chat.giga.model.completion.ChatMessageRole;
import chat.giga.model.completion.CompletionRequest;
import chat.giga.model.completion.CompletionResponse;
import chat.giga.model.completion.ChatMessage;
public class CompletionConversationExample {

    public static void main(String[] args) throws IOException {
        
        // Получение данных из config.ini
        String[] config = get_config();
        final String KEY = config[0];

        GigaChatClient client = GigaChatClient.builder()
                .verifySslCerts(false)
                .authClient(AuthClient.builder()
                        .withOAuth(OAuthBuilder.builder()
                                .scope(Scope.GIGACHAT_API_PERS)
                                .authKey(KEY)
                                .build())
                        .build())
                .build();
        CompletionRequest.CompletionRequestBuilder builder = CompletionRequest.builder()
                .model(ModelName.GIGA_CHAT_MAX_2);
        builder = builder.message(ChatMessage.builder()
                        .content("Напиши анекдот до 15-ти слов")
                        .role(ChatMessageRole.SYSTEM)
                        .build());
        CompletionRequest request = builder.build();
        CompletionResponse response = client.completions(request);
        System.out.println(response.choices().get(0).message().content());
        builder = builder.message(ChatMessage.builder()
                .content("напиши текст моего последнего вопроса")
                .role(ChatMessageRole.USER).build());
        request = builder.build();
        response = client.completions(request);
        System.out.println(response.choices().get(0).message().content());

    }


    public static void func1() {

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