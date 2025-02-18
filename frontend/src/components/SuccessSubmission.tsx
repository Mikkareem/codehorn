import {AcceptedSubmission} from "../types";

const SuccessSubmission = ({
   submission
} : {
    submission: AcceptedSubmission
}) => {
    return <div>
        <p className='text-2xl text-green-500'>{submission.verdict}</p>
        <p>Submitted on ${submission.submissionTime}</p>

        <div className='flex items-center justify-center w-full px-4 py-2'>
            <div className='h-[150px] flex-grow'>
                <p>Execution Time: ${submission.executionTime}</p>
            </div>
            <div className='h-[150px] flex-grow'>
                <p>Memory Consumption: ${submission.memoryConsumption}</p>
            </div>
        </div>

        <div className='bg-secondary text-secondary-foreground p-4'>
            <p>{submission.submittedCode}</p>
        </div>
    </div>
}

export default SuccessSubmission;