// WelcomePage.js
import React from 'react';
import LoginForm from '../Components/LoginForm';

class WelcomePage extends React.Component {
    render() {
        return (
            <div>
                <h1>Welcome to our Website!</h1>
                <p>Explore and enjoy our content.</p>

                {/* Render the login form */}
                <LoginForm />
            </div>
        );
    }
}

export default WelcomePage;
