const tagList = {
  'code': 200,
  'message': '操作成功！',
  'data': {
    'list': [
      {
        'id': 1,
        'name': '随笔', // 标签名称
        'count': 10 // 文章数量
      },
      {
        'id': 2,
        'name': 'Database', // 标签名称
        'count': 10 // 文章数量
      },
      {
        'id': 3,
        'name': 'Java', // 标签名称
        'count': 10 // 文章数量
      },
      {
        'id': 4,
        'name': 'Java', // 标签名称
        'count': 10 // 文章数量
      },
      {
        'id': 5,
        'name': 'Java', // 标签名称
        'count': 10 // 文章数量
      }
    ]
  }
}

export const getTagList = req => {
  return tagList
}
