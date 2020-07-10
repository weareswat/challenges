import React, { useState, useEffect } from "react";
import getInvoices from "../../../services/getInvoices";
import Table from "../../visual/Table/Table";
import Filter from "../../visual/Filter/Filter";
import Pagination from "../../visual/Pagination/Pagination";
import "./style.scss";

const getOption = (label, value) => ({
  label,
  value
});

const columns = [
  getOption("Document Status", "status"),
  getOption("Document Type", "type"),
  getOption("Number", "number"),
  getOption("Client Name", "client_name"),
  getOption("Date", "date"),
  getOption("Total without VAT", "total_w_vat")
];

const InvoicesList = ({

}) => {
  const [invoices, setInvoices] = useState({
    items: [],
    total: 0,
    requested: false
  });
  const [pageOpts, setPageOpts] = useState({
    page: 0,
    offset: 7
  });
  const [filter, setFilter] = useState({
    by: null,
    val: ""
  });

  const handleGetInvoices = () => {
    const { items, totalCount } = getInvoices(pageOpts, filter);
    setInvoices({
      items,
      total: totalCount,
      requested: true
    });
  };

  const handleChangeFilter = (filterBy, filterVal) => {
    setFilter({
      by: filterBy,
      val: filterVal
    });
    setPageOpts({
      ...pageOpts,
      page: 0
    });
  };

  const handlePageChange = nextPage => {
    setPageOpts({
      ...pageOpts,
      page: nextPage
    });
  };

  // Listen to page change and update invoices list
  // Only get the invoices if they were already requested before, by clicking the button
  useEffect(() => {
    if (invoices.requested) {
      handleGetInvoices();
    }
  }, [pageOpts.page, filter]);

  return (
    <div className="invoices-container">
      <button id="getInvoicesBtn" onClick={handleGetInvoices}>
        Get Invoices
      </button>
      <Filter 
        options={columns} 
        onChangeFilter={handleChangeFilter}
      />
      <Table
        columns={columns} 
        items={invoices.items}
        itemIdProp="id"
      />
      <Pagination 
        totalItems={invoices.total} 
        page={pageOpts.page} 
        offset={pageOpts.offset} 
        onPageChange={handlePageChange} 
      />
    </div>
  )
};

export default InvoicesList;