export default {
  getTagById: req => {
    return getTagById
  },
  getTagList: req => {
    return tagList
  },
  getAllTag: req => {
    return allTags
  }
}

const getTagById = {
  'code': 20000,
  'message': '操作成功！',
  'data': {
    'id': 1,
    'name': '随笔', // 标签名称
    'count': 10 // 文章数量
  }
}

const tagList = {
  'code': 20000,
  'message': '操作成功！',
  'data': {
    'total': 33,
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
        'name': 'Java1', // 标签名称
        'count': 10 // 文章数量
      },
      {
        'id': 4,
        'name': 'Java2', // 标签名称
        'count': 10 // 文章数量
      },
      {
        'id': 5,
        'name': 'Java3', // 标签名称
        'count': 10 // 文章数量
      }
    ],
    'pageNum': 1,
    'pageSize': 10,
    'size': 10,
    'startRow': 1,
    'endRow': 10,
    'pages': 4,
    'prePage': 0,
    'nextPage': 2,
    'isFirstPage': true,
    'isLastPage': false,
    'hasPreviousPage': false,
    'hasNextPage': true,
    'navigatePages': 8,
    'navigatepageNums': [
      1,
      2,
      3,
      4
    ],
    'navigateFirstPage': 1,
    'navigateLastPage': 4
  }
}

const allTags = {
  'code': 20000,
  'message': '操作成功！',
  'data': [
    {
      'id': 1,
      'name': 'java',
      'count': 4
    },
    {
      'id': 2,
      'name': 'python',
      'count': 2
    },
    {
      'id': 3,
      'name': 'vue',
      'count': 0
    }
  ]
}

