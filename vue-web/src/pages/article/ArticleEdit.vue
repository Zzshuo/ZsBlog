<template>
  <div>
    <blog-header></blog-header>
    <div class="container">
      <markdown-edit></markdown-edit>
    </div>
    <blog-footer></blog-footer>
  </div>
</template>

<script>
import MarkdownEdit from '../md/MarkdownEdit'
export default {
  name: 'ArticleEdit',
  components: {
    MarkdownEdit
  }
}
</script>

<style scoped lang="stylus">
</style>
