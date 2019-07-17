const articleList = {
  'code': 200,
  'message': '操作成功！',
  'data': {
    'total': 33,
    'list': [
      {
        'id': 1,
        'title': 'title1111',
        'createTime': '2018-11-08T07:35:54.000+0000',
        'content': 'contentcontentcontentcontentcontentcontentcontentcontentcontentcontentcontentcontentcontent',
        'views': 999,
        'comments': 888,
        'original': 1
      },
      {
        'id': 2,
        'title': '',
        'original': 1,
        'typeId': 1,
        'status': 1,
        'description': null,
        'keywords': null,
        'state': 1,
        'createTime': '2018-11-08T07:35:55.000+0000',
        'updateTime': '2018-11-08T07:35:55.000+0000',
        'content': null
      },
      {
        'id': 3,
        'title': '',
        'original': 1,
        'typeId': 1,
        'status': 1,
        'description': null,
        'keywords': null,
        'state': 1,
        'createTime': '2018-11-08T07:35:57.000+0000',
        'updateTime': '2018-11-08T07:35:57.000+0000',
        'content': null
      },
      {
        'id': 4,
        'title': '',
        'original': 1,
        'typeId': 1,
        'status': 1,
        'description': null,
        'keywords': null,
        'state': 1,
        'createTime': '2018-11-08T07:35:58.000+0000',
        'updateTime': '2018-11-08T07:35:58.000+0000',
        'content': null
      },
      {
        'id': 5,
        'title': '',
        'original': 1,
        'typeId': 1,
        'status': 1,
        'description': null,
        'keywords': null,
        'state': 1,
        'createTime': '2018-11-08T07:35:59.000+0000',
        'updateTime': '2018-11-08T07:35:59.000+0000',
        'content': null
      },
      {
        'id': 6,
        'title': '',
        'original': 1,
        'typeId': 1,
        'status': 1,
        'description': null,
        'keywords': null,
        'state': 1,
        'createTime': '2018-11-08T07:36:00.000+0000',
        'updateTime': '2018-11-08T07:36:00.000+0000',
        'content': null
      },
      {
        'id': 7,
        'title': '',
        'original': 1,
        'typeId': 1,
        'status': 1,
        'description': null,
        'keywords': null,
        'state': 1,
        'createTime': '2018-11-08T07:36:00.000+0000',
        'updateTime': '2018-11-08T07:36:00.000+0000',
        'content': null
      },
      {
        'id': 8,
        'title': '',
        'original': 1,
        'typeId': 1,
        'status': 1,
        'description': null,
        'keywords': null,
        'state': 1,
        'createTime': '2018-11-08T07:36:00.000+0000',
        'updateTime': '2018-11-08T07:36:00.000+0000',
        'content': null
      },
      {
        'id': 9,
        'title': '',
        'original': 1,
        'typeId': 1,
        'status': 1,
        'description': null,
        'keywords': null,
        'state': 1,
        'createTime': '2018-11-08T07:36:00.000+0000',
        'updateTime': '2018-11-08T07:36:00.000+0000',
        'content': null
      },
      {
        'id': 10,
        'title': '',
        'original': 1,
        'typeId': 1,
        'status': 1,
        'description': null,
        'keywords': null,
        'state': 1,
        'createTime': '2018-11-08T07:36:00.000+0000',
        'updateTime': '2018-11-08T07:36:00.000+0000',
        'content': null
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

export const getArticleList = req => {
  return articleList
}
