<template>
  <div class="indexPage">
    <!--轮播图-->
    <IndexSwrap></IndexSwrap>

    <!--动态资讯-->
    <information></information>

    <LoveAdopt></LoveAdopt>

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
            <a href="###" class="db fr h37 lh37 cor6">更多>></a>
          </div>
          <img src="/static/images/21.png" alt="" class="db mgt8 ww100">

          <ul class="ul05">
            <li v-for="(item,index) in regulationList" :key="index">
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                {{item.informationTypeName}}<span
                class="fr cor9">{{item.creationTime | formatDate('yyyy/MM/dd')}}</span>
              </a>
            </li>
            <li v-if="!regulationList || regulationList.length == 0">
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3">
                无<span class="fr cor9"></span>
              </a>
            </li>
            <!--
            <li>
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                西宁市养犬登记管理办法<span class="fr cor9">2018/05/16</span>
              </a>
            </li>
            <li>
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                西宁市养犬登记管理办法<span class="fr cor9">2018/05/16</span>
              </a>
            </li>
            <li>
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                西宁市养犬登记管理办法<span class="fr cor9">2018/05/16</span>
              </a>
            </li>
            <li>
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                西宁市养犬登记管理办法<span class="fr cor9">2018/05/16</span>
              </a>
            </li>
            <li>
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                西宁市养犬登记管理办法<span class="fr cor9">2018/05/16</span>
              </a>
            </li>
            -->
          </ul>
        </div>

        <!--养犬知识-->
        <div class="fr w590">
          <div class="ovh">
            <div class="fl cor3 h37 lh37">
              <img class="mgr8" src="/static/images/19.png" alt="">
              <span class="fs18 cor6b4a22 fb mgr30">养犬知识</span>
            </div>
            <a href="###" class="db fr h37 lh37 cor6">更多>></a>
          </div>
          <img src="/static/images/21.png" alt="" class="db mgt8 ww100">
          <ul class="ul05">

            <li v-for="(item,index) in knowledgeList" :key="index">
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                {{item.informationTypeName}}<span
                class="fr cor9">{{item.creationTime | formatDate('yyyy/MM/dd')}}</span>
              </a>
            </li>

            <li v-if="!knowledgeList || knowledgeList.length == 0">
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3">
                无<span class="fr cor9"></span>
              </a>
            </li>

            <!--
            <li>
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                西宁市养犬登记管理办法<span class="fr cor9">2018/05/16</span>
              </a>
            </li>
            <li>
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                西宁市养犬登记管理办法<span class="fr cor9">2018/05/16</span>
              </a>
            </li>
            <li>
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                西宁市养犬登记管理办法<span class="fr cor9">2018/05/16</span>
              </a>
            </li>
            <li>
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                西宁市养犬登记管理办法<span class="fr cor9">2018/05/16</span>
              </a>
            </li>
            <li>
              <a href="###" class="db h50 lh50 pdl24 pdr10 ovh fs14 cor3 pc3">
                西宁市养犬登记管理办法<span class="fr cor9">2018/05/16</span>
              </a>
            </li>
            -->
          </ul>
        </div>

      </div>
      <!--政策法规-->

      <!--危险犬种-->
      <div class="mgt35">
        <div class="ovh">
          <div class="fl cor3 h37 lh37">
            <img class="mgr8" src="/static/images/20.png" alt="">
            <span class="fs18 cor6b4a22 fb mgr30">危险犬只品种</span> 共有 <span class="fb">{{riskListTotalCount}}</span>
            种危险犬只品种
          </div>
          <a href="###" class="db fr h37 lh37 cor6">更多>></a>
        </div>
        <img src="/static/images/23.png" alt="" class="db mgt8 ww100">
        <ul class="mgt22 ovh ul06">

          <li class="fl w236 hov8" v-for="(item,index) in riskList" :key="index">
            <a href="###" class="db">
              <img class="ww100" src="item.thumbnailUrl" alt="">
              <p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">{{item.dogBreedBame}}</span></p>
            </a>
          </li>
          <li class="fl w236 hov8" v-if="!riskList || riskList.length == 0">
            <a href="###" class="db">
              <img class="ww100" src="/static/images/9-2.png" alt="">
              <p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">还没有危险犬种信息哦！</span></p>
            </a>
          </li>
        </ul>
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
  import LoveAdopt from './LoveAdopt'

  export default {
    name: "MainHome",
    components: {
      IndexSwrap, information, LoveAdopt
    },
    data() {
      return {
        regulationList: [],//政策法规列表
        regulationTotalCount: 0,//政策法规总条数
        knowledgeList: [],//养犬知识列表
        knowledgeTotalCount: 0,//养犬知识总条数
        riskList: [],//危险犬种列表
        riskListTotalCount: 0,//危险犬种总数
      }
    },
    created() {
      let params = {
        //查询政策法规//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
        data: {TypeNumber: null, status: 0, pageNum: 1, pageSize: 5},//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
        data2: {TypeNumber: null, status: 2, pageNum: 1, pageSize: 5},//TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
      }

      initRegulation(this, params.data, (list, count) => {
        this.regulationList = list;//政策法规列表
        this.regulationTotalCount = count;//政策法规总条数
      });
      initRegulation(this, params.data2, (list, count) => {
        this.knowledgeList = list;//养犬知识列表
        this.knowledgeTotalCount = count;//养犬知识总条数
      });

      //查询危险犬种
      initRisk(this)

    }, mounted() {
      console.log(this.$data)
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

  //危险犬种
  function initRisk(_this) {
    _this.$axios({
      method: 'post',
      url: '/biz/dogAndOwner/selectDogBanList',
      params: {num: 1, size: 10}
    }).then((res) => {
      if (res.data.code == '000000' && res.data.result != null) {
        // console.log(res)
        _this.riskList = res.data.result.dogInfos
        _this.riskListTotalCount = res.data.result.page.totalCount
      }

    }).catch((err) => {
      console.log(err);
    })
  }
</script>
<style scoped>

</style>
