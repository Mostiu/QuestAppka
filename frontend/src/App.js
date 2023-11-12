// App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import HomePage from './Routes/HomePage';
import User from './Routes/User';
import Courses from './Routes/Courses';
import CityChallenge from './Routes/CityChallenge';
import CityChallengeBrowse from './Routes/CityChallengeBrowse';
import CourseGenerator from './Routes/CourseGenerator';
import CourseGenerated from './Routes/CourseGenerated';
import WelcomePage from './Routes/WelcomePage'; // Import WelcomePage component

import { ProSidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';
import 'react-pro-sidebar/dist/css/styles.css';
import './Styles/App.css'; // Import your custom CSS file for styling

class App extends React.Component {
    render() {
        const routes = [
            { path: "/", name: "WelcomePage", element: <WelcomePage /> },
            { path: "/home", name: "HomePage", element: <HomePage /> },
            { path: "/user", name: "User", element: <User /> },
            { path: "/courses", name: "Courses", element: <Courses /> },
            { path: "/cityChallenge", name: "CityChallenge", element: <CityChallenge /> },
            { path: "/cityChallengeBrowse", name: "CityChallengeBrowse", element: <CityChallengeBrowse /> },
            { path: "/courseGenerator", name: "CourseGenerator", element: <CourseGenerator /> },
            { path: "/courseGenerated", name: "CourseGenerated", element: <CourseGenerated /> },
        ];

        return (
            <Router>
                <div className="app-container">
                    <ProSidebar className="sidebar">
                        <Menu iconShape="square">
                            {routes.map((route, index) => (
                                <MenuItem key={index}>
                                    <Link to={route.path}>{route.name}</Link>
                                </MenuItem>
                            ))}
                        </Menu>
                    </ProSidebar>

                    <div className="content-container">
                        <Routes>
                            {routes.map((route, index) => (
                                <Route key={index} path={route.path} element={route.element} />
                            ))}
                        </Routes>
                    </div>
                </div>
            </Router>
        );
    }
}

export default App;
