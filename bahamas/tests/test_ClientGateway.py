import unittest
from flask_testing import TestCase

from flask import Flask, Response

import bahamas.app.validations
from bahamas.app import ClientGateway


class Test(TestCase):
    def create_app(self):
        app = Flask(__name__)
        app.config['TESTING'] = True
        return app

    def test_register(self):
        response = self.register(invoice_id='12345', fiscal_id='999999999', name='Bob', email='bob@bob.com')
        self.assertEqual(response.status_code, 200)

    def register(self, invoice_id, fiscal_id, name, email):
        ClientGateway.app.testing = True
        client = ClientGateway.app.test_client()
        r = client.get(
            '/store-bahamas-client/{}'.format(invoice_id),
            query_string='fiscal_id={}&name={}&email={}'.format(fiscal_id, name, email)
        )
        return Response(
            r.data,
            status=r.status_code,
            content_type=r.headers['content-type']
        )

    # def test_retrieve(self):
    #     response = ClientGateway.app.get(
    #         '/store-bahamas-client/1234'
    #     )
    #     self.assertEquals(response.status_code, 200)

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
