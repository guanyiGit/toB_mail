<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">

    <div>
      <p class="fs18 h28 lh28 cor0">组织权限管理</p>
      <ul class="ul003 mgt20">
        <li class="active" @click.prevent="checkTab = 1"><a href="###">组织管理</a></li>
        <li @click.prevent="checkTab = 2"><a href="###">用户管理</a></li>
        <li @click.prevent="checkTab = 3"><a href="###">角色管理</a></li>
      </ul>

      <div class="mgt30">
        <ul class="ul0004">
          <!--组织管理-->
          <li class="db">
            <div class="ovh mgt20">
              <div class="fr">
                <form action="" class="form-inline">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="组织名称/编号" v-model="orgParams.query">
                    <span class="input-group-btn">
                      <button class="btn bged145b" @click="queryOrg">
                        <i class="glyphicon glyphicon-search corw"></i>
                      </button>
								    </span>
                  </div>
                </form>
              </div>
            </div>
            <ul class="ulDiv ovh mgt30">
              <li class="fl mgr30">
                <router-link :to="{name:'platformOrgAdd'}" class="db pdl29 corED145B ios2">新增组织</router-link>
              </li>
            </ul>
            <div class="mgt30">
              <table class="table table-bordered t-c">
              <!--<table class="tab2">-->
                <tbody>
                  <tr>
                    <td>组织编号</td>
                    <td>组织名称</td>
                    <td>创建时间</td>
                    <td>操作</td>
                  </tr>
                  <tr v-for="(item,index) in orgList">
                    <td>{{ item.orgNo }}</td>
                    <td>{{ item.orgName}}</td>
                    <td>{{item.createDate | formatDate("yyyy-MM-dd") }}</td>
                    <td><a href="#" class="btn btn-xs btn-danger"
                           @click.prevent="deleteOrg(item.id)">删除</a></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--用户管理-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fl mgr30" v-if="isplatformAdmin">
                <select class="form-control ios0" @change="orgChange" v-model="orgParams.orgId">
                  <option value="0" selected="selected">全部组织</option>
                  <option v-for="(item,index) in orgList" :value="item.id">{{ item.orgName }}</option>
                </select>
              </div>
              <div class="fl mgr30" v-if="!isplatformAdmin">
                <select class="form-control ios0" @change="orgChange" v-model="orgParams.orgId">
                  <option v-for="(item,index) in orgList" :value="item.id">{{ item.orgName }}</option>
                </select>
              </div>
              <div class="fr">
                <form action="" class="form-inline">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="用户名称/编号/手机号" v-model="userParams.query">
                    <span class="input-group-btn">
									<button type="submit" class="btn bged145b" @click="queryUser">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </form>
              </div>
            </div>
            <ul class="ulDiv ovh mgt30">
              <li class="fl mgr30">
                <router-link :to="{name:'userAdd'}" class="db pdl29 corED145B ios2">新增用户</router-link>
              </li>
            </ul>
            <div class="mgt30">
              <table class="tab2">
                <tbody>
                <tr>
                  <td>用户编号</td>
                  <td>用户名</td>
                  <td>姓名</td>
                  <td>手机号</td>
                  <td>性别</td>
                  <td>所属组织</td>
                  <td>所属角色</td>
                  <td>创建时间</td>
                  <td>状态</td>
                  <td>操作</td>
                </tr>
                <tr v-for="(item,index) in userList">
                  <td>{{ item.userId }}</td>
                  <td>{{ item.username }}</td>
                  <td>{{ item.name }}</td>
                  <td>{{ item.mobile }}</td>
                  <td>{{ item.sex ==0?'男':'女' }}</td>
                  <td>{{ item.orgInfoDO.orgName }}</td>
                  <td>{{ item.roleNames }}</td>
                  <td>{{ item.gmtCreate | formatDate("yyyy-MM-dd") }}</td>
                  <td>{{ item.status == 0?'启用':'禁用' }}</td>
                  <td>
                    <!--<a href="#" class="db btn btn-xs btn-primary">查看</a>-->
                    <!--<a href="#" class="db btn btn-xs mgt10 btn-success">编辑</a>-->
                    <a href="#" class="db btn btn-xs  btn-info" @click.prevent="forbiddenUser(item.userId)"
                       v-if="item.status == 0">禁用</a>
                    <a href="#" class="db btn btn-xs  btn-info" @click.prevent="enableUser(item.userId)"
                       v-if="item.status == 1">启用</a>
                    <a href="#" class="db btn btn-xs btn-danger" @click.prevent="deleteUser(item.userId)">删除</a>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </li>
          <!--角色管理-->
          <li class="dn">
            <div class="ovh mgt20">
              <div class="fr">
                <form action="" class="form-inline">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="角色名称/编号">
                    <span class="input-group-btn">
									<button type="submit" class="btn bged145b">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                  </div>
                </form>
              </div>
            </div>
            <div class="mgt30">
              <table class="table table-bordered t-c">
                <tbody>
                <tr>
                  <td class="ww50 pdt20 pdb20">角色编号</td>
                  <td class="ww50 pdt20 pdb20">角色名称</td>
                </tr>
                <tr v-for="(item,index) in initRoleList">
                  <td class="pdt20 pdb20">{{ item.roleId }}</td>
                  <td class="pdt20 pdb20">{{ item.roleName }}</td>
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



  export default {
    name: "platformAdmin",
    data() {
      return {
        initOrgSelectList:[],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        orgParams: {orgId: 0, pageNum: 1, pageSize: 10, query: ''},
        userParams: {orgId:1, pageNum: 1, pageSize: 10, query: ''},
        roleParams:{roleId:1, pageNum: 1, pageSize: 10, query: ''},
        checkTab: 1, //页标签
        orgList: [],
        selectOrgList: [], //下拉组织列表
        userList: [],
        isplatformAdmin: true,  //是否是平台管理员，默认是
        pageData: {
          curPage: 1,//当前页吗
          totalCount: 1,//总条数
          pageSize: 1//页面大小
        },
        initRoleList: [],  //角色列表

        orgPageData:{},
        userPageData:{},
        rolePageData:{},

      }
    },

    created() {
      //获取用户信息
      this.configJS.getUserInfo(this);
      //判断是否是平台管理员
      var isplatformAdmin = this.configJS.isplatformAdmin();
      this.isplatformAdmin = isplatformAdmin;
      this.initOrgList();
      this.initOrgSelect();
      this.initUserList();
      initRoleList(this);
    },

    watch: {
      checkTab(newVal, oldVal) {
        check_Tab(this,newVal)
      }
    },

    mounted() {
      var $ul003 = $('.ul003>li');
      $ul003.click(function () {
        $(this).addClass('active').siblings().removeClass('active');
        var i = $ul003.index(this);
        $('.ul0004>li').eq(i).show().siblings('.ul0004>li').hide();
      });
    },

    methods: {
      /******* 分页**********/
      chekdpage: function (pageNum) {
        // orgParams: {orgId: 0, pageNum: 1, pageSize: 10, query: ''},
        // userParams: {orgId:1, pageNum: 1, pageSize: 10, query: ''},
        // roleParams:{roleId:1, pageNum: 1, pageSize: 10, query: ''},
        // checkTab: 1, //页标签
        if (this.checkTab == 1){
          this.orgParams.pageNum = pageNum;
          this.initOrgList();
        } else if(this.checkTab == 2){
          this.userParams.pageNum = pageNum;
          initUserList(this);
        }else if(this.checkTab == 3){
          this.roleParams.pageNum = pageNum;
          initRoleList(this);
        }
      },

      append(data) {
        this.$router.push({name:'platformOrgAdd'})
      },

      remove(node, data) {
        console.log(node);
        const orgId = node.id;
        deleteOrg(this,orgId);
      },




      /********** 用户模块 ************/
      /**
       *根据组织查询用户
       */
      orgChange() {
        var that = this;
        //获取当前选中组织Id
        var orgId = this.orgParams.orgId;
        //如果是平台管理员，只查询该平台管理员所创建的用户
        if (this.isplatformAdmin) {
          this.userParams.userIdCreate = this.configJS.userInfo.userId;
        }else {
          if(orgId == 0){
            orgId = this.configJS.userInfo.orgId
          }
        }
        this.userParams.orgId = orgId;
        var fun = function () {
          if (that.checkTab == 1){
            that.pageData.curPage = that.orgPageData.curPage;
            that.pageData.totalCount = that.orgPageData.totalCount;
            that.pageData.pageSize = that.orgPageData.pageSize
          } else if(that.checkTab == 2){
            that.pageData.curPage = that.userPageData.curPage;
            that.pageData.totalCount = that.userPageData.totalCount;
            that.pageData.pageSize = that.userPageData.pageSize
          }else {
            that.pageData.curPage = that.rolePageData.curPage;
            that.pageData.totalCount = that.rolePageData.totalCount;
            that.pageData.pageSize = that.rolePageData.pageSize
          }
        }
        initUserList(this,fun);

      },

      deleteUser(userId) {
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteUser(this, userId);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        })
      },

      forbiddenUser(userId) {
        forbiddenUser(this, userId)
      },

      enableUser(userId) {
        enableUser(this, userId);
      },

      /**
       *搜索框搜素用户
       */
      queryUser() {
        initUserList(this);
      },

      /**
       *加载默认您用户列表
       */
      initUserList() {
        var that = this;
        //获取当前登录人userId
        var orgId = that.configJS.userInfo.orgId;
        if (that.isplatformAdmin){
          that.userParams.userIdCreate = that.configJS.userInfo.userId;
        } else{
          that.userParams.orgId = orgId;
        }
        initUserList(that);
      },


      /********** 组织模块 ************/
      initOrgList(){
        if (this.isplatformAdmin){
          initTopOrgData(this);
        } else{
          initOrgList(this);
        }
      },

      initOrgSelect(){
        if (this.isplatformAdmin){
          initOrgDropDown(this);
        } else{
          initOrgSelect(this);
        }
      },


      queryOrg() {
        initTopOrgData(this);
      },

      deleteOrg(id) {
        this.$confirm('此操作将永久删除该组织, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteOrg(this, id);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        })
      },
    }
  };

  /****************   用户模块  **************************/
  /**
   * 加载用户列表
   * @param that
   * @param orgId
   */
  function initUserList(that,fun) {
    that.$axios({
      url: '/biz/tob-user/system/userInfo/list',
      // url:'/shiro/system/userInfo/list',
      params: that.userParams
    }).then((res) => {
      console.log("userInfo",res.data);
      var initUserList = res.data.rows;
      for(var index in initUserList){
          var roleNames = "";
          var roles = initUserList[index].roles;
          for(var i in roles){
            roleNames += roles[i].roleName +","
          }
        roleNames = roleNames.substring(0,roleNames.length-1);
        initUserList[index].roleNames = roleNames
      }
      that.userList = initUserList;
      that.userPageData.totalCount = res.data.total;//总条数
      that.userPageData.curPage = that.userParams.pageNum;//当前页码
      that.userPageData.pageSize = that.userParams.pageSize;//页面大小
      var fun_type = typeof fun;
      if (fun_type == "function"){
        fun();
      }
    })
  }

  function deleteUser(that, userId) {
    that.$axios({
      url: '/biz/tob-user/system/userInfo/remove',
      // url:'/shiro/system/userInfo/remove',
      method: 'post',
      params: {
        id: userId
      }
    }).then((res) => {
      console.log(res);
      if (res.data.code == 200) {
        that.$message({
          type: 'success',
          message: '删除成功!',
          duration: 1000,
          onClose() {
            initUserList(that);
          }
        });
      } else {
        that.$message({
          type: 'error',
          message: '服务器异常，请稍后再试!'
        })
      }
    })
  }

  /**
   * 禁用用户
   */
  function forbiddenUser(that, userId) {
    that.$axios({
      url: '/biz/tob-user/system/userInfo/forbiddenUser',
      // url:'/shiro/system/userInfo/forbiddenUser',
      method: 'post',
      params: {
        userId: userId
      }
    }).then((res) => {
      console.log(res)
      if (res.data.code == 200) {
        that.$message({
          type: 'success',
          message: '禁用成功!',
          duration: 1000,
          onClose() {
            initUserList(that);
          }
        })
      }
    })
  }

  /**
   * 启用用户
   */
  function enableUser(that, userId) {
    that.$axios({
      url: '/biz/tob-user/system/userInfo/enableUser',
      // url:'/shiro/system/userInfo/enableUser',
      method: 'post',
      params: {
        userId: userId
      }
    }).then((res) => {
      console.log(res)
      if (res.data.code == 200) {
        that.$message({
          type: 'success',
          message: '启用成功!',
          duration: 1000,
          onClose() {
            initUserList(that);
          }
        })
      }
    })
  }


  /****************   角色模块***********************/
  function initRoleList(that) {
    var roleParams = that.roleParams;
    roleParams.roleId = that.configJS.userInfo.roles[0].roleId;
    that.$axios({
      url: '/biz/tob-user/system/roleInfo/list',
      params:roleParams
    }).then( res =>{
      console.log('initRoleCheckBox',res);
      if (res.status == 200) {
        var initRoleList = res.data.rows;
        that.initRoleList = initRoleList;
        that.rolePageData.totalCount = res.data.total;//总条数
        that.rolePageData.curPage = that.roleParams.pageNum;//当前页码
        that.rolePageData.pageSize = that.roleParams.pageSize;//页面大小
      }
    })
  }

  /****************   组织模块  **************************/
  /**
   * 加载组织下拉框(不分页)
   * 使用与平台管理员
   * @param that
   */
  function initOrgDropDown(that) {
    that.$axios({
      url: '/biz/tob-user/system/orgInfo/initOrgDropDown',
      // url:'/shiro/system/orgInfo/initOrgDropDown',
    }).then((res) => {
      console.log(res);
      if (res.data.code == 0) {
        var initOrgDropDown = res.data.orgList;
        that.selectOrgList = initOrgDropDown;
      }
    })
  }

  /**
   * 加载组织下拉框
   * 适用于管理员
   */
  function initOrgSelect(that) {
    that.$axios({
      url: '/biz/tob-user/system/orgInfo/list',
      // url:'/shiro/system/orgInfo/list',
      params:{
        orgId:that.configJS.userInfo.orgId,
      }
    }).then((res) => {
      console.log("list",res);
      if(res.status == 200){
        that.initOrgSelectList = res.data;
      }
    })
  }


  /**
   * 查询组织列表(分页)
   * 适用于平台管理员
   * @param that
   */
  function initTopOrgData(that) {
    that.$axios({
      url: '/biz/tob-user/system/orgInfo/findTopParentOrgList',
      params: that.orgParams,
    }).then((res) => {
      console.log("initTopOrgData",res);
      var topOrgList = res.data;
      that.orgList = topOrgList.rows;
      //默认首次分页
      that.pageData.totalCount = res.data.total;//总条数
      that.pageData.curPage = that.orgParams.pageNum;//当前页码
      that.pageData.pageSize = that.orgParams.pageSize;//页面大小

      that.orgPageData.totalCount = res.data.total;//总条数
      that.orgPageData.curPage = that.orgParams.pageNum;//当前页码
      that.orgPageData.pageSize = that.orgParams.pageSize;//页面大小
    })
  }

  /**
   * 查询组织列表(分页)
   * 适用于公司管理员
   * @param that
   */
  function initOrgList(that) {
    var orgParams = that.orgParams;
    orgParams.orgId = that.configJS.userInfo.orgId;
    that.$axios({
      url: '/biz/tob-user/system/orgInfo/findOrgList',
      // url:'/shiro/system/orgInfo/findOrgList',
      params: orgParams,
    }).then((res) => {
      console.log("initOrgList",res);
      var topOrgList = res.data;
      that.orgList = topOrgList.rows;
      //默认首次分页
      that.pageData.totalCount = res.data.total;//总条数
      that.pageData.curPage = that.orgParams.pageNum;//当前页码
      that.pageData.pageSize = that.orgParams.pageSize;//页面大小

      that.orgPageData.totalCount = res.data.total;//总条数
      that.orgPageData.curPage = that.orgParams.pageNum;//当前页码
      that.orgPageData.pageSize = that.orgParams.pageSize;//页面大小
    })
  }



  /**
   * 删除组织
   * @param that
   * @param orgId
   */
  function deleteOrg(that, orgId) {
    that.$axios({
      url: '/biz/tob-user/system/orgInfo/remove',
      method: 'post',
      params: {
        id: orgId
      },
    }).then((res) => {
      if (res.data.code == 200) {
        that.$message({
          type: 'success',
          message: '删除成功!',
          duration: 1000,
          onClose() {
            if(that.isplatformAdmin){
              initTopOrgData(that)
            }else {
              initOrgList(that)
            }
          }
        });
      } else {
        that.$message({
          type: 'error',
          message: '服务器异常，请稍后再试!'
        })
      }
    }).catch((err) => {
      that.$message({
        type: 'error',
        message: '服务器异常，请稍后再试!'
      })
    })
  }

  function check_Tab(that,checkTab){
    if (checkTab == 1){
      that.pageData.curPage = that.orgPageData.curPage;
      that.pageData.totalCount = that.orgPageData.totalCount;
      that.pageData.pageSize = that.orgPageData.pageSize
    } else if(checkTab == 2){
      that.pageData.curPage = that.userPageData.curPage;
      that.pageData.totalCount = that.userPageData.totalCount;
      that.pageData.pageSize = that.userPageData.pageSize
    }else {
      that.pageData.curPage = that.rolePageData.curPage;
      that.pageData.totalCount = that.rolePageData.totalCount;
      that.pageData.pageSize = that.rolePageData.pageSize
    }
  }


</script>

<style scoped>

</style>
