package workshop.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workshop.std.Controller;
import workshop.std.StdRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiaweizhang on 11/18/2016.
 */

@RestController
public class TodosController extends Controller {

    @Autowired
    private TodosService todosService;

    @RequestMapping(value = "/api/todos",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getTodos(HttpServletRequest httpServletRequest, @RequestParam(value = "completed", required = false) Boolean completed) {
        StdRequest stdRequest = pre(httpServletRequest);
        return wrap(todosService.getTodos(stdRequest, completed));
    }

    @RequestMapping(value = "/api/todos",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity createTodo(HttpServletRequest httpServletRequest, @RequestBody final CreateTodoRequest createTodoRequest) {
        pre(createTodoRequest, httpServletRequest);
        return wrap(todosService.createTodo(createTodoRequest));
    }

    @RequestMapping(value = "/api/todos/{todoId}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteTodo(HttpServletRequest httpServletRequest, @PathVariable(value = "todoId") long todoId) {
        StdRequest stdRequest = pre(httpServletRequest);
        return wrap(todosService.deleteTodo(stdRequest, todoId));
    }

    @RequestMapping(value = "/api/todos/{todoId}",
            method = RequestMethod.PUT,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity editTodo(HttpServletRequest httpServletRequest,
                                   @RequestBody final EditTodoRequest editTodoRequest,
                                   @PathVariable(value = "todoId") long todoId) {
        pre(editTodoRequest, httpServletRequest);
        return wrap(todosService.editTodo(editTodoRequest, todoId));
    }
}
