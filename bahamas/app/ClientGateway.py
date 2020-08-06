import re

from bahamas.app import app
from flask import request

from bahamas.app import db
from bahamas.app.models import Client, Invoice

NEEDED_ARGS = ['email', 'fiscal_id', 'name']


@app.route('/store-bahamas-client/<invoice>')
def register(invoice):
    for key in NEEDED_ARGS:
        if key not in request.args:
            return "Missing or invalid argument. Please check if all parameters are correct", 400

    cl_email = request.args['email']
    cl_fiscal = request.args['fiscal_id']
    cl_name = request.args['name']

    if valid_name(cl_name) and valid_email(cl_email) and valid_fiscal_id(cl_fiscal):
        client = Client.query.filter(Client.email == cl_email).first()
        if client is None:
            client = Client(fiscal_id=cl_fiscal, email=cl_email, name=cl_name)
            db.session.add(client)

        invoice_q = Invoice.query.filter(Invoice.invoice_id == invoice).first()
        if invoice_q is not None:
            return "The provided invoice already has a second user", 200
        else:
            invoice = Invoice(invoice_id=invoice, rel_sec_client=client)
            db.session.add(invoice)
        print(client)
        db.session.commit()
        return request.args, 200
    else:
        return "Please check your parameters and try again", 400


@app.route('/retrieve-bahamas-client/<invoice_id>')
def retrieve(invoice_id):
    print(app.config['SQLALCHEMY_DATABASE_URI'])
    return invoice_id, 200


def valid_name(name):
    regex = '^[_A-zÀ-ú0-9]*((-|\s)*[_A-zÀ-ú0-9])*$'
    if re.search(regex, name):
        return True
    else:
        return False


def valid_email(email):
    regex = '^[a-z0-9]+[\._]?[a-z0-9]+[@]\w+[.]\w+$'
    if re.search(regex, email):
        return True
    else:
        return False


def valid_fiscal_id(fiscal_id):
    regex_europe = '^((AT)?U[0-9]{8}|(BE)?0[0-9]{9}|(BG)?[0-9]{9,10}|(CY)?[0-9]{8}L|\
        (CZ)?[0-9]{8,10}|(DE)?[0-9]{9}|(DK)?[0-9]{8}|(EE)?[0-9]{9}|\
        (EL|GR)?[0-9]{9}|(ES)?[0-9A-Z][0-9]{7}[0-9A-Z]|(FI)?[0-9]{8}|\
        (FR)?[0-9A-Z]{2}[0-9]{9}|(GB)?([0-9]{9}([0-9]{3})?|[A-Z]{2}[0-9]{3})|\
        (HU)?[0-9]{8}|(IE)?[0-9]S[0-9]{5}L|(IT)?[0-9]{11}|\
        (LT)?([0-9]{9}|[0-9]{12})|(LU)?[0-9]{8}|(LV)?[0-9]{11}|(MT)?[0-9]{8}|\
        (NL)?[0-9]{9}B[0-9]{2}|(PL)?[0-9]{10}|(PT)?[0-9]{9}|(RO)?[0-9]{2,10}|\
        (SE)?[0-9]{12}|(SI)?[0-9]{8}|(SK)?[0-9]{10})$'
    if re.search(regex_europe, fiscal_id):
        return True
    else:
        return False
