<template>
  <div>
    <el-tag
      v-for="tag in dynamicTags"
      :key="tag"
      :disable-transitions="false"
      closable
      @close="handleClose(tag)">
      {{ tag }}
    </el-tag>
    <el-input
      v-if="inputVisible"
      ref="saveTagInput"
      v-model="inputValue"
      size="small"
      class="input-new-tag"
      @keyup.enter.native="handleInputConfirm"
      @blur="handleInputConfirm"
    />
    <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
    <el-checkbox-group v-model="dynamicTags">
      <el-checkbox v-for="city in cities" :label="city" :key="city">{{ city }}</el-checkbox>
    </el-checkbox-group>
  </div>
</template>

<script>
const cityOptions = ['上海', '北京', '广州', '深圳']
export default {
  name: 'Tag',
  data() {
    return {
      dynamicTags: [],
      inputVisible: false,
      inputValue: '',
      cities: cityOptions
    }
  },
  methods: {
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1)
    },
    showInput() {
      this.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },
    handleInputConfirm() {
      const inputValue = this.inputValue
      if (inputValue) {
        this.dynamicTags.push(inputValue)
      }
      this.inputVisible = false
      this.inputValue = ''
    }
  }
}
</script>

<style scoped lang="stylus">
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>
