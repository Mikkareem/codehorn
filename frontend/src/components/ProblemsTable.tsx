import { ProblemListItem} from "../types"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "./basic/ui/table"
import { Link } from "react-router"
import {Pencil, StickyNote, Trash2} from "lucide-react";
import {useEffect, useState} from "react";

async function getPagedProblems(page: string, perPage: string) {
  const response = await fetch(`http://localhost:3000/api/problems?page=${page}&perPage=${perPage}`, { cache: 'no-cache' })
  return response.json()
}

function ProblemsTable({ page, perPage } : { page: string, perPage: string }) {

  const [problems, setProblems] = useState<ProblemListItem[]>([])
  const [totalPages, setTotalPages] = useState(0)

  useEffect(() => {
    const fetchData = async () => {
      const response = await getPagedProblems(page, perPage)

      setProblems(response.problems)
      setTotalPages(response.totalPages)
    }

    fetchData()

  }, [page, perPage])


  return (
    <div>
      <Table>
        <TableHeader>
          <TableRow className='hover:bg-transparent'>
            <TableHead>No.</TableHead>
            <TableHead>Problem Title</TableHead>
            <TableHead>Difficulty</TableHead>
            <TableHead>Testcases</TableHead>
            <TableHead>Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {problems.map((problem: ProblemListItem) => (
            <TableRow key={problem.problemNo} className='hover:bg-transparent'>
              <TableCell>{problem.problemNo}</TableCell>
              <TableCell>
                <Link to={`/problems/${problem.problemNo}`} className='hover:text-blue-500'>{problem.problemName}</Link>
              </TableCell>
              <TableCell
                  className={`
                    ${problem.problemDifficulty == 'Easy' ? 'text-green-500' : problem.problemDifficulty == 'Medium' ? 'text-orange-500' : 'text-red-500'} 
                  `}
              >{problem.problemDifficulty}</TableCell>
              <TableCell>
                <Link to={`/problems/crud/${problem.problemNo}/testcases`}>
                  <StickyNote size={20}/>
                </Link>
              </TableCell>
              <TableCell>
                <div className='flex'>
                  <Link to={`/problems/crud?pid=${problem.problemNo}`}>
                    <Pencil size={20}/>
                  </Link>
                  <Trash2 size={20}/>
                </div>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
        <div className="flex gap-2">
          {
            [...Array(totalPages)].map((_, i) => i+1).map((_page) => (
                <Link key={_page} to={`/problems?page=${_page}&perPage=${perPage}`}
                  className={`px-2 cursor-pointer ${_page === +page && 'bg-leetcode-tertiary rounded-md'}`}
                >{_page}</Link>
            ))
          }
        </div>
    </div>
  )
}

export default ProblemsTable