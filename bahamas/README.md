# client-from-bahamas
 Invoicexpress challenge

`python install -r requirements-txt`

`export FLASK_APP=main.py`

`flask db init`

`flask db migrate -m "tables"`

`flask db upgrade`

`flask run` 

Unit testing:

`python -m unittest bahamas\app\tests\test_ClientGateway.py`