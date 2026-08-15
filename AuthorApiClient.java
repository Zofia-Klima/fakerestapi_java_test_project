import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class AuthorApiClient {

    public Author[] getAllAuthors() throws Exception {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://fakerestapi.azurewebsites.net/api/v1/Authors"))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        assert getResponse.statusCode() == 200 : "GET request for full list of authors failed. Status code: " + getResponse.statusCode();

        System.out.println("GET request for full list of authors completed successfully. Status code is correct (200).");

        Gson gson = new Gson();

        Author[] apiAuthors = gson.fromJson(getResponse.body(), Author[].class);

        return apiAuthors;
    }
}