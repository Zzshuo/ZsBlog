<template>
  <a-card :bordered="false">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="48">
          <a-col :md="8" :sm="24">
            <a-form-item label="标题">
              <a-input v-model="queryParam.title" placeholder=""/>
            </a-form-item>
          </a-col>
          <template v-if="advanced">
            <a-col :md="8" :sm="24">
              <a-form-item label="分类">
                <a-input v-model="queryParam.typeId" placeholder=""/>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="标签">
                <a-input v-model="queryParam.tagId" placeholder=""/>
              </a-form-item>
            </a-col>
          </template>
          <a-col :md="!advanced && 16 || 24" :sm="24">
            <span class="table-page-search-submitButtons" :style=" { float: 'right', overflow: 'hidden' } ">
              <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
              <a-button style="margin-left: 8px" @click="() => queryParam = {}">重置</a-button>
              <a @click="toggleAdvanced" style="margin-left: 8px">
                {{ advanced ? '收起' : '展开' }}
                <a-icon :type="advanced ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <s-table
      ref="table"
      size="default"
      rowKey="id"
      :columns="columns"
      :data="loadData"
      showPagination="auto"
    >
      <span slot="original" slot-scope="original">
        <a-switch :checked="original" checkedChildren="原创" unCheckedChildren="转载" />
      </span>
      <span slot="tagList" slot-scope="tagList">
        <template v-for=" tag in tagList">
          <a-tag :key="tag.id">{{ tag.name }}</a-tag>
        </template>
      </span>
      <span slot="image" slot-scope="image">
        <img :src="image" style="height:100px"/>
      </span>
      <span slot="state" slot-scope="state">
        <a-badge :status="state | statusTypeFilter" :text="state | statusFilter" />
      </span>
      <span slot="action" slot-scope="text, record">
        <template>
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical" />
          <a v-if="record.state==1" @click="handleState(record,2)">草稿</a>
          <a v-else-if="record.state==2" @click="handleState(record,1)">发布</a>
          <a-divider type="vertical" />
          <a @click="handleState(record,0)">删除</a>
        </template>
      </span>
    </s-table>
  </a-card>
</template>

<script>
import { STable } from '@/components'

const statusMap = {
  0: {
    status: 'error',
    text: '删除'
  },
  1: {
    status: 'success',
    text: '发布'
  },
  2: {
    status: 'warning',
    text: '草稿'
  }
}

export default {
  name: 'ArticleList',
  components: {
    STable
  },
  data () {
    return {
      // 高级搜索 展开/关闭
      advanced: false,
      // 查询参数
      queryParam: {},
      // 表头
      columns: [
        {
          title: 'id',
          dataIndex: 'id'
        },
        {
          title: '标题',
          dataIndex: 'title'
        },
        {
          title: '封面图',
          dataIndex: 'image',
          scopedSlots: { customRender: 'image' }
        },

        {
          title: '分类',
          dataIndex: 'type.name'
        },
        {
          title: '标签',
          dataIndex: 'tagList',
          scopedSlots: { customRender: 'tagList' }
        },
        {
          title: '是否原创',
          dataIndex: 'original',
          scopedSlots: { customRender: 'original' }
        },
        {
          title: '状态',
          dataIndex: 'state',
          scopedSlots: { customRender: 'state' }
        },
        {
          title: '浏览次数',
          dataIndex: 'views'
        },
        {
          title: '评论次数',
          dataIndex: 'comments'
        },
        {
          title: '创建时间',
          dataIndex: 'createTime'
        },
        {
          title: '更新时间',
          dataIndex: 'updateTime'
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
        console.log('loadData.parameter', parameter)
        return this.api.getArticleList(Object.assign(parameter, this.queryParam))
          .then(res => {
            console.log(res.data)
            return res.data
          })
      }
    }
  },
  filters: {
    statusFilter (type) {
      return statusMap[type].text
    },
    statusTypeFilter (type) {
      return statusMap[type].status
    }
  },
  created () {
  },
  methods: {
    handleEdit (record) {
      this.$router.push('/article/articleEdit/' + record.id)
    },
    handleState (record, state) {
      const parameter = {
        id: record.id,
        state: state
      }
      this.api.articleUpdateState(parameter).then(res => {
        if (res && res.code === 200) {
          this.$message.info(`操作成功`)
        } else {
          this.$message.error(res.message)
        }
      })
    },
    toggleAdvanced () {
      this.advanced = !this.advanced
    }
  }
}
</script>
