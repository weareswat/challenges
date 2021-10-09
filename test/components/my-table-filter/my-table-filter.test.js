/**
 * @jest-environment jsdom
 */

import { render, screen, waitFor } from '@testing-library/react'
import userEvent from '@testing-library/user-event'

import { MyTableFilter } from '@components'

test('loads and displays the form', async () => {
  const namesToFind = [
    'Document status',
    'Document type',
    'Number',
    'Client name',
    'Date',
    'Total without VAT',
  ]
  render(<MyTableFilter />)

  expect(screen.queryByTestId('my-table-filter')).not.toBeNull()
  namesToFind.forEach(name => {
    expect(screen.getByText(name)).not.toBeNull()
  })
})

test('on click in submit button call onSubmit prop', async () => {
  const handleOnSubmit = jest.fn()
  render(<MyTableFilter onSubmit={handleOnSubmit} />)

  expect(screen.getByText('Submit')).not.toBeNull()
  userEvent.click(screen.getByText('Submit'))
  await waitFor(() =>
    expect(handleOnSubmit).toHaveBeenCalledWith({
      client_name: '',
      date: '',
      number: '',
      status: '',
      total_w_vat: '',
      type: '',
    }),
  )
})


