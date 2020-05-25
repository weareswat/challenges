import * as pgsql from './utils/pgsql';
import { Config } from './config';
import { ExpressWrapper } from './utils/express-wrapper';
import { router } from './routes';

// init DB connection
pgsql.init(); 

// init web server
new ExpressWrapper(Config.port, router).start();
