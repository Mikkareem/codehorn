"use client"

import React, {startTransition, useActionState} from 'react';
import {fetchUserData} from "@/actions/AuthActions";

const ContestsPage = () => {
    const [state, runAction, isPending] = useActionState(
        async (prevState, userId) => await fetchUserData(userId),
        null
    );

    const handleButtonClick = async () => {
        // Trigger the server action with a userId
        startTransition(async () => {
            await runAction(state ? state.userId : 134); // Fetch user with ID 1
        })
    };

    return (
        <div>
            <h1>Fetch User Data with useActionState</h1>

            <button onClick={handleButtonClick} disabled={isPending}>
                {isPending ? 'Fetching...' : 'Fetch User Data'}
            </button>

            {(state && !isPending) && (
                <div>
                    <h2>User Data:</h2>
                    <p>ID: {state.userId}</p>
                    <p>Name: {state.message}</p>
                </div>
            )}
        </div>
    );
};

export default ContestsPage;