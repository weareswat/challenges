const service = require('./comparatorService')

/**
 * /createChanges
 */
function createChanges(req, rsp) {
    service.createChanges(req.body.obj1, req.body.obj2)
        .then(respObj => {
            rsp.statusCode = 201
            rsp.json(respObj)
        })
        .catch(errObj => {
            rsp.statusCode = 400
            rsp.json(errObj)
        })
}

/**
 * /getChanges/:id&:minDate&:maxDate
 */
function getChanges(req, rsp) {
    service.getChanges(req.params.id, req.params.minDate, req.params.maxDate)
        .then(respObj => {
            rsp.statusCode = 200
            rsp.json(respObj)
        })
        .catch(errObj => {
            rsp.statusCode = 400
            rsp.json(errObj)
        })
}

module.exports = {
    createChanges: createChanges,
    getChanges: getChanges
}
