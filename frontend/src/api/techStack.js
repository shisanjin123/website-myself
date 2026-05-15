import request from '../utils/request'

export function getTechStacks() {
  return request.get('/tech-stack')
}

export function addTechStack(data) {
  return request.post('/admin/tech-stack', data)
}

export function updateTechStack(id, data) {
  return request.put(`/admin/tech-stack/${id}`, data)
}

export function deleteTechStack(id) {
  return request.delete(`/admin/tech-stack/${id}`)
}
