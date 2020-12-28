/**
 * @jest-environment node
 */

const axios = require('axios')
const assert = require('assert')
const utils = require('../utils/utils')

describe("Testing with valid input", function() {
    it("Should return status code 201 and body as expected", function (done) {
        const body = {
            obj1: {
                "_id": 4321424,
                "name": "Bruce Norris"
            },
            obj2: {
                "_id": 4321424,
                "name": "Bruce Willis"
            }
        }

        const expected =
            {
                "field": "name",
                "old": "Bruce Norris",
                "new": "Bruce Willis"
            }

        let url = utils.postURL();

        axios
            .post(url, body)
            .then(response => {
                assert.strictEqual(response.status, 201)
                assert.strictEqual(JSON.stringify(response.data.body[0].diff), JSON.stringify(expected))
                done()
            })
    });
})

describe("Testing with invalid input", function() {
    it("Invalid _id property - Should return status code 400", function (done) {
        const body = {
            obj1: {
                "_id": "",
                "name": "Bruce Norris"
            },
            obj2: {
                "_id": 4321424,
                "name": "Bruce Willis"
            }
        }

        let url = utils.postURL();

        axios
            .post(url, body)
            .catch(error => {
                assert.strictEqual(error.response.status, 400)
                done()
            })
    });
})
