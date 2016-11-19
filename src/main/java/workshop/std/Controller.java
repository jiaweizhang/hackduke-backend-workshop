package workshop.std;

import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import utilities.TokenUtility;
import workshop.exceptions.JwtAuthException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by jiaweizhang on 11/17/2016.
 */

public class Controller {

    public StdRequest pre(HttpServletRequest httpServletRequest) {
        StdRequest stdRequest = new StdRequest();
        pre(stdRequest, httpServletRequest);
        return stdRequest;
    }

    public void pre(StdRequest stdRequest, HttpServletRequest httpServletRequest) {
        String jwt = httpServletRequest.getHeader("Authorization");
        try {
            Claims claims = TokenUtility.retrieveClaims(jwt);
            stdRequest.username = claims.getSubject();
        } catch (Exception e) {
            throw new JwtAuthException();
        }
    }

    protected ResponseEntity wrap(StdResponse stdResponse) {
        stdResponse.timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        return ResponseEntity.status(stdResponse.status).body(stdResponse);
    }
}