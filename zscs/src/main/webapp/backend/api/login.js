function loginApi(data) {
  return $axios({
    'url': '/root/login',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': '/root/logout',
    'method': 'post',
  })
}
