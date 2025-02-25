'use client'

import { ResizablePanelGroup, ResizablePanel, ResizableHandle } from "@/components/ui/resizable"
import FirstSectionTabs from "./FirstSectionTabs"
import SecondSectionTabs from "./SecondSectionTabs"
import ThirdSectionTabs from "./ThirdSectionTabs"
import {useRef} from "react";

const NewModelPanels = () => {

    const group1Ref = useRef(null);
    const group2Ref = useRef(null);

    const groupCollapseToggle = () => {
        if(!group1Ref.current.isCollapsed() || !group2Ref.current.isCollapsed()) {
            group1Ref.current.collapse();
            group2Ref.current.collapse();
        } else {
            group1Ref.current.expand();
            group2Ref.current.expand();
        }
    }

    return (
        <ResizablePanelGroup direction='horizontal' >
            <ResizablePanel
                defaultSize={33}
                collapsible={true}
                ref={group1Ref}
            >
                <FirstSectionTabs className="w-full h-full flex flex-col rounded-xl bg-card border p-2"/>
            </ResizablePanel>

            <ResizableHandle className="bg-transparent"/>

            <ResizablePanel
                defaultSize={33}
                minSize={33}
            >
                <SecondSectionTabs
                    className="h-full flex flex-col rounded-xl bg-card border p-2"
                    toggler={groupCollapseToggle}
                />
            </ResizablePanel>

            <ResizableHandle className="bg-transparent"/>

            <ResizablePanel
                defaultSize={33}
                collapsible={true}
                ref={group2Ref}
            >
                <ThirdSectionTabs className="w-full h-full flex flex-col bg-card border rounded-xl p-2"/>
            </ResizablePanel>
        </ResizablePanelGroup>
    )
}

const OldModelPanels = () => (
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

const MobilePanels = () => (
    <>
        <FirstSectionTabs className="w-full md:h-full flex flex-col rounded-xl p-2"/>
        <SecondSectionTabs className="h-full flex flex-col rounded-xl p-2"/>
        <ThirdSectionTabs className="w-full h-full flex flex-col rounded-xl p-2"/>
    </>
)

export default () => {
    // const [usage] = useState<'new' | 'old' | 'mobile'>(() => Math.random() > 0.5 ? 'mobile' : Math.random() > 0.5 ? 'old' : 'new')

    // if(usage === 'new') {
    //   return <NewModelPanels />
    // } else if(usage === 'old') {
    //   return <OldModelPanels />
    // } else if(usage === 'mobile') {
    //   return <MobilePanels />
    // }

    return <NewModelPanels />
}