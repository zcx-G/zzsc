// 查询列表接口
const getGoodPage = (params) => {
  return $axios({
    url: '/good/page',
    method: 'get',
    params
  })
}

// 删除接口
const deleteGood = (ids) => {
  return $axios({
    url: '/good/delete',
    method: 'post',
    // params: { ids }
    data: ids
  })
}

// 修改接口
const editGood = (params) => {
  return $axios({
    url: '/good/update',
    method: 'post',
    data: { ...params }
  })
}

// 新增接口
const addGood = (params) => {
  return $axios({
    url: '/good/add',
    method: 'post',
    data: { ...params }
  })
}

// 查询详情
const queryGoodById = (id) => {
  return $axios({
    url: `/good/selectById?id=${id}`,
    method: 'get'
  })
}

// 获取菜品分类列表
const getCategoryList = (params) => {
  return $axios({
    url: '/category/list',
    method: 'get',
    params
  })
}

// 查菜品列表的接口
const queryGoodList = (params) => {
  return $axios({
    url: '/good/list',
    method: 'get',
    params
  })
}

// 文件down预览
const commonDownload = (params) => {
  return $axios({
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    url: '/common/download',
    method: 'get',
    params
  })
}

// 起售停售接口
const goodStatusByStatus = (params) => {
  return $axios({
    url: `/good/status?status=`+params.status,
    method: 'post',
    data: Array.isArray(params.id)? params.id :[params.id]
  })
}