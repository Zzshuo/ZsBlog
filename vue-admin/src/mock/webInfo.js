export default {
  getWebInfo: req => {
    return webInfo
  }
}

const webInfo = {
  'code': 200,
  'message': '操作成功！',
  'data': {
    'articleCount': 1, // 文章总数
    'tagCount': 10, // 标签总数
    'msgCount': 20, // 留言总数
    'online': 8, // 在线人数
    'runDay': 101 // 运行天数
  }
}
