<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li>
          <a href="###" @click.prevent="toMyIndex">我的主页 ></a>
        </li>
        <li>我的消息</li>
      </ul>
    </div>
    <div class="ovh k3 mgt20">
      <div class="pdt6 pdb15 pdl30 pdr30">
        <el-collapse accordion v-if="list && list.length>0">
          <el-collapse-item v-for="(item,index) in list" :key="index"
                            class="db cor3 lh45 hov8 pdl24 pc3 ">
            <template slot="title" class="titile">
              <span>{{item.notifTitle}}</span>
              <span class="fr cor9 mar30">{{item.creationTime | formatDate(pattern)}}</span>
            </template>
            <div>{{item.notiContent}}</div>
          </el-collapse-item>
        </el-collapse>
        <div v-if="!list || list.length==0">暂无消息</div>

        <!--<ul class="ul13">-->
        <!--<li v-for="(item,index) in list" :key="index">-->
        <!--<a href="###" class="db cor3 h45 lh45 hov8 pdl24 pc3">-->
        <!--{{item.notifTitle}}<span class="fr cor9">{{item.creationTime | formatDate(pattern)}}</span>-->
        <!--</a>-->
        <!--</li>-->
        <!--</ul>-->
      </div>
    </div>
    <!--分页-->
    <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
    <!--分页-->
  </div>
  <!--内容区END-->
</template>

<script>

  import configJs from '../../common/config'

  //请求参数
  let params = {
    offset: 1,
    limit: 10,
    receiverd: configJs.userInfo.userId
  }
  export default {
    name: "message",
    data() {
      return {
        pattern: 'yyyy/MM/dd',
        totalCount: 0,
        list: []
      }
    },
    computed: {
      //分页参数
      pageData() {
        return {
          curPage: params.offset,//当前页吗
          totalCount: this.totalCount,//总条数
          pageSize: params.limit//页面大小}
        }
      }
    },
    methods: {
      //分页
      chekdpage(currentPage) {
        params.offset = currentPage//页码
        initData(this)
      },
      //跳到我的首页
      toMyIndex() {
        configJs.resource.memu.map(item => {
          if (item.level == 2 && item.seq == 1) {
            this.$router.push({name: item.link});
          }
        })
      }
    },
    created() {
      initData(this)
    }
  }

  function initData(_this) {
    _this.$axios({
      url: '/biz/notice/selectNoticeByReceiverd',
      method: 'post',
      params
    }).then(res => {
      _this.totalCount = res.data.data.total;
      _this.list = res.data.data.list;
      console.log(_this.list)
    })
  }
</script>

<style scoped>
  .pc3 {
    background-position-y: 16px;
  }

  .mar30 {
    margin-right: 30px;
  }
</style>
