import React, { useState, useEffect} from 'react';

export default function InvoicesTable() {
  const [hasError, setErrors] = useState("Empty");
  const [invoices, setInvoices] = useState({invoices: [{"id": "000000"}]});

  useEffect(() => {
    // This must be mocked: maybe read a file
    // async function fetchData() {
    //   const res = await fetch('https://reactintregration.app.invoicexpress.com/invoices.json?api_key=170a9887d6c02ccf1b1ed782599e3ab4a00d47ae', 
    //   {
    //     mode: 'no-cors',
    //     headers: {
    //       "Access-Control-Allow-Origin": "*",
    //       "Access-Control-Allow-Credentials": true
    //     }
    //   });
    //   debugger
    //   res
    //     .json()
    //     .then(res => setInvoices(res))
    //     .catch(err => setErrors(err))
    // }
    // fetchData();
  });

  return (
    <span>
      {invoices.invoices.map( invoice => <p>{invoice.id}</p>)}
      <h3>Errors: {hasError.stack}</h3>
    </span>
  )
}