'use client'

import PlaygroundContextProvider from "@/contexts/playground"
import PanelModel from '@/app/(with-playground)/playground/components/PanelModel'

const PlaygroundPage = ({ problem }) => {
    return (
        <PlaygroundContextProvider problem={problem}>
            <div className='flex flex-col flex-grow overflow-hidden'>
                <PanelModel />
            </div>
        </PlaygroundContextProvider>
    )
}

export default PlaygroundPage