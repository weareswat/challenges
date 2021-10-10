/**
 * @jest-environment jsdom
 */

import { arrayFilter, arraySplitByItemNumber, clearEmptyFields } from '@helpers/array'

 describe('#arrayFilter', () => {
   it('should return 1 item', () => {
      const items = [{client_name: 'tusk'}, {client_name: 'tisk'}, {client_name: 'tesk'}]
      const filter = { client_name: 'tusk' }
      const result = arrayFilter(items, filter)

      expect(result).not.toBeNull()
      expect(result.length).toBe(1)
   })

   it('should return zero items', () => {
      const items = [{client_name: 'tusk'}, {client_name: 'tisk'}, {client_name: 'tesk'}]
      const filter = { client_name: 'abc' }
      const result = arrayFilter(items, filter)

      expect(result).not.toBeNull()
      expect(result.length).toBe(0)
   })
 })

 describe('#clearEmptyFields', () => {
   it('should return a empty object', () => {
      const param = { client_name: '', other_param: '', email: '' }
      const result = clearEmptyFields(param)

      expect(result).not.toBeNull()
      expect(result).toMatchObject({})
   })

   it('should return "{ client_name: \'tusk\' }"', () => {
    const param = { client_name: 'tusk', other_param: '', email: '' }
    const result = clearEmptyFields(param)

    expect(result).not.toBeNull()
    expect(result).toMatchObject({ client_name: 'tusk' })
   })
 })

 describe('#arraySplitByItemNumber', () => {
   it('should return a array with 4 items', () => {
      const param = [0,0,0,0,0,0,0,0]
      const result = arraySplitByItemNumber(param, 2)

      expect(result).not.toBeNull()
      expect(result.length).toBe(4)
   })
 })
