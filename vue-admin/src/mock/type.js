export default {
  getAllType: req => {
    return allType
  }
}

const allType = {
  'code': 200,
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

