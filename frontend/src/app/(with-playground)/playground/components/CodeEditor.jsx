"use client"

import { StreamLanguage } from '@codemirror/language'
import ReactCodeMirror from '@uiw/react-codemirror'
import { vscodeDark } from '@uiw/codemirror-theme-vscode'
import { java, c, cpp } from '@codemirror/legacy-modes/mode/clike'
import { javascript } from '@codemirror/legacy-modes/mode/javascript'
import { python } from '@codemirror/legacy-modes/mode/python'
import { usePlaygroundContext } from '@/contexts/playground'
import {fetchCodeSnippet} from "@/actions/PlaygroundActions";
import { startTransition, useActionState, useEffect } from 'react'

export default function CodeEditor() {
    const { state: { code, language, problem }, dispatch } = usePlaygroundContext()

    const [data, fetchCodeSnippetAction, isPending] = useActionState(
        async (_, language) => await fetchCodeSnippet(problem.problemNo, language),
        null
    )

    useEffect(() => {
        async function fetchData(){
            startTransition(async () => await fetchCodeSnippetAction(language))
        }
        fetchData()
    }, [language, problem.problemNo])

    useEffect(() => {
        if(data) {
            dispatch({ type: 'setCode', payload: data.snippet })
        }
    }, [data])

    const extensions = language.toLowerCase() === 'c' ? [StreamLanguage.define(c)]
        : language.toLowerCase() === 'cpp' ? [StreamLanguage.define(cpp)]
            : language.toLowerCase() === 'java' ? [StreamLanguage.define(java)]
                : language.toLowerCase() === 'python' ? [StreamLanguage.define(python)]
                    : language.toLowerCase() === 'javascript' ? [StreamLanguage.define(javascript)] : []

    return (data && !isPending) ? (
        <ReactCodeMirror
            value={code}
            theme={vscodeDark}
            extensions={extensions}
            basicSetup={{
                tabSize: 2,
            }}
            onChange={value => dispatch({ type: 'setCode', payload: value })}
            className='flex-grow overflow-y-auto rounded-xl'
            height='100%'
        />
    ) : <>Loading Snippets.....</>
}