<template>
  <div>
    <div class="alert alert-danger" v-if="error">
      <p>{{ error }}</p>
    </div>
    <div class="row">
      <div class="col-sm-8"></div>
      <div class="col-sm-4">
        <input type="radio" id="one" value="One" v-model="picked" v-on:change="getTodos">
        <label for="one">Show All</label>
        <br>
        <input type="radio" id="two" value="Two" v-model="picked" v-on:change="getTodos">
        <label for="two">Show Incomplete</label>
        <br>
        <input type="radio" id="three" value="Three" v-model="picked" v-on:change="getTodos">
        <label for="three">Show Complete</label>
      </div>
    </div>
    <div class="form-group">
      <label>New Todo</label>
      <input @keyup.enter="createTodo" type="text" class="form-control" v-model="newTodo"
             placeholder="Go buy condoms">
      <br/>
      <label>Todos</label>
      <form>
        <div v-for="todo in todos">
          <div class="form-group row">
            <div class="col-sm-9">
              <input type="text" class="form-control" :disabled="todo.completed" v-model="todo.todo"
                     @keyup.enter="editTodo(todo.todoId, todo.todo, todo.completed)">
            </div>
            <div class="col-sm-2">
              <a class="btn btn-success" v-if="!todo.completed"
                 @click="editTodo(todo.todoId, todo.todo, true)">Complete</a>
              <a class="btn btn-warning" v-if="todo.completed" @click="editTodo(todo.todoId, todo.todo, false)">Uncomplete</a>
            </div>
            <div class="col-sm-1">
              <a class="btn btn-danger" @click="deleteTodo(todo.todoId)">X</a>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>
<style>

</style>
<script>
  export default{
    created() {
      this.todos = this.getTodos();
    },
    data() {
      return {
        todos: [],
        error: '',
        newTodo: '',
        picked: "One"
      }
    },
    methods: {
      createTodo() {
        var data = {'todo': this.newTodo};
        var headers = {'headers': this.getAuthHeader()};
        this.$http.post("http://localhost:8080/api/todos", data, headers).then((response) => {
          this.newTodo = '';
          this.todos = this.getTodos();
        }, (response) => {
          // error callback
          this.error = response.body.message;
        })
      },
      getTodos() {
        var endpoint = "http://localhost:8080/api/todos";
        if (this.picked == 'Two') {
          endpoint += "?completed=false";
        } else if (this.picked == 'Three') {
          endpoint += "?completed=true";
        }
        var headers = {'headers': this.getAuthHeader()};
        this.$http.get(endpoint, headers).then((response) => {
          this.todos = response.body.body;

        }, (response) => {
          // error callback
          this.error = response.body.message;
        })
      },
      deleteTodo(todoId) {
        var headers = {'headers': this.getAuthHeader()};
        this.$http.delete("http://localhost:8080/api/todos/" + todoId, headers).then((response) => {
          this.todos = this.getTodos();

        }, (response) => {
          // error callback
          this.error = response.body.message;
        })
      },
      editTodo(todoId, todo, completed) {
        var data = {'todo': todo, 'completed': completed};
        var headers = {'headers': this.getAuthHeader()};
        this.$http.put("http://localhost:8080/api/todos/" + todoId, data, headers).then((response) => {
          this.todos = this.getTodos();

        }, (response) => {
          // error callback
          this.error = response.body.message;
        })
      },
      getAuthHeader() {
        return {'Authorization': localStorage.getItem('id_token')};
      }
    }
  }
</script>
