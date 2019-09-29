<template>
  <div class="indexPage">
    <!--轮播图-->
    <IndexSwrap></IndexSwrap>

    <!--动态资讯-->
    <information></information>

    <LoveAdoptt></LoveAdoptt>

    <div class="w1200 c-b">
      <!--政策法规 & 养犬知识-->
      <div class="mgt35 ovh">
        <!--政策法规 -->
        <div class="fl w590">
          <div class="ovh">
            <div class="fl cor3 h37 lh37">
              <img class="mgr8" src="/static/images/15.png" alt="">
              <span class="fs18 cor6b4a22 fb mgr30">政策法规</span>
            </div>
            <router-link style="cursor:pointer;" class="db fr h37 lh37 cor6" :to="{name:'policiesRegulations'}">更多>></router-link>
          </div>
          <img src="/static/images/21.png" alt="" class="db mgt8 ww100">

          <ul class="ul05">
            <li v-for="(item,index) in regulationList" :key="index">
              <router-link :to="{name:'infoDetails',params:{id:item.informationId,type:5}}" style="cursor:pointer;" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                {{item.infoTitile.length<25?item.infoTitile:item.infoTitile.substring(0,24)}}<span
                class="fr cor9">{{item.creationTime | formatDate('yyyy/MM/dd')}}</span>
              </router-link>
            </li>
            <li v-if="!regulationList || regulationList.length == 0">
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3">
                无<span class="fr cor9"></span>
              </a>
            </li>
          </ul>
        </div>

        <!--养犬知识-->
        <div class="fr w590">
          <div class="ovh">
            <div class="fl cor3 h37 lh37">
              <img class="mgr8" src="/static/images/19.png" alt="">
              <span class="fs18 cor6b4a22 fb mgr30">养犬知识</span>
            </div>
            <router-link style="cursor:pointer;" class="db fr h37 lh37 cor6" :to="{name:'knowledge'}">更多>></router-link>
          </div>
          <img src="/static/images/21.png" alt="" class="db mgt8 ww100">
          <ul class="ul05">

            <li v-for="(item,index) in knowledgeList" :key="index">
              <router-link :to="{name:'infoDetails',params:{id:item.informationId,type:6}}" style="cursor:pointer;" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                {{item.infoTitile.length<25?item.infoTitile:item.infoTitile.substring(0,24)}}<span
                class="fr cor9">{{item.creationTime | formatDate('yyyy/MM/dd')}}</span>
              </router-link>
            </li>

            <li v-if="!knowledgeList || knowledgeList.length == 0">
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3">
                无<span class="fr cor9"></span>
              </a>
            </li>

          </ul>
        </div>

      </div>
      <!--政策法规-->

      <!--爱心领养-->
      <div class="mgt35">
        <div class="ovh">
          <div class="fl cor3 h37 lh37" v-if="totalCount>0">
            <img class="mgr8" src="/static/images/7.png" alt="">
            <span class="fs18 cor6b4a22 fb mgr30">爱心领养</span>
            共有 <span class="fb">{{totalCount}}</span> 只狗狗可以领养哦！
          </div>
          <div class="fl cor3 h37 lh37" v-else>
            <img class="mgr8" src="/static/images/7.png" alt="">
            <span class="fs18 cor6b4a22 fb mgr30">爱心领养</span>
            没有可领养的的狗狗啦！
          </div>
          <router-link :to="{name:'loveAdopt'}" style="cursor:pointer;" class="db fr h37 lh37 cor6">更多>></router-link>
        </div>
        <img class="db mgt10" src="/static/images/23.png" alt="">
        <ul class="ul02 mgt10">

          <!--列表-->
          <li class="fl w230" v-for="item in dogList" :key="item.dogId">
            <div>
              <img class="ww100 imgSize" style="height: 200px" :src="item.thumbnailUrl?item.thumbnailUrl:'/static/images/28-8.png'" alt="">
              <!--<img class="ww100 imgSize" src="/static/images/28-8.png" alt="">-->
              <p v-if="item.dogBreed" class="fs18 cor3 fb t-c mgt18">{{item.dogBreed}}</p>
              <p v-else class="fs18 cor3 fb t-c mgt18">&nbsp;</p>
              <div class="pdl20 pdr20 ovh clearfix">
                <p class="ww50 fl cor6 mgt20">姓名：{{item.dogName}}</p>
                <p class="ww50 fl cor6 mgt20">性别：{{item.dogGender==0?'雄':'雌'}}</p>
                <p class="ww50 fl cor6 mgt20">毛色：{{item.dogColor}}</p>
              </div>
              <router-link style="cursor:pointer;" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1" :to="{name: 'adoptt', params: {collectionId: item.collectionId,dogPublicityId:item.dogPublicityId}}">立即领养</router-link>
            </div>
          </li>
        </ul>
        <!--<div class="ovh">-->
          <!--<div class="fl cor3 h37 lh37">-->
            <!--<img class="mgr8" src="/static/images/20.png" alt="">-->
            <!--<span class="fs18 cor6b4a22 fb mgr30">禁养犬种</span> 共有 <span class="fb">{{riskListTotalCount}}</span>-->
            <!--种禁养犬种！-->
          <!--</div>-->
          <!--<router-link style="cursor:pointer;" class="db fr h37 lh37 cor6" :to="{name:'policiesRegulations',val:1}">更多>></router-link>-->
        <!--</div>-->
        <!--<img src="/static/images/23.png" alt="" class="db mgt8 ww100">-->
        <!--<ul class="mgt22 ovh ul06">-->

          <!--<li class="fl w199 hov8" v-for="(item,index) in riskList" :key="index">&lt;!&ndash;w236&ndash;&gt;-->
            <!--<a href="javascript:;" class="db">-->
              <!--<img class="ww100 imgsize" :src="item.url?item.url:'/static/images/28-8.png'" alt="">-->
              <!--<p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">{{item.dogBanName}}</span></p>-->
            <!--</a>-->
          <!--</li>-->
          <!--<li class="fl w236 hov8" v-if="!riskList || riskList.length == 0">-->

            <!--<a class="db" style="color: #000000">-->
              <!--无-->
              <!--&lt;!&ndash;&lt;!&ndash;<img class="ww100" src="/static/images/9-2.png" alt="">&ndash;&gt;&ndash;&gt;-->
              <!--&lt;!&ndash;<p class="cor6 t-c h45 lh45 bgf2">无<span class="fb mgr20"></span></p>&ndash;&gt;-->
            <!--</a>-->
          <!--</li>-->
        <!--</ul>-->
      </div>
      <!--危险犬种-->

      <!--友情链接-->
      <!--<link-exchange></link-exchange>-->

    </div>

  </div>
