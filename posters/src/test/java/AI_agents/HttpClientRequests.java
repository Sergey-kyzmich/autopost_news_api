package AI_agents;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.ini4j.Ini;
import java.io.File;
import java.io.IOException;

public class HttpClientRequests {
    public static String sendGetRequest(String url) throws Exception {
        try{
            Ini config = new Ini(new File("config.ini"));  
            // Auth_data auth_data = new Auth_data();
            String key = config.get("database", "url");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept", "application/json")
                    .header("RqUID", "546af61a-d507-4bb1-9819-a318cc35ed40")
                    .header("Authorization", "Basic <"+key+">")
                    .POST(HttpRequest.BodyPublishers.ofString("{}"))
                    .build();

            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());
            return response.body();
        } catch (IOException e) {
            return ""+e;
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "https://gigachat.devices.sberbank.ru/api/v1/models";
        String response = sendGetRequest(url);
        System.out.println(response);
    }
}