
import PlaygroundPage from "../PlaygroundPage"

const getProblemById = async (id) => {
    // const response = await fetch(`http://localhost:3000/api/problems/${id}`, { cache: 'no-cache' })
    // return await response.json()
    await new Promise(resolve => setTimeout(resolve, 2000))

    return {
        problem: {
            problemNo: id,
            title: 'Add Two Numbers',
            description: 'Add Two Number description',
            difficulty: 'Easy',
            preferredSnippet: 'Preferred Snippet',
            preferredLanguage: 'Java',
            sampleTestcases: [
                {
                    id: 1,
                    isHidden: false,
                    inputs: [
                        {
                            details: {
                                name: 'first',
                                collectionType: 'SINGLE',
                                type: 'NON_STRING',
                                displayOrder: 1
                            },
                            value: '189'
                        },
                        {
                            details: {
                                name: 'second',
                                collectionType: 'SINGLE',
                                type: 'NON_STRING',
                                displayOrder: 2
                            },
                            value: '393'
                        }
                    ]
                }
            ]
        }
    }
}

export default async (props) => {
    const params = await props.params

    const data = await getProblemById(params.problemId)

    return <PlaygroundPage problem={data.problem}/>
}