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
        <>
            <div>
                {navLinks.map(link => {
                    const isActive = pathname.startsWith(link.href)
                    return <Link
                        key={link.name}
                        href={link.href}
                        className={`mr-3 ${isActive ? 'font-bold': 'text-blue-500'}`}
                    >
                        {link.name}
                    </Link>
                })}
            </div>
            {children}
        </>
    );
};

export default NavigationLayout;