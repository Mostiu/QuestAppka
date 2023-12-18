import React from 'react';
import '../Styles/Home.css';
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import InfiniteScroll from 'react-infinite-scroll-component';
import Card from "../Components/Card";
import ImageCard from "../Components/ImageCard";
import axios from "axios";

class HomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cityChallenges: [],
            userInfo: {},
            userCourses: [],
            hasMore: true,
            page: 1
        };
    }

    componentDidMount() {
        this.fetchUserCourses();
        this.fetchUserCityChallenges();
        this.fetchUserInfo();

    }

    fetchUserInfo = () => {
        const token = localStorage.getItem('jwtToken');
        const mail = localStorage.getItem('mail');

        fetch(`http://localhost:8080/api/users/${mail}`, {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then(data => {
                this.setState({ userInfo: data });
            })
            .catch(error => {
                console.error('Error fetching user info:', error);
            });
    }


    getCityChallengeTags = async (id) => {
        try {
            const storedToken = localStorage.getItem('jwtToken');
            const response = await axios.get(`http://localhost:8080/api/cityChallenges/${id}/tags`, {
                headers: {
                    'Authorization': `Bearer ${storedToken}`,
                    'Content-Type': 'application/json',
                },
            });
            console.log('responsetags', response.data);
            const data = await response.data;
            return data;
        } catch (error) {
            console.error('Error fetching tags:', error);
            return [];
        }
    };



    fetchUserCityChallenges = async () => {
        const storedToken = localStorage.getItem('jwtToken');
        const mail = localStorage.getItem('mail');
        if (storedToken) {
            try {
                const response = await axios.get(`http://localhost:8080/api/users/${mail}/cityChallenges`, {
                    headers: {
                        'Authorization': `Bearer ${storedToken}`,
                        'Content-Type': 'application/json',
                    },
                });

                const data = await response.data;

                // Fetch tags for each city challenge
                const tagsPromises = data.map(async (cityChallenge) => {
                    const tags = await this.getCityChallengeTags(cityChallenge[0]);
                    return [ ...cityChallenge, tags ]; // Combine city challenge data with tags
                });

                const cityChallengesWithData = await Promise.all(tagsPromises);
                console.log('cityChallengesWithData', cityChallengesWithData);
                this.setState({
                    cityChallenges: cityChallengesWithData,
                });
            } catch (error) {
                console.error('Error fetching city challenges:', error);
            }
        } else {
            console.error('No token found. User may not be authenticated.');
        }
    };

    getCourseTags = async (id) => {
        try {
            const storedToken = localStorage.getItem('jwtToken');
            const response = await axios.get(`http://localhost:8080/api/courses/${id}/tags`, {
                headers: {
                    'Authorization': `Bearer ${storedToken}`,
                    'Content-Type': 'application/json',
                },
            });

            const data = await response.data;
            return data;
        } catch (error) {
            console.error('Error fetching tags:', error);
            return [];
        }
    };


    fetchUserCourses = async () => {
        const storedToken = localStorage.getItem('jwtToken');
        const mail = localStorage.getItem('mail');

        if (storedToken) {
            try {
                const response = await axios.get(`http://localhost:8080/api/users/${mail}/courses`, {
                    headers: {
                        'Authorization': `Bearer ${storedToken}`,
                        'Content-Type': 'application/json',
                    },
                });

                const data = await response.data;

                // Fetch tags for each user course
                const tagsPromises = data.map(async (userCourse) => {
                    const tags = await this.getCourseTags(userCourse[0]);
                    return [ ...userCourse, tags ]; // Combine user course data with tags
                });

                const userCoursesWithData = await Promise.all(tagsPromises);
                console.log('userCoursesWithData', userCoursesWithData);
                this.setState({
                    userCourses: userCoursesWithData,
                });
            } catch (error) {
                console.error('Error fetching courses:', error);
            }
        }
    };

    fetchMoreData = () => {
        this.setState(prevState => ({
            page: prevState.page + 1
        }));
    }

    render() {
        return (
            <div className="Home">
                <div className={"LeftContainer"}>

                    <ImageCard name={this.state.userInfo.name} email={this.state.userInfo.email} />

                    <h1> City challenge</h1>

                    <div className={"RightContainer"} style={{ height: '250px', overflowY: 'scroll', paddingLeft: '40px' }}>
                        <InfiniteScroll
                            dataLength={this.state.cityChallenges ? this.state.cityChallenges.length : 0}
                            next={this.fetchMoreData}
                            hasMore={this.state.hasMore}
                            loader={<h4>Nothing to show yet</h4>}
                            endMessage={<p>No more challanges to show.</p>}
                            style={{ padding: '0 20px' }}
                        >
                            {this.state.cityChallenges && this.state.cityChallenges.map((cityChallenge, index) => (
                                <div key={index}>
                                    <Card
                                        title={cityChallenge[1]}
                                        description={cityChallenge[2]}
                                        tags={cityChallenge[3]}
                                        contentId={cityChallenge[0]}
                                        isCourse={false}
                                    />
                                </div>
                            ))}
                        </InfiniteScroll>
                    </div>
                </div>

                <div className={"RightContainer"} >
                    <h1>Courses in progress</h1>
                    <div style={{ height: '480px', overflowY: 'scroll' }}>
                        <InfiniteScroll
                            dataLength={this.state.userCourses ? this.state.userCourses.length : 0}
                            next={this.fetchMoreData}
                            hasMore={this.state.hasMore}
                            loader={<h4>Loading...</h4>}
                            endMessage={<p>No more courses to show.</p>}
                            style={{ padding: '0 20px' }}
                        >
                            {this.state.userCourses && this.state.userCourses.map((userCourse, index) => (
                                <div key={index}>
                                    <Card
                                        title={userCourse[1]}
                                        description={userCourse[2]}
                                        tags={userCourse[4]}
                                        contentId={userCourse[0]}
                                        isCourse={true}
                                    />
                                </div>
                            ))}
                        </InfiniteScroll>
                    </div>

                </div>
            </div>
        );
    }
}

export default HomePage;
