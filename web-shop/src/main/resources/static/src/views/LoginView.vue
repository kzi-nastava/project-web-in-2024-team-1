<template>
  <div class="login-container">
    <h1>Login</h1>
    <label for="username">Username:</label>
    <input v-model="account.username" /><br />
    <label for="password">Password:</label>
    <input v-model="account.password" /><br />
    <button v-on:click="login()">Login</button>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "LoginView",
  data: function () {
    return {
      account: {
            username: '',
            password: ''
      },
      errorMessage: ''
    };
  },
  methods: {
    login: function () {
      axios
        .post("http://localhost:8181/api/login", this.account, {
          withCredentials: true,
        })
        .then(res => {
        console.log(res);
        // Assuming the response contains account data with a role
        const userRole = res.data.userRole;
        if (userRole === 'ADMINISTRATOR') {
          this.$router.push("/admin");
        } else if (userRole === 'SELLER') {
          this.$router.push("/seller");
        } else {
          this.$router.push("/customer");
        }
      })
      .catch(err => {
        console.error(err);
          if (err.response && err.response.status === 401) {
            if (err.response.data === 'Invalid username') {
              this.errorMessage = "Invalid username!";
            } else if (err.response.data === 'Invalid password') {
              this.errorMessage = "Invalid password!";
            } else {
              this.errorMessage = "Invalid username or password!";
            }
          } else {
            this.errorMessage = "Error during login!";
          }
      });

      
    },
  },
};
</script>

<style scoped>
.login-container {
  border: 2px solid #000; 
  padding: 20px; 
  width: 300px; 
  margin: auto; 
  margin-top: 50px; 
  border-radius: 10px; 
  background-color: pink; 
}
label {
  display: block;
  font-size: 25px;
}
button {
  margin-top: 10px;
  width: 80px;
  height: 30px;
  font-size: 18px;
}
input {
  width: 230px; /* Postavite širinu input polja */
  font-size: 16px; /* Promenite veličinu fonta za input polje */
  margin-top: 10px;
  padding: 5px; /* Dodajte padding za bolji izgled */
}
</style>