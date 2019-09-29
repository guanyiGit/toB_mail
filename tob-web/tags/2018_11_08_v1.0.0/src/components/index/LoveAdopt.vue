<template>
  <!--爱心领养 总共-->
  <div class="w1200 c-b ovh mgt40">

    <!--爱心领养-->
    <div class="fl w765">
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

        <router-link style="cursor:pointer;" class="db fr h37 lh37 cor6" :to="{name:'loveAdopt'}">更多>></router-link>
      </div>
      <img class="db mgt10" src="/static/images/8.png" alt="">
      <ul class="ul02 mgt10">

        <!--列表-->
        <li class="fl w247" v-for="item in dogList" :key="item.dogId">
          <div>
            <img class="ww100" src="item.thumbnailUrl" alt="">
            <p class="fs18 cor3 fb t-c mgt18">{{item.dogBreed}}</p>
            <div class="pdl20 pdr20 ovh clearfix">
              <p class="ww50 fl cor6 mgt20">姓名：{{item.dogName}}</p>
              <p class="ww50 fl cor6 mgt20">性别：{{item.dogGender==0?'雄':'雌'}}</p>
              <p class="ww50 fl cor6 mgt20">毛色：{{item.dogColor}}</p>
            </div>
            <a href="###" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1">立即领养</a>
          </div>
        </li>


        <!--
      <li class="fl w247">
        <div>
          <img class="ww100" src="/static/images/9-1.png" alt="">
          <p class="fs18 cor3 fb t-c mgt18">哈士奇</p>
          <div class="pdl20 pdr20 ovh clearfix">
            <p class="ww50 fl cor6 mgt20">姓名：小花</p>
            <p class="ww50 fl cor6 mgt20">性别：公</p>
            <p class="ww50 fl cor6 mgt20">毛色：黑白色</p>
          </div>
          <a href="###" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1">立即领养</a>
        </div>
      </li>
      <li class="fl w247">
        <div>
          <img class="ww100" src="/static/images/9-2.png" alt="">
          <p class="fs18 cor3 fb t-c mgt18">哈士奇</p>
          <div class="pdl20 pdr20 ovh clearfix">
            <p class="ww50 fl cor6 mgt20">姓名：小花</p>
            <p class="ww50 fl cor6 mgt20">性别：公</p>
            <p class="ww50 fl cor6 mgt20">毛色：黑白色</p>
          </div>
          <a href="###" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1">立即领养</a>
        </div>
      </li>
      <li class="fl w247">
        <div>
          <img class="ww100" src="/static/images/9-3.png" alt="">
          <p class="fs18 cor3 fb t-c mgt18">哈士奇</p>
          <div class="pdl20 pdr20 ovh clearfix">
            <p class="ww50 fl cor6 mgt20">姓名：小花</p>
            <p class="ww50 fl cor6 mgt20">性别：公</p>
            <p class="ww50 fl cor6 mgt20">毛色：黑白色</p>
          </div>
          <a href="###" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1">立即领养</a>
        </div>
      </li>
      <li class="fl w247">
        <div>
          <img class="ww100" src="/static/images/9-1.png" alt="">
          <p class="fs18 cor3 fb t-c mgt18">哈士奇</p>
          <div class="pdl20 pdr20 ovh clearfix">
            <p class="ww50 fl cor6 mgt20">姓名：小花</p>
            <p class="ww50 fl cor6 mgt20">性别：公</p>
            <p class="ww50 fl cor6 mgt20">毛色：黑白色</p>
          </div>
          <a href="###" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1">立即领养</a>
        </div>
      </li>
      <li class="fl w247">
        <div>
          <img class="ww100" src="/static/images/9-2.png" alt="">
          <p class="fs18 cor3 fb t-c mgt18">哈士奇</p>
          <div class="pdl20 pdr20 ovh clearfix">
            <p class="ww50 fl cor6 mgt20">姓名：小花</p>
            <p class="ww50 fl cor6 mgt20">性别：公</p>
            <p class="ww50 fl cor6 mgt20">毛色：黑白色</p>
          </div>
          <a href="###" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1">立即领养</a>
        </div>
      </li>
      <li class="fl w247">
        <div>
          <img class="ww100" src="/static/images/9-3.png" alt="">
          <p class="fs18 cor3 fb t-c mgt18">哈士奇</p>
          <div class="pdl20 pdr20 ovh clearfix">
            <p class="ww50 fl cor6 mgt20">姓名：小花</p>
            <p class="ww50 fl cor6 mgt20">性别：公</p>
            <p class="ww50 fl cor6 mgt20">毛色：黑白色</p>
          </div>
          <a href="###" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1">立即领养</a>
        </div>
      </li>
      <li class="fl w247">
        <div>
          <img class="ww100" src="/static/images/9-1.png" alt="">
          <p class="fs18 cor3 fb t-c mgt18">哈士奇</p>
          <div class="pdl20 pdr20 ovh clearfix">
            <p class="ww50 fl cor6 mgt20">姓名：小花</p>
            <p class="ww50 fl cor6 mgt20">性别：公</p>
            <p class="ww50 fl cor6 mgt20">毛色：黑白色</p>
          </div>
          <a href="###" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1">立即领养</a>
        </div>
      </li>
      <li class="fl w247">
        <div>
          <img class="ww100" src="/static/images/9-2.png" alt="">
          <p class="fs18 cor3 fb t-c mgt18">哈士奇</p>
          <div class="pdl20 pdr20 ovh clearfix">
            <p class="ww50 fl cor6 mgt20">姓名：小花</p>
            <p class="ww50 fl cor6 mgt20">性别：公</p>
            <p class="ww50 fl cor6 mgt20">毛色：黑白色</p>
          </div>
          <a href="###" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1">立即领养</a>
        </div>
      </li>
      <li class="fl w247">
        <div>
          <img class="ww100" src="/static/images/9-3.png" alt="">
          <p class="fs18 cor3 fb t-c mgt18">哈士奇</p>
          <div class="pdl20 pdr20 ovh clearfix">
            <p class="ww50 fl cor6 mgt20">姓名：小花</p>
            <p class="ww50 fl cor6 mgt20">性别：公</p>
            <p class="ww50 fl cor6 mgt20">毛色：黑白色</p>
          </div>
          <a href="###" class="db c-b corw t-c pc2 w119 h30 lh30 mgt28 a1">立即领养</a>
        </div>
      </li>
      -->

      </ul>
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
            <router-link :to="{name:'dogManager_dogadd'}" class="db hov8">
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
          <a href="###" class="dinb corw t-c a2 w139 h41 lh41">服务网点注册</a>
          <a href="###" class="dinb corw t-c a2 w139 h41 lh41">投诉建议</a>
        </div>
      </div>
      <div class="mgt30">
        <div class="ovh">
          <div class="fl cor3 h37 lh37">
            <img class="mgr8" src="/static/images/15.png" alt="">
            <span class="fs21 cor6a4a22 mgr10">服务网点</span> 共有 <span>{{hospitalListTotalCount}}</span> 家服务网点为您服务！
          </div>
          <router-link style="cursor:pointer;" class="db fr h37 lh37 cor6" :to="{name:'serviceGuide'}">更多>></router-link>
        </div>
        <img src="/static/images/16.png" alt="" class="db ww100 mgt12">
        <div class="pdt10">
          <ul class="ul04">
            <!--网点列表-->

            <li class="mgt12" v-for="(item,index)  in hospitalList" :key="index">
              <router-link :to="{name:'checkBranches'}" class="db ovh">
                <div class="fl w122"><img src="/static/images/17-1.png" alt=""></div>
                <div class="mgl143 rel pdt12">
                  <p class="fs16 cor3">{{item.organizationName}}</p>
                  <p class="cor9 mgt15">电话：{{item.phone}}</p>
                  <p class="cor9 mgt10 w210 lh20">地址：{{item.address}}</p>
                  <span class="db abs z100 w65 h28 lh28 t-c corw sp1">预约</span>
                </div>
            </router-link>
            </li>
            <!--
            <li class="mgt12">
              <a href="###" class="db ovh">
                <div class="fl w122"><img src="/static/images/17-1.png" alt=""></div>
                <div class="mgl143 rel pdt12">
                  <p class="fs16 cor3">西宁市瑞鹏宠物医院</p>
                  <p class="cor9 mgt15">电话：13800138000</p>
                  <p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>
                  <span class="db abs z100 w65 h28 lh28 t-c corw sp1">预约</span>
                </div>
              </a>
            </li>
            <li class="mgt12">
              <a href="###" class="db ovh">
                <div class="fl w122"><img src="/static/images/17-2.png" alt=""></div>
                <div class="mgl143 rel pdt12">
                  <p class="fs16 cor3">西宁市瑞鹏宠物医院</p>
                  <p class="cor9 mgt15">电话：13800138000</p>
                  <p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>
                  <span class="db abs z100 w65 h28 lh28 t-c corw sp1">预约</span>
                </div>
              </a>
            </li>
            <li class="mgt12">
              <a href="###" class="db ovh">
                <div class="fl w122"><img src="/static/images/17-3.png" alt=""></div>
                <div class="mgl143 rel pdt12">
                  <p class="fs16 cor3">西宁市瑞鹏宠物医院</p>
                  <p class="cor9 mgt15">电话：13800138000</p>
                  <p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>
                  <span class="db abs z100 w65 h28 lh28 t-c corw sp1">预约</span>
                </div>
              </a>
            </li>
            <li class="mgt12">
              <a href="###" class="db ovh">
                <div class="fl w122"><img src="/static/images/17-1.png" alt=""></div>
                <div class="mgl143 rel pdt12">
                  <p class="fs16 cor3">西宁市瑞鹏宠物医院</p>
                  <p class="cor9 mgt15">电话：13800138000</p>
                  <p class="cor9 mgt10 w210 lh20">地址：青海省西宁市城东区青藏铁路花园北门向西200米</p>
                  <span class="db abs z100 w65 h28 lh28 t-c corw sp1">预约</span>
                </div>
              </a>
            </li>
            -->
          </ul>
        </div>

      </div>
    </div>
  </div>
  <!--爱心领养END-->

