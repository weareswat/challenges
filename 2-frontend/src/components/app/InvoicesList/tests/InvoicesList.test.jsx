import React from 'react';
import { shallow } from 'enzyme';
import InvoicesList from "../InvoicesList";
import Pagination from "../../../visual/Pagination/Pagination";
import Filter from "../../../visual/Filter/Filter";
import Table from "../../../visual/Table/Table";
import getInvoices from "../../../../services/getInvoices";

/**
 * MOCKS **************************
 */

jest.mock("../../../../services/getInvoices");

const mockInvoices = [
  {id: "mockInvoice1"}, 
  {id: "mockInvoice2"}, 
  {id: "mockInvoice3"}
];
getInvoices.mockImplementation(() => ({
  items: mockInvoices,
  total: 10
}));

/**
 * ********************************
 */

/**
 * DEFAULT STATES **************************
 */

const defaultInvoices = {
  items: [],
  total: 0,
  requested: false
};

const defaultPageOpts = {
  page: 0,
  offset: 7
};

const defaultFilter = {
  by: null,
  val: ""
};

 /**
  * ****************************************
  */

const getShallowWrapper = () => (
  shallow(
    <InvoicesList />
  )
);

const getRequestInvoicesBtn = wrapper => (
  wrapper.find("#getInvoicesBtn").at(0)
);

const getPagination = wrapper => (
  wrapper.find(Pagination).at(0)
);

const getFilter = wrapper => (
  wrapper.find(Filter).at(0)
);

const getTable = wrapper => (
  wrapper.find(Table).at(0)
);

const assertRequestInvoices = (wrapper, pageOpts, filter) => {
  const btn = getRequestInvoicesBtn(wrapper);
  btn.simulate("click");
  expect(getInvoices).toHaveBeenCalledTimes(1);
  expect(getInvoices).toHaveBeenCalledWith(pageOpts, filter); 
};

describe("InvoicesList", () => {
  beforeEach(() => {
    getInvoices.mockClear();
  });

  it("should render a button to get the invoices, a Filter, a Table and a Pagination", () => {
    const wrapper = getShallowWrapper();
    expect(getRequestInvoicesBtn(wrapper).exists()).toBeTruthy();    
    expect(getFilter(wrapper).exists()).toBeTruthy();

    const pagination = getPagination(wrapper);
    expect(pagination.exists()).toBeTruthy();
    const pagProps = pagination.props();
    expect(pagProps.page).toEqual(defaultPageOpts.page);
    expect(pagProps.offset).toEqual(defaultPageOpts.offset);
    expect(pagProps.totalItems).toEqual(defaultInvoices.total);
    
    const table = getTable(wrapper);
    expect(table.exists()).toBeTruthy();
    expect(table.props().items).toEqual(defaultInvoices.items);
  });

  it("should call getInvoices service with default services when get invoices button is clicked" +
    "and inject those invoices into the table component", () => {

    const wrapper = getShallowWrapper();
    const btn = getRequestInvoicesBtn(wrapper);
    btn.simulate("click");
    expect(getInvoices).toHaveBeenCalledTimes(1);
    expect(getInvoices).toHaveBeenCalledWith(defaultPageOpts, defaultFilter); 

    const table = getTable(wrapper);
    expect(table.props().items).toEqual(mockInvoices);
  });

  it("should call getInvoices service with new page options", () => {
    const wrapper = getShallowWrapper();
    const btn = getRequestInvoicesBtn(wrapper);
    btn.simulate("click"); // have to do a first request, otherwise handlePageChange won't do a request

    const pagination = getPagination(wrapper);
    const newPage = 1;
    const newPageOpts = {
      ...defaultPageOpts,
      page: newPage
    };
    pagination.props().onPageChange(newPage);

    expect(getInvoices).toHaveBeenCalledTimes(2); // called on first click, and on page change
    expect(getInvoices.mock.calls[1][0]).toEqual(newPageOpts);
    expect(getInvoices.mock.calls[1][1]).toEqual(defaultFilter);
  });

  it("should call getInvoices service with new filter options", () => {
    const wrapper = getShallowWrapper();
    const btn = getRequestInvoicesBtn(wrapper);
    btn.simulate("click"); // have to do a first request, otherwise handleFilterChange won't do a request

    const filter = getFilter(wrapper);
    const newFilter = {
      by: "mockBy",
      val: "mockVal"
    };
    filter.props().onChangeFilter(newFilter.by, newFilter.val);

    expect(getInvoices).toHaveBeenCalledTimes(2); // called on first click, and on filter change
    expect(getInvoices.mock.calls[1][0]).toEqual(defaultPageOpts);
    expect(getInvoices.mock.calls[1][1]).toEqual(newFilter);
  });

  it("should not call getInvoices, if the get invoices btn wasn't clicked", () => {
    // If a first request wasn't done with the button, page changes and filter changes will not trigger requests
    const wrapper = getShallowWrapper();
    const pagination = getPagination(wrapper);
    pagination.props().onPageChange(1);
    const filter = getFilter(wrapper);
    filter.props().onChangeFilter("mockBy", "mockVal");

    expect(getInvoices).not.toHaveBeenCalled();
  });
});