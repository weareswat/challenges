import Grid from '@mui/material/Grid'
import Button from '@mui/material/Button'
import FormControl from '@mui/material/FormControl'
import InputLabel from '@mui/material/InputLabel'
import Select from '@mui/material/Select'
import MenuItem from '@mui/material/MenuItem'
import TextField from '@mui/material/TextField'
import PropTypes from 'prop-types'
import { useFormik } from 'formik'

function MyTableFilter({ onSubmit }) {
  const formik = useFormik({
    initialValues: {
      status: '',
      type: '',
      number: '',
      client_name: '',
      date: '',
      total_w_vat: '',
    },
    onSubmit: (data) => onSubmit(data),
  })

  return (
    <Grid item xs={12} data-testid="my-table-filter">
      <h2>Filter fields:</h2>

      <form onSubmit={formik.handleSubmit}>
        <Grid container spacing={2}>
          <Grid item md={4} sm={6} xs={12}>
            <FormControl sx={{ width: '100%' }} variant="standard">
              <InputLabel id="document-status-label">Document status</InputLabel>
              <Select
                size="small"
                labelId="document-status-label"
                id="document-status"
                label="Document status"
                onChange={formik.handleChange}
                value={formik.values.status}
                name="status"
                data-testid="status"
              >
                <MenuItem value=""><em>None</em></MenuItem>
                <MenuItem value={'Draft'}>Draft</MenuItem>
                <MenuItem value={'Final'}>Final</MenuItem>
                <MenuItem value={'Paid'}>Paid</MenuItem>
                <MenuItem value={'Cancelled'}>Cancelled</MenuItem>
              </Select>
            </FormControl>
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <FormControl sx={{ width: '100%' }} variant="standard">
              <InputLabel id="document-type-label">Document type</InputLabel>
              <Select
                size="small"
                labelId="document-type-label"
                id="document-type"
                label="Document type"
                onChange={formik.handleChange}
                value={formik.values.type}
                name="type"
                data-testid="type"
              >
                <MenuItem value="">
                  <em>None</em>
                </MenuItem>
                <MenuItem value={'Invoice'}>Invoice</MenuItem>
                <MenuItem value={'Invoice-Receipt'}>Invoice-Receipt</MenuItem>
                <MenuItem value={'Receipt'}>Receipt</MenuItem>
              </Select>
            </FormControl>
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <TextField
              id="number"
              label="Number"
              variant="standard"
              onChange={formik.handleChange}
              value={formik.values.number}
              size="small"
              data-testid="number"
              sx={{ width: '100%' }}
            />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <TextField
              id="client_name"
              label="Client name"
              variant="standard"
              onChange={formik.handleChange}
              value={formik.values.client_name}
              size="small"
              data-testid="client_name"
              name="client_name"
              sx={{ width: '100%' }}
            />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <TextField
              id="date"
              label="Date"
              variant="standard"
              onChange={formik.handleChange}
              value={formik.values.date}
              size="small"
              type="date"
              name="date"
              data-testid="date"
              sx={{ width: '100%' }}
            />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <TextField
              id="total_w_vat"
              name="total_w_vat"
              data-testid="total_w_vat"
              label="Total without VAT"
              variant="standard"
              onChange={formik.handleChange}
              value={formik.values.total_w_vat}
              size="small"
              type="number"
              sx={{ width: '100%' }}
            />
          </Grid>
        </Grid>

        <div style={{ marginTop: 15 }}>
          <Button variant="contained" type="submit">
            Submit
          </Button>
        </div>
      </form>
    </Grid>
  )
}

MyTableFilter.propTypes = {
  onSubmit: PropTypes.func,
}

MyTableFilter.defaultProps = {
  onSubmit: () => {},
}

export default MyTableFilter