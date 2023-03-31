<template>
  <LogIn/>
</template>

<script setup lang="ts">
// @ is an alias to /src
import LogIn from "@/components/LogIn.vue"
import { onMounted } from "vue";
import { useTokenStore } from "@/stores/token";
import router from "@/router";

const tokenStore = useTokenStore();

onMounted(() => {
  if(tokenStore.jwtToken) {
    router.push("/kalkulator")
    console.log("Already logged in");
  }
})

</script>

<style scoped>

</style>