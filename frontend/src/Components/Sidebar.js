import React from 'react';
import { slide as Menu } from 'react-burger-menu';
import '../Styles/Sidebar.css';

const Sidebar = (props) => {
    const menuStyles = {
        bmBurgerButton: {
            position: 'fixed',
            width: '36px',
            height: '30px',
            left: '20px',
            top: '20px',
        },
        bmBurgerBars: {
            background: 'white', // Change this to the desired color (white in this case)
        },
        bmBurgerBarsHover: {
            background: 'white', // Change this to the desired hover color
        },
    };

    return (
        <Menu width={'20%'} styles={menuStyles}>
            <a className="menu-item bm-first-item" href="/user">
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

export default Sidebar;
