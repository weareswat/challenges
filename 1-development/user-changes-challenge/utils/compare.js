function compare(obj1, obj2) {
    return {
        "_id": obj1['_id'],
        "diffObject": compareWithAccumulator(obj1, obj2, [], "")
    }
}

/**
 * It's assumed that both objects have a property named _id
 *
 * @param obj1 - first object to compare
 * @param obj2 - second object to compare
 * @param accumulator - accumulator for the results
 * @param propertyFullPath - full path of a property
 * @returns array with multiple objects, each containing a difference between two objects
 */
function compareWithAccumulator(obj1, obj2, accumulator, propertyFullPath) {
    Object.keys(obj1).forEach(key => {
        if (!compareKeys(obj1, obj2))
            throw 'Invalid arguments - Different properties in the objects.'

        if (obj1['_id'] !== obj2['_id'])
            throw 'Invalid arguments - _id property must have the same value in both objects.'

        if (obj1[key] == null || obj2[key] == null)
            throw 'Invalid arguments - Null value.'

        if (isObject(obj1[key])) {
            compareWithAccumulator(
                obj1[key],
                obj2[key],
                accumulator,
                propertyFullPath + key + ".")
        }

        else if (obj2[key] !== obj1[key]) {
            let diffObject = createDiffObject(propertyFullPath + key, obj1[key], obj2[key])
            accumulator.push(diffObject)
        }
    })

    return accumulator
}

/**
 *
 * @param array of objects
 * @param id of objects to return
 * @param minDate minimum date to use in the filtering
 * @param maxDate maximum date to use in the filtering
 * @returns array of objects, each containing a difference between a property across time
 */
function retrieveBetweenDatesAndID(array, userID, minDate, maxDate) {
    const objectsInRangeOfDate = array.filter(elem =>
            elem.userid === parseInt(userID) &&
            elem.date > new Date(minDate) &&
            elem.date < new Date(maxDate)
    )

    const fields = [...new Set(objectsInRangeOfDate.map(item => item.field))]

    let result = []
    fields.forEach(field => {
        const objsWithThisField = objectsInRangeOfDate.filter(elem => elem.field === field)

        let { minDate, maxDate} = getDates(objsWithThisField)
        let oldValue = objsWithThisField.find(obj => obj.date === minDate).old
        let newValue = objsWithThisField.find(obj => obj.date === maxDate).new

        result.push(createDiffObject(field, oldValue, newValue))
    })

    return result
}

/**
 * HELPER FUNCTIONS
 */

/**
 * Returns an object base on the parameters
 */
function createDiffObject(fieldName, oldValue, newValue) {
    return {
        'field': fieldName,
        'old': oldValue,
        'new': newValue
    }
}

/**
 * Returns whether the object is of type 'object' and its value is not null
 */
function isObject(obj) {
    return typeof obj === 'object' && obj !== null
}

/**
 * Returns a minimum and maximum dates of objects inside an array
 */
function getDates(array) {
    return {
        "minDate": array.reduce((a,b) => a.date < b.date ? a : b).date,
        "maxDate": array.reduce((a,b) => a.date > b.date ? a : b).date
    };
}

/**
 * Returns whether both objects have the same keys or not
 */
function compareKeys(a, b) {
    let aKeys = Object.keys(a).sort();
    let bKeys = Object.keys(b).sort();
    return JSON.stringify(aKeys) === JSON.stringify(bKeys);
}

module.exports = { compare, retrieveBetweenDatesAndID }
