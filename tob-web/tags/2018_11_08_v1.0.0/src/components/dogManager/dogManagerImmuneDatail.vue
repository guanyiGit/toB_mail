<template>
  <!--内容区-->
  <div class="w980 fr">
    <!--<div class="ovh">
            <div class="fl cor6 h40 lh40">
                <a href="###" class="db fl hov8 corffa472 mgr35 pc9 pdl36">免疫登记</a>
                <a href="###" class="db fl hov8 corffa472 mgr35 pc10 pdl36">移出</a>
                <a href="###" class="db fl hov8 corffa472 pc7 pdl36">发消息</a>
            </div>
            <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
                <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="姓名/身份证号">
                <input type="submit" value="" class="in1 db abs z100 top0 bort right0">
            </form>
        </div>-->
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li><router-link :to="{name:'dogManager_IllnessList'}">病历记录></router-link></li>
        <li>查看详情</li>
      </ul>
    </div>
    <div class="mgt20 k3">
      <table class="table10 t-c" width="100%">
        <tbody>
        <tr>
          <td>免疫证号</td>
          <td class="cor9">{{vaccine.immune_card_num}}</td>
          <td>犬主姓名</td>
          <td class="cor9">{{vaccine.dog_owner_name}}</td>
        </tr>
        <tr>
          <td>联系电话</td>
          <td class="cor9">{{vaccine.dog_owner_phone}}</td>
          <td>地址</td>
          <td class="cor9">{{vaccine.address}}</td>
        </tr>
        <tr>
          <td>犬只品种</td>
          <td class="cor9">{{vaccine.dictionary_describe1}}</td>
          <td>犬龄</td>
          <td class="cor9">{{vaccine.birth_time}}</td>
        </tr>
        <tr>
          <td>性别</td>
          <td class="cor9">{{vaccine.dog_gender==0?'公':'母'}}</td>
          <td>毛色</td>
          <td class="cor9">{{vaccine.dictionary_describe}}</td>
        </tr>
        <tr>
          <td>免疫时间</td>
          <td class="cor9">{{vaccine.fact_injection_date|formatDate(dataTime)}}</td>
          <td>免疫项目</td>
          <td class="cor9">{{vaccine.vaccine_name}}</td>
        </tr>
        <tr>
          <td>免疫厂家及批号</td>
          <td>{{vaccine.vaccine_producer}}</td>
          <td>接种兽医</td>
          <td>{{vaccine.name}}</td>
        </tr>
        <tr>
          <td>下次免疫时间</td>
          <td class="cor9" v-if="vaccine.next_injection_date">{{vaccine.next_injection_date|formatDate(dataTime)}}</td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td>备注</td>
          <td colspan="3">{{vaccine.remarks}}</td>
        </tr>
        </tbody>
      </table>
    </div>


  </div>
  <!--内容区END-->
</template>

<script>
    export default {
        name: "dogManagerImmuneDatail"
      ,data(){
          return{
            vaccine:{},//免疫详情对象
            dataTime:'yyyy/MM/dd'
          }
      },created(){
          initData(this);
      }
    }
    function initData(_this){
      _this.$axios({
        url:'/biz/dogInfo/getTVaccineId',
        params:{"id":_this.$route.params.VIId}
      }).then((ref=>{
        _this.vaccine=ref.data;
        _this.vaccine.birth_time=CalAge(_this.vaccine.birth_time)
      })).catch((ref=>{
        alert("出现错误")
      }))
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
