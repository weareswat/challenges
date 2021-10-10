import axios from 'axios'

export default class BaseService {
  pathPrefix = '/api'
  path = '/base'

  async getAll(params) {
    return axios.get(this.#buildUrl(), { params })
  }

  #buildUrl() {
    return `${process.env.API_HOST}${this.pathPrefix}${this.path}`
  }
}