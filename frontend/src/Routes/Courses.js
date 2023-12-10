import React, { useState, useEffect } from 'react';
import Card from "../Components/Card";
import '../Styles/Courses.css';
import * as Progress from '@radix-ui/react-progress';
import "../Styles/ProgressDemo.css";
import CourseCard from "../Components/CourseCard";
import SubmitCard from "../Components/SubmitCard";

const Courses = () => {
    const [progress, setProgress] = useState(15);

    useEffect(() => {
        const timer = setTimeout(() => setProgress(66), 500);
        return () => clearTimeout(timer);
    }, []);

    const renderContent = () => (
        <div className="Course">
            <div className={"LeftContainer"}>
                <CourseCard></CourseCard>
            </div>
            <div className={"RightContainer"}>
                <SubmitCard></SubmitCard>

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

            <h1 style={{ marginTop: '2em' }}>Progress</h1>
                <div className="centered-progress">

                    <Progress.Root className="ProgressRoot" value={progress}>
                        <Progress.Indicator
                            className="ProgressIndicator"
                            style={{ transform: `translateX(-${100 - progress}%)` }}
                        />
                    </Progress.Root>

                </div>


        </div>
    );
};

export default Courses;
