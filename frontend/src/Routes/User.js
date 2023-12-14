import React from 'react';
import '../Styles/Home.css';
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import InfiniteScroll from 'react-infinite-scroll-component';
import Card from "../Components/Card";
import ImageCard from "../Components/ImageCard";

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
        this.fetchUserCityChallenges();
        this.fetchUserInfo();
        this.fetchUserCourses();
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
            const response = await fetch(`http://localhost:8080/api/cityChallenges/${id}/tags`, {
                headers: {
                    'Authorization': `Bearer ${storedToken}`,
                    'Content-Type': 'application/json',
                },
            });

            const data = await response.json();
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
                const response = await fetch(`http://localhost:8080/api/users/${mail}/cityChallenges`, {
                    headers: {
                        'Authorization': `Bearer ${storedToken}`,
                        'Content-Type': 'application/json',
                    },
                });

                const data = await response.json();

                // Fetch tags for each city challenge
                const tagsPromises = data.map(async (cityChallenge) => {
                    const tags = await this.getCityChallengeTags(cityChallenge.id);
                    return { ...cityChallenge, tags }; // Combine city challenge data with tags
                });

                const cityChallengesWithData = await Promise.all(tagsPromises);

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
            const response = await fetch(`http://localhost:8080/api/courses/${id}/tags`, {
                headers: {
                    'Authorization': `Bearer ${storedToken}`,
                    'Content-Type': 'application/json',
                },
            });

            const data = await response.json();
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
                const response = await fetch(`http://localhost:8080/api/users/${mail}/courses`, {
                    headers: {
                        'Authorization': `Bearer ${storedToken}`,
                        'Content-Type': 'application/json',
                    },
                });

                const data = await response.json();

                // Fetch tags for each user course
                const tagsPromises = data.map(async (userCourse) => {
                    const tags = await this.getCourseTags(userCourse.id);
                    return { ...userCourse, tags }; // Combine user course data with tags
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
                            loader={<h4>Loading...</h4>}
                            endMessage={<p>No more challanges to show.</p>}
                            style={{ padding: '0 20px' }}
                        >
                            {this.state.cityChallenges && this.state.cityChallenges.map((cityChallenge, index) => (
                                <div key={index}>
                                    <Card
                                        title={cityChallenge.title}
                                        description={cityChallenge.description}
                                        tags={cityChallenge.tags}
                                        contentId={cityChallenge.id}
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
                                        title={userCourse.title}
                                        description={userCourse.description}
                                        tags={userCourse.tags}
                                        contentId={userCourse.id}
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
