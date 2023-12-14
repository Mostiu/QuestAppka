import React, {useEffect, useState} from 'react';
import '../Styles/CityChallengeBrowse.css';
import {useSearchParams} from "react-router-dom";
import axios from "axios";

const CityChallengeBrowse = () => {


    const [cityChallengeData, setCityChallengeData] = useState(null);
    const [searchParams, setSearchParams] = useSearchParams();
    const [commentData, setCommentData] = useState("");

    const cityChallengeId = searchParams.get('content_id');

    useEffect(() => {
        console.log('Fetching city challenge data...', cityChallengeId);
        const fetchData = async () => {
            if(cityChallengeId) {
                const storedToken = localStorage.getItem('jwtToken');
                const mail = localStorage.getItem('mail');

                if (storedToken) {
                    try {
                        const response = await axios.get(`http://localhost:8080/api/cityChallenges/${cityChallengeId}`, {
                            headers: {
                                'Authorization': `Bearer ${storedToken}`,
                                'Content-Type': 'application/json',
                            },
                        });

                        setCityChallengeData(response.data);
                    } catch (error) {
                        console.error('Error fetching city challenge:', error);
                    }
                }

                if(storedToken){
                    try{
                        const response = await axios.get(`http://localhost:8080/api/users/${mail}/cityChallenge/${cityChallengeId}/comment`, {
                            headers: {
                                "Authorization": `Bearer ${storedToken}`,
                                "Content-Type": "application/json",
                            }
                        }

                        );
                        setCommentData(response.data);
                    }
                    catch (error) {
                        console.error('Error fetching comments:', error);
                }


            }
        };

        fetchData().then(r => console.log('Done fetching city challenge data'));

    }}, [cityChallengeId]);


    return (
        <div className={"Challenge"}>
            <h1 className={"challengeTitle"}>{cityChallengeData.title}</h1>
            {/*<p className={"authorText"}>Created by {cityChallengeData.created_by}</p>*/}
            <p className={"challengeDescription"}>{cityChallengeData.description}</p>
            <input
                type="url"

                className="fileInput"
            />
            <button className={"submitChallenge"}>Submit</button>
        </div>
    );

}

export default CityChallengeBrowse;
