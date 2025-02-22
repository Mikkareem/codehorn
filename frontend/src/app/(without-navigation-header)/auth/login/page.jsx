"use client"

import {useActionState} from "react";
import {loginAction} from "@/actions/AuthActions";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";


const LoginForm = () => {
    const [error, action, isPending] = useActionState(loginAction, null);

    return (
        <div className="flex justify-center items-center w-full h-screen">
            <form autoComplete='off' action={action} className='flex flex-col gap-4 items-center w-[300px] p-4'>
                <h1 className='text-4xl'>Codehorn</h1>
                <h3 className='text-3xl'>Login</h3>
                {error && (<p className='text-destructive'>{error}</p>)}

                <Input name="username" type="text" placeholder="Username"/>
                <Input name="password" type="password" placeholder="Password"/>

                <Button disabled={isPending} type="submit">
                    {isPending ? 'Verifying Credentials...' : 'Login'}
                </Button>
            </form>
        </div>
    )
}

const Login = () => {

    return (
        <div className="mx-auto min-h-screen bg-amber-400">
            <LoginForm />
        </div>
    );
};

export default Login;