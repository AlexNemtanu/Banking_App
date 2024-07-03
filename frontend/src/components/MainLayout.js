import React, {useEffect, useState} from 'react';
import HomeNavbar from './HomeNavbar';
import MenuList from './MenuList';
import { Outlet } from 'react-router-dom';

const MainLayout = () => {
  const [username, setUsername] = useState('');
  useEffect(() => {
    const storedUsername = localStorage.getItem('username');
    if (storedUsername) {
      setUsername(storedUsername);
    }
  }, []);
  return (
    <div>
      <HomeNavbar username={username}/>
      <div style={styles.mainContainer}>
        <div style={styles.leftContainer}>
          <div className="card-container">
            <MenuList />
          </div>
        </div>
        <div style={styles.rightContainer}>
          <Outlet />
        </div>
      </div>
    </div>
  );
};

const styles = {
  mainContainer: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'flex-start',
    height: 'calc(100vh - 60px)',
    padding: '20px',
  },
  leftContainer: {
    flex: '0 0 30%',
  },
  rightContainer: {
    flex: '1 1 auto',
    marginLeft: '20px',
  },
};

export default MainLayout;
