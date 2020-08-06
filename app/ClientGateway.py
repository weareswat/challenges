import os
import re

from app import app
from flask import request

from app import db
from app.models import Client, Invoice

NEEDED_ARGS = ['email', 'fiscal_id', 'name']


@app.route('/store-bahamas-client/<invoice_id>')
def register(invoice_id):
    for key in NEEDED_ARGS:
        if key not in request.args:
            return "Missing or invalid argument. Please check if all parameters are correct", 400
    return request.args, 200


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
