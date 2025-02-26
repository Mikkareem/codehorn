import {ResizableHandle, ResizablePanel, ResizablePanelGroup} from "@/components/ui/resizable";
import FirstSectionTabs from "@/app/(with-playground)/playground/components/FirstSectionTabs";
import SecondSectionTabs from "@/app/(with-playground)/playground/components/SecondSectionTabs";
import ThirdSectionTabs from "@/app/(with-playground)/playground/components/ThirdSectionTabs";

export default () => (
    <ResizablePanelGroup direction='horizontal'>
        <ResizablePanel defaultSize={50} className='p-2'>
            <FirstSectionTabs className="w-full h-full flex flex-col rounded-xl p-2"/>
        </ResizablePanel>
        <ResizableHandle className="bg-transparent"/>
        <ResizablePanel defaultSize={50}>
            <ResizablePanelGroup direction='vertical'>
                <ResizablePanel defaultSize={50} minSize={50} className='p-2'>
                    <SecondSectionTabs className="h-full flex flex-col rounded-xl p-2"/>
                </ResizablePanel>
                <ResizableHandle className="bg-transparent"/>
                <ResizablePanel defaultSize={50} className='p-2'>
                    <ThirdSectionTabs className="w-full h-full flex flex-col rounded-xl p-2"/>
                </ResizablePanel>
            </ResizablePanelGroup>
        </ResizablePanel>
    </ResizablePanelGroup>
)