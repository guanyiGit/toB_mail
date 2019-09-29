<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">
    <div>
      <p class="fs18 h28 lh28 cor0">科目管理</p>
      <div class="mgt30">
        <div class="ovh mgt20">
          <ul class="ulDiv fl" v-if="isAdminBud">
            <!--<li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios2">新增科目</a></li>-->
            <router-link :to="{name:'addSubDefine'}" class="db pdl29 corED145B ios2">新增科目定义</router-link>
          </ul>
          <div class="fr">
            <form @submit.prevent="submit" class="form-inline">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="科目定义名称/年份" v-model="params.query">
                <span class="input-group-btn">
							<button type="submit" class="btn bged145b">
								<i class="glyphicon glyphicon-search corw"></i>
							</button>
						</span>
              </div>
            </form>
          </div>
        </div>
        <div class="mgt30">
          <table class="tab2">
            <tbody>
            <tr>
              <td>预算科目定义年份</td>
              <td>预算科目定义名称</td>
              <td>操作</td>
            </tr>
            <tr v-for="(item,index) in defineList">
              <td>{{ item.year }}</td>
              <td>{{ item.subDefineName }}</td>
              <td>
                <!--<a href="###" class="btn btn-xs btn-success">查看</a>-->
                <router-link  :to="{name:'subList',query:{ 'subDefineId':item.id }}" class="btn btn-xs btn-primary">科目管理</router-link>
                <a href="###" class="btn btn-xs btn-danger" @click.prevent="deleteSubDefine(item.id)" v-if="isAdminBud">删除</a>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
      <!--分页-->
      <div class="fen c-b ovh mgt30 t-c w410">
        <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
      </div>
      <!--分页END-->
    </div>
  </div>
</template>

<script>
    export default {
        name: "subDefineList",

      data(){
          return {
            params:{orgId: 1, year:"", pageNum: 1, pageSize: 10, query: ''},
            pageData: {
              curPage: 1,//当前页吗
              totalCount: 1,//总条数
              pageSize: 1//页面大小
            },
            defineList:[],
            isAdminBud:true,

          }
      },

      created(){
        this.configJS.getUserInfo(this);
        initDefineList(this);
        var isAdminBud = this.configJS.isAdminBud();
        this.isAdminBud = isAdminBud;
      },

      methods:{

        deleteSubDefine(subDefineId){
          deleteSubDefine(this,subDefineId)
        },

        /******* 分页**********/
        chekdpage: function (pageNum) {
          this.params.pageNum = pageNum;
        },
      }
    }

    function deleteSubDefine(that,subDefineId) {
        that.$axios({
          url:"/biz/tob-budget/subject/deleteSubDefine",
          params:{
            subDefineId:subDefineId
          }
        }).then( (res) => {
          console.log(res);
          if (res.data.code == 200){
            that.$message({
              type: 'success',
              message: '删除成功!',
              duration: 1000,
              onClose(){
                initDefineList(that)
              }
            })
          }

        } )
    }

   function initDefineList(that) {
      var params = that.params;
     params.orgId = that.configJS.userInfo.orgId;
     that.$axios({
       url:'/biz/tob-budget/subject/getSubDefines',
       params :params,
     }).then( (res) =>{
      console.log(res);
      if( res.data.code == 200){
         that.defineList = res.data.obj;
      }
     })
   }
</script>

<style scoped>

</style>
