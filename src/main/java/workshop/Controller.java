package workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jiaweizhang on 11/17/2016.
 */

@RestController
public class Controller {

    @Autowired
    private JdbcTemplate jt;

    @RequestMapping(value = "",
            method = RequestMethod.GET)
    @ResponseBody
    public String root() {
        return "apple-pie";
    }

    @RequestMapping(value = "/api/todos",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Todo> getTodos() {
        List<Todo> todos = jt.query("SELECT todo_id, todo FROM todos", new TodoMapper());
        return todos;
    }

    @RequestMapping(value = "/api/todos",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public boolean createTodo(@RequestBody final CreateTodoRequest createTodoRequest) {
        jt.update("INSERT INTO todos (todo) VALUES (?)", createTodoRequest.todo);
        return true;
    }

    @RequestMapping(value = "/api/todos/{todoId}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteTodo(@PathVariable(value = "todoId") long todoId) {
        jt.update("DELETE FROM todos WHERE todo_id = ?", todoId);
        return true;
    }

    @RequestMapping(value = "/api/todos/{todoId}",
            method = RequestMethod.PUT,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public boolean editTodo(@RequestBody final EditTodoRequest editTodoRequest, @PathVariable(value = "todoId") long todoId) {
        jt.update("UPDATE todos SET todo = ? WHERE todo_id = ?", editTodoRequest.todo, todoId);
        return true;
    }

    private static final class TodoMapper implements RowMapper<Todo> {
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();
            todo.todoId = rs.getLong("todo_id");
            todo.todo = rs.getString("todo");
            return todo;
        }
    }
}
