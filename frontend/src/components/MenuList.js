import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import '../style/MenuList.css';

const MenuList = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [selectedMenu, setSelectedMenu] = useState('');

  useEffect(() => {
    
    const currentPath = location.pathname.substring(1);
    
    setSelectedMenu(currentPath || 'home');
  }, [location.pathname]);

  const handleNavigation = (menu) => {
    setSelectedMenu(menu);
    navigate(`/${menu}`);
  };

  return (
    <div className="menu-container">
      <div
        onClick={() => handleNavigation('home')}
        className={selectedMenu === 'home' ? 'menu-item selectedMenu' : 'menu-item'}
      >
       <span className="icon-home" /> Home
      </div>
      <div
        onClick={() => handleNavigation('accounts')}
        className={selectedMenu === 'accounts' ? 'menu-item selectedMenu' : 'menu-item'}
      >
       <span className="icon-user" /> Accounts
      </div>
      <div
        onClick={() => handleNavigation('transfers')}
        className={selectedMenu === 'transfers' ? 'menu-item selectedMenu' : 'menu-item'}
      >
       <span className="icon-tab" />Transfers
      </div>
    </div>
  );
};

export default MenuList;
