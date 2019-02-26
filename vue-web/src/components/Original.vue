<template>
  <span>
    <Avatar v-if="original===1" style="color: #f56a00; background-color: #fde3cf" class="original">原</Avatar>
    <Avatar v-else style="color: #86ca5e; background: #fde3cf" class="original">转</Avatar>
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
