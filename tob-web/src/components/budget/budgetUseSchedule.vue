<template>
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">执行进度计划</p>
        <a style="cursor:pointer;" class="db fr fs18 cor0" @click="returnn">返回>></a>
      </div>

      <div>
        <div class="mgt30">
          <div class="tp2 db">
            <table class="table2 ww100 t-c">
            <tbody>
            <tr>
              <td class="pdt25 pdb25 cor0 ww25">月度/季度</td>
              <td class="pdt25 pdb25 cor0 ww25">预算进度(计划%)</td>
              <td class="pdt25 pdb25 cor0 ww25">预算进度(实际%)</td>
              <td class="pdt25 pdb25 cor0 ww25">状态</td>
            </tr>
            <tr v-for="(item,index) in budgetUseScheduleList">
              <template v-if="item.type===0">
                <td class="pdt25 pdb25 cor0 ww25">{{item.planMonth}}</td>
                <td class="pdt25 pdb25 cor0 ww25">{{item.planRate}}</td>
                <td class="pdt25 pdb25 cor0 ww25">{{item.actualUseRate?item.actualUseRate:'0'}}</td>
                <td class="pdt25 pdb25 cor0 ww25">
                  <template v-if="item.actualUseRate ==null">
                  </template>
                  <template v-else-if="item.planRate-item.actualUseRate>=0">
                      <p>正常</p>
                  </template>
                  <template v-else>
                    <span style="color:red">超额{{item.actualUseRate-item.planRate}}%</span>
                  </template>
                </td>
              </template>
              <template v-else>
                <td class="pdt25 pdb25 cor0 ww25">{{item.planQuarter}}</td>
                <td class="pdt25 pdb25 cor0 ww25">{{item.planRate}}</td>
                <td class="pdt25 pdb25 cor0 ww25">{{item.actualUseRate?item.actualUseRate:'0'}}</td>
                <td class="pdt25 pdb25 cor0 ww25">
                  <template v-if="item.planRate-item.actualUseRate>=0">
                    <p>正常</p>
                  </template>
                  <template v-else>
                    <p style="color:red">超额{{item.actualUseRate-item.planRate}}%</p>
                  </template>
                </td>
              </template>

            </tr>

            </tbody>
            </table>
          </div>


        </div>
      </div>>
    </div>
  </div>

</template>

<script>
  var _this = '';
  export default {
    name: "budgetUseSchedule",
    data(){
      return{
        map:{
          budgetYear:0,
          subjectId:0,
          orgId:0,
          planId:0,
          budegtAmount:0
        },
        budgetUseScheduleList:[],
        myTab:null

      }
    },
    mounted(){

    },
    created(){
      _this.map.budgetYear=this.$route.query.budgetYear;
      _this.map.subjectId=this.$route.query.subjectId;
      _this.map.orgId=this.$route.query.orgId;
      _this.map.budegtAmount=this.$route.query.amount;
      _this.map.planId=this.$route.query.planId;
      this.myTab=this.$route.query.myTab;
      findSchedule();
    },
    beforeCreate(){
      _this=this;
    },
    methods:{
      returnn(){
        // this.$router.back(-1);
        this.$router.push({name:'bm_budgetManage',params:{myTab:this.myTab}});
      }
    }

  }
  function findSchedule(){//初始化预算使用详细执行进度
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/budget/findSchedule',
      params:  _this.map
    }).then((res) => {
      debugger
      if(res.data.code==200){
        _this.budgetUseScheduleList=res.data.obj;
      }
    }).catch((err) => {
      console.log(err);
    })
  }
</script>

<style scoped>

</style>
