import axios from "axios";
import { defineStore } from "pinia";
import {getCalculatorHistory, getJwtToken, getUserInfo} from "../utils/httputils.js"
import { ref } from "vue";

export interface User {
    username: string,
    password: string
}

export const useTokenStore = defineStore("token", {
    state: () => ({
        jwtToken: null as string | null,
        loggedInUser: null as User | null,
    history: [],
  }),
  persist: {
    storage: sessionStorage, // note that data in sessionStorage is cleared when the page session ends
  },
  actions: {
    async getTokenAndSaveInStore(username: any, password: any) {
        try{
            let response = await getJwtToken(username, password);
            let data = response.data;
            if(data != null && data != '' && data != undefined){
                this.jwtToken = data;
                let userdata = await getUserInfo(username, this.jwtToken)
                this.loggedInUser = userdata.data

                setTimeout(() => {
                    this.getTokenAndSaveInStore(username, password);
                  }, 4 * 60 * 1000); // get new token 1 minute before access token expiration
        
            }
        } catch (err){
            console.log(err)
        }
    },
    async getHistory() {
        try {
            if (this.loggedInUser) {
                console.log(this.loggedInUser)
                let response = await getCalculatorHistory(this.loggedInUser.username, this.jwtToken);
                
                if (response.data) {
                    this.history = response.data.slice(-10).reverse()
                }
            }
        } catch (err) {
            console.log(err)
        }
    },
}
})