import React, {useEffect, useState } from 'react';
import '../style/Accounts.css';
import axios from 'axios';
const Accounts = () => {
  const [accounts, setAccounts] = useState([]);
  const [newAccountName, setNewAccountName] = useState('');
  const [newAccountBalance, setNewAccountBalance] = useState('');
  const [depositAmount, setDepositAmount] = useState('');
  const [withdrawAmount, setWithdrawAmount] = useState('');

  useEffect(() => {
    fetchAccounts();
  }, []);

  const fetchAccounts = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/accounts');
      setAccounts(response.data);
    } catch (error) {
      console.error('Error fetching accounts:', error);
    }
  };

  const addAccount = async () => {
    if (newAccountName && newAccountBalance) {
      const newAccount = {
        accountHolderName: newAccountName, 
        balance: parseFloat(newAccountBalance),
      };
      try {
        const response = await axios.post('http://localhost:8080/api/accounts', newAccount); 
        setAccounts([...accounts, response.data]);
        setNewAccountName('');
        setNewAccountBalance('');
      } catch (error) {
        console.error('Error adding account:', error);
      }
    }
  };

  const deleteAccount = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/accounts/${id}`);
      setAccounts(accounts.filter(account => account.id !== id));
    } catch (error) {
      console.error('Error deleting account:', error);
    }
  };

  const depositMoney = async (id, amount) => {
    try {
      await axios.put(`http://localhost:8080/api/accounts/${id}/deposit`, { amount: parseFloat(amount) });
      fetchAccounts();
    } catch (error) {
      console.error('Error depositing money:', error);
    }
  };

  const withdrawMoney = async (id, amount) => {
    try {
      await axios.put(`http://localhost:8080/api/accounts/${id}/withdraw`, { amount: parseFloat(amount) });
      fetchAccounts();
    } catch (error) {
      console.error('Error withdrawing money:', error);
    }
  };

  return (
    <div style={styles.container}>
      <h2>Accounts</h2>
      <div style={styles.addAccountForm}>
        <input
          type="text"
          placeholder="Account Name"
          value={newAccountName}
          onChange={(e) => setNewAccountName(e.target.value)}
          style={styles.input}
        />
        <input
          type="number"
          placeholder="Initial Balance"
          value={newAccountBalance}
          onChange={(e) => setNewAccountBalance(e.target.value)}
          style={styles.input}
        />
        <button onClick={addAccount} style={styles.button}>Add Account</button>
      </div>
      <div>
        {accounts.map(account => (
          <div key={account.id} className="account-item">
            <h3>{account.accountHolderName}</h3>
            <p>Balance: ${account.balance.toFixed(2)}</p>
            <button onClick={() => deleteAccount(account.id)} className="delete-button">Delete Account</button>
            <div className="transaction-form">
              <input
                type="number"
                placeholder="Deposit Amount"
                value={account.depositAmount}
                onChange={(e) => account.depositAmount = e.target.value}
                className="input"
              />
              <button onClick={() => { depositMoney(account.id, account.depositAmount); account.depositAmount = ''; }} className="transaction-button">Deposit</button>
            </div>
            <div className="transaction-form">
              <input
                type="number"
                placeholder="Withdraw Amount"
                value={account.withdrawAmount}
                onChange={(e) => account.withdrawAmount = e.target.value}
                className="input"
              />
              <button onClick={() => { withdrawMoney(account.id, account.withdrawAmount); account.withdrawAmount = ''; }} className="transaction-button">Withdraw</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

const styles = {
  container: {
    padding: '20px',
    backgroundColor: '#f5f5f5',
    borderRadius: '5px',
    marginTop: '20px',
  },
  addAccountForm: {
    marginBottom: '20px',
  },
  input: {
    padding: '10px',
    marginRight: '10px',
    borderRadius: '5px',
    border: '1px solid #ccc',
  },
  button: {
    padding: '10px 20px',
    borderRadius: '5px',
    border: 'none',
    backgroundColor: '#5e057e',
    color: '#fff',
    cursor: 'pointer',
  },
};

export default Accounts;
