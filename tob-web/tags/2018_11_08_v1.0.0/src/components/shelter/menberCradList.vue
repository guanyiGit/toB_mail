<template>
  <!--内容区-->
  <div class="w980 fr">
    <!--<div class="mbx">
            <ul class="ul11 clearfix">
                <li><a href="###">犬只档案></a></li>
                <li>查看详情</li>
            </ul>
        </div>-->
    <div class="ovh mgt20">
      <div class="fl cor6 h40 lh40">
        <router-link :to="{name:'hospice_memberCardAdd'}" class="db fl hov8 corffa472 mgr35 pc9 pdl36">会员证办理</router-link>
      </div>
      <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
        <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" v-model="searchKey" placeholder="姓名/身份证号">
        <input type="submit" value="" class="in1 db abs z100 top0 bort right0 cups" @click.prevent="clickSearch">
      </form>
    </div>
    <div class="k3 mgt20">
      <table class="table11" width="100%">
        <tbody>
        <tr>
          <td>会员证号</td>
          <td>犬主姓名</td>
          <td>发证时间</td>
          <td>电话</td>
          <td>办理时间</td>
          <!--<td>办理人</td>-->
          <td>办理机构</td>
          <td>操作</td>
        </tr>

        <tr v-for="(item,index) in  list" :key="index">
          <td>{{item.memberCardNo.length>10 ?item.memberCardNo.substr(0,8):item.memberCardNo}}</td>
          <td>{{item.tDogOwner.dogOwnerName}}</td>
          <td>{{item.issueDate | formatDate(datePattern)}}</td>
          <td>{{item.memberPhone}}</td>
          <td>{{item.creationTime | formatDate(datePattern)}}</td>
          <!--<td>王晓燕</td>-->
          <td>{{item.tOrganization.organizationName}}</td>
          <td><router-link :to="{name:'hospice_memberCardInfo',params:{cardId:item.memberCardId}}" class="dinb hov8 cor009cff">查看详情</router-link></td>
        </tr>
        </tbody>
      </table>
    </div>
    <!--分页-->
    <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
    <!--分页-->
    <!--分页-->
    <!--<div class="t-c mgt40 mgb50">
            <input type="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" value="保存">
            <a href="###" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</a>
        </div>-->
  </div>
  <!--内容区END-->
</template>

<script>
  let params = {
    offset:1,
    limit:6,
    keyWord:null,
  }

  export default {
    name: "menberCradList",
    data() {
      return {
        datePattern:"yyyy年MM月dd日",
        list:[],//证书列表
        searchKey:null,//搜索关键字
        totalCount:0,
      }
    },
    watch:{
      searchKey(newVal,oldVal){
        if(newVal && newVal.trim().length>0 && newVal != oldVal) {
          params.keyWord = newVal;
          initData(this)
        }
      }
    },
    methods:{
      clickSearch(){
        initData(this);
      },
      //分页
      chekdpage(currentPage) {
        params.offset = currentPage//页码
        initData(this)
      },
    },
    computed: {
      //分页参数
      pageData() {
        return {
          curPage: params.offset,//当前页吗
          totalCount: this.totalCount,//总条数
          pageSize: params.limit//页面大小}
        }
      },

    },
    created(){
      initData(this);
    }
  }

  function initData(_this) {
    _this.$axios({
      url:'/biz/member/list',
      params,
      isloading:true
    }).then(res=>{
      if(res.data.meta.success){
        _this.list = res.data.data.list;
        _this.totalCount = res.data.data.total
      }
      console.log(_this.$data)
    })
  }
</script>

<style scoped>
.cups{
  cursor: pointer;
}
</style>
