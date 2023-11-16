import React from 'react';
import {slide as Menu} from 'react-burger-menu';
import '../Styles/Sidebar.css';

export default props => {
    return (
        <Menu width={'20%'}>
            <a className="menu-item, bm-first-item" href="/user">
                <span className="menu-text">User</span>
            </a>
            <a className="menu-item" href="/">
                User
            </a>
            <a className="menu-item" href="/courses">
                Pizzas
            </a>
            <a className="menu-item" href="/desserts">
                Desserts
            </a>
        </Menu>
    );
};
