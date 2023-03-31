<template>
    <form @submit.prevent="handleLoginClick">
      <label for="username">Brukernavn</label>
      <input type="text" name="username" id="username" v-model="username">
      <br><br>
      <label for="password">Passord</label>
      <input type="password" name="password" id="password" v-model="password">
      <br><br>
      <input type="submit" value="Logg inn" class="logInOut">
    </form>
</template>
  
<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import router from '@/router';
import { useTokenStore } from "../stores/token";
  
const tokenStore = useTokenStore();
let username: string;
let password: string;


 async function handleLoginClick() {
    await tokenStore.getTokenAndSaveInStore(username, password);
    if(tokenStore.jwtToken){
        router.push("/kalkulator");
    } else {
    }
}

  
  
  
  
</script>
  
  <style scoped>
  form {
    margin-top: 100px;
  }
  label {
    margin: 10px;
  }
  
  </style>