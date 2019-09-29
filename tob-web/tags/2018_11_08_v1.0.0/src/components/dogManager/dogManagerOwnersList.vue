<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="ovh">
      <div class="fl cor6 h40 lh40">
        <!--<a href="###" class="db fl hov8 corffa472 mgr35 pc7 pdl36">发消息</a>-->
      </div>
      <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
        <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" v-model="searchKey" placeholder="犬主姓名/身份证号">
        <input type="submit" value="" class="in1 db abs z100 top0 bort right0 cupo" @click.prevent="clickSearch">
      </form>
    </div>

    <div class="mgt10 k3">
      <!--<div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">违法信息</div>-->
      <div class="t-c">
        <table class="table5" width="100%" border="1">
          <tbody>
          <tr>
            <td class="cor0 h60 bgf1">犬主姓名</td>
            <td class="cor0 h60 bgf1">身份证号</td>
            <td class="cor0 h60 bgf1">联系电话</td>
            <td class="cor0 h60 bgf1">所属区域</td>
            <td class="cor0 h60 bgf1">犬只数</td>
            <td class="cor0 h60 bgf1 txenter">操作</td>
          </tr>
          <tr v-for="(item,index) in list" :key="index">
            <td>{{item.dogOwnerName}}</td>
            <td>{{item.cardNum}}</td>
            <td>{{item.dogOwnerPhone}}</td>
            <td>{{item.districtName}}</td>
            <td>{{item.dogNum}}</td>
            <td class="t-c">
              <router-link :to="{name:'dogManager_owners_info',params:{id:item.dogOwnerId}}"
                           class="dinb cor009cff hov8 mgr14">查看详情
              </router-link>
              <!--<a href="###" class="dinb cor009cff hov8">发消息</a>-->
            </td>
          </tr>

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

  let params = {
    input: null,
    num: 1,
    size: 10
  }
  export default {
    name: "dogManagerOwnersList",
    data() {
      return {
        searchKey: '',
        totalCount: 0,
        list: {},
      }
    },
    created() {
      initData(this)
    },
    watch: {
      searchKey(newVal, oldVal) {
        if (newVal && newVal != oldVal && newVal.trim().length > 0) {
          params.input = newVal;
          initData(this)
        }
      }
    },
    methods: {
      clickSearch() {
        initData(this);
      },
      //分页
      chekdpage(currentPage) {
        params.num = currentPage//页码
        initData(this)
      },
    },
    computed: {
      //分页参数
      pageData() {
        return {
          curPage: params.num,//当前页吗
          totalCount: this.totalCount,//总条数
          pageSize: params.size//页面大小}
        }
      },
    }
  }

  function initData(_this) {
    if (!params.input) delete  params.input
    _this.$axios({
      url: '/biz/dogAndOwner/selectOwnerArchivesList',
      params
    }).then(res => {
      if (res.data) {
        _this.totalCount = res.data.total
        _this.list = res.data.rows
      }
      console.log(_this.$data)
    })
  }
</script>

<style scoped>

  .cupo {
    cursor: pointer;
  }
  .txenter{
    text-align: center;
  }

  td:first-child {
    padding-left: 30px;
  }
</style>
