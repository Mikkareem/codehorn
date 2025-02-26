import NewModelPanels from "@/app/(with-playground)/playground/components/NewModelPanels";

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