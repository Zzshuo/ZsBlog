<template>
  <svg class="svg-icon" aria-hidden="true">
    <use :xlink:href="iconName"></use>
  </svg>
</template>

<script>
  export default {
    name: 'icon',
    props: {
      iconClass: {
        type: String,
        required: true
      }
    },
    computed: {
      iconName() {
        return `#${this.iconClass}`
      }
    }
  }
</script>

<style>
  .svg-icon {
    width: 1em;
    height: 1em;
    vertical-align: -0.2em;
    fill: currentColor;
    overflow: hidden;
    margin-right: 0rem;
  }
</style>
