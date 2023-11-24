import React from 'react';
import {slide as Menu} from 'react-burger-menu';
import '../Styles/Sidebar.css';

export default props => {
    return (
        <Menu width={'20%'}>
            <a className="menu-item, bm-first-item" href="/user">
                <span className="menu-text">User</span>
            </a>
            <a className="menu-item" href="/home">
                Home
            </a>
            <a className="menu-item" href="/courses">
                Courses
            </a>
            <a className="menu-item" href="/CityChallengeBrowse">
                City Challenge Browse
            </a>
            <a className="menu-item" href="/CourseGenerator">
                Course Generator
            </a>
        </Menu>
    );
};
