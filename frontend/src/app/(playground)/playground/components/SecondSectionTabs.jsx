'use client'

import { usePlaygroundContext } from "@/contexts/playground"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import CodeSection from "./CodeSection"

export default ({ className }) => {
    const { state: { selectedSecondSectionTab, secondSectionTabs }, dispatch } = usePlaygroundContext()

    return <Tabs
        value={selectedSecondSectionTab.type}
        onValueChange={(value)=>{ dispatch({ type: 'secondSectionTabChange', payload: value}) }}
        className={className}
    >
        <TabsList className="justify-start w-max">
            {secondSectionTabs.map((tab)=>(
                <TabsTrigger key={tab.type} value={tab.type}>{tab.title}</TabsTrigger>
            ))}
        </TabsList>
        {
            secondSectionTabs.map(tab => (
                <TabsContent key={tab.type} value={tab.type} className="max-h-[90%] flex-grow">
                    {
                        (()=>{
                            switch(tab.type) {
                                case 'editor':
                                    return <CodeSection />
                                case 'notes':
                                    return <div>Notes</div>
                            }
                        })()
                    }
                </TabsContent>
            ))
        }
    </Tabs>
}