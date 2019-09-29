<template>
<div>
  <!--头-->
  <MainTop></MainTop>
  <!--信息详情-->
  <div class="w1200 c-b mgt40">
    <div class="ovh">
      <div class="fl cor6 h40 lh40">
        <img class="mgr8 db fl" src="../../../static/images/19.png" alt="">
        <span class="fs18 cor6a4a22 fb mgr30 db fl"><a style="cursor:pointer;" class="fs18 cor6a4a22" @click="tolist">列表</a>>>详情</span>
        <!--<a href="###" class="db fl tab1 active mgr35">服务指南</a>-->
        <!--<a href="###" class="db fl tab1">服务网点</a>-->
      </div>
    </div>
    <img class="db mgt10" src="../../../static/images/23.png" alt="">
    <div class="pdt20">
      <div class="db tab2">
        <div class="bord">
          <div class="fs16 cor0 pdt25 pdb28 bgf1 pdl30 t-c">
            <p class="fs20 cor0 lig">{{infodeteail?infodeteail.infoTitile?infodeteail.infoTitile:'':''}}</p>
            <p class="cor9 mgt15 lig">浏览次数：<span>{{infodeteail?infodeteail.viewCount?infodeteail.viewCount:'':''}}</span> 来源：<span>{{infodeteail?infodeteail.pubishOrg?infodeteail.pubishOrg:'':''}} </span> 发布时间：<span v-if="infodeteail&&infodeteail.approvalTime">{{infodeteail.approvalTime | formatDate("yyyy-MM-dd")}}</span></p>
          </div>
          <div class=" pdb60 pdl30 pdr30" v-html="infodeteail?infodeteail.infoContent?infodeteail.infoContent:'':''">
            <p class="fs16 cor0 lig mgt20">一、办理条件</p>
            <p class="mgt20 cor6 lig lh30">
              1、原则上申请人须年满十八周岁，有稳定职业 ，最好有较固定住所，经济不拮据者。（学生和未满18周岁的申请人，需同住的父母代为申请。<br>2、把宠物视为家庭的一员，有决心照顾它们一辈子，不会因工作调动、生育问题等抛弃它们。<br>3、A方式：为杜绝某些领养人一时心血来潮或没有稳定生活来源或是恶意欺骗而随便领养，也为了能给流浪动物一个较稳定的归宿，领养时犬只需收取保证金300-1000元
            </p>
            <p class="fs16 cor0 lig mgt20">二、办理材料</p>
            <p class="mgt20 cor6 lig lh30">
              1、原则上申请人须年满十八周岁，有稳定职业 ，最好有较固定住所，经济不拮据者。（学生和未满18周岁的申请人，需同住的父母代为申请。<br>2、把宠物视为家庭的一员，有决心照顾它们一辈子，不会因工作调动、生育问题等抛弃它们。<br>3、A方式：为杜绝某些领养人一时心血来潮或没有稳定生活来源或是恶意欺骗而随便领养，也为了能给流浪动物一个较稳定的归宿，领养时犬只需收取保证金300-1000元
            </p>
            <p class="fs16 cor0 lig mgt20">三、办理流程</p>
            <p class="mgt20 cor6 lig lh30">
              1、原则上申请人须年满十八周岁，有稳定职业 ，最好有较固定住所，经济不拮据者。（学生和未满18周岁的申请人，需同住的父母代为申请。<br>2、把宠物视为家庭的一员，有决心照顾它们一辈子，不会因工作调动、生育问题等抛弃它们。<br>3、A方式：为杜绝某些领养人一时心血来潮或没有稳定生活来源或是恶意欺骗而随便领养，也为了能给流浪动物一个较稳定的归宿，领养时犬只需收取保证金300-1000元
            </p>
            <p class="fs16 cor0 lig mgt20">四、办理地点</p>
            <p class="mgt20 cor6 lig lh30">
              1、原则上申请人须年满十八周岁，有稳定职业 ，最好有较固定住所，经济不拮据者。（学生和未满18周岁的申请人，需同住的父母代为申请。<br>2、把宠物视为家庭的一员，有决心照顾它们一辈子，不会因工作调动、生育问题等抛弃它们。<br>3、A方式：为杜绝某些领养人一时心血来潮或没有稳定生活来源或是恶意欺骗而随便领养，也为了能给流浪动物一个较稳定的归宿，领养时犬只需收取保证金300-1000元
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--爱心领养END-->
<p style="margin-top: 20px" v-if="infodeteail&&infodeteail.failReason">不通过原因：{{infodeteail.failReason}}</p>
  <div class="clearfix"></div>

  <!--友情链接-->
  <div class="mgt110 ovh w1200 c-b">
    <div class="fl w160 h72 pdl20 pdt17 bor-r">
      <p class="fs20 cor6b4a22 h22 lh22">友情链接</p>
      <p class="cor9 h16 lh16">Brand introduction</p>
    </div>
    <div class="mgl160">
      <ul class="ul07">
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9 hov8">狗狗网</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9 hov8">瑞鹏宠物中心</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9 hov8">邵阳宠物医院</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9 hov8">丰原宠物网</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9 hov8">全进宠物食品网</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9 hov8">邵阳宠物医院</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9 hov8">邵阳宠物医院</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9 hov8">狗狗网</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9">瑞鹏宠物中心</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9">邵阳宠物医院</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9">狗狗网</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9">全进宠物食品网</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9">邵阳宠物医院</a>
        </li>
        <li class="fl">
          <a href="###" class="db h36 lh36 cor9">狗狗网</a>
        </li>
      </ul>
    </div>
  </div>
  <!--友情链接-->

  <!--分割线-->
  <div class="mgt110"><img class="db c-b" src="../../../static/images/6.png" alt="分割线"></div>
  <!--分割线END-->

  <!--底部-->
  <MainBottom></MainBottom>
</div>
</template>

<script>
  var _this = '';
    export default {
        name: "infoDetails",
      data() {
        return {
          infodeteail:null
        }
      },
      created() {
        informations();
      },
      methods:{
          tolist(){
            this.$router.back(-1);
          }
      },
      beforeCreate(){
        _this = this;
      }
    }
    function informations() {
      _this.$axios({
        method: 'post',
        url: '/biz/information/getInformation',
        params: {informationId:_this.$route.params.id}
      }).then((res) => {
        if (res.status == 200) {
          console.log('111')
          console.log(res.data)
          _this.infodeteail=res.data.data
        }
      }).catch((err) => {
        console.log(err);
      })
    }
</script>

<style scoped>

</style>
