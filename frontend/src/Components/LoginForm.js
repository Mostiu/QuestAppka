import React, { useState, useEffect } from 'react';
import axios from "axios";

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = () => {
        // Add your login logic and authentication here
        console.log('Logging in with:', username, password);
    };

    // Make a POST request to the API
    axios.post('http://localhost:8080/api/users', {
        username: username,
        password: password
    })
        .then(response => {
            // Handle the response as needed
            console.log('API Response:', response.data);
        })
        .catch(error => {
            // Handle errors
            console.error('Error making API request:', error);
        });

    return (
        <div>
            <h2>Login Form</h2>
            <form>
                <label>
                    Username:
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
                </label>
                <br />
                <label>
                    Password:
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </label>
                <br />
                <button type="button" onClick={handleLogin}>
                    Login
                </button>
            </form>
        </div>
    );
};

export default LoginForm;
