import { isNil, isEmpty } from "ramda";
import * as documentsJson from "./documents.json";

const filter = (invoices, filterOpts) => {
  const { by: filterBy, val: filterVal } = filterOpts;
  if (isNil(filterBy) || isEmpty(filterVal)) {
    return invoices;
  }
  return invoices.filter(inv => inv[filterBy].includes(filterVal));
};

const getInvoices = (pageOpts, filterOpts) => {
  const { page, offset } = pageOpts;
  const filteredInvoices = filter(documentsJson.documents, filterOpts);
  const invoices = filteredInvoices.slice(page * offset, (page * offset) + offset);
  return {
    items: invoices,
    totalCount: filteredInvoices.length
  };
};

export default getInvoices;