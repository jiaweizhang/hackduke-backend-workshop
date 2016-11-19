<template>
    <div>
      <h2>Log In</h2>
      <p>Log in to view your todos</p>
      <div class="alert alert-danger" v-if="error">
        <p>{{ error }}</p>
      </div>
      <div class="form-group">
        <input
          type="text"
          class="form-control"
          placeholder="Enter your username"
          v-model="credentials.username"
        >
      </div>
      <div class="form-group">
        <input
          type="password"
          class="form-control"
          placeholder="Enter your password"
          v-model="credentials.password"
        >
      </div>
      <button class="btn btn-primary" @click="submit()">Access</button>
    </div>
</template>
<style>

</style>
<script>
  const API_URL = 'http://localhost:8080/';
  const LOGIN_URL = API_URL + 'api/users/login';

  export default {
    data() {
      return {
        // We need to initialize the component with any
        // properties that will be used in it
        credentials: {
          username: '',
          password: ''
        },
        error: ''
      }
    },
    methods: {
      submit() {
        var credentials = {
          username: this.credentials.username,
          password: this.credentials.password
        };
        // We need to pass the component's this context
        // to properly make use of http in the auth service
        this.$http.post(LOGIN_URL, credentials).then((response) => {
          localStorage.setItem('id_token', response.body.body.token);

          this.$router.push('/todo');
        }, (response) => {
          // error callback
          this.error = response.body.message;
        })
      }
    }

  }
</script>
