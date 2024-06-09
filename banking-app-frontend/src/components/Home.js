import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div>
            <h2>Home</h2>
            <Link to="/accounts">View Accounts</Link>
        </div>
    );
};

export default Home;
