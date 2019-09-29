<template>
    <!--收容所执法管理-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="ovh">
        <div class="fl cor6 h40 lh40">
          <router-link style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pc9 pdl36" :to="{name:'hospice_illegalRegistration'}">违法登记</router-link>
          <a style="cursor:pointer;display: none" class="db fl hov8 corffa472 mgr35 pc7 pdl36">信息发布</a>
          <!--<a href="###" class="db fl hov8 corffa472 pc8 pdl36">精准搜索</a>-->

        </div>
        <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
          <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="标题" v-model="keyWord" @keyup.enter="search">
          <input style="cursor:pointer;" value="" class="in1 db abs z100 top0 bort right0" @click.prevent="search">
        </form>
      </div>

      <div class="mgt20 k3">
        <!--<div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
                  犬只/犬主基本信息
              </div>-->
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
              <td class="cor0 h60 bgf1">犬只标识号</td>
              <td class="cor0 h60 bgf1">本次扣分</td>
              <td class="cor0 h60 bgf1">是否收容</td>
              <td class="cor0 h60 bgf1">操作</td>
            </tr>
            <tr v-for="(item,index) in lawEList" :key="index">
              <td>
                <label class="lab3 mgr40"><input type="checkbox" :checked="item.isCheckd"><i></i></label>
              </td>
              <td>{{item.violationDate | formatDate('yyyy-MM-dd')}}</td>
              <td>{{item.violationType}}</td>
              <td>
                <p>{{item.dogOwnerName}}</p>
                <p class="cor9 mgt5">{{item.dogOwnerPhone}}</p>
              </td>
              <td>{{item.deviceNumber}}</td>
              <td>{{item.points}}</td>
              <td>{{item.isCollection}}</td>
              <td class="t-c">
                <router-link style="cursor:pointer;" class="dinb cor009cff hov8" :to="{name:'enforcementManagementDetails',params:{info:JSON.stringify(item)}}">查看详情</router-link>
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
  </div>
</template>

<script>
  //请求参数
  let params = {
    pn: 1,
    pageSize: 10,
    string: ''
  }
    export default {
        name: "enforcementManagement",
      data() {
        return {
          totalCount: 0,//总条数
          lawEList: [],//执法管理列表
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
        // //违法登记
        // illegalRegistration(){
        //   let blackIdList = [];//需要删除的id集合
        //   this.lawEList.map(item => {
        //     if (item && item.isCheckd) {//所有选中的
        //       var dat={
        //         dogId:item.dogId,
        //         dogOwnerId:item.dogId
        //       }
        //       blackIdList.push(dat)
        //     }
        //   })
        //   if (blackIdList.length > 0) {
        //     this.$router.push({name: 'illegalRegistration', params: {dogId: blackIdList[0].dogId,dogOwnerId:blackIdList[0].dogOwnerId}})
        //   }else if (blackIdList.length > 1) {
        //     this.$alert("只能选择一只犬！")
        //   } else{
        //     this.$alert("未选择！")
        //   }
        // }
      },
      computed: {
        //分页参数
        pageData() {
          return {
            curPage: params.pn,//当前页码
            totalCount: this.totalCount,//总条数
            pageSize: params.pageSize//页面大小
          }
        },

      },
      created() {
        initData(this)
      },
      watch:{
        keyWord(newVal,oldVal){
          params.string = newVal;
        }
      }
    }

    //查询列表
    function initData(_this) {
      if (!params.string || params.string.trim().length == 0) delete  params.string
      _this.$axios({
        url: '/biz/violationPunish/findAll',
        params:params
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
