import React from 'react';
import '../Styles/Home.css';
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import axios from "axios";
import InfiniteScroll from 'react-infinite-scroll-component';

// requires a loader
class HomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cityChallenges: []
        }
    }


    fetchRandomWords = () => {
        // Mock API call for random words
        const mockRandomWords = [
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

    componentDidMount() {
        axios.get('http://localhost:8080/api/cityChallenges')
            .then(response => {
                this.setState({ cityChallenges: response.data })
            })
            .catch((error) => {
                console.log(error);
            })
    }

    render() {
        console.log("City Challenges:", this.state.cityChallenges[0]);

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

                    <h1 className={"CoursesStartedH1"}>Home Page</h1>

                </div>

               <div className={"RightContainer"}>
                   <InfiniteScroll
                       dataLength={this.state.randomWords.length}
                       next={this.fetchMoreData}
                       hasMore={this.state.hasMore}
                       loader={<h4>Loading...</h4>}
                       endMessage={<p>No more words to show.</p>}
                   >
                       {this.state.randomWords.map((word, index) => (
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
