import {router} from '../main.js'

// URL and endpoint constants
const API_URL = 'http://localhost:8080/';
const LOGIN_URL = API_URL + 'api/users/login';
const SIGNUP_URL = API_URL + 'api/users/register';

export default {

  // User object will let us check authentication status
  user: {
    authenticated: false
  },

  // Send a request to the login URL and save the returned JWT
  login(context, creds, redirect) {
    context.$http.post(LOGIN_URL, creds).then((response) => {
      localStorage.setItem('id_token', response.body.body.token);

      this.user.authenticated = true;

      // Redirect to a specified route
      if(redirect) {
        router.push(redirect)
      }
    }, (response) => {
      // error callback
      context.error = response.body.message;
    })
  },

  signup(context, creds, redirect) {
    context.$http.post(SIGNUP_URL, creds).then((response) => {
      localStorage.setItem('id_token', response.body.body.token);

      this.user.authenticated = true;

      // Redirect to a specified route
      if(redirect) {
        router.push(redirect)
      }
    }, (response) => {
      // error callback
      context.error = response.body.message;
    })
  },

  // To log out, we just need to remove the token
  logout() {
    localStorage.removeItem('id_token');
  },

  // The object to be passed as a header for authenticated requests
  getAuthHeader() {
    return {
      'Authorization': localStorage.getItem('id_token')
    }
  }
}
