import { StreamLanguage } from '@codemirror/language'
import ReactCodeMirror from '@uiw/react-codemirror'
import { vscodeDark } from '@uiw/codemirror-theme-vscode'
import { java, c, cpp } from '@codemirror/legacy-modes/mode/clike'
import { javascript } from '@codemirror/legacy-modes/mode/javascript'
import { python } from '@codemirror/legacy-modes/mode/python'
import { useSuspenseQuery } from '@tanstack/react-query'

import { usePlaygroundContext } from '../contexts/playgroundcontext'
import {SnippetType} from "../types";

const fetchCodeSnippet = async (language: string, onSuccess: (data: SnippetType) => void) => {
    const data = await (await fetch(`http://localhost:3000/api/problems/1/snippets?language=${language}`)).json()
    onSuccess(data)
    return data
}

const CodeEditor = () => {
    const { state: { code, language }, dispatch } = usePlaygroundContext()

    useSuspenseQuery({
        queryKey: ['editor', language],
        queryFn: () => fetchCodeSnippet(language, (data) => dispatch({ type: 'setCode', payload: data.snippet })),
        refetchOnMount: false,
    })

    const extensions = language == 'C' ? [StreamLanguage.define(c)]
        : language == 'Cpp' ? [StreamLanguage.define(cpp)]
            : language === 'Java' ? [StreamLanguage.define(java)]
                : language === 'Python' ? [StreamLanguage.define(python)]
                    : language === 'Javascript' ? [StreamLanguage.define(javascript)] : []

    return (
        <ReactCodeMirror
            value={code}
            theme={vscodeDark}
            extensions={extensions}
            basicSetup={{
                tabSize: 2,
            }}
            onChange={value => dispatch({ type: 'setCode', payload: value })}
            className='flex-grow overflow-y-auto border border-white rounded-xl'
            height='100%'
        />
    )
}

export default CodeEditor;