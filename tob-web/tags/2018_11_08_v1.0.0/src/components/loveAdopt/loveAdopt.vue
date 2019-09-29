<template>
  <div>
    <!--头-->
    <MainTop></MainTop>
    <!--爱心领养-->
    <div class="w1200 c-b mgt40">

      <div class="ovh">
        <div class="fl cor3 h37 lh37">
          <img class="mgr8" src="../../../static/images/7.png" alt="">
          <span class="fs18 cor6b4a22 fb mgr30">爱心领养</span> 共有 <span class="fb">{{totol}}</span> 只狗狗可以领养哦！
        </div>
        <!--<a href="###" class="db fr h37 lh37 cor6">更多>></a>-->
      </div>
      <img class="db mgt10" src="../../../static/images/23.png" alt="">

      <div class="clearfix">
        <div class="fl cor3 h30 lh30 mgt20 mgr35">
          <span>品种：</span>
          <select id="breedSelect" class="w120 h30 lh30 bore pdl10 cor9 pc4" v-on:change="breedSelectt">
            <option value="-1">全部</option>
            <option v-for="item in varieties" v-bind:value=item.value>{{item.name}}</option>
          </select>
        </div>
        <div class="fl cor3 h30 lh30 mgt20 mgr35">
          <span>性别：</span>
          <select id="sexSelect" class="w120 h30 lh30 bore pdl10 cor9 pc4" v-on:change="sexSelectt">
            <option value="-1">全部</option>
            <option value="0">雄</option>
            <option value="1">雌</option>
          </select>
        </div>
        <div class="fl cor3 h30 lh30 mgt20 mgr35">
          <span>犬龄：</span>
          <select id="dogAgeSelect" class="w120 h30 lh30 bore pdl10 cor9 pc4" v-on:change="dogAgeSelectt">
            <option value="0">全部</option>
            <option value="1">3岁以下</option>
            <option value="2">4-8岁</option>
            <option value="3">8~12岁</option>
            <option value="4">12岁以上</option>
          </select>
        </div>
        <div class="fl cor3 h30 lh30 mgt20">
          <span>毛色：</span>
          <select id="colorSelect" class="w120 h30 lh30 bore pdl10 cor9 pc4" v-on:change="colorSelectt">
            <option value="-1">全部</option>
            <option v-for="item in colour" v-bind:value=item.value>{{item.name}}</option>
          </select>
        </div>
      </div>
      <ul class="ul08 mgt10">
        <li v-for="item in dogs" class="fl w291">
          <div style="cursor:pointer;" @click="toApply(item.collectionId,item.adoptionPublicityId)">
            <img class="ww100" v-bind:src="item.thumbnailUrl" alt="">
            <p class="fs18 cor3 fb t-c mgt18">{{item.dogBreed}}</p>
            <div class="pdl20 pdr20 ovh clearfix">
              <p class="ww50 fl cor6 mgt20">姓名：{{item.dogName}}</p>
              <p class="ww50 fl cor6 mgt20">犬龄：{{item.dogAge}}个月</p>
              <p class="ww50 fl cor6 mgt20">性别：{{item.dogGender==0?'雄':'雌'}}</p>
              <p class="ww50 fl cor6 mgt20">毛色：{{item.dogColor}}</p>
            </div>

          </div>
        </li>
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-2.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->
        <!--</div>-->
        <!--</li>-->
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-3.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->

        <!--</div>-->
        <!--</li>-->
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-1.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->

        <!--</div>-->
        <!--</li>-->
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-2.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->

        <!--</div>-->
        <!--</li>-->
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-3.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->

        <!--</div>-->
        <!--</li>-->
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-1.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->

        <!--</div>-->
        <!--</li>-->
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-2.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->

        <!--</div>-->
        <!--</li>-->
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-3.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->

        <!--</div>-->
        <!--</li>-->
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-1.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->

        <!--</div>-->
        <!--</li>-->
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-2.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->

        <!--</div>-->
        <!--</li>-->
        <!--<li class="fl w291">-->
        <!--<div>-->
        <!--<img class="ww100" src="../../../static/images/9-3.png" alt="">-->
        <!--<p class="fs18 cor3 fb t-c mgt18">哈士奇</p>-->
        <!--<div class="pdl20 pdr20 ovh clearfix">-->
        <!--<p class="ww50 fl cor6 mgt20">姓名：小花</p>-->
        <!--<p class="ww50 fl cor6 mgt20">性别：公</p>-->
        <!--<p class="ww50 fl cor6 mgt20">毛色：黑白色</p>-->
        <!--</div>-->

        <!--</div>-->
        <!--</li>-->
      </ul>


    </div>
    <!--爱心领养END-->
    <div class="clearfix"></div>

    <!--分页-->
    <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>

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
  let params = {
    selectParmas: {
      breed: null,
      gender: null,
      startAge: null,
      endAge: null,
      color: null
    }
  }
  import $ from 'jquery'

  var _this = '';
  export default {
    name: "loveAdoptt",
    data() {
      return {
        varieties: [],//品种
        gender: [],//性别
        dogsAge: [],//犬龄
        colour: [],//毛色
        dogs: [],
        totol: null,
        pageData: {
          curPage: params.pn ? params.pn : 1,//当前页吗
          totalCount: this.totalPage ? this.totalPage * params.pageSize : 1,//总条数
          pageSize: params.pageSize ? params.pageSize : 1//页面大小
        }
      }
    },
    created() {
      InitBreed();
      InitColor();
      picturePage(null);
    }, beforeCreate() {
      _this = this;
    },
    methods: {
      breedSelectt: function () {
        breedSelect();
      },
      sexSelectt: function () {
        sexSelect();
      },
      dogAgeSelectt: function () {
        dogAgeSelect();
      },
      colorSelectt: function () {
        colorSelect();
      },
      //跳转申请页
      toApply: function (id,dogPublicityId) {
        this.$router.push({name: 'adopt', params: {collectionId: id,dogPublicityId:dogPublicityId}})
      },
      chekdpage() {
      }
    }
  }

  //初始化品种
  function InitBreed() {
    _this.$axios({
      method: 'get',
      url: '/biz/adoption/selectdogPro',
      params: {logg: 1}
    }).then((res) => {
      if (res.status == 200) {
        _this.varieties = res.data
      }
    })
  }

  //初始化毛色
  function InitColor() {
    _this.$axios({
      method: 'get',
      url: '/biz/adoption/selectdogPro',
      params: {logg: 2}
    }).then((res) => {
      if (res.status == 200) {
        _this.colour = res.data
      }
    })
  }

  //犬只列表
  function picturePage(string) {
    _this.$axios({
      method: 'get',
      url: '/biz/collection/findAdoptionsList',
      params: {page: 1, rows: 12, string: string}
    }).then((res) => {
      console.log(res.data)
      if (res.status == 200) {
        _this.dogs = res.data.rows
        _this.totol = res.data.records
      }
    })
  }

  //品种搜索
  function breedSelect() {
    var breed = $("#breedSelect option:selected").val();
    if (breed == -1) {
      params.selectParmas.breed = null;
    } else {
      params.selectParmas.breed = breed;
    }
    picturePage(JSON.stringify(params.selectParmas));
  }

  //性别搜索
  function sexSelect() {
    var gender = $("#sexSelect option:selected").val();
    if (gender == -1) {
      params.selectParmas.gender = null;
    } else {
      params.selectParmas.gender = gender;
    }
    picturePage(JSON.stringify(params.selectParmas));
  }

  //犬龄搜索
  function dogAgeSelect() {
    var age = $("#dogAgeSelect option:selected").val();
    if (age == 0) {
      params.selectParmas.startAge = null;
      params.selectParmas.endAge = null;
    } else if (age == 1) {
      params.selectParmas.startAge = null;
      params.selectParmas.endAge = 3;
    } else if (age == 2) {
      params.selectParmas.startAge = 3;
      params.selectParmas.endAge = 8;
    } else if (age == 3) {
      params.selectParmas.startAge = 8;
      params.selectParmas.endAge = 12;
    } else {
      params.selectParmas.startAge = 12;
      params.selectParmas.endAge = null;
    }
    picturePage(JSON.stringify(params.selectParmas));
  }

  //毛色搜索
  function colorSelect() {
    var color = $("#colorSelect option:selected").val();
    if (color == -1) {
      params.selectParmas.color = null;
    } else {
      params.selectParmas.color = color;
    }
    picturePage(JSON.stringify(params.selectParmas));
  }
</script>

<style scoped>

</style>
