<template>
  <div class="ww100 pdb130 pdl15 pdr15 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">预算使用明细</p>
        <a style="cursor:pointer;" class="db fr fs18 cor0" @click="returnn">返回>></a>
      </div>

      <div class="ovh mgt20">
        <div class="fl mgr20">
          <select class="form-control ios0" v-model="map.userId" @change="selectChange">
            <option :value="item.id" v-for="item in uList" :key="item.id">{{item.name}}</option>
          </select>
        </div>
        <div class="fr">
          <div  class="form-inline" @keyup.13="search">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="商品名称/编号" v-model="map.searchKey">
              <span class="input-group-btn">
							<button type="submit" class="btn bged145b" @click="search">
								<i class="glyphicon glyphicon-search corw"></i>
							</button>
						</span>
            </div>
          </div>
        </div>
      </div>

      <div class="mgt30">
        <table class="tab2">
          <tbody>
          <tr>
            <td>序号</td>
            <td>商品编号</td>
            <td>商品名称</td>
            <td>采购人员</td>
            <td>采购日期</td>
            <td>采购数量</td>
            <td>采购单价</td>
            <td>总金额</td>
            <td>扣除预算</td>
          </tr>
          <tr v-for="(item,index) in budgetDetailUseList">
            <td>{{index + 1}}</td>
            <td>{{item.code}}</td>
            <td>{{item.name}}</td>
            <td>{{item.buyMan}}</td>
            <td>{{item.buyDate | formatDate("yyyy-MM-dd HH:mm:ss")}}</td>
            <td>{{item.buyNum}}</td>
            <td>{{item.price}}</td>
            <td>{{item.buyNum * item.price}}</td>
            <td>{{item.buyNum * item.price}}</td>
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
</template>

<script>
  var _this = '';
    export default {
        name: "budgetUseDetail",
      data(){
          return{
              map:{
                budgetYear:0,
                subjectId:0,
                orgId:0,
                searchKey:null,
                pageNum:1,
                pageSize:10,
                userId:0
              },
            budgetDetailUseList:[],
            pageData:{
              curPage:1,//当前页码
              totalCount:1,//总条数
              pageSize:1//页面大小
            },
            uList:[],//可以下拉的用户列表
            myTab:null

          }
      },
      mounted(){

      },
      created(){
        _this.map.budgetYear=this.$route.query.budgetYear;
        _this.map.subjectId=this.$route.query.subjectId ? this.$route.query.subjectId : 0;
        _this.map.orgId=this.$route.query.orgId;
        this.myTab=this.$route.query.myTab;
        initBudgetDetailUseList();

        //查侠下拉框用户信息
        initSelectUser()
      },
      beforeCreate(){
        _this=this;
      },
      methods:{
        chekdpage: function(pageNum) {//分页
          _this.map.pageNum = pageNum;
          _this.pageData.curPage = pageNum;
          initBudgetDetailUseList();
        },
        search:function () {
          initBudgetDetailUseList();
        },
        selectChange(){
          this.map.pageNum = 1;
          _this.pageData.curPage = 1;
          initBudgetDetailUseList();
        },
        returnn(){
          // this.$router.back(-1);
          this.$router.push({name:'bm_budgetManage',params:{myTab:this.myTab}});
        }

      }

    }
  function initBudgetDetailUseList(){//初始化预算使用详细列表
      _this.$axios({
        method: 'get',
        url: '/biz/tob-budget/biz/budget/budgetDetailUseList',
        params:  _this.map
      }).then((res) => {
        if(res.data.code==200){
          _this.budgetDetailUseList=res.data.obj.rows;
          _this.pageData.totalCount=res.data.obj.total;//总条数
          _this.pageData.totalCount=res.data.obj.total;//总条数
          _this.pageData.curPage= _this.map.pageNum;//当前页吗
          _this.pageData.pageSize= _this.map.pageSize;//页面大小
        }
      }).catch((err) => {
        console.log(err);
      })
  }

  function initSelectUser(){
    let params = {
       "subjectId":_this.$route.query.subjectId ? _this.$route.query.subjectId : 0,
       "budgetYear":_this.$route.query.budgetYear,
       "orgId":_this.$route.query.orgId
    }
    _this.$route.query.subjectId
    _this.$axios({
      url:"/biz/tob-budget/biz/budget/users",
      method:"GET",
      params,
    }).then(res=>{
      if(res.data.success){
        _this.uList = res.data.obj;
        _this.uList.unshift({
          "id":"0",
          "name":"所有人  --",
        })
      }
    })
  }

</script>

<style scoped>
select{
  text-align: center;
  /*text-align-last: center;*/
}
</style>
