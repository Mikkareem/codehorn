import { axiosInstance } from "@/config/AxiosConfig"

export const runCode = async (code, language, problemId, testcases) => {
    const body = {
        language,
        userTestcases: testcases,
        userCode: code
    }
    const response = await axiosInstance.post(`/problems/${problemId}/run`, body)
    return await response.data
}

export const submitCode = async (code, language, problemId, testcases) => {
    const body = {
        language,
        sampleTestcases: testcases,
        userCode: code
    }
    const response = await axiosInstance.post(`/problems/${problemId}/submit`, body)
    return await response.data
}

export const fetchCodeSnippet = async (language, onSuccess) => {
    const data = await (await fetch(`http://localhost:3000/api/problems/1/snippets?language=${language}`)).json()
    onSuccess(data)
    return data
}

export const getSubmission = async (submissionId, problemId) => {
    const response = await fetch(`http://localhost:3000/api/problems/${problemId}/submissions/${submissionId}`, { cache: 'no-cache' })
    return await response.json()
}
