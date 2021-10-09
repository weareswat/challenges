const initialState = {
  documents: [],
  loading: false,
  error: false,
}

function reducer(state, action) {
  switch (action.type) {
    case 'setDocuments':
      return { ...state, documents: action.payload }
    case 'loadingToggle':
      return { ...state, loading: !state.loading }
    case 'errorToggle':
      return { ...state, error: !state.error }
    default:
      throw new Error()
  }
}

export { reducer, initialState }
