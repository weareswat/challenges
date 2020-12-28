/**
 * @jest-environment node
 */

const moment = require('moment')
const assert = require('assert')
const axios = require('axios')
const utils = require('../utils/utils')

describe("Testing with valid input", function() {
    it("Should return status code 200 and body length is 0", function (done) {
        let id = 347548679
        let minDate = '2030-10-10'
        let maxDate = '2030-10-20'

        let url = utils.getURL(id, minDate, maxDate);
        axios
            .get(url)
            .then(response => {
                assert.strictEqual(response.status, 200)
                assert.strictEqual(response.data.body.length, 0)
                done()
            })
    });
})
    it("Should return status code 200 and body length is 1", function(done) {
        let id = 123456
        let minDate = moment().format()
        let maxDate = moment().add(1, 'days').format()

        let postURL = utils.postURL();
        let body = {
            obj1: {
                "_id": id,
                "name": "Bruce Norris",
                "address": { "street": "Some street" }
            },
            obj2: {
                "_id": id,
                "name": "Bruce Willis",
                "address": { "street": "Some street" }
            }
        }
        axios
            .post(postURL, body)
            .then(response => {
                let getURL = utils.getURL(id, minDate, maxDate);
                axios
                    .get(getURL)
                    .then(response => {
                        assert.strictEqual(response.status, 200)
                        assert.strictEqual(response.data.body.length, 1)
                        done()
                    })
            })
    });

describe("Testing with invalid input", function() {
    it("Should return status code 400", function (done) {
        let id = 50
        let minDate = 'abcdef'
        let maxDate = 'abcdef'

        let url = utils.getURL(id, minDate, maxDate);

        axios
            .get(url)
            .catch(error => {
                assert.strictEqual(error.response.status, 400)
                done()
            })
    });
})
