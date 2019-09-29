<template>
  <div class="ww100 pdl15 pdr15 pdb130 pdt1">
    <div class="w1200 c-b mm100 mgt30">
      <div class="ovh pdb10 bor-b-d">
        <p class="fl fs18 cor0">年度预算分配</p>
        <a style="cursor:pointer;" class="db fr fs18 cor0" @click="returnn">返回>></a>
      </div>
      <div class="mgt20 bor-d pdt30 pdb50">
        <div class="w1000 c-b">
          <form action="#">

            <div class="ovh mgt20" >
              <div class="fl w80 fs16 cor0 h40 lh40">年度：</div>
              <div class="mgl80">
                <select class="db bor-d ww100 h40 lh40 pdl10 pdr10 cor6 ios0" v-model="map.budgetYear" @change="selectYear">
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
              <p class="fl mgr40" v-if="!isAdminBud"> {{map.budgetYear+'年'+orgName+'总预算:'+budgetAmount}} </p>
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
                    <td class=" h43 l430">￥<input type="text" style=" width: 69%;" v-model="item.budgetAmount" oninput = "value=value.replace(/[^\d.]/g,'')"></td>
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
  var orgIdArr = new Array();
  var _this = '';
    export default {
        name: "addBudgetByOrg",
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
          orgName:null,
          isAdminBud:true,
          budgetAmount:0,//该部门本年的预算额
          subList:[],//科目
          myTab:null,
          forbidden:false,//如果分配科目为叶子节点,则禁止输入
          checkAllocation:false,//ture代表已分配,false代表未分配
          orgIdList:orgIdArr//非叶子组织id集合

        }
      },
      created(){
        //获取用户信息
        this.configJS.getUserInfo(this);
        _this.isAdminBud=this.configJS.isAdminBud();
        _this.currOrgId=this.configJS.userInfo.orgId;
        _this.myTab=this.$route.params.myTab;
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
          beforeAdd();
        },
        returnn(){
          // this.$router.back(-1);
          this.$router.push({name:'bm_budgetManage',params:{myTab:_this.myTab}});
        },
        selectYear(){
          initBudgetAmount();
          initOrgList();
          checkAllocation2();
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
      var  year2=year1+1;
      _this.map.budgetYear=year2;
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
      url: '/biz/tob-budget/subject/getButtomSubs',
      params:{orgId:_this.currOrgId,year:_this.map.budgetYear,type:1}
    }).then((res) => {
      if(res.data.obj !=null){
        _this.subList = res.data.obj;
      }
      if(res.data.code==200){
        let paraList = _this.map.list;
        _this.map.list.map(e=>{
          let tep = res.data.obj.map(item=>{
            return{
              "subAmount":"",
              "subName":item.subName,
              "subjectId":item.id
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
          _this.forbidden=true;//非叶子组织
          orgIdArr.push(orgId);//用变量把非叶子组织存起来
        }
      }
    }).catch((err) => {
      console.log(err);
    })
  }

  function beforeAdd() {//提交前进行逻辑判断


    if(!_this.isAdminBud && _this.budgetAmount==0){
      _this.$alert("当前部门无可分配预算额!");
      return;
    }
    if(_this.checkAllocation){
      _this.$alert(_this.map.budgetYear+"年度已分配预算,请勿重复分配!");
      return;
    }
    let list1=_this.map.list;
    var temp0=0;
    for(var i=0;i<list1.length;i++){
      temp0+=Number(temp0)+Number(list1[i].budgetAmount);
      if(list1[i].budgetAmount==null)
      {
        list1[i].budgetAmount=0;

      }else
      {
        //判断其是否为非叶子节点（已存起来）a=-1 叶子节点；
        var a=orgIdArr.indexOf(list1[i].id);
        if(a==-1){//叶子节点
          var temp=0;
          for(var j=0;j<list1[i].subList.length;j++)
          {
            // if((list1[i].subList)[j].subAmount==null){
            //   (list1[i].subList)[j].subAmount=0;
            // }
            //temp+=(list1[i].subList)[j].subAmount;
            temp=Number(temp)+Number(list1[i].subList[j].subAmount);

          }
          if(temp !=0 && temp !=list1[i].budgetAmount){
            _this.$alert(list1[i].orgName+"预算额与所分配科目额不等,请重新分配!");
            return;
          }
        }
      }
    }
    //非顶级预算员:上级分配金额和分配给下家部门之和不相等
    if(!_this.isAdminBud &&temp0 !=_this.budgetAmount){
      _this.$alert("分配给下级部门的预算额和本年度预算额不等!请重新分配!");
      return;
    }

      _this.$confirm('确定提交吗？').then(_ => {
        add();
      }).catch(_ => {
      });


  }


  function add() {

    let params = {
        "pId":_this.currOrgId,
        "budgetYear":_this.map.budgetYear,
        "effectiveMonth":_this.map.effectiveMonth,
        "orgId":"",
        "list":_this.map.list.map(e=>{
          return {
            "orgId":e.id,
            "orgName":e.orgName,
            "totalAmount":e.budgetAmount,
            "budgetYear":_this.map.budgetYear,
            "effectiveMonth":_this.map.effectiveMonth,
            "pId":_this.currOrgId,
            "childSubs":e.subList.map(x=>{
              return {
                "subId":x.subjectId,
                "orgId":e.id,
                "subName":x.subName,
                "subAmount":x.subAmount,
                "effectiveMonth":_this.map.effectiveMonth,
                "budgetYear":_this.map.budgetYear,
                "pId":_this.currOrgId,
              }


            })
          }
        })
      }


    _this.$axios({
      method: 'post',
      url: '/biz/tob-budget/biz/budget/addBudget',
      headers:{
        'Content-Type': 'application/json;charset=utf-8'  //这里加上头部信息
      },
      data:JSON.stringify(params)
    }).then((res) => {
      if(res.data.code==200){
        _this.$alert("添加成功");
        _this.$router.push({name: 'bm_budgetManage',params:{myTab:_this.myTab}});

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
        let money=res.data.obj.budgetAmount;
        if(money==null){
          money=0;
        }
          _this.budgetAmount=money;
      }
    }).catch((err) => {
      console.log(err);
    })
  }


  function checkAllocation2() {//检查本部门是否给下级部门分配预算
    _this.$axios({
      method: 'get',
      url: '/biz/tob-budget/biz/budget/checkAllocation2',
      params:{orgId:_this.currOrgId,year:_this.map.budgetYear}
    }).then((res) => {
      if(res.data.code==200 && res.data.obj !=0){
        _this.checkAllocation=true;
        _this.$alert(_this.map.budgetYear+"年度已分配预算,请勿重复分配!");
        return;
      }
    }).catch((err) => {
      console.log(err);
    })
  }
</script>

<style scoped>

</style>
