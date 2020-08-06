import unittest
from unittest import TestCase
from app import ClientGateway


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
        self.assertTrue(ClientGateway.valid_email("teste@teste.pt"))
        self.assertFalse(ClientGateway.valid_email("teste@.pt"))
        self.assertFalse(ClientGateway.valid_email("teste.pt"))

    def test_valid_fiscal_id(self):
        self.assertTrue(ClientGateway.valid_fiscal_id("999999990"))  # PORTUGAL
        self.assertTrue(ClientGateway.valid_fiscal_id("99999999"))  # Malta
        self.assertFalse(ClientGateway.valid_fiscal_id("9"))

    def test_valid_name(self):
        self.assertTrue(ClientGateway.valid_name("Gonçalo"))
        self.assertTrue(ClientGateway.valid_name("Jose"))
        self.assertFalse(ClientGateway.valid_name("asd$"))
        self.assertFalse(ClientGateway.valid_name("ola!"))
