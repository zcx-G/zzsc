//获取所有地址
function addressListApi() {
    return $axios({
      'url': '/address/list',
      'method': 'get',
    })
  }

//获取最新地址
function addressLastUpdateApi() {
    return $axios({
      'url': '/address/lastUpdate',
      'method': 'get',
    })
}

//新增地址
function  addAddressApi(data){
    return $axios({
        'url': '/address/save',
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
function deleteAddressApi(params) {
    return $axios({
        'url': '/address',
        'method': 'delete',
        params
    })
}

//查询单个地址
function addressFindOneApi(id) {
  return $axios({
    'url': `/address/${id}`,
    'method': 'get',
  })
}

//设置默认地址
function  setDefaultAddressApi(id){
  return $axios({
      'url': '/address/Default?id='+id,
      'method': 'get',
    })
}

//获取默认地址
function getDefaultAddressApi() {
  return $axios({
    'url': `/address/Default`,
    'method': 'get',
  })
}