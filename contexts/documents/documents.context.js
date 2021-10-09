import { createContext, useReducer } from 'react'

import { reducer, initialState } from './documents.reducer'

const DocumentsContext = createContext({});

function DocumentsContextProvider({ children }) {
  const [state, dispatch] = useReducer(reducer, initialState);

  return (
    <DocumentsContext.Provider value={{ dispatch, state }}>
      {children}
    </DocumentsContext.Provider>
  )
}

export { DocumentsContextProvider, DocumentsContext }