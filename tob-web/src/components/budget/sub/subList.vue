<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">
    <div>
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">科目管理</p>
        <a href="###" class="db fr fs18 cor0" @click.prevent="returnLastPage">返回>></a>
      </div>
      <div class="ovh mgt20">
        <ul class="ulDiv fl" v-if="isAdminBud">
          <!--<li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios2">新增科目</a></li>-->
          <router-link :to="{name:'subAdd',query:{subDefineId:subDefineId}}" class="db pdl29 corED145B ios2">新增科目</router-link>
        </ul>
      </div>
      <div class="mgt30">
        <table class="tab2">
          <tbody>
          <tr>
            <td>科目编号</td>
            <td>科目名称</td>
            <td>上级科目</td>
            <td>预算类型</td>
            <td>操作</td>
          </tr>
          <tr v-for="(item,index) in subList">
            <td>{{ item.id }}</td>
            <td>{{ item.subName }}</td>
            <td>{{ item.pName?item.pName:'--' }}</td>
            <td v-if="item.type">{{ item.type ==1?"年度预算":"月度预算" }}</td>
            <td v-else>--</td>
            <td>
              <!--<router-link  :to="{ name:'subAdd',query:{subPid:item.id,subDefineId:subDefineId} }" class="btn btn-xs btn-primary">新增下级科目</router-link>-->
              <a href="###" class="btn btn-xs btn-danger" @click.prevent="deleteSub(item.id)" v-if="isAdminBud">删除</a>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <!--分页-->
      <!--<div class="fen c-b ovh mgt30 t-c w410">-->
        <!--<page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>-->
      <!--</div>-->
      <!--分页END-->
    </div>

  </div>
</template>

<script>
  export default {
    name: "subList",
    data() {
      return {
        subDefineId:this.$route.query.subDefineId,  //科目定义id
        isAdminBud: true,
        params: {
          orgId: 1, pageNum: 1, pageSize: 10, searchKey: ''
        },
        subList: [],
        pageData: {
          curPage: 1,//当前页吗
          totalCount: 1,//总条数
          pageSize: 1//页面大小
        },
      }
    },

    created() {
      this.configJS.getUserInfo(this);
      initSubList(this);


      var isAdminBud = this.configJS.isAdminBud();
      this.isAdminBud = isAdminBud;
    },


    methods: {


      returnLastPage() {
        this.$router.push({name: "subDefineList"})
      },


      submit() {
        initSubList(this);
      },

      test() {
        console.log("subDefineId", this.$route.query.subDefineId);
      },

      /******* 分页**********/
      chekdpage: function (pageNum) {
        this.params.pageNum = pageNum;
      },

      /**
       * 删除科目
       */
      deleteSub(subId) {
        deleteSub(this, subId);
      }

    }
  }

  function deleteSub(that, subId) {
    that.$axios({
      url: "/biz/tob-budget/subject/deleteById",
      params: {
        subId: subId
      }
    }).then((res) => {
      console.log(res);
      if (res.data && res.data.code == 200) {
        that.$message({
          type: 'success',
          message: '删除成功!',
          duration: 1000,
          onClose() {
            initSubList(that);
          }
        })
      }

    })
  }

  /**
   * 获取科目列表
   */
  function initSubList(that) {
    var params = that.params;
    params.orgId = that.configJS.userInfo.orgId;
    that.$axios({
      url: '/biz/tob-budget/subject/getSubs/'+that.subDefineId,
    }).then((res) => {
      console.log(res);
      if (res.status == 200 && res.data.code == 200) {
        var subList = res.data.obj;
        if (subList){
          subList.forEach( (item,index)=>{
            subList.forEach( (x,index) =>{
              if (item.pId == x.id){
                item.pName = x.subName
              }
            } )
          })
        }
        that.subList = subList;
      }
    }).catch((err) => {
      console.log(err);
    })
  }


</script>

<style scoped>

</style>
