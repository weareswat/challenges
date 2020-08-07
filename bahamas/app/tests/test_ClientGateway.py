import unittest
from flask_testing import TestCase

from flask import Flask, Response

import bahamas.app.validations
from bahamas.app import gateway, db, app
from bahamas.config import TestingConfig


class Test(TestCase):
    # Testing application setup
    def create_app(self):
        gateway.app.config.from_object(TestingConfig)
        db.init_app(app)

        return gateway.app

    def setUp(self):
        self.app = self.create_app().test_client()
        db.drop_all()
        db.create_all()

    def tearDown(self):
        db.session.remove()
        db.drop_all()

    # Testing
    def test_register(self):
        # No invoices created with id 12345
        response = self.register(invoice_id='12345', fiscal_id='999999999', name='Bob', email='bob@bob.com')
        self.assertEqual(response.status_code, 404)

        from bahamas.app.models import Invoice

        # Create an invoice with a random number for primary client
        invoice = Invoice(invoice_id='12345', pri_client='1')
        db.session.add(invoice)

        # Add a secondary client to the invoice
        response = self.register(invoice_id='12345', fiscal_id='999999999', name='Bob', email='bob@bob.com')
        self.assertEqual(response.status_code, 200)

        # Add a secondary client after one was already there
        response = self.register(invoice_id='12345', fiscal_id='999999998', name='Ed', email='ed@ed.com')
        self.assertEqual(response.status_code, 404)

    def register(self, invoice_id, fiscal_id, name, email):
        r = self.app.get(
            '/store-bahamas-client/{}'.format(invoice_id),
            query_string='fiscal_id={}&name={}&email={}'.format(fiscal_id, name, email)
        )
        return Response(
            r.data,
            status=r.status_code,
            content_type=r.headers['content-type']
        )

    def test_retrieve(self):
        # Invoice 12345 not in database
        response = self.retrieve(invoice_id='12345')
        self.assertEqual(response.status_code, 404)

        from bahamas.app.models import Invoice, Client

        # Create an invoice with a random number for primary client
        invoice = Invoice(invoice_id='12345', pri_client='1')
        db.session.add(invoice)

        # Create a random client
        client = Client(fiscal_id='999999999', name='Bob', email='bob@bob.com')
        db.session.add(client)

        # No secondary client assigned to invoice
        response = self.retrieve(invoice_id='12345')
        self.assertEqual(response.status_code, 404)

        # Add the client to invoice
        invoice.rel_sec_client = client

        # Retrieving secondary client from invoice
        response = self.retrieve(invoice_id='12345')
        self.assertEqual(response.status_code, 200)

    def retrieve(self, invoice_id):
        r = self.app.get('/retrieve-bahamas-client/{}'.format(invoice_id))
        return Response(
            r.data,
            status=r.status_code,
            content_type=r.headers['content-type']
        )

    def test_valid_email(self):
        self.assertTrue(bahamas.app.validations.email_is_valid("teste@teste.pt"))
        self.assertFalse(bahamas.app.validations.email_is_valid("teste@.pt"))
        self.assertFalse(bahamas.app.validations.email_is_valid("teste.pt"))

    def test_valid_fiscal_id(self):
        self.assertTrue(bahamas.app.validations.fiscal_id_is_valid("999999990"))  # PORTUGAL
        self.assertTrue(bahamas.app.validations.fiscal_id_is_valid("99999999"))  # Malta
        self.assertFalse(bahamas.app.validations.fiscal_id_is_valid("9"))

    def test_valid_name(self):
        self.assertTrue(bahamas.app.validations.name_is_valid("Gonçalo"))
        self.assertTrue(bahamas.app.validations.name_is_valid("Jose"))
        self.assertFalse(bahamas.app.validations.name_is_valid("asd$"))
        self.assertFalse(bahamas.app.validations.name_is_valid("ola!"))

    def test_valid_invoice(self):
        self.assertTrue(bahamas.app.validations.invoice_is_valid("12345"))
        self.assertFalse(bahamas.app.validations.invoice_is_valid("asd$123"))


if __name__ == '__main__':
    unittest.main()
