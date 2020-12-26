const db = require('./db')
const { getRespObj } = require("../utils/utils");
const { compare, retrieveBetweenDatesAndID } = require("../utils/compare");

module.exports = {
    createChanges: createChanges,
    getChanges: getChanges
}

function createChanges(obj1, obj2) {
    return new Promise(function(resolve, reject) {
        try {
            const diff = compare(obj1, obj2)
            return db.createChanges(diff)
                .then(respObj => resolve(respObj))
                .catch(errObj => reject(errObj))
        } catch (error) {
            reject(getRespObj(error + "."))
        }
    })
}

function getChanges(id, minDate, maxDate) {
    return new Promise(function(resolve, reject) {
        return db.getChanges(id, minDate, maxDate)
            .then(respObj => {
                respObj.body = retrieveBetweenDatesAndID(respObj.body, id, minDate, maxDate)
                resolve(respObj)
            })
            .catch(errObj => reject(errObj))
    })
}

