'use client'

import { usePlaygroundContext } from "@/contexts/playground"
import { Label } from "@/components/ui/label"
import { useState } from "react"

export default () => {
    const [selectedTestcase, setSelectedTestcase] = useState(0)
    const { state: { problem } } = usePlaygroundContext();

    if(!problem) {
        return
    }

    const testcases = problem.sampleTestcases

    return (
        <div className="h-full overflow-y-auto">
            <div className="flex gap-6 flex-wrap py-4">
                {testcases.map((_, index) => (
                    <div
                        key={index}
                        onClick={() => setSelectedTestcase(index)}
                        className={
                            `px-4 py-2 rounded-md text-sm cursor-pointer bg-secondary text-secondary-foreground ${index === selectedTestcase && 'border-2 border-primary'}`
                        }
                    >
                        Case {index+1}
                    </div>
                ))}
            </div>
            {testcases.length > 0
                ? (<Testcase testcase={testcases[selectedTestcase]}/>)
                : (
                    <div className='w-full h-full flex justify-center items-center'>
                        <p>No testcases found</p>
                    </div>
                )
            }
        </div>
    )
}

const Testcase = ({ testcase }) => {
    return (
        <div>
            <h4 className="text-xl font-semibold">Inputs</h4>
            {testcase.inputs.map((input) => (
                <div key={input.details.name}>
                    <Label>{input.details.name}=</Label>
                    <div className="rounded-lg px-4 py-2 bg-secondary text-secondary-foreground">
                        <p>{input.value}</p>
                    </div>
                </div>
            ))}
        </div>
    )
}