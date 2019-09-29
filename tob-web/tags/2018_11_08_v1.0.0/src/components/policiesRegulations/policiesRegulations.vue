<template>
  <!--wrap-->
  <div >

    <!--头-->
    <MainTop></MainTop>

    <!--禁養犬種-->
    <div class="w1200 c-b mgt40">
      <div class="ovh">
        <div class="fl cor6 h40 lh40">
          <img class="mgr8 db fl" src="../../../static/images/15.png" alt="">
          <span class="fs18 cor6a4a22 fb mgr30 db fl">政策指南</span>
          <a style="cursor:pointer;" class="db fl tab1 mgr35">政策法规</a>
          <a style="cursor:pointer;" class="db fl tab1 active">禁养品种</a>
        </div>
        <form class="fr db w380 h40 borbfbfbf ras20 rel form first" action="#" style="display: none">
          <input id="input1" type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort">
          <input value="" class="in1 db abs z100 top0 bort right0" v-on:click="policiess">
        </form>
        <form class="fr db w380 h40 borbfbfbf ras20 rel form last" action="#">
          <input id="input2" type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort">
          <input value="" class="in1 db abs z100 top0 bort right0" v-on:click="banDogs">
        </form>
      </div>
      <img class="db mgt10" src="../../../static/images/23.png" alt="">
      <div class=" pdt10 pages">
        <div class="dn tab2 first">
          <ul class="ul05">
            <li v-for="item in infos">
              <router-link href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3" :to="{name:'infoDetails',params:{id:item.informationId}}">
                {{item.infoTitile}}<span class="fr cor9">{{item.creationTime | formatDate('yyyy-MM-dd')}}</span>
              </router-link>
            </li>
          </ul>
          <!--分页-->
          <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
        </div>
        <div class="db tab2 last">
          <ul class="mgt22 ovh ul06">
            <li v-for="item in dogs" class="fl w236 hov8">
              <a href="###" class="db">
                <img class="ww100" v-bind:src="item.url" alt="">
                <p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">{{item.dogBreedBame}}</span></p>
              </a>
            </li>
            <!--<li class="fl w236 hov8">-->
              <!--<a href="###" class="db">-->
                <!--<img class="ww100" src="../../../static/images/9-2.png" alt="">-->
                <!--<p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">牛头犬</span>Akita</p>-->
              <!--</a>-->
            <!--</li>-->
            <!--<li class="fl w236 hov8">-->
              <!--<a href="###" class="db">-->
                <!--<img class="ww100" src="../../../static/images/9-3.png" alt="">-->
                <!--<p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">牛头犬</span>Akita</p>-->
              <!--</a>-->
            <!--</li>-->
            <!--<li class="fl w236 hov8">-->
              <!--<a href="###" class="db">-->
                <!--<img class="ww100" src="../../../static/images/9-2.png" alt="">-->
                <!--<p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">牛头犬</span>Akita</p>-->
              <!--</a>-->
            <!--</li>-->
            <!--<li class="fl w236 hov8">-->
              <!--<a href="###" class="db">-->
                <!--<img class="ww100" src="../../../static/images/9-1.png" alt="">-->
                <!--<p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">牛头犬</span>Akita</p>-->
              <!--</a>-->
            <!--</li>-->
            <!--<li class="fl w236 hov8">-->
              <!--<a href="###" class="db">-->
                <!--<img class="ww100" src="../../../static/images/9-2.png" alt="">-->
                <!--<p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">牛头犬</span>Akita</p>-->
              <!--</a>-->
            <!--</li>-->
            <!--<li class="fl w236 hov8">-->
              <!--<a href="###" class="db">-->
                <!--<img class="ww100" src="../../../static/images/9-1.png" alt="">-->
                <!--<p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">牛头犬</span>Akita</p>-->
              <!--</a>-->
            <!--</li>-->
            <!--<li class="fl w236 hov8">-->
              <!--<a href="###" class="db">-->
                <!--<img class="ww100" src="../../../static/images/9-2.png" alt="">-->
                <!--<p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">牛头犬</span>Akita</p>-->
              <!--</a>-->
            <!--</li>-->
            <!--<li class="fl w236 hov8">-->
              <!--<a href="###" class="db">-->
                <!--<img class="ww100" src="../../../static/images/9-3.png" alt="">-->
                <!--<p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">牛头犬</span>Akita</p>-->
              <!--</a>-->
            <!--</li>-->
            <!--<li class="fl w236 hov8">-->
              <!--<a href="###" class="db">-->
                <!--<img class="ww100" src="../../../static/images/9-1.png" alt="">-->
                <!--<p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">牛头犬</span>Akita</p>-->
              <!--</a>-->
            <!--</li>-->
          </ul>
          <!--分页-->
          <page-vue :pageData="pageData1" @chekdpageCallcak="chekdpage1"></page-vue>
        </div>
      </div>
    </div>
    <div class="clearfix"></div>
    <!--爱心领养END-->


    <div class="w1200 c-b">
      <!--友情链接-->
      <div class="mgt110 ovh">
        <div class="fl w160 h72 pdl20 pdt17 bor-r">
          <p class="fs20 cor6b4a22 h22 lh22">友情链接</p>
          <p class="cor9 h16 lh16">Brand introduction</p>
        </div>
        <div class="mgl160">
          <ul class="ul07">
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">狗狗网</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">瑞鹏宠物中心</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">邵阳宠物医院</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">丰原宠物网</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">全进宠物食品网</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">邵阳宠物医院</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">邵阳宠物医院</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">狗狗网</a>
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
    </div>

    <!--分割线-->
    <div class="mgt110"><img class="db c-b" src="../../../static/images/6.png" alt="分割线"></div>
    <!--分割线END-->

    <!--底部-->
    <MainBottom></MainBottom>
  </div>
  <!--wrap end-->
