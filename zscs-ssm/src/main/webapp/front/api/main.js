//获取所有的商品分类
function categoryListApi() {
    return $axios({
      'url': '/categorys/list',
      'method': 'get',
    })
  }

//获取商品分类对应的商品
function goodListApi(params) {
    return $axios({
        'url': '/goods/categoryList',
        'method': 'get',
        params
    })
}


//获取购物车内商品的集合
function cartListApi(data) {
    return $axios({
        'url': '/shoppingCart',
        'method': 'get',
        params:{...data}
    })
}

//购物车中添加商品
function  addCartApi(data){
    return $axios({
        'url': '/shoppingCart',
        'method': 'post',
        data
      })
}

//购物车中修改商品
function  updateCartApi(data){
    return $axios({
        'url': '/shoppingCart',
        'method': 'put',
        data
      })
}

//删除购物车的商品
function clearCartApi() {
    return $axios({
        'url': '/shoppingCart',
        'method': 'delete',
    })
}



