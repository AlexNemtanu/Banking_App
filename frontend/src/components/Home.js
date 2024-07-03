import React, {useEffect, useState} from 'react';

const Home = () => {
  const [username, setUsername] = useState('');
  useEffect(() => {
    const storedUsername = localStorage.getItem('username');
    if (storedUsername) {
      setUsername(storedUsername);
    }
  }, []);
  return (
    <div style={styles.container}>
      <h2>Welcome, {username}!</h2>
      <p>Text </p>
    </div>
  );
};

const styles = {
  container: {
    padding: '20px',
    backgroundColor: '#f5f5f5',
    borderRadius: '5px',
    marginTop: '20px',
    
  },
};

export default Home;
