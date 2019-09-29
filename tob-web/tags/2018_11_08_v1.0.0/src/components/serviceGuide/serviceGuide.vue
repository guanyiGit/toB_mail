<template>
<div>
  <!--头-->
  <MainTop></MainTop>
  <!--服务指南-->
  <div class="w1200 c-b mgt40">
    <div class="ovh">
      <div class="fl cor6 h40 lh40">
        <img class="mgr8 db fl" src="../../../static/images/19.png" alt="">
        <span class="fs18 cor6a4a22 fb mgr30 db fl">养犬服务</span>
        <a style="cursor:pointer;" class="db fl tab1 active mgr35">服务指南</a>
        <a style="cursor:pointer;" class="db fl tab1">服务网点</a>
      </div>
      <form class="fr db w380 h40 borbfbfbf ras20 rel form" action="#">
        <input id="input1" type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort">
        <input value="" class="in1 db abs z100 top0 bort right0" v-on:click="serviceGuidee">
      </form>
      <form class="fr db w380 h40 borbfbfbf ras20 rel form" action="#" style="display: none">
        <input id="input2" type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort">
        <input value="" class="in1 db abs z100 top0 bort right0" v-on:click="serviceOutlets">
      </form>
    </div>
    <img class="db mgt10" src="../../../static/images/23.png" alt="">
    <div class=" pdt10">
      <div class="db tab2">
        <ul class="ul05">
          <li v-for="item in infos">
            <router-link href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3" :to="{name:'infoDetails',params:{id:item.informationId}}">
              {{item.infoTitile}}<span class="fr cor9">{{item.creationTime | formatDate('yyyy-MM-dd')}}</span>
            </router-link>
          </li>
        </ul>
        <!--分页-->
        <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
        <!--分页-->
      </div>
      <div class="dn tab2">
        <ul class="ul09 ovh">
          <li class="w385 fl mgt18" v-for="item in hospitalList">
            <div class="ovh">
              <div class="fl w122">
                <img v-if="item&&item.pictureUrl&&item.pictureUrl.length>0" v-bind:src="item.pictureUrl[0]" src="../../../static/images/17-1.png" alt="">
                <img v-else src="../../../static/images/28-8.png" alt="">
              </div>
              <div class="mgl140">
                <div class="fs16 cor3 h30 lh30">
                  {{item.organizationName}}<a style="cursor:pointer;" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>
                </div>
                <p class="cor9 mgt15">电话：{{item.phone}}</p>
                <p class="cor9 mgt10 w210 lh20">地址：{{item.address}}</p>
              </div>
            </div>
          </li>
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!--<li class="w385 fl mgt18">-->
            <!--<div class="ovh">-->
              <!--<div class="fl w122"><img src="../../../static/images/17-1.png" alt=""></div>-->
              <!--<div class="mgl140">-->
                <!--<div class="fs16 cor3 h30 lh30">-->
                  <!--西宁市瑞鹏宠物医院<a href="###" class="dinb fr w65 h28 lh28 t-c corw a3">预约</a>-->
                <!--</div>-->
                <!--<p class="cor9 mgt15">电话：13800138000</p>-->
                <!--<p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
        </ul>
        <!--分页-->
        <page-vue :pageData="pageData1" @chekdpageCallcak="chekdpage1"></page-vue>
        <!--分页-->
      </div>
    </div>
  </div>
  <!--服务指南END-->

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
  //请求参数
  let params = {
    data: {TypeNumber: 3, status: 1, pageNum: 1,pageSize:20,query:null},//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
    data1:{provinceId : null,ownedCityId : null,districtId : null,orgtype : 2,status : 1,pageNum:1,pageSize:15}//服务网点参数
  }
  import $ from 'jquery'

  var _this = '';
    export default {
        name: "serviceGuide",
      data() {
        return {
          infos: [],//服务指南
          hospitalList:[],//服务网点
          totalPage:0,
          totalPage1:0
        }
      },
      created() {
        selectServiceGuide();
        selectServiceOutlets();
      },beforeCreate(){
        _this = this;
      },
      mounted(){
        var $tab1= $('.tab1');
        $tab1.mouseover(function(){
          $(this).addClass('active').siblings().removeClass('active');
          var i= $tab1.index(this);
          $('.tab2').eq(i).show().siblings('.tab2').hide();
          $('.form').eq(i).show().siblings('.form').hide();
        });
      },
      computed: {
        pageData() {
          return {
            curPage: params.data.pageNum ? params.data.pageNum : 1,//当前页吗
            totalCount: this.totalPage ? this.totalPage * params.data.pageSize : 1,//总条数
            pageSize: params.data.pageSize ? params.data.pageSize : 1//页面大小
          }
        },
        pageData1() {
          return {
            curPage: params.data1.pageNum ? params.data1.pageNum : 1,//当前页吗
            totalCount: this.totalPage1 ? this.totalPage1 * params.data1.pageSize : 1,//总条数
            pageSize: params.data1.pageSize ? params.data1.pageSize : 1//页面大小
          }
        }
      },
      methods: {
        serviceGuidee: function (event) {
          params.data.query=$("#input1").val().trim();
          selectServiceGuide();
        },
        serviceOutlets: function (event) {
          // params.data1.query=$("#input2").val().trim();
          selectServiceOutlets();
        },
        chekdpage(pageNum) {
          params.data.pageNum = pageNum
          selectServiceOutlets();
        },
        chekdpage1(pageNum) {
          params.data1.pageNum = pageNum
          selectServiceGuide();
        }
      }
    }
  function selectServiceGuide(){
    //查询服务指南列表
    _this.$axios({
      method: 'post',
      url: '/biz/information/getInformations',
      params: params.data
    }).then((res) => {
      if (res.data.status == 200) {
        _this.infos=res.data.data.list
      }
    }).catch((err) => {
      console.log(err);
    })
  }
  function selectServiceOutlets(){
    //查詢服务网点
    _this.$axios({
      method: 'post',
      url: '/biz/serviceSite/getServiceSitesforAdmin',
      params: params.data1
    }).then((res) => {
      if (res.data.status == 200 && res.data.data != null) {
        _this.hospitalList = res.data.data.list;//服務網點
      }
      // console.log(this.$data)
    }).catch((err) => {
      console.log(err);
    })
  }
</script>

<style scoped>

</style>
