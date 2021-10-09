import documentItems from '../../documents.json'

export default function handler(req, res) {
  res.status(200).json(documentItems)
}
