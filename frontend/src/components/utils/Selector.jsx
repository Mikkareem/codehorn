import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";

const Selector = (props) => {
    const { className, placeholder, options, value, onChange } = props;

    return (
        <div className={className}>
            <Select value={value} onValueChange={onChange}>
                <SelectTrigger>
                    <SelectValue placeholder={placeholder} />
                </SelectTrigger>
                <SelectContent>
                    {options.map((option) => (
                        <SelectItem key={option} value={option}>{option}</SelectItem>
                    ))}
                </SelectContent>
            </Select>
        </div>
    )
}

export default Selector;