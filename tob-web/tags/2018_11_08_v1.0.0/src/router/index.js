import Vue from 'vue'
import Router from 'vue-router'
import iView from 'iview'

import login from '../components/login/login'
import IndexPage from '../components/index/IndexPage'
import MainContainer from '../components/common/MainContainer'

import dogManager from '../components/dogManager/dogManager'
import dogManagerList from '../components/dogManager/dogManagerList'
import dogManagerInfo from '../components/dogManager/dogManagerInfo'
import dogManagerBlacklist from '../components/dogManager/dogManagerBlacklist'
import dogManagerBlackInfo from '../components/dogManager/dogManagerBlackInfo'
import dogManagerBlackAdd from '../components/dogManager/dogManagerBlackAdd'
import dogManagerDogList from '../components/dogManager/dogManagerDogList'
import dogManagerDogAdd from '../components/dogManager/dogManagerDogAdd'
import dogManagerLawEList from '../components/dogManager/dogManagerLawEList'
import dogManagerLawEInfo from '../components/dogManager/dogManagerLawEInfo'
import dogManagerBanList from '../components/dogManager/dogManagerBanList'
import dogManagerBanAdd from '../components/dogManager/dogManagerBanAdd'
import dogManagerFence from '../components/dogManager/dogManagerFence'
import dogManagerIndex from '../components/dogManager/dogManagerIndex'
import dogManagerOwnersList from '../components/dogManager/dogManagerOwnersList'
import dogManagerOwnersInfo from '../components/dogManager/dogManagerOwnersInfo'
import dogManagerUnlicensed from '../components/dogManager/dogManagerUnlicensed'
import siteRegistrationfrom from '../components/serviceGuide/siteRegistration'

import dogManagerIllnessList from '../components/dogManager/dogManagerIllnessList' //txy
import dogManagerIllnessDatails from '../components/dogManager/dogManagerIllnessDatails'
import dogManagerIllnessAdd from '../components/dogManager/dogManagerIllnessAdd'
import dogManagerImmuneList from '../components/dogManager/dogManagerImmuneList'
import dogManagerImmuneDatail from '../components/dogManager/dogManagerImmuneDatail'
import dogManagerImmuneAdd from '../components/dogManager/dogManagerImmuneAdd' //txy
import Policies from '../components/policiesRegulations/policiesRegulations'
import loveAdopt from '../components/loveAdopt/loveAdopt'
import serviceGuide from '../components/serviceGuide/serviceGuide'
import Information from '../components/infoAndknowledge/Information'
import knowledge from '../components/infoAndknowledge/knowledge'
import infoDetails from '../components/infoAndknowledge/infoDetails'
import bookingOutlets from '../components/serviceGuide/bookingOutlets'
import dogShelter from '../components/shelter/dogShelter/dogShelter'
import pendingAdoptionDetails from '../components/shelter/dogShelter/pendingAdoptionDetails'
import adopt from '../components/loveAdopt/adopt'
import certificateDealt from '../components/petHospital/certificateDealt'
import addImmuneCard from '../components/petHospital/addImmuneCard'
import checkImmuneCard from '../components/petHospital/checkImmuneCard'
import reservationList from '../components/petHospital/reservationList'
import reservationAdd from '../components/petHospital/reservationAdd'

import hospice from '../components/shelter/hospice'
import releaseDetails from '../components/shelter/dogShelter/releaseDetails'
import beenAdoptedDetails from '../components/shelter/dogShelter/beenAdoptedDetails'
import claimedDetails from '../components/shelter/dogShelter/claimedDetails'
import hasDealwith from '../components/shelter/dogShelter/hasDealwith'
import theShelterDetail from '../components/shelter/dogShelter/theShelterDetail'
import enforcementManagement from '../components/shelter/enforcementManagement'
import informationRelease from '../components/shelter/informationRelease'
import release from '../components/shelter/release'
import illegalRegistration from '../components/shelter/illegalRegistration'
import membershipCardAdd from '../components/shelter/membershipCardAdd'
import menberCradList from '../components/shelter/menberCradList'
import menberInfo from '../components/shelter/menberInfo'
import collectionPage from '../components/shelter/collectionPage'
import forRegistration from '../components/shelter/dogShelter/forRegistration'
import handleRegistration from '../components/shelter/dogShelter/handleRegistration'
import claimToRegister from '../components/shelter/dogShelter/claimToRegister'
import adoptionRegistration from '../components/shelter/dogShelter/adoptionRegistration'
import confirmToAdopt from '../components/shelter/dogShelter/confirmToAdopt'

