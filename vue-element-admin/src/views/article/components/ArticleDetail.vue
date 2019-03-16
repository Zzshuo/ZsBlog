<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" label-position="left" class="form-container">

      <sticky :class-name="'sub-navbar '+postForm.state">
        <CommentDropdown v-model="postForm.comment_disabled" />
        <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm('postForm')">发布</el-button>
        <el-button v-loading="loading" type="warning" @click="draftForm">草稿</el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-form-item style="margin-bottom: 40px;" prop="title">
          <MDinput v-model="postForm.title" :maxlength="100" name="name" required>
            标题
          </MDinput>
        </el-form-item>
        <el-row>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="分类:">
              <form-item-type v-model="postForm.typeId"/>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="类型:">
              <el-radio-group v-model="postForm.original">
                <el-radio-button label="原创"/>
                <el-radio-button label="转载"/>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="标签:">
          <form-item-tag v-model="postForm.tagList"/>
        </el-form-item>
        <el-form-item prop="content" style="margin-bottom: 30px;">
          <mavon-editor
            ref="md"
            v-model="postForm.content"
            :box-shadow = "false"
            class="mavonEditor"
            @imgAdd="$imgAdd"/>
        </el-form-item>
        <!--<el-form-item label="封面图:">-->
        <!--<form-item-image v-model="postForm.image"/>-->
        <!--</el-form-item>-->
      </div>
    </el-form>
  </div>
</template>

<script>
import Upload from '@/components/Upload/singleImage3'
import MDinput from '@/components/MDinput'
import Sticky from '@/components/Sticky' // 粘性header组件
import { fetchArticle } from '@/api/article'
import { userSearch } from '@/api/remoteSearch'
import { CommentDropdown } from './Dropdown'
import { FormItemImage, FormItemTag, FormItemType } from './FormItem'
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

const defaultForm = {
  state: 1,
  title: '', // 文章题目
  content: '', // 文章内容
  image: '', // 文章图片
  original: undefined, // 前台展示时间
  typeId: undefined,
  tagList: [],
  comment_disabled: false
}

export default {
  name: 'ArticleDetail',
  components: { MDinput, Upload, Sticky, CommentDropdown, mavonEditor, FormItemImage, FormItemTag, FormItemType },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      postForm: Object.assign({}, defaultForm),
      loading: false,
      userListOptions: [],
      rules: {
        title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
        content: [{ required: true, message: '请输入内容', trigger: 'blur' }],
        original: [{ required: true, message: '请选择类型', rigger: 'change' }],
        typeId: [{ required: true, message: '请选择分类', trigger: 'blur' }],
        tagList: [{ required: true, message: '请选择标签', trigger: 'blur' }]
      },
      tempRoute: {}
    }
  },
  computed: {
    contentShortLength() {
      return this.postForm.content_short.length
    },
    lang() {
      return this.$store.getters.language
    }
  },
  created() {
    if (this.isEdit) {
      const id = this.$route.params && this.$route.params.id
      this.fetchData(id)
    } else {
      this.postForm = Object.assign({}, defaultForm)
    }

    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route)
  },
  methods: {
    fetchData(id) {
      fetchArticle(id).then(response => {
        this.postForm = response.data
        // Just for test
        this.postForm.title += `   Article Id:${this.postForm.id}`
        this.postForm.content_short += `   Article Id:${this.postForm.id}`

        // Set tagsview title
        this.setTagsViewTitle()
      }).catch(err => {
        console.log(err)
      })
    },
    setTagsViewTitle() {
      const title = this.lang === 'zh' ? '编辑文章' : 'Edit Article'
      const route = Object.assign({}, this.tempRoute, { title: `${title}-${this.postForm.id}` })
      this.$store.dispatch('updateVisitedView', route)
    },
    submitForm() {
      console.log(this.postForm)
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$notify({
            title: '成功',
            message: '发布文章成功',
            type: 'success',
            duration: 2000
          })
          this.postForm.state = 1
          this.loading = false
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    draftForm() {
      if (this.postForm.content.length === 0 || this.postForm.title.length === 0) {
        this.$message({
          message: '请填写必要的标题和内容',
          type: 'warning'
        })
        return
      }
      this.$message({
        message: '保存成功',
        type: 'success',
        showClose: true,
        duration: 1000
      })
      this.postForm.state = 3
    },
    getRemoteUserList(query) {
      userSearch(query).then(response => {
        if (!response.data.items) return
        this.userListOptions = response.data.items.map(v => v.name)
      })
    },
    $imgAdd(pos, $file) {
      console.log('111')
      // 第一步.将图片上传到服务器.
      var formdata = new FormData()
      formdata.append('image', $file)
      // axios({
      //   url: 'server url',
      //   method: 'post',
      //   data: formdata,
      //   headers: { 'Content-Type': 'multipart/form-data' }
      // }).then((url) => {
      //   // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
      //   /**
      //    * $vm 指为mavonEditor实例，可以通过如下两种方式获取
      //    * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
      //    * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
      //    */
      //   var $vm = this.$refs.md
      //   $vm.$img2Url(pos, url)
      // })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
@import "~@/styles/mixin.scss";
.createPost-container {
  position: relative;
  .createPost-main-container {
    padding: 10px 40px;
    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;
      .postInfo-container-item {
        float: left;
      }
    }
  }
  .word-counter {
    width: 40px;
    position: absolute;
    right: -10px;
    top: 0px;
  }
}
.mavonEditor {
  height: 500px;
  z-index: 0;
}
</style>
