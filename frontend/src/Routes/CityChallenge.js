import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import axios from 'axios';
import '../Styles/CityChallengeBrowse.css';

const CityChallenge = () => {
    const [cityChallengeData, setCityChallengeData] = useState(null);
    const [searchParams, setSearchParams] = useSearchParams();
    const [isLoading, setIsLoading] = useState(true);
    const [commentData, setCommentData] = useState('');

    const cityChallengeId = searchParams.get('content_id');

    useEffect(() => {
        const fetchData = async () => {
            if (cityChallengeId) {
                const storedToken = localStorage.getItem('jwtToken');
                const mail = localStorage.getItem('mail');

                if (storedToken) {
                    try {
                        const response = await axios.get(
                            `http://localhost:8080/api/cityChallenges/${cityChallengeId}`,
                            {
                                headers: {
                                    Authorization: `Bearer ${storedToken}`,
                                    'Content-Type': 'application/json',
                                },
                            }
                        );

                        setCityChallengeData(response.data);
                    } catch (error) {
                        console.error('Error fetching city challenge:', error);
                    } finally {
                        setIsLoading(false);
                    }
                }

                if(storedToken){
                    try {
                        const response = await axios.get(`http://localhost:8080/api/users/${mail}/cityChallenge/${cityChallengeId}/comment`, {
                            headers: {
                                "Authorization": `Bearer ${storedToken}`,
                                "Content-Type": "application/json",
                            }
                        });

                        setCommentData(response.data);
                    } catch (error) {
                        console.error('Error fetching comments:', error);
                    }
                }
            }
        };

        fetchData().then(() => console.log('Done fetching city challenge data'));
    }, [cityChallengeId]);
    const renderContent = () => {
        if (isLoading) {
            return <p>Loading...</p>;
        }

        if (cityChallengeData) {
            return (
                <div className={'Challenge'}>
                    <h1 className={'challengeTitle'}>{cityChallengeData.title}</h1>
                    <p className={'challengeDescription'}>{cityChallengeData.description}</p>
                    <input type="url" className="fileInput" />
                    <button className={'submitChallenge'}>Submit</button>
                </div>
            );
        }

        return <p>No data available.</p>;
    };

    return <div>{renderContent()}</div>;
};

export default CityChallenge;
