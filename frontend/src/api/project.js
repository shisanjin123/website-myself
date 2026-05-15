import request from '../utils/request'

export function getProjects() {
  return request.get('/projects')
}

export function getProject(id) {
  return request.get(`/projects/${id}`)
}

export function addProject(data) {
  return request.post('/admin/projects', data)
}

export function updateProject(id, data) {
  return request.put(`/admin/projects/${id}`, data)
}

export function deleteProject(id) {
  return request.delete(`/admin/projects/${id}`)
}
