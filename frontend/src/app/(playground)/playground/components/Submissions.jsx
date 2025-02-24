import React from 'react';
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";

const sampleSubmissions = [
    {
        id: 1,
        verdict: 'Accepted',
        language: 'Java',
        runtime: '146ms',
        memory: '60 mb'
    },
    {
        id: 2,
        verdict: 'Accepted',
        language: 'Cpp',
        runtime: '446ms',
        memory: '18 mb'
    },
    {
        id: 3,
        verdict: 'Wrong Answer',
        language: 'Python',
        runtime: '46ms',
        memory: '30 mb'
    },
    {
        id: 4,
        verdict: 'Accepted',
        language: 'C',
        runtime: '46ms',
        memory: '60 mb'
    },
]

const Submissions = () => {
    return (
        <div>
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHead>Verdict</TableHead>
                        <TableHead>Language</TableHead>
                        <TableHead>Runtime</TableHead>
                        <TableHead>Memory</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {sampleSubmissions.map((submission) => (
                        <TableRow key={submission.id}>
                            <TableCell
                                className={submission.verdict === 'Accepted' ? 'text-green-500' : 'text-red-500'}
                            >{submission.verdict}</TableCell>
                            <TableCell>{submission.language}</TableCell>
                            <TableCell>{submission.runtime}</TableCell>
                            <TableCell>{submission.memory}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </div>
    );
};

export default Submissions;