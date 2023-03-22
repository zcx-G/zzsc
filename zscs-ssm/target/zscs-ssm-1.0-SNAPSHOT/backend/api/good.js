// 查询列表接口
const getGoodPage = (params) => {
  return $axios({
    url: '/goods/page',
    method: 'get',
    params
  })
}

// 删除接口
const deleteGood = (ids) => {
  return $axios({
    url: '/goods',
    method: 'delete',
    data:ids
  })
}

// 修改接口
const editGood = (params) => {
  return $axios({
    url: '/goods',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addGood = (params) => {
  return $axios({
    url: '/goods',
    method: 'post',
    data: { ...params }
  })
}

// 查询详情
const queryGoodById = (id) => {
  return $axios({
    url: `/goods/${id}`,
    method: 'get'
  })
}

// 获取菜品分类列表
const getCategoryList = (params) => {
  return $axios({
    url: '/categorys/list',
    method: 'get',
    params
  })
}

// 查菜品列表的接口
const queryGoodList = (params) => {
  return $axios({
    url: '/goods',
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
    url: '/file/download',
    method: 'get',
    params
  })
}

// 起售停售接口
const goodStatusByStatus = (params) => {
  console.log(params.id)
    return $axios({
    url: `/goods/${params.status}`,
    method: 'put',
    data:params.id
  })
}