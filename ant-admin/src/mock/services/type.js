import Mock from 'mockjs2'
import { builder, getQueryParameters } from '../util'

const totalCount = 200

const getTypeList = options => {
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
      name: 'typename' + tmpKey,
      count: Mock.mock('@integer(1, 999)')
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

const deleteType = options => {
  return builder({}, '', 200)
}

const saveType = options => {
  return builder({}, 'xxx', 200)
}

const getAllTypes = options => {
  const data = []
  for (let i = 1; i < 10; i++) {
    data.push({
      id: i,
      name: 'typename' + i
    })
  }
  return builder({
    data: data
  })
}

Mock.mock(/\/admin\/type\/list/, 'post', getTypeList)
Mock.mock(/\/admin\/type\/delete/, 'post', deleteType)
Mock.mock(/\/admin\/type\/save/, 'post', saveType)
Mock.mock(/\/admin\/type\/getAllTypes/, 'post', getAllTypes)
