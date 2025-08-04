<template>
  <div class="contact">
    <ContactForm />
  </div>
</template>

<script setup>
import ContactForm from '@/components/ContactForm.vue';
import { useTokenStore } from "../stores/token"
import { onMounted } from 'vue';
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