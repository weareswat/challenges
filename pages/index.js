
import { useEffect, useContext, useState } from 'react'
import Grid from '@mui/material/Grid'
import Backdrop from '@mui/material/Backdrop'
import CircularProgress from '@mui/material/CircularProgress'

import { MyTable, MyTableFilter } from '../components'
import { DocumentService } from '../services'
import { DocumentsContext } from '../contexts'
import { arrayFilter } from '../helpers'

import styles from '../styles/Home.module.scss'

export default function Home() {
  const [documents, setDocuments] = useState([])
  const { dispatch, state } = useContext(DocumentsContext)

  const getData = async () => {
    try {
      dispatch({ type: 'loadingToggle' })
      const documentService = new DocumentService()
      const { data } = await documentService.getAll()
      const payload = data?.documents || [];
      dispatch({ type: 'setDocuments', payload })
      setDocuments(payload)
    } catch (e) {
      console.log('Error', e)
    }

    dispatch({ type: 'loadingToggle' })
  }

  useEffect(() => {
    getData()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  const onSubmitFilter = (values) => {
    dispatch({ type: 'loadingToggle' })
    dispatch({ type: 'setFilter', payload: values || {} })

    const result = arrayFilter(state.documents, values)
    setDocuments(result)

    dispatch({ type: 'loadingToggle' })
  }

  return (
    <>
      <Grid item xs={12}>
        <h1>Invoices list</h1>
      </Grid>

      <MyTableFilter onSubmit={onSubmitFilter} />
      <MyTable items={documents} />

      <Backdrop
        sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={state.loading}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
    </>
  )
}
