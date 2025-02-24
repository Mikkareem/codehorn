import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import Link from "next/link";
import {StickyNote, Pencil, Trash2, CircleCheckBig, Contrast} from 'lucide-react'

const allProblems = [
    { difficulty: "Easy", problemNo: 1, title: 'Add Two numbers', status: 'Todo' },
    { difficulty: "Easy", problemNo: 2, title: 'Add Two numbers', status: 'Attempted'},
    { difficulty: "Easy", problemNo: 3, title: 'Add Two numbers', status: 'Solved' },
    { difficulty: "Easy", problemNo: 4, title: 'Add Two numbers', status: 'Todo' },
    { difficulty: "Easy", problemNo: 5, title: 'Add Two numbers', status: 'Todo' },
    { difficulty: "Medium", problemNo: 6, title: 'Add Two numbers', status: 'Todo' },
    { difficulty: "Easy", problemNo: 7, title: 'Add Two numbers', status: 'Todo' },
    { difficulty: "Easy", problemNo: 8, title: 'Add Two numbers', status: 'Solved' },
    { difficulty: "Easy", problemNo: 9, title: 'Add Two numbers', status: 'Todo' },
    { difficulty: "Hard", problemNo: 10, title: 'Add Two numbers', status: 'Todo' },
    { difficulty: "Easy", problemNo: 11, title: 'Add Two numbers', status: 'Attempted' },
    { difficulty: "Easy", problemNo: 12, title: 'Add Two numbers', status: 'Todo' },
    { difficulty: "Easy", problemNo: 13, title: 'Add Two numbers', status: 'Todo' },
    { difficulty: "Easy", problemNo: 14, title: 'Add Two numbers', status: 'Todo' },
]

const ProblemsSetTable = async ({ filters }) => {

    await new Promise(resolve => setTimeout(resolve, 4000))

    const problems = allProblems
        .filter(_ => {
            if(filters.listFilter !== '') {

            }
            return true
        })
        .filter(problem => {
            if(filters.difficultyFilter !== '') {
                return problem.difficulty === filters.difficultyFilter;
            }
            return true
        })
        .filter(problem => {
            if(filters.statusFilter !== '') {
                return problem.status === filters.statusFilter;
            }
            return true
        })

    return (
        <div>
            <Table>
                <TableHeader>
                    <TableRow className='hover:bg-transparent'>
                        <TableHead>Status</TableHead>
                        <TableHead>Problem Title</TableHead>
                        <TableHead>Difficulty</TableHead>
                        <TableHead>Testcases</TableHead>
                        <TableHead>Actions</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {problems.map(problem => (
                        <TableRow key={problem.problemNo} className='hover:bg-transparent'>
                            <TableCell>
                                {
                                    problem.status === 'Solved' ? <CircleCheckBig className='text-green-400'/>
                                        : problem.status==='Attempted' ? <Contrast className='text-amber-400'/>
                                            : null
                                }
                            </TableCell>
                            <TableCell>
                                <Link href={`/problems/${problem.problemNo}`} className='hover:text-blue-500'>{`${problem.problemNo}. ${problem.title}`}</Link>
                            </TableCell>
                            <TableCell
                                className={`${problem.difficulty === 'Easy' ? 'text-green-500' : problem.difficulty === 'Medium' ? 'text-orange-500' : 'text-red-500'}`}
                            >{problem.difficulty}</TableCell>
                            <TableCell>
                                <Link href={`/problems/crud/${problem.problemNo}/testcases`}>
                                    <StickyNote size={20}/>
                                </Link>
                            </TableCell>
                            <TableCell>
                                <div className='flex'>
                                    <Link href={`/problems/crud?pid=${problem.problemNo}`}>
                                        <Pencil size={20}/>
                                    </Link>
                                    <Trash2 size={20}/>
                                </div>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </div>
    );
};

export default ProblemsSetTable;