import {useMutationState, useQuery} from "@tanstack/react-query"
import SuccessSubmission from "./SuccessSubmission";
import FailureSubmission from "./FailureSubmission";
import {getSubmission} from "@/actions/PlaygroundActions";

const Submission = ({ id, problemId }) => {
    const { data, isFetching } = useQuery({
        queryKey: ['get-submission', id, problemId],
        queryFn: () => getSubmission(id, problemId)
    })

    if(isFetching) {
        return <div>Fetching the results....</div>
    }

    return (
        <>{data.verdict === 'Accepted' ? <SuccessSubmission submission={data}/> : <FailureSubmission submission={data}/>}</>
    )
}

// eslint-disable-next-line react/display-name,import/no-anonymous-default-export
export default () => {

    const submitMutations = useMutationState({
        filters: { mutationKey: ['submit-code'] }
    })

    if(submitMutations.length < 1) {
        return <div>No submissions yet. Either select a submission or submit the code.</div>
    }

    const isSubmissionPending = submitMutations[submitMutations.length - 1].status === 'pending'

    if(isSubmissionPending) {
        return <div>Submitting the code.....</div>
    }

    console.log(submitMutations[submitMutations.length - 1].data)

    const submission = (submitMutations[submitMutations.length - 1].data)

    return (
        <Submission id={submission.submissionId} problemId={submission.problemId}/>
    )
}