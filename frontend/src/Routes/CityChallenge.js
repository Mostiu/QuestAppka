import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import axios from 'axios';
import '../Styles/CityChallengeBrowse.css';

const CityChallenge = () => {
    const [cityChallengeData, setCityChallengeData] = useState([]);
    const [searchParams, setSearchParams] = useSearchParams();
    const [isLoading, setIsLoading] = useState(true);
    const [newComment, setNewComment] = useState('');

    const cityChallengeId = searchParams.get('content_id');

    useEffect(() => {
        const fetchData = async () => {
            if (cityChallengeId) {
                const storedToken = localStorage.getItem('jwtToken');
                const mail = localStorage.getItem('mail');

                if (storedToken) {
                    try {
                        const response = await axios.get(
                            `http://localhost:8080/api/users/${mail}/cityChallenge/${cityChallengeId}`,
                            {
                                headers: {
                                    Authorization: `Bearer ${storedToken}`,
                                    'Content-Type': 'application/json',
                                },
                            }
                        );
                        setCityChallengeData(response.data[0]);
                        setNewComment(cityChallengeData[3] ? cityChallengeData[3] : '');
                    } catch (error) {
                        console.error('Error fetching city challenge:', error);
                    } finally {
                        setIsLoading(false);
                    }
                }

            }
        };

        fetchData().then(() => console.log('Done fetching city challenge data'));
    }, [cityChallengeId]);

    const handleSubmit = async () => {
        const storedToken = localStorage.getItem('jwtToken');
        const mail = localStorage.getItem('mail');

        if (storedToken) {
            try {
                // Make a POST request to submit a new comment
                await axios.post(
                    `http://localhost:8080/api/users/${mail}/cityChallenge/${cityChallengeId}/comment`,
                     newComment ,
                    {
                        headers: {
                            Authorization: `Bearer ${storedToken}`,
                            'Content-Type': 'application/json',
                        },
                    }
                );

            } catch (error) {
                console.error('Error submitting comment:', error);
            }
        }
    };

    const renderContent = () => {
        if (isLoading) {
            return <p>Loading...</p>;
        }

        if (cityChallengeData && cityChallengeData.length > 0) {
            return (
                <div className={'Challenge'}>
                    <h1 className={'challengeTitle'}>{cityChallengeData[1]}</h1>
                    <p className={'challengeDescription'}>{cityChallengeData[2]}</p>
                    <input
                        type="text"
                        value={newComment}
                        onChange={(e) => setNewComment(e.target.value)}
                        className="commentInput"
                        placeholder={cityChallengeData[3] ? cityChallengeData[3] : 'Submit a comment'}
                    />
                    <button className={'submitChallenge'} onClick={handleSubmit}>
                        Submit
                    </button>
                </div>
            );
        }

        return <p>No data available.</p>;
    };

    return <div>{renderContent()}</div>;
};

export default CityChallenge;
