import unittest
from unittest import TestCase

import bahamas.app.validations
from bahamas.app import ClientGateway


class Test(TestCase):
    # def test_register(self):
    #     response = ClientGateway.app.get(
    #         '/store-bahamas-client/1234?fiscal_id=999999999&name=Bob&email=bob@bob.com'
    #     )
    #     self.assertEquals(response.status_code, 200)

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
