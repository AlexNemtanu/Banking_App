import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import '../style/Navbar.css';

const HomeNavbar = ({ username }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const [selectedButton, setSelectedButton] = useState('');

  useEffect(() => {
   
    const currentPath = location.pathname.substring(1);
    
    setSelectedButton(currentPath || 'home');
  }, [location.pathname]);

  const handleLogout = () => {
    setSelectedButton('');
    navigate('/login');
  };

  return (
    <nav className="navbar">
      <div className="title" onClick={() => navigate('/home')}>
        Banking App
      </div>
      <div className="user-info">
        {username && (
          <p className="username">
            <strong>{username}</strong>
          </p>
        )}
        <button
          onClick={handleLogout}
          className={selectedButton === 'home' ? 'button selectedButton' : 'button'}
        >
          Log out
        </button>
      </div>
    </nav>
  );
};

export default HomeNavbar;
