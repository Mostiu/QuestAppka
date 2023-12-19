import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify'; // Import ToastContainer
import 'react-toastify/dist/ReactToastify.css';
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
                console.log(requestData);
                console.log('User authenticated successfully:', response.data);
                navigate('/home', { replace: true });
                toast.success('Authentication successful!');
            })
            .catch(error => {
                console.error('Authentication error:', error);
                if(isLogin) toast.error(`Authentication failed. Please check your credentials. ${error.response.data.message}`);
                else toast.error(`Registration failed. Please check your credentials. ${error.response.data.message}`);

            });
    };

    return (
        <div>
            {/* Add ToastContainer at the top level of your component */}
            <ToastContainer />
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
                    Email:
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
                    <span className={'signText'} onClick={() => setIsLogin(!isLogin)}>
                        {isLogin ? 'Sign up' : 'Sign in'}
                    </span>
                </p>
                <button className={'loginFormButton'} type="button" onClick={handleAuthentication}>
                    {isLogin ? 'Login' : 'Register'}
                </button>
            </form>
        </div>
    );
};

export default AuthenticationForm;
