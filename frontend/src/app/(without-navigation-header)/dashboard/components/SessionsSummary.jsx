'use client'

import Canvas from "@/components/utils/Canvas";

const SessionsCard = ({ session }) => {

    const draw = (context) => {


        context.translate(context.canvas.width/2, context.canvas.height/2)
        context.rotate(-90 * Math.PI / 180);

        context.lineWidth = 5
        context.beginPath()
        context.arc(0, 0, context.canvas.width/2-10, 0, 2*Math.PI, false)
        context.strokeStyle = "lightgrey"
        context.stroke()

        // Easy
        let startAngle = 0
        let endAngle = (360 * session.percents[0]) * (Math.PI/180)
        context.lineWidth = 5
        context.beginPath()
        context.arc(0, 0, context.canvas.width/2-10, startAngle, endAngle, false)
        context.strokeStyle = "green"
        context.stroke()

        // Medium
        startAngle = endAngle
        endAngle = (360 * session.percents[1] + (startAngle * (180/Math.PI))) * (Math.PI/180)
        context.beginPath()
        context.arc(0, 0, context.canvas.width/2-10, startAngle, endAngle, false)
        context.strokeStyle = "orange"
        context.stroke()

        // Hard
        startAngle = endAngle
        endAngle = (360 * session.percents[2] + (startAngle * (180/Math.PI))) * (Math.PI/180)
        context.beginPath()
        context.arc(0, 0, context.canvas.width/2-10, startAngle, endAngle, false)
        context.strokeStyle = "red"
        context.stroke()
    }

    return <div className='rounded-lg shadow-lg p-4 flex flex-col gap-2 w-max bg-background'>
        <div className='flex justify-between items-center'>
            <p>{session.title}</p>
            <p>Settings</p>
        </div>
        <div className='flex gap-2 items-center'>
            <Canvas draw={draw} width="100" height="100" className='w-[100px] aspect-square' />
            <div className='flex flex-col'>
                <div className='flex gap-2'>
                    <p>Easy</p>
                    <p>112/222</p>
                </div>
                <div className='flex gap-2'>
                    <p>Medium</p>
                    <p>112/222</p>
                </div>
                <div className='flex gap-2'>
                    <p>Hard</p>
                    <p>112/222</p>
                </div>
            </div>
        </div>
    </div>
}

const SessionsSummary = () => {

    const session = {
        title: 'Session',
        percents: [0.34, 0.12, 0.014]
    }

    return (
        <div>
            <SessionsCard session={session} />
        </div>
    );
};

export default SessionsSummary;