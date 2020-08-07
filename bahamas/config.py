import os

BASEDIR = os.path.abspath(os.path.dirname(__file__))
APP_DB = 'app.db'
TEST_DB = 'test.db'


class Config(object):
    SQLALCHEMY_DATABASE_URI = os.environ.get('DATABASE_URL') or \
            'sqlite:///' + os.path.join(BASEDIR, APP_DB)
    SQLALCHEMY_TRACK_MODIFICATIONS = False


class TestingConfig(object):
    SQLALCHEMY_DATABASE_URI = os.environ.get('DATABASE_URL_TEST') or \
            'sqlite:///' + os.path.join(BASEDIR, TEST_DB)
    TESTING = True
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    WTF_CSRF_ENABLED = False
    DEBUG = False
