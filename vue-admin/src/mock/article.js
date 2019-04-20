export default {
  getArticle: req => {
    return getArticle
  },
  saveArticle: req => {
    return success
  },
  getArticleList: req => {
    return articleList
  }
}

const getArticle = {
  'code': 200,
  'message': '操作成功！',
  'data': {
    'id': 1,
    'title': 'titleasd1',
    'original': true,
    'typeId': 1,
    'tagIdList': [1, 2],
    'state': 1,
    'createTime': '2018-11-08T07:35:54.000+0000',
    'updateTime': '2018-11-08T07:35:54.000+0000',
    'content': '# jjjj  \r ```java \r  @BusinessLog("退出登录") \r  @PostMapping("/logout") \r  public ModelAndView logout() { \r  //退出   SecurityUtils.getSubject().logout();\r   return ResponseUtil.view("/admin/logout");} \r  ```\r### 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r> 引用文本 Blockquotes\r引用的行内混合 Blockquotes\r> 引用：如果想要插入空白换行`即<br />标签`，在插入处先键入两个以上的空格然后回车即可，[普通链接](http://localhost/)\r### 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r### 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r#### 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r# 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r##### 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r',
    'views': 999,
    'comments': 888
  }
}

const success = {
  'code': 200,
  'message': '操作成功！',
  'data': {}
}

const articleList = {
  'code': 200,
  'message': '操作成功！',
  'data': {
    'total': 33,
    'list': [
      {
        'id': 1,
        'title': 'title1111',
        'original': true,
        'typeId': 1,
        'tagIdList': [1, 2],
        'state': 1,
        'createTime': '2018-11-08T07:35:55.000+0000',
        'updateTime': '2018-11-08T07:35:55.000+0000',
        'views': 999,
        'comments': 888
      },
      {
        'id': 2,
        'title': 'title2222',
        'original': true,
        'typeId': 1,
        'tagIdList': [1, 2],
        'state': 1,
        'createTime': '2018-11-08T07:35:55.000+0000',
        'updateTime': '2018-11-08T07:35:55.000+0000',
        'views': 999,
        'comments': 888
      },
      {
        'id': 3,
        'title': 'title3333',
        'original': true,
        'typeId': 1,
        'tagIds': [1, 2],
        'state': 1,
        'createTime': '2018-11-08T07:35:55.000+0000',
        'updateTime': '2018-11-08T07:35:55.000+0000',
        'views': 999,
        'comments': 888
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
