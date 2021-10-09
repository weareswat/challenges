import Grid from '@mui/material/Grid'
import TableContainer from '@mui/material/TableContainer'
import Table from '@mui/material/Table'
import TableBody from '@mui/material/TableBody'
import TableCell from '@mui/material/TableCell'
import TableHead from '@mui/material/TableHead'
import TableRow from '@mui/material/TableRow'
import Paper from '@mui/material/Paper'
import PropTypes from 'prop-types'

function MyTable({ items }) {
  if (!items || items.length === 0) return null

  return (
    <Grid item xs={12} data-testid="my-table">
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              <TableCell>Document status</TableCell>
              <TableCell>Document type</TableCell>
              <TableCell>Number</TableCell>
              <TableCell>Client name</TableCell>
              <TableCell>Date</TableCell>
              <TableCell>Total without VAT</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {items.map((row, index) => (
              <TableRow
                key={`my-table-${index}`}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell>{row.status}</TableCell>
                <TableCell>{row.type}</TableCell>
                <TableCell>{row.number}</TableCell>
                <TableCell>{row.client_name}</TableCell>
                <TableCell>{row.date}</TableCell>
                <TableCell>{row.total_w_vat}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Grid>
  )
}

MyTable.propTypes = {
  items: PropTypes.array,
}

MyTable.defaultProps = {
  items: [],
}

export default MyTable