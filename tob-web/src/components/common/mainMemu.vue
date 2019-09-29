<template>
  <!--侧边栏-->
  <!--<router-link class="w200 fl bor6b4a22 t-c" tag="div" :to="{name:mumeTitile.link}">-->
    <!--&lt;!&ndash;<div class="w200 fl bor6b4a22 t-c">&ndash;&gt;-->
    <!--<div id="caidan" class="tit pdt30 pdb40 bg6b4a22">-->
      <!--<img :src="mumeTitile.imgUrl" alt="" class="db c-b">-->
      <!--<p class="fs30 corw fsd mgt14">{{mumeTitile.resourceName}}</p>-->
      <!--<p class="fs16 corw ">管理平台</p>-->
    <!--</div>-->

    <!--<div class="ulnav mgb30">-->
      <!--<li v-for="(item,index) in memuList" :key="index" v-if="item.level && item.level==2 && item.seq>1"-->
          <!--:class="{active:isActiveMemu(index)}" @click="changeMemuIndex(index)">-->
        <!--&lt;!&ndash;<a router-link :to="{'name':item.link}" class="cor3c3c3c h52 lh52" @click.prevent="activeIndex=index">{{item.resourceName}}<span&ndash;&gt;-->
        <!--<router-link :to="{name:item.link}" class="cor3c3c3c h52 lh52" @click.prevent="activeIndex=index">-->
          <!--{{item.resourceName}}-->
          <!--<span class="dn">→</span>-->
        <!--</router-link>-->
      <!--</li>-->
    <!--</div>-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
  <!--</router-link>-->
  <!--侧边栏END-->
  <div class="fl w170 pdt30">
    <div class="bgED145B pdt60 pdb50">
      <a href="###" class="db t-c">
        <p class="fs36 corw" style="font-family: '方正大标宋简体';">采购<span>商城</span></p>
        <p class="fs24 corw">后台管理</p>
      </a>
    </div>
    <!--供应商菜单-->
    <ul class="ul002 bgf2 pdb30">
      <!--<li class="active"><router-link style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame" :to="{name:'platformGoods'}">组织权限管理</router-link></li>-->
      <li v-for="(e,i) in memuList"><router-link style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame" :to="{'name':e.likName}">{{e.name}}</router-link></li>
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">商品上架管理</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">订单管理</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">供应商管理</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">商品管理</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">预算管理</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">科目管理</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">执行计划</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">订单审批</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">结算管理</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">订单审核</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">首页</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">购物车</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">个人中心</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">基本信息</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">商品管理</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">送货管理</a></li>-->
      <!--<li><a style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame">安装管理</a></li>-->
    </ul>
    <!--<div v-for="(e,i) in returnn(userInfo.roleId)"><router-link style="cursor:pointer;" class="db h60 lh60 t-c fs18 corED145B" target="menuFrame"  :to="{'name':e.likName}">{{e.name}}</router-link></div>-->
  </div>
</template>

