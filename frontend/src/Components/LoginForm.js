import React, { useState, useEffect } from 'react';
import axios from "axios";
import "../Styles/LoginForm.css";
import "../Styles/WelcomePage.css";

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = () => {
        console.log('Logging in with:', username, password);

        axios.post('http://localhost:8080/api/auth/register', {
            firstname: "kermit",
            lastname: "frog",
            email: username,
            password: password,
        })
            .then(response => {
                // Assuming the JWT token is in response.data.token, replace it with the actual property name
                const jwtToken = response.data.token;

                // Store the token in localStorage
                localStorage.setItem('jwtToken', jwtToken);

                // Handle success, you might want to redirect the user or show a success message
                console.log('User registered successfully:', response.data);
            })
            .catch(error => {
                // Handle error, you might want to show an error message to the user
                console.error('Error registering user:', error);
            });
    };


    // // Make a POST request to the API
    // axios.post('http://localhost:8080/api/users', {
    //     username: username,
    //     password: password
    // })
    //     .then(response => {
    //         // Handle the response as needed
    //         console.log('API Response:', response.data);
    //     })
    //     .catch(error => {
    //         // Handle errors
    //         console.error('Error making API request:', error);
    //     });

    return (
        <div>
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
                <button className={"loginFormButton"} type="button" onClick={handleLogin}>
                    Login
                </button>
            </form>
        </div>
    );
};

export default LoginForm;
