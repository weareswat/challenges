## GET /getChanges/:id&:minDate&:maxDate
Returns a list of one change per user/field, filtered by `id`, `minDate` and `maxDate`.
- Request:
  - Path parameters:
    - `id` - User id
    - `minDate` - Start date to filter
    - `maxDate` - End date to filter
  - Body: None
- Response:
  - Success:
    - URL example: /getChanges/1&2020-12-25&2020-12-26
    - Status code: 200
    - Content-Type: application/json
    - Body example:
    ```
    {
        message: 'Games obtained successfully.',
        body: [
            {
                "field": "name",
                "old": "Bruce Norris",
                "new": "Bruce Willis"
            },
            (...)
        ]
    }
    ```
  - Error:
    - URL example: /getChanges/1&invalidDate&invalidDate
    - Status code: 400
    - Content-Type: application/json
    - Body example:
    ```
    {
        "message": "error: invalid input syntax for type timestamp: invalidDate.",
        "body": {}
    }
    ```

## POST /createChanges
Creates an object with the difference between `obj1` and `obj2`.
- Request:
  - Body:
    ``` 
    {
    	"obj1": {
    		"_id": 1,
            "name": "Bruce Willis",
    		"address": {
    			"city": "Lisbon",
    			"country": "Portugal"
    		}
    	},
    	"obj2": {
    		"_id": 1,
    		"name": "John",
    		"address": {
    			"city": "Berlin",
    			"country": "Germany"
    		}
    	}
    }
    ```
- Response:
  - Success:
    - Status code: 201
    - Content-Type: application/json
    - Body example:
    ```
    {
      "message": "Created - Values created successfully.",
      "body": {
            "_id": 1,
            "diffObject": [
                {
                    "field": "name",
                    "old": "Bruce Willis",
                    "new": "John"
                },
                {
                    "field": "address.city",
                    "old": "Lisbon",
                    "new": "Berlin"
                },
                {
                    "field": "address.country",
                    "old": "Portugal",
                    "new": "Germany"
                }
            ]
        }
    }
    ```
  - Error:
    - Status code: 400
    - Content-Type: application/json
    - Request body example:
        - Different values for property _id
        - Null value for a property
        - Both objects don't have the exact same properties
    ```
    {
    	"obj1": {
    		"_id": 5,
            "name": "Bruce Willis"
    	},
    	"obj2": {
    		"_id": 1,
    		"name": null,
    		"address": {
    			"city": "Berlin",
    			"country": "Germany"
    		}
    	}
    }
    ```
    - Response body example:
    ```
    {
        "message": "Invalid arguments - Null value..",
        "body": {}
    }
    ```