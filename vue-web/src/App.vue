<template>
  <div id="app" class="app-container">
    <my-header></my-header>
    <router-view/>
    <my-footer></my-footer>
  </div>
</template>

<script>
import MyHeader from './views/common/MyHeader'
import MyFooter from './views/common/MyFooter'
export default {
  name: 'App',
  components: {
    MyHeader,
    MyFooter
  }
}
</script>
<style lang="scss" scoped>
</style>
