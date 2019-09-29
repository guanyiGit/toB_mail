<template>
  <div>
    <!--头-->
    <MainTop></MainTop>
    <!--动态资讯-->
    <div class="w1200 c-b mgt40">
      <div class="ovh">
        <div class="fl cor6 h40 lh40">
          <img class="mgr8 db fl" src="../../../static/images/19.png" alt="">
          <span class="fs18 cor6a4a22 fb mgr30 db fl">动态资讯</span>
          <!--<a href="###" class="db fl tab1 active mgr35">服务指南</a>-->
          <!--<a href="###" class="db fl tab1">服务网点</a>-->
        </div>
        <form class="fr db w380 h40 borbfbfbf ras20 rel form" action="#">
          <input id="input1" type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort">
          <input value="" class="in1 db abs z100 top0 bort right0" v-on:click="informations">
        </form>
      </div>
      <img class="db mgt10" src="../../../static/images/23.png" alt="">
      <div class=" pdt10">
        <div class="db tab2">
          <ul class="ul05">
            <li v-for="item in infos">
              <!--<a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">-->
              <router-link class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3" :to="{name:'infoDetails',params:{id:item.informationId}}">
                  {{item.infoTitile}}<span class="fr cor9">{{item.creationTime | formatDate('yyyy-MM-dd')}}</span>
              </router-link>
              <!--</a>-->
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!--爱心领养END-->

    <div class="clearfix"></div>

    <!--分页-->
    <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>

    <!--友情链接-->
    <link-exchange></link-exchange>
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
    data: {TypeNumber: 1, status: 1, pageNum: 1,pageSize:20,query:null},//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
  }
  import $ from 'jquery'
  var _this = '';
    export default {
        name: "Information",
      data() {
        return {
          infos: [],//动态资讯
          totalPage:0
        }
      },
      created() {
        selectInformations();
      },beforeCreate(){
        _this = this;
      },
      computed: {
        pageData() {
          return {
            curPage: params.data.pageNum ? params.data.pageNum : 1,//当前页吗
            totalCount: this.totalPage ? this.totalPage * params.data.pageSize : 1,//总条数
            pageSize: params.data.pageSize ? params.data.pageSize : 1//页面大小
          }
        }
      },
      methods: {
        informations: function (event) {
          params.data.query=$("#input1").val().trim();
          selectInformations();
        },
        chekdpage(pageNum) {
          params.data.pageNum = pageNum
          selectInformations();
        },
      }
    }
  function selectInformations(){
    //查询动态资讯列表
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
</script>

<style scoped>

</style>
