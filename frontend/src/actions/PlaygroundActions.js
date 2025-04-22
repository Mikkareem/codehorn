'use server'

import { axiosInstance } from "@/config/AxiosConfig"

export const getProblemById = async (id) => {
    const response = await axiosInstance.get(`/problems/${id}`)
    return response.data
}

export const runCode = async (code, language, problemId, testcases) => {
    const body = {
        language,
        userTestcases: testcases,
        userCode: code
    }
    const response = await axiosInstance.post(`/problems/${problemId}/run`, body)
    return response.data
}

export const submitCode = async (code, language, problemId, testcases) => {
    const body = {
        language,
        userTestcases: testcases,
        userCode: code
    }
    const response = await axiosInstance.post(`/problems/${problemId}/submit`, body)
    return response.data
}

export const fetchCodeSnippet = async (problemId, language) => {
    const response = await axiosInstance.get(`/problems/${problemId}/snippet?language=${language.toLowerCase()}`)
    return response.data
}

export const getSubmission = async (submissionId, problemId) => {
    const response = await axiosInstance.get(`/problems/${problemId}/submissions/${submissionId}`)
    return response.data
}
