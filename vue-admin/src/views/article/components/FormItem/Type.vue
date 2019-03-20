<template>
  <el-select v-model="typeId" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"/>
  </el-select>
</template>

<script>
export default {
  name: 'Type',
  props: {
    value: {
      default: null,
      type: Number
    }
  },
  data() {
    return {
      options: [{
        value: 1,
        label: '前端'
      }, {
        value: 2,
        label: '后端'
      }, {
        value: 3,
        label: '其他'
      }]
    }
  },
  computed: {
    typeId: {
      get() {
        return this.value
      },
      set(val) {
        this.$emit('input', val)
      }
    }
  }
}
</script>

<style scoped lang="stylus">
</style>
