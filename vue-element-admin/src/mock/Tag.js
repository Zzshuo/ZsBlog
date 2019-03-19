const tag = {
  'code': 200,
  'message': '操作成功！',
  'data': {
    'id': 1,
    'name': '随笔', // 标签名称
    'count': 10 // 文章数量
  }
}

export const getTag = req => {
  return tag
}
