
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import SignUp from './components/SignUp';
import Login from './components/Login';
import Home from './components/Home';
import AboutUs from './components/AboutUs';
import Accounts from './components/Accounts';
import MainLayout from './components/MainLayout';

function App() {
  return (
    <Router>
      <div>
      <Routes>
          <Route path="/signup" element={<SignUp />} />
          <Route path="/login" element={<Login />} />
          <Route path="about" element={<AboutUs />} />
          <Route path="/" element={<MainLayout />}>
            <Route path="home" element={<Home />} />
            <Route path="accounts" element={<Accounts />} />
            
          </Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
