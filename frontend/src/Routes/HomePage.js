import React from 'react';
import '../Styles/Home.css';
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import axios from "axios";

// requires a loader
class HomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cityChallenges: []
        }
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
                   <h1>Home Page</h1>
                   <h1>Home Page</h1>
                   <h1>Home Page</h1>
                   <h1>Home Page</h1>
               </div>


            </div>
        );
    }
}

export default HomePage;
