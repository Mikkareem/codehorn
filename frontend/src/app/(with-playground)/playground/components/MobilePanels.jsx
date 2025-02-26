import FirstSectionTabs from "@/app/(with-playground)/playground/components/FirstSectionTabs";
import SecondSectionTabs from "@/app/(with-playground)/playground/components/SecondSectionTabs";
import ThirdSectionTabs from "@/app/(with-playground)/playground/components/ThirdSectionTabs";

export default () => (
    <>
        <FirstSectionTabs className="w-full md:h-full flex flex-col rounded-xl p-2"/>
        <SecondSectionTabs className="h-full flex flex-col rounded-xl p-2"/>
        <ThirdSectionTabs className="w-full h-full flex flex-col rounded-xl p-2"/>
    </>
)
