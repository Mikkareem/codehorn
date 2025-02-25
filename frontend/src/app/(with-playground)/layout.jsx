
const PlaygroundLayout = ({ children }) => {
    return (
        <div className='flex flex-col h-screen'>
            <div className='h-20 shrink-0'>
                <p>Playground Header</p>
            </div>
            {children}
        </div>
    );
};

export default PlaygroundLayout;