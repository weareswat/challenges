const arrayFilter = (array, filter) => {
  const params = clearEmptyFields(filter)
  if (params) {
    let keys = Object.keys(params)
    const result = array.filter(doc => {
      let isValid = true
      for (let i = 0; i < keys.length; i++) {
        const key = keys[i];
        if (key === 'client_name') {
          const re = new RegExp(`${params[key]}`, 'g')
          if (!re.test(doc[key])) {
            isValid = false
            break
          }
        } else {
          if (params[key] !== doc[key]) {
            isValid = false
            break
          }
        }
      }
      return isValid
    })

    return result
  }

  return array
}

const clearEmptyFields = (object) => {
  const params = {}
  const keys = Object.keys(object)
  keys.forEach(key => {
    const value = object[key]
    if (value) params[key] = value
  })

  return params
}

const arraySplitByItemNumber = (array, number) =>
  new Array(Math.ceil(array.length / +number))
    .fill()
    .map(_ => array.splice(0, +number))

export {
  arrayFilter,
  clearEmptyFields,
  arraySplitByItemNumber,
}
