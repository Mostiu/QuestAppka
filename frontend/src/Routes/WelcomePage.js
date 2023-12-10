// WelcomePage.js
import React from 'react';
import LoginForm from '../Components/AuthenticationForm';
import "../Styles/WelcomePage.css";

class WelcomePage extends React.Component {
    render() {
        return (
            <div>
                <h1 className={"WelcomeTitle"}>Questappka</h1>
                <p className={"WelcomeParagraph"}> Your road to become the developer</p>

                {/* Render the login form */}
                <LoginForm />
            </div>
        );
    }
}

export default WelcomePage;
