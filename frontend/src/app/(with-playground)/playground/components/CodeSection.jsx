import { Suspense } from "react";
import CodeButtons from "./CodeButtons";
import CodeEditor from "./CodeEditor";
import LanguageSelector from "./LanguageSelector";
import {Settings} from "lucide-react";

export default () => (
    <div className="h-[100%] flex flex-col gap-2">
        <div className='flex justify-between items-center'>
            <LanguageSelector />
            <div className='flex gap-3 items-center'>
                <Settings size={16} />
                <CodeButtons />
            </div>
        </div>
        <Suspense fallback={<div>Loading....</div>}>
            <CodeEditor />
        </Suspense>
    </div>
)