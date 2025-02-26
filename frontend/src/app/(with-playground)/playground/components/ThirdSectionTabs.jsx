'use client'

import { usePlaygroundContext } from "@/contexts/playground"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import TestResults from "./TestResults"
import Testcases from "./Testcases"

export default ({ className, collapsed }) => {
    const { state: { selectedThirdSectionTab, thirdSectionTabs }, dispatch } = usePlaygroundContext()

    const tabsListCollapseRelatedClasses = `${collapsed ? 'rotate-90 origin-[0_36px] mt-[-36px]' : ''}`

    return <Tabs value={selectedThirdSectionTab.type} onValueChange={(value)=>{ dispatch({ type: 'thirdSectionTabChange', payload: value }) }} className={className}>
        <TabsList className={`justify-start w-max ${tabsListCollapseRelatedClasses}`}>
            {thirdSectionTabs.map((tab)=>(
                <TabsTrigger key={tab.type} value={tab.type}>{tab.title}</TabsTrigger>
            ))}
        </TabsList>
        {
            !collapsed ? thirdSectionTabs.map(tab => (
                <TabsContent key={tab.type} value={tab.type} className="flex-grow overflow-auto">
                    {
                        (()=>{
                            switch(tab.type) {
                                case 'testcases':
                                    return <Testcases />
                                case 'testResults':
                                    return <TestResults />
                            }
                        })()
                    }
                </TabsContent>
            )) : null
        }
    </Tabs>
}