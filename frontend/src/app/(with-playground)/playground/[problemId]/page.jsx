
import { getProblemById } from "@/actions/PlaygroundActions"
import PlaygroundPage from "../PlaygroundPage"

export default async (props) => {
    const params = await props.params

    const data = await getProblemById(params.problemId)

    return <PlaygroundPage problem={data.problem}/>
}