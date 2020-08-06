from app import db


class Client(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    fiscal_id = db.Column(db.String(20), unique=True)
    email = db.Column(db.String(120), unique=True)
    name = db.Column(db.String(64))
    invoices = db.relationship('Invoice', backref='author', lazy='dynamic')

    def __repr__(self):
        return '<User {}>'.format(self.email)


class Invoice(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    invoice_id = db.Column(db.String(64), index=True, unique=True)
    pri_client = db.Column(db.String(20))
    sec_client = db.Column(db.Integer, db.ForeignKey('client.id'))

    def __repr__(self):
        return '<Invoice {}>'.format(self.invoice_id)
