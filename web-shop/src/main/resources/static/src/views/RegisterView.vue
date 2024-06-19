<template>
<div class="register-container">
    <h1>Register</h1>
    <label for="role">Role:</label>
    <select v-model="account.role">
        <option value="CUSTOMER">Customer</option>
        <option value="SELLER">Seller</option>
    </select><br />
    <label for="name">Name:</label>
    <input v-model="account.name" /><br />
    <label for="lastname">Last Name:</label>
    <input v-model="account.lastname" /><br />
    <label for="username">Username :</label>
    <input v-model="account.username" /><br />
    <label for="email">Email:</label>
    <input v-model="account.email" /><br />
    <label for="phoneNumber">Phone Number :</label>
    <input v-model="account.phoneNumber" /><br />
    <label for="password">Password :</label>
    <input v-model="account.password" /><br />
    <label for="confirmPassword">Confirm Password :</label>
    <input v-model="account.confirmPassword" /><br />
    <button v-on:click="register()">Register</button>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "RegisterView",
  data: function () {
    return {
      account: {
            role: 'CUSTOMER',
            name: '',
            lastName: '',
            username: '',
            email: '',
            phoneNumber: '',
            password: '',
            confirmPassword: ''
      },
      errorMessage: ''
    };
  },
  methods: {
    register: function () {
      axios
        .post("http://localhost:8181/api/register", this.account, {
          withCredentials: true,
        })
        .then(res => {
          console.log(res);
          // Preusmeravanje na login stranicu nakon uspešne registracije
          this.$router.push("/login");
        })
        .catch(err => {
          console.error(err);
          if (err.response && err.response.status === 400) {
            if (err.response.data === 'Username already exists') {
              this.errorMessage = "Username already exists!";
            } else if (err.response.data === 'Gmail already exists') {
              this.errorMessage = "Email already exists!";
            } else if (err.response.data === 'Password not correct') {
              this.errorMessage = "Password not correct!";
            } else {
              this.errorMessage = err.response.data;
            }
          } else {
            this.errorMessage = "Error during registration!";
          }
        });

      
    },
  },
};
</script>

<style scoped>
.register-container {
  border: 2px solid #000; 
  padding: 20px; 
  width: 350px; 
  margin: auto; 
  margin-top: 10px; 
  border-radius: 10px; 
  background-color: pink; 
}
label {
  display: block;
  margin-top: 10px;
  font-size: 20px; 
}
input, select {
  width: 250px;
  font-size: 18px;
  margin-top: 10px;
  padding: 5px;
}
button {
  margin-top: 10px;
  font-size: 18px;
  width: 100px;
  height: 30px;
}
.error-message {
  color: red;
  margin-top: 10px;
  font-size: 16px;
}
</style>