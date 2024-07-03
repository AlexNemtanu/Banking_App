import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import '../style/Navbar.css';

const AuthNavbar = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [selectedButton, setSelectedButton] = useState('');

  useEffect(() => {
   
    const currentPath = location.pathname.substring(1);
   
    setSelectedButton(currentPath);
  }, [location.pathname]);

  const handleNavigation = (path, button) => {
    setSelectedButton(button);
    navigate(path);
  };

  return (
    <nav className="navbar">
      <div className="title" onClick={() => handleNavigation('/home', 'home')}>
        Banking App
      </div>
      <div className="buttonContainer">
        <button
          onClick={() => handleNavigation('/login', 'login')}
          className={selectedButton === 'login' ? 'button selectedButton' : 'button'}
        >
          Login
        </button>
        <button
          onClick={() => handleNavigation('/signup', 'signup')}
          className={selectedButton === 'signup' ? 'button selectedButton' : 'button'}
        >
          Sign Up
        </button>
        <button
          onClick={() => handleNavigation('/about', 'about')}
          className={selectedButton === 'about' ? 'button selectedButton' : 'button'}
        >
          About Us
        </button>
      </div>
    </nav>
  );
};

export default AuthNavbar;
