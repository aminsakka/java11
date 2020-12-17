import io.javalin.Javalin;

public class DemoServer {


    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7001);
        app.get("/hello", ctx -> ctx.result("Hello ! Java 11 is cool"));
    }
}
