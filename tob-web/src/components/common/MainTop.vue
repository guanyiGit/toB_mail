<template>
  <!--头部-->
  <div class="ww100 header">
    <div class="bgf2 pdl15 pdr15">
      <div class="w1200 c-b mm100 cor9 h40 lh40">欢迎进入采购商城后台管理系统！      客服热线：400-888-888</div>
    </div>
    <div class="bgdf pdl15 pdr15">
      <div class="w1200 h70 c-b mm100 pdt15 pdb15">
        <ul class="ul001 fr h40">
          <li class="fl h40 pdt5">
            <a href="javascript:void(0);" class="db h30 lh30 cor3">
              <span><img src="/static/images/2.png" alt=""></span>
              <span>{{ userInfo?userInfo.name:'' }}({{ userInfo && userInfo.roles && userInfo.roles.length > 0?userInfo.roles[0].roleName:'' }}）</span>
            </a>
          </li>
          <li class="fl h40 rel pdt5">
            <a href="javascript:void(0);" class="db h30 lh30 cor3">账号管理</a>
            <div class="abs liDiv t-c bor-d6 z1000 bgw dn time05">
              <a  class="db w150 h40 lh40 bor-b-d6 cor9 xiu" @click.prevent="showTab">修改密码</a>
              <a href="###" class="db w150 h40 lh40 bor-b-d6 cor9 deng" @click.prevent = "logout">退出登录</a>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <!--修改密码弹窗-->
    <form @submit.prevent="updataPassword" id="password" class="dn"  style="z-index: 200000">
      <div class="password">
        <div class="pass">
          <div class="h70 lh70 pdl30 pdr20 cor0 bgf1">
            原密码：<a href="###" class="dinb fr close2" @click.prevent = "closeTab"><img src="/static/images/32.png" alt=""></a>
          </div>
          <div class="pdl155 pdr155 pdt10 pdb30">
            <div class="mgt20 ovh">
              <span class="db fl w90 t-r cor0 h40 lh40">原密码：</span>
              <div class="mgl95"><input type="password" class="db ww100 h40 lh40 bor-d pdl10" placeholder="" v-model="updatePassword.oldPassword"></div>
            </div>
            <div class="mgt20 ovh">
              <span class="db fl w90 t-r cor0 h40 lh40">新密码：</span>
              <div class="mgl95"><input type="password" class="db ww100 h40 lh40 bor-d pdl10" placeholder="" v-model="updatePassword.newPassword"></div>
            </div>
            <div class="mgt20 ovh">
              <span class="db fl w90 t-r cor0 h40 lh40">确认新密码：</span>
              <div class="mgl95"><input type="password" class="db ww100 h40 lh40 bor-d pdl10" placeholder="" v-model="updatePassword.checkPassword" @blur="checkPasswordEvent"></div>
            </div>
            <div class="mgt40 t-c">
              <input type="submit" class="w100 btn btn-primary" value="保存">
              <input type="button" class="w100 pdr30 btn btn-danger" value="取消" @click="cancel">
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>


</template>

<script>

  // $(function(){
  //   $('.xiu').click(function(){
  //     $('#password').slideDown();
  //   });
  //   $('.close2').click(function(){
  //     $('#password').slideUp();
  //   });
  // });



  export default {
    name: "MainTop",
    data(){
      return {
        userInfo:{},
        updatePassword:{
          oldPassword:"",
          newPassword:"",
          checkPassword:"",
        }
      }
    },

    created(){
      this.configJS.getUserInfo(this);
      this.userInfo = this.configJS.userInfo;
    },

    methods:{
      showTab(){
        $('#password').slideDown();
      },

      closeTab(){
        $('#password').slideUp();
      },

      /**
       * 检查两次输入密码是否一致
        */
      checkPasswordEvent(){
        var that = this;
        var newPassword = this.updatePassword.newPassword;
        var checkPassword = this.updatePassword.checkPassword;
        if (newPassword && checkPassword){
          if (newPassword != checkPassword){
            that.$message({
              type: 'error',
              message: '两次输入密码不一致!',
              duration: 1000,
            })
          }
        }
      },

      cancel(){
        this.closeTab();
      },

      updataPassword(){
        debugger
        var that = this;
        that.$axios({
          url:'/biz/tob-user/system/userInfo/resetPassword',
          params:{
            userId:this.configJS.userInfo.userId,
            oldPassword:this.updatePassword.oldPassword,
            newPassword:this.updatePassword.newPassword,
          }
        }).then(res => {
          console.log("resetPassword",res);
          if(res.data.code == 200){
            that.closeTab();
            that.$confirm("修改密码成功,请重新登录", "修改密码提示",{
              confirmButtonText: '确定',
              showCancelButton:false,
              type: 'success ',
            }).then( () =>{
              //退出当前用户并跳转到登录页
              that.logout();
            } )
          }else if (res.data.code == 501){
            that.$message({
              type: 'error',
              message: '原密码错误',
              duration: 1000,
              onClose(){
                that.closeTab();
              }
            })
          }
        })
      },

      logout(){
        this.$axios({
          url:"/biz/logout"
        }).then((res) =>{
          console.log(res);
          if(res.data.code == 200){
            //登出成功，清空内存
            this.configJS.userInfo="";
            this.configJS.authorization ="";
            //登出成功，删除本地用户信息和authorization
            localStorage.removeItem("userInfo");
            localStorage.removeItem("authorization");
            //跳转到登录页
            this.$router.push({name:"login"})
          }
        });
      }
    }
  }


</script>

<style scoped>
  .w1300 {
    width: 1300px;
  }
</style>
