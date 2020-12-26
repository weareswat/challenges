const webApi = require('./webApi')
const express = require('express')
const app = express()
const utils = require('../utils/utils')

const PORT = utils.SERVER_PORT

app.use(express.json())

app.post('/createChanges', webApi.createChanges)
app.get('/getChanges/:id&:minDate&:maxDate', webApi.getChanges)

app.listen(PORT, () => console.log(`listening on port ${PORT} ...`))
