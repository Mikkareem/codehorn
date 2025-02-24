const FailureSubmission = ({ submission }) => {
    return <div>
        <p>{submission.verdict}</p>
        <p>{submission.submittedCode}</p>
    </div>
}

export default FailureSubmission;