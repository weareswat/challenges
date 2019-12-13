# React Challenge

We want you to solve a problem that we had at invoicexpress.


## The problem

We want to display a **list** of **invoices**, and be able to **filter** and **paginate** that list.

### Information displayed on the list

Each **invoice** has the following information:

* Document status (Draft, Final, Paid, Cancelled)
* Document type (Invoice, Invoice-Receipt, Receipt)
* Number (either Draft, or a string in the format year/number. **IE:** 2018/11)
* Client name
* Date
* Total without VAT

### Filtering capabilities

We want the user to be able to filter by any of the invoice fields.
That means filter by:

* Document status
* Document type
* Document Number
* Client name
* Date
* Total without VAT

### Pagination

* Assume we only show **10 documents** for each batch

## Implementation

* Use the `documents.json` file as the information source of your data
  * Ideally this information would come from our API, but for now just paste this JSON somewhere in your code and fake an API call
  * Don't like our `documents.json` format? You're free to change the JSON specification as you see fit
* You're free to design and display the information as you see fit (toggle, dropdown, sliders, etc)

## How to submit your solution:

* Either create a repo, or give us your github user so we can add you to this repo
  * If you're dded to this repo, create another branch, another folder, and work there
* Submit your solution by creating a Pull Request(PR)
  * Use a branching structure that makes it easier for us to review your work
  * You can create as many PR as you like


Best of luck!
Any question don't hesitate to contact us!