<script>
  import configJS from '@/common/config'
  const datas ={
    1:[
      {
        "name":"组织权限管理",
        "likName":"platformAdmin"
      }
    ],
    2:[
      {
        "name":"商品基本信息管理",
        "likName":"platformGoods"
      },
      {
        "name":"商品上架管理",
        "likName":"platformPutaway"
      },
      {
        "name":"订单管理",
        "likName":"orderManage"
      },
    ],
    3:[
      {
        "name":"组织权限管理",
        "likName":"platformAdmin"
      }
    ],
    4:[
      {
        "name":"供应商管理",
        "likName":"supplierList"
      },
      {
        "name":"商品管理",
        "likName":"buyerGoods"
      },
    ],
    5:[
      {
        "name":"组织权限管理",
        "likName":"platformAdmin"
      }
    ],
    6:[
      {
        "name":"预算管理",
        "likName":"bm_budgetManage"
      },
      {
        "name":"科目管理",
        "likName":"subList"
      },
      {
        "name":"执行计划",
        "likName":"deliveryPlan"
      },
      // {
      //   "name":"订单审批",
      //   "likName":"bm_orderApproval"
      // },
      // {
      //   "name":"结算管理",
      //   "likName":""
      // },
    ],
    7:[
      {
        "name":"订单审核",
        "likName":"orderAudit"
      },
    ],
    8:[
      {
        "name":"我的订单",
        "likName":"myOrders"
      },
      {
        "name":"预算查询",
        "likName":"budgetQuery"
      },
    ],
    9:[
      {
        "name":"基本信息",
        "likName":"basic"
      },
      {
        "name":"商品管理",
        "likName":"supplierGoodsList"
      },
      {
        "name":"订单管理",
        "likName":"supplierOrderList"
      },
      {
        "name":"送货管理",
        "likName":"deliveryManage"
      },
    ],
    10:[
      {
        "name":"订单审批",
        "likName":"pal_orderApproval"
      },
    ],
  }

  export default {
    name: "mainMemu",
    data() {
      return {
        memuList: [],
        // activeIndex: 0,//激活的菜单下标
        userInfo:configJS.userInfo
      }
    },
    created(){
      let roleIds=configJS.userInfo.roles
      if (roleIds && roleIds.length == 1){
        let id=roleIds[0].roleId
       this.memuList=datas[id]
      }
    },
    computed:{
        returnn (roleId) {
            return datas[roleId]
        }
    }
    // created() {
    //   //判断用户角色信息
    //   let roleIds = configJS.userInfo.tRoles.map(e => e.roleId);
    //   let toPath = this.$route.path;
    //   if (roleIds && roleIds.length == 1 && toPath) {
    //     let roleId = roleIds[0];
    //     if (roleId == 1 && toPath.indexOf("/dogManager/") < 0) {//犬只办
    //       this.$router.replace({'name': 'dogManager_index'})
    //     } else if (roleId == 2 && toPath.indexOf("/hospice/") < 0) {//收容所
    //       this.$router.replace({'name': 'hospice_index'})
    //     } else if (roleId == 3) {//犬主
    //       this.$router.replace({'name': 'index'})
    //     } else if (roleId == 4 && toPath.indexOf("/hospital/") < 0) {//宠物医院
    //       this.$router.replace({'name': 'hospital_index'})
    //     } else if (roleId == 5 && toPath.indexOf("/operator/") < 0) {//运营方
    //       this.$router.replace({'name': 'operator_index'})
    //     }
    //   }
    // },
    // computed: {
    //   isActiveMemu(){
    //     return (index)=>{
    //       let storeMemuIndex = this.$store.getters.getMemuIndex
    //       if(storeMemuIndex && storeMemuIndex >=0){
    //         return storeMemuIndex == index;
    //       }else{
    //         return localStorage.getItem("memuIndex") == index;
    //       }
    //     }
    //   },
    //   mumeTitile() {//菜单titile
    //     let mumeTitile = {
    //       resourceName: '菜单',
    //       link: 'index'
    //     }
    //     let imgUrl = '';
    //     this.memuList.map(item => {
    //       //主菜单名称
    //       if (item && item.seq == 0 && item.level == 1) {
    //         mumeTitile = item;
    //       }
    //       //背景图
    //       if (item && item.seq == 0 && item.level == 1) {
    //         imgUrl = item.description;
    //       }
    //     })
    //     mumeTitile.imgUrl = imgUrl;
    //     return mumeTitile;
    //   }
    // },
    // methods: {
    //   changeMemuIndex(index){
    //     // debugger
    //     //
    //     this.$store.commit('changMemuIndex',index);
    //     localStorage.setItem("memuIndex",index);
    //   }
    // }
  }
</script>
<style scoped>
  .ulnav a:hover {
    cursor: pointer;
  }

  .bor6b4a22 {
    cursor: pointer;
  }

  li:last-child {
    border-bottom: dashed 1px #9F8A72;
  }

  #caidan{
    height: 170px;
    margin-top: -20px;
  }
</style>
