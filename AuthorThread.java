import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.Gson;

public class AuthorThread extends Thread {
    private Author author;

    public AuthorThread(Author author){
        this.author = author;
    }

    @Override
    public void run(){
        try{
            Gson gson = new Gson();
	        String jsonRequest = gson.toJson(author);

            // POST section
            HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://fakerestapi.azurewebsites.net/api/v1/Authors"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonRequest))
                .build();
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());

            // fakerestapi does not actually create a resource, thus here I am only asserting if the response code is 200 OK
            assert postResponse.statusCode() == 200 : "POST failed for author: " + author.getFirstName() + " " + author.getLastName();
            System.out.println("POST status code is correct for author " + author.getFirstName() + " " + author.getLastName());

            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println("API Response: " + postResponse.body());
            System.out.println("Status:" + postResponse.statusCode());
            System.out.println();

            // GET section
            HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://fakerestapi.azurewebsites.net/api/v1/Authors/" + author.getId()))
                .header("Accept", "application/json")
                .GET()
                .build();

            HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());

            Author getAuthor = gson.fromJson(getResponse.body(), Author.class);
            
            // I have decided to anyway 'test' API as if it actually was creating a resource. As expected, only the 'else' statement passes.
            if (author.getId().equals(getAuthor.getId())
                && author.getIdBook().equals(getAuthor.getIdBook())
                && author.getFirstName().equals(getAuthor.getFirstName())
                && author.getLastName().equals(getAuthor.getLastName())) {

                System.out.println("Author has been created successfuly.");

            } else {

                System.out.println("Author data is incorrect/Author " + author.getFirstName() + " " + author.getLastName() + " has not been created.");
            }
        }catch (Exception e) {
            e.printStackTrace();
            }
    }
}
