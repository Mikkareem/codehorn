import {createContext, use, useReducer} from "react";


const PlaygroundContext = createContext(null)

const playgroundDispatchFn = (state, action) => {
    switch (action.type) {
        case 'changeLanguage': {
            return { ...state, language: action.payload }
        }
        case 'setCode': {
            return { ...state, code: action.payload };
        }
        case 'runCode': {
            const testResultTab = state.thirdSectionTabs.find(tab => tab.type === 'testResults')
            if(testResultTab) {
                return { ...state, selectedThirdSectionTab: testResultTab }
            } else {
                return state
            }
        }
        case 'submitCode': {
            const submissionTab = state.firstSectionTabs.find(tab => tab.type === 'submission')
            if(submissionTab) {
                return { ...state, selectedFirstSectionTab: submissionTab }
            } else {
                return state
            }
        }
        case 'firstSectionTabChange': {
            const selectedTab = state.firstSectionTabs.find((tab) => tab.type === action.payload) || state.firstSectionTabs[0]
            return {...state, selectedFirstSectionTab: selectedTab};
        }
        case 'secondSectionTabChange': {
            const selectedTab = state.secondSectionTabs.find((tab) => tab.type === action.payload) || state.secondSectionTabs[0]
            return {...state, selectedSecondSectionTab: selectedTab};
        }
        case 'thirdSectionTabChange': {
            const selectedTab = state.thirdSectionTabs.find((tab) => tab.type === action.payload) || state.thirdSectionTabs[0]
            return {...state, selectedThirdSectionTab: selectedTab};
        }
    }
}

const initialState = {
    code: '',
    language: 'C',
    firstSectionTabs: [
        { type: 'description', isCloseable: false, title: 'Description' },
        { type: 'submissions', isCloseable: false, title: 'Submissions' },
        { type: 'submission', isCloseable: false, title: 'Submission' },
        { type: 'solution', isCloseable: false, title: 'Solution' },
    ],
    secondSectionTabs: [
        { type: 'editor', isCloseable: false, title: 'Code' },
        { type: 'notes', isCloseable: true, title: 'Notes' },
    ],
    thirdSectionTabs: [
        { type: 'testcases', isCloseable: false, title: 'Testcases' },
        { type: 'testResults', isCloseable: false, title: 'Test Results' },
    ],
    selectedFirstSectionTab: { type: 'description', isCloseable: false, title: 'Description' },
    selectedSecondSectionTab: { type: 'editor', isCloseable: false, title: 'Code' },
    selectedThirdSectionTab: { type: 'testcases', isCloseable: false, title: 'Testcases' },
    problem: null
}

export default function PlaygroundContextProvider({ children, problem }) {
    const newState = { ...initialState, problem }
    const [state, dispatch] = useReducer(playgroundDispatchFn, newState)

    return (
        <PlaygroundContext
            value={{state, dispatch}}
        >
            {children}
        </PlaygroundContext>
    )
}

export const usePlaygroundContext = () => {
    const context = use(PlaygroundContext)
    if(!context) {
        throw new Error("Playground Context values are not provided")
    }
    return context;
}