import { QueryClientProvider, QueryClient } from '@tanstack/react-query'
import React from 'react'

export default ({ children }: { children: React.ReactNode }) => {
  const client = new QueryClient()
  return <QueryClientProvider client={client}>{children}</QueryClientProvider>
}