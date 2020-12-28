const db = require('./db-utils')
const {getRespObj} = require("../utils/utils");

module.exports = {
    createChanges: createChanges,
    getChanges: getChanges
}

/**
 * Inserts the object received as a parameter in the database
 */
function createChanges(obj) {
    return new Promise(function(resolve, reject) {
        try {
            let promises = []
            for (const diffObj of obj.diffObject) {
                promises.push(db.query(
                `insert into changes (userID, diff) values($1, $2) returning *`,
                [obj._id, JSON.stringify(diffObj)],
                function (err, result)  {}).then(response => {
                    return Promise.resolve(response.rows[0])
                }))
            }
            Promise.all(promises).then(values => {
                resolve(getRespObj("Values created successfully.", values))
            });
        } catch (error) {
            reject(getRespObj(error + "."))
        }
    })
}

/**
 * Returns an array of objects, where each object's 'id' matches the parameter 'id' and
 * its 'date' is between 'minDate' and 'maxDate'
 */
function getChanges(id, minDate, maxDate) {
    return new Promise(function(resolve, reject) {
        try {
            db.query(
                `
                select uuid, userID, date, diff ->> 'field' as field, diff ->> 'old' as old, diff ->> 'new' as new 
                from changes
                where userID = $1 and date > $2 and date < $3`, [id, minDate, maxDate])
                .then(rsp => resolve(getRespObj("Values retrieved successfully.", rsp.rows)))
                .catch(err => reject(getRespObj(err + "."))
            )
        } catch (error) {
            reject(getRespObj(error + "."))
        }
    })
}
