import React from 'react';
import InvoicesTable from './components/InvoicesTable';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>
          Invoices
        </p>
      </header>
      <InvoicesTable className='invoicesTable'/>
    </div>
  );
}

export default App;
