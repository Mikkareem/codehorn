
import { usePlaygroundContext } from "../contexts/playgroundcontext"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "./basic/ui/tabs"
import TestResults from "./TestResults"
import Testcases from "./Testcases"

export default ({ className }: {className ?: string}) => {
  const { state: { selectedThirdSectionTab, thirdSectionTabs }, dispatch } = usePlaygroundContext()

  return <Tabs value={selectedThirdSectionTab.type} onValueChange={(value)=>{ dispatch({ type: 'thirdSectionTabChange', payload: value }) }} className={className}>
    <TabsList className="justify-start w-max">
      {thirdSectionTabs.map((tab)=>(
        <TabsTrigger key={tab.type} value={tab.type}>{tab.title}</TabsTrigger>
      ))}
    </TabsList>
    {
      thirdSectionTabs.map(tab => (
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
      ))
    }
  </Tabs>
}