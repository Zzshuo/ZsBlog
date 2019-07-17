<template>
  <span>
    <Avatar v-if="original===1" style="color: #f56a00; background-color: #fde3cf" class="original">原创</Avatar>
    <Avatar v-else style="color: rgb(38, 162, 3); background: rgb(0, 231, 239)" class="original">转载</Avatar>
  </span>
</template>

<script>
export default {
  name: 'Original',
  props: {
    original: Number
  }
}
</script>

<style scoped lang="stylus">
.original
  /*font-size 1.5rem*/

</style>