</template>


<script>
  import IndexSwrap from './IndexSwrap'
  import information from './information'
  import LoveAdoptt from './LoveAdopt'
  import configJS from '../../common/config'

  export default {
    name: "MainHome",
    components: {
      IndexSwrap, information, LoveAdoptt
    },
    data() {
      return {
        regulationList: [],//政策法规列表
        regulationTotalCount: 0,//政策法规总条数
        knowledgeList: [],//养犬知识列表
        knowledgeTotalCount: 0,//养犬知识总条数
        // riskList: [],//危险犬种列表
        // riskListTotalCount: 0,//危险犬种总数
        dogList: [],//宠物列表
        totalCount: 0,//总条数
      }
    },
    created() {
      try {
        this.$store.commit('changMemuIndex',-1);
        localStorage.setItem("memuIndex",-1);
      }catch (e) {console.log(e)}

      let params = {
        //查询政策法规//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
        data: {TypeNumber: 0, status: 1, pageNum: 1, pageSize: 5},//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
        data2: {TypeNumber: 2, status: 1, pageNum: 1, pageSize: 5},//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
      }

      initRegulation(this, params.data, (list, count) => {
        this.regulationList = list;//政策法规列表
        this.regulationTotalCount = count;//政策法规总条数
      });
      initRegulation(this, params.data2, (list, count) => {
        this.knowledgeList = list;//养犬知识列表
        this.knowledgeTotalCount = count;//养犬知识总条数
      });

      // //查询危险犬种
      // initRisk(this)

      //爱心领养查询
      initAdopt(this)
    }, mounted() {
    }
  }


  //资讯查询
  function initRegulation(_this, params, result) {
    _this.$axios({
      method: 'post',
      url: '/biz/information/getInformations',
      params: params
    }).then((res) => {
      if (res.data.status == 200 && res.data.data != null) {
        result(res.data.data.list, res.data.data.total);//列表,总条数
      }

    }).catch((err) => {
      console.log(err);
    })
  }

  // //危险犬种
  // function initRisk(_this) {
  //   _this.$axios({
  //     method: 'post',
  //     url: '/biz/dogAndOwner/selectDogBanList',
  //     params: {num: 1, size: 10}
  //   }).then((res) => {
  //     if (res.data.code == '000000' && res.data.result != null) {
  //       // console.log(res)
  //       _this.riskList = res.data.result.dogInfos
  //       _this.riskListTotalCount = res.data.result.page.totalCount
  //     }
  //
  //   }).catch((err) => {
  //     console.log(err);
  //   })
  // }

  //爱心领养查询
  function initAdopt(_this) {
    _this.$axios({
      method: 'get',
      url: '/biz/collection/findAdoptionsList',
      params: {page: 1, rows: 5}
    }).then((res) => {
      if (res.status == 200 && res.data != null) {
        _this.dogList = res.data.rows;//宠物列表
        _this.totalCount = res.data.records//总条数
      }
    }).catch((err) => {
      console.log(err);
    })
  }
</script>
<style scoped>
.imgsize{
  width: 236px;
  height: 157.33px;
}
</style>
