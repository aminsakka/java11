import io.javalin.Javalin;
import java.util.Arrays;
public class DemoServer {


    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7001);

        //simple string
        app.get("/v1/hello", ctx -> ctx.result("Hello from v1 server !"));

        //json
        app.get("/v2/hello", ctx -> {
                ctx.json((new Hello("Hello from v2 Server !")));
        });

        //path param
        app.get("v3/hello/:name", ctx ->
                ctx.json(new Hello ("Hello " + ctx.pathParam("name") +" from v3 Server")));


        //Timeout
        app.get("v4/hello", ctx -> {
                ctx.wait(5000);
                ctx.result("Request Timeout");
        });

        //Stream
        app.get("v5/hello", ctx -> {
            ctx.result("Streaming lines \n from server. \n Hello\n http client.");
        });

        //Async
        app.get("v6/hello", ctx -> {
            ctx.wait(3000);
            ctx.json("Streaming lines \n from server. \n Hello\n http client.");
        });


        app.get("hello/cookie-store", ctx -> {

            ctx.cookieStore("string-cookie", "Hello cookie !" );
            ctx.cookieStore("visit", 3);
            ctx.cookieStore("list", Arrays.asList("One", "Two", "Three"));

        });

    }
}
