import {Route, Routes} from "react-router";
import Home from "./routes/Home.tsx";
import Problems from "./routes/Problems.tsx";

function App() {
  return (
    <Routes>
      <Route index element={<Home />}/>
      <Route path="problems" element={<Problems />}/>
    </Routes>
  )
}

export default App
