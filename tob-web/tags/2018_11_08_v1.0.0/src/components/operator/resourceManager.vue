<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="ovh div1 h50 rel">
      <p class="abs ww100 left0 bottom0"></p>
      <a style="cursor:pointer;" class="fl db fs16 cor3c3c3c w200 h50 lh50 t-c active">用户</a>
      <a style="cursor:pointer;" class="fl db fs16 cor3c3c3c w200 h50 lh50 t-c">角色</a>
    </div>
    <div class="div12 db mgt20">
      <div class="ovh">
        <div class="fl cor6 h40 lh40">
          <router-link :to="{ name:'operator_add_user' }" class="db fl hov8 corffa472 mgr35 pdl36 pc9">新增用户
          </router-link>
          <a style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pdl36 pc10" @click="deleteUser">删除用户</a>
          <a style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pdl36 pc25 t-show" @click="edtRole">分配角色</a>
          <a style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pdl36 pc26" v-on:click="enable">启用</a>
          <a style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pdl36 pc27" @click="disable">禁用</a>
        </div>
        <div class="fr db w380 h40 borbfbfbf ras20 rel">
          <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="名称/电话" v-model="query">
          <input type="submit" value="" class="in1 db abs z100 top0 bort right0" @click="searchUser">
        </div>
      </div>
      <div class="ovh k3 mgt20">
        <table class="table11" width="100%">
          <tbody>
          <tr>
            <td>
              <label class="lab3 mgr40"><input type="checkbox" v-model="checkAll"><i></i></label>
            </td>
            <td>用户编号</td>
            <td>用户名称</td>
            <td>联系方式</td>
            <td>机构类型</td>
            <td>机构名称</td>
            <td>所属角色</td>
            <td>创建时间</td>
            <td>是否启用</td>
          </tr>
          <tr v-for="(item,index) in list" :key="index">
            <td>
              <label class="lab3 mgr40"><input type="checkbox" v-model="item.isCheckd"><i></i></label>
            </td>
            <td>{{ item.userId }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.phone }}</td>
            <td>{{ item.orgTypeName }}</td>
            <td>{{ item.orgName }}</td>
            <td>{{ item.roleName }}</td>
            <td>{{ item.createTime |formatDate(pattern) }}</td>
            <td>{{ item.uStatus == 1?"是":"否" }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="div12 dn mgt20">
      <div class="ovh">
        <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
          <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="角色名称" v-model="query">
          <input type="submit" value="" class="in1 db abs z100 top0 bort right0">
        </form>
      </div>
      <div class="ovh k3 mgt20">
        <table class="table11" width="100%">
          <tbody>
          <tr>
            <td>角色编号</td>
            <td>角色名称</td>
            <td>创建时间</td>
            <td>是否启用</td>
          </tr>
          <tr v-for="(item,index) in roleList" :key="index">
            <td>{{ item.roleId }}</td>
            <td>{{ item.roleName }}</td>
            <td>{{ item.creationTime|formatDate(pattern) }}</td>
            <td>{{ item.rStatus==1?"是":"否" }}</td>
            <!--<td>-->
            <!--<a href="###" class="dinb hov8 cor009cff mgr14">查看权限</a>-->
            <!--</td>-->
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <el-dialog title="角色分配" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="edtBox">
        <el-form-item label="所选用户 " :label-width="formLabelWidth">
          <el-input :value="uNames" autocomplete="off" class="text" disabled></el-input>
        </el-form-item>
        <el-form-item label="分配角色 " :label-width="formLabelWidth">
          <el-select v-model="edtBox.role" placeholder="请选择要给用户分配的角色" class="text">
            <el-option :label="item.roleName" :value="item" v-for="(item,index) in roleList" :key="index"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRoles">确 定</el-button>
      </div>
    </el-dialog>

    <!--分页-->
    <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>

  </div>
  <!--内容区END-->
</template>

<script>

  //请求参数pageData
  let params = {
    pageSize: 9,
    pageNum: 1,
    query: null,
  }

  export default {
    name: "resourceManager",
    data() {
      return {
        pattern: 'yyyy年MM月dd日',
        checkTab: 1,//页签tab
        list: [],  //用户列表
        roleList: [], //角色列表
        roleSubmit: [],//分配角色提交
        totalCount: 0,//总条数
        userIds: [],
        selectAll: [],
        query: null,//搜索关键字
        roleIds: [],
        checkAll: false,
        edtBox: {//角色编辑框
          users: [],//选择的用户
          role: {},//所选角色
        },
        dialogFormVisible: false,
        formLabelWidth: '80px'
      }
    },
    //vue 生命周期 在data加载 el未加载
    created() {
      initData(this);
      initRoleData(this);
    },
    mounted() {
      var $tab12 = $('.div1>a');
      $tab12.mouseover(function () {
        $(this).addClass('active').siblings().removeClass('active');
        var i = $tab12.index(this);
        $('.div12').eq(i).show().siblings('.div12').hide();
      });
    },
    watch: {
      query(newVal) {
        params.query = newVal;
        initData(this);
      },
      checkAll(newVal) {
        for (let item of this.list) {
          item.isCheckd = newVal;
        }
      }

    },

    computed: {
      //分页参数
      pageData() {
        return {
          curPage: params.pageNum,//当前页吗
          totalCount: this.totalCount,//总条数
          pageSize: params.pageSize//页面大小}
        }
      },
      uNames() {
        return this.edtBox.users.map(item => item.name).join()
      }
    },

    methods: {
      //分页
      chekdpage(currentPage) {
        params.pageNum = currentPage//页码
        initData(this)
      },

      //分配角色
      edtRole() {
        let temps = [];
        this.list.map(e => {
          if (e.isCheckd) temps.push(e)
        })
        if (temps.length == 0) {
          this.$alert("您还没有选择一条记录！")
          return;
        }
        this.dialogFormVisible = true;
        this.edtBox.users = temps;
      },
      //添加角色
      addRoles() {
        this.dialogFormVisible = false
        let temp = this.edtBox.users.map(item => {
          return {
            userId: item.userId,
            roleId: this.edtBox.role.roleId ? this.edtBox.role.roleId : 1
          }
        })
        this.$axios({
          url: '/biz/user/assignRole',
          method: 'post',
          headers: {
            'content-type': 'application/json'
          },
          isloading: true,
          data: JSON.stringify(temp)
        }).then(res => {
          if (res.data.status == 200) {
            this.alert("编辑成功！")
          } else {
            this.alert("编辑失败！")
          }
        })
      },

      /**
       * 启用
       */
      enable() {
        enable(this);
      },
      /**
       * 禁用
       */
      disable() {
        disable(this);
      },
      /**
       * 删除用户
       */
      deleteUser() {
        deleteUser(this);
      },

      /**
       * 搜索用户
       */
      searchUser() {
        params.query = this.query;
        initData(this);
      }
    },
  }

  /**
   * 加载列表
   * @param that
   */
  function initData(that) {
    if (!params.query)
      delete params.query

    that.$axios({
      url: "/biz/user/list",
      method: "post",
      params: params,
      isloading: true
    }).then(res => {
      if (res.data.status == 200) {
        //判断list是否有值
        var userList = res.data.data.list;
        if (userList && userList.length > 0) {
          for (let temp of userList) {
            temp.isCheckd = false;//默认未选中
          }
          that.totalCount = res.data.data.total;
          that.list = userList
        } else {
        }
      }
    })
  }

  /**
   *加载角色列表
   */
  function initRoleData(that) {
    that.$axios({
      url: "/biz/role/list",
      method: "post",
    }).then(res => {
      if (res.data.status == 200) {
        var roleList = res.data.data.list;
        that.roleList = roleList;
      }
    })
  }

  /**
   * 启用
   * @param userId
   */
  function enable(that) {
    let temps = [];
    that.list.map(e => {
      {
        if (e.isCheckd && e.uStatus == 0) temps.push(e.userId)
      }
    })
    if (temps.length == 0) {
      that.$alert("您还没有选择一个禁用状态用户！")
      return;
    }

    that.$axios({
      url: "/biz/user/updateStatuss",
      method: "post",
      params: {
        userIds: temps.join(),
        status: 1
      },
      isloading: true
    }).then(res => {
      if (res.data.status == 200) {
        that.$alert("启用成功！")
        initData(that);
      } else {
        that.$alert("启用失败！")
      }
    })
  }

  /**
   * 禁用
   * @param userId
   */
  function disable(that) {
    let temps = [];
    that.list.map(e => {
      {
        if (e.isCheckd && e.uStatus == 1) temps.push(e.userId)
      }
    })
    if (temps.length == 0) {
      that.$alert("您还没有选择一个启用状态用户！")
      return;
    }

    that.$axios({
      url: "/biz/user/updateStatuss",
      method: "post",
      params: {
        userIds: temps.join(),
        status: 0
      },
      isloading: true
    }).then(res => {
      if (res.data.status == 200) {
        that.$alert("禁用成功！")
        initData(that);
      } else {
        that.$alert("禁用失败！")
      }
    })
  }

  /**
   * 删除用户
   * @param that
   */
  function deleteUser(that) {
    let temps = [];
    that.list.map(e => {
      {
        if (e.isCheckd) temps.push(e.userId)
      }
    })

    if (temps.length == 0) {
      that.$alert("您还没有选择一个用户！")
      return;
    }

    that.$axios({
      url: "/biz/user/deletes",
      method: "post",
      params: {userIds: temps.join()},
      isloading: true,
    }).then(res => {
      if (res.data.status == 200) {
        that.$alert("删除成功");
        initData(that);
      } else {
        that.$alert("删除失败");
      }
    })

  }


</script>

<style scoped>
  .text {
    width: 300px;
  }
</style>
