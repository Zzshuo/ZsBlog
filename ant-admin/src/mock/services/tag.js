import Mock from 'mockjs2'
import { builder, getQueryParameters } from '../util'

const totalCount = 200
const allTags = [{ id: 1, name: 'zzz1' }, { id: 2, name: 'zzz2' }, { id: 3, name: 'zzz3' }]

const getTagList = options => {
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
      name: 'name' + tmpKey,
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

const deleteTag = options => {
  return builder({}, '', 200)
}

const saveTag = options => {
  const parameters = getQueryParameters(options)
  const value = parseInt(parameters.value)
  allTags.push({ id: allTags.length + 1, name: value })
  return builder({}, 'xxx', 200)
}

const getAllTags = options => {
  return builder({
    data: allTags
  })
}

Mock.mock(/\/admin\/tag\/list/, 'post', getTagList)
Mock.mock(/\/admin\/tag\/delete/, 'post', deleteTag)
Mock.mock(/\/admin\/tag\/save/, 'post', saveTag)
Mock.mock(/\/admin\/tag\/getAllTags/, 'post', getAllTags)
