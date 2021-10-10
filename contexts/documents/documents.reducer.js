const initialState = {
  documents: [],
  filter: {},
  loading: false,
  error: false,
}

function reducer(state, action) {
  switch (action.type) {
    case 'setDocuments':
      return { ...state, documents: action.payload }
    case 'setFilter':
      return { ...state, filter: action.payload }
    case 'loadingToggle':
      return { ...state, loading: !state.loading }
    case 'errorToggle':
      return { ...state, error: !state.error }
    default:
      throw new Error()
  }
}

export { reducer, initialState }
