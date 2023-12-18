import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom'; // Import Link
import '../Styles/AuthenticationForm.css';
import '../Styles/WelcomePage.css';

const AuthenticationForm = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const navigate = useNavigate();

    const handleAuthentication = () => {
        console.log('Performing authentication:', username, password, firstName, lastName);

        const endpoint = isLogin
            ? 'http://localhost:8080/api/auth/authenticate'
            : 'http://localhost:8080/api/auth/register';

        const requestData = isLogin
            ? { email: username, password: password }
            : { email: username, password: password, firstName: firstName, lastName: lastName };

        axios
            .post(endpoint, requestData)
            .then(response => {
                const jwtToken = response.data.token;
                localStorage.setItem('jwtToken', jwtToken);
                localStorage.setItem('mail', username);
                console.log(requestData)
                console.log('User authenticated successfully:', response.data);
                navigate('/home', { replace: true });
            })
            .catch(error => {
                console.error('Authentication error:', error);
            });
    };

    return (
        <div>
            <form>
                {!isLogin && (
                    <>
                        <label>
                            First Name:
                            <input type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                        </label>
                        <br />
                        <label>
                            Last Name:
                            <input type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                        </label>
                        <br />
                    </>
                )}
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
