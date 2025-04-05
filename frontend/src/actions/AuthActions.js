"use server"

import {permanentRedirect} from "next/navigation";
import {cookies} from "next/headers";
import {axiosInstance} from "@/config/AxiosConfig";

export const loginAction = async (previousState, formData) => {
    const username = formData.get('username')
    const password = formData.get('password')

    const { data } = await axiosInstance.post('/auth/login', { username, password }, { skipAuth: true })

    if(data.status === "SUCCESS") {
        const cookiesStore = await cookies()
        cookiesStore.set('session', data.token, null)
        permanentRedirect('/dashboard')
    } else if(data.status === "FAILURE") {
        return data.error
    } else {
        return "Something went wrong"
    }
}

export const getRolesOfUser = async () => {
    const { data } = await axiosInstance.get('/roles')
    return data.roles
}

export const fetchUserData = async (userId) => {
    await new Promise(resolve => setTimeout(resolve, 3000))

    console.log('Fetching data for user: ', userId)

    return {
        userId: userId+1,
        message: 'Successfully fetched user data'
    }
}