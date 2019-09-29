<template>
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">执行进度计划详情</p>
        <a href="###" class="db fr fs18 cor0" @click.prevent="$router.go(-1)">返回>></a>
      </div>
      <div class="mgt30">
        <div class="w100 fl h40 lh40 corED145B">执行计划名称:</div>
        <div class="mgl100"><input type="text" class="db h40 lh40 bor-d ww100" v-model="plan.planName" readonly></div>

        <div class="w100 fl h40 lh40 corED145B mgt20">执行计划编号:</div>
        <div class="mgl100 mgt20"><input type="text" class="db h40 lh40 bor-d ww100"  v-model="plan.planCode" readonly></div>
      </div>
      <form action="#">
        <div class="mgt30">
          <div class="tp2" v-if="oneShow">
            <table class="table2 ww100 t-c">
            <tbody>
            <tr>
              <td class="pdt25 pdb25 cor0 ww50">月度</td>
              <td class="pdt25 pdb25 cor0 ww50">预算进度</td>
            </tr>
            <tr v-for="(item,index) in planDetailList">
              <td class="pdt25 pdb25 cor0">{{index+1}}</td>
              <td class="pdt25 pdb25 cor0">{{item.planRate}}%</td>
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
              <td class="pdt25 pdb25 cor0">{{item.planRate}}%</td>
            </tr>
            </tbody>
            </table>
          </div>
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
        oneShow:false,
        twoShow:false,
        plan:{},
        planDetailList:[]
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

    methods: {}
  }
  //detail
  function  findDetail(planId) {
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/plan/findDetail',
      params: {planId:planId}
    }).then((res) => {
        console.log(res);
      if (res.data.success) {
          _this.plan=res.data.obj.plan;
          _this.planDetailList=res.data.obj.planDetailList;
      }
    })
  }
</script>
