import Vue from "vue";
import App from "./App.vue";
import Home from "./components/Home.vue";
import Login from "./components/Login.vue";
import Signup from "./components/Signup.vue";
import Todos from "./components/Todos.vue";
import VueRouter from "vue-router";
import VueResource from "vue-resource";
Vue.use(VueResource);
Vue.use(VueRouter);

const routes = [
  {path: '/', component: Home},
  {path: '/login', component: Login},
  {path: '/signup', component: Signup},
  {path: '/todos', component: Todos},
  {path: '*', redirect: '/'}
];

export var router = new VueRouter({routes});

const app = new Vue({
  el: '#app',
  router,
  render: h => h(App)
});
