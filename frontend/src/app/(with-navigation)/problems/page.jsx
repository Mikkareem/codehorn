import StudyPlanSection from "@/app/(with-navigation)/problems/components/StudyPlanSection";
import TopicWiseGroupSection from "@/app/(with-navigation)/problems/components/TopicWiseGroupSection";
import CategoryChipsSection from "@/app/(with-navigation)/problems/components/CategoryChipsSection";
import ProblemsTableFilterSection from "@/app/(with-navigation)/problems/components/ProblemsTableFilterSection";
import ProblemsSetTable from "@/app/(with-navigation)/problems/components/ProblemsSetTable";
import {Suspense} from "react";

const ProblemsListPage = async ({ searchParams }) => {

    let sParams = await searchParams;

    const listFilter = sParams['list'] ?? ''
    const difficultyFilter = sParams['difficulty'] ?? ''
    const statusFilter = sParams['status'] ?? ''

    const filters = { listFilter, difficultyFilter, statusFilter }

    return (
        <div className='flex gap-4 pt-6'>
            <div className='flex-grow flex flex-col gap-4'>
                <StudyPlanSection />
                <TopicWiseGroupSection />
                <CategoryChipsSection />
                <ProblemsTableFilterSection />

                <Suspense fallback={<div>Loading...</div>} key={JSON.stringify(filters)}>
                    <ProblemsSetTable filters={filters} />
                </Suspense>
            </div>
            <div className='min-w-[300px]'>
                <div className='rounded-lg bg-background aspect-square w-full'></div>
            </div>
        </div>
    );
};

export default ProblemsListPage;