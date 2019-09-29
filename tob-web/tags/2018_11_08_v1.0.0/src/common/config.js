const globalConf = {
  basicUrl:"https://qlw.soholy.cn/biz",
  userInfo:{//模拟登录后的用户信息
  //     userId:11,//用户id
  //     organizationId:1,//机构id
  //     orgType:1,
  //     dogOwnId:2,//犬主id
  //     districtId:1,//机构id
  //     username:'zhangsan',//用户名
  //     name:'张三三',//名称
  //     sex:1,//性别
  //     phone:'13566354855',//电话
  //     email:'xiaopengge@soholy.cn',//邮箱
  //     loginNum:1,//登录次数
  //     recentlogime:new Date(),//最近登录时间
  //     dogRefDevices:[
  //       {
  //           dogId:10101608,
  //           dogOwnerId:2,
  //           dogBreedType:1,
  //           dogColorType:1,
  //           dogName:1,
  //           birthTime:new Date(),
  //           dogGender:1,
  //           dogCharacter:'无',
  //           weight:2,
  //           dogStatus:1,
  //           cancellationReason:'无',
  //           cancellationTime:new Date(),
  //           deductionSum:1,
  //           fatherId:null,
  //           motherId:null,
  //           healthStatus:0,
  //           operationId:1,
  //           creationTime:new Date(),
  //           dogRemarks:"无",
  //           device:{
  //             deviceId:1,
  //             deviceNumber:'12456',
  //             deviceStatus:1,
  //             startTime:new Date(),
  //             deviceBrand:1,
  //             deviceModelId:1,
  //             productionTime:new Date(),
  //             deviceRemarks:'---',
  //             deviceType:1,
  //             creationTime:new Date(),
  //             orgId:1,
  //             deviceWorkPattern:0,
  //             uploadIntervalTime:300,
  //             imei:'86120124624887',
  //             deviceFactoryId:1,
  //             deviceName:'test_device',
  //             verifyCode:'86120124624887',
  //             psk:'115412-15fa21451-da15514',
  //             deviceBatch:'--',
  //             deviceIdIot:'1545-df44151-454df5454-154dfasdf4541'
  //           }
  //       }
  //     ],
  //     tOrganization:{
  //       organizationId:1,
  //       organizationName:'医院test',
  //       organizationCapital:'深圳',
  //       registerNumber:'001',
  //       creditCode:'信用代码',
  //       establishDate:new Date(),
  //       provinceId:1,
  //       ownedCityId:1,
  //       districtId:1,
  //       phone:'16895668455',
  //       personLiable:'老板负责人',
  //       orgType:1,
  //       scopeOfOperation:'什么都搞',
  //       address:'新安街道人很话不多摄取12号',
  //       longitude:'122.01421',
  //       latitude:'22.142',
  //       status:2,
  //       failReason:'',
  //       power:0,
  //       approvalTime:new Date(),
  //       creationTime:new Date(),
  //     },
  //     tRoles:[{roleId:1,
  //         deptId:1,
  //         roleName:'admin-role',
  //         rStatus:0,
  //         parentId:null,
  //         roleDescribe:'max roles',
  //         creationTime:new Date()
  //     }]
  },
  resource:{//用户资源信息
  //   memu:[//菜单
  //     {resourceId: 1, resourceName: "犬只办", keyword: "/MobileOperator/Authority", resourceStatus: 0, resourceType: 1, seq:0,parent_id:0,level:1,creation_time:new Date(),link:'dogManager_list',description:'/static/images/b1.png'},
  //     {resourceId: 214, resourceName: "犬只档案", keyword: "/MobileOperator/Authority", resourceStatus: 0, resourceType: 1, seq:null,parent_id:0,level:1,creation_time:new Date(),link:'dogManager_list',description:'description'},
  //     {resourceId: 214, resourceName: "犬主档案", keyword: "/MobileOperator/Authority", resourceStatus: 0, resourceType: 1, seq:null,parent_id:0,level:1,creation_time:new Date(),link:'dogManager_doglist',description:'description'},
  //     {resourceId: 242, resourceName: "黑名单", keyword: "/user/list", resourceStatus: 0, resourceType: 1, seq:null,parent_id:0,level:1,creation_time:new Date(),link:'dogManager_blacklist',description:'description'},
  //     {resourceId: 243, resourceName: "执法处罚", keyword: "/user/add", resourceStatus: 0, resourceType: 1,seq:null,parent_id:0,level:1,creation_time:new Date(),link:'dogManager_LawEList',description:'description'},
  //     {resourceId: 248, resourceName: "禁养犬种", keyword: "/role/getResourceIds", resourceStatus: 0, resourceType: 1,seq:null,parent_id:0,level:1,creation_time:new Date(),link:'dogManager_BanList',description:'description'},
  //     {resourceId: 244, resourceName: "服务网点", keyword: "/user/delete", resourceStatus: 0, resourceType: 1,seq:null ,parent_id:0,level:1,creation_time:new Date(),link:'#',description:'description'},
  //     {resourceId: 245, resourceName: "预约管理", keyword: "/user/assignRole", resourceStatus: 0, resourceType: 1,seq:null ,parent_id:0,level:1,creation_time:new Date(),link:'#',description:'description'},
  //     {resourceId: 246, resourceName: "信息发布", keyword: "/user/updateStatus", resourceStatus: 0, resourceType: 1,seq:null ,parent_id:0,level:1,creation_time:new Date(),link:'#',description:'description'},
  //     {resourceId: 247, resourceName: "电子围栏", keyword: "/role/list", resourceStatus: 0, resourceType: 1,seq:null,parent_id:0,level:1,creation_time:new Date(),link:'#',description:'description'},
  //     {resourceId: 248, resourceName: "犬只消息", keyword: "/role/getResourceIds", resourceStatus: 0, resourceType: 1,seq:null,parent_id:0,level:1,creation_time:new Date(),link:'#',description:'description'},
  //     {resourceId: 248, resourceName: "统计信息", keyword: "/role/getResourceIds", resourceStatus: 0, resourceType: 1,seq:null,parent_id:0,level:1,creation_time:new Date(),link:'#',description:'description'},
  //   ],
  //   other:[//其他资源信息
  //   ],
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
