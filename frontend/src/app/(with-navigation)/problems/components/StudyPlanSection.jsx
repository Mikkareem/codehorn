import React from 'react';
import Link from 'next/link';

const StudyPlanCard = ({ plan }) => {

    return (
        <div className="rounded-lg shadow-xl p-4 flex items-center gap-2 bg-background">
            <div className="w-[30%] aspect-square bg-pink-500 flex-shrink-0"></div>
            <div className="flex flex-col justify-center gap-2 flex-1 min-w-0">
                <h3 className="text-lg line-clamp-2">{plan.title}</h3>
                <p className="text-xs text-muted-foreground truncate">
                    {plan.description}
                </p>
            </div>
        </div>
    )
}

const StudyPlanSection = () => {

    const cards = [
        { title: 'Top Interview 150', description: 'Must-do List for interview prep' },
        { title: 'Codehorn 75', description: 'Ace coding interviews with 75 Questions' },
        { title: 'SQL 50', description: 'Crack SQL interviews with 50 Questions' },
        { title: 'Introduction to Pandas', description: 'Learn basic pandas in 15 Questions' },
        { title: '30 days of Javascript', description: 'Learn Javascript basics with 30 Questions' },
        { title: 'Amazon Spring \'23 High frequency', description: 'Practice Amazon 25 Recently Asked Questions' },
    ]

    return (
        <div className='flex flex-col gap-2'>
            <div className='flex justify-between items-center'>
                <h2>Study Plan</h2>
                <Link href=''>See All</Link>
            </div>

            <div className='grid grid-cols-3 gap-2'>
                {cards.map((plan) => (
                    <StudyPlanCard key={plan.title} plan={plan} />
                ))}
            </div>
        </div>
    );
};

export default StudyPlanSection;