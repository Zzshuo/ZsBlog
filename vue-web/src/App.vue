<template>
  <div id="app" class="zs-app" style="padding-top: 60px">
    <blog-header></blog-header>
    <router-view />
    <blog-footer></blog-footer>
  </div>
</template>

<script>
import BlogHeader from './view/common/BlogHeader'
import BlogFooter from './view/common/BlogFooter'
export default {
  name: 'App',
  components: {
    BlogHeader,
    BlogFooter
  }
}
</script>
<style scoped lang="stylus">
</style>
