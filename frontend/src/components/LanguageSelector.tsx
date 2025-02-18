
import { usePlaygroundContext } from '../contexts/playgroundcontext'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from './basic/ui/select'

type LanguageSelectorProps = { className ?: string; }

const LanguageSelector = ({ className }: LanguageSelectorProps) => {
  const { state: { language }, dispatch } = usePlaygroundContext()

  return (
    <div className={className}>
      <Select value={language} onValueChange={value => dispatch({ type: 'changeLanguage', payload: value })}>
        <SelectTrigger className='outline-none border-none'>
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