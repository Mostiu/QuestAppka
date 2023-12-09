import React from 'react';
import '../Styles/Home.css';
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import InfiniteScroll from 'react-infinite-scroll-component';
import Card from "../Components/Card";

class HomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cityChallenges: [],
            randomWords: [],
            hasMore: true,
            page: 1
        };
    }

    componentDidMount() {
        this.fetchCityChallenges();
        this.fetchRandomWords();
    }

    fetchCityChallenges = () => {
        // Retrieve the stored token from localStorage
        const storedToken = localStorage.getItem('jwtToken');

        // Check if the token exists
        if (storedToken) {
            // Use a real API call for city challenges
            // Assuming an endpoint like 'http://localhost:8080/api/cityChallenges'
            fetch('http://localhost:8080/api/cityChallenges', {
                headers: {
                    'Authorization': `Bearer ${storedToken}`, // Include the stored token in the Authorization header
                    'Content-Type': 'application/json',
                }
            })
                .then(response => response.json())
                .then(data => {
                    console.log('storage', localStorage.getItem('jwtToken'));
                    this.setState({ cityChallenges: data });
                })
                .catch(error => {
                    console.error('Error fetching city challenges:', error);
                });
        } else {
            console.error('No token found. User may not be authenticated.');
           // this.setState({cityChallenges: []})// Handle the case when no token is found
        }
    };


    fetchRandomWords = () => {
        // Mock API call for random words
        const mockRandomWords = [
            'Apple', 'Banana', 'Carrot', 'Dolphin', 'Elephant',
            'Flower', 'Giraffe', 'Happiness', 'Igloo', 'Jazz',
            'Apple', 'Banana', 'Carrot', 'Dolphin', 'Elephant',
            'Flower', 'Giraffe', 'Happiness', 'Igloo', 'Jazz',
            'Apple', 'Banana', 'Carrot', 'Dolphin', 'Elephant',
            'Flower', 'Giraffe', 'Happiness', 'Igloo', 'Jazz',
            // Add more words as needed
        ];
        this.setState(prevState => ({
            randomWords: [...prevState.randomWords, ...mockRandomWords],
            hasMore: false // In this mock example, set hasMore to false since it's not a dynamic API
        }));
    }

    fetchMoreData = () => {
        // Mock API call for fetching more random words
        this.fetchRandomWords();
        this.setState(prevState => ({
            page: prevState.page + 1
        }));
    }

    render() {
        const { randomWords } = this.state;

        return (
            <div className="Home">
                <div className={"LeftContainer"}>
                    <div className={"CityChallenge"}>
                        <Carousel infiniteLoop={true}
                                  showIndicators={false}
                                  showStatus={false}>
                            {this.state.cityChallenges.map(challenge => (
                                <div key={challenge.id}>
                                    <h2>{challenge.title}</h2>
                                    <p>{challenge.description}</p>
                                </div>
                            ))}
                        </Carousel>
                    </div>


                    <div className={"RightContainer"} style={{ height: '250px', overflowY: 'scroll', paddingLeft: '40px' }}>
                        <InfiniteScroll
                            dataLength={randomWords ? randomWords.length : 0}
                            next={this.fetchMoreData}
                            hasMore={this.state.hasMore}
                            loader={<h4>Loading...</h4>}
                            endMessage={<p>No more words to show.</p>}
                            style={{ padding: '0 20px' }}
                        >
                            {randomWords && randomWords.map((word, index) => (
                                <Card></Card>
                            ))}
                        </InfiniteScroll>
                    </div>
                </div>

                <div className={"RightContainer"} style={{ height: '480px', overflowY: 'scroll' }}>
                    <InfiniteScroll
                        dataLength={randomWords ? randomWords.length : 0}
                        next={this.fetchMoreData}
                        hasMore={this.state.hasMore}
                        loader={<h4>Loading...</h4>}
                        endMessage={<p>No more words to show.</p>}
                        style={{ padding: '0 20px' }}
                    >
                        {randomWords && randomWords.map((word, index) => (
                            <div key={index}>
                                <h1>{word}</h1>
                            </div>
                        ))}
                    </InfiniteScroll>
                </div>
            </div>
        );
    }
}

export default HomePage;
