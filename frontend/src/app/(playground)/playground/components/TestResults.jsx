'use client'

import { Label } from "@/components/ui/label"
import { useMutationState } from "@tanstack/react-query"
import { useState } from "react"

export default () => {
    const [selectedTestResult, setSelectedTestResult] = useState(0)

    const runCodeMutations = useMutationState({
        filters: { mutationKey: ['run-code'] },
    })

    if(runCodeMutations.length === 0) {
        return <div className='w-full h-full flex justify-center items-center text-center'>
            <p className='w-3/4'>No Test Results found, Please run the code to see the results</p>
        </div>
    }

    const status = runCodeMutations[runCodeMutations.length-1].status

    if(status === 'pending') {
        return <LoadingIndicatorForTestResults />
    } else if(status === 'error') {
        return <div>Unfortunately, we experienced an technical issue. Please try again later.</div>
    }

    const testResults = runCodeMutations[runCodeMutations.length-1].data.results

    return (
        <div className="flex flex-col gap-4 h-full overflow-y-auto">
            <div className="flex gap-6 flex-wrap">
                {testResults.map((testResult, index) => (
                    <div
                        key={index+1}
                        onClick={() => setSelectedTestResult(index)}
                        className={`bg-muted ${testResult.result === 'Accepted' ? 'text-green-500': 'text-red-500'} px-4 py-2 rounded-xl text-sm cursor-pointer 
                        ${index === selectedTestResult && `border-2 ${testResult.result === 'Accepted' ? 'border-green-500': 'border-red-500'}`}`}
                    >
                        Case {index+1}
                    </div>
                ))}
            </div>
            <TestResult testResult={testResults[selectedTestResult]}/>
        </div>
    )
}

const TestResult = ({ testResult }) => {
    return (
        <div className='flex flex-col h-full gap-4'>
            <div className={`my-2 w-max bg-muted ${testResult.result === 'Accepted' ? 'text-green-500': 'text-red-500'} px-4 py-2 rounded-xl`}>
                <h3 className='text-2xl font-extrabold'>{testResult.result}</h3>
            </div>

            <h4 className="text-xl font-semibold">Inputs</h4>
            {testResult.testcase.inputs.map((input) => (
                <div key={input.details.name}>
                    <Label>{input.details.name}=</Label>
                    <div className="bg-secondary text-secondary-foreground rounded-lg px-4 py-2">
                        <p>{input.value}</p>
                    </div>
                </div>
            ))}

            {
                testResult.stdout && (
                    <>
                        <h4 className="text-xl font-semibold">Standard Output</h4>
                        <div className="bg-secondary text-secondary-foreground rounded-lg px-4 py-2">
                            <p>{testResult.stdout}</p>
                        </div>
                    </>
                )
            }

            {
                testResult.yourResult && (
                    <>
                        <h4 className="text-xl font-semibold">Output</h4>
                        <div className="bg-secondary text-secondary-foreground rounded-lg px-4 py-2">
                            <p>{testResult.yourResult}</p>
                        </div>
                    </>
                )
            }

            {
                testResult.expectedResult && (
                    <>
                        <h4 className="text-xl font-semibold">Expected Output</h4>
                        <div className="bg-secondary text-secondary-foregrounde rounded-lg px-4 py-2">
                            <p>{testResult.expectedResult}</p>
                        </div>
                    </>
                )
            }
        </div>
    )
}

const LoadingIndicatorForTestResults = () => {
    return (
        <div className="w-full h-full">
            {/*<Skeleton className='w-full h-1/3 rounded bg-primary/25'/>*/}
            Loading.....
        </div>
    )
}