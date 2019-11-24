<template>
  <a-modal
    title="标签"
    :width="640"
    :visible="visible"
    @cancel="handleCancel"
  >
    <a-form :form="form">
      <a-form-item
        label="标签"
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
      >
        <tag-select
          :allTags="allTags"
          @addNewTag="addNewTag"
          placeholder="请选择标签"
          v-decorator="['tagIdList', {rules: [{required: true, message: '请选择标签'}]}]"
        ></tag-select>
      </a-form-item>
      <a-form-item
        label="分类"
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
      >
        <a-select placeholder="请选择分类" v-decorator="[ 'typeId', {rules: [{ required: true, message: '请选择分类'}]} ]">
          <template v-for=" type in allTypes">
            <a-select-option :key="type.id" :value="type.id">{{ type.name }}</a-select-option>
          </template>
        </a-select>
      </a-form-item>
      <a-form-item
        label="类型"
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
      >
        <a-switch v-decorator="['original']" checkedChildren="原创" unCheckedChildren="转载"/>
      </a-form-item>
      <a-form-item
        label="备注"
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
      >
        <a-textarea placeholder="请输入备注" v-decorator="['comment']" />
      </a-form-item>
    </a-form>
    <template slot="footer">
      <a-button @click="handleCancel">取消</a-button>
      <a-button type="primary" @click="handleSubmit(2)">草稿</a-button>
      <a-button type="primary" @click="handleSubmit(1)">发布</a-button>
    </template>
  </a-modal>
</template>

<script>
import pick from 'lodash.pick'
import TagSelect from './TagSelect'
export default {
  components: {
    TagSelect
  },
  data () {
    return {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 4 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 18 }
      },
      visible: false,
      form: this.$form.createForm(this),
      allTags: [],
      allTypes: []
    }
  },
  mounted () {
    this.getAllTags()
    this.getAllTypes()
  },
  methods: {
    edit (record) {
      this.visible = true
      if (record.original === undefined) {
        record.original = true
      }
      if (record.type !== undefined) {
        record.typeId = record.type.id
      }
      if (record.tagList !== undefined) {
        record.tagIdList = record.tagList.map(function (tag) { return tag.id })
        console.log(record)
      }

      this.$nextTick(() => {
        this.form.setFieldsValue(pick(record, [ 'tagIdList', 'typeId', 'original' ]))
      })
    },
    handleSubmit (state) {
      const { form: { validateFields } } = this
      validateFields((errors, values) => {
        values.state = state
        if (!errors) {
          this.$emit('handleSubmit', values)
          this.visible = false
        }
      })
    },
    handleCancel () {
      this.visible = false
    },
    addNewTag (value) {
      this.api.saveTag({ value: value }).then(res => {
        if (res && res.code === 200) {
          this.getAllTags()
        } else {
          this.$message.error(res.message)
        }
      })
    },
    getAllTags () {
      this.api.getAllTags().then(res => {
        if (res && res.code === 200) {
          this.allTags = res.data.data
        } else {
          this.$message.error(res.message)
        }
      })
    },
    getAllTypes () {
      this.api.getAllTypes().then(res => {
        if (res && res.code === 200) {
          this.allTypes = res.data.data
        } else {
          this.$message.error(res.message)
        }
      })
    }
  }
}
</script>

<style  lang="less" scoped>
  /deep/ .ant-switch {
    background-color: #ff8d18
  }
  /deep/ .ant-switch-checked {
    background-color: #1890ff
  }
</style>
