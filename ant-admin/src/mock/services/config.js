import Mock from 'mockjs2'
import { builder, getQueryParameters } from '../util'

const totalCount = 200

const getConfigListByType = options => {
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
      configName: 'name' + tmpKey,
      configKey: 'key' + tmpKey,
      configValue: 'value' + tmpKey,
      configType: 'type' + tmpKey
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

const getAllConfigType = options => {
  const data = [{
    id: '0',
    name: '默认'
  }, {
    id: '1',
    name: '阿里云'
  }]
  return builder(data)
}

const saveConfig = options => {
  return builder({}, 'xxx', 200)
}
Mock.mock(/\/admin\/config\/save/, 'post', saveConfig)
Mock.mock(/\/admin\/config\/getListByType/, 'post', getConfigListByType)
Mock.mock(/\/admin\/config\/getAllConfigType/, 'post', getAllConfigType)
