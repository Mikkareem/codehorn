'use client'

import Selector from "@/components/utils/Selector";
import {Input} from "@/components/ui/input";
import {useEffect, useState} from "react";
import {useRouter, useSearchParams} from "next/navigation";

const FilterBox = () => {

    const router = useRouter()
    const searchParams = useSearchParams()

    const [listFilter, setListFilter] = useState(searchParams.get('list') ?? '');
    const [difficultyFilter, setDifficultyFilter] = useState(searchParams.get('difficulty') ?? '');
    const [statusFilter, setStatusFilter] = useState(searchParams.get('status') ?? '');

    const applyFilter = () => {
        let params = ''

        if(listFilter !== '') {
            if(params !== '') {
                params += '&'
            }
            params += `list=${encodeURIComponent(listFilter)}`;
        }
        if(difficultyFilter !== '') {
            if(params !== '') {
                params += '&'
            }
            params += `difficulty=${encodeURIComponent(difficultyFilter)}`;
        }
        if(statusFilter !== '') {
            if(params !== '') {
                params += '&'
            }
            params += `status=${encodeURIComponent(statusFilter)}`;
        }

        if(params !== '') {
            console.log(params);
            router.push(`/problems?${params}`);
        }
    }

    useEffect(() => {
        applyFilter()
    }, [listFilter, difficultyFilter, statusFilter]);

    const listsOptions = [
        'Codehorn Curated Algorithms',
        'Codehorn Curated SQL Questions',
        'Top 100 Liked Questions',
        'Top Interview Questions',
        'Favourites'
    ]

    const difficultyOptions = ['Easy', 'Medium', 'Hard']

    const statusOptions = ['Todo', 'Solved', 'Attempted']

    return (
        <div className='flex gap-3'>
            <Selector
                placeholder='Lists'
                options={listsOptions}
                className='max-w-36'
                value={listFilter}
                onChange={setListFilter}
            />

            <Selector
                placeholder='Difficulty'
                options={difficultyOptions}
                value={difficultyFilter}
                onChange={setDifficultyFilter}
            />

            <Selector
                placeholder='Status'
                options={statusOptions}
                value={statusFilter}
                onChange={setStatusFilter}
            />
        </div>
    )
}

const SearchBar = () => (
    <div className='flex-grow'>
        <Input className='w-full' placeholder='Search Problems' />
    </div>
)

const PickOneButton = () => (
    <div className='flex gap-2 items-center'>
        <div className='w-[24px] aspect-square rounded-3xl bg-green-500'/>
        <p className='text-md text-green-500 font-semibold'>Pick One</p>
    </div>
)

const ProblemsTableFilterSection = () => {
    return (
        <div className='flex gap-2'>
            <FilterBox />
            <SearchBar />
            <PickOneButton />
        </div>
    );
};

export default ProblemsTableFilterSection;