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
    - URL example: /getChanges/1&2020-10-30&2020-12-31
    - Status code: 200
    - Content-Type: application/json
    - Body example:
    ```json
    {
        "message": "Values retrieved successfully.",
        "body": [
            {
                "field": "address",
                "old": "2",
                "new": "3"
            },
            {
                "field": "name",
                "old": "gabriel",
                "new": "John"
            },
            {
                "field": "address.country",
                "old": "Portugal",
                "new": "Germany"
            },
            {
                "field": "address.city",
                "old": "Lisbon",
                "new": "Berlin"
            }
        ]
    }
    ```
  - Error:
    - URL example: /getChanges/1&invalidDate&invalidDate
    - Status code: 400
    - Content-Type: application/json
    - Body example:
    ```json
    {
        "message": "error: invalid input syntax for type timestamp: invalidDate.",
        "body": {}
    }
    ```

## POST /createChanges
Creates an object with the difference between `obj1` and `obj2`.
- Request:
  - Body:
    ``` json
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
    ```json
    {
      "message": "Created - Values created successfully.",
      "body": [
          {
            "uuid": 69,
            "userid": 1,
            "date": "2020-12-28T19:00:03.361Z",
            "diff": {
              "field": "name",
              "old": "Bruce Willis",
              "new": "John"
            }
          },
          {
            "uuid": 68,
            "userid": 1,
            "date": "2020-12-28T19:00:03.251Z",
            "diff": {
              "field": "address.city",
              "old": "Lisbon",
              "new": "Berlin"
            }
          },
          {
            "uuid": 67,
            "userid": 1,
            "date": "2020-12-28T19:00:03.250Z",
            "diff": {
              "field": "address.country",
              "old": "Portugal",
              "new": "Germany"
            }
          }
      ]
    }
    ```
  - Error:
    - Status code: 400
    - Content-Type: application/json
    - Request body example:
    
    ```json
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
    ```json
    {
        "message": "Invalid arguments - Null value..",
        "body": {}
    }
    ```
    - Possible errors: 
        - Different values for property _id
        - Null value for a property
        - Both objects don't have the exact same properties
