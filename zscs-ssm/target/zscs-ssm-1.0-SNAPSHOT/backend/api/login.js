function loginApi(data) {
  return $axios({
    'url': '/roots',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': '/roots',
    'method': 'get',
  })
}
