import axios from 'axios'
import {cookies} from "next/headers";
import {permanentRedirect} from "next/navigation";

export const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
})

const getUserToken = async () => {
    await new Promise((resolve) => setTimeout(resolve, 5000));

    const cookieStore = await cookies();
    const session = cookieStore.get("session")

    if(!session) {
        permanentRedirect("/auth/login")
    }

    if(session.value.trim() === "") {
        permanentRedirect("/auth/login")
    }

    return session.value
}

axiosInstance.interceptors.request.use(
    async config => {
        const skipAuth = config.skipAuth !== undefined ? config.skipAuth : false
        if(!skipAuth) {
            const token = await getUserToken()
            config.headers.Authorization = `Bearer ${token}`
        }
        return config
    },
    err => Promise.reject(err)
)