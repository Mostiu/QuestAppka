import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import HomePage from './Routes/HomePage';
import User from './Routes/User';
import Courses from './Routes/Courses';
import CityChallenge from './Routes/CityChallenge';
import CityChallengeBrowse from './Routes/CityChallengeBrowse';
import CourseGenerator from './Routes/CourseGenerator';
import CourseGenerated from './Routes/CourseGenerated';
import WelcomePage from './Routes/WelcomePage';

class App extends React.Component {
    render() {
        return (
            <Router>
                <div>


                    <Routes>
                        <Route path="/" element={<WelcomePage />} />
                        <Route path="/home" element={<HomePage />} />
                        <Route path="/user" element={<User />} />
                        <Route path="/courses" element={<Courses />} />
                        <Route path="/cityChallenge" element={<CityChallenge />} />
                        <Route path="/cityChallengeBrowse" element={<CityChallengeBrowse />} />
                        <Route path="/courseGenerator" element={<CourseGenerator />} />
                        <Route path="/courseGenerated" element={<CourseGenerated />} />
                    </Routes>


                    <h1>
                        <Link to="/">Click me for WelcomePage</Link>
                    </h1>
                    <h1>
                        <Link to="/home">Click me for Home Page</Link>
                    </h1>
                    <h1>
                        <Link to="/user">Click me for User Page</Link>
                    </h1>
                    <h1>
                        <Link to="/courses">Click me for Courses Page</Link>
                    </h1>
                    <h1>
                        <Link to="/cityChallenge">Click me for City Challenge Page</Link>
                    </h1>
                    <h1>
                        <Link to="/cityChallengeBrowse">Click me for City Challenge Browse Page</Link>
                    </h1>
                    <h1>
                        <Link to="/courseGenerator">Click me for Course Generator Page</Link>
                    </h1>
                </div>
            </Router>
        );
    }
}

export default App;
