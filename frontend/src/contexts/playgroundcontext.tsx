import {Problem} from "../types";
import {useContext, useReducer} from "react";
import {initialState, PlaygroundContext, playgroundDispatchFn} from "./playground.ts";

type PlaygroundContextProviderProps = {
    problem: Problem,
    children: React.ReactNode
}

export default function PlaygroundContextProvider(
    { children, problem }: PlaygroundContextProviderProps
) {
    const newState = { ...initialState, problem }
    const [state, dispatch] = useReducer(playgroundDispatchFn, newState)

    return (
        <PlaygroundContext.Provider value={{state, dispatch}}>
            {children}
        </PlaygroundContext.Provider>
    )
}

export const usePlaygroundContext = () => {
    const context = useContext(PlaygroundContext)
    if(!context) {
        throw new Error("Playground Context values are not provided")
    }
    return context;
}