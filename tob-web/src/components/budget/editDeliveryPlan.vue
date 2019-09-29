<template>
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">编辑执行进度计划</p>
        <router-link  :to="{name:'deliveryPlan'}" href="javascript:void (0)" class="db fr fs18 cor0">返回</router-link>
      </div>
      <div class="mgt30">
        <div class="w100 fl h40 lh40 corED145B">执行计划名称:</div>
        <div class="mgl100"><input type="text" class="db h40 lh40 bor-d ww100" v-model="plan.planName" ></div>

        <div class="w100 fl h40 lh40 corED145B mgt20">执行计划编号:</div>
        <div class="mgl100 mgt20"><input type="text" class="db h40 lh40 bor-d ww100"  v-model="plan.planCode"></div>
      </div>
      <form action="#">
        <div class="mgt30">
          <div class="tp2" v-if="oneShow">
            <table class="table2 ww100 t-c">
              <tbody>
              <tr>
                <td class="pdt25 pdb25 cor0 ww50">月度(%)</td>
                <td class="pdt25 pdb25 cor0 ww50">预算进度(%)</td>
              </tr>
              <tr v-for="(item,index) in planDetailList">
                <td class="pdt25 pdb25 cor0">{{index+1}}</td>
                <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="item.planRate"></td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="tp2" v-if="twoShow">
            <table class="table2 ww100 t-c">
              <tbody>
              <tr>
                <td class="pdt25 pdb25 cor0 ww50">季度</td>
                <td class="pdt25 pdb25 cor0 ww50">预算进度</td>
              </tr>
              <tr v-for="(item,index) in planDetailList">
                <td class="pdt25 pdb25 cor0">{{index+1}}</td>
                <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="item.planRate"></td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>

        <div class="mgt40 t-c">
          <a href="javascript:void (0)" class="dinb w120 btn btn-primary mgr4" @click="updatee">保存</a>
          <router-link  :to="{name:'deliveryPlan'}" href="javascript:void (0)" class="dinb w120 btn btn-danger">取消</router-link>
        </div>
      </form>
    </div>

  </div>
</template>

<style>

</style>

<script>
  var _this = '';
  export default {
    name: "deliveryPlanDetail",
    data() {
      return {
        blag:true,
        oneShow:false,
        twoShow:false,
        plan:{},
        planDetailList:[],
        budgetVO:{},
      }


    },
    created() {

      var planId=this.$route.query.planId;
      var type=this.$route.query.type;
      if(type==0){
        _this.oneShow=true;
      }else{
        _this.twoShow=true;
      }
      findDetail(planId);


    },beforeCreate(){
      _this = this;
    },

    methods: {
        updatee:function () {
          if( $.trim(_this.plan.planName)==''){
            _this.$alert("执行计划名称不能为空");
            return false
          }
          if($.trim(_this.plan.planCode)==''){
            _this.$alert("执行计划编号不能为空");
            return false
          }
          update();
        }
    }
  }
  //detail
  function  findDetail(planId) {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/plan/findDetail',
      params: {planId:planId}
    }).then((res) => {
      if (res.data.success) {
        _this.plan=res.data.obj.plan;
        _this.planDetailList=res.data.obj.planDetailList;
      }
    })
  }

  //update
  function update() {
      _this.blag=true;
      _this.budgetVO.plan=_this.plan;
      _this.budgetVO.planDetailList=_this.planDetailList;
    _this.budgetVO.planDetailList.forEach(item=>{
      if(item.planRate>100 || item.planRate<0 || item.planRate==null || $.trim(item.planRate)==''){
        _this.blag=false;
        return false;
      }
    });
    if(_this.blag==false){
      _this.$alert("预算进度输入不正确");
      return false;
    }

    _this.$axios({
      method: 'post',
      url: '/biz/tob-budget/biz/plan/updatePlan',
      headers:{
        'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
      },
      data: JSON.stringify(_this.budgetVO)
    }).then((res) => {
      if(res.status==200){
        _this.$router.push({name: 'deliveryPlan'});
        _this.$alert("修改成功");

      }
    })
  }
</script>
