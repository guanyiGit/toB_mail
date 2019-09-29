import Vue from 'vue'
import Router from 'vue-router'
import MainContainer from '../components/common/MainContainer'
import deliveryManage from '../components/order/deliveryManage'

import configJS from '../common/config'

import order from '../components/order/order'
import invoiceDetails from '../components/order/InvoiceDetails'
import addDeliveryNote from '../components/order/addDeliveryNote'
import orderAudit from '../components/order/orderAudit'
import orderApproval from '../components/order/orderApproval'
import myOrders from '../components/order/myOrders'
import orderDetail from '../components/order/orderDetail'
import auditApproval from '../components/order/auditApproval'
import SongHuoXinXi from '../components/order/SongHuoXinXi'
import AnZhuangXinXi from '../components/order/AnZhuangXinXi'
import FuJiaFeiYong from '../components/order/FuJiaFeiYong'


import cart from '../components/cart/cart'
import cartManage from '../components/cart/cartManage'
import accountDetailInfo from '../components/cart/accountDetailInfo'

import platformAdmin from '../components/orgAndPower/org/platformAdmin'
import platformOrgAdd from '../components/orgAndPower/org/platformOrgAdd'
import userAdd from  '../components/orgAndPower/power/UserAdd'
import assetsList from  '../components/orgAndPower/power/assetsList'

import subList from '../components/budget/sub/subList'
import subAdd from '../components/budget/sub/subAdd'
import subDefineList from '../components/budget/sub/subDefineList'
import addSubDefine from '../components/budget/sub/addSubDefine'

import login from '../components/login/login'

import basic from '../components/supplier/basic'//txy
import supplierList from '../components/supplier/supplierList'
import supplierAdd from '../components/supplier/supplierAdd'
import supplierUpd from '../components/supplier/supplierUpd'
import supplierDetails from '../components/supplier/supplierDetails'
import deliveryManAdd from '../components/supplier/deliveryManAdd'
import deliveryManDetails from '../components/supplier/deliveryManDetails'
import deliveryManUpd from '../components/supplier/deliveryManUpd'
import installerAdd from '../components/supplier/installerAdd'
import installerDetails from '../components/supplier/installerDetails'
import installerUpd from '../components/supplier/installerUpd'
import supplierGoodsList from '../components/supplier/supplierGoodsList'
import supplierGoodsDetail from '../components/supplier/supplierGoodsDetail'
import supplierOrderList from "../components/supplier/supplierOrderList"
import supplierOrderDetail from '../components/supplier/supplierOrderDetail'

import product from '../components/product/product'
import productIndex from '../components/product/index'
import productList from '../components/product/list'
import productInfo from '../components/product/info'
import mainMemu from '../components/common/mainMemu'




import goods from '../components/goods/goods'
import platformGoods from '../components/goods/platformGoods'
import buyerGoods from '../components/goods/buyerGoods'
import platformPutaway from '../components/goods/platformPutaway'
import addAttrGroup from '../components/goods/addAttrGroup'
import addBrand from '../components/goods/addBrand'
import addGoodsCategory from '../components/goods/addGoodsCategory'
import goodsDetail from '../components/goods/goodsDetail'
import addAttr from '../components/goods/addAttr'
import addGoods1 from '../components/goods/addGoods1'
import addGoods2 from '../components/goods/addGoods2'
import  budgetManage from '../components/budget/budgetManage'
import  addBudgetByOrg from '../components/budget/addBudgetByOrg'
import  addBudgetByOrgMonth from '../components/budget/addBudgetByOrgMonth'
import  addBudgetBySub from '../components/budget/addBudgetBySub'
import  addDeliveryPlan from '../components/budget/addDeliveryPlan'
import  deliveryPlan from '../components/budget/deliveryPlan'
import  deliveryPlanDetail from '../components/budget/deliveryPlanDetail'
import  budgetUseSchedule from '../components/budget/budgetUseSchedule'
import  budgetUseDetail from '../components/budget/budgetUseDetail'

import  budgetExecute from '../components/budget/budgetExecute'


import admin from '../components/parent/admin'
import platformOperator from '../components/parent/platformOperator'
import supGoodsManageperson from '../components/parent/supGoodsManageperson'
import purchasingAuditor from '../components/parent/purchasingAuditor'
import budgetMember from '../components/parent/budgetMember'
import procurementStaff from '../components/parent/procurementStaff'
import vendorManager from '../components/parent/vendorManager'
import purchasingApproval from '../components/parent/purchasingApproval'

import  editDeliveryPlan from '../components/budget/editDeliveryPlan'
import budgetQuery from '../components/order/budgetQuery'
Vue.use(Router)

