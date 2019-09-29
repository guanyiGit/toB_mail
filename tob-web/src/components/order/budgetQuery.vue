<template>
  <!--内容区域-->
  <div class=" pdt30 pdb30 pdr15 set pdl20 pdr20">
    <div class="">
      <p class="fs18 h28 lh28 cor0">预算查询</p>
    </div>

    <div class="fl mal20 mgl20" style="margin-left: 0px;margin-top: 10px;">
      <select class="form-control ios0 cursor" v-model="map.subjectId" @change="selectSub">
        <option value="0">所属科目</option>
        <option  :value="item.subjectId" v-for="(item,index) in subjectList">{{item.subName}}</option>
      </select>
    </div>


    <div class="mgt30">
      <ul class="ul0004">
        <!--内容1-->
        <li class="db">
          <div class="ovh mgt20">
            
          </div>
          <div class="mgt30">
            <table class="tab2">
              <tbody>
              <tr>
                <td>年度</td>
                <td>生效月份</td>
                <td>预算使用部门</td>
                <td>所属科目</td>
                <td>预算额</td>
                <td>已使用预算</td>
                <td>可用预算</td>
                <td>操作</td>
              </tr>
              <tr v-for="(item,index) in budgetUseList">
                <td>{{item.budgetYear}}</td>
                <td>{{item.effectiveMonth}}</td>
                <td>{{item.orgName}}</td>
                <td>{{item.subName}}</td>
                <td>{{item.budgetAmount}}</td>
                <td>{{item.actualUse}}</td>
                <td>{{item.budgetAmount-item.actualUse}}</td>
                <td>
                  <router-link class="btn btn-xs btn-primary" :to="{name:'budgetUseDetail',query:{budgetYear:item.budgetYear,subjectId:item.subjectId,orgId:item.orgId}}">预算使用明细</router-link>
                  <template v-if="item.planId!=null">
                    <router-link class="btn btn-xs btn-success" :to="{name:'budgetUseSchedule',query:{planId:item.planId,amount:item.budgetAmount,budgetYear:item.budgetYear,subjectId:item.subjectId,orgId:item.orgId}}">执行进度</router-link>
                  </template>
                </td>

              </tr>
              </tbody>
            </table>
          </div>
        </li>

      </ul>
    </div>
    <!--分页-->
    <div class="fen c-b ovh mgt30 t-c w410">
      <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
    </div>
    <!--分页END-->


  </div>
  <!--内容区域END-->
</template>

<script>
  var _this = '';
  export default {
    name: "budgetQuery",
    data(){
      return{
        map:{
          pageNum:1,
          pageSize:7,
          currOrgId:32,
          subjectId:0,
          searchKey:null
        },
        budgetUseList:[],//使用列表
        subjectList:[],
        pageData:{
          curPage:1,//当前页码
          totalCount:0,//总条数
          pageSize:7//页面大小
        },

      }
    },
    beforeCreate(){
      _this = this;
    },
    created(){
      //获取用户信息
      this.configJS.getUserInfo(this);
      _this.map.currOrgId=this.configJS.userInfo.orgId;
      initSubjectList();//初始化所属科目
      initBudgetUseList();
    },
    methods:{
      chekdpage: function(pageNum) {//分页
        _this.map.pageNum = pageNum;
        _this.pageData.curPage = pageNum;
          initBudgetUseList();

      },
      selectSub:function () {//科目查询
        initBudgetUseList();
      }

    },
  }


  function initBudgetUseList() {//初始化预算使用列表
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/budget/budgetUseList',
      params:  _this.map
    }).then((res) => {
      if(res.data.code==200){
        _this.budgetUseList=res.data.obj.rows;
        _this.pageData.totalCount=res.data.obj.total;//总条数
        _this.pageData.curPage= _this.map.pageNum;//当前页吗
        _this.pageData.pageSize= _this.map.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }
  function initSubjectList() {//初始化科目下拉框
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/budget/findSubjectList',
      params:{currOrgId:_this.map.currOrgId}
    }).then((res) => {
      if(res.data.code==200){
        console.log(res)
        _this.subjectList= res.data.obj;
      }
    }).catch((err) => {
      console.log(err);
    })
  }
</script>

<style scoped>

</style>
