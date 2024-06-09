import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Home from './components/Home';
import AccountList from './components/AccountList';
import AccountDetails from './components/AccountDetails';
import AddAccount from './components/AddAccount';

function App() {
    return (
        <Router>
            <nav>
                <Link to="/login">Login</Link>
                <Link to="/register">Register</Link>
                <Link to="/home">Home</Link>
            </nav>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/home" element={<Home />} />
                <Route path="/accounts" element={<AccountList />} />
                <Route path="/accounts/:id" element={<AccountDetails />} />
                <Route path="/add-account" element={<AddAccount />} />
            </Routes>
        </Router>
    );
}

export default App;
