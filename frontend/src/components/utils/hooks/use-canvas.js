"use client"

import {useEffect, useRef} from "react";

export const useCanvas = (draw) => {
    const ref = useRef()

    useEffect(() => {
        const canvas = ref.current
        const context = canvas.getContext('2d')
        draw(context)
    }, [draw])

    return ref
}