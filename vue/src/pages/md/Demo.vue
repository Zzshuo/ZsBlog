<template>
  <div>
    <mark-down-edit></mark-down-edit>
    <div>--------------------------------------------</div>
    <mark-down-view></mark-down-view>
    <div>--------------------------------------------</div>
    <mark-down-viewdemo></mark-down-viewdemo>
  </div>
</template>

<script>
import MarkDownEdit from './MarkdownEdit'
import MarkDownView from './MarkdownView'
import MarkDownViewdemo from './MarkdownViewdemo'
export default {
  name: 'Demo',
  components: {
    MarkDownEdit,
    MarkDownView,
    MarkDownViewdemo
  }
}
</script>

<style scoped lang="stylus">
</style>
