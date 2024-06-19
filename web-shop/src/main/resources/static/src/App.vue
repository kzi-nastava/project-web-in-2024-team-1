<template>
  <div id="app">
    <header>
      <div class="title">fake kupujem prodajem</div>
      <div class="nav-links">
        <router-link v-if="showAuthLinks" to="/login">Login</router-link>
        <router-link v-if="showAuthLinks" to="/register">Register</router-link>
        <router-link v-if="!showAuthLinks" to="/profile">Profile</router-link>
      </div>
    </header>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  computed: {
    showAuthLinks() {
      return this.$route.name === 'home' || this.$route.name === 'login' || this.$route.name === 'register';
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  border: #2c3e50;
}

header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  background-color: pink;
  color: darkpink;
  
}

.title {
  font-weight: bold;
  font-size: 24px;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
