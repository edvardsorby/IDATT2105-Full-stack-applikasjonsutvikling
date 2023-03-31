<template>
    <h1>Gi tilbakemelding</h1>
    <Form @submit="sendForm" :validation-schema="schema" v-slot="{ meta }" validate-on-mount>
        <label for="text">Navn</label>
        <Field type="text" name="name" id="name" v-model="feedback.name" :validateOnInput="true" />
        <ErrorMessage name="name" />
        <br>
        <label for="email">E-post</label>
        <Field type="email" name="email" id="email" v-model="feedback.email"  :validateOnInput="true"/>
        <ErrorMessage name="email" />
        <br>
        <label for="message">Melding</label>
        <Field type="text" name="message" id="message" v-model="feedback.message"  :validateOnInput="true"/>
        <ErrorMessage name="message" />
        <br>
        <br>
        <input type="submit" value="Send inn" id="submit" :disabled="!meta.valid">
    </Form>

</template>

<script>
import axios from 'axios'
import { Form, Field, ErrorMessage } from 'vee-validate'
import { useUserStore } from '../stores/userStore'

export default {
    components: {
        Form,
        Field,
        ErrorMessage
    },
    data() {
        return {
            feedback: {
                name: '',
                email: '',
                message: '',
            },
            feedbacks: [],
            schema: {
                name(value) {
                    if(value.length < 1) {
                        return 'Vennligst oppgi et navn'
                    }
                    return true;
                },
                email(value) {
                    // if the field is empty
                    if (!value) {
                        return 'Dette feltet er påkrevd';
                    }
                    // if the field is not a valid email
                    const regex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
                    if (!regex.test(value)) {
                        return 'Dette feltet krever en gyldig e-post';
                    }
                    // All is good
                    return true;
                },
                message(value) {
                    if(value.length < 1) {
                        return 'Vennligst skriv en melding'
                    }
                    return true;
                },
            }
        }
    },
    methods: {
        async handleSendClick() {
            const sendResponse = await axios.get("http://localhost:3000/login");
            alert("Innsending: " + sendResponse);
        },
        sendForm (e) {
            useUserStore().setName(this.feedback.name);
            useUserStore().setEmail(this.feedback.email);
            console.log(this.feedback.name)
            console.log(this.feedback.email)
            console.log(useUserStore().name)
            console.log(useUserStore().email)
/*
            axios.post('http://localhost:3000/feedback', this.feedback)
            .then(function (response) {
                console.log('Response', response);
                alert("Vellykket innsending");
            })
            .catch(function (err) {
                console.log('Error', err);
                alert("Noe gikk galt");
            })*/
        },
    },
    created() {

        this.feedback.name = useUserStore().name;
        this.feedback.email = useUserStore().email;
    },
}
</script>

<style scoped>
form {
    display: flex;
    flex-direction: column;
    width: 200px;
    margin: auto;
    margin-top: 50px;
}
#submit {
    background-color: #6fff5c;
    color: #2e2e2e;
    width: 100px;
    height: 30px;
    border-radius: 5px;
    margin: auto;
}
#submit:hover {
    cursor: pointer;
    background-color: #2e2e2e;
    color: #ffffff;
}
#submit:disabled {
    background-color: #60885a;
    cursor: default;
    color: inherit;
}
</style>