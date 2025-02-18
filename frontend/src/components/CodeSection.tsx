import { Suspense } from "react";
import CodeButtons from "./CodeButtons";
import CodeEditor from "./CodeEditor";
import LanguageSelector from "./LanguageSelector";
import {Settings} from "lucide-react";

export default () => (
    <div className="h-[100%] flex flex-col gap-2">
        <div className='flex justify-between'>
            <LanguageSelector className='self-start'/>
            <div className='flex gap-3 items-center'>
                <Settings size={20} />
                <CodeButtons />
            </div>
        </div>
        {/* <div className="flex-grow"> */}
        <Suspense fallback={<div>Loading....</div>}>
            <CodeEditor />
        </Suspense>
        {/* </div> */}
    </div>
)