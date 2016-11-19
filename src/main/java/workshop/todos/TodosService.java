package workshop.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import workshop.std.StdRequest;
import workshop.std.StdResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jiaweizhang on 11/18/2016.
 */

@Service
public class TodosService {

    @Autowired
    private JdbcTemplate jt;

    public StdResponse getTodos(StdRequest stdRequest, Boolean completed) {
        if (completed == null) {
            // if completed == null, then we just fetch all
            List<Todo> todos = jt.query("SELECT todo_id, todo, completed FROM todos WHERE owner = ?",
                    new Object[]{stdRequest.username}, new TodoMapper());
            return new StdResponse(HttpStatus.OK, true, "Retrieved all todos", todos);
        } else if (completed) {
            // if completed == true, then we just fetch the completed todos
            List<Todo> todos = jt.query("SELECT todo_id, todo, completed FROM todos WHERE completed = true AND owner = ?",
                    new Object[]{stdRequest.username}, new TodoMapper());
            return new StdResponse(HttpStatus.OK, true, "Retrieved all completed todos", todos);
        } else {
            // if completed == false, then we just fetch the not completed todos
            List<Todo> todos = jt.query("SELECT todo_id, todo, completed FROM todos WHERE completed = false AND owner = ?",
                    new Object[]{stdRequest.username}, new TodoMapper());
            return new StdResponse(HttpStatus.OK, true, "Retrieved all incomplete todos", todos);
        }
    }

    public StdResponse createTodo(CreateTodoRequest createTodoRequest) {
        jt.update("INSERT INTO todos (todo, completed, owner) VALUES (?, ?, ?)",
                createTodoRequest.todo, false, createTodoRequest.username);
        return new StdResponse(HttpStatus.OK, true, "Created todo");
    }

    public StdResponse deleteTodo(StdRequest stdRequest, long todoId) {
        // make sure it's your todo
        boolean todoExists = jt.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM todos WHERE todo_id = ? AND owner = ?)",
                Boolean.class,
                todoId, stdRequest.username
        );

        if (!todoExists) {
            return new StdResponse(HttpStatus.OK, false, "Todo not found");
        }

        jt.update("DELETE FROM todos WHERE todo_id = ?", todoId);
        return new StdResponse(HttpStatus.OK, true, "Todo deleted");
    }

    public StdResponse editTodo(EditTodoRequest editTodoRequest, long todoId) {
        // make sure it's your todo
        boolean todoExists = jt.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM todos WHERE todo_id = ? AND owner = ?)",
                Boolean.class,
                todoId, editTodoRequest.username
        );

        if (!todoExists) {
            return new StdResponse(HttpStatus.OK, false, "Todo not found");
        }

        jt.update("UPDATE todos SET todo = ?, completed = ? WHERE todo_id = ?",
                editTodoRequest.todo, editTodoRequest.completed, todoId);
        return new StdResponse(HttpStatus.OK, true, "Todo updated");
    }

    private static final class TodoMapper implements RowMapper<Todo> {
        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();
            todo.todoId = rs.getLong("todo_id");
            todo.todo = rs.getString("todo");
            todo.completed = rs.getBoolean("completed");
            return todo;
        }
    }
}
