import SubmissionsSummary from "@/app/(without-navigation-header)/dashboard/components/SubmissionsSummary";
import SessionsSummary from "@/app/(without-navigation-header)/dashboard/components/SessionsSummary";

const Dashboard = async () => {
    return (
        <div className='flex flex-col p-4 gap-2'>
            <h1 className='text-4xl'>Dashboard</h1>
            <SubmissionsSummary />
            <SessionsSummary />
        </div>
    );
};

export default Dashboard;