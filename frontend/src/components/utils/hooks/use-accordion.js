'use client'

import {useState} from "react";

const useAccordion = (initialState) => {
    const [isExpanded, setIsExpanded] = useState(initialState)

    const collapse = () => setIsExpanded(false)
    const expand = () => setIsExpanded(true)
    const toggle = () => {
        if(isExpanded) {
            collapse()
        } else {
            expand()
        }
    }

    return { isExpanded, collapse, expand, toggle }
}

export default useAccordion;