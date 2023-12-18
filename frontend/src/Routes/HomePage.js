import React from 'react';
import '../Styles/Home.css';
import {Carousel} from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import InfiniteScroll from 'react-infinite-scroll-component';
import Card from "../Components/Card";
import {Link} from "react-router-dom";
import axios from "axios";

class HomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cityChallenges: [],
            courses: [],
            userCourses: [],
            coursesTags: [],
            hasMore: true,
            searchInput: "",
            page: 1
        };
    }

    componentDidMount() {
        this.fetchCityChallenges();
        this.fetchCourses();
        this.fetchUserCourses();
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

    fetchCityChallenges = async () => {
        const storedToken = localStorage.getItem('jwtToken');

        if (storedToken) {
            try {
                const response = await axios.get('http://localhost:8080/api/cityChallenges', {
                    headers: {
                        'Authorization': `Bearer ${storedToken}`,
                        'Content-Type': 'application/json',
                    },
                });
                console.log('responsecityChallenges', response.data)
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
                    'Authorization': `Bearer ${storedToken }`,
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

    fetchCourses = async () => {
        const storedToken = localStorage.getItem('jwtToken');

        if (storedToken) {
            try {
                const response = await axios.get('http://localhost:8080/api/courses', {
                    headers: {
                        'Authorization': `Bearer ${storedToken}`,
                        'Content-Type': 'application/json',
                    },
                });

                const data = await response.data;

                // Fetch tags for each course
                const tagsPromises = data.map(async (course) => {
                    const tags = await this.getCourseTags(course[0]);
                    return [ ...course, tags ]; // Combine course data with tags
                });

                const coursesWithData = await Promise.all(tagsPromises);
                console.log('coursesWithData', coursesWithData);
                this.setState({
                    courses: coursesWithData,
                });
            } catch (error) {
                console.error('Error fetching courses:', error);
            }
        } else {
            console.error('No token found. User may not be authenticated.');
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
        //  this.fetchCourses();
        this.setState(prevState => ({
            page: prevState.page + 1
        }));
    }


    handleApplyButtonClick = async (courseId) => {
        try {
            const storedToken = localStorage.getItem('jwtToken');
            const mail = localStorage.getItem('mail');
            const response = await axios.post(
                `http://localhost:8080/api/users/${mail}/enroll/${courseId}`,
                { },
                {
                    headers: {
                        'Authorization': `Bearer ${storedToken}`,
                        'Content-Type': 'application/json',
                    },
                }
            );

            // Handle the response as needed
            console.log('Apply for course response:', response.data);
            window.location.reload();
            // Add your custom logic based on the response, e.g., show a success message
        } catch (error) {
            console.error('Error applying for course:', error);
            // Handle errors, e.g., show an error message
        }
    };

    handleSearchInputChange = (event) => {
        this.setState({ searchInput: event.target.value });
    };



    render() {
        const filteredCourses = this.state.courses.filter((course) => {
            const searchInputLowerCase = this.state.searchInput.toLowerCase();
            const courseTitleLowerCase = course[1].toLowerCase();
            const courseDescriptionLowerCase = course[2].toLowerCase();

            // Check if the course is not in userCourses before including it in filteredCourses
            const isNotInUserCourses = !this.state.userCourses.some(
                (userCourse) => userCourse[0] === course[0]
            );

            return (
                (courseTitleLowerCase.includes(searchInputLowerCase) ||
                    courseDescriptionLowerCase.includes(searchInputLowerCase)) &&
                isNotInUserCourses
            );
        });
        return (
            <div className="Home">

                    {/* Left Container */}
                    <div className="LeftContainer" style={{ height: '500px', maxWidth:"525px" }}>
                        <div className="CityChallenge">
                            <Carousel
                                infiniteLoop={true}
                                showIndicators={false}
                                showStatus={false}
                            >
                                {this.state.cityChallenges &&
                                    this.state.cityChallenges.map((cityChallenge, index) => (
                                        <Link
                                            to={`/cityChallenge?content_id=${cityChallenge[0]}`}
                                            className="card-link"
                                            key={index}
                                        >

                                                <h2>{cityChallenge[1]}</h2>
                                                <p>{cityChallenge[2]}</p>
                                                <p>{cityChallenge[3].map((tag) => `#${tag} `)}</p>

                                        </Link>
                                    ))}
                            </Carousel>
                        </div>
                        <h2>My courses</h2>
                            <div className={{}} style={{ height: '500px', overflowY: 'scroll'}}>

                                <InfiniteScroll
                                    dataLength={
                                        this.state.userCourses ? this.state.userCourses.length : 0
                                    }
                                    next={this.fetchMoreData}
                                    hasMore={this.state.hasMore}
                                    loader={<h4>Nothing to show</h4>}
                                    endMessage={<p>No more courses to show.</p>}
                                >
                                    {this.state.userCourses &&
                                        this.state.userCourses.map((userCourse, index) => (
                                            <div key={index} className="column" >
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

                    {/* Right Container */}
                    <div className="RightContainer" style={{ height: '500px', maxWidth:"400px" }}>
                        <h2>Recommended courses</h2>
                        <input style={{ width: '80%', margin: "5px auto 25px auto" }}
                               type="text" placeholder="Search.." name="search"
                               value={this.state.searchInput}
                               onChange={this.handleSearchInputChange}
                        />
                        <div style={{ height: '500px', overflowY: 'scroll' }}>
                        <InfiniteScroll
                            dataLength={filteredCourses.length}
                            next={this.fetchMoreData}
                            hasMore={this.state.hasMore}

                            endMessage={<p>No more courses to show.</p>}
                        >
                            {filteredCourses.map((course, index) => (
                                <div className={"column"} key={index}>
                                    <Card
                                        title={course[1]}
                                        description={course[2]}
                                        tags={course[4]}
                                        contentId={course[0]}
                                        isCourse={true}
                                        applied={false}
                                    />
                                    <button
                                        className="button"
                                        onClick={() => this.handleApplyButtonClick(course[0])}
                                    >
                                        Apply for course
                                    </button>
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
