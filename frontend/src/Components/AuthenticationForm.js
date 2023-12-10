import React, { useState } from 'react';
import axios from 'axios';
import '../Styles/AuthenticationForm.css';
import '../Styles/WelcomePage.css';

const AuthenticationForm = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleAuthentication = () => {
        console.log('Performing authentication:', username, password);

        const endpoint = isLogin
            ? 'http://localhost:8080/api/auth/authenticate'
            : 'http://localhost:8080/api/auth/register';

        axios
            .post(endpoint, {
                email: username,
                password: password,
            })
            .then(response => {

                const jwtToken = response.data.token;


                localStorage.setItem('jwtToken', jwtToken);
                localStorage.setItem('mail', username);


                console.log('User authenticated successfully:', response.data);
            })
            .catch(error => {
                console.error('Authentication error:', error);
            });
    };

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
                <p className={'signInText'}>
                    {isLogin ? "Don't have an account? " : 'Already have an account? '}
                    <span className={"signText"} onClick={() => setIsLogin(!isLogin)}>{isLogin ? 'Sign up' : 'Sign in'}</span>
                </p>
                <button className={'loginFormButton'} type="button" onClick={handleAuthentication}>
                    {isLogin ? 'Login' : 'Register'}
                </button>
            </form>
        </div>
    );
};

export default AuthenticationForm;
