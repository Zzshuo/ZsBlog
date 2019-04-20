import store from '../store'

export function formatState(state) {
  return store.getters.stateMap[state]
}
export function formatTag(id) {
  return store.getters.allTag.find((tag) => { return tag.id === id }).name
}
