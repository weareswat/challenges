# React Challenge

We want you to solve a problem that we had at invoicexpress.


## 1 The problem

We want to display a **list** of **invoices**, and be able to **filter** and **paginate** that list.

### 1.2 Information displayed on the list

Each **invoice** has the following information:

* Document status (Draft, Final, Paid, Cancelled)
* Document type (Invoice, Invoice-Receipt, Receipt)
* Number (either Draft, or a string in the format year/number. **IE:** 2018/11)
* Client name
* Date
* Total without VAT

### 1.3 Filtering capabilities

We want the user to be able to filter by any of the invoice fields.
That means filter by:

* Document status
* Document type
* Document Number
* Client name
* Date
* Total without VAT

### 1.4 Pagination

* Assume we only show **7 documents** for each batch

## 2 Implementation

* Use the `documents.json` file as the information source of your data
  * Ideally this information would come from our API, but for now just paste this JSON somewhere in your code and fake an API call
  * Don't like our `documents.json` format? You're free to change the JSON specification as you see fit
* You're free to design and display the information as you see fit (toggle, dropdown, sliders, etc)

## 3 What we value

* Code organization
* Tests
* Good documentation

## 4 How to submit your solution:

* Create a public repository
  * Create a PR where we can see the code and comment
  * Use a branching structure that makes it easier for us to review your work
* Deploy your solution to github pages

Best of luck!
Any question don't hesitate to contact us!