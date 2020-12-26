const SERVER_HOST = "localhost"
const SERVER_PORT = 8080
const SERVER_URI = `http://${SERVER_HOST}:${SERVER_PORT}`

const getURL = (id, minDate, maxDate) => `${SERVER_URI}/getChanges/${id}&${minDate}&${maxDate}`
const postURL = () => `${SERVER_URI}/createChanges`

/**
 * Returns a response object
 */
function getRespObj(message, body = {}) {
    return {
        'message': message,
        'body': body
    }
}

module.exports = {
    getURL,
    postURL,
    SERVER_PORT,
    getRespObj
}
