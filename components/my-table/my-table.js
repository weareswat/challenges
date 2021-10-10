import { useEffect, useState } from 'react'
import Grid from '@mui/material/Grid'
import TableContainer from '@mui/material/TableContainer'
import Table from '@mui/material/Table'
import TableBody from '@mui/material/TableBody'
import TableCell from '@mui/material/TableCell'
import TableHead from '@mui/material/TableHead'
import TableRow from '@mui/material/TableRow'
import TableFooter from '@mui/material/TableFooter'
import TablePagination from '@mui/material/TablePagination'
import Paper from '@mui/material/Paper'
import PropTypes from 'prop-types'

import { arraySplitByItemNumber } from '../../helpers'

function MyTable({ items }) {
  const [itemsToShow, setItemsToShow] = useState([])
  const [page, setPage] = useState(0)

  const handleChangePage = (event, newPage) => setPage(+newPage)

  useEffect(() => {
    if (items && items.length > 0) {
      setPage(0)
      const newItems = [...items]
      const splitedArray = arraySplitByItemNumber(newItems, 7)
      const [result] = splitedArray
      setItemsToShow(result)
    }
  }, [items])

  useEffect(() => {
    if (items && items.length > 0 && page >= 0) {
      const newItems = [...items]
      const splitedArray = arraySplitByItemNumber(newItems, 7)
      setItemsToShow(splitedArray[page])
    }
  }, [page, items])

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
            {itemsToShow && itemsToShow.map((row, index) => (
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
          <TableFooter>
            <TableRow>
              <TablePagination
                rowsPerPageOptions={[7]}
                colSpan={3}
                count={items.length}
                rowsPerPage={7}
                page={page}
                SelectProps={{
                  inputProps: {
                    'aria-label': 'rows per page',
                  },
                  native: true,
                }}
                onPageChange={handleChangePage}
              />
            </TableRow>
          </TableFooter>
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