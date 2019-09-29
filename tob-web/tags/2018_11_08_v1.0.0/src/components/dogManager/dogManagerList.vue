<template>
  <!--内容区-->
  <div class="w980 fr">

    <!--headler-->
    <div class="ovh">
      <div class="fl cor6 h40 lh40">
        <router-link class="db fl hov8 corffa472 mgr35 pc9 pdl36" :to="{name:'dogManager_dogadd'}">办理犬证</router-link>
        <!--<a href="###" class="db fl hov8 corffa472 mgr35 pc7 pdl36">发消息</a>-->
        <!--<a href="###" class="db fl hov8 corffa472 pc8 pdl36">精准搜索</a>-->
      </div>
      <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
        <input type="text" @keyup.enter="search" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort"
               @dblclick="$event.target.select()" v-model="params.input" placeholder="犬主姓名/身份证号/犬证号/免疫证号/标示牌号">
        <input type="text" value="" class="seachClick in1 db abs z100 top0 bort right0" @click="search">
      </form>
    </div>
    <!--headler end-->

    <!--table -->
    <div class="mgt10 k3">
      <!--<div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">违法信息</div>-->
      <div class="t-c">
        <table class="table5" width="100%" border="1">
          <tbody>

          <tr>
            <td class="cor0 h60 bgf1 teCen" @click="selectAll" >全选</td>
            <td class="cor0 h60 bgf1 teCen t-l pdl100" style="width: 360px;">犬只信息</td>
            <td class="cor0 h60 bgf1 teCen">犬主</td>
            <td class="cor0 h60 bgf1 teCen">权证号</td>
            <td class="cor0 h60 bgf1 teCen">免疫证号</td>
            <td class="cor0 h60 bgf1 teCen">操作</td>
          </tr>

          <tr v-for="(item,index) in dogList" :key="index">
            <td>
              <label class="lab3 mgr40"><input type="checkbox" v-model="item.checkd"><i></i></label>
            </td>
            <td class="pdt20 pdb20">
              <div class="ovh t-l">
                <div class="fl w76"><img :src="item.url?item.url:'/static/images/b7.png'" alt=""></div>
                <div class="mgl98">
                  <div class="cor0 h22 lh22">
                    {{item.dogName}}
                    <a href="###" class="dinb a5 w66 h22 lh22 t-c fs12 corw hov8 mgl20"
                       v-if="item.endTime && item.endTime>new Date()"></a>
                    <a href="###" class="dinb a6 w66 h22 lh22 t-c fs12 corw hov8 mgl6"
                       v-if="item.nextInsDate && item.nextInsDate>new Date()">年检超期</a>
                  </div>
                  <div class="cor9 mgt10 ovh">
                    <p class="fl mgr30">性别：{{item.dogGender==0?'雄':item.dogGender==1?'雌':'未指定'}}</p>
                    <p class="fl">品种：{{item.breed}}</p>
                  </div>
                  <div class="cor9 mgt10 ovh">
                    <p class="fl mgr30">犬龄：{{item.dogAge?item.dogAge +'个月':'-'}}</p>
                    <p class="fl">毛色：{{item.colour?'毛色：'+item.colour:'-'}}</p>
                  </div>
                </div>
              </div>
            </td>
            <td class="t-c ">
              <p>{{item.dogOwnerName?item.dogOwnerName:'-'}}</p><br>
              <p class="cor9">{{item.phone}}</p>
            </td>
            <td class="teCen">{{item.dogCardNum?item.dogCardNum:'-'}}</td>
            <td class="teCen">{{item.immuneCardNum?item.dogCardNum:'-'}}</td>
            <td class="t-c">
              <router-link class="dinb cor009cff hov8" :to="{name:'dogManager_info',params:{dogId:item.dogId,name:'犬只档案'}}">查看详情
              </router-link>
            </td>
          </tr>

          </tbody>
        </table>
      </div>
    </div>
    <!--table end-->

    <!--分页-->
    <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
    <!--分页-->

  </div>
  <!--内容区END-->
</template>

<script>

  export default {
    name: "dogManagerList",
    data() {
      return {
        dogList: [],//犬只列表
        page: [],//分页对象
        params: {//请求参数
          num: 1,
          size: 6,
          input: ''
        },

      }
    },
    created() {
      initData(this)
    },
    mounted() {
      console.log(this.$data)
    },
    watch: {},
    methods: {
      search() {//搜索
        initData(this)
      },
      chekdpage(pageNum) {
        this.params.num = pageNum
        initData(this)
      },
      selectAll() {//全选
        this.dogList.map(item => {

        })
      }
    },
    computed: {
      pageData() {//分页参数
        let _this = this;
        return {
          curPage: _this.page.pageNum,//当前页吗
          totalCount: _this.page.totalCount,//总条数
          pageSize: _this.page.pageSize//页面大小
        }
      }
    }

  }

  function initData(_this) {
    if (!_this.params.input || _this.params.input.trim().length == 0) {
      delete _this.params.input//判断查询参数
    }
    _this.$axios({
      method: 'post',
      url: '/biz/dogAndOwner/selectDogList',
      data: _this.params
    }).then((res) => {
      if (res.data.code == '000000' && res.data.result != null) {
        res.data.result.dogInfos.map(item => {
          item.checkd = false//添加选中标示 默认未选中
        })
        _this.dogList = res.data.result.dogInfos;
        _this.page = res.data.result.page;

        //分页数据
        // this.pageData.curPage = _this.page.pageNum;//当前页吗
        // this.pageData.totalCount = _this.page.totalCount;//总条数
        // this.pageData.pageSize = _this.page.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }
</script>

<style scoped>
  .seachClick:hover {
    cursor: pointer;
  }

  .teCen {
    text-align: center;
  }

</style>
