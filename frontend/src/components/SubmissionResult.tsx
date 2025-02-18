import {useMutationState, useQuery} from "@tanstack/react-query"
import {AcceptedSubmission, NonAcceptedSubmission} from "../types";
import SuccessSubmission from "./SuccessSubmission";
import FailureSubmission from "./FailureSubmission";

const getSubmission = async (submissionId: number, problemId: number) => {
  const response = await fetch(`http://localhost:3000/api/problems/${problemId}/submissions/${submissionId}`, { cache: 'no-cache' })
  return await response.json()
}

const Submission = ({ id, problemId }: { id: number, problemId: number }) => {
  const { data, isFetching } = useQuery({
    queryKey: ['get-submission', id, problemId],
    queryFn: () => getSubmission(id, problemId)
  })

  if(isFetching) {
    return <div>Fetching the results....</div>
  }

  return (
    <>{data.verdict == 'Accepted' ? <SuccessSubmission submission={data as AcceptedSubmission}/> : <FailureSubmission submission={data as NonAcceptedSubmission}/>}</>
  )
}

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

  const submission = (submitMutations[submitMutations.length - 1].data as { submissionId: number, problemId: number })

  return (
    <Submission id={submission.submissionId} problemId={submission.problemId}/>
  )
}