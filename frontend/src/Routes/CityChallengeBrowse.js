import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import axios from 'axios';
import '../Styles/CityChallengeBrowse.css';
import InfiniteScroll from 'react-infinite-scroll-component';
import SubmitTile from "../Components/SubmitTile";

const CityChallengeBrowse = () => {
    const [cityChallengeData, setCityChallengeData] = useState([]);
    const [cityChallengeId, setCityChallengeId] = useState('');
    const [page, setPage] = useState(1); // Start from page 1
    const [options, setOptions] = useState([]);
    const [chosenIndex, setChosenIndex] = useState(null);

    useEffect(() => {
        const fetchOptions = async () => {
            const storedToken = localStorage.getItem('jwtToken');
            const mail = localStorage.getItem('mail');
            if (storedToken) {
                try {
                    const response = await axios.get(`http://localhost:8080/api/cityChallenges`, {
                        headers: {
                            Authorization: `Bearer ${storedToken}`,
                            'Content-Type': 'application/json',
                        },
                    });
                    console.log(response.data);
                    setOptions(response.data);
                } catch (error) {
                    console.error('Error fetching city challenges:', error);
                }
            }
        };

        fetchOptions().then(() => console.log('Done fetching city challenge data'));
    }, [page]);

    const fetchMoreData = async () => {
        setPage(page + 1);
    };

    const handleCityChallengeChange = async (e) => {
        setChosenIndex(e.target.value);
        const selectedCityChallengeId = options[e.target.value][0];
        setCityChallengeId(selectedCityChallengeId);
        setCityChallengeData([]); // Clear previous data
        console.log(cityChallengeId);
        console.log(chosenIndex);
        const storedToken = localStorage.getItem('jwtToken');
        const mail = localStorage.getItem('mail');
        if (storedToken) {
            try {
                const response = await axios.get(
                    `http://localhost:8080/api/cityChallenges/${selectedCityChallengeId}/submitted`,
                    {
                        headers: {
                            Authorization: `Bearer ${storedToken}`,
                            'Content-Type': 'application/json',
                        },
                    }
                );
                console.log(response.data);
                setCityChallengeData(response.data);
            } catch (error) {
                console.error('Error fetching city challenge:', error);
            }
        }
    };

    return (
        <div className="cityChallengeBrowse">
            {cityChallengeId !== '' && (
                <h1 className="cityChallengeTitle">Results for {options[chosenIndex][1]}</h1>
            )}
            <div>
                <select
                    name="selectedCityChallenge"
                    value={chosenIndex}
                    onChange={handleCityChallengeChange}
                    style={{ marginRight: '30px', marginBottom: '30px' }}
                >
                    <option value="" disabled>
                        Select City Challenge
                    </option>
                    {options.map((city, index) => (
                        <option key={city[0]} value={index}>
                            {city[1]}
                        </option>
                    ))}
                </select>
            </div>
            <InfiniteScroll
                next={fetchMoreData}
                hasMore={true}
                dataLength={cityChallengeData.length}
                endMessage="No more to show"
            >
                {cityChallengeData &&
                    cityChallengeData.map((cityChallenge) => (
                        <SubmitTile
                            name={cityChallenge[0]}
                            desc={cityChallenge[1]}
                        />
                    ))}
            </InfiniteScroll>
        </div>
    );
};

export default CityChallengeBrowse;