let router = new Router({
  routes: [
    {path: '/login', name: 'login', meta: {noRequireAuth: true}, component: login},//登录页

    {
      path: '/', name: 'container', component: MainContainer, //容器
      children: [
        {path: '/',  redirect: {name: 'login'} },
        // {path: 'index', name: 'index', meta: {noRequireAuth: true}, component: IndexPage}, //游客--主页
        {path:'admin',name:'admin',component:admin,//各种管理员
          children:[
            {path:'platformAdmin',name:'platformAdmin',component:platformAdmin}, //管理员首页
            {path:'platformOrgAdd',name:'platformOrgAdd',component:platformOrgAdd}, //组织添加
            {path:'userAdd',name:'userAdd',component:userAdd}, //用户添加
            {path:'assetsList',name:'assetsList',component:assetsList}, //资产清单
          ]
        },
        {path:'platformOperator',name:'platformOperator',component:platformOperator,//平台运营人员
          children:[
            {path: 'platformGoods', name: 'platformGoods', component: platformGoods},//商品基本信息管理
            {path: 'addAttrGroup', name: 'addAttrGroup', component: addAttrGroup},//添加商品属性分组
            {path: 'platformPutaway', name: 'platformPutaway', component: platformPutaway},//商品上架管理
            {path: 'addBrand', name: 'addBrand', component: addBrand},//添加品牌
            {path: 'addGoodsCategory', name: 'addGoodsCategory', component: addGoodsCategory},//添加商品分类
            {path: 'addAttr', name: 'addAttr', component: addAttr},//添加属性
          ]
        },
        {path:'supGoodsManageperson',name:'supGoodsManageperson',component:supGoodsManageperson,//供应商商品管理人员
          children:[
            {path:'supplierList',name:'supplierList',component:supplierList},//供应商管理列表txy
            {path:'supplierAdd',name:'supplierAdd',component:supplierAdd},//供应商添加txy
            {path:'supplierUpd/:id',name:'supplierUpd',component:supplierUpd},//供应商修改txy
            {path:'supplierDetails/:id',name:'supplierDetails',component:supplierDetails},//供应商详情txy
            {path: 'buyerGoods', name: 'buyerGoods', component: buyerGoods},//商品管理列表
            {path: 'goodsDetail', name: 'goodsDetail', component: goodsDetail},//商品详情
            {path: 'addGoods1', name: 'addGoods1', component: addGoods1},//添加商品1
            {path: 'addGoods2', name: 'addGoods2', component: addGoods2},//添加商品2
          ]
        },

        {path:'budgetMember',name:'budgetMember',component:budgetMember,//预算员
          children:[
            {path: 'budgetManage',name: 'bm_budgetManage',component : budgetManage},
            {path: 'addBudgetByOrg',name: 'addBudgetByOrg',component : addBudgetByOrg},
            {path: 'addBudgetByOrgMonth',name: 'addBudgetByOrgMonth',component : addBudgetByOrgMonth},
            {path: 'addBudgetBySub',name: 'addBudgetBySub',component : addBudgetBySub},
            {path: 'addDeliveryPlan',name: 'addDeliveryPlan',component : addDeliveryPlan},
            {path: 'budgetExecute',name: 'budgetExecute',component : budgetExecute},
            {path: 'budgetUseDetail',name: 'budgetUseDetail',component : budgetUseDetail},
            {path: 'budgetUseSchedule',name: 'budgetUseSchedule',component : budgetUseSchedule},
            {path: 'deliveryPlan',name: 'deliveryPlan',component : deliveryPlan},
            {path: 'deliveryPlanDetail',name: 'deliveryPlanDetail',component : deliveryPlanDetail},
            {path: 'editDeliveryPlan',name: 'editDeliveryPlan',component : editDeliveryPlan},
            {path: 'orderApproval', name: 'bm_orderApproval', component: orderApproval},//订单审批列表
            {path: 'orderDetail', name: 'bm_orderDetail', component: orderDetail},//订单详情
            {path: 'auditApproval', name: 'bm_auditApproval', component: auditApproval},//订单审核审批
            {path:'subList',name:'subList',component:subList},  //科目列表
            {path:'subAdd',name:'subAdd',component:subAdd},     //科目添加
            {path:'subDefineList',name:'subDefineList',component:subDefineList},
            {path:'addSubDefine',name:'addSubDefine',component:addSubDefine}
          ]
        },
        {path:'purchasingApproval',name:'purchasingApproval',component:purchasingApproval,//采购审批人员
          children:[
            {path: 'orderApproval', name: 'pal_orderApproval', component: orderApproval},//订单审批列表
            {path: 'orderDetail', name: 'pal_orderDetail', component: orderDetail},//订单详情
            {path: 'auditApproval/:orderInfo/type/excess', name: 'pal_auditApproval', component: auditApproval},//订单审核审批
          ]
        },
        {path:'purchasingAuditor',name:'purchasingAuditor',component:purchasingAuditor,//采购审核人员
          children:[
            {path: 'orderAudit', name: 'orderAudit', component: orderAudit},//订单审核列表
            {path: 'orderDetail', name: 'pa_orderDetail', component: orderDetail},//订单详情
            {path: 'auditApproval/:orderInfo/type/excess', name: 'pa_auditApproval', component: auditApproval},//订单审核审批
          ]
        },
        /**商城相关 采购人员*/
        {path: 'product', name: 'product', component: product,
          children:[
            {path: 'index', name:'product_index', component: productIndex},//商城首页
            {path: 'list', name:'product_list', component: productList },//商城商品列表
            {path: 'info/:skuId', name:'product_info', component: productInfo},//商城商品详情
            {path: 'cartManage',name: 'cartManage',component : cartManage}, //购物车列表
            {path: 'accountDetailInfo/:id',name: 'accountDetailInfo',component : accountDetailInfo},//订单核算
            {path:'procurementStaff',name:'procurementStaff',component:procurementStaff,//采购人员
              children:[
                {path: 'myOrders', name: 'myOrders', component: myOrders},//我的订单列表
                {path: 'orderDetail', name: 'ps_orderDetail', component: orderDetail},//订单详情
                {path: 'suoghuo/:orderId', name: 'suoghuo', component: SongHuoXinXi},//送货信息
                {path: 'anzhuang/:orderId', name: 'anzhuang', component: AnZhuangXinXi},//安装信息
                {path: 'feiyong/:orderId', name: 'feiyong', component: FuJiaFeiYong},//其他费用信息
                {path: 'budgetQuery',name: 'budgetQuery',component : budgetQuery},//预算查询
              ]
            },
          ]
        },
        {path:'vendorManager',name:'vendorManager',component:vendorManager,//供应商方管理员
          children:[
            {path:'deliveryManAdd', name: 'deliveryManAdd', component: deliveryManAdd},//送货员添加txy
            {path:'deliveryManDetails/:id', name: 'deliveryManDetails', component: deliveryManDetails},//送货员查看txy
            {path:'deliveryManUpd/:id', name: 'deliveryManUpd', component: deliveryManUpd},//送货员修改txy
            {path:'installerAdd', name: 'installerAdd', component: installerAdd},//安装员添加txy
            {path:'installerDetails/:id', name: 'installerDetails', component: installerDetails},//安装员查看txy
            {path:'installerUpd/:id', name: 'installerUpd', component: installerUpd},//安装员修改txy
            {path:'basic',name:'basic',component:basic},//供应商基本信息txy
            {path:'supplierGoodsList', name: 'supplierGoodsList', component: supplierGoodsList},//供应商商品列表txy
            {path: 'supplierGoodsDetail', name: 'supplierGoodsDetail', component: supplierGoodsDetail},//商品详情
            {path: 'supplierOrderList', name: 'supplierOrderList', component: supplierOrderList},//订单管理
            {path: 'supplierOrderDetail', name: 'supplierOrderDetail', component: supplierOrderDetail},//订单详情
            {path: 'deliveryManage', name: 'deliveryManage', component: deliveryManage},//送货管理列表
            {path: 'invoiceDetails/:havesentSingle/supId', name: 'invoiceDetails', component: invoiceDetails},//发货单详情
            {path: 'addDeliveryNote', name: 'addDeliveryNote', component: addDeliveryNote},//新增送货单
          ]
        },
      ]
    },
    {path: '*', redirect: {name: 'login'}},
  ],
  // //页面跳转定位到最上
  // scrollBehavior (to, from, savedPosition) {
  //   // return 期望滚动到哪个的位置
  //   return { x: 0, y: 0 }
  // }
});

