import {NonAcceptedSubmission} from "../types";

const FailureSubmission = ({
   submission
}: {
    submission: NonAcceptedSubmission
}) => {
    return <div>
        <p>{submission.verdict}</p>
        <p>{submission.submittedCode}</p>
    </div>
}

export default FailureSubmission;