# Helpers

## Array Helpers

### arrayFilter

```JSX
const items = [
  {client_name: 'tusk'},
  {client_name: 'tisk'},
  {client_name: 'tesk'},
]
const filter = { client_name: 'tusk' }
arrayFilter(items, filter) // returns [{client_name: 'tusk'}]
```

### clearEmptyFields

```JSX
const param = {
  client_name: 'tusk',
  other_param: '',
  email: '',
}
clearEmptyFields(param) // returns { client_name: 'tusk' }
```

### arraySplitByItemNumber

```JSX
const param = [0,0,0,0,0,0,0,0]
arraySplitByItemNumber(param, 2)
// returns:
// [
//   [0,0],
//   [0,0],
//   [0,0],
//   [0,0],
// ]
```
