<template>
 <div>

  <!--头部-->
  <div class="ww100 header">
    <!--<div class="bgf2 pdl15 pdr15">-->
      <!--<div class="w1200 c-b mm100 cor9 ovh">-->
        <!--<div class="fl h40 lh40">欢迎进入xx商城后台管理系统！      客服热线：400-888-888</div>-->
        <!--<div class="fr ovh">-->
          <!--<a style="cursor:pointer;" class="db h40 lh40 cor9 mgl40 fl pdl25 ios13">我的收藏</a>-->
          <!--<router-link style="cursor:pointer;" class="db h40 lh40 cor9 mgl40 fl" :to="{name:'myOrders'}">我的订单</router-link>-->
          <!--<router-link style="cursor:pointer;" class="db h40 lh40 cor9 mgl40 fl pdl25 ios14" :to="{name:'myOrders'}">个人中心</router-link>-->
          <!--<a style="cursor:pointer;" class="db h40 lh40 cor9 mgl40 fl" @click.prevent = "logout">退出登陆</a>-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->


    <div class="bgf2 pdl15 pdr60">
      <div class="w1200 c-b mm100 cor9 h40">
        <div class="fl h40 lh40">欢迎进入xx商城后台管理系统！      客服热线：400-888-888</div>
        <ul class="fr h40 h-ul">
          <li class="fl mgl40"><a href="###" class="db h40 lh40 cor9 pdl25 ios13">我的收藏</a></li>
          <li class="fl mgl40"><router-link style="cursor:pointer;" class="db h40 lh40 cor9 mgl40 fl" :to="{name:'myOrders'}">我的订单</router-link></li>
          <li class="fl mgl40"><router-link style="cursor:pointer;" class="db h40 lh40 cor9 mgl40 fl pdl25 ios14" :to="{name:'myOrders'}">个人中心</router-link></li>
          <li class="fl mgl40 rel">
            <a href="###" class="db h40 lh40 cor9">账号管理</a>
            <div class="abs liDiv t-c bor-d6 z1000 bgw dn time05">
              <a href="###" class="db w150 h40 lh40 bor-b-d6 cor9 xiu" @click.prevent="showTab">修改密码</a>
              <a href="###" class="db w150 h40 lh40 bor-b-d6 cor9 deng" @click.prevent = "logout">退出登录</a>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <!-- 动态页面 -->
    <div class="pdl15 pdr15 h201 bor-b-ED145B">
      <div class="w1200 c-b mm100 ovh pdt40 pdb30">
        <div class="fl w187">
          <a href="###" class="db"><img src="/static/images/21.png" alt=""></a>
        </div>
        <div class="fr w187 pdt18 pdb18 pdl30 rel">
          <router-link :to="{name:'cartManage'}" class="db rel w157 borb hov7 t-c">
            <p class="dinb fs12 h40 lh40 corED145B rel">
              <img class="mgr5" src="/static/images/29.png" alt="">去购物车结算
              <span class="abs db z10 span2">{{cardCount}}</span>
            </p>
          </router-link>
          <!-- <p class="abs z10 pp1 cor3">本月可用预算：¥{{budgetMonth | numFilter}}</p> -->
        </div>
        <div class="h-m pdt18 pdb18 rel">
          <form action="#">
            <div class="w80 fr">
              <input type="text" @click="search" class="ww100 h40 lh40 bgED145B bort corw t-c hov7 cupo" value="搜索">
            </div>
            <div class="mgr80">
              <input type="text" v-model="serachKey" @keyup.enter="search" class="ww100 h40 lh40 bor-2 cor9 pdl15" placeholder="请输入产品名称">
            </div>
          </form>
          <!--
          <div class="div3 abs z10 ovh">
            <a href="###" class="db fl cor9 mgr20 hov7">笔记本电脑</a>
            <a href="###" class="db fl cor9 mgr20 hov7">复印纸</a>
            <a href="###" class="db fl cor9 mgr20 hov7">办公玩具</a>
            <a href="###" class="db fl cor9 mgr20 hov7">投影仪</a>
            <a href="###" class="db fl cor9 mgr20 hov7">办公椅</a>
            <a href="###" class="db fl cor9 mgr20 hov7">米</a>
            <a href="###" class="db fl cor9 mgr20 hov7">食用油</a>
          </div>
          -->
        </div>
      </div>
      <div class="w1200 c-b mm100 rel">
        <ul class="ul8 ovh">
          <li class="fl" :class="activeTabInd == -1 ? 'active':''" @click.prevent="checkTab(-1)"  @mouseenter="lvesShow = true" @mouseleave="lvesShow = false"><a href="###" class="db h50 lh50">首页</a></li>
          <li class="fl" :class="activeTabInd == index ? 'active':''" @click.prevent="checkTab(index)" v-for="(item,index) in levList" :key="index"><a href="###" class="db h50 lh50">{{item.cname}}</a></li>
        </ul>
        <!-- subDiv -->
        <div class=" abs ww100">
          <!--  v-show="lvesShow"  dn-->
          <div class="bgw pdt10 pdb40 pdl90 pdr90" v-show="lvesShow" @mouseenter="lvesShow = true" @mouseleave="lvesShow = false">

            <div class="ovh mgt20" v-for="(lve0,lve0Ind) in levs" :key="lve0Ind">
              <p class="fl w145 cor707070 fb pdl40" :class="'ios20-'+(lve0Ind+1)">{{lve0.cname}} > </p>
              <ul class="ovh mgl145">
                <li class="fl mgr15 hov7" v-for="(lve1,lve1Ind) in lve0.children">
                  <a href="javascript:;" class="db cor707070" @click="secondLevelClick(lve1)">{{lve1.cname}}</a>
                </li>
              </ul>
            </div>

  <!--
            <div class="ovh mgt20">
              <p class="fl w145 cor707070 fb pdl40 ios20-1">整机设备></p>
              <ul class="ovh mgl145">
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">笔记本电脑</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">台式机 </a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">打印机</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">MAC</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">投影仪</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">显示器</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">工作站</a></li>
              </ul>
            </div>
            <div class="ovh mgt20">
              <p class="fl w145 cor707070 fb pdl40 ios20-2">办公耗材></p>
              <ul class="ovh mgl145">
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">复印纸</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">办公文具 </a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">笔类 </a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">文件管理</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">投影仪</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">打印耗材</a></li>
              </ul>
            </div>
            <div class="ovh mgt20">
              <p class="fl w145 cor707070 fb pdl40 ios20-3">办公电器></p>
              <ul class="ovh mgl145">
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">会议电视</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">电话机 </a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">饮水机</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">咖啡机</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">养生壶</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">微波炉</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">加湿器</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">空气净化器</a></li>
              </ul>
            </div>
            <div class="ovh mgt20">
              <p class="fl w145 cor707070 fb pdl40 ios20-4">办公家具></p>
              <ul class="ovh mgl145">
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">班台办公桌</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">文件柜 </a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">办公椅</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">屏风工位</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">货架展示架</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">会议桌</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">办公沙发</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">商会办公定制</a></li>
              </ul>
            </div>
            <div class="ovh mgt20">
              <p class="fl w145 cor707070 fb pdl40 ios20-5">配件周边></p>
              <ul class="ovh mgl145">
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">硬盘</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">主板 </a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">cpu</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">显卡</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">内存仪</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">鼠标</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">u盘</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">电脑包</a></li>
              </ul>
            </div>
            <div class="ovh mgt20">
              <p class="fl w145 cor707070 fb pdl40 ios20-6">配件周边></p>
              <ul class="ovh mgl145">
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">墙贴装饰贴脑</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">装饰字画 </a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">灯饰照明</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">厨房卫浴</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">基建材料</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">全屋定制</a></li>
                <li class="fl mgr15 hov7"><a href="###" class="db cor707070">盆栽绿植</a></li>
              </ul>
            </div>
