import React from 'react';
import '../Styles/Home.css';
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";// requires a loader
class HomePage extends React.Component {
    render() {
        return (
            <div className="Home">
                <div className={"LeftContainer"}>
                    <div className={"CityChallenge"}>
                        <Carousel infiniteLoop={true}
                            showIndicators={false}
                            showStatus={false}>
                            <div>
                                <h2>City Challenge</h2>
                                <p>City Challenge is a game where you have to find different locations in a city. You can play it alone or with friends. The goal is to find all the locations as fast as possible. You can also create your own City Challenge.</p>
                            </div>
                            <div>
                                <h2>City Challenge2</h2>
                                <p>City Challenge2 is a game where you have to find different locations in a city. You can play it alone or with friends. The goal is to find all the locations as fast as possible. You can also create your own City Challenge.</p>
                            </div>
                            <div>
                                <h2>City Challenge3</h2>
                                <p>City Challenge3 is a game where you have to find different locations in a city. You can play it alone or with friends. The goal is to find all the locations as fast as possible. You can also create your own City Challenge.</p>
                            </div>

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
