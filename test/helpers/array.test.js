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

   it('should return "[]"', () => {
      const items = [{client_name: 'tusk'}, {client_name: 'tisk'}, {client_name: 'tesk'}]
      const filter = { client_name: 'abc' }
      const result = arrayFilter(items, filter)

      expect(result).not.toBeNull()
      expect(result.length).toBe(0)
   })

   it('should return "[]" when pass wrong params', () => {
      const items = 'items'
      const filter = null
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

   it('should return "{}" when pass wrong params', () => {
    const param = 'null'
    const result = clearEmptyFields(param)

    expect(result).not.toBeNull()
    expect(result).toMatchObject({})
   })
 })

 describe('#arraySplitByItemNumber', () => {
   it('should return a array with 4 items', () => {
      const param = [0,0,0,0,0,0,0,0]
      const result = arraySplitByItemNumber(param, 2)

      expect(result).not.toBeNull()
      expect(result.length).toBe(4)
   })

   it('should return a "[]" when pass wrong params', () => {
      const param = '[]'
      const result = arraySplitByItemNumber(param, null)

      expect(result).not.toBeNull()
      expect(result.length).toBe(0)
   })
 })
