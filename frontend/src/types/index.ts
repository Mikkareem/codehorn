import { Dispatch } from 'react'

export type CodeRequest = {
    problemId: string,
    userCode: string,
    language: string,
    sampleTestcases: Testcase[]
}

export type TestcaseResult = {
    testcase: Testcase,
    yourResult?: string,
    expectedResult?: string,
    stdout?: string,
    result: 'Accepted' | 'Wrong Answer' | 'Runtime Error' | 'Compilation Error' | 'Time Limit Exceeded'
}

export type Testcase = {
    id: number,
    inputs: {
        details: TestcaseInputFormat,
        value: string
    }[],
    isHidden: boolean
}

export type Difficulty = 'Easy' | 'Medium' | 'Hard'

export type Problem = {
    problemNo: number,
    title: string,
    description: string,
    difficulty: Difficulty,
    preferredSnippet?: string,
    preferredLanguage?: string,
    sampleTestcases: Testcase[]
}

export type ProblemListItem = {
    problemNo: number,
    problemName: string,
    problemDifficulty: Difficulty,
    isSolved: boolean,
}

type FirstSectionTabType = 'description' | 'submissions' | 'submission' | 'solution'
type SecondSectionTabType = 'editor' | 'notes'
type ThirdSectionTabType = 'testcases' | 'testResults'

type Tab<T> = {
    type: T,
    title: string,
    isCloseable: boolean
}

export type PlaygroundState = {
    code: string;
    language: string;
    firstSectionTabs: Tab<FirstSectionTabType>[];
    secondSectionTabs: Tab<SecondSectionTabType>[];
    thirdSectionTabs: Tab<ThirdSectionTabType>[];
    selectedFirstSectionTab: Tab<FirstSectionTabType>;
    selectedSecondSectionTab: Tab<SecondSectionTabType>;
    selectedThirdSectionTab: Tab<ThirdSectionTabType>;
    problem: Problem | null;
}

export type PlaygroundActionType = 'changeLanguage' | 'setCode' | 'runCode' | 'submitCode' | 'firstSectionTabChange' | 'secondSectionTabChange' | 'thirdSectionTabChange'

export type PlaygroundAction<T = unknown> = {
    type: PlaygroundActionType,
    payload ?: T,
}

export type PlaygroundContextType = {
    state: PlaygroundState,
    dispatch: Dispatch<PlaygroundAction>
}

export type TestcaseCollectionType = 'SINGLE' | 'LIST' | 'LIST_LIST'
export type TestcaseType = 'STRING' | 'NON_STRING'

export type TestcaseInputFormat = {
    name: string,
    collectionType: TestcaseCollectionType,
    type: TestcaseType,
    displayOrder: number
}

export type TestcaseFormat = {
    id: number,
    formats: TestcaseInputFormat[],
}

export type ProblemCrudItem = {
    id: number,
    title: string,
    description: string,
    difficulty: Difficulty,
    snippets: {
        c: string,
        cpp: string,
        java: string,
        python: string,
        javascript: string,
    },
    fileContent: {
        c: string,
        creplaceStr: string,
        cpp: string,
        cppReplaceStr: string,
        java: string,
        javaReplaceStr: string,
        python: string,
        pythonReplaceStr: string,
        javascript: string,
        javascriptReplaceStr: string
    },
    testcaseFormats: TestcaseInputFormat[]
}

export type AcceptedSubmission = {
    submissionId: number,
    problemId: number,
    userId: string,
    submittedCode: string,
    codeLanguage: string,
    submissionTime: string,
    executionTime: string,
    memoryConsumption: number,
    verdict: string,
}

export type NonAcceptedSubmission = {
    submissionId: number,
    problemId: number,
    userId: string,
    submittedCode: string,
    codeLanguage: string,
    submissionTime: string,
    verdict: string,
    totalTestcasesCount: number,
    executedTestcasesCount: number,
    failedTestcase: {
        expectedOutput: string,
        yourOutput: string,
        stdout: string,
        inputs: {
            name: string,
            input: string
        }[]
    }
}

export type SnippetType = {
    snippet: string;
    [key: string]: unknown; // Allows additional keys
};