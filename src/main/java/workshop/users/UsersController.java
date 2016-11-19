package workshop.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workshop.std.Controller;

/**
 * Created by jiaweizhang on 11/18/2016.
 */

@RestController
@RequestMapping("/api/users")
public class UsersController extends Controller {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        return wrap(usersService.register(registerRequest));
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return wrap(usersService.login(loginRequest));
    }
}
