function sendMsgApi(phone) {
    return $axios({
        'url': '/user/${phone}',
        'method': 'get',
    })
}

function loginApi(params) {
    return $axios({
      'url': '/user',
      'method': 'get',
        params
    })
  }

function loginoutApi() {
  return $axios({
    'url': '/user',
    'method': 'put',
  })
}

  