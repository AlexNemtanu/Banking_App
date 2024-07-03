import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import AuthNavbar from './AuthNavbar';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async () => {
    if (!username || !password) {
      setError('Please fill in all fields');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', {
        username: username,
        password: password,
      });
      console.log(response.data);
      localStorage.setItem('username', username);
      navigate('/home');
    } catch (error) {
      console.error('There was an error logging in!', error);
      setError('Invalid username or password');
    }
  };

  return (
    <div>
      <AuthNavbar />
      <div style={styles.container}>
        <h1 style={styles.title}>Login</h1>
        {error && <p style={styles.error}>{error}</p>}
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          style={styles.input}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={styles.input}
        />
        <button onClick={handleLogin} style={styles.button}>Login</button>
        <button onClick={() => navigate('/signup')} style={styles.linkButton}>Don't have an account? Sign Up</button>
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
  error: {
    color: 'red',
    textAlign: 'center',
    marginTop: '10px',  
  },
};

export default Login;
