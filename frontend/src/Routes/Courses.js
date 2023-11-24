import React, { useState, useEffect } from 'react';
import Card from "../Components/Card";
import '../Styles/Courses.css';
import * as Progress from '@radix-ui/react-progress';
import "../Styles/ProgressDemo.css";

const Courses = () => {
    const [progress, setProgress] = useState(15);

    useEffect(() => {
        const timer = setTimeout(() => setProgress(66), 500);
        return () => clearTimeout(timer);
    }, []);

    const renderContent = () => (
        <div className="Course">
            <div className={"LeftContainer"}>
                <Card></Card>
            </div>
            <div className={"RightContainer"}>
                <Card></Card>
                <button> AAA</button>
            </div>
        </div>
    );

    const decreaseProgress = () => {
        setProgress(prevProgress => Math.max(prevProgress - 10, 0));
    };

    const increaseProgress = () => {
        setProgress(prevProgress => Math.min(prevProgress + 10, 100));
    };

    return (
        <div>
            {renderContent()}
            <div className="centered-progress">
                <Progress.Root className="ProgressRoot" value={progress}>
                    <Progress.Indicator
                        className="ProgressIndicator"
                        style={{ transform: `translateX(-${100 - progress}%)` }}
                    />
                </Progress.Root>
            </div>
            <div className="Course">
                <button onClick={decreaseProgress}> AAA</button>
                <button onClick={increaseProgress}> BBB</button>
            </div>
        </div>
    );
};

export default Courses;
