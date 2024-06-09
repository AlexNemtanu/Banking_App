import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const AccountDetails = () => {
    const { id } = useParams();
    const [account, setAccount] = useState(null);

    useEffect(() => {
        const fetchAccount = async () => {
            try {
                const response = await axios.get(`/api/accounts/${id}`);
                setAccount(response.data);
            } catch (error) {
                console.error("Failed to fetch account:", error);
            }
        };
        fetchAccount();
    }, [id]);

    if (!account) return <div>Loading...</div>;

    return (
        <div>
            <h2>{account.accountHolderName}</h2>
            <p>Balance: {account.balance}</p>
        </div>
    );
};

export default AccountDetails;
