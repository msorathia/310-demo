import React, { useState } from 'react';
import axios from "axios";
import "../styles/index.css";

//Two functions- One for signup and one for login
//Called by code "on click" or "on submit" (value of on click or on submit is specified by the function)
//Within functions, create request similar to signUpResponse
//Specify login or signup

//Backend passes success/failure message through the signUpResponse
//According to the signUpResponse, create a function that displays a popup such as the one on likes 52 on github

function LoginSignup() {
  const [SignupsuccessResponse, SignupsetSuccess] = useState(false);
  const [LoginsuccessResponse, LoginsetSuccess] = useState(false);
  const [SignuperrorResponse, SignupsetError] = useState(false);
  const [LoginerrorResponse, LoginsetError] = useState(false);
  const [showLogin, setShowLogin] = useState(true);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [isFormComplete, setIsFormComplete] = useState(false);

  const handleSignUp = async (event) => {
      SignupsetSuccess(false);
      SignupsetError(false);
      LoginsetSuccess(false);
      LoginsetError(false);
      event.preventDefault();
      try {
          const signUpResponse = await axios.post(
              `http://localhost:8080/userController/signup?name=${name}&email=${email}&password=${password}&confirmPassword=${confirmPassword}`
          );
          console.log("value = " + signUpResponse.data);

          if(signUpResponse.data.success == "true")
          {
            SignupsetSuccess(true);
          }
          else
          {
            SignupsetError(true);
          }
      } catch (error) {
          console.error(error);
      }
  };

  const handleLogin = async (event) => {
        SignupsetSuccess(false);
        SignupsetError(false);
        LoginsetSuccess(false);
        LoginsetError(false);
        event.preventDefault();
        try {
            const loginResponse = await axios.post(
                `http://localhost:8080/userController/login?email=${email}&password=${password}`
            );
            console.log("value = " + loginResponse.data.success);
             if(loginResponse.data.success == "true")
             {
                 LoginsetSuccess(true);
             }
             else
            {
                LoginsetError(true);
            }
            }
            catch (error) {
            console.error(error);
        }
    };

  const toggleForm = () => {
    setShowLogin(!showLogin);
  };

  return (
    <div className="container">
      <div className="form-container">
        {showLogin ? (
          <form onSubmit={handleLogin}>
            <h2>Login</h2>
            <label htmlFor="email">Email</label>
            <input
              type="email"
              name="email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              required
            />
            <label htmlFor="password">Password</label>
            <input
              type="password"
              name="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              required
            />
            <button type="submit" onClick={handleLogin}>Login</button>
            <p>Don&apos;t have an account? <button type="button" onClick={toggleForm}>Sign up</button></p>
            <div>
                  {LoginsuccessResponse && <p>Operation successful!</p>}
                  {LoginerrorResponse && <p>Operation failed!</p>}
            </div>
          </form>
        ) : (
          <form onSubmit={handleSignUp}>
            <h2>Sign Up</h2>
            <label htmlFor="name">Name</label>
            <input
              type="text"
              name="name"
              value={name}
              onChange={(event) => setName(event.target.value)}
              required
            />
            <label htmlFor="email">Email</label>
            <input
              type="email"
              name="email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              required
            />
            <label htmlFor="password">Password</label>
            <input
              type="password"
              name="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              required
            />
            <label htmlFor="confirm-password">Confirm Password</label>
            <input
              type="password"
              name="confirm-password"
              value={confirmPassword}
              onChange={(event) => setConfirmPassword(event.target.value)}
              required
            />
            <button type="submit" onSubmit={handleSignUp}>Sign Up</button>
            <p>Already have an account? <button type="button" onClick={toggleForm}>Login</button></p>
          </form>
        )}
      </div>
      {SignupsuccessResponse && <p>Operation successful!</p>}
      {SignuperrorResponse && <p>Operation failed!</p>}
    </div>
  );
}

export default LoginSignup;



