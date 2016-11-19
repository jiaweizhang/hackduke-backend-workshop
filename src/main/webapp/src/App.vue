<template>
  <div>
    <div class="wrap">
      <nav class="navbar navbar-default">
        <div class="container">
          <div class="navbar-header">
            <router-link class="navbar-brand" to="/">Home</router-link>
          </div>
          <ul class="nav navbar-nav">
            <li v-if="isAuthenticated()">
              <router-link to="/todos">Todos</router-link>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li v-if="!isAuthenticated()">
              <router-link to="/signup">Sign Up</router-link>
            </li>
            <li v-if="!isAuthenticated()">
              <router-link to="/login">Login</router-link>
            </li>
            <li v-if="isAuthenticated()">
              <a href="" @click="logout()">Logout</a>
            </li>
          </ul>
        </div>
      </nav>
      <div class="container">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>


<script>
  import auth from './auth/index.js'

  export default {
    methods: {
      logout() {
        auth.logout();
      },
      isAuthenticated() {
        return localStorage.getItem('id_token') != null;
      }
    }
  }
</script>
