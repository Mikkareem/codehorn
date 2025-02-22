import React from 'react';

const PlaygroundLayout = ({ children }) => {
    return (
        <div>
            <p>Playground Header</p>
            {children}
        </div>
    );
};

export default PlaygroundLayout;