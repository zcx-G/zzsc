function loginApi(data) {
  return $axios({
    'url': 'http://localhost/loginServlet',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': 'http://localhost/logoutServlet',
    'method': 'post',
  })
}
