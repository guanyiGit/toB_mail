<template>
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">年度预算分配</p>
        <a style="cursor:pointer;" class="db fr fs18 cor0"  @click="returnn">返回>></a>
      </div>
      <div class="mgt20 bor-d pdt30 pdb50">
        <div class="w600 c-b">
          <form action="#">

            <div class="ovh mgt20" >
              <div class="fl w80 fs16 cor0 h40 lh40">年度：</div>
              <div class="mgl80">
                <select class="db bor-d ww100 h40 lh40 pdl10 pdr10 cor6 ios0" v-model="map.budgetYear" @change="selectYear">
                  <option  :value="item" v-for="(item,index) in yearList">{{item}}</option>
                </select>
              </div>
            </div>
            <div class="ovh mgt20">
              <div class="fl w80 fs16 cor0 h40 lh40">生效月份：</div>
              <div class="mgl80">
                <select class="db bor-d ww100 h40 lh40 pdl10 pdr10 cor6 ios0" v-model="map.effectiveMonth">
                  <option  :value="item" v-for="(item,index) in monthList">{{item}}</option>
                </select>
              </div>
            </div>
            <!--<div class="ovh mgt20">
              <div class="fl w80 fs16 cor0 h40 lh40">预算科目：</div>
              <div class="mgl80">
                <select class="db bor-d ww100 h40 lh40 pdl10 pdr10 cor6 ios0">
                  <option value="1">XXX总公司</option>
                  <option value="1">SSS公司</option>
                </select>
              </div>
            </div>-->
            <div class="ovh mgt20 fs16 corED145B fb">
              <p class="fl mgr40">{{map.budgetYear+'年'+orgName+'总预算:'+budgetAmount}}</p>
            </div>

            <div class="ovh mgt20">
              <div class="mgl80">
                <table class="tab3">
                  <tbody>
                  <tr>
                    <td class="ww33 h43 l43">序号</td>
                    <td class="ww33 h43 l43">分配科目</td>
                    <td class="ww33 h43 l43">预算额</td>
                  </tr>
                  <tr v-for="(item,index) in map.list" :key="index">
                    <td class=" h43 l43">{{index}}</td>
                    <td class=" h43 l43">{{item.subName}}</td>
                    <td class=" h43 l430">￥<input type="text" v-model="item.budgetAmount"></td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="mgt40 t-c">
              <input type="button" class="w100 btn btn-primary" value="保存" @click="save">
              <input type="button" class="w100 pdr30 btn btn-danger" value="取消"  @click="returnn">
            </div>
          </form>
        </div>
      </div>

    </div>
  </div>

</template>

<script>
  var _this = '';
  export default {
    name: "addBudgetBySub",
    data()
    {
      return{
        map:{
          budgetYear:0,
          effectiveMonth:1,
          list:null,
          type:0,
          currOrgId:32
        },
        orgName:null,
        budgetAmount:0,
        currOrgId:1,
        yearList:[],
        monthList:[1,2,3,4,5,6,7,8,9,10,11,12],
        myTab:null,
        isAdminBud:false,
        checkAllocation:false


      }
    },
    created(){
      //获取用户信息
      this.configJS.getUserInfo(this);
      _this.map.currOrgId=this.configJS.userInfo.orgId;
      _this.isAdminBud=this.configJS.isAdminBud();
      _this.myTab=this.$route.params.myTab;
      checkAllocation();//检查部门科目是否被分配
      initYearList();//初始化年份下拉框
      initSubList();//初始化下拉组织下拉框
      initOrgName();
      initBudgetAmount();//初始化本级组织预算
    },
    beforeCreate(){
      _this = this;
    },
    methods:{
      save:function () {
        beforeAdd();
      },
      returnn(){
        this.$router.push({name:'bm_budgetManage',params:{myTab:this.myTab}});
      },
      selectYear:function () {
        initBudgetAmount();
        initSubList();//初始化下拉组织下拉框
        checkAllocation();//检查部门科目是否被分配
      }
    }
  }

  function initYearList() {
    var year1=new  Date().getFullYear();
    var  year2=year1+1;
    _this.map.budgetYear=year2;
    var arr=new Array();
    arr.push(year1);
    arr.push(year2);
    _this.yearList=arr;
  }
  function initSubList() {//初始化下级科目
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/subject/getButtomSubs',
      params:{orgId:_this.map.currOrgId,year:_this.map.budgetYear,type:1}
    }).then((res) => {
      if(res.data.success && res.data.obj !=null){
        _this.map.list = res.data.obj;
      }

    }).catch((err) => {
      console.log(err);
    })
  }

  function beforeAdd() {

    if(!_this.isAdminBud && _this.budgetAmount==0){
      _this.$alert("当前部门无可分配预算额!");
      return;
    }
    if(_this.checkAllocation){
      _this.$alert(_this.map.budgetYear+"年度科目已分配预算,请勿重复分配!");
      return;
    }

    var temp=0;
    for (var i=0;i<_this.map.list.length;i++) {
      temp=Number(temp)+Number(_this.map.list[i].budgetAmount);
    }

    if(temp!=_this.budgetAmount){
      _this.$alert("您分配的科目预算额之和与本年度可用预算额不等!");
      return;
    }
    add();
  }


  function add() {
    let  list=_this.map.list.map(e=>{
      return{
          "orgId":_this.map.currOrgId,
          "subId":e.id,
          "budgetYear":_this.map.budgetYear,
          "effectiveMonth":_this.map.effectiveMonth,
          "subAmount":e.budgetAmount,
          "pId":_this.map.currOrgId
      }
    })
    _this.$axios({
      method: 'post',
      url: '/biz/tob-budget/biz/budget/addBudgetBySub',
      headers:{
        'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
      },
      data:JSON.stringify(list)
    }).then((res) => {
      if(res.data.code==200){
        _this.$router.push({name: 'bm_budgetManage'});
        _this.$alert("添加成功");

      }else{
        _this.$alert("添加失败");
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  function initOrgName() {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-user/system/orgInfo/getOrgDetail',
      params:{id:_this.map.currOrgId}
    }).then((res) => {
      if(res.data.code==200){

        _this.orgName=res.data.obj.orgInfo.orgName;
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  function initBudgetAmount() {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/budget/findAllocation2',
      params:{orgId:_this.map.currOrgId,year:_this.map.budgetYear}
    }).then((res) => {
      if(res.data.code==200 && res.data.obj !=null){
        _this.budgetAmount=res.data.obj.budgetAmount;
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  function checkAllocation() {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/budget/checkAllocation',
      params:{orgId:_this.map.currOrgId,year:_this.map.budgetYear}
    }).then((res) => {
      if(res.data.code==200 && res.data.obj !=0){
        /*_this.$alert("您已分配科目预算,请勿重复分配,可前往预算接收页面查看!");*/
        _this.checkAllocation=true;
      }
    }).catch((err) => {
      console.log(err);
    })
  }




</script>

<style scoped>

</style>
