<template>
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">月度预算分配</p>
        <a style="cursor:pointer;" class="db fr fs18 cor0" @click="returnn">返回>></a>
      </div>
      <div class="mgt20 bor-d pdt30 pdb50">
        <div class="w1000 c-b">
          <form action="#">

            <div class="ovh mgt20" >
              <div class="fl w80 fs16 cor0 h40 lh40">年度：</div>
              <div class="mgl80">
                <select class="db bor-d ww100 h40 lh40 pdl10 pdr10 cor6 ios0" v-model="map.budgetYear">
                  <option  :value="item" v-for="(item,index) in yearList">{{item}}</option>
                </select>
              </div>
            </div>
            <div class="ovh mgt20">
              <div class="fl w80 fs16 cor0 h40 lh40">生效月份：</div>
              <div class="mgl80">
                <select class="db bor-d ww100 h40 lh40 pdl10 pdr10 cor6 ios0" v-model="map.effectiveMonth">
                  <option  :value="item" v-for="(item,index) in monthList">{{item}}</option>
                </select>
              </div>
            </div>
            <!--<div class="ovh mgt20">
              <div class="fl w80 fs16 cor0 h40 lh40">预算科目：</div>
              <div class="mgl80">
                <select class="db bor-d ww100 h40 lh40 pdl10 pdr10 cor6 ios0">
                  <option value="1">XXX总公司</option>
                  <option value="1">SSS公司</option>
                </select>
              </div>
            </div>-->
            <div class="ovh mgt20 fs16 corED145B fb">
              <p class="fl mgr40" v-if="isAdminBud==false"> {{map.budgetYear+'年'+orgName+'总预算:'+budgetAmount}} </p>
            </div>

            <div class="ovh mgt20">
              <div class="mgl80">
                <table class="tab3" style="width: 919px;">
                  <tbody>
                  <tr>
                    <td class=" h43 l43" :style ="dicClass">序号</td>
                    <td class=" h43 l43" :style ="dicClass">分配部门</td>
                    <td class=" h43 l43" :style ="dicClass"><span :style="styleObject">* </span>预算额</td>
                    <td class=" h43 l43" :style ="dicClass" v-for="item in  subList" :key="item.subjectId">{{item.subName}}</td>
                  </tr>
                  <tr v-for="(item,index) in map.list" :key="index">
                    <td class=" h43 l43">{{index}}</td>
                    <td class=" h43 l43">{{item.orgName}}</td>
                    <td class=" h43 l430">￥<input type="text" style=" width: 69%;" v-model="item.budgetAmount"></td>
                    <td v-for="e in  item.subList" :key="e.subjectId">
                      ￥<input type="text" style=" width: 69%;" v-model="e.subAmount" :disabled="forbidden">
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="mgt40 t-c">
              <input type="button" class="w100 btn btn-primary" value="保存" @click="save">
              <input type="button" class="w100 pdr30 btn btn-danger" value="取消" @click="returnn">
            </div>
          </form>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
  var _this = '';
    export default {
        name: "addBudgetByOrgMonth",
      data()
      {
        return{
          map:{
            budgetYear:0,
            effectiveMonth:1,
            type:1,
            list:null
          },
          currOrgId:1,
          yearList:[],
          monthList:[1,2,3,4,5,6,7,8,9,10,11,12],
          orgName:'',
          isAdminBud:true,
          budgetAmount:0,//该部门本年的预算额
          subList:[],//科目
          myTab:null,
          forbidden:false//如果分配科目为叶子节点,则禁止输入

        }
      },
      created(){
        //获取用户信息
        this.configJS.getUserInfo(this);
        _this.isAdminBud=this.configJS.isAdminBud();
        _this.currOrgId=this.configJS.userInfo.orgId;
        _this.myTab=this.$route.params.myTab
        initOrgName();
        initYearList();//初始化年份下拉框
        initOrgList();//初始化下拉组织下拉框
        // initSubList();//初始化科目下拉框
        initBudgetAmount();//初始化本级组织预算
      },
      beforeCreate(){
        _this = this;
      },
      methods:{
        save:function () {
          add();
        },
        returnn(){
          this.$router.push({name:'bm_budgetManage',params:{myTab:_this.myTab}});
        }
      },
      computed:{
        dicClass(){
          return {
            "width":(1/(this.subList.length+4) * 100)+'%'
          }
        },
        styleObject(){
          return {
            fontWeight:'800',
            color: 'red',
            fontSize:'150%'
          }
        }
      }
    }

    function initYearList() {
      var year1=new  Date().getFullYear();
      _this.map.budgetYear=year1;
      var  year2=year1+1;
      var arr=new Array();
      arr.push(year1);
      arr.push(year2);
      _this.yearList=arr;
    }
    function initOrgList() {
      _this.$axios({
        method: 'get',
        url: '/biz/tob-user/system/orgInfo/getOrgSubList',
        params:{orgId:_this.currOrgId}
      }).then((res) => {
        console.log(111)
        console.log(res)
        if(res.data.code==200){
          _this.map.list= res.data.obj.getOrgSubList;
        }
      }).catch((err) => {
        console.log(err);
      }).finally(e=>initSubList())
    }

    function initSubList() {//初始化下级科目
      _this.$axios({
        method: 'get',
        url: '/biz/tob-budget/biz/budget/findSubjectList',
        params:{currOrgId:_this.currOrgId}
      }).then((res) => {
        if(res.data.code==200){
          let paraList = _this.map.list;
          _this.subList = res.data.obj;

          _this.map.list.map(e=>{
            let tep = res.data.obj.map(item=>{
              return{
                "subAmount":"",
                "subName":item.subName,
                "subjectId":item.subjectId
              }
            })
            e.subList = tep;
            return e;
          });
          _this.map.list = paraList;
          for (var i=0;i<paraList.length;i++){
            let orgId=paraList[i].id;//获取遍历的下级组织，并判断其是否为叶子组织
            checkBottomOrg(orgId);
          }
        }
      }).catch((err) => {
        console.log(err);
      })
    }

    function checkBottomOrg(orgId) {//判断当前组织有没有下级节点
      _this.$axios({
        method: 'get',
        url: '/biz/tob-user/system/orgInfo/checkBottomOrg',
        async:false,
        params:{id:orgId}
      }).then((res) => {
        if(res.data.code==200){
          if(res.data.obj.isBottomOrg==false){
            _this.forbidden=true;//非最后一级组织
          }
        }
      }).catch((err) => {
        console.log(err);
      })
    }



    function add() {
      // let params = {
      //   "pId":_this.currOrgId,
      //   "budgetYear":_this.map.budgetYear,
      //   "effectiveMonth":_this.map.effectiveMonth,
      //   "orgId":"",
      //   "list":_this.map.list.map(e=>{
      //     return {
      //       "orgId":e.id,
      //       "orgName":e.orgName,
      //       "totalAmount":e.budgetAmount,
      //       "budgetYear":_this.map.budgetYear,
      //       "effectiveMonth":_this.map.effectiveMonth,
      //       "pId":_this.currOrgId,
      //       "childSubs":e.subList.map(x=>{
      //         return {
      //           "subId":x.subjectId,
      //           "orgId":e.id,
      //           "subName":x.subName,
      //           "subAmount":x.subAmount,
      //           "effectiveMonth":_this.map.effectiveMonth,
      //           "budgetYear":_this.map.budgetYear,
      //           "pId":_this.currOrgId,
      //         }
      //       })
      //     }
      //   })
      // }
      let mony=0
      _this.map.list.map(e=>{
        if (e.budgetAmount!=null && e.budgetAmount!="") {
          mony+=e.budgetAmount
        }
        let xmony=0
        e.subList.map(x=>{
          if (x.subAmount!=null && x.subAmount!=""){
            xmony+=x.subAmount
          }
        })
        if (xmony>e.budgetAmount) {
          _this.$alert("科目分配的金额之和不能大于机构的预算额")
          return
        }
      })
      if (_this.budgetAmount>0) {
        if (mony>_this.budgetAmount) {
          _this.$alert("各机构预算额之和不能大于总预算")
          return
        }
      }
      let params = _this.map.list.map(e=>{
        return {
          "pId":_this.currOrgId,
          "budgetYear":_this.map.budgetYear,
          "budgetMonth":_this.map.effectiveMonth,
          "orgId":e.id,
          "budgetAmount":e.budgetAmount,
          "budgetMonthSubList":e.subList.map(x=>{
            return {
              "subjectId":x.subjectId,
              "budgetAmount":x.subAmount
            }
          })
        }
      })

      console.log(JSON.stringify(params))
      _this.$axios({
        method: 'post',
        url: '/biz/tob-budget/biz/budget/addBudgetMonth',
        //url: 'localhost:9002/biz/budget/addBudget',
        headers:{
          'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
        },
        data:JSON.stringify(params)
      }).then((res) => {
        if(res.data.code==200){
          _this.$router.push({name: 'bm_budgetManage'});
          _this.$alert("添加成功");
        }else{
          _this.$alert("添加失败");
        }
      }).catch((err) => {
        console.log(err);
      })
    }

    function initOrgName() {
      _this.$axios({
        method: 'get',
        url: '/biz/tob-user/system/orgInfo/getOrgDetail',
        params:{id:_this.currOrgId}
      }).then((res) => {
        if(res.data.code==200){
          _this.orgName=res.data.obj.orgInfo.orgName;
        }
      }).catch((err) => {
        console.log(err);
      })
    }

    function initBudgetAmount() {
      _this.$axios({
        method: 'get',
        url: '/biz/tob-budget/biz/budget/findAllocation2',
        params:{orgId:_this.currOrgId,year:_this.map.budgetYear}
      }).then((res) => {
        if(res.data.code==200 && res.data.obj !=null){
          _this.budgetAmount=res.data.obj.budgetAmount;
        }
      }).catch((err) => {
        console.log(err);
      })
    }
</script>

<style scoped>

</style>
