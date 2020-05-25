import * as express from 'express';
import * as bodyParser from 'body-parser';

/**
 * Wraps express initialization and basic config,
 */
export class ExpressWrapper {
	private port: number;
	private app: express.Express;

	constructor(port: number, router: express.Router) {
		this.port = port;

		this.app = express();

		this.app.use(bodyParser.json());
		this.app.use(bodyParser.urlencoded({ extended: true }));

		this.app.use(router);
	}

	start() {
		this.app.listen(this.port, () => {
			console.log(`Server listening on port ${this.port}`);
		});
	}
}
