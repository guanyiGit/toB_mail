<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">

    <div>
      <p class="fs18 h28 lh28 cor0">执行计划</p>
      <div class="mgt30">
        <div class="ovh mgt20">
          <ul class="ulDiv fl">
            <li class="fl mgr30"><router-link  :to="{name:'addDeliveryPlan'}" href="javascript:void (0)" class="db pdl29 corED145B ios2" >新增执行计划</router-link></li>
          </ul>
          <div class="fr">
            <div class="form-inline" @keyup.13="search">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="执行计划名称/编号" v-model="params.searchKey">
                <span class="input-group-btn">
							<button type="button" class="btn bged145b" @click="search">
								<i class="glyphicon glyphicon-search corw"></i>
							</button>
						</span>
              </div>
            </div>
          </div>
        </div>

        <div class="mgt30">
          <table class="tab2">
            <tbody>
            <tr>
              <td>执行计划编号</td>
              <td>执行计划名称</td>
              <td>操作</td>
            </tr>
            <tr v-for="(item,index) in planList">
              <td>{{item.planCode}}</td>
              <td>{{item.planName}}</td>
              <td>
                <router-link href="javascript:void (0)" class="btn btn-xs btn-success" :to="{name:'deliveryPlanDetail',query:{planId:item.id,type:item.type}}">查看</router-link>
                <router-link href="javascript:void (0)" class="btn btn-xs btn-primary" :to="{name:'editDeliveryPlan',query:{planId:item.id,type:item.type}}">编辑</router-link>
                <a href="javascript:void (0)" class="btn btn-xs btn-danger" @click="deletee(item.id)">删除</a>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
      <!--分页-->
      <div class="fen c-b ovh mgt30 t-c w410">
        <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
      </div>
      <!--分页END-->
    </div>







  </div>

</template>

<style>

</style>

<script>
  var _this = '';
  export default {
    name: "deliveryPlan",
    data() {
      return {
        params:{orgId: 1, pageNum: 1, pageSize:10, searchKey:''},

        planList:[],
        pageData:{
          curPage:1,//当前页吗
          totalCount:1,//总条数
          pageSize:1//页面大小
        },
      }
    },
    created() {
      this.configJS.getUserInfo(this);
      _this.params.orgId=this.configJS.userInfo.orgId;
      selectplanList();
    },beforeCreate(){
      _this = this;
    },
    mounted(){

    },
    methods: {
      chekdpage: function(pageNum) {
        _this.params.pageNum = pageNum
        selectplanList();
      },
      search:function () {
        selectplanList();
      },
      deletee:function (planId) {
        _this.$confirm('确定删除吗？').then(_ => {
          deletee(planId);
        }).catch(_ => {
        });

      }

    },

  }
  function selectplanList(){    //查询商品列表
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/plan/planList',
      params:  _this.params
    }).then((res) => {
      if (res.status == 200) {
        console.log(res);
        _this.planList=res.data.rows;
        _this.pageData.totalCount=res.data.total;//总条数
        _this.pageData.curPage= _this.params.pageNum;//当前页吗
        _this.pageData.pageSize= _this.params.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  function  deletee(planId) {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/plan/deletePlan',
      params: {planId:planId}
    }).then((res) => {
      if (res.status == 200) {
        selectplanList();
      }
    })
  }

</script>
