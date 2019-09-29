<template>
  <!--禁养犬种 总共-->
  <div class="w1200 c-b ovh mgt40">

    <!--禁养犬种-->
    <div class="fl w765">
      <div class="ovh">
      <div class="fl cor3 h37 lh37">
      <img class="mgr8" src="/static/images/20.png" alt="">
      <span class="fs18 cor6b4a22 fb mgr30">禁养犬种</span> 共有 <span class="fb">{{riskListTotalCount}}</span>
      种禁养犬种！<span style="margin-left: 100px">禁养大型犬的标准：成年犬体高超过61cm(含)以上的犬只。</span>
      </div>
      <router-link style="cursor:pointer;" class="db fr h37 lh37 cor6" :to="{name:'policiesRegulations',params:{vall:1}}">更多>></router-link>
      </div>
      <img src="/static/images/8.png" alt="" class="db mgt8 ww100">
      <ul class="mgt22 ovh ul06">

      <li class="fl w250 hov8" v-for="(item,index) in riskList" :key="index"><!--w236-->
      <a href="javascript:;" class="db">
      <img class="ww100 imgsize" style="height: 220px" :src="item.url?item.url:'/static/images/28-8.png'" alt="">
      <p class="cor6 t-c h45 lh45 bgf2"><span class="fb mgr20">{{item.dogBanName}}</span></p>
      </a>
      </li>
      <li class="fl w236 hov8" v-if="!riskList || riskList.length == 0">

      <a class="db" style="color: #000000">
      无
      <!--&lt;!&ndash;<img class="ww100" src="/static/images/9-2.png" alt="">&ndash;&gt;-->
      <!--<p class="cor6 t-c h45 lh45 bgf2">无<span class="fb mgr20"></span></p>-->
      </a>
      </li>
      </ul>
      <!--<div class="ovh">-->
        <!--<div class="fl cor3 h37 lh37" v-if="totalCount>0">-->
          <!--<img class="mgr8" src="/static/images/7.png" alt="">-->
          <!--<span class="fs18 cor6b4a22 fb mgr30">爱心领养</span>-->
          <!--共有 <span class="fb">{{totalCount}}</span> 只狗狗可以领养哦！-->
        <!--</div>-->
        <!--<div class="fl cor3 h37 lh37" v-else>-->
          <!--<img class="mgr8" src="/static/images/7.png" alt="">-->
          <!--<span class="fs18 cor6b4a22 fb mgr30">爱心领养</span>-->
          <!--没有可领养的的狗狗啦！-->
        <!--</div>-->

        <!--<router-link :to="{name:'loveAdopt'}" href="###" class="db fr h37 lh37 cor6">更多>></router-link>-->
      <!--</div>-->
      <!--<img class="db mgt10" src="/static/images/8.png" alt="">-->
      <!--<ul class="ul02 mgt10">-->

        <!--&lt;!&ndash;列表&ndash;&gt;-->
        <!--<li class="fl w220" v-for="item in dogList" :key="item.dogId">-->
          <!--<div>-->
            <!--<img class="ww100 imgSize" :src="item.thumbnailUrl?item.thumbnailUrl:'/static/images/28-8.png'" alt="">-->
            <!--&lt;!&ndash;<img class="ww100 imgSize" src="/static/images/28-8.png" alt="">&ndash;&gt;-->
            <!--<p class="fs18 cor3 fb t-c mgt18">{{item.dogBreed}}</p>-->
            <!--<div class="pdl20 pdr20 ovh clearfix">-->
              <!--<p class="ww50 fl cor6 mgt20">姓名：{{item.dogName}}</p>-->
              <!--<p class="ww50 fl cor6 mgt20">性别：{{item.dogGender==0?'雄':'雌'}}</p>-->
              <!--<p class="ww50 fl cor6 mgt20">毛色：{{item.dogColor}}</p>-->
            <!--</div>-->
            <!--<router-link style="cursor:pointer;" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1" :to="{name: 'adoptt', params: {collectionId: item.collectionId,dogPublicityId:item.dogPublicityId}}">立即领养</router-link>-->
          <!--</div>-->
        <!--</li>-->
      <!--</ul>-->
    </div>

    <!--服务网点-->
    <div class="fr w414">
      <div class="borffa472 ras14 pdt35 pdb45 pdl35 pdr35">
        <div class="ovh pdl17">
          <div class="fl w57"><img src="/static/images/11.png" alt=""></div>
          <div class="mgl70 pdt5">
            <p class="fs24 cor3c3c3c h26 lh26">服务指南</p>
            <p class="cor9 t-k h16 lh16">brand introduction</p>
          </div>
        </div>
        <img class="db mgt24" src="/static/images/12.png" alt="">
        <ul class="ul03 ovh">
          <li class="mgt37 fl ww33">
            <router-link :to="{name:'infoDetails'}" class="db hov8">
              <img class="db c-b" src="/static/images/13-1.png" alt="">
              <p class="fs18 cor3 t-c mgt10">办证</p>
            </router-link>
          </li>
          <li class="mgt37 fl ww33">
            <a href="javascript:void(0)" @click="$message('未开通')" class="db hov8">
              <img class="db c-b" src="/static/images/13-2.png" alt="">
              <p class="fs18 cor3 t-c mgt10">年检</p>
            </a>
          </li>
          <li class="mgt37 fl ww33">
            <a href="javascript:void(0)" @click="$message('未开通')" class="db hov8">
              <img class="db c-b" src="/static/images/13-3.png" alt="">
              <p class="fs18 cor3 t-c mgt10">防疫</p>
            </a>
          </li>
          <li class="mgt37 fl ww33">
            <router-link :to="{name:'loveAdopt'}" class="db hov8">
              <img class="db c-b" src="/static/images/13-4.png" alt="">
              <p class="fs18 cor3 t-c mgt10">领养</p>
            </router-link>
          </li>
          <li class="mgt37 fl ww33">
            <a href="javascript:void(0)" @click="$message('未开通')" class="db hov8">
              <img class="db c-b" src="/static/images/13-5.png" alt="">
              <p class="fs18 cor3 t-c mgt10">救助</p>
            </a>
          </li>
          <li class="mgt37 fl ww33">
            <a href="javascript:void(0)" @click="$message('未开通')" class="db hov8">
              <img class="db c-b" src="/static/images/13-6.png" alt="">
              <p class="fs18 cor3 t-c mgt10">医疗</p>
            </a>
          </li>
        </ul>
        <img class="db mgt28" src="/static/images/12.png" alt="">
        <div class="t-c pdt34">
          <router-link :to="{name:'siteRegistrationfrom'}" class="dinb corw t-c a2 w139 h41 lh41">服务网点注册</router-link>
          <a href="###" @click="$message('未开通')" class="dinb corw t-c a2 w139 h41 lh41">投诉建议</a>
        </div>
      </div>
      <div class="mgt30">
        <div class="ovh">
          <div class="fl cor3 h37 lh37">
            <img class="mgr8" src="/static/images/15.png" alt="">
            <span class="fs21 cor6a4a22 mgr10">服务网点</span> 共有 <span>{{hospitalListTotalCount}}</span> 家服务网点为您服务！
          </div>
          <router-link :to="{name:'serviceGuide',params:{vall:1}}" class="db fr h37 lh37 cor6">更多>></router-link>
        </div>
        <img src="/static/images/16.png" alt="" class="db ww100 mgt12">
        <div class="pdt10">
          <ul class="ul04">
            <!--网点列表-->

            <li class="mgt12" v-for="(item,index)  in hospitalList" :key="index">
              <!--<a class="db ovh" @click="$message('未开通')">--><!--:to="{name:'checkBranches'}"-->

                <div class="db ovh">
                  <router-link :to="{name:'HospitalDetails',params:{id:item.organizationId}}">
                    <div class="fl w122"><img src="/static/images/17-1.png" alt=""></div>
                  </router-link>
                  <div class="mgl143 rel pdt12">
                    <router-link :to="{name:'HospitalDetails',params:{id:item.organizationId}}">
                      <p class="fs16 cor3">{{item.organizationName}}</p>
                    </router-link>
                    <p class="cor9 mgt15">电话：{{item.phone}}</p>
                    <p class="cor9 mgt10 w210 lh20">地址：{{item.address}}</p>
                    <a @click="$message('未开通')"><span class="db abs z100 w65 h28 lh28 t-c corw sp1">预约</span></a>
                  </div>
                </div>

            </li>
          </ul>
        </div>

      </div>
    </div>
  </div>
  <!--爱心领养END-->

