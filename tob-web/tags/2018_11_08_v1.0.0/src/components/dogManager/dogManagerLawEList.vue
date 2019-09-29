<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="ovh">
      <div class="fl cor6 h40 lh40">
        <!--<a href="###" class="db fl hov8 corffa472 mgr35 pc7 pdl36">发消息</a>-->
        <!--<a href="###" class="db fl hov8 corffa472 pc8 pdl36">精准搜索</a>-->

      </div>
      <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
        <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="姓名/身份证号" v-model="keyWord" @keyup.enter="search">
        <input type="submit" value="" class="in1 db abs z100 top0 bort right0 curpoint" @click.prevent="search">
      </form>
    </div>

    <div class="mgt10 k3">
      <!--<div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">违法信息</div>-->
      <div class="t-c">
        <table class="table5" width="100%" border="1">
          <tbody>
          <tr>
            <td class="cor0 h60 bgf1">
              <label class="lab3 mgr40"><input type="checkbox"><i></i></label>
            </td>
            <td class="cor0 h60 bgf1">违法时间</td>
            <td class="cor0 h60 bgf1">违法行为</td>
            <td class="cor0 h60 bgf1">犬主</td>
            <td class="cor0 h60 bgf1">犬主标识号</td>
            <td class="cor0 h60 bgf1">处罚方式</td>
            <td class="cor0 h60 bgf1">累计扣分</td>
            <td class="cor0 h60 bgf1">操作</td>
          </tr>

          <tr v-for="(item,index) in lawEList" :key="index">
            <td>
              <label class="lab3 mgr40"><input type="checkbox" :checked="item.isCheckd"><i></i></label>
            </td>
            <td>{{item.violationDate | formatDate(dataFormatPattern)}}</td>
            <td>{{item.violationType}}</td>
            <td>
              <p>{{item.dogOwnerName}}</p>
              <p class="cor9 mgt5">13800121254</p>
            </td>
            <td>{{item.deviceNumber}}</td>
            <td>{{item.isCollection}}</td>
            <td>{{item.points?'-'+item.points:'0'}}</td>
            <td class="t-c">
              <router-link class="dinb cor009cff hov8" :to="{name:'dogManager_LawEInfo',params:{'violationId':item.violationId}}">查看详情</router-link>
              <!--<a href="###" class="dinb cor009cff hov8">查看详情</a>-->
            </td>
          </tr>

          <!--<tr>-->
            <!--<td>-->
              <!--<label class="lab3 mgr40"><input type="checkbox" checked><i></i></label>-->
            <!--</td>-->
            <!--<td>2017-01-01</td>-->
            <!--<td>不牵狗绳</td>-->
            <!--<td>-->
              <!--<p>张三</p>-->
              <!--<p class="cor9 mgt5">13800121254</p>-->
            <!--</td>-->
            <!--<td>2018-REDF-ET</td>-->
            <!--<td>收容</td>-->
            <!--<td>-12</td>-->
            <!--<td class="t-c">-->
              <!--<router-link class="dinb cor009cff hov8" :to="{name:'dogManager_LawEInfo',params:{'violationId':1}}">查看详情</router-link>-->
            <!--</td>-->
          <!--</tr>-->
          </tbody>
        </table>
      </div>
    </div>
    <!--分页-->
    <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
    <!--分页-->
  </div>
  <!--内容区END-->
</template>

<script>
  export default {
    name: "dogManagerLawEList",
    data() {
      return {
        totalCount: 0,//总条数
        lawEList: [],//执法处罚列表
        dataFormatPattern :'yyyy年MM月dd日',
        keyWord:'',//搜索关键字
      }
    },
    methods: {
      //分页
      chekdpage(currentPage) {
        params.pn = currentPage//页码
        initData(this)
      },
      //搜索
      search(){
        initData(this)
      },
    },
    computed: {
      //分页参数
      pageData() {
        return {
          curPage: params.pn,//当前页吗
          totalCount: this.totalCount,//总条数
          pageSize: params.pageSize//页面大小}
        }
      },

    }, created() {
      initData(this)
    },
    watch:{
      keyWord(newVal,oldVal){
        params.string = newVal;
        initData(this)
      }
    }
  }
  //请求参数
  let params = {
    pn: 1,
    pageSize: 10,
    string: ''
  }

  //查询列表
  function initData(_this) {
    if (!params.string || params.string.trim().length == 0)
      delete  params.string

    _this.$axios({
      url: '/biz/violationPunish/findAll',
      params,isloading: true
    }).then((res) => {
      if (res.data.status == 200) {
        _this.totalCount = res.data.data.totalRecord

        _this.lawEList = res.data.data.lists
        //添加checkd标示
        _this.lawEList.map(item=>{
          item.isCheckd = false
        })

      }
    }).catch(_ => { })
  }
</script>

<style scoped>

</style>
