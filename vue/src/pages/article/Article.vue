<template>
  <div class="container zs-main">
    <div class="tile is-ancestor">
      <div class="tile is-parent is-9">
        <div class="zs-box zs-content" style="height: 1500px">
          <Breadcrumb separator=">">
            <BreadcrumbItem to="/">Home</BreadcrumbItem>
            <BreadcrumbItem to="/components/breadcrumb">Components</BreadcrumbItem>
            <BreadcrumbItem>Breadcrumb</BreadcrumbItem>
          </Breadcrumb>
          <div v-html="compiledMarkdown" class="markdown-body" ref="markdownBody"></div>
        </div>
      </div>
      <div class="tile is-parent is-hidden-mobile" v-if="true" style="position:relative">
        <Anchor show-ink :affix="true" style="position: fixed;width: calc(100% - 80%);max-width: 250px">
          <AnchorLink v-for="item in category" :key="item.id" :href="item.href" :title="item.title" />
        </Anchor>
      </div>
    </div>
  </div>
</template>

<script>
import marked from 'marked'
import highlightJs from 'highlight.js'
import 'highlight.js/styles/atelier-dune-dark.css'
import 'mavon-editor/dist/markdown/github-markdown.min.css'
export default {
  name: 'Article',
  components: {
  },
  data () {
    return {
      compiledMarkdown: '',
      article: {
        content: ''
      },
      id: this.$route.params.id,
      category: []
    }
  },
  mounted () {
    this.markdown()
    this.getArticle()
  },
  methods: {
    getArticle () {
      this.api.getArticleById(this.id).then((res) => {
        if (res && res.code === 200) {
          this.article = res.data
          this.compiledMarkdown = marked(this.article.content, {sanitize: true})
          this.$nextTick(() => {
            // 提取文章标签，生成目录
            Array.from(this.$refs.markdownBody.querySelectorAll('h1,h2,h3,h4,h5,h6')).forEach((item, index) => {
              item.id = item.localName + '-' + index
              this.category.push({
                tagName: item.localName,
                title: item.innerText,
                href: '#' + item.localName + '-' + index
              })
            })
            this.showSide = true
          })
        } else {
          console.log(res.code + res.message)
        }
      })
    },
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
</style>
