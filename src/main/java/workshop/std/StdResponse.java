package workshop.std;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

/**
 * Created by jiaweizhang on 11/18/2016.
 */
public class StdResponse {
    public HttpStatus status;
    public boolean success;
    public String message;
    public Object body;
    public Timestamp timestamp;

    public StdResponse(HttpStatus status, boolean success, String message, Object body) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public StdResponse(HttpStatus status, boolean success, String message) {
        this(status, success, message, null);
    }
}
