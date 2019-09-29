<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">

    <div>
      <p class="fs18 h28 lh28 cor0">预算管理</p>
      <ul class="ul003 mgt20">
        <li class="active" @click.prevent="checkTab = 1"><a href="###">预算接收</a></li>
        <li id="allocation" @click.prevent="checkTab = 2"><a href="###">预算分配</a></li>
        <li id="use" @click.prevent="checkTab = 3"><a href="###">预算使用明细查询</a></li>
      </ul>

      <div class="mgt30">
        <ul class="ul0004">
          <!--内容1-->
          <li class="db">
            <div class="ovh mgt20">
              <div class="fl mgr20">
                <select class="form-control ios0" v-model="map.budgetYear" @change="selectYear">
                  <option value="0">年度</option>
                  <option  :value="item" v-for="(item,index) in yearList">{{item}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0" v-model="map.subjectId" @change="selectSub">
                  <option value="0">科目</option>
                  <option  :value="item.subjectId" v-for="(item,index) in subjectList">{{item.subName}}</option>
                </select>
             </div>

            </div>

            <div class="mgt30">
              <table class="tab2">
                <tbody>
                <tr>
                  <td>年度</td>
                  <td>生效月份</td>
                  <td>预算类型</td>
                  <td>接收单位</td>
                  <td>所属科目</td>
                  <td>预算额</td>
                  <td>操作</td>
                </tr>
                <tr v-for="(item,index) in budgetReceiveList">
                  <td>{{item.budgetYear}}</td>
                  <td v-if="item.budgetCategory==1">{{item.effectiveMonth}}</td>
                  <td v-else>{{item.budgetMonth}}</td>
                  <td v-if="item.budgetCategory==1">年度预算</td>
                  <td v-else>月度预算</td>
                  <td>{{item.orgName}}</td>
                  <td v-if="item.budgetType==0">总额</td>
                  <td v-else>{{item.subName}}</td>
                  <td>{{item.budgetAmount}}</td>
                  <td>
                    <router-link class="btn btn-xs btn-primary" :to="{name:'deliveryPlanDetail',query:{'planId':item.planId,'type':item.type}}" v-if="item.planId">查看执行计划</router-link>
                    <span v-else>无</span>
                    <!--<router-link href="###" class="btn btn-sm btn-primary" :to="{name:'goodsDetail',params:{goodsId:item.goodsId}}">查看执行计划</router-link>-->
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--内容2-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl mgr20">
                <select class="form-control ios0" v-model="map.budgetYear" @change="selectYear">
                  <option value="0">年度</option>
                  <option  :value="item" v-for="(item,index) in yearList">{{item}}</option>
                </select>
              </div>
              <div class="fl mgr20">
                <select class="form-control ios0" v-model="map.orgId" @change="selectOrg1">
                  <option value="0">分配部门</option>
                  <option  :value="item.id" v-for="(item,index) in orgList1">{{item.orgName}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0" v-model="map.budgetCategory" @change="selectBudgetCategory">
                  <option value="0">预算类型</option>
                  <option  value="1"> 年度预算 </option>
                  <option  value="2"> 月度预算 </option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline" @keyup.13="search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="所属科目名称" v-model="map.searchKey">
                    <span class="input-group-btn">
									<button type="submit" class="btn bged145b" @click="search">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>
            <ul class="ulDiv ovh mgt30">
              <li class="fl mgr30">
                <template v-if="checkBottomOrg===true"><!--是最后一级组织-->
                  <a  class="db pdl29 corED145B ios8 lii">预算分配</a>
                  <div class="abs liDiv t-c bor-d6 z1000 bgw dn time05 lii lis">
                    <router-link class="db w100 h40 lh40 bor-b-d6 cor9 deng" :to="{name: 'addBudgetBySub',params:{myTab:2}}">年度预算分配</router-link>
                    <router-link class="db w100 h40 lh40 bor-b-d6 cor9 deng" :to="{name: 'addBudgetByOrgMonth',params:{myTab:2}}">月度预算分配</router-link>
                  </div>
                </template>
                <template v-else><!--非最后一级组织-->
                  <a  class="db pdl29 corED145B ios8 lii">预算分配</a>
                  <div class="abs liDiv t-c bor-d6 z1000 bgw dn time05 lii lis">
                    <router-link class="db w100 h40 lh40 bor-b-d6 cor9 deng" :to="{name: 'addBudgetByOrg',params:{myTab:2}}">年度预算分配</router-link>
                    <router-link class="db w100 h40 lh40 bor-b-d6 cor9 deng" :to="{name: 'addBudgetByOrgMonth',params:{myTab:2}}">月度预算分配</router-link>
                  </div>
                </template>
              </li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios4">导入</a></li>
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios5">模板下载</a></li>
              <!--<li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios7">批量入库</a></li>-->
            </ul>

            <div class="mgt40">
              <table class="tab2">
                <tbody>
                <tr>
                  <td>年度</td>
                  <td>生效月份</td>
                  <td>预算类型</td>
                  <td>分配部门</td>
                  <td>所属科目</td>
                  <td>预算额</td>
                  <td>操作</td>
                </tr>
                <tr v-for="(item,index) in budgetAllocationList">
                  <td>{{item.budgetYear}}</td>
                  <td v-if="item.budgetCategory==1">{{item.effectiveMonth}}</td>
                  <td v-else>{{item.budgetMonth}}</td>
                  <td v-if="item.budgetCategory==1">年度预算</td>
                  <td v-else>月度预算</td>
                  <td>{{item.orgName}}</td>
                  <td v-if="item.budgetType==0">总额</td>
                  <td v-else>{{item.subName}}</td>
                  <td>{{item.budgetAmount}}</td>
                  <td>
                    <router-link class="btn btn-xs btn-primary" :to="{name:'budgetExecute',query:{
                    'year':item.budgetYear,
                    'orgId':item.orgId,
                    'subjectId':item.subjectId,
                    'myTab':2
                    }}">执行进度设定</router-link>
                   <!--  &lt;!&ndash;<router-link href="###" class="btn btn-sm btn-primary" :to="{name:'goodsDetail',params:{goodsId:item.goodsId}}">预算额调整</router-link>&ndash;&gt; -->
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--内容3-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl mgr20">
                <select class="form-control ios0" v-model="map.budgetYear" @change="selectYear">
                  <option value="0">年度</option>
                  <option  :value="item" v-for="(item,index) in yearList">{{item}}</option>
                </select>
              </div>
              <div class="fl mgr20">
                <select class="form-control ios0" v-model="map.orgId" @change="selectOrg2">
                  <option value="0">使用部门</option>
                  <option  :value="item.id" v-for="(item,index) in orgList2">{{item.orgName}}</option>
                </select>
              </div>
              <div class="fl">
                <select class="form-control ios0" v-model="map.budgetCategory" @change="selectBudgetCategory">
                  <option value="0">预算类型</option>
                  <option  value="1"> 年度预算 </option>
                  <option  value="2"> 月度预算 </option>
                </select>
              </div>
              <div class="fr">
                <div class="form-inline" @keyup.13="search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="所属科目名称" v-model="map.searchKey">
                    <span class="input-group-btn">
									<button type="submit" class="btn bged145b" @click="search">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="mgt40">
              <table class="tab2">
                <tbody>
                <tr>
                  <td>年度</td>
                  <td>生效月份</td>
                  <td>预算类型</td>
                  <td>预算使用部门</td>
                  <td>所属科目</td>
                  <td>预算额</td>
                  <td>已使用预算</td>
                  <td>可用预算</td>
                  <td>操作</td>
                </tr>

                <tr v-for="(item,index) in budgetUseList">
                  <td>{{item.budgetYear}}</td>
                  <td>{{ item.budgetCategory==1? item.effectiveMonth:item.budgetMonth}}</td>
                  <td>{{ item.budgetCategory==1? '年度预算':'月度预算' }}</td>
                  <td>{{item.orgName}}</td>
                  <td>{{item.budgetType==0?'总额':item.subName}}</td>
                  <td>{{item.budgetAmount}}</td>
                  <td>{{item.actualUse?item.actualUse:'0'}}</td>
                  <td>{{
                        item.budgetAmount&&item.actualUse? item.budgetAmount-item.actualUse:item.budgetAmount
                  }}</td>

                  <td style="width: 180px;">
                    <router-link  class="btn btn-xs btn-primary" :to="{name:'budgetUseDetail',query:{budgetYear:item.budgetYear,subjectId:item.subjectId,orgId:item.orgId,myTab:3}}">预算使用明细</router-link>
                    <router-link v-show="item.planId!=null" class="btn btn-xs btn-success" :to="{name:'budgetUseSchedule',query:{planId:item.planId,amount:item.budgetAmount,
                    budgetYear:item.budgetYear,subjectId:item.subjectId,orgId:item.orgId,myTab:3}}">执行进度</router-link>
                  </td>

                </tr>
                </tbody>
              </table>
            </div>
          </li>

        </ul>
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
  import $ from 'jquery'
  var _this = '';
  export default {
    name: "budgetManage",
    data(){
      return{
        map:{
          pageNum:1,
          pageSize:10,
          budgetYear:0,
          currOrgId:1,
          subjectId:0,
          orgId:0,
          searchKey:null,
          type:0,
          budgetCategory:0
        },
        checkTab:1,//页签tab
        budgetReceiveList:[],//接收列表
        budgetAllocationList:[],//分配列表
        budgetUseList:[],//使用列表
        yearList:[],
        subjectList:[],
        orgList1:[],//预算分配实体下拉框
        orgList2:[],//预算使用实体下拉框
        checkBottomOrg:true,//默认是叶子组织为true，不是叶子组织为false
        isAdminBud:true,//true是顶级预算人员，false是子部门预算人员
        pageData:{
          curPage:1,//当前页码
          totalCount:1,//总条数

          pageSize:10//页面大小
        }
      }
    },
    beforeCreate(){
      _this = this;
    },
    created(){
      //获取用户信息
      this.configJS.getUserInfo(this);
      _this.isAdminBud=this.configJS.isAdminBud();
      _this.map.currOrgId=this.configJS.userInfo.orgId;
      initYearList();//初始化预算年份
      initSubjectList();//初始化所属科目
      initOrgList1();//初始化预算分配预算实体列表
      initOrgList2();//初始化预算使用明细预算实体列表
      checkBottomOrg();//是否是叶子节点,true:是。
      initBudgetReceiveList();//初始化预算接收列表

    },
    mounted(){
      var $ul003= $('.ul003>li');
      $ul003.click(function(){
        $(this).addClass('active').siblings().removeClass('active');
        var i= $ul003.index(this);
        $('.ul0004>li').eq(i).show().siblings('.ul0004>li').hide();
      });
      if (this.$route.params.myTab==2) {
        $("#allocation").click();
      };
      if (this.$route.params.myTab==3) {
        $("#use").click();
      }

      var lii= $('.lii');
      lii.mouseover(function(){
        $(".lis").show()
      });
      lii.mouseout(function(){
        $(".lis").hide()
      });
    },
    methods:{
      chekdpage: function(pageNum) {//分页
        _this.map.pageNum = pageNum;
        _this.pageData.curPage = pageNum;
        if(_this.checkTab==1){
           initBudgetReceiveList();
        }else if (_this.checkTab==2){
          initBudgetAllocationList();
        }else{
          initBudgetUseList();
        }



      },
      search:function(){//搜索条件查询
        if(_this.checkTab==2){
          initBudgetAllocationList();
        }else if (_this.checkTab==3){
          initBudgetUseList();
        }
      },
      selectYear:function () {//下拉框年份
        if(_this.checkTab==1){
          initSubjectList();
          initBudgetReceiveList();
        }else if (_this.checkTab==2){
          initBudgetAllocationList();
        }else {
            initBudgetUseList();
        }

      },
      selectSub:function () {//科目查询
        initBudgetReceiveList();
      },
      selectOrg1:function(){
        initBudgetAllocationList();
      },
      selectOrg2:function () {
        initBudgetUseList();
      },
      selectBudgetCategory:function () {
        if(_this.checkTab==2){
          initBudgetAllocationList();
        }else {
          initBudgetUseList();
        }
      }


    },
    watch:{
      checkTab(newVal,oldVal){

        if(newVal != oldVal) {
          _this.map.pageNum=1;
          _this.map.budgetYear=0;
          _this.map.subjectId=0;
          _this.map.orgId=0;
          _this.map.searchKey=null;
          _this.map.budgetCategory=0;
          if(_this.checkTab==1){//预算接收列表
            initBudgetReceiveList();
          }else if (_this.checkTab==2) {//预算分配列表
            initBudgetAllocationList();
          }else if (_this.checkTab==3){//预算使用列表
            initBudgetUseList();
          }

        }
      },

    }
  }

  function initBudgetReceiveList() {//初始化预算接收列表
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/budget/budgetReceiveList',
      params:  _this.map
    }).then((res) => {
      if(res.data.code==200){
        _this.budgetReceiveList=res.data.obj.rows;
        _this.pageData.totalCount=res.data.obj.total;//总条数
        _this.pageData.curPage= _this.map.pageNum;//当前页吗
        _this.pageData.pageSize= _this.map.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  function initBudgetAllocationList() {//初始化预算分配列表

    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/budget/budgetAllocationList',
      params:  _this.map
    }).then((res) => {
      if(res.data.code==200){
        _this.budgetAllocationList=res.data.obj.rows;
        _this.pageData.totalCount=res.data.obj.total;//总条数
        _this.pageData.curPage= _this.map.pageNum;//当前页吗
        _this.pageData.pageSize= _this.map.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  function initBudgetUseList() {//初始化预算使用列表
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/budget/budgetUseList',
      params:  _this.map
    }).then((res) => {
      if(res.data.code==200){

        _this.budgetUseList=res.data.obj.rows;
        _this.pageData.totalCount=res.data.obj.total;//总条数
        _this.pageData.curPage= _this.map.pageNum;//当前页吗
        _this.pageData.pageSize= _this.map.pageSize;//页面大小
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  function initYearList() {//初始化年度下拉框
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/budget/findYearList'
    }).then((res) => {
      if(res.data.code==200){
       _this.yearList= res.data.obj;
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  function initSubjectList() {//初始化下级科目
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/subject/getButtomSubs',
      params:{orgId:_this.map.currOrgId,year:_this.map.budgetYear}
    }).then((res) => {
      if(res.data.obj !=null){
        _this.subjectList = res.data.obj.map(item=>{
          return{
            "subName":item.subName,
            "subjectId":item.id
          }
        });
      }

    }).catch((err) => {
      console.log(err);
    })
  }






  function checkBottomOrg() {//判断当前组织有没有下级节点
    _this.$axios({
      method: 'get',
      url: '/biz/tob-user/system/orgInfo/checkBottomOrg',
      async:false,
      params:{id:_this.map.currOrgId}
    }).then((res) => {
      if(res.data.code==200){
        if(res.data.obj.isBottomOrg==false){
          _this.map.type=1;//非最后一级组织
        }else{
          _this.map.type=0;//最后一级组织
        }
          _this.checkBottomOrg=res.data.obj.isBottomOrg;
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  function initOrgList1() {//预算分配列表组织下拉框
    _this.$axios({
      method: 'get',
      url: '/biz/tob-user/system/orgInfo/getOrgSubList',
      params:{orgId:_this.map.currOrgId}
    }).then((res) => {
      if(res.data.code==200){
        _this.orgList1=res.data.obj.getOrgSubList;
      }
    }).catch((err) => {
      console.log(err);
    })
  }


  function initOrgList2() {//预算使用列表组织下拉框
    _this.$axios({
      method: 'get',
      url: '/biz/tob-user/system/orgInfo/findBottomOrgList',
      params:{orgId:_this.map.currOrgId}
    }).then((res) => {
      if(res.data.code==200){
        _this.orgList2=res.data.obj.bottomOrgList;
      }
    }).catch((err) => {
      console.log(err);
    })
  }





</script>

<style scoped>
.demo-table-expand{
  color: red;
}
.cell{
  color: red;
}
.test{
  color: red;
}
</style>
