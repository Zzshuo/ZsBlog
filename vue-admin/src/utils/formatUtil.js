import store from '../store'

export function formatState(state) {
  return store.getters.stateMap[state]
}
const stateColorMap = {
  1: 'success',
  2: 'info',
  3: 'danger'
}

export function formatStateColor(state) {
  return stateColorMap[state]
}

export function formatTag(tagId) {
  return store.getters.allTag.find((tag) => { return tag.id === tagId }).name
}

export function formatType(typeId) {
  return store.getters.allType.find((type) => { return type.id === typeId }).name
}
