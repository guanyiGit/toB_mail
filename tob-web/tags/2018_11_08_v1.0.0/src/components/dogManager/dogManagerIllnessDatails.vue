<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li><router-link :to="{name:'dogManager_ImmuneList'}">免疫登记></router-link></li>
        <li>病历记录</li>
      </ul>
    </div>
    <div class="mgt20 k3">
      <table class="table10 t-c" width="100%" border="1">
        <tbody>
        <tr>
          <td>免疫证号</td>
          <td class="cor9"  v-if="Datails.dogImmune">{{Datails.dogImmune.immuneCardNum}}</td>
          <td>犬证号</td>
          <td class="cor9" v-if="Datails.tDogCard">{{Datails.tDogCard.dogCardNum}}</td>
        </tr>
        <tr v-if="Datails.dogOwner">
          <td>犬主姓名</td>
          <td class="cor9">{{Datails.dogOwner.dogOwnerName}}</td>
          <td>联系电话</td>
          <td class="cor9">{{Datails.dogOwner.dogOwnerPhone}}</td>
        </tr>
        <tr>
          <td>犬只名称</td>
          <td class="cor9" v-if="Datails.dogName">{{Datails.dogName}}</td>
          <td>性别</td>
          <td class="cor9">{{Datails.dogGender==0?'公':'母'}}</td>
        </tr>
        <tr>
          <td>犬只品种</td>
          <td class="cor9" v-if="Datails.dogBreedDictionary">{{Datails.dogBreedDictionary.dictionaryDescribe}}</td>
          <td>犬龄</td>
          <td class="cor9" v-if="Datails.birthTime">{{Datails.birthTime}}</td>
        </tr>
        <tr>
          <td>毛色</td>
          <td class="cor9" v-if="Datails.dogColorDictionary">{{Datails.dogColorDictionary.dictionaryDescribe}}</td>
          <td></td>
          <td class="cor9"></td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="ovh mgt20">
      <div class="fl cor6 h40 lh40">
        <router-link :to="{name:'dogManager_IllnessAdd'}"class="db fl hov8 corffa472 mgr35 pc9 pdl36">新增</router-link>
      </div>
    </div>

    <div class="mgt10 k3 t-c">
      <table class="table5" width="100%" border="1">
        <tbody>
        <tr>
          <td class="cor0 h60 bgf1">时间</td>
          <td class="cor0 h60 bgf1">病历记录</td>
          <td class="cor0 h60 bgf1">诊断</td>
          <td class="cor0 h60 bgf1">治疗</td>
          <td class="cor0 h60 bgf1">备注</td>
        </tr>
        <tr v-for="(item,index) in DatailList":key="index">
          <td class="cor3">{{item.creationTime|formatDate(dataFormatPattern)}}</td>
          <td class="cor3">{{item.symptom}}</td>
          <td class="cor3">{{item.diagnose}}</td>
          <td>{{item.treatmentInfo}}</td>
          <td>{{item.remarks}}</td>
        </tr>

        </tbody>
      </table>
    </div>

  </div>
  <!--内容区END-->
</template>

<script>
  import  conf from "../../common/config"
    export default {
        name: "dogManagerIllnessDatails"
      ,data(){
          return{
            DatailList:{}//病历集合
            ,Datails:''
            ,dataFormatPattern :'yyyy年MM月dd日'

          }
      },created(){
          initData(this);
          initDatas(this);

      }
    }
    function initData(_this) {
      _this.$axios({
        url: '/biz/dogInfo/findDetailTherapyList',
        params: {"dogId": _this.$route.params.dogId,"orgId":conf.userInfo.organizationId}
      }).then((res) => {
        _this.DatailList=res.data

      }).catch((res)=>{
        console.log(res.message)
      });

    }
    function initDatas(_this) {
      _this.$axios({
        url: '/biz/dogInfo/findTherapyDetail',
        params: {"dogId": _this.$route.params.dogId,"orgId":conf.userInfo.organizationId}
      }).then((res) => {
        _this.Datails=res.data
        _this.Datails.birthTime=CalAge(_this.Datails.birthTime)
      }).catch((res)=>{
        console.log(res.message)
      })
    }
  function CalAge(date1) {
    var datetime1 = new Date(date1);
    var datetime2 = new Date();
    var year1 = datetime1.getFullYear();
    var year2 = datetime2.getFullYear();
    var age=year2-year1+1;
    return age+"岁";
  }


</script>

<style scoped>
  td{
    text-align:center;
  }
</style>
