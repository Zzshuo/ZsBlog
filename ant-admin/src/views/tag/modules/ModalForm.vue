<template>
  <a-modal
    title="标签"
    :width="640"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleSubmit"
    @cancel="handleCancel"
  >
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-form-item
          label="id"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
        >
          <a-input v-decorator="['id']" disabled="disabled"/>
        </a-form-item>
        <a-form-item
          label="名称"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
        >
          <a-input v-decorator="['name', {rules: [{required: true}]}]" />
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
import pick from 'lodash.pick'
export default {
  data () {
    return {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 7 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 13 }
      },
      visible: false,
      confirmLoading: false,
      form: this.$form.createForm(this)
    }
  },
  methods: {
    add () {
      this.visible = true
      this.$nextTick(() => {
        this.form.resetFields()
      })
    },
    edit (record) {
      this.visible = true
      this.$nextTick(() => {
        this.form.setFieldsValue(pick(record, ['id', 'name']))
      })
    },
    handleSubmit () {
      const { form: { validateFields } } = this
      this.confirmLoading = true
      validateFields((errors, values) => {
        if (!errors) {
          this.api.saveTag(values).then(res => {
            console.log(res)
            if (res && res.code === 200) {
              this.visible = false
              this.confirmLoading = false
              this.$message.info(`保存成功`)
              this.$emit('ok', values)
            } else {
              this.confirmLoading = false
              this.$message.error(res.message)
            }
          })
        } else {
          this.confirmLoading = false
        }
      })
    },
    handleCancel () {
      this.visible = false
    }
  }
}
</script>
