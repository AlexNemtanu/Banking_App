import React, { useState } from 'react';
import axios from 'axios';

const AddAccount = () => {
    const [accountHolderName, setAccountHolderName] = useState('');
    const [balance, setBalance] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/api/accounts', { accountHolderName, balance });
            if (response.status === 201) {
                alert("Account added successfully");
                setAccountHolderName('');
                setBalance('');
            }
        } catch (error) {
            console.error("Failed to add account:", error);
        }
    };

    return (
        <div>
            <h2>Add Account</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={accountHolderName}
                    onChange={(e) => setAccountHolderName(e.target.value)}
                    placeholder="Account Holder Name"
                />
                <input
                    type="number"
                    value={balance}
                    onChange={(e) => setBalance(e.target.value)}
                    placeholder="Balance"
                />
                <button type="submit">Add Account</button>
            </form>
        </div>
    );
};

export default AddAccount;
