<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="ovh div1 h50 rel">
      <p class="abs ww100 left0 bottom0"></p>
      <a href="javascript:void(0);" class="fl db fs16 cor3c3c3c w200 h50 lh50 t-c active" @mouseover.prevent="checkTab = 0">待审核</a>
      <a href="javascript:void(0);" class="fl db fs16 cor3c3c3c w200 h50 lh50 t-c" @mouseover.prevent="checkTab = 3">已审核</a>
    </div>
    <div class="div12 db mgt20">
      <div class="ovh">
        <div class="fl cor6 h40 lh40">
          <!--<router-link :to="{name:'operator_msgAdd'}" class="db fl hov8 corffa472 mgr35 pc9 pdl36">新增</router-link>-->
        </div>
        <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
          <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="标题" v-model="keyword">
          <input type="submit" value="" class="in1 db abs z100 top0 bort right0">
        </form>
      </div>
      <div class="ovh k3 mgt20">
        <table class="table11" width="100%">
          <tbody>
          <tr>
            <td class="ww24">标题</td>
            <td>类型</td>
            <td>发布单位</td>
            <td>发布时间</td>
            <td>操作</td>
          </tr>
          <tr v-for="(item,index) in  msgList" :key="index">
            <td>{{item.infoTitile}}</td>
            <td>{{item.informationTypeName}}</td>
            <td>{{item.pubishOrg}}</td>
            <td>{{item.creationTime | formatDate(datePattern)}}</td>
            <td>
              <router-link style="cursor:pointer;" class="dinb hov8 cor009cff" :to="{name:'operator_msgInfo',params:{'id':item.informationId}}">审核</router-link>
            </td>
          </tr>

          </tbody>
        </table>

      </div>
    </div>
    <div class="div12 dn mgt20">
      <div class="ovh">

        <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
          <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="标题">
          <input type="submit" value="" class="in1 db abs z100 top0 bort right0">
        </form>
      </div>
      <div class="ovh k3 mgt20">
        <table class="table11" width="100%">
          <tbody>
          <tr>
            <td class="ww24">标题</td>
            <td>类型</td>
            <td>发布单位</td>
            <td>审批时间</td>
            <td>审批状态</td>
            <td>操作</td>
          </tr>

          <tr v-for="(item,index) in  msgList" :key="index" v-if="msgList && msgList.length>0">
            <td>{{item.infoTitile}}</td>
            <td>{{item.informationTypeName}}</td>
            <td>{{item.pubishOrg}}</td>
            <td>{{item.creationTime | formatDate(datePattern)}}</td>
            <td>
              <a class="dinb a9 active">{{item.mark==1?'审核通过':item.mark==2?'审核不通过':''}}</a>
            </td>
            <td>
              <router-link style="cursor:pointer;" class="dinb hov8 cor009cff" :to="{name:'infoDetails',params:{'id':item.informationId}}">查看</router-link>
            </td>
          </tr>
          </tbody>
        </table>

      </div>
    </div>
    <!--分页-->
    <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
    <!--分页-->
  </div>
</template>

<script>

  //请求参数pageData
  let params = {
    pageSize: 9,
    pageNum: 1,
    status:0,//0.待审核 1.审核通过 2.审核不通过
    query: null,
  }
  export default {
    name: "operatorMsgAuditLis",
    data() {
      return {
        checkTab:1,//页签tab
        datePattern:'yyyy年MM与dd日',
        totalCount: 0,//总条数
        msgList: [],//资讯列表
        keyword:null,//搜索关键字
      }
    },
    methods: {
      //分页
      chekdpage(currentPage) {
        params.pageNum = currentPage//页码
        initData(this)
      },
    },
    computed: {
      //分页参数
      pageData() {
        return {
          curPage: params.pageNum,//当前页吗
          totalCount: this.totalCount,//总条数
          pageSize: params.pageSize//页面大小}
        }
      },
    },
    created() {
      initData(this)
    },
    mounted(){
      var $tab3= $('.div1>a');
      $tab3.mouseover(function(){
        $(this).addClass('active').siblings().removeClass('active');
        var i= $tab3.index(this);
        $('.div12').eq(i).show().siblings('.div12').hide();
      });
    },
    watch:{
      checkTab(newVal,oldVal){
        if(newVal != oldVal) {
          params.status =  newVal;//切换tab
          initData(this)
        }
      },
      keyword(newVal){
        if(newVal){
          params.query = newVal;
          initData(this)
        }
      }
    }

  }

  function initData(_this) {
    if (!params.query)
      delete  params.query
    _this.$axios({
      url: '/biz/information/getInformations',
      method: 'post',
      params: params
    }).then(res => {
      if(res.data.status == 200){
        _this.totalCount = res.data.data.total;
        _this.msgList = res.data.data.list;
      }
    })
  }

</script>

<style scoped>

</style>
