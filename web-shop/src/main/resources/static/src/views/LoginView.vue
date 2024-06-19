<template>
  <h1>Login</h1>
  <label for="username">Username:</label>
  <input v-model="account.username" /><br />
  <label for="password">Password:</label>
  <input v-model="account.password" /><br />
  <button v-on:click="login()">Login</button>
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
      }
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
        alert("Invalid username or password!");
      });

      // fetch("http://localhost:8081/api/login", {
      //   method: "POST",
      //   credentials: "include",
      //   headers: {
      //     Accept: "application/json",
      //     "Content-type": "application/json",
      //   },
      //   body: JSON.stringify(this.employee),
      // })
      //   .then((response) => response.json)
      //   .then((data) => {
      //     console.log("Success : " + data);
      //     this.$router.push("/employees");
      //   })
      //   .catch((err) => {
      //     console.log("Error : " + err);
      //     alert(err);
      //   });
    },
  },
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}
button {
  margin-top: 10px;
}
</style>