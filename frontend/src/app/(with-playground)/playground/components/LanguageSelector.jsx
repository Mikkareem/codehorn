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
                    <SelectItem value='C'>C</SelectItem>
                    <SelectItem value='Cpp'>Cpp</SelectItem>
                    <SelectItem value='Java'>Java</SelectItem>
                    <SelectItem value='Python'>Python</SelectItem>
                    <SelectItem value='Javascript'>Javascript</SelectItem>
                </SelectContent>
            </Select>
        </div>
    )
}

export default LanguageSelector