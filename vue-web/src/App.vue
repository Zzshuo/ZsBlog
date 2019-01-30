<template>
  <div id="app" class="zs-app">
    <blog-header></blog-header>
    <router-view/>
    <blog-footer></blog-footer>
  </div>
</template>

<script>
import BlogHeader from './pages/common/BlogHeader'
import BlogFooter from './pages/common/BlogFooter'
export default {
  name: 'App',
  components: {
    BlogHeader,
    BlogFooter
  }
}
</script>
<style>
</style>
