<template>
  <div>
    <div v-html="compiledMarkdown" class="markdown-body md"></div>
  </div>
</template>

<script>
import marked from 'marked'
import highlightJs from 'highlight.js'
import 'highlight.js/styles/atelier-dune-dark.css'

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
  mounted () {
    this.markdown()
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
        gfm: true,
        tables: true,
        breaks: false,
        pedantic: false,
        sanitize: false,
        smartLists: true,
        smartypants: false,
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
