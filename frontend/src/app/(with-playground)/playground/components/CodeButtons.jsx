'use client'

import { Button } from "@/components/ui/button"
import { useMutation } from "@tanstack/react-query"
import {notFound} from "next/navigation";
import {usePlaygroundContext} from "@/contexts/playground";
import {runCode, submitCode} from "@/actions/PlaygroundActions";

export default function CodeButtons() {
    const { state: { code, language, problem }, dispatch } = usePlaygroundContext()

    if(!problem) notFound()

    const { mutate: run, isPending: runPending } = useMutation({
        mutationKey: ['run-code'],
        mutationFn: (code) => {
            dispatch({ type: 'runCode' })
            return runCode(code, language, problem.problemNo+"", problem.sampleTestcases)
        }
    })

    const { mutate: submit, isPending: submitPending } = useMutation({
        mutationKey: ['submit-code'],
        mutationFn: (code) => {
            dispatch({ type: 'submitCode' })
            return submitCode(code, language, problem.problemNo+"", problem.sampleTestcases)
        }
    })

    return <div className="flex gap-3">
        <Button
            className="bg-green-700 hover:bg-green-900 text-white h-7"
            onClick={() => run(code)}
        >
            {runPending ? 'Running' : 'Run'}
        </Button>
        <Button
            onClick={() => submit(code)}
            className="h-7"
        >
            {submitPending ? 'Submitting' : 'Submit'}
        </Button>
    </div>
}