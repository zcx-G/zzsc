//获取所有地址
function addressListApi() {
    return $axios({
      'url': '/address',
      'method': 'get',
    })
  }

//新增地址
function  addAddressApi(data){
    return $axios({
        'url': '/address',
        'method': 'post',
        data
      })
}

//修改地址
function  updateAddressApi(data){
    return $axios({
        'url': '/address',
        'method': 'put',
        data
      })
}

//删除地址
function deleteAddressApi(id) {
    return $axios({
        'url': `/address/${id}`,
        'method': 'delete'
    })
}

//查询单个地址
function selectByIdApi(id) {
  return $axios({
        'url': `/address/${id}`,
        'method': 'get'
  })
}

//设置默认地址
function  setDefaultAddressApi(id){
  return $axios({
      'url': `/address/${id}`,
      'method': 'put'

    })
}

//获取默认地址
function getDefaultAddressApi() {
  return $axios({
    'url': `/address/Default`,
    'method': 'get',
  })
}