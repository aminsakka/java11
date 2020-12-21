import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoServerTest {

    private static final String BASE = "http://localhost:7001" ;

    final HttpClient client = HttpClient.newHttpClient();

    @Test
    public void should_send_sync_request() throws IOException, InterruptedException {
        //GIVEN
        final HttpRequest request = buildHttpRequest("/v1/hello");

        //WHEN
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //THEN
        int expectedCode = 200;
        assertThat(response.statusCode()).isEqualTo(expectedCode);

        final String expectedBody = "Hello from v1 server !";
        assertThat(response.body()).isEqualTo(expectedBody);
    }

    @Test
    public void should_send_sync_request_check_header() throws IOException, InterruptedException {
        //GIVEN
        final HttpRequest request = buildHttpRequest("/v2/hello");

        //WHEN
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //THEN
        final String expectedContentType = "application/json" ;
        assertThat(response.headers().allValues("content-type").contains(expectedContentType));

        final String expectedServer = "Javalin";
        assertThat(response.headers().allValues("server").contains(expectedServer));
    }

    //Timeout
    @Test
    public void should_send_sync_request_with_timeout() throws IOException, InterruptedException {
        //GIVEN
        final HttpRequest
                request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(3))
                .uri(URI.create(BASE + "/v4/hello"))
                .build();

        //WHEN
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //THEN
        int expectedCode = 500;
        assertThat(response.statusCode()).isEqualTo(expectedCode);
    }

    //BodyHandler - Stream
    @Test
    public void should_send_sync_request_stream_result() throws IOException, InterruptedException {
        //GIVEN
        final HttpRequest request = buildHttpRequest("/v5/hello");

        //WHEN
        final HttpResponse<Stream<String>> response = client.send(request, HttpResponse.BodyHandlers.ofLines());

        System.out.println("Body: " + response.body().collect(Collectors.toList()));
        //THEN
        int expectedCode = 200;
        assertThat(response.statusCode()).isEqualTo(expectedCode);
    }

    //TODO : NOT YET IMPLEMENTED
    @Test
    public void should_send_request_from_file() throws FileNotFoundException {
        HttpRequest requestBodyOfFile = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Path.of("user.json")))
                .uri(URI.create( BASE + "/v8/hello"))
                .build();
    }

    //    TODO : NOT YET IMPLEMENTED
    //Cookie
    @Test
    public void should_send_sync_request_cookie() throws IOException, InterruptedException {

        HttpRequest request = buildHttpRequest("/hello/cookie-store");

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        // retrieve cookie
    }

    @Test
    public void should_send_async_request(){

        HttpRequest
                request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create("http://localhost:7001/v6/hello"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    private  HttpRequest buildHttpRequest(String route) {
        return HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create(BASE + route))
                .build();
    }
}