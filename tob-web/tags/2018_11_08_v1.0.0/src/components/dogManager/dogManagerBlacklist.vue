<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="ovh">
      <div class="fl cor6 h40 lh40">
        <!--<a href="###" class="db fl hov8 corffa472 mgr35 pc9 pdl36">新增</a>-->
        <router-link class="db fl hov8 corffa472 mgr35 pc9 pdl36" :to="{name:'hospice_blackadd'}">新增</router-link>
        <a href="###" class="db fl hov8 corffa472 mgr35 pc10 pdl36" @click.prevent="removeAl">移除</a>
        <!--<a href="###" class="db fl hov8 corffa472 pc7 pdl36">发消息</a>-->

      </div>
      <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
        <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" @dblclick="$event.target.select()"
               placeholder="姓名/身份证号/电话" v-model="keyWord"
               @keyup.enter="search">
        <input type="submit" value="" class="seachClick in1 db abs z100 top0 bort right0" @click.prevent="search">
      </form>
    </div>

    <div class="mgt10 k3">
      <!--<div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">违法信息</div>-->
      <div class="t-c">
        <table class="table5" width="100%" border="1">
          <tbody>
          <tr>
            <td class="cor0 h60 bgf1 t-c">全选</td>
            <td class="cor0 h60 bgf1">姓名</td>
            <td class="cor0 h60 bgf1">身份证号</td>
            <td class="cor0 h60 bgf1">联系电话</td>
            <td class="cor0 h60 bgf1">列入黑名单原因</td>
            <td class="cor0 h60 bgf1">操作</td>
          </tr>

          <tr v-for="(item,index) in userList" :key="index">
            <td>
              <label class="lab3 mgr40"><input type="checkbox" v-model="item.isCheckd"><i></i></label>
            </td>
            <td class="cor3">{{item.blackName}}</td>
            <td class="cor3">{{item.cardNum}}</td>
            <td>{{item.phone}}</td>
            <td>{{item.joinCause}}</td>
            <td class="t-c">
              <!--<a href="###" class="dinb cor009cff hov8 mgr14">查看详情</a>-->
              <router-link class="dinb cor009cff hov8 mgr14"
                           :to="{name:'hospice_blackinfo',params:{blackId:item.blackId}}">查看详情
              </router-link>
              <a href="###" class="dinb cor009cff hov8" @click.prevent="remove(item.blackId)">移除</a>
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
    pageSize: 10,
    pn: 1,
    string: '' //搜索参数
  }
  export default {
    name: "dogManagerBlacklist",
    data() {
      return {
        totalPage: 0,//总页数
        userList: [],//用户列表
        keyWord: '',//搜索关键字
      }
    },
    created() {
      initData(this)
    },
    mounted() {
      console.log(this.$data)
    },
    computed: {
      pageData() {
        return {
          curPage: params.pn ? params.pn : 1,//当前页吗
          totalCount: this.totalPage ? this.totalPage * params.pageSize : 1,//总条数
          pageSize: params.pageSize ? params.pageSize : 1//页面大小
        }
      }
    },
    methods: {
      chekdpage(pageNum) {
        params.pn = pageNum
        initData(this)
      },
      search() {//搜索
        initData(this)
      },
      removeAl() {//移除选择的多个
        let blackIdList = [];//需要删除的id集合
        this.userList.map(item => {
          if (item && item.isCheckd) {//所有选中的
            blackIdList.push(item.blackId)
          }
        })

        if (blackIdList.length > 0) {
          removeItem(this, blackIdList);
        } else {
          this.$alert("未选择一项！")
        }
      },
      remove(blackId) {//单个移除
        if (blackId) {
          removeItem(this, [blackId]);
        }
      }
    },
    watch: {
      keyWord(newVal, oldVal) {
        params.string = newVal;
        initData(this)
      }
    }
  }

  function initData(_this) {
    if (!params.string) {
      delete params.string
    }
    _this.$axios({
      url: '/biz/balck/findAll',
      method: 'get',
      params: params,
      isloading: true,
    }).then((res) => {
      if (res.data.status == 200) {
        if (res.data.data == null) {
          _this.userList = []
          _this.totalPage = 0
        } else {
          res.data.data.lists.map(item => {
            item.isCheckd = false;//默认未选中
          })
          _this.userList = res.data.data.lists
          _this.totalPage = res.data.data.totalPage
        }
      }
    }).catch((res) => {
      console.log(res)
    })
  }

  //移除黑名单
  function removeItem(_this, blackIdList) {
    if (blackIdList && blackIdList.length > 0) {
      _this.$axios({
        url: '/biz/balck/remove',
        method: "get",
        params: {"blackIdList": blackIdList.join()},
        isloading: true,
      }).then(res => {
        if (res.data.status == 200) {
          _this.$alert("移除成功!")
          initData(_this)
        } else {
          _this.$alert("移除失败！")
        }
      })
    }
  }

</script>

<style scoped>
  .seachClick:hover {
    cursor: pointer;
  }
</style>
