import documentItems from '../../public/documents.json'

export default function handler(req, res) {
  res.status(200).json(documentItems)
}
