'use client'

import { usePlaygroundContext } from "@/contexts/playground"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import CodeDescription from "./CodeDescription"
import SubmissionResult from "./SubmissionResult"
import Submissions from "./Submissions";
import Solution from "./Solution";

export default ({ className, collapsed }) => {
    const { state: { selectedFirstSectionTab, firstSectionTabs }, dispatch } = usePlaygroundContext()

    const tabsListCollapseRelatedClasses = `${collapsed ? 'rotate-90 origin-[0_36px] mt-[-36px]' : ''}`

    return (
        <Tabs
            value={selectedFirstSectionTab.type}
            onValueChange={(value)=>{ dispatch({ type: 'firstSectionTabChange', payload: value }) }}
            className={className}
        >
            <TabsList className={`justify-start w-max ${tabsListCollapseRelatedClasses}`}>
                {firstSectionTabs.map((tab)=>(
                    <TabsTrigger key={tab.type} value={tab.type}>{tab.title}</TabsTrigger>
                ))}
            </TabsList>
            {
                (collapsed === false) ? firstSectionTabs.map(tab => (
                    <TabsContent key={tab.type} value={tab.type} className="flex-grow overflow-auto">
                        {
                            (()=>{
                                switch(tab.type) {
                                    case 'description':
                                        return <CodeDescription />
                                    case 'submissions':
                                        return <Submissions />
                                    case 'submission':
                                        return <SubmissionResult />
                                    case 'solution':
                                        return <Solution />
                                }
                            })()
                        }
                    </TabsContent>
                )) : null
            }
        </Tabs>
    )
}