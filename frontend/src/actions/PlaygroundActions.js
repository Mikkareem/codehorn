
export const runCode = async (code, language, problemId, testcases) => {
    const body = {
        problemId,
        language,
        sampleTestcases: testcases,
        userCode: code
    }
    const response = await fetch(
        `http://localhost:8080/problems/${problemId}/run`,
        {
            cache: 'no-cache',
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        }
    )
    return await response.json()
}

export const submitCode = async (code, language, problemId, testcases) => {
    const body = {
        problemId,
        language,
        sampleTestcases: testcases,
        userCode: code
    }
    const response = await fetch(
        `http://localhost:3000/api/problems/${problemId}/submit`,
        {
            cache: 'no-cache',
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        }
    )
    return await response.json()
}

export const fetchCodeSnippet = async (language, onSuccess) => {
    const data = await (await fetch(`http://localhost:8080/problems/1/snippets?language=${language}`)).json()
    onSuccess(data)
    return data
}

export const getSubmission = async (submissionId, problemId) => {
    const response = await fetch(`http://localhost:8080/problems/${problemId}/submissions/${submissionId}`, { cache: 'no-cache' })
    return await response.json()
}