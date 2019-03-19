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
      <el-checkbox v-for="tag in tagList" :label="tag.name" :key="tag.id">{{ tag.name }}</el-checkbox>
    </el-checkbox-group>
  </div>
</template>

<script>
export default {
  name: 'Tag',
  data() {
    return {
      dynamicTags: [],
      inputVisible: false,
      inputValue: '',
      tagList: []
    }
  },
  mounted() {
    this.getTagList()
  },
  methods: {
    getTagList() {
      this.api.getTagList(this.reqVo).then(response => {
        const res = response.data
        if (res && res.code === 200) {
          const data = res.data
          this.tagList = data.list
        } else {
          console.log(res.code + res.message)
        }
      })
    },
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
