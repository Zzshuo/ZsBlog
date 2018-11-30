<template>
  <div>
    <div>view</div>
    <mavon-editor
      class="md"
      :value="value"
      :subfield = "props.subfield"
      :defaultOpen = "props.defaultOpen"
      :toolbarsFlag = "props.toolbarsFlag"
      :editable = "props.editable"
      :scrollStyle = "props.scrollStyle"
      :boxShadow = "props.boxShadow"
      :codeStyle = "props.codeStyle"
      :navigation = "props.navigation"
      :ishljs = "props.ishljs"
      :fontSize = "props.fontSize"
    />
  </div>
</template>

<script>
export default {
  name: 'MarkDownView',
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  computed: {
    props () {
      return {
        subfield: false, // 单双栏模式
        defaultOpen: 'preview', // edit： 默认展示编辑区域 ， preview： 默认展示预览区域
        toolbarsFlag: false, // 工具栏是否显示
        editable: false, // 是否允许编辑
        scrollStyle: true, // 开启滚动条样式
        boxShadow: false, // 开启边框阴影
        codeStyle: 'code-github', // markdown样式： 默认github
        navigation: true, // 默认展示目录
        ishljs: true, // 代码高亮
        fontSize: '30px'
      }
    }
  }
}
</script>

<style scoped lang="stylus">
  md >>> .markdown-body pre
    font-size 50px !important
</style>
