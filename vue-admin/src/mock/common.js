export default {
  getArticleStateMap: req => {
    return getArticleStateMap
  }
}

const getArticleStateMap = {
  'code': 20000,
  'message': '操作成功！',
  'data': {
    '1': '发布',
    '2': '草稿',
    '3': '删除'
  }
}
