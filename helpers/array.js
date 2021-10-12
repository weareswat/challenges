const arrayFilter = (array, filter) => {
  try {
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
  } catch {
    return []
  }
}

const clearEmptyFields = (object) => {
  try {
    const params = {}
    const keys = Object.keys(object)
    keys.forEach(key => {
      const value = object[key]
      if (value) params[key] = value
    })

    return params
  } catch {
    return {}
  }
}

const arraySplitByItemNumber = (array, number) => {
  try {
    return new Array(Math.ceil(array.length / +number))
      .fill()
      .map(_ => array.splice(0, +number))
  } catch {
    return []
  }
}

export {
  arrayFilter,
  clearEmptyFields,
  arraySplitByItemNumber,
}
