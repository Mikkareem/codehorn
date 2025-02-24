import React from 'react';

const CategoryChip = ({ category }) => (
    <div className='flex gap-2 p-3 rounded-3xl bg-background flex-shrink-0'>
        <div className='w-[24px] aspect-square bg-amber-400 rounded-3xl' />
        <p className='text-md font-semibold'>{category}</p>
    </div>
)

const CategoryChipsSection = () => {

    const categories = ['All Topics', 'Algorithms', 'Database', 'Shell', 'Concurrency', 'Javascript'];

    return (
        <div className='flex gap-3 items-center overflow-hidden'>
            {categories.map(category => (
                <CategoryChip key={category} category={category} />
            ))}
        </div>
    );
};

export default CategoryChipsSection;