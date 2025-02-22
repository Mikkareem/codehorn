import SubmissionsSummary from "@/app/(without-navigation-header)/dashboard/components/SubmissionsSummary";

const Dashboard = async () => {
    return (
        <div className='flex flex-col'>
            <h1 className='text-4xl'>Dashboard</h1>

            <SubmissionsSummary />
        </div>
    );
};

export default Dashboard;