import test from '../components/operator/test'

import operator from '../components/operator/operator'
import operatorMsgAuditList from '../components/operator/operatorMsgAuditList'
import operatorMsgInfo from '../components/operator/operatorMsgInfo'
import resourceManager from '../components/operator/resourceManager'
import addUser from '../components/operator/addUser'
import operatorIndex from '../components/operator/operatorIndex'
import operatorMsgAdd from '../components/operator/operatorMsgAdd'
import operatorDeviceList from '../components/operator/operatorDeviceList'
import operatorDeviceInfo from '../components/operator/operatorDeviceInfo'
import message from '../components/operator/message'
import serviceNetworkAudit from '../components/operator/serviceNetworkAudit'
import networkAudit from '../components/operator/networkAudit'
import checkBranches from '../components/operator/checkBranches'
import enforcementManagementDetails from '../components/shelter/enforcementManagementDetails'

import petHospital from '../components/petHospital/petHospital'
import petHospitalIndex from '../components/petHospital/petHospitalIndex'
import configJS from "../common/config";
import axios from 'axios'

Vue.use(Router)


let router = new Router({
  routes: [
    {
      path: '/', name: 'container', component: MainContainer, //容器
      children: [
        {path: 'index', name: 'index', meta: {noRequireAuth: true}, component: IndexPage},//游客--主页
        {path: 'loveAdopt', name: 'loveAdopt', meta: {noRequireAuth: true}, component: loveAdopt},//游客-爱心领养
        {path: 'policies', name: 'policiesRegulations', meta: {noRequireAuth: true}, component: Policies},//游客-政策法规
        {path: 'serviceGuide', name: 'serviceGuide', meta: {noRequireAuth: true}, component: serviceGuide},//游客-服务指南
        {path: 'Information', name: 'Information', meta: {noRequireAuth: true}, component: Information},//游客-动态资讯
        {path: 'knowledge', name: 'knowledge', meta: {noRequireAuth: true}, component: knowledge},//游客-养犬知识
        {path: 'infoDetails', name: 'infoDetails', meta: {noRequireAuth: true}, component: infoDetails},//详情
        {path: 'bookingOutlets', name: 'bookingOutlets', meta: {noRequireAuth: true}, component: bookingOutlets},
        {path: 'siteRegistrationfrom', name: 'siteRegistrationfrom', component: siteRegistrationfrom },
        {path: 'adopt', name: 'adopt', component: adopt},//爱心领养申请页
        {
          path: 'dogManager', name: 'dogManager', component: dogManager, //犬只办
          children: [
            {path: 'index', name: 'dogManager_index', component: dogManagerIndex},//首页
            {path: 'message', name: 'dogManager_message', meta: {noRequireAuth: true}, component: message},//我的消息

            {path: 'list', name: 'dogManager_list', component: dogManagerList},//犬只档案
            {path: 'info/:dogId', name: 'dogManager_info', component: dogManagerInfo},//犬只档案--查看详情/犬只详情/犬证详情

            {path: 'ownersList', name: 'dogManager_owners_list', component: dogManagerOwnersList},//犬主档案列表
            {path: 'ownersInfo/:id', name: 'dogManager_owners_info', component: dogManagerOwnersInfo},//犬主档案详情

            {path: 'unlicensedList', name: 'dogManager_Unlicensed_list', component: dogManagerUnlicensed},//犬只办、收容所 无证犬只[未做]

            {path: 'doglist', name: 'dogManager_doglist', component: dogManagerDogList},//犬证列表.犬证管理
            {path: 'dogadd', name: 'dogManager_dogadd', component: dogManagerDogAdd},//犬证办理

            {path: 'blacklist', name: 'dogManager_blacklist', component: dogManagerBlacklist},//黑名单列表.
            {path: 'blackinfo/:blackId', name: 'dogManager_blackinfo', component: dogManagerBlackInfo},//黑名单详情.
            {path: 'blackadd', name: 'dogManager_blackadd', component: dogManagerBlackAdd},//黑名单添加.

            {path: 'LawEList', name: 'dogManager_LawEList', component: dogManagerLawEList},//执法处罚列表
            {path: 'LawEInfo/:violationId', name: 'dogManager_LawEInfo', component: dogManagerLawEInfo},//执法处罚 详情

            {path: 'serviceNetworkAudit', name: 'dogManager_serviceNetworkAudit', component: serviceNetworkAudit},//服务网点审核列表
            {path: 'networkAudit', name: 'dogManager_networkAudit', component: networkAudit},//服务网点审核
            {path: 'checkBranches', name: 'dogManager_checkBranches', component: checkBranches},//服务网点审核网点详情

            {path: 'informationRelease', name: 'dogManager_informationRelease', component: informationRelease},//犬只办/收容所-信息发布列表

            {path: 'fence', name: 'dogManager_fence', component: dogManagerFence},//围栏

            //注销

            {path: 'BanList', name: 'dogManager_BanList', component: dogManagerBanList},//禁养犬种
            {path: 'BanAdd', name: 'dogManager_BanAdd', component: dogManagerBanAdd},//禁养犬种 新增
          ]
        },
        {
          path: 'hospice', name: 'hospice', component: hospice,//收容所
          children: [
            {path: 'index', name: 'hospice_index', component: collectionPage}, //收容所首页
            {path: 'message', name: 'hospice_message', meta: {noRequireAuth: true}, component: message},//我的消息

            {path: 'enforcementManagement', name: 'hospice_enforcementManagement', component: enforcementManagement},//收容所-执法管理列表
            {path: 'illegalRegistration', name: 'hospice_illegalRegistration', component: illegalRegistration},//收容所-执法管理-违法登记

            {path: 'dogShelter', name: 'hospice_dogShelter', component: dogShelter},//犬只收容
            {path: 'theShelterDetail', name: 'hospice_theShelterDetail', component: theShelterDetail},//收容-收容中-查看详情
            {path: 'releaseDetails', name: 'hospice_releaseDetails', component: releaseDetails},//收容-发布中-查看详情
            {path: 'pendingAdoptionDetails', name: 'hospice_pendingAdoptionDetails', component: pendingAdoptionDetails},//犬只收容-待确认领养-查看详情
            {path: 'claimedDetails', name: 'hospice_claimedDetails', component: claimedDetails},//犬只收容-已认领-查看详情
            {path: 'beenAdoptedDetails', name: 'hospice_beenAdoptedDetails', component: beenAdoptedDetails},//犬只收容-已领养-查看详情
            {path: 'hasDealwith', name: 'hospice_hasDealwith', component: hasDealwith},//犬只收容-已处理-查看详情
            {path: 'dogShelter', name: 'dogShelter', component: dogShelter},//犬只收容
            {path: 'theShelterDetail', name: 'theShelterDetail', component: theShelterDetail},//收容-收容中-查看详情
            {path: 'releaseDetails', name: 'releaseDetails', component: releaseDetails},//收容-发布中-查看详情
            {path: 'pendingAdoptionDetails', name: 'pendingAdoptionDetails', component: pendingAdoptionDetails},//犬只收容-待确认领养-查看详情
            {path: 'claimedDetails', name: 'claimedDetails', component: claimedDetails},//犬只收容-已认领-查看详情
            {path: 'beenAdoptedDetails', name: 'beenAdoptedDetails', component: beenAdoptedDetails},//犬只收容-已领养-查看详情
            {path: 'hasDealwith', name: 'hasDealwith', component: hasDealwith},//犬只收容-已处理-查看详情
            {path: 'dogShelter', name: 'dogShelter', component: dogShelter},//犬只收容
            {path: 'forRegistration', name: 'forRegistration', component: forRegistration},//犬只收容-收容登记
            {path: 'handleRegistration', name: 'handleRegistration', component: handleRegistration},//收容中-处理登记
            {path: 'claimToRegister', name: 'claimToRegister', component: claimToRegister},//收容中-认领登记
            {path: 'adoptionRegistration', name: 'adoptionRegistration', component: adoptionRegistration},//收容中-领养登记
            {path: 'confirmToAdopt', name: 'confirmToAdopt', component: confirmToAdopt},//待确认领养-确认领养
            {path: 'theShelterDetail', name: 'theShelterDetail', component: theShelterDetail},//收容-收容中-查看详情
            {path: 'releaseDetails', name: 'releaseDetails', component: releaseDetails},//收容-发布中-查看详情
            {path: 'pendingAdoptionDetails', name: 'pendingAdoptionDetails', component: pendingAdoptionDetails},//犬只收容-待确认领养-查看详情
            {path: 'claimedDetails', name: 'claimedDetails', component: claimedDetails},//犬只收容-已认领-查看详情
            {path: 'beenAdoptedDetails', name: 'beenAdoptedDetails', component: beenAdoptedDetails},//犬只收容-已领养-查看详情
            {path: 'hasDealwith', name: 'hasDealwith', component: hasDealwith},//犬只收容-已处理-查看详情

            {path: 'unlicensedList', name: 'hospice_Unlicensed_list', component: dogManagerUnlicensed},//犬只办、收容所 无证犬只[未做]

            {path: 'doglist', name: 'hospice_doglist', component: dogManagerDogList},//犬证列表.犬证管理
            {path: 'dogadd', name: 'hospice_dogadd', component: dogManagerDogAdd},//犬证办理
            {path: 'info/:dogId', name: 'hospice_info', component: dogManagerInfo},//犬只档案--查看详情/犬只详情/犬证详情

            {path: 'memberCardList', name: 'hospice_memberCardList', component: menberCradList}, //会员证列表
            {path: 'memberCardInfo/:cardId', name: 'hospice_memberCardInfo', component: menberInfo}, //会员证详情
            {path: 'memberCardAdd', name: 'hospice_memberCardAdd', component: membershipCardAdd}, //会员证添加

            {path: 'informationRelease', name: 'hospice_informationRelease', component: informationRelease},//犬只办/收容所-信息发布列表

            {path: 'blacklist', name: 'hospice_blacklist', component: dogManagerBlacklist},//黑名单列表.
            {path: 'blackinfo/:blackId', name: 'hospice_blackinfo', component: dogManagerBlackInfo},//黑名单详情.
            {path: 'blackadd', name: 'hospice_blackadd', component: dogManagerBlackAdd},//黑名单添加.
          ]
        },
        {
          path: 'hospital', name: 'hospital', component: petHospital,//宠物医院
          children: [
            {path: 'index', name: 'hospital_index', component: petHospitalIndex}, //宠物医院首页
            {path: 'message', name: 'hospital_message', meta: {noRequireAuth: true}, component: message},//我的消息

            {path: 'certificateDealt', name: 'hospital_certificateDealt', component: certificateDealt},//宠物医院犬证管理/免疫证管理
            {path: 'addImmuneCard', name: 'hospital_addImmuneCard', component: addImmuneCard},//宠物医添加免疫证
            {path: 'checkImmuneCard', name: 'hospital_checkImmuneCard', component: checkImmuneCard},//宠物医院查看免疫证
            {path: 'dogadd', name: 'hospital_dogadd', component: dogManagerDogAdd},//犬证办理

            {path: 'ImmuneList', name: 'hospital_ImmuneList', component: dogManagerImmuneList},//免疫记录txy
            {path: 'ImmuneDatail/:seqId', name: 'hospital_ImmuneDatail', component: dogManagerImmuneDatail},//免疫详情txy
            {path: 'ImmuneAdd', name: 'hospital_ImmuneAdd', component: dogManagerImmuneAdd},//添加免疫记录txy

            //年检 列表 新增 详情

            {path: 'IllnessList', name: 'hospital_IllnessList', component: dogManagerIllnessList},//病历记录txy
            {path: 'IllnessAdd', name: 'hospital_IllnessAdd', component: dogManagerIllnessAdd},//病历添加txy
            {path: 'txyinfo/:dogId', name: 'hospital_txyinfo', component: dogManagerIllnessDatails},//病历详情txy

            {path: 'reservationList', name: 'hospital_reservationList', component: reservationList},//宠物医院预约列表
            {path: 'reservationAdd', name: 'hospital_reservationAdd', component: reservationAdd},//宠物医院预约详情页
          ]
        },
        {
          path: 'operator', name: 'operator', component: operator,//运营商
          children: [
            {path: 'index', name: 'operator_index', component: operatorIndex}, //运营商首页
            {path: 'message', name: 'operator_message', meta: {noRequireAuth: true}, component: message},//我的消息

            {path: 'deviceList', name: 'operator_device_list', component: operatorDeviceList},//设备列表页
            {path: 'deviceInfo/:devNo', name: 'operator_device_info', component: operatorDeviceInfo},//设备详情页

            {path: 'informationRelease', name: 'operator_informationRelease', component: informationRelease},//犬只办/收容所-信息发布列表

            {path: 'msgAuditList', name: 'operator_msgAuditList', component: operatorMsgAuditList}, //信息审核列表页
            {path: 'msgAdd', name: 'operator_msgAdd', component: operatorMsgAdd}, //信息审核新增页
            {path: 'msgInfo', name: 'operator_msgInfo', component: operatorMsgInfo},//信息审核详情页

            {path: 'serviceNetworkAudit', name: 'operator_serviceNetworkAudit', component: serviceNetworkAudit},//服务网点审核列表
            {path: 'networkAudit', name: 'operator_networkAudit', component: networkAudit},//服务网点审核
            {path: 'checkBranches', name: 'operator_checkBranches', component: checkBranches},//服务网点审核网点详情

            {path: 'resource', name: 'operator_resource', component: resourceManager},//权限管理页
            {path: 'adduser', name: 'operator_add_user', component: addUser},//新增用户
            {path: 'test', name: 'test', component: test},//权限管理页
            {path: 'enforcementManagementDetails', name: 'enforcementManagementDetails', component: enforcementManagementDetails },//违法详情.
          ]
        }
      ]
    },
    {path: '/login', name: 'login', meta: {noRequireAuth: true}, component: login},//登录页
    {path: '*', redirect: {name: 'index'}},
  ]
});


