<template>
  <h1>Kalkulator</h1>
  <div id="calculator">
    <div id="display">
      <span> {{ calculation }} </span>
    </div>
    <button @click="clear()" id="C">C</button>
    <button @click="ans()" id="ANS">ANS</button>
    <button @click="del()" id="DEL">DEL</button>
    <button @click="insertOperator('/')" id="divide">/</button>
    <button @click="insertNumber('7')" id="seven">7</button>
    <button @click="insertNumber('8')" id="eight">8</button>
    <button @click="insertNumber('9')" id="nine">9</button>
    <button @click="insertOperator('x')" id="multiply">x</button>
    <button @click="insertNumber('4')" id="four">4</button>
    <button @click="insertNumber('5')" id="five">5</button>
    <button @click="insertNumber('6')" id="six">6</button>
    <button @click="insertOperator('-')" id="minus">-</button>
    <button @click="insertNumber('1')" id="one">1</button>
    <button @click="insertNumber('2')" id="two">2</button>
    <button @click="insertNumber('3')" id="three">3</button>
    <button @click="insertOperator('+')" id="plus">+</button>
    <button id="equals2" data-testid="equals2"></button>
    <button @click="insertNumber('0')" id="zero">0</button>
    <button @click="insertComma()" id="comma">,</button>
    <button @click="postCalculation()" id="equals" data-testid="equals">=</button>
  </div>
</template>

<script lang="ts">
import axios from 'axios'
import { useTokenStore } from '@/stores/token';

export default {
  name: "KalkulatorComponent",
  emits: ['add-to-history'],
  data() {
    return {
      calculation: "",
      history: [],
      answer: "",
      doneCalculating: false,
      currentIsAnOperator: false,
    };
  },
  props: {
    propName: null
  },
  watch: {
    propName(newValue) {
      this.insertNumber(newValue)
    }
  },
  methods: {
    insertNumber(number: any) {
      if (this.doneCalculating) {
        this.calculation = "";
        this.doneCalculating = false;
      }

      this.calculation += number;
      this.currentIsAnOperator = false;
    },
    insertOperator(operator: any) {
      if (this.doneCalculating) {
        this.reset();
        this.calculation += this.answer;
      }

      if (this.currentIsAnOperator) {
        this.del();
      }
      this.calculation += operator;
      this.currentIsAnOperator = true;
    },
    clear() {
      this.calculation = "";
    },
    addToHistory() {
      this.$emit('add-to-history', this.calculation);
    },
    ans() {
      if (this.doneCalculating) {
        this.reset();
        this.doneCalculating = false;
      }
      console.log(this.calculation.substring(this.calculation.length-this.answer.length, this.calculation.length));
      console.log(this.calculation.length);
      console.log(String(this.answer).length);
      if(this.calculation.substring(this.calculation.length-String(this.answer).length, this.calculation.length) == this.answer) {
        return;
      }
      this.calculation += this.answer;
      this.currentIsAnOperator = false;
    },
    del() {
      if(this.doneCalculating) {
        this.ans();
        this.doneCalculating = false;
      }
      this.calculation = this.calculation.substring(0,this.calculation.length-1);

    },
    reset() {
      this.calculation = "";
      this.doneCalculating = false;
    },
    insertComma() {
      if (this.calculation.length < 1) {
        this.insertNumber("0");
      }
      this.insertNumber(".");
      // TODO Make more robust exception handling
    },
    postCalculation() {
      const postObject = {
        calculation: this.calculation,
        user: {
          username: this.tokenStore.loggedInUser?.username,
          password: this.tokenStore.loggedInUser?.password
        }
      }

      const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization" : "Bearer " + this.tokenStore.jwtToken
        },
    };

      axios.post('http://localhost:8080/api/calculations', postObject, config)
        .then(response => {
            this.calculation += "="
            this.calculation += Math.round((response.data).answer*100)/100;
            this.addToHistory();
            this.tokenStore.getHistory()
            this.doneCalculating = true;
        })
        .catch(error => {
            console.log(error)
        })
    },
    insertAnswer(answer: string) {
      this.insertNumber(answer);
    },
  },
  setup() {
      const tokenStore = useTokenStore();
      return { tokenStore };
    },
};
</script>

<style scoped>
@import url('https://fonts.cdnfonts.com/css/seven-segment');
                
#calculator {
  display: grid;
  background-color: #6fff5c;
  height: 400px;
  width: 300px;
  margin: auto;
  border-radius: 10px;
  gap: 2px;
  padding: 5px;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr 1fr 1fr 1fr;
  font-family: 'Seven Segment', sans-serif;
}
#display {
  color: #6fff5c;
  background-color: #2e2e2e;
  font-weight: bold;
  grid-column: 1/5;
  grid-row: 1/2;
  border-radius: inherit;
  height: 1fr;
  font-family: 'Seven Segment', sans-serif;
  font-size: 50px;
                                                
}
#display span {
  height: 100%;
  width: 100%;
}
#C {
  grid-column: 1/2;
  grid-row: 2/3;
}
#equals {
  grid-column: 4/5;
  grid-row: 6/7;
  background-color: #6fff5c;
  color: #2e2e2e;
  border: 2px solid #2e2e2e;
}
button {
  color: #6fff5c;
  background-color: #2e2e2e;
  border: none;
  border-radius: inherit;
  font-size: 30px;
}
button:hover {
  cursor: pointer;
}
</style>