-->
          </div>

        </div>
      </div>
    </div>
  </div>
  <!--头部END-->

   <router-view name="mume"></router-view>
  <!-- 主体内容 -->
  <router-view :levList="levList" :activeTabInd="activeTabInd" @changCartCount="changCart" :serachKey="serachKey" ref="child" :list="list" :page="page"></router-view>
  <!-- 主体内容end -->

  <!--侧边栏-->
  <div class="aside fixed z1000 w50 pdt240 top0">
    <!-- <a href="#" class="db hov7 aside1">
      <img class="c-b" src="/static/images/49-1.png" alt="">
    </a> -->
    <router-link :to="{name:'cartManage'}" class="db mgt50 hov7 aside2">
      <img class="c-b" src="/static/images/49-2.png" alt="">
      <p class="mgt8 w20 t-c c-b corw">购物车</p>
      <p class="pp2 w28 h28 c-b ras14 mgt10 bgED145B cor444851 t-c lh28">{{cardCount}}</p>
    </router-link>
    <a href="javascript:;" class="db mgt45 hov7 aside3">
      <img class="c-b" src="/static/images/49-3.png" alt="">
      <p class="mgt8 w20 t-c c-b corw">预算</p>
    </a>
   <!--  <a href="#" class="db mgt45 mgt50 hov7 aside4">
      <img class="c-b" src="/static/images/49-4.png" alt="">
    </a>
    <a href="#" class="db mgt30 hov7 aside5">
      <img class="c-b" src="/static/images/49-5.png" alt="">
    </a>
    <a href="#" class="db mgt290 hov7 aside6">
      <img class="c-b" src="/static/images/49-6.png" alt="">
    </a> -->
  </div>
  <!--侧边栏END-->

  <!--预算弹窗-->
