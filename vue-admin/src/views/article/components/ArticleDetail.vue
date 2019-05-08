<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" hide-required-asterisk class="form-container">

      <sticky :class-name="'sub-navbar state'+postForm.state">
        <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm('postForm')">发布</el-button>
        <el-button v-loading="loading" type="warning" @click="draftForm">草稿</el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-form-item prop="title">
          <MDinput v-model="postForm.title" :maxlength="100" name="name" required>
            标题
          </MDinput>
        </el-form-item>
        <el-row :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="8">
            <el-form-item label="分类:" prop="typeId">
              <form-item-type v-model="postForm.typeId"/>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="8">
            <el-form-item label="类型:" prop="original">
              <el-select v-model="postForm.original" placeholder="请选择">
                <el-option v-for="item in this.$store.getters.original" :key="item.value" :label="item.name" :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="8">
            <el-form-item label="标签:" prop="tagList">
              <el-select
                v-model="postForm.tagIdList"
                multiple
                filterable
                allow-create
                default-first-option
                placeholder="请选择文章标签">
                <el-option
                  v-for="tag in this.$store.getters.allTag"
                  :key="tag.id"
                  :label="tag.name"
                  :value="tag.id"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item prop="content" style="margin-bottom: 30px;">
          <mavon-editor
            ref="md"
            v-model="postForm.content"
            :box-shadow = "false"
            class="mavonEditor"
            @imgAdd="addArticleImage"/>
        </el-form-item>
        <el-form-item label="封面图:">
          <form-item-image v-model="postForm.image"/>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
import MDinput from '@/components/MDinput'
import Sticky from '@/components/Sticky' // 粘性header组件
import { FormItemImage, FormItemType } from './FormItem'
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import { mapGetters } from 'vuex'

const defaultForm = {
  // 1:发布,2:草稿,0:删除
  state: 0,
  tagIdList: []
}

export default {
  name: 'ArticleDetail',
  components: { MDinput, Sticky, mavonEditor, FormItemImage, FormItemType },
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
      rules: {
        title: [{ required: true, message: '请输入标题', trigger: 'change' }],
        content: [{ required: true, message: '请输入内容', trigger: 'change' }],
        original: [{ type: 'boolean', required: true, message: '请选择类型', trigger: 'change' }],
        typeId: [{ required: true, message: '请选择分类', trigger: 'change' }],
        tagIdList: [{ type: 'array', required: true, message: '请选择标签', trigger: 'change' }]
      },
      reqVo: {}
    }
  },
  computed: {
    ...mapGetters([
      'allTag'
    ])
  },
  created() {
    if (this.isEdit) {
      const id = this.$route.params && this.$route.params.id
      this.fetchData(id)
    } else {
      this.postForm = Object.assign({}, defaultForm)
    }
  },
  methods: {
    fetchData(id) {
      this.reqVo.id = id
      this.api.getArticleById(this.reqVo).then(data => {
        this.postForm = data
      })
    },
    submitForm() {
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.postForm.state = 1
          this.api.saveArticle(this.postForm).then(data => {
            this.$message({
              message: '发布文章成功',
              type: 'success',
              duration: 1000
            })
            this.loading = false
            this.$router.push('/article/list')
          }, () => {
            this.loading = false
          })
        }
      })
    },
    // 保存草稿
    draftForm() {
      this.postForm.state = 2
      this.api.saveArticle(this.postForm).then(data => {
        this.$message({
          message: '保存草稿成功',
          type: 'success',
          duration: 1000
        })
        this.$router.push('/article/list')
      })
    },
    addArticleImage(pos, $file) {
      // 第一步.将图片上传到服务器.
      const formdata = new FormData()
      formdata.append('file', $file)
      this.api.addArticleImage(formdata).then(data => {
        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
        /**
         * $vm 指为mavonEditor实例，可以通过如下两种方式获取
         * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
         * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
         */
        const $vm = this.$refs.md
        $vm.$img2Url(pos, data)
      })
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
  .el-select {
    width:100%
  }
}
.mavonEditor {
  height: 500px;
  z-index: 0;
}
</style>
