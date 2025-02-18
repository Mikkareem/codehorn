import React from 'react'

const HiddenInput = (props: React.HTMLProps<HTMLInputElement>) => {
  return (
    <input {...props} type='hidden'/>
  )
}

export default HiddenInput