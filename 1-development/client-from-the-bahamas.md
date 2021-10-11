InvoiceXpress is a web app that makes online invoicing easy. Customers can create an account and use the API to create invoices. Each invoice has a `Client`, and there is an [API for interacting with clients](http://invoicexpress.com/api/clients/create).

### Bahamas

It has come to our attention that on the Bahamas, invoices can have an additional client, that will be eligible for tax deductions. We need a service to handle that specific logic. The service would be called by the main app to store and retrieve the `Client`.

The service should have the following endpoints, where `1234` is the invoice's id:

```
/store-bahamas-client/1234?fiscal_id=999999999&name=Bob&email=bob@bob.com
/retrieve-bahamas-client/1234
```

When the `Client` is stored, the service should make a call to

```
https://bahamas.gov/register?invoice=1234&fiscal_id=999999999&name=Bob&email=bob@bob.com
```

### Objectives

We'd like to see a working web app, with the requested endpoints (Store Client, Retrieve Client). No UI is needed, only the endpoints. You can store the client on a RDBMS or NoSQL of your choice, or can store the client via InvoiceXpress's API.

Please mock external service calls instead of calling the actual services. This includes InvoiceXpress API (if you decide to use it) and the bahamas.gov site.

We'd like to see how communication happens between us. Please use GitHub.

Tech stack: please use preferably Ruby / Rails or JAVA, but if you are more versatile in a different tech stack, you may use one of your choice.
