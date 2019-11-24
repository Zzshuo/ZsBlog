<template>
  <a-card :bordered="false">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="48">
          <a-col :md="8" :sm="24">
            <a-form-item label="标签名称">
              <a-input v-model="queryParam.id" placeholder=""/>
            </a-form-item>
          </a-col>
          <a-col :md="16" :sm="24">
            <span class="table-page-search-submitButtons" :style="{ float: 'right', overflow: 'hidden' } ">
              <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
              <a-button style="margin-left: 8px" @click="() => queryParam = {}">重置</a-button>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <div class="table-operator">
      <a-button type="primary" icon="plus" @click="$refs.modalForm.add()">新建</a-button>
    </div>

    <s-table
      ref="table"
      size="default"
      rowKey="id"
      :columns="columns"
      :data="loadData"
      showPagination="auto"
    >
      <span slot="serial" slot-scope="text, record, index">
        {{ index + 1 }}
      </span>
      <span slot="action" slot-scope="text, record">
        <template>
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical" />
          <a @click="handleDelete(record)">删除</a>
        </template>
      </span>
    </s-table>
    <modal-form ref="modalForm" @ok="handleOk" />
  </a-card>
</template>

<script>
import { STable } from '@/components'
import ModalForm from './modules/ModalForm'
export default {
  name: 'TypeList',
  components: {
    STable,
    ModalForm
  },
  data () {
    return {
      // 查询参数
      queryParam: {},
      // 表头
      columns: [
        {
          title: '#',
          dataIndex: 'id'
        },
        {
          title: '名称',
          dataIndex: 'name'
        },
        {
          title: '文章数',
          dataIndex: 'count'
        },
        {
          title: '操作',
          dataIndex: 'action',
          width: '150px',
          scopedSlots: { customRender: 'action' }
        }
      ],
      // 加载数据方法 必须为 Promise 对象
      loadData: parameter => {
        return this.api.getTypeList(Object.assign(parameter, this.queryParam))
          .then(res => {
            console.log(res.data)
            return res.data
          })
      }
    }
  },
  filters: {
  },
  created () {
  },
  methods: {
    handleOk () {
      this.$refs.table.refresh()
    },
    handleEdit (record) {
      this.$refs.modalForm.edit(record)
    },
    handleDelete (record) {
      this.api.deleteType(record).then(res => {
        if (res && res.code === 200) {
          this.$message.info(`删除成功`)
        } else {
          this.$message.error(res.message)
        }
      })
    }
  }
}
</script>
