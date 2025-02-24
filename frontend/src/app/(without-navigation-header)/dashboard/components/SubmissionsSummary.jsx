import React from 'react';

const SummaryCard = (props) => (
    <div className='w-max p-4 rounded-lg shadow-lg flex flex-col bg-background'>
        <p className='text-sm text-muted-foreground'>{props.title}</p>
        <p className='text-lg font-semibold'>{props.value}</p>
    </div>
)

const SubmissionsSummary = () => {
    return (
        <div className='flex flex-wrap gap-4'>
            <SummaryCard title='Solved Problems(Overall)' value='12 / 2350'/>
        </div>
    );
};

export default SubmissionsSummary;