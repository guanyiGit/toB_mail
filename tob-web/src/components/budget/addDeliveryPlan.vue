<template>
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">新增执行进度计划</p>
        <a href="###" class="db fr fs18 cor0" @click.prevent="$router.go(-1)">返回>></a>
      </div>
      <div class="mgt30">
        <div class="w100 fl h40 lh40 corED145B">执行计划名称:</div>
        <div class="mgl100"><input type="text" class="db h40 lh40 bor-d ww100" v-model="budgetVO.plan.planName"></div>

        <div class="w100 fl h40 lh40 corED145B mgt20">执行计划编号:</div>
        <div class="mgl100 mgt20"><input type="text" class="db h40 lh40 bor-d ww100" v-model="budgetVO.plan.planCode"></div>
      </div>
      <div class="mgt30 ovh tp1">
        <a href="javascript:void (0)" class="db fl active" @click="changeOne">按月</a>
        <a href="javascript:void (0)" class="db fl" @click="changeTwo">按季度</a>
      </div>
      <form action="#">
        <div class="mgt30">
          <div class="tp2 db" >
            <table class="table2 ww100 t-c">
            <tbody>
            <tr>
              <td class="pdt25 pdb25 cor0 ww50" >月度</td>
              <td class="pdt25 pdb25 cor0 ww50" >预算进度</td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">1</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail1.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">2</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail2.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">3</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail3.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">4</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail4.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">5</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail5.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">6</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail6.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">7</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail7.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">8</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail8.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">9</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail9.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">10</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail10.planRate"></td>
            </tr> <tr>
              <td class="pdt25 pdb25 cor0">11</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail11.planRate"></td>
            </tr> <tr>
              <td class="pdt25 pdb25 cor0">12</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail12.planRate"></td>
            </tr>

            </tbody>
            </table>
          </div>
          <div class="tp2 dn" >
            <table class="table2 ww100 t-c">
            <tbody>
            <tr>
              <td class="pdt25 pdb25 cor0 ww50">季度</td>
              <td class="pdt25 pdb25 cor0 ww50">预算进度</td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">1</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail13.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">2</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail14.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">3</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail15.planRate"></td>
            </tr>
            <tr>
              <td class="pdt25 pdb25 cor0">4</td>
              <td class="pdt25 pdb25 cor0"><input type="text" class="db ww100 t-c inp0 bort" placeholder="%" v-model="planDetail16.planRate"></td>
            </tr>
            </tbody>
            </table>
          </div>

        </div>
        <div class="mgt40 t-c">
          <a href="javascript:void (0)" class="dinb w120 btn btn-primary mgr4" @click="save">保存</a>
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
    name: "addDeliveryPlan",
    data() {
      return {
        blag:true,
        tiggle:1,
        budgetVO:{
          plan:{planName:'', planCode:'',type:0},

          planDetailList:[]
        },
        //按月
        planDetail1:{planMonth:1,planRate:null,type:0},
        planDetail2:{planMonth:2,planRate:null,type:0},
        planDetail3:{planMonth:3,planRate:null,type:0},
        planDetail4:{planMonth:4,planRate:null,type:0},
        planDetail5:{planMonth:5,planRate:null,type:0},
        planDetail6:{planMonth:6,planRate:null,type:0},
        planDetail7:{planMonth:7,planRate:null,type:0},
        planDetail8:{planMonth:8,planRate:null,type:0},
        planDetail9:{planMonth:9,planRate:null,type:0},
        planDetail10:{planMonth:10,planRate:null,type:0},
        planDetail11:{planMonth:11,planRate:null,type:0},
        planDetail12:{planMonth:12,planRate:null,type:0},
        //按季度
        planDetail13:{planQuarter:1,planRate:null,type:1},
        planDetail14:{planQuarter:2,planRate:null,type:1},
        planDetail15:{planQuarter:3,planRate:null,type:1},
        planDetail16:{planQuarter:4,planRate:null,type:1},

      }


    },
    created() {
      this.configJS.getUserInfo(this);
      _this.budgetVO.plan.orgId=this.configJS.userInfo.orgId;
      _this.budgetVO.plan.createBy=this.configJS.userInfo.userId;
    },beforeCreate(){
      _this = this;
    },
    mounted(){
      var $tp= $('.tp1>a');
      $tp.click(function(){
        $(this).addClass('active').siblings().removeClass('active');
        var i= $tp.index(this);
        $('.tp2').eq(i).show().siblings('.tp2').hide();
      });
    },
    methods: {
      changeOne:function () {
        _this.tiggle=1;
        _this.budgetVO.plan.type=0;
      },changeTwo:function () {
        _this.tiggle=2;
        _this.budgetVO.plan.type=1;
      },
      save:function () {

        if( $.trim(_this.budgetVO.plan.planName)==''){
          _this.$alert("执行计划名称不能为空");
          return false
        }
        if( $.trim(_this.budgetVO.plan.planCode)==''){
          _this.$alert("执行计划编号不能为空");
          return false
        }
        if(_this.tiggle==1){
          _this.budgetVO.planDetailList=[];
            _this.budgetVO.planDetailList.push(_this.planDetail1);
            _this.budgetVO.planDetailList.push(_this.planDetail2);
            _this.budgetVO.planDetailList.push(_this.planDetail3);
            _this.budgetVO.planDetailList.push(_this.planDetail4);
            _this.budgetVO.planDetailList.push(_this.planDetail5);
            _this.budgetVO.planDetailList.push(_this.planDetail6);
            _this.budgetVO.planDetailList.push(_this.planDetail7);
            _this.budgetVO.planDetailList.push(_this.planDetail8);
            _this.budgetVO.planDetailList.push(_this.planDetail9);
            _this.budgetVO.planDetailList.push(_this.planDetail10);
            _this.budgetVO.planDetailList.push(_this.planDetail11);
            _this.budgetVO.planDetailList.push(_this.planDetail12);
        }else{
          _this.budgetVO.planDetailList=[];
          _this.budgetVO.planDetailList.push(_this.planDetail13);
          _this.budgetVO.planDetailList.push(_this.planDetail14);
          _this.budgetVO.planDetailList.push(_this.planDetail15);
          _this.budgetVO.planDetailList.push(_this.planDetail16);
        }
        _this.blag=true;
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
        saveDetailPlan();
      }
    },
  }
  //添加
  function saveDetailPlan() {
    _this.$axios({
      method: 'post',
      url: '/biz/tob-budget/biz/plan/addPlan',
      headers:{
        'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
      },
      data: JSON.stringify(_this.budgetVO)
    }).then((res) => {
      if(res.status==200){
        _this.$router.push({name: 'deliveryPlan'});
        _this.$alert("添加成功");

      }
    })
  }
</script>
