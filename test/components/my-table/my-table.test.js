/**
 * @jest-environment jsdom
 */

import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'

import MyTable from '@components/my-table'

test('loads and displays Nothing', async () => {
  render(<MyTable items={[]} />)

  expect(screen.queryByTestId('my-table')).toBeNull()
})

test('loads and displays 3 items', async () => {
  const items = [
    { status: "Final", type: "Invoice", number: "2019/11", client_name: "Elon Tusk 1", date: "2019-02-31", total_w_vat: 123.42 },
    { status: "Final", type: "Invoice", number: "2019/11", client_name: "Elon Tusk 2", date: "2019-02-31", total_w_vat: 123.42 },
    { status: "Final", type: "Invoice", number: "2019/11", client_name: "Elon Tusk 3", date: "2019-02-31", total_w_vat: 123.42 },
  ];
  render(<MyTable items={items} />)

  expect(screen.queryByTestId('my-table')).not.toBeNull()

  expect(screen.findAllByRole('tr')).not.toBeNull()
  items.forEach(item => {
    expect(screen.queryByText(item.client_name)).not.toBeNull()
  })
})

test('changes page state when click on pagination button', async () => {
  const items = [];
  for (let i = 0; i < 26; i++) {
    items.push({ status: "Final", type: "Invoice", number: "2019/11", client_name: `Elon Tusk ${i}`, date: "2019-02-31", total_w_vat: 123.42 + i });
  }

  const { debug } = render(<MyTable items={items} />)
  const button = screen.getByRole('button', { name: /next page/i })
  const pagination = screen.getByTestId('my-table-pagination').querySelector('p')

  expect(button).not.toBeNull()
  expect(pagination).not.toBeNull()

  userEvent.click(button)
  userEvent.click(button)

  expect(screen.getByTestId('my-table-pagination').querySelector('p').textContent).toBe('8-14 of 26')
})
