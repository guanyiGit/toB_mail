<template>
  <!--分页-->
  <div class="fen c-b ovh mgt20 w426 mgb30">
    <el-pagination class="bgcpage"
                   background
                   :page-size="pageData.pageSize"
                   layout="prev, pager, next"
                   :current-page="pageData.curPage"
                   :total="pageData.totalCount"
                   @prev-click="chekdPage"
                   @next-click="chekdPage"
                   @current-change="chekdPage"
    >
    </el-pagination>

  </div>
  <!--分页-->
</template>

<script>

  export default {
    name: "pageVue",
    data() {
      return {
      }
    },
    props: {
      pageData: {//分页数据
        type: Object,
        default: function () {
          return {
            curPage: 1,//当前页吗
            totalCount: 1,//总条数
            pageSize: 1,//页面大小
          }
        }
      }
    },

    methods: {
      chekdPage(index) {//点击切换页面
        this.$emit('chekdpageCallcak', index)
      },
    }
  }

</script>

<style scoped>
  .el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color: rgb(253, 163, 118);
  }

  .bgcpage{
    display: flex;
    justify-content: center;
  }

  .pageCls {
    height: 200px;
  }
</style>
