const article = {
  'code': 200,
  'message': '操作成功！',
  'data': {
    'id': 1,
    'title': 'titleasd1',
    'typeId': 1,
    'description': 'descrjfadskljfklajfldkajflasjfklasfjla;sfjlsa',
    'keywords': null,
    'state': 1,
    'createTime': '2018-11-08T07:35:54.000+0000',
    'updateTime': '2018-11-08T07:35:54.000+0000',
    'content': '# jjjj  \r ```java \r  @BusinessLog("退出登录") \r  @PostMapping("/logout") \r  public ModelAndView logout() { \r  //退出   SecurityUtils.getSubject().logout();\r   return ResponseUtil.view("/admin/logout");} \r  ```\r### 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r> 引用文本 Blockquotes\r引用的行内混合 Blockquotes\r> 引用：如果想要插入空白换行`即<br />标签`，在插入处先键入两个以上的空格然后回车即可，[普通链接](http://localhost/)\r### 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r### 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r#### 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r# 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r##### 引用 Blo在插入处先键入 两个以上的 空格然后回车即可ckquotes\r',
    'views': 999,
    'comments': 888,
    'original': 0
  }
}

const success = {
  'code': 200,
  'message': '操作成功！',
  'data': {}
}

export const getArticle = req => {
  return article
}

export const saveArticle = req => {
  return success
}