<div class="yschuangIndex  dn">
  <div class="ovh" style="margin-bottom: 15px;">
    <div class="fl mgr30" style="line-height: 23px;">
      <p class="fl cor0 ios29 pdl33">预算执行情况总览</p>
    </div>
    <div class="fl">
      <a href="javascript:;" @click="hideBudgetWind" class="btn btn-xs bg-danger bgED145B corw w136">预算使用明细</a>
    </div>
    <a href="javascript:;" class="db fr cor0 hiddd">隐藏</a>
  </div>
  <div class="mgt5 table-responsive">
    <table class="tab80">
      <tbody>
        <tr>
          <td >科目</td>
          <td >本年度预算总额</td>
          <td >已用预算</td>
          <td >本月/季度可用预算</td>
          <td >本年度可用预算</td>
          <td >计划进度</td>
          <td >实际进度</td>
          <td >状态</td>
        </tr>
        <tr v-for="(item,index) in  budgetList" :key="index">
          <td>{{item.subName?item.subName:'--'}}</td>
          <td>{{item.yearAmount?$options.filters.numFilter(item.yearAmount):'0.00'}}</td>
          <td>{{item.actualUse?$options.filters.numFilter(item.actualUse):'0.00'}}</td>
          <td>{{item.leftMonthOrSeasonAmount?$options.filters.numFilter(item.leftMonthOrSeasonAmount):'0.00'}}</td>
          <td>{{item.leftYearAmount?$options.filters.numFilter(item.leftYearAmount):'0.00'}}</td>
          <td>{{item.yearRate?$options.filters.numFilter(item.yearRate):'0.00'}}%</td>
          <td>{{item.actualMonthOrSeasonRate?$options.filters.numFilter(item.actualMonthOrSeasonRate):'0.00'}}%</td>
          <td>
            {{
              item.actualMonthOrSeasonRate > item.planMonthOrSeasonRate ?
              // "已超支："+(item.actualMonthOrSeasonRate - item.planMonthOrSeasonRate)+"%":
              "已超支："+ $options.filters.numFilter(item.actualMonthOrSeasonRate - item.planMonthOrSeasonRate)+"%":
              item.actualMonthOrSeasonRate < item.planMonthOrSeasonRate ?"正常":"其他"
            }}
          </td>
        </tr>
        <tr v-if="budgetList.length == 0 ">
          <td colspan="9">无数据</td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
