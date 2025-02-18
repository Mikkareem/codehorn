import { usePlaygroundContext } from "../contexts/playgroundcontext"
import { Badge } from "./basic/ui/badge"
import { ThumbsUp, ThumbsDown } from "lucide-react"
import Markdown from "react-markdown"
import rehypeRaw from "rehype-raw"

export default () => {
  const { state: { problem } } = usePlaygroundContext()
  return <div className="flex flex-col gap-4">
    <h1 className="text-3xl font-extrabold">
      {`${problem?.problemNo}. ${problem?.title}`}
    </h1>
    <div className="flex gap-4">
      {
        (() => {
          switch(problem?.difficulty) {
            case 'Easy': {
              return <Badge className="bg-green-700 hover:bg-green-700">Easy</Badge>
            }
            case 'Medium': {
              return <Badge className="bg-orange-400 hover:bg-orange-400">Medium</Badge>
            }
            case 'Hard': {
              return <Badge className="bg-red-700 hover:bg-red-700">Hard</Badge>
            }
          }
        })()
      }
      <div className="flex items-center gap-2">
        <ThumbsUp />
        <p>3728</p>
      </div>
      <div className="flex items-center gap-2">
        <ThumbsDown />
        <p>38</p>
      </div>
    </div>
    <Markdown
      rehypePlugins={[rehypeRaw]}
    >
      {/* {`<p>Given <code>prices</code>, find the best time to buy and sell stocks.</p>`} */}
      {problem?.description}
    </Markdown>
  </div>
}