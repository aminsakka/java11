import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoServerShould {

    private static final String HOST = "localhost" ;
    private static final int PORT = 7001;
    private static final String BASE = "http://" + HOST + ":" + PORT;

    private static final String URL1 = "https://fr.wikipedia.org/wiki/France";
    private static final String URL2 = "https://www.wikimedia.fr/articles-wikipedia-les-plus-lus-2019/";
    private static final String URL3 = "https://fr.wikipedia.org/wiki/Saison_8_de_Game_of_Thrones";
    private static final String URL4 = "https://fr.wikipedia.org/wiki/Kylian_Mbapp%C3%A9";
    private static final String URL5 = "https://fr.wikipedia.org/wiki/Cath%C3%A9drale_Notre-Dame_de_Paris";

    private static final String PARIS = "Paris";
    private static final String INTERNATIONAL = "International";

    private static final List<URI> URLS = Stream.
            of(URL1, URL2, URL3, URL4, URL5).
            map(URI::create).
            collect(Collectors.toList());

    @Test
    public void should_follow_redirect() throws IOException, InterruptedException {
        final HttpClient client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        final HttpRequest request = buildHttpRequest("/hello-redir");

        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int expectedCode = 200;
        assertThat(response.statusCode()).isEqualTo(expectedCode);

        final String expectedBody = "You are redirected to the new web site.";
        assertThat(response.body()).isEqualTo(expectedBody);
    }

    /* From Input Stream

     */


    //POST JSON FROM FILE
    //Use BodyPublishers to Post the request
    @Test
    public void should_post_request_from_json_file() throws IOException, InterruptedException {
        final HttpClient client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:7001/orders"))
                .header("Content-Type","application/json")
                .build();

        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int expectedCode = 201;
        assertThat(response.statusCode()).isEqualTo(expectedCode);
    }

    @Test
    public void blocking_search_Paris_in_URL1_should_return_true(){
        final HttpClient CLIENT = HttpClient.newBuilder().build();

        boolean found = blockingSearch(CLIENT, URI.create(URL1), PARIS);

        assertThat(true).isEqualTo(found);
    }

    @Test
    public void non_blocking_search_Paris_in_URL1_should_return_true() throws ExecutionException, InterruptedException, TimeoutException {
        final HttpClient CLIENT = HttpClient.newBuilder().build();

        CompletableFuture<String> result =
                asynchronousSearch(CLIENT, URI.create(URL1), PARIS);

        assertThat(result.get()).
                isEqualTo("Completed " + URL1 + " / found: true");

    }


    @Test
    public void should_use_blocking_search(){
        final HttpClient CLIENT = HttpClient.newBuilder().build();

        Instant now1 = Instant.now();

        URLS.forEach(url -> {
            boolean found = blockingSearch(CLIENT, url, INTERNATIONAL);
            System.out.println("Completed " + url + " / found: " + found);
        });
        Instant now2 = Instant.now();

        long duration =  Duration.between(now1, now2).getSeconds();
        System.out.println(" Duration : " + duration);
        assertThat(duration).isGreaterThan(1);
    }

    @Test
    public void should_call_non_blocking_search_in_parallal_stream(){
        final HttpClient CLIENT = HttpClient.newBuilder().build();

        Instant now1 = Instant.now();

        //Do some stuff here to stream on all the URIs and get the array initialized.
        //call asynchronousSearch
        CompletableFuture[] futures = null;

        Instant now2 = Instant.now();
        long duration1 =  Duration.between(now1, now2).getSeconds();

        System.out.println(" Duration 1 (send asyn request) : " + duration1 + " seconds.");
        assertThat(duration1).isZero();

        //Do some stuff here to ensure that all the async tasks are finished


        Instant now3 = Instant.now();
        long duration2 =  Duration.between(now1, now3).getSeconds();

        System.out.println(" Duration 2 : (receive all responses) : " + duration2 + " seconds.");
        assertThat(duration2).isGreaterThan(1);
    }


    private CompletableFuture<String> asynchronousSearch(HttpClient client, URI uri, String search) {

        CompletableFuture<String> futureResult = new CompletableFuture<String>();
        futureResult.complete("Not yet implemented");
        return futureResult;
    }


    private boolean blockingSearch(HttpClient client, URI uri, String search) {
        return false ;
    }

    private  HttpRequest buildHttpRequest(String route) {
        return HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create(BASE + route))
                .build();
    }
}