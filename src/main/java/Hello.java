import java.util.Date;

public class Hello {
    private String message ;
    private String version ;

    public Hello(String message) {
        this.message = message;
    }

    public Hello(String message, String version) {
        this.message = message;
        this.version = version ;
    }

    public String getMessage() {
        return message;
    }

    public void setDescription(String message) {
        this.message = message;
    }
}
