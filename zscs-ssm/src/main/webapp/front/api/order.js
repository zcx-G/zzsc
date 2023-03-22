//提交订单
function  addOrderApi(data){
    return $axios({
        'url': '/orders',
        'method': 'post',
        data
      })
}

//查询所有订单
function orderListApi() {
  return $axios({
    'url': '/orders/list',
    'method': 'get',
  })
}

//分页查询订单
function orderPagingApi(data) {
  return $axios({
      'url': '/orders',
      'method': 'get',
      params:{...data}
  })
}


//查询订单详情
function orderDetailListApi(id) {
    console.log(id)
    return $axios({
        'url': `/orders/${id}`,
        'method': 'get',
    })
}

//再来一单
function orderAgainApi(data) {
  return $axios({
      'url': '/orders/again',
      'method': 'post',
      data
  })
}