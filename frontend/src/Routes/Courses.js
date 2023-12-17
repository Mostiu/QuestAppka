// Courses.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Card from '../Components/Card';
import '../Styles/Courses.css';
import * as Progress from '@radix-ui/react-progress';
import '../Styles/ProgressDemo.css';
import CourseCard from '../Components/CourseCard';
import SubmitCard from '../Components/SubmitCard';
import { useSearchParams } from 'react-router-dom';

const Courses = () => {

    const [progress, setProgress] = useState(15);
    const [courseData, setCourseData] = useState(null);
    const [searchParams, setSearchParams] = useSearchParams();
    const [questNumber, setQuestNumber] = useState(0);
    const courseId = searchParams.get('content_id');
    const [progressText, setProgressText] = useState('Loading...');
    const [newComment, setNewComment] = useState('');

    useEffect(() => {
        // Update progress text based on the progress value
        if (progress === 100) {
            setProgressText('Completed!');
        } else {
            setProgressText(`Progress: ${progress}%`);
        }
    }, [progress]);


    useEffect(() => {
        console.log('Fetching course data...', courseId,questNumber);
        const fetchData = async () => {
            if(courseId) {
                const storedToken = localStorage.getItem('jwtToken');
                const mail = localStorage.getItem('mail');

                if (storedToken) {
                    try {
                        const response = await axios.get(`http://localhost:8080/api/users/${mail}/course/${courseId}/quests`, {
                            headers: {
                                'Authorization': `Bearer ${storedToken}`,
                                'Content-Type': 'application/json',
                            },
                        });
                        console.log(response.data);
                        setCourseData(response.data);
                        setNewComment(courseData[questNumber][2])
                    } catch (error) {
                        console.error('Error fetching course:', error);
                    }
                }

            }


        };

        fetchData().then(r => console.log('Done fetching course data'));
        // Simulating progress update after a delay
        const timer = setTimeout(() => setProgress(66), 500);

        // Cleanup timer on component unmount
        return () => clearTimeout(timer);
    }, [courseId]);

    useEffect(() => {
        // Ensure that questNumber is within valid bounds
        if (courseData && questNumber < 0) {
            setQuestNumber(0);
        } else if (courseData && questNumber >= courseData.length) {
            setQuestNumber(courseData.length - 1);
        }
    }, [questNumber, courseData]);

    const renderContent = () => {
        if (courseData && courseData.length > 0) {
            return (
                <div className="Course">
                    <div className={"LeftContainer"}>
                        <CourseCard
                            title={courseData[questNumber][0]}
                            text={courseData[questNumber][1]}
                        />
                    </div>
                    <div className={"RightContainer"}>
                        <SubmitCard
                            title="Submission"
                            questId={courseData[questNumber][3]}
                            courseId={courseId}
                            inputPlaceholder={courseData[questNumber][2] ? courseData[questNumber][2] : "Enter your answer here"}
                        />
                    </div>
                </div>

            );
        } else {
            // Render a loading state or other fallback content
            return <p>Loading...</p>;
        }
    };

    const decreaseProgress = () => {
        setProgress(prevProgress => Math.max(prevProgress - 10, 0));
        setQuestNumber(prevQuestNumber => Math.max(prevQuestNumber - 1, 0));
        //window.location.reload(); // Refresh the page
    };

    const increaseProgress = () => {
        setProgress(prevProgress => Math.min(prevProgress + 10, 100));
        setQuestNumber(prevQuestNumber => Math.min(prevQuestNumber + 1, courseData.length - 1));
        //window.location.reload(); // Refresh the page
    };

    return (
        <div>
            {renderContent()}

            <h1 style={{ marginTop: '2em' }}>{progressText}</h1>
            <div className="centered-progress">
                <Progress.Root className="ProgressRoot" value={progress}>
                    <Progress.Indicator
                        className="ProgressIndicator"
                        style={{ transform: `translateX(-${100 - progress}%)` }}
                    />
                </Progress.Root>
            </div>

            <div className="Course">
                <button onClick={decreaseProgress}> Previous </button>
                <button onClick={increaseProgress}> Next </button>
            </div>
        </div>
    );
};

export default Courses;
