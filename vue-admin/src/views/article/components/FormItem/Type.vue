<template>
  <el-select v-model="typeId" placeholder="请选择">
    <el-option
      v-for="type in this.$store.getters.allType"
      :key="type.id"
      :label="type.name"
      :value="type.id"/>
  </el-select>
</template>

<script>
import { mapGetters } from 'vuex'
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
    ...mapGetters([
      'allType'
    ]),
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
