# Documents Context

Let's get started:

```JSX
import { DocumentsContextProvider } from 'contexts'

<DocumentsContextProvider>
  <YourComponent />
</DocumentsContextProvider>
```

Inside your component:

```JSX
import { useContext } from 'react'
import { DocumentsContext } from 'contexts'

function YourComponent() {
  // state has the values of documents context inside
  const { state, dispatch } = useContext(DocumentsContext)

  return(
    {state.documents.map(document => (<div>{document.client_name}</div>))}
  )
}

```

This is the initial state and you can update its value using the dispatch function

```JSX
const initialState = {
  documents: [],
  filter: {},
  loading: false,
  error: false,
}
```

How to use the dispatcher:

```JSX
// inside your component call useContext
const { dispatch } = useContext(DocumentsContext)

// update the documents array state by the payload value
dispatch({ type: 'setDocuments', payload: [] })

// update the filter object state by the payload value
dispatch({ type: 'setFilter', payload: {} })

// toggle loading state
dispatch({ type: 'loadingToggle' })

// toggle error state
dispatch({ type: 'errorToggle' })
```
