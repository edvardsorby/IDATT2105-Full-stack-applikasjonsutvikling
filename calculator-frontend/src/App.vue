<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import { useTokenStore } from './stores/token'
import router from '@/router';


const tokenStore = useTokenStore();

function logOut() {
  router.push("/")
  tokenStore.loggedInUser = null;
  tokenStore.jwtToken = null;
  tokenStore.history = []
  console.log("Logging out")
}

</script>

<template>
  <header>
    <h1 v-if="!tokenStore.loggedInUser">Kalkulator</h1>
    <nav v-if="tokenStore.loggedInUser">
      <RouterLink to="/kalkulator">Kalkulator</RouterLink>
      <RouterLink to="/kontaktskjema">Kontaktskjema</RouterLink>
      <RouterLink to="/info">Info</RouterLink>
    </nav>
    <button v-if="tokenStore.loggedInUser" @click="logOut()" class="logInOut">Logg ut</button>
  </header>

  <div id="line"></div>
  <RouterView />
</template>

<style>
html {
  height: 100%;
}
body {
  background-image: linear-gradient(to bottom right, #222222, #555555);
  background-size: cover;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #d1d1d1;
}

nav a {
  font-weight: bold;
  color: #d1d1d1;
  margin: 10px;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color: #6fff5c;
}

header {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80px;
}

header button {
  position: absolute;
  right: 50px;

}
#line {
  height: 1px;
  background-color: #6fff5c;
  margin: 0px 20px 0px 20px;
}
.logInOut {
  padding: 10px;
  background-color: #6fff5c;
  border-radius: 5px;
  border: none;
}
.logInOut:hover {
  cursor: pointer;
  background-color: #ffffff;
}
h1 {
  margin: 10px;
}
</style>