</template>

<script>
  let params = {
    data: {pn: 1, pageSize: 9, string: null, status: 1},
    //TypeNumber 0：政策法规 1：动态资讯 2:养犬宣传 3：服务指南 status:0审核中，1上架，2下架
    data2:{provinceId : null,ownedCityId : null,districtId : null,orgtype : 2,status : 1,pageNum:1,pageSize:4}
    //服务网点参数

  }
  export default {
    name: "LoveAdopt",
    data() {
      return {
        dogList: [],//宠物列表
        totalCount: 0,//总条数
        hospitalList:0,//宠物医院列表
        hospitalListTotalCount:0,//宠物医院总数
      }
    },
    beforeCreate() {
      this.$axios.all([initAdopt(this),initHospitalList(this)])
        .then(this.$axios.spread( (acct, perms) =>{
          // 两个请求现在都执行完成
          // console.log(this.$data)
        }))

    }
  }

  //爱心领养查询
  function initAdopt(_this) {
    _this.$axios({
      method: 'get',
      url: '/biz/collection/findCollectionList',
      params: params.data
    }).then((res) => {
      if (res.data.status == 200 && res.data.data != null) {
        this.dogList = res.data.data.list;//宠物列表
        this.totalCount = res.data.data.totalRecord//总条数
      }
    }).catch((err) => {
      console.log(err);
    })
  }
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

</style>
