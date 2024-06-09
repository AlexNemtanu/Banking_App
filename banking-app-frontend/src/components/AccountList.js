import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const AccountList = () => {
    const [accounts, setAccounts] = useState([]);

    useEffect(() => {
        const fetchAccounts = async () => {
            try {
                const response = await axios.get('/api/accounts');
                setAccounts(response.data);
            } catch (error) {
                console.error("Failed to fetch accounts:", error);
            }
        };
        fetchAccounts();
    }, []);

    return (
        <div>
            <h2>Accounts</h2>
            <ul>
                {accounts.map(account => (
                    <li key={account.id}>
                        <Link to={`/accounts/${account.id}`}>{account.accountHolderName}</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AccountList;
