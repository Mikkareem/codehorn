
import { usePlaygroundContext } from '../contexts/playgroundcontext'

const CodeViewer = () => {
  const { state: { code } } = usePlaygroundContext()
  return (
    <p>{code}</p>
  )
}

export default CodeViewer