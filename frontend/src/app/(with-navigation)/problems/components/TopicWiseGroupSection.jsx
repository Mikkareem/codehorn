'use client'

import useAccordion from "@/components/utils/hooks/use-accordion";

const TopicCard = ({ topic, count }) => {
    return (
        <div className="group flex gap-1 cursor-pointer">
            <p className='text-sm font-semibold group-hover:text-blue-600'>{topic}</p>
            <p className='text-xs font-semibold text-muted-foreground bg-background rounded-2xl py-0.5 px-1'>{count}</p>
        </div>
    )
}

const TopicWiseGroupSection = () => {

    const { isExpanded, toggle } = useAccordion(false);

    const topics = [
        { topic: 'Arrays', count: 689 },
        { topic: 'String', count: 689 },
        { topic: 'Hash Table', count: 689 },
        { topic: 'Dynamic Programming', count: 689 },
        { topic: 'Math', count: 689 },
        { topic: 'Sorting', count: 689 },
        { topic: 'Greedy', count: 689 },
        { topic: 'Depth-First Search', count: 689 },
        { topic: 'Binary Search', count: 689 },
        { topic: 'Database', count: 689 },
        { topic: 'Matrix', count: 689 },
        { topic: 'Tree', count: 689 },
        { topic: 'Breadth-First Search', count: 689 },
        { topic: 'Bit Manipulation', count: 689 },
        { topic: 'Two Pointers', count: 689 },
        { topic: 'Prefix Sum', count: 689 },
        { topic: 'Binary Tree', count: 689 },
        { topic: 'Simulation', count: 689 },
        { topic: 'Stack', count: 689 },
        { topic: 'Graph', count: 689 },
        { topic: 'Counting', count: 689 },
        { topic: 'Sliding Window', count: 689 },
    ]

    return (
        <div className="relative">
            <div className={`flex flex-wrap gap-3 overflow-hidden ${isExpanded ? 'max-h-full': 'max-h-6'}`}>
                {topics.map((topic) => (
                    <TopicCard key={topic.topic} topic={topic.topic} count={topic.count} />
                ))}
            </div>
            <button
                className='absolute bottom-0 right-0 bg-body-background text-muted-foreground'
                onClick={() => toggle()}
            >
                {isExpanded ? "Collapse" : "Expand"}
            </button>
        </div>
    );
};

export default TopicWiseGroupSection;