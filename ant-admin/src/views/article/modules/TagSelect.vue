<template>
  <div>
    <template v-for=" tag in allTags">
      <a-checkable-tag
        :key="tag.id"
        :checked="selectedTags.indexOf(tag.id) > -1"
        @change="(checked) => handleTagSelect(tag, checked)"
      >
        {{ tag.name }}
      </a-checkable-tag>
    </template>
    <a-input
      v-if="tagInputVisible"
      ref="tagInput"
      type="text"
      size="small"
      :style="{ width: '78px' }"
      :value="tagInputValue"
      @change="handleTagInputChange"
      @blur="handleTagInputConfirm"
      @keyup.enter="handleTagInputConfirm"
    />
    <a-tag v-else @click="showTagInput" style="background: #fff; borderStyle: dashed;">
      <a-icon type="plus" /> New Tag
    </a-tag>
  </div>
</template>

<script>
export default {
  model: {
    prop: 'tagIdList',
    event: 'change'
  },
  props: {
    allTags: {
      type: Array,
      default: function () {
        return []
      }
    },
    tagIdList: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
  data () {
    return {
      tagInputVisible: false,
      tagInputValue: '',
      selectedTags: this.tagIdList
    }
  },
  methods: {
    showTagInput () {
      this.tagInputVisible = true
      this.$nextTick(function () {
        this.$refs.tagInput.focus()
      })
    },
    handleTagInputChange (e) {
      this.tagInputValue = e.target.value
    },
    handleTagInputConfirm () {
      const tagInputValue = this.tagInputValue

      this.$emit('addNewTag', tagInputValue)

      this.tagInputVisible = false
      this.tagInputValue = ''
    },
    handleTagSelect (tag, checked) {
      this.selectedTags = checked
        ? [...this.selectedTags, tag.id]
        : this.selectedTags.filter(t => t !== tag.id)

      this.$emit('change', this.selectedTags)
    }
  }
}
</script>

<style>

</style>
