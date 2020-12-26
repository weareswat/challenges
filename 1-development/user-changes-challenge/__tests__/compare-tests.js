const { compare } = require('../utils/compare.js')

describe("Test valid inputs", () => {
    test("Test without nested objects", () => {
        const a = {
            "_id": 1,
            "name": "Bruce Norris"
        }

        const b = {
            "_id": 1,
            "name": "Bruce Willis"
        }

        const expected = [
            {
                "field": "name",
                "old": "Bruce Norris",
                "new": "Bruce Willis"
            }
        ]

        expect(compare(a, b).diffObject).toEqual(expected);
    });

    test("Test with object instead of simple value", () => {
        const a = {
            "_id": 1,
            "name": "Miguel"
        }

        const b = {
            "_id": 1,
            "name": {
                "extra": 2
            }
        }

        const expected = [
            {
                "field": "name",
                "old": "Miguel",
                "new": {
                    "extra": 2
                }
            }
        ]

        expect(compare(a, b).diffObject).toEqual(expected)
    })

    test("Test with one nested object", () => {
        const a = {
            "_id": 1,
            "name": "Bruce Norris",
            "address": {"street": "Some street"}
        }

        const b = {
            "_id": 1,
            "name": "Bruce Willis",
            "address": {"street": "Nakatomi Plaza"}
        }

        const expected = [
            {
                "field": "name",
                "old": "Bruce Norris",
                "new": "Bruce Willis"
            },
            {
                "field": "address.street",
                "old": "Some street",
                "new": "Nakatomi Plaza"
            }
        ]

        expect(compare(a, b).diffObject).toEqual(expected);
    });

    test("Test with one nested object with multiple properties", () => {
        const a = {
            "_id": 1,
            "name": "Bruce Norris",
            "address": {
                "street": "Some street",
                "city": "Some city"
            }
        }

        const b = {
            "_id": 1,
            "name": "Bruce Willis",
            "address": {
                "street": "Nakatomi Plaza",
                "city": "Another city"
            }
        }

        const expected = [
            {
                "field": "name",
                "old": "Bruce Norris",
                "new": "Bruce Willis"
            },
            {
                "field": "address.street",
                "old": "Some street",
                "new": "Nakatomi Plaza"
            },
            {
                "field": "address.city",
                "old": "Some city",
                "new": "Another city"
            }
        ]

        expect(compare(a, b).diffObject).toEqual(expected);
    });

    test("Test with two nested objects", () => {
        const a = {
            "_id": 1,
            "name": "Bruce Norris",
            "address": {
                "street": "Some street",
                "extraObject": {
                    "anotherExtraObject": 5
                }
            }
        }

        const b = {
            "_id": 1,
            "name": "Bruce Willis",
            "address": {
                "street": "Nakatomi Plaza",
                "extraObject": {
                    "anotherExtraObject": 7
                }
            }
        }

        const expected = [
            {
                "field": "name",
                "old": "Bruce Norris",
                "new": "Bruce Willis"
            },
            {
                "field": "address.street",
                "old": "Some street",
                "new": "Nakatomi Plaza"
            },
            {
                "field": "address.extraObject.anotherExtraObject",
                "old": 5,
                "new": 7
            }
        ]

        expect(compare(a, b).diffObject).toEqual(expected);
    });
});

describe("Test invalid inputs", () => {
    test("Test with null value", () => {
        const a = {
            "_id": 1,
            "name": "Bruce Norris",
            "address": { "street": "Some street" }
        }

        const b = {
            "_id": 1,
            "name": "Bruce Willis",
            "address": null
        }

        try {
            compare(a, b)
        } catch (e) {
            expect(e).toBe("Invalid arguments - Null value.");
        }
    });

    test("Test with different properties", () => {
        const a = {
            "_id": 1,
            "name": "Bruce Norris"
        }

        const b = {
            "_id": 1,
            "wrongName": "Bruce Willis"
        }

        try {
            compare(a, b)
        } catch (e) {
            expect(e).toBe("Invalid arguments - Different properties in the objects.");
        }
    })

    test("Test with different _id values", () => {
        const a = {
            "_id": 1,
            "name": "Bruce Norris"
        }

        const b = {
            "_id": 6,
            "wrongName": "Bruce Willis"
        }

        try {
            compare(a, b)
        } catch (e) {
            expect(e).toBe("Invalid arguments - Different properties in the objects.");
        }
    })
})
