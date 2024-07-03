import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import AuthNavbar from './AuthNavbar';

const SignUp = () => {
  const [fullName, setFullName] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const navigate = useNavigate();

  const handleSignUp = async () => {
    if (!fullName || !username || !password) {
      setError('Please fill in all fields');
      return;
    }
    try {
      const response = await axios.post('http://localhost:8080/api/auth/register', {
        fullname: fullName,
        username: username,
        password: password,
      });
      console.log(response.data);
      
      navigate('/login');
    } catch (error) {
      if (error.response && error.response.status === 400) {
        setError(error.response.data);
      } else {
        console.error('There was an error registering the user!', error);
      }
    }
  };

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
    setError(''); 
  };

  return (
    <div>
      <AuthNavbar />
      <div style={styles.container}>
        <h1 style={styles.title}>Sign Up</h1>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <input
          type="text"
          placeholder="Full Name"
          value={fullName}
          onChange={(e) => setFullName(e.target.value)}
          style={styles.input}
        />
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={handleUsernameChange}
          style={styles.input}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={styles.input}
        />
        <button onClick={handleSignUp} style={styles.button}>Sign Up</button>
        <button onClick={() => navigate('/login')} style={styles.linkButton}>Already registered? Log in</button>
      </div>
    </div>
  );
};

const styles = {
  container: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    height: 'calc(100vh - 60px)',
    paddingTop: '20px',
    backgroundColor: '#f5f5f5',
  },
  title: {
    color: '#5e057e',
  },
  input: {
    margin: '10px 0',
    padding: '10px',
    width: '200px',
    borderRadius: '5px',
    border: '1px solid #ccc',
  },
  button: {
    margin: '10px 0',
    padding: '10px 20px',
    backgroundColor: '#5e057e',
    color: '#fff',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
  },
  linkButton: {
    margin: '10px 0',
    padding: '10px 20px',
    backgroundColor: 'transparent',
    color: '#5e057e',
    border: 'none',
    cursor: 'pointer',
    textDecoration: 'underline',
  },
};

export default SignUp;
