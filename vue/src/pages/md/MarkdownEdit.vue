<template>
  <div style="margin-top: 1rem">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="标题" prop="title">
        <el-input v-model="form.title" class="article-input"></el-input>
      </el-form-item>
      <el-form-item label="类型" prop="original">
        <el-radio-group v-model="form.original">
          <el-radio label="原创"></el-radio>
          <el-radio label="转载"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="分类" prop="type">
        <el-select v-model="form.type" placeholder="请选择分类">
          <el-option label="后端" value="shanghai"></el-option>
          <el-option label="前端" value="beijing"></el-option>
          <el-option label="其他" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="标签" class="label">
        <el-select
          v-model="form.label"
          multiple
          filterable
          allow-create
          placeholder="请选择文章标签">
          <el-option
            v-for="item in labelList"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="form.desc"  class="article-input"></el-input>
      </el-form-item>
      <el-form-item label="关键字" >
        <el-input v-model="form.keywords" class="article-input"></el-input>
      </el-form-item>
      <el-form-item class="article-content">
        <mavon-editor ref=md
                      v-model="form.content"
                      :toolbars="toolbars"
                      :placeholder="placeholder"
                      @imgAdd='imgAdd'
                      @save="save"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">发布</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'MarkdownEdit',
  data () {
    return {
      radio: '1',
      form: {
        title: '',
        desc: '',
        keywords: '',
        label: '',
        type: '',
        original: '',
        content: '111'
      },
      rules: {
        title: [
          {required: true, message: '请输入标题', trigger: 'blur'},
          {min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur'}
        ],
        original: [
          { required: true, message: '请选择类型', trigger: 'change' }
        ],
        type: [
          { required: true, message: '请选择分类', trigger: 'change' }
        ]
      },
      labelList: [{
        value: 'HTML',
        label: 'HTML'
      }, {
        value: 'CSS',
        label: 'CSS'
      }, {
        value: 'JavaScript',
        label: 'JavaScript'
      }],
      placeholder: '请输入...',
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: true, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true // 预 览
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    save (value, html) {
      alert(this.form.content)
      alert(html)
      alert(value)
    },
    imgAdd (pos, $file) {
      this.$refs.md.$img2Url(pos, 'http://localhost:8080/static/img/default.a06834e.gif')
      // 第一步.将图片上传到服务器.
      // var formdata = new FormData()
      // formdata.append ('image', $file)
      // axios ({
      //   url: 'server url',
      //   method: 'post',
      //   data: formdata,
      //   headers: { 'Content-Type': 'multipart/form-data' }
      // }).then((url) => {
      //   // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
      //   /**
      //    * $vm 指为mavonEditor实例，可以通过如下两种方式获取
      //    * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
      //    * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
      //    */
      //   // this.$refs.editor.$img2Url(filename, 'http://localhost:8080/static/img/default.a06834e.gif') // 更改图片内文章上传地址
      //   this.$refs.md.$img2Url(pos, url)
      // })
    }
  }
}
</script>

<style scoped lang="stylus">
  .article-content >>> .el-form-item__content
    margin-left 0 !important
  .article-input
    max-width 30rem
  .label >>> .el-select
    max-width 30rem
    width 100%
  .v-note-wrapper
    height 33rem !important
    max-height 33rem !important
    position inherit
</style>
