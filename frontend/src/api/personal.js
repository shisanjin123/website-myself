import request from '../utils/request'

export function getPersonalIntro() {
  return request.get('/personal')
}

export function updatePersonalIntro(data) {
  return request.put('/admin/personal', data)
}
