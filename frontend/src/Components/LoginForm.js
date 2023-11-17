import React, { useState } from 'react';
import axios from 'axios';

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = () => {
        // Add your login logic and authentication here
        console.log('Logging in with:', username, password);

        // Example: Make a POST request to create a new user
        axios.post('http://localhost:8080/api/users', {
            name:   "kermit",
            username: username,
            password: password,
        })
            .then(response => {
                // Handle success, you might want to redirect the user or show a success message
                console.log('User registered successfully:', response.data);
            })
            .catch(error => {
                // Handle error, you might want to show an error message to the user
                console.error('Error registering user:', error);
            });
    };

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
