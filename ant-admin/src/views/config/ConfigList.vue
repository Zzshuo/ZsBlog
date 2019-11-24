<template>
  <a-card :bordered="false">
    <a-tabs
      defaultActiveKey="0"
      tabPosition="left"
      @tabClick="tabClick"
    >
      <a-tab-pane
        v-for="configType in configTypeList"
        :key="configType.id"
        :tab="configType.name"
      >
        <a-spin :spinning="confirmLoading">
          <s-table
            ref="table"
            size="default"
            rowKey="id"
            :columns="columns"
            :data="loadData"
          >
            <template v-for="(col, index) in columns" v-if="col.scopedSlots" :slot="col.dataIndex" slot-scope="text, record">
              <div :key="index">
                <a-input
                  v-if="record.editable"
                  style="margin: -5px 0"
                  :value="text"
                  @change="e => handleChange(e.target.value, record.key, col, record)"
                />
                <template v-else>{{ text }}</template>
              </div>
            </template>
            <template slot="action" slot-scope="text, record">
              <div class="editable-row-operations">
                <span v-if="record.editable">
                  <a @click="() => save(record,configType.id)">保存</a>
                  <a-divider type="vertical" />
                  <a-popconfirm title="真的放弃编辑吗?" @confirm="() => cancel(record)">
                    <a>取消</a>
                  </a-popconfirm>
                </span>
                <span v-else>
                  <a class="edit" @click="() => edit(record)">修改</a>
                  <a-divider type="vertical" />
                  <a class="delete" @click="() => del(record)">删除</a>
                </span>
              </div>
            </template>
          </s-table>
        </a-spin>
      </a-tab-pane>
    </a-tabs>
  </a-card>
</template>
<script>
import { STable } from '@/components'
export default {
  name: 'ConfigList',
  components: {
    STable
  },
  data () {
    return {
      configTypeList: [],
      // 查询参数
      queryParam: {
        configType: 0
      },
      columns: [
        {
          title: '名称',
          width: '150px',
          dataIndex: 'configName'
        },
        {
          title: 'key',
          width: '250px',
          dataIndex: 'configKey'
        },
        {
          title: 'value',
          dataIndex: 'configValue',
          scopedSlots: { customRender: 'configValue' }
        },
        {
          title: '操作',
          dataIndex: 'action',
          width: '150px',
          scopedSlots: { customRender: 'action' }
        }
      ],
      confirmLoading: false,
      // 加载数据方法 必须为 Promise 对象
      loadData: parameter => {
        return this.api.getConfigListByType(Object.assign(parameter, this.queryParam))
          .then(res => {
            var data = res.data
            data.data.forEach(item => {
              item.editable = false
            })
            return data
          })
      }
    }
  },
  created () {
    this.getALLConfigType()
  },
  methods: {
    tabClick (value) {
      this.queryParam.configType = value
      this.refresh()
    },
    getALLConfigType () {
      this.api.getALLConfigType().then(res => {
        this.configTypeList = res.data
      })
    },
    refresh (index) {
      if (this.$refs.table[index]) {
        this.$refs.table[index].refresh()
      }
    },

    handleChange (value, key, column, record) {
      record[column.dataIndex] = value
    },
    edit (row) {
      row.editable = true
    },
    save (row) {
      this.confirmLoading = true
      this.api.saveConfig(row).then(res => {
        if (res && res.code === 200) {
          row.editable = false
          this.confirmLoading = false
          this.$message.info(`保存成功`)
          this.refresh()
        } else {
          this.confirmLoading = false
          this.$message.error(res.message)
        }
      })
    },
    cancel (row) {
      row.editable = false
    }
  }
}
</script>
