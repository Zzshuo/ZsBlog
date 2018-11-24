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
  data () {
    return {
      value: 'Markdown 是一种方便记忆、书写的纯文本标记语言，用户可以使用这些标记符号以最小的输入代价生成极富表现力的文档：譬如您正在阅读的这份文档。它使用简单的符号标记不同的标题，分割不同的段落，**粗体** 或者 *斜体* 某些文字，更棒的是，它还可以\n' +
        '\n' +
        '### 1. 制作一份待办事宜 [Todo 列表](https://www.zybuluo.com/mdeditor?url=https://www.zybuluo.com/static/editor/md-help.markdown#13-待办事宜-todo-列表)\n' +
        '\n' +
        '- [ ] 支持以 PDF 格式导出文稿\n' +
        '- [ ] 改进 Cmd 渲染算法，使用局部渲染技术提高渲染效率\n' +
        '- [x] 新增 Todo 列表功能\n' +
        '- [x] 修复 LaTex 公式渲染问题\n' +
        '- [x] 新增 LaTex 公式编号功能\n' +
        '\n' +
        '### 2. 书写一个质能守恒公式[^LaTeX]\n' +
        '\n' +
        '$$E=mc^2$$\n' +
        '\n' +
        '### 3. 高亮一段代码[^code]\n' +
        '\n' +
        '```python\n' +
        '@requires_authorization\n' +
        'class SomeClass:\n' +
        '    pass\n' +
        '粗体\n' +
        'if __name__ == \'__main__\':\n' +
        '    # A comment\n' +
        '    print \'hello world\'\n' +
        '```\n'
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
