'use client'

import {useRef} from "react";
import {ResizableHandle, ResizablePanel, ResizablePanelGroup} from "@/components/ui/resizable";
import FirstSectionTabs from "@/app/(with-playground)/playground/components/FirstSectionTabs";
import SecondSectionTabs from "@/app/(with-playground)/playground/components/SecondSectionTabs";
import ThirdSectionTabs from "@/app/(with-playground)/playground/components/ThirdSectionTabs";

export default () => {

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