router.beforeEach((to, from, next) => {next()
  let routerName = ""
  try {
    if(!configJS.userInfo || ! configJS.authorization){
        //用户信息
        configJS.userInfo = JSON.parse(localStorage.getItem("userInfo"));
        configJS.Authorization = localStorage.getItem("authorization");
    }

    let userInfo = configJS.userInfo;
    let authorization = configJS.Authorization;
    if(to.name === "login"){
        routerName = routerIndex(userInfo.roles, next);
    }

    if(routerName){
      next({name: routerName});
    }else{
      next();
    }
  }catch (e) {
    console.log(e);
  }
})

function routerIndex(roles) {
  if(roles && roles.length > 0){
    for(let role of roles){
      let roleId =  role.roleId;
      return getRoleIndex(roleId);
    }
  }
}

function getRoleIndex(roleId) {
  if (roleId ==1 || roleId ==3 || roleId==5) {
    return "platformAdmin"
    //平台运营人员
  }else if(roleId == 2){
    return "platformGoods"
    //供应商商品管理人员
  }else if(roleId == 4){
    return "supplierList"
    //预算人员
  }else if(roleId == 6){
    return "bm_budgetManage"
    //采购审核人员
  }else if(roleId == 7){
    return "orderAudit"
    //采购人员
  }else if(roleId ==8){
    return "product_index"
    //供应商方-管理员
  }else if(roleId ==9){
    return "basic"
  }else if ( roleId == 10){
    return "pal_orderApproval"
  }
}
export default router;
