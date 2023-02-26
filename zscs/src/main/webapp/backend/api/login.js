function loginApi(data) {
  return $axios({
    'url': 'http://localhost/root/login',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': 'http://localhost/root/logout',
    'method': 'post',
  })
}
