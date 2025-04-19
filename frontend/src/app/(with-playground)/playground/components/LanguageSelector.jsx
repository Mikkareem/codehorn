'use client'

import { usePlaygroundContext } from '@/contexts/playground'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'

const LanguageSelector = ({ className }) => {
    const { state: { language }, dispatch } = usePlaygroundContext()

    return (
        <div className={className}>
            <Select value={language} onValueChange={value => dispatch({ type: 'changeLanguage', payload: value })}>
                <SelectTrigger className='outline-none border-none focus:outline-none focus:shadow-none focus:ring-0 focus:ring-offset-0 h-6'>
                    <SelectValue placeholder='Select Language' />
                </SelectTrigger>
                <SelectContent>
                    <SelectItem value='c'>C</SelectItem>
                    <SelectItem value='cpp'>Cpp</SelectItem>
                    <SelectItem value='java'>Java</SelectItem>
                    <SelectItem value='python'>Python</SelectItem>
                    <SelectItem value='javascript'>Javascript</SelectItem>
                </SelectContent>
            </Select>
        </div>
    )
}

export default LanguageSelector