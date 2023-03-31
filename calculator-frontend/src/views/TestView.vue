<template>
    <div class="test">
      <h1>Dette er en informasjonsside uten innhold</h1>
    </div>
  </template>
  
<script setup lang="ts">
import { onMounted } from 'vue';
import { useTokenStore } from '@/stores/token';
import router from '@/router';

const tokenStore = useTokenStore();

onMounted(() => {
  if(!tokenStore.jwtToken) {
      console.log("Unauthenticated context");
      router.push("/")
  } else {
      console.log("Authenticated context");
  }
})
    

</script>

  <style>
  @media (min-width: 1024px) {
    .about {
      min-height: 100vh;
      display: flex;
      align-items: center;
    }
  }
  </style>
  