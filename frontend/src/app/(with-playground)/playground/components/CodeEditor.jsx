"use client"

import { StreamLanguage } from '@codemirror/language'
import ReactCodeMirror from '@uiw/react-codemirror'
import { vscodeDark } from '@uiw/codemirror-theme-vscode'
import { java, c, cpp } from '@codemirror/legacy-modes/mode/clike'
import { javascript } from '@codemirror/legacy-modes/mode/javascript'
import { python } from '@codemirror/legacy-modes/mode/python'
import { usePlaygroundContext } from '@/contexts/playground'
import { useSuspenseQuery } from '@tanstack/react-query'
import {fetchCodeSnippet} from "@/actions/PlaygroundActions";

export default function CodeEditor() {
    const { state: { code, language }, dispatch } = usePlaygroundContext()

    // useSuspenseQuery({
    //     queryKey: ['editor', language],
    //     queryFn: () => fetchCodeSnippet(language, (data) => dispatch({ type: 'setCode', payload: data.snippet })),
    //     refetchOnMount: false,
    // })

    const extensions = language === 'c' ? [StreamLanguage.define(c)]
        : language === 'cpp' ? [StreamLanguage.define(cpp)]
            : language === 'java' ? [StreamLanguage.define(java)]
                : language === 'python' ? [StreamLanguage.define(python)]
                    : language === 'javascript' ? [StreamLanguage.define(javascript)] : []

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