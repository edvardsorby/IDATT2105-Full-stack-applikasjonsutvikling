import axios from "axios";

const baseurl = "http://localhost:8080";

export const loginRequest = (user: any) => {
    const config = {
        headers: {
            "Content-type": "application/json",
        },
    };
    return axios.get(baseurl + "/login", config);
}

export const getJwtToken = (username: any, password: any) => {
    const config = {
        headers: {
            "Content-type": "application/json",
        },
    };
    return axios.post("http://localhost:8080/token", JSON.stringify({ username, password }), config);
}

export const getUserInfo = (username: string, token: any) => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization" : "Bearer " + token
        },
    };
    return axios.get("http://localhost:8080/api/users/"+username, config);
}

export const getCalculatorHistory = (username: string, token: any) => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization" : "Bearer " + token
        },
        params: {
            username: username
        }
    };
    return axios.get("http://localhost:8080/api/calculations", config);
}