</template>

<script>
  //请求参数
  let params = {
    data: {TypeNumber: 0, status: 1, pageNum: 1,pageSize:20,query:null},//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
    data1: {num: 0,size:10,input:null},//禁養犬種參數
  }
  import $ from 'jquery'
  var _this = '';
    export default {
        name: "policiesRegulations",
      data() {
        return {
          infos: [],//政策法规
          dogs:[],//禁養犬種
          totalPage:0,
          totalPage1:0
        }
      },
      created() {
        policiesRegulationss();
        banDogs();
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
            curPage: params.data1.num ? params.data1.num : 1,//当前页吗
            totalCount: this.totalPage1 ? this.totalPage1 * params.data1.size : 1,//总条数
            pageSize: params.data1.size ? params.data1.size : 1//页面大小
          }
        }
      },
      methods: {
        banDogs: function (event) {
          params.data1.input=$("#input2").val().trim();
          banDogs();
        },
        policiess: function (event) {
          params.data.query=$("#input1").val().trim();
          policiesRegulationss();
        },
        chekdpage(pageNum) {
          params.data.pageNum = pageNum
          policiesRegulationss();
        },
        chekdpage1(pageNum) {
          params.data1.num = pageNum
          banDogs(this);
        }
      }
    }
    function policiesRegulationss(){
      //查询政策法规列表
      _this.$axios({
        method: 'post',
        url: '/biz/information/getInformations',
        params: params.data
      }).then((res) => {
        if (res.data.status == 200) {
          _this.infos=res.data.data.list
        }
      })
    }
    function banDogs(){
      //查詢禁養犬種
      _this.$axios({
        method: 'post',
        url: '/biz/dogAndOwner/selectDogBanList',
        params: params.data1
      }).then((res) => {
        if (res.data.code == "000000") {
          _this.dogs=res.data.result.dogInfos
        }
      })
    }
</script>

<style scoped>
</style>
