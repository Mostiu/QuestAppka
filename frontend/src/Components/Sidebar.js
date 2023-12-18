import React from 'react';
import { slide as Menu } from 'react-burger-menu';
import '../Styles/Sidebar.css';
import {Link, useNavigate} from "react-router-dom";

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

    const navigate = useNavigate();
    const handleLogout = () => {
        localStorage.setItem('jwtToken', '');
        localStorage.setItem('mail', '')
        navigate('/' , { replace: true })
        console.log('Logging out...');
    };

    if(localStorage.getItem('jwtToken') === null || localStorage.getItem('jwtToken') === '') return (
        <Menu width={'20%'} styles={menuStyles}>
            <a className="menu-item bm-first-item" href="/">
                <span className="menu-text">Login/Register</span>
            </a>
        </Menu>
    );
    else
    return (
        <Menu width={'20%'} styles={menuStyles}>
            <a className="menu-item bm-first-item" href="/user">
                <span className="menu-text">User</span>
            </a>
            <a className="menu-item" href="/home">
                Home
            </a>

            <a className="menu-item" href="/CourseGenerator">
                Course Generator
            </a>
            <a className="menu-item" href="/CityChallengeBrowse">
                City Challenge Browser
            </a>
            <button className="menu-item" onClick={handleLogout}>
                Logout
            </button>
        </Menu>
    );
};

export default Sidebar;