</template>

<script>
  let params = {
    // data: {page: 1, rows: 9},
    //TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
    data2:{provinceId : null,ownedCityId : null,districtId : null,orgtype : 2,status : 1,pageNum:1,pageSize:2}
    //服务网点参数

  }
  export default {
      name: "LoveAdoptt",
    data() {
      return {
        // dogList: [],//宠物列表
        // totalCount: 0,//总条数
        hospitalList:0,//宠物医院列表
        hospitalListTotalCount:0,//宠物医院总数
        riskList: [],//危险犬种列表
        riskListTotalCount: 0,//危险犬种总数
      }
    },
    beforeCreate() {
      // this.$axios.all([initAdopt(this),initHospitalList(this)])
      //   .then(this.$axios.spread( (acct, perms) =>{
      //     // 两个请求现在都执行完成
      //     // console.log(this.$data)
      //   }))
      this.$axios.all([initRisk(this),initHospitalList(this)])
        .then(this.$axios.spread( (acct, perms) =>{
          // 两个请求现在都执行完成
          // console.log(this.$data)
        }))
    }
  }

  //危险犬种
  function initRisk(_this) {
    _this.$axios({
      method: 'post',
      url: '/biz/dogAndOwner/selectDogBanList',
      params: {num: 1, size: 9}
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
  // //爱心领养查询
  // function initAdopt(_this) {
  //   _this.$axios({
  //     method: 'get',
  //     url: '/biz/collection/findAdoptionsList',
  //     params: params.data
  //   }).then((res) => {
  //     if (res.status == 200 && res.data != null) {
  //       _this.dogList = res.data.rows;//宠物列表
  //       _this.totalCount = res.data.records//总条数
  //     }
  //   }).catch((err) => {
  //     console.log(err);
  //   })
  // }
  //网点列表查询
  function initHospitalList(_this) {
    _this.$axios({
      method: 'post',
      url: '/biz/serviceSite/getServiceSitesforAdmin',
      params: params.data2
    }).then((res) => {
      if (res.data.status == 200 && res.data.data != null) {
        _this.hospitalList = res.data.data.list;//宠物列表
        _this.hospitalListTotalCount = res.data.data.total//总条数
      }
      // console.log(this.$data)
    }).catch((err) => {
      console.log(err);
    })
  }

</script>

<style scoped>
.imgSize{
  height: 150px;
  width: 225px;
}
</style>
