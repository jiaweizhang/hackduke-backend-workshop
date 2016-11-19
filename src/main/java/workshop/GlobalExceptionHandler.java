package workshop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import workshop.exceptions.JwtAuthException;
import workshop.std.Controller;
import workshop.std.StdResponse;

/**
 * Created by jiaweizhang on 11/18/2016.
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends Controller {

    @ExceptionHandler(JwtAuthException.class)
    public ResponseEntity handleJwtAuthException() {
        return wrap(new StdResponse(HttpStatus.FORBIDDEN, false, "Jwt Auth failed"));
    }
}