<template>

  <div id="login" class="fixed">
    <div class="login">
      <div>
        <div class="ovh">
          <img class="c-b" src="images/1.png" alt="">
        </div>
        <form action="#" class="mgt40">
          <el-tooltip class="item" effect="dark" :content.sync="userNameMsg" placement="right-start"
                      popper-class="test">
            <p class="mgt10"><input class="db ww100 h50 lh50 pdl65 ras6 borbfc695 pc21" type="text" v-model="userName"
                                    placeholder="请输入用户名"></p>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" :content.sync="passwordMsg" placement="right-start">
            <p class="mgt10"><input class="db ww100 h50 lh50 pdl65 ras6 borbfc695 pc22" type="password"
                                    v-model="password" placeholder="请输入密码"></p>
          </el-tooltip>

          <!--<a href="###" class="mgt25 db corfffefe pdl40 hov8">忘记密码</a>-->
          <p class="mgt35"><input class="formsub db ww100 h58 lh60 sub1 ras10 fs24 corw t-c" type="submit"
                                  @click.prevent="login" value="登录">
          </p>
        </form>
      </div>
    </div>
  </div>

</template>

<script>
  import configJS from '../../common/config'

  export default {
    name: "login",
    data() {
      return {
        userName: null,
        userNameMsg: "请输入用户名",
        userNameIs: false,
        password: null,
        passwordMsg: "请输入密码",
        passwordIs: false
      }
    },
    watch: {
      userName(newVal, oldVal) {
        this.userNameIs = false;
        if (!newVal || newVal.trim().length == 0) {
          this.userNameMsg = "用户名不能为空";
        } else {
          this.userNameMsg = "√"
          this.userNameIs = true;
        }
      },
      password(newVal, oldVal) {
        this.passwordIs = false;
        let reg = /^[a-zA-Z\d_]{5,20}$/;
        if (!newVal || newVal.trim().length == 0) {
          this.passwordMsg = "密码不能为空";
        }
        if (newVal.trim().length <= 5) {
          this.passwordMsg = "密码长度不够";
        } else if (!reg.test(newVal)) {
          this.passwordMsg = "密码格式有误，不能以数组开头";
        } else {
          this.passwordMsg = "√";
          this.passwordIs = true;
        }
      }
    },
    beforeCreate() {
      //如果存在用户信息到首页
      if (configJS.userInfo && configJS.userInfo.userId) {
        toIndex(this)
      }
    },
    methods: {
      //登录
      login() {
        if (this.userNameIs && this.passwordIs) {
          this.$axios({
            // axios({
            url: '/biz/login',
            method: "post",
            params: {
              "username": this.userName,
              "password": this.password
            },
            isloading:true
          }).then((res) => {
            if (res.data.status == 200) {
              if (res.data.data) {
                //登录成功将用户信息放入全局
                configJS.userInfo = res.data.data;
                // try {
                  // localStorage.setItem("user_info_token", JSON.stringify(res.data.data))
                // } catch (e) {
                // }
              }
              //获取资源信息
              this.$axios({
                url: "/biz/resource/getResources",
              }).then(res => {
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

                  try {
                    //用户资源信息
                    // localStorage.setItem("resource_info_token", JSON.stringify(configJS.resource));
                    // localStorage.setItem("manager_dog_user_id", configJS.userInfo);
                  } catch (e) {
                  }

                  // console.log(configJS.resource.memu)
                  //跳转到首页
                  toIndex(this)
                }
              })

            } else {
              this.$alert(res.data.msg, "登录提示")
            }
          }).catch(_ => {
          })
        } else {
          this.$alert(this.userNameMsg != "√" ? "用户名格式不正确！" : "密码格式不正确", "登录提示")
        }
      }
    }
  }

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

  //根据用户信息跳转到其首页
  function toIndex(_this) {
    //用户角色
    try {
      //用户已经登录，并且有首页权限
      let toUrl = 'index';
      configJS.resource.memu.map(item => {
        if (item.seq == 1 && item.level == 2) {
          toUrl = item.link;
        }
      })
      _this.$router.push({name: toUrl});

      // let roleId = configJS.userInfo.tRoles[0].roleId;
      // if (roleId == 1) {//犬只办
      //   _this.$router.push({name: "dogManager_index"});
      // } else if (roleId == 2) {//收容所
      //   _this.$router.push({name: "index"});
      // } else if (roleId == 3) {//犬主
      //   _this.$router.push({name: "index"});
      // } else if (roleId == 4) {//宠物医院
      //   _this.$router.push({name: "index"});
      // } else if (roleId == 5) {//运营方
      //   _this.$router.push({name: "operator_index"});
      // } else if (roleId == 6) {//系统管理员
      //   _this.$router.push({name: "index"});
      // } else {//游客首页
      //   _this.$router.push({name: "index"});
      // }
    } catch (e) {
      _this.$router.push({name: "index"});
    }
  }
</script>

<style scoped>
  .formsub {
    cursor: pointer;
  }
</style>
