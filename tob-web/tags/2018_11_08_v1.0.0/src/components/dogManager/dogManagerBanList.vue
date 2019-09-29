<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="ovh">
      <div class="fl cor6 h40 lh40">
        <router-link :to="{name:'dogManager_BanAdd'}" class="db fl hov8 corffa472 mgr35 pc9 pdl36">新增禁养犬种</router-link>
        <!--<router-link :to="{name:'dogManager_BanAdd'}"></router-link>-->
        <!--<a href="###" class="db fl hov8 corffa472 pc8 pdl36">精准搜索</a>-->

      </div>
      <!--<form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
                <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="姓名/身份证号">
                <input type="submit" value="" class="in1 db abs z100 top0 bort right0">
            </form>-->
    </div>

    <div class="mgt10 k3">
      <!--<div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">违法信息</div>-->
      <div class="t-c">
        <table class="table5" width="100%" border="1">
          <tbody>
          <tr>
            <td class="cor0 h60 bgf1">犬只图片</td>
            <td class="cor0 h60 bgf1">禁养犬种</td>
            <td class="cor0 h60 bgf1">发布时间</td>
            <td class="cor0 h60 bgf1">备注</td>
            <td class="cor0 h60 bgf1">操作</td>
          </tr>

          <tr v-for="(item,index) in banEList " :key="index">
            <td class="pdt20 pdb20"><img style="height: 51px;" :src="item.tImages&& item.tImages.length>0?item.tImages[0].thumbnailUrl:''" alt=""></td>
            <td>{{item.tDictionary ?item.tDictionary.dictionaryDescribe:''}}</td>
            <td>{{item.creationTime|formatDate(dataFormatPattern)}}</td>
            <td>{{item.note}}</td>
            <td class="t-c">
              <a href="###" class="dinb cor009cff hov8" @click.prevent="remove(item.dogBanId)">移出</a>
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
  export default {
    name: "operatorMsgAuditList",
    data() {
      return {
        totalCount: 0,//总条数
        banEList: [],//禁养犬种列表
        dataFormatPattern: 'yyyy年MM月dd日',
      }
    },
    methods: {
      //分页
      chekdpage(currentPage) {
        params.pn = currentPage//页码
        initData(this)
      },
      //搜索
      search() {
        initData(this)
      },
      //移除
      remove(dogBanId) {
        let alertMsg = '操作失败！'
        this.$axios({
          method: 'post',
          url: '/biz/ban/remove',
          isloading: true,
          params:{'ids':[dogBanId].join()}
        }).then((res) => {
          if (res.data.status == 200) {
            alertMsg = "操作成功！"
          }
        }).catch(_ => {
        }).finally(_ => {
          this.$alert(alertMsg, '移除提示')
          initData(this);
        })
      }

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
    watch: {
      keyWord(newVal, oldVal) {
        params.string = newVal;
      }
    }
  }
  //请求参数
  let params = {
    pn: 1,
    pageSize: 6,
    string: ''
  }

  //查询列表
  function initData(_this) {
    if (!params.string || params.string.trim().length == 0) delete  params.string
    _this.$axios({
      url: '/biz/ban/findAll',
      params,
      isloading: true,
    }).then((res) => {
      if (res.data.status == 200 && res.data.data) {
        _this.totalCount = res.data.data.totalRecord;
        _this.banEList = res.data.data.lists;
        //添加checkd标示
        _this.banEList.map(item => {
          item.isCheckd = false
        })
        console.log(res)
      }
    }).catch(_ => {
    })
  }

</script>

<style scoped>
td:first-child{
  padding-left: 10px;
}
</style>
