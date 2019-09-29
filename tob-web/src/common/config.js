const globalConf = {
  // basicUrl:"http://192.168.0.69:9528/biz",
  basicUrl:"http://localhost:9528/biz",
  // shiroUrl:"http://192.168.0.14:9528/biz",
  // shiroUrl:"http://192.168.0.14:9003",

  // basicUrl:"http://192.168.0.69:9527/biz",
  // userInfo:{
  //   userId: 1,
  //   userNo: 112,
  //   username: "13800138000",
  //   name: "张三",
  //   password: "e7d341c6253c8c3f0d15517da52b1466",
  //   orgInfoDO: {
  //
  //   },
  //   email: null,
  //   mobile: "13800138000",
  //   status: 0,
  //   userIdCreate: null,
  //   gmtCreate: null,
  //   gmtModified: null,
  //   roleIds: null,
  //   sex: 0,
  //   roles: [{
  //     roleId: 1,
  //     roleName: "平台管理员",
  //     roleSign: "admin",
  //     remark: null,
  //     userIdCreate: null,
  //     gmtCreate: null,
  //     gmtModified: null,
  //     menuIds: null,
  //     type: 0
  //   }]
  // },
  Authorization:"",

  setUserInfo(userInfo){
    this.userInfo = userInfo
  },

  setAuthorization(Authorization){
    this.Authorization = Authorization
  },


  getUserInfo(that){
    var  userInfo =  localStorage.getItem("userInfo");
    if(userInfo){
      this.userInfo = JSON.parse(userInfo);
    }else {
      //如果没有用户信息，跳转到登录页
      that.$router.push({name:'login'})
    }
  },


  isAdminBud(){
    var orgInfoDO = this.userInfo.orgInfoDO;
    var role = this.userInfo.roles[0];
    if( orgInfoDO.pid == 0 ){
      if (role.roleId == 6){
        return true;
      }
    }
    return false;
  },

  isplatformAdmin(){
    var roleId = this.userInfo.roles[0].roleId;
    if(roleId == 1){
        return true
    }else{
        return false
    }
  },

  isCompanyAdmin(){
    var roleId = this.userInfo.roles[0].roleId;
    if(roleId == 3){
      return true
    }else{
      return false
    }
  },

  isChildCompanyAdmin(){
    var roleId = this.userInfo.roles[0].roleId;
    if(roleId == 5){
      return true
    }else{
      return false
    }
  },


  mainMemuList:[//主页菜单
    {name:"首页",navName:"index"},//名称， 跳转路由名称
    {name:"爱心领养",navName:"loveAdopt"},
    {name:"服务指南",navName:"serviceGuide"},
    {name:"政策法规",navName:"policiesRegulations"},
    {name:"动态资讯",navName:"Information"},
    {name:"养犬知识",navName:"knowledge"},
  ]
}
module.exports = globalConf;
