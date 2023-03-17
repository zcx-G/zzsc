//获取所有地址
function addressListApi() {
    return $axios({
      'url': '/address/list',
      'method': 'get',
    })
  }

//新增地址
function  addAddressApi(data){
    return $axios({
        'url': '/address/add',
        'method': 'post',
        data
      })
}

//修改地址
function  updateAddressApi(data){
    return $axios({
        'url': '/address/update',
        'method': 'post',
        data
      })
}

//删除地址
function deleteAddressApi(params) {
    return $axios({
        'url': '/address/delete',
        'method': 'get',
        params
    })
}

//查询单个地址
function selectByIdApi(params) {
  return $axios({
        'url': `/address/selectById`,
        'method': 'get',
         params
  })
}

//设置默认地址
function  setDefaultAddressApi(params){
  return $axios({
      'url': '/address/Default',
      'method': 'get',
      params
    })
}

//获取默认地址
function getDefaultAddressApi() {
  return $axios({
    'url': `/address/Default`,
    'method': 'get',
  })
}