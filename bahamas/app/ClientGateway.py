from bahamas.app import app
from flask import request, Response
import requests

from bahamas.app import db
from bahamas.app.models import Client, Invoice
from bahamas.app.validations import *

NEEDED_ARGS = ['email', 'fiscal_id', 'name']


@app.route('/store-bahamas-client/<invoice>')
def register(invoice):
    for key in NEEDED_ARGS:
        if key not in request.args:
            return "Missing or invalid argument. Please check if all parameters are correct", 404

    cl_email = request.args['email']
    cl_fiscal = request.args['fiscal_id']
    cl_name = request.args['name']

    response = None
    if name_is_valid(cl_name) and email_is_valid(cl_email) and fiscal_id_is_valid(cl_fiscal) and invoice_is_valid(invoice):
        client = Client.query.filter(Client.email == cl_email).first()
        if client is None:
            client = Client(fiscal_id=cl_fiscal, email=cl_email, name=cl_name)
            db.session.add(client)
            r = requests.get("http://127.0.0.1:8000/mockup-bahamas?invoice={}&fiscal_id={}&name={}&email={}".format(invoice, cl_fiscal, cl_name, cl_email))
            return Response(
                r.text,
                status=r.status_code,
                content_type=r.headers['content-type']
            )
        else:
            client.name, client.fiscal_id = cl_name, cl_fiscal
        invoice_q = Invoice.query.filter(Invoice.invoice_id == invoice).first()
        if invoice_q is not None:
            if invoice_q.sec_client is not None:
                if invoice_q.rel_sec_client != client:
                    return "The provided invoice already has a second user", 200
            else:
                invoice_q.rel_sec_client = client
            response = "The client info as been updated"
        else:
            response = "No invoices with that id"
        # LOGIC FOR INSERTING A NEW INVOICE
        # else:
        #     invoice = Invoice(invoice_id=invoice, rel_sec_client=client)
        #     db.session.add(invoice)
        db.session.commit()
        return response, 200
    else:
        return "Please check your parameters and try again", 404


@app.route('/retrieve-bahamas-client/<invoice_id>')
def retrieve(invoice_id):
    if invoice_is_valid(invoice_id):
        invoice_q = Invoice.query.filter(Invoice.invoice_id == invoice_id).first()
        if invoice_q is not None:
            client = invoice_q.rel_sec_client
            client_info = {
                'email': client.email,
                'name': client.name,
                'fiscal_id': client.fiscal_id
            }
            return client_info, 200
    return "No invoices with that id", 404


@app.route('/mockup-bahamas')
def mochup_bahamas():
    return request.args, 200


