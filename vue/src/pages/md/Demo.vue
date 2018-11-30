<template>
  <div>
    <mark-down-edit></mark-down-edit>
    <div>--------------------------------------------</div>
    <mark-down-view></mark-down-view>
  </div>
</template>

<script>
import MarkDownEdit from './MarkdownEdit'
import MarkDownView from './MarkdownView'
export default {
  name: 'Demo',
  components: {
    MarkDownEdit,
    MarkDownView
  }
}
</script>

<style scoped lang="stylus">
</style>
