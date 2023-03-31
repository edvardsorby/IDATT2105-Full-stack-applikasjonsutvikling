<template>
  <div class = grid>
    <div class="calculator">
      <Calculator @add-to-history="historyAdd" :propName="value"/>
    </div>
      <div class="history">
      <CalculatorHistory @customChange="insertAnswer"/>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Calculator from "@/components/Calculator.vue"
import CalculatorHistory from "@/components/CalculatorHistory.vue"
import { useTokenStore } from "../stores/token"
import { getUserInfo } from "../utils/httputils.ts"
import router from '@/router';


export default {
  name: "CalculatorView",
  components: {
    Calculator,
    CalculatorHistory,
  },
  async mounted() {
    if(!this.tokenStore.jwtToken) {
        console.log("Unauthenticated context");
        router.push("/")
    } else {
        console.log("Authenticated context");
        let response  = await getUserInfo("edvard", this.tokenStore.jwtToken);
        this.user = response.data;
    }
  },
  data() {
    return {
      history: [],
      value: ""
    };
  },
  methods: {
    historyAdd(calculation) {
      this.history.push(calculation);
    },
    insertAnswer(answer) {
      this.value = answer
      console.log(this.value + " TEST")
    }
  },
  setup() {
      const tokenStore = useTokenStore();
      return { tokenStore };
    },
  
};
</script>

<style>
.grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  margin: 0;
  padding: 0;
}

</style>