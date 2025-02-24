'use client'

import { usePathname } from 'next/navigation'
import Link from "next/link";

const navLinks = [
    { name: "Problems", href: "/problems" },
    { name: "Discuss", href: "/discuss" },
    { name: "Contests", href: "/contests" },
]

const NavigationLayout = ({ children }) => {
    const pathname = usePathname()

    return (
        <div>
            <div className='h-16 flex items-center bg-muted'>
                <div className='max-w-[90%] mx-auto w-full'>
                    {navLinks.map(link => {
                        const isActive = pathname.startsWith(link.href)
                        return <Link
                            key={link.name}
                            href={link.href}
                            className={`mr-3 ${isActive ? 'font-bold': 'text-muted-foreground'}`}
                        >
                            {link.name}
                        </Link>
                    })}
                </div>
            </div>
            <div className='max-w-[90%] mx-auto'>
                {children}
            </div>
        </div>
    );
};

export default NavigationLayout;