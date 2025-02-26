'use client'

import {useEffect, useLayoutEffect, useRef, useState} from "react";
import {ResizableHandle, ResizablePanel, ResizablePanelGroup} from "@/components/ui/resizable";
import FirstSectionTabs from "@/app/(with-playground)/playground/components/FirstSectionTabs";
import SecondSectionTabs from "@/app/(with-playground)/playground/components/SecondSectionTabs";
import ThirdSectionTabs from "@/app/(with-playground)/playground/components/ThirdSectionTabs";

export default () => {

    const [groupCollapsedSize, setGroupCollapsedSize] = useState(0)

    const [group1Collapsed, setGroup1Collapsed] = useState(false);
    const [group3Collapsed, setGroup3Collapsed] = useState(false);

    const group1Ref = useRef(null);
    const group2Ref = useRef(null);
    const group3Ref = useRef(null);

    useEffect(() => {
        if(group1Collapsed) {
            group1Ref.current.collapse()
        } else {
            group1Ref.current.expand()
        }
    }, [group1Collapsed])

    useEffect(() => {
        if(group3Collapsed) {
            group3Ref.current.collapse()
        } else {
            group3Ref.current.expand()
        }
    }, [group3Collapsed])

    useLayoutEffect(() => {
        const panelGroup = document.querySelector('[data-panel-group-id="panels"]')
        const observer = new ResizeObserver(() => {
            const width = panelGroup.offsetWidth
            const groupSize = (50/width)*100
            setGroupCollapsedSize(groupSize)
        })

        observer.observe(panelGroup)

        return () => {
            observer.unobserve(panelGroup)
            observer.disconnect()
        }
    }, [])

    const group2CollapseToggle = () => {
        if(!group1Collapsed) {
            setGroup1Collapsed(true);
        } else {
            setGroup1Collapsed(false);
        }

        if(!group3Collapsed) {
            setGroup3Collapsed(true);
        } else {
            setGroup3Collapsed(false);
        }
    }

    return (
        <ResizablePanelGroup direction='horizontal' id='panels'>
            <ResizablePanel
                defaultSize={33}
                collapsible={true}
                collapsedSize={groupCollapsedSize}
                ref={group1Ref}
            >
                <FirstSectionTabs
                    className="w-full h-full flex flex-col rounded-xl bg-card border p-2"
                    collapsed={group1Collapsed}
                />
            </ResizablePanel>

            <ResizableHandle className="bg-transparent hover:bg-blue-600"/>

            <ResizablePanel
                defaultSize={33}
                minSize={33}
                ref={group2Ref}
            >
                <SecondSectionTabs
                    className="h-full flex flex-col rounded-xl bg-card border p-2"
                    toggler={group2CollapseToggle}
                    inExpandedMode={!group1Collapsed && !group3Collapsed}
                />
            </ResizablePanel>

            <ResizableHandle className="bg-transparent hover:bg-blue-600"/>

            <ResizablePanel
                defaultSize={33}
                collapsible={true}
                collapsedSize={groupCollapsedSize}
                ref={group3Ref}
            >
                <ThirdSectionTabs
                    className="w-full h-full flex flex-col bg-card border rounded-xl p-2"
                    collapsed={group3Collapsed}
                />
            </ResizablePanel>
        </ResizablePanelGroup>
    )
}