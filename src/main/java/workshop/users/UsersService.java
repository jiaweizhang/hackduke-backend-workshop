package workshop.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utilities.TokenUtility;
import workshop.std.StdResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiaweizhang on 11/18/2016.
 */

@Service
public class UsersService {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private JdbcTemplate jt;

    public StdResponse register(RegisterRequest registerRequest) {
        // check that username does not exist
        boolean usernameExists = jt.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM users WHERE users.username = ?)",
                Boolean.class,
                registerRequest.username);

        if (usernameExists) {
            return new StdResponse(HttpStatus.OK, false, "Username already exists");
        }

        String passhash = passwordEncoder.encode(registerRequest.password);

        jt.update("INSERT INTO users (username, passhash) VALUES (?, ?)", registerRequest.username, passhash);

        String token = TokenUtility.generateToken(registerRequest.username);
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        return new StdResponse(HttpStatus.OK, true, "User created", body);
    }

    public StdResponse login(LoginRequest loginRequest) {
        // check that username exists
        boolean usernameExists = jt.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM users WHERE users.username = ?)",
                Boolean.class,
                loginRequest.username);

        if (!usernameExists) {
            return new StdResponse(HttpStatus.OK, false, "Username does not exist");
        }

        String passhash = jt.queryForObject("SELECT passhash FROM users WHERE username = ?", String.class, loginRequest.username);
        if (passwordEncoder.matches(loginRequest.password, passhash)) {
            // valid
            String token = TokenUtility.generateToken(loginRequest.username);
            Map<String, String> body = new HashMap<>();
            body.put("token", token);
            return new StdResponse(HttpStatus.OK, true, "User validated", body);
        } else {
            // invalid
            return new StdResponse(HttpStatus.OK, false, "Invalid password");
        }
    }
}
