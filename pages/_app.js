import Head from 'next/head'
import Grid from '@mui/material/Grid'
import Container from '@mui/material/Container'
import CssBaseline from '@mui/material/CssBaseline'

import { DocumentsContextProvider } from 'contexts'

import '../styles/globals.scss'

function MyApp({ Component, pageProps }) {
  return (
    <>
      <Head>
        <title>InvoiceExpress React Test</title>
        <link rel="icon" href="/favicon.ico" />
        <meta name="viewport" content="initial-scale=1, width=device-width" key="material-ui-viewport" />
      </Head>
      <CssBaseline />

      <DocumentsContextProvider>
        <Container>
          <Grid container spacing={2}>
            <Component {...pageProps} />
          </Grid>
        </Container>
      </DocumentsContextProvider>
    </>
  )
}

export default MyApp
