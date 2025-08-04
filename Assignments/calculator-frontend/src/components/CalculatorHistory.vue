<template>
  <h1>Logg</h1>
  <div class="logg">
    <ul>
      <li v-for="calculation in history" :key="calculation.id" @click="handleChange(calculation)">
        {{ calculation.calculation + " = " + calculation.answer }}
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts"> // TODO Upload calculation to database in equals() and load the 10 last ones in the calculation history. 
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useTokenStore } from '@/stores/token';


interface Calculation {
  id: number;
  calculation: string;
  answer: string;
}

let tokenStore = useTokenStore();
let history = computed<Calculation[]>(() => tokenStore.history);


const emit = defineEmits(['customChange'])
function handleChange(calculation: any) {
  emit('customChange', calculation.answer)
}

onMounted(() => {
  tokenStore.getHistory()
})

</script>

<style scoped>
.logg {
  font-size: 32px;
}
ul {
  list-style-type: none;
}
li:hover {
  cursor: pointer;
}
</style>