<!--预算弹窗END-->


</div>
</template>

<script>

  import configJS from '@/common/config'

  export default {
    name: "product",
    data(){
      return {
        levList:[],//级别
        activeTabInd:-1,//激活下标
        serachKey:'',//搜索关键字
        list:[],//商品列表
        levs:[],//级别级联菜单
        lvesShow:false,//级别级联菜单是否显示
        category:{},
        page:{
          pageNum:1, //页码
          limit:16, //页面大小
          totalCount:0
        },
        cardCount:0,//我的购物车数量
        budgetList:0,//预算列表
      }
    },
    mounted(){
        $('.aside3').click(function(){
          const stat = $('.yschuangIndex').css("display");
          if(stat == "none"){
            $('.yschuangIndex').show();
          }else{
            $('.yschuangIndex').hide();
          }

        });
        $('.hiddd').click(function(){
          $('.yschuangIndex').hide();
        })
    },
    components: {
    },
    methods:{
      showTab(){
        $('#password').slideDown();
      },

      closeTab(){
        $('#password').slideUp();
      },

      //隐藏预算框
      hideBudgetWind(){
        this.$router.push({'name':'budgetQuery'})
        $('.yschuangIndex').hide();
      },
      //切换tab
      checkTab(curIndex){
        this.activeTabInd = curIndex;
        if(curIndex < 0){//'首页'被点击了
          this.category = {};
          this.$router.push({name:'product_index'})
          return;
        }

        this.category = this.levList[this.activeTabInd]

        //在主页中点击其他tab
        if(this.$route.path.indexOf("/product/index") > 0){
          //切换主页中子tab
          //将主页中的排行分类
          const child = this.$refs.child;
          if(child.checkPurchaseInd)
            child.checkPurchaseInd(this.activeTabInd);
          if(child.checkDateInd)
            child.checkDateInd(this.activeTabInd);
        }else{
          //在其他页面中点击其他tab

          //搜索商品
          this.initSearch()

          //跳到列表页
          if(this.$route.path.indexOf("/product/list") == -1){
            this.$router.push({name:'product_list'})
          }
        }
      },
      //商品搜索
      search(){
        const newVal = this.serachKey
        if(newVal && newVal.trim().length > 0){
          this.$router.push({
            name:'product_list'
          })
        }
        this.activeTabInd = -1;//搜索时将tab置空到首页
        this.category = {};
        this.page.pageNum = 1;
        initData.call(this)

      },
      initSearch(pageNum = 1,childSearchKey = ''){
        this.page.pageNum = pageNum;
        initData.call(this,childSearchKey);
      },
      //菜单中的二级菜单被点击
      secondLevelClick(category){
        this.activeTabInd = -1;//将tab置空
        this.category = category;
        this.$router.push({name:'product_list'})
        this.page.pageNum = 1;
        initData.call(this);
      },
      //添加购物车
      addCart(item,count = 1){
        const params = {
          goodsId:item.goodsId,
          skuId:item.skuDOList[0].skuId,
          count:count,
          price:item.skuDOList[0].price,
          userId:configJS.userInfo.userId,
          supId:item.supId
        }
        addCart.call(this,params)
      },

      changCart(){
        myCartCount.call(this);
      }  ,

      logout(){
        this.$axios({
          url:"/biz/logout"
        }).then((res) =>{
          console.log(res);
          if(res.data.code == 200){
            //登出成功，清空内存
            this.configJS.userInfo="",
             this.configJS.authorization =""
            //登出成功，删除本地用户信息和authorization
            localStorage.removeItem("userInfo");
            localStorage.removeItem("authorization");
            //跳转到登录页
            this.$router.push({name:"login"})
          }
        });
      }

    },
    created(){
      //商品类别
      categorys.call(this)

      //按钮中的商品类别
      categorysList.call(this)

      //购物车数量
      myCartCount.call(this);

      //本月可用预算
      EnabledBudgetMonth.call(this)
    },

}
  //我的购物车数量
  function myCartCount(){
    const userId = configJS.userInfo.userId;
    // this.$axios.get("/biz/tob-mall/pruduct/cart/account/"+userId)
    this.$axios.get("/biz/tob-shopping-cart/biz/card/account/"+userId)
    .then(res=>{
      if(res.data.success){
        this.cardCount = res.data.obj;
      }
    })
  }

  //添加购物车
  function addCart(params){
    this.$axios({
        // url:"/biz/tob-mall/pruduct/card/add",
        url:"/biz/tob-shopping-cart/biz/card",
        method:"post",
        headers:{
          "Content-Type":"application/json"
        },
        data:JSON.stringify(params)
    }).then(res=>{
      if(res.data.success){
        this.$message({
          showClose: true,
          message: '添加成功',
          type: 'success'
        });
        //购物车添加成功需要重新计算我的购物车个数
        myCartCount.call(this)
      }else{
        this.$message({
          showClose: true,
          message: '添加失败',
          type: 'error'
        });
      }
    })
  }
  //商品类别
  function categorys() {
   this.$axios({
    url:'/biz/tob-mall/pruduct/categorys',
      params:{level:0}//顶级级别
    }).then(res=>{
      if(res.data.success){
        this.levList = res.data.obj;

      }
    })
  }
 //商品查询
 function initData(childSearchKey = '') {
  const params = {
      pageNum:this.page.pageNum, //页码
      limit:this.page.limit //页面大小
    }
    if(childSearchKey && childSearchKey.trim().length > 0){
      params.keyWord = childSearchKey.trim();
    }else{
      if(this.serachKey && this.serachKey.trim().length > 0){
        params.keyWord = this.serachKey.trim();//商品名称搜索
      }
    }

    if(this.category && this.category.cid >= 0){
      params.cid = this.category.cid;
    }

    this.$axios({
      url:'/biz/tob-mall/pruduct',
      params:params
    }).then(res=>{
      if(res.data.success){
        this.list = res.data.obj.list;
        this.page.totalCount = res.data.obj.total;
      }

    })
  }

  //按钮中的商品分类列表
  function categorysList(){
     this.$axios({
      url:'/biz/tob-mall/pruduct/categorys',
      method:'patch',
      params:{"pid":0}
    }).then(res=>{
      if(res.data.success){
        this.levs = res.data.obj;
      }
    })
  }
  //本月可用预算
  function EnabledBudgetMonth(){
    const orgId = this.configJS.userInfo.orgId;
    // this.$axios.get("/biz/tob-mall/pruduct/budget/budgetPublic/"+orgId)
    this.$axios({
        url:"/biz/tob-budget/biz/budget/budgetPublic",
        params:{"orgId":orgId}
    }).then(res=>{
      if(res.data.success){
        try{
          this.budgetList = res.data.obj;
        }catch(err){
          console.log(err)
        }
      }
    })

  }
</script>

<style scoped>
.cupo{
  cursor: pointer;
}
.el-scrollbar__wrap {
  overflow-x: hidden;
}
.yschuangIndex {
    width: 1200px;
    position: fixed;
    left: 50%;
    margin-left: -600px;
    bottom: 0;
    z-index: 1000001;
    background-color: #fff;
    padding: 20px;
    -webkit-box-shadow: 0 0 8px #888888;
    box-shadow: 0 0 8px #888888;
}
</style>