// 判断是否需要登录权限 以及是否登录
router.beforeEach((to, from, next) => {
  iView.LoadingBar.start();
  // next()

  // next();
  // return;
  if (!to.matched.some(res => res.meta.noRequireAuth)) {// 判断是否需要登录
    //当前应用中没有用户信息
    if (!configJS.userInfo || !configJS.userInfo.userId) {
      try {
        axios.post('/biz/getUSerInfo').then(res => {
          if (res.data.status == 200 && res.data.data) {
            configJS.userInfo = res.data.data;
            axios.get('/biz/resource/getResources')
              .then(res => {
                if (res.data.status == 200 && res.data.data) {
                  let memus = [];//菜单
                  let resource = res.data.data.map((item, index) => {//其他资源
                    if (item.resourceType && item.resourceType == 1) {//菜单类型
                      memus.push(item)
                    } else {//其他资源
                      return item;
                    }
                  })
                  memus.sort(compare)
                  configJS.resource.memu = memus;
                  configJS.resource.other = resource;
                  next();
                } else {
                  //没有用户信息游客首页
                  next({name: 'index',})
                }
              })
          } else {
            //没有用户信息游客首页
            next({name: 'index',})
          }
        })

        try {
          //用户资源信息
          // localStorage.setItem("resource_info_token", JSON.stringify(configJS.resource));
          // localStorage.setItem("manager_dog_user_id", configJS.userInfo);
        } catch (e) {
        }

        //userInfo = JSON.parse(localStorage.getItem("user_info_token"));
        // resource = JSON.parse(localStorage.getItem("resource_info_token"));
      } catch (e) {
        console.log(e)
        next({name: 'index',})
      }
      // //成功取出用户信息
      // if (userInfo && resource) {
      //   configJS.userInfo = userInfo;
      //   configJS.resource = resource;
      //   next();//放行
      //   return;
      // }

      // this.$router.push({name:"login"});
    } else {//有用户信息
      next();//放行
    }
  } else {//无须登录页面
    next()
  }
})


//资源排序
let compare = function (obj1, obj2) {
  var val1 = obj1.seq;
  var val2 = obj2.seq;
  if (val1 < val2) {
    return -1;
  } else if (val1 > val2) {
    return 1;
  } else {
    return 0;
  }
}

export default router;
