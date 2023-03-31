import { defineStore } from "pinia"

export const useUserStore = defineStore('userStore', {
    state: () => ({
        name: '',
        email: '',
        loggedIn: false,
        currentUser: null
    }),
    getters: {
        getName(): string {
            return this.name
        },
        getEmail(): string {
            return this.email
        },
        getCurrentUser(): any {
            return this.currentUser
        }
    },
    actions: {
        setName(name: string) {
            this.name = name
        },
        setEmail(email: string) {
            this.email = email
        },
        logIn() {
            this.loggedIn = true
        },
        logOut() {
            this.loggedIn = false
        },
        setCurrentUser(user: any) {
            this.currentUser = user
        }
    }
})