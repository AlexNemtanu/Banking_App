
import React from 'react';
import Navbar from './AuthNavbar';

const AboutUs = () => {
  return (
    <div>
      <Navbar showAuthButtons />
      <div style={styles.container}>
        <h1 style={styles.title}>About Us</h1>
        <p style={styles.text}>Work in progress</p>
      </div>
    </div>
  );
};

const styles = {
  container: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'flex-start',
    height: 'calc(100vh - 60px)',
    paddingTop: '20px',
    backgroundColor: '#f5f5f5',
  },
  title: {
    color: '#5e057e',
  },
  text: {
    margin: '20px',
    color: '#333',
  },
};

export default AboutUs;
