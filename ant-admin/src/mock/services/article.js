import Mock from 'mockjs2'
import { builder, getQueryParameters } from '../util'

const totalCount = 200

const getArticleList = options => {
  const parameters = getQueryParameters(options)
  const data = []
  const pageNo = parseInt(parameters.pageNo)
  const pageSize = parseInt(parameters.pageSize)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? totalCount % pageSize : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    data.push({
      id: tmpKey,
      title: 'test' + tmpKey,
      original: Mock.Random.boolean(),
      type: {
        id: tmpKey,
        name: 'type' + tmpKey
      },
      tagList: [
        {
          id: 1,
          name: 'tag1'
        },
        {
          id: 2,
          name: 'tag2'
        }
      ],
      image: 'https://cdn4.buysellads.net/uu/1/41334/1550855391-cc_dark.png',
      state: Mock.mock('@integer(1, 2)'),
      createTime: '2019-09-09',
      updateTime: '2019-09-10',
      views: Mock.mock('@integer(1, 999)'),
      comments: Mock.mock('@integer(1, 999)')
    })
  }

  return builder({
    pageSize: pageSize,
    pageNo: pageNo,
    totalCount: totalCount,
    totalPage: totalPage,
    data: data
  })
}

const getArticleById = options => {
  return builder({
    data: {
      id: 1,
      title: 'test',
      original: false,
      type: {
        id: 1,
        name: 'type1'
      },
      tagList: [
        {
          id: 1,
          name: 'tag1'
        },
        {
          id: 2,
          name: 'tag2'
        }
      ],
      image: 'imageurl',
      state: 1,
      createTime: '2019-09-09',
      updateTime: '2019-09-10',
      views: 123,
      comments: 234,
      content: 'content1232144'
    }
  })
}

const saveArticle = options => {
  return builder({}, 'xxx', 200)
}

const articleUpdateState = options => {
  return builder({}, 'xxx', 200)
}

Mock.mock(/\/admin\/article\/save/, 'post', saveArticle)
Mock.mock(/\/admin\/article\/list/, 'post', getArticleList)
Mock.mock(/\/admin\/article\/get/, 'post', getArticleById)
Mock.mock(/\/admin\/article\/updateState/, 'post', articleUpdateState)
