<template>
  <div class="columns">
    <div v-html="compiledMarkdown" class="markdown-body column" id="markdown-body"></div>
    <div class="column is-hidden-mobile is-one-quarter" v-show="showNav">
      <div class="markdown-catalog" id="markdown-catalog">
      </div>
    </div>
  </div>
</template>

<script>
import marked from 'marked'
import highlightJs from 'highlight.js'
import 'highlight.js/styles/atelier-dune-dark.css'
import 'mavon-editor/dist/markdown/github-markdown.min.css'
import catalog from '../../assets/utils/catalog'

export default {
  name: 'Markdown',
  components: {
  },
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      showNav: true
    }
  },
  mounted () {
    this.markdown()
    catalog()
  },
  computed: {
    compiledMarkdown () {
      return marked(this.value, {sanitize: true})
    }
  },
  methods: {
    markdown () {
      marked.setOptions({
        renderer: new marked.Renderer(),
        gfm: true, // 允许 Git Hub标准的markdown.
        tables: true, // 允许支持表格语法。该选项要求 gfm 为true。
        breaks: false, // 允许回车换行。该选项要求 gfm 为true。
        pedantic: false, // 尽可能地兼容 markdown.pl的晦涩部分。不纠正原始模型任何的不良行为和错误。
        sanitize: false, // 对输出进行过滤（清理），将忽略任何已经输入的html代码（标签）
        smartLists: true, // 使用比原生markdown更时髦的列表。 旧的列表将可能被作为pedantic的处理内容过滤掉.
        smartypants: true, // 使用更为时髦的标点，比如在引用语法中加入破折号。
        highlight: function (code, lang) {
          if (lang && highlightJs.getLanguage(lang)) {
            return highlightJs.highlight(lang, code, true).value
          } else {
            return highlightJs.highlightAuto(code).value
          }
        }
      })
    }
  }
}
</script>

<style scoped lang="stylus">
  .markdown-catalog
    position fixed
    max-width 224px
  @media screen and (min-width: 769px)
    .markdown-catalog
      width 21.5%
  @media screen and (min-width: 1088px)
    .markdown-catalog
      width 19.9%
  @media screen and (min-width: 1280px)
    .markdown-catalog
      width 17.7%
  @media screen and (min-width: 1472px)
    .markdown-catalog
      width 15.3%
</style>
