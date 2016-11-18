package workshop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiaweizhang on 11/17/2016.
 */

@RestController
public class Controller {

    @RequestMapping(value = "/api/todos",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getTodos() {

    }

    @RequestMapping(value = "/api/todos",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity createTodo(@RequestBody final ContestCreationRequest req) {

    }

    @RequestMapping(value = "/api/todos/{todoId}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteTodo(@PathVariable(value = "todoId") long todoId) {

    }

    @RequestMapping(value = "/api/todos/{todoId}",
    method = RequestMethod.PUT,
    headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity editTodo(@RequestBody final dkfjdkfj req, @PathVariable(value = "todoId") long todoId) {

    }
}
