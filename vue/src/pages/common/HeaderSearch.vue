<template>
  <div class="field has-addons">
    <div class="control">
      <input class="input" type="text" placeholder="search">
    </div>
    <div class="control">
      <a class="button">
        <icon icon-class="icon-sousuo"/>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HeaderSearch'
}
</script>

<style scoped lang="stylus">
@media screen and (max-width: 768px) {
  .input {
    width 10rem !important
  }
}
@media screen and (min-width: 769px) {
  .input {
    width 20rem !important
  }
}
</style>
