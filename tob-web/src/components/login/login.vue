<template>
  <div class="wrap2">
    <div id="login" class="rel">
      <form @submit.prevent="submit">
        <div class="w1200 c-b login mm100 ovh" >
          <div class="fr w400 bgw pdb110">
            <div class="pdt50 pdl40 pdr40 tb11 ovh">
              <a href="#" class="db fl ww50 rel fs18 cor9 t-c pdb15">手机动态密码登录<span></span></a>
              <a href="#" class="db fl ww50 rel fs18 cor9 t-c pdb15 active">普通登录<span></span></a>
            </div>
            <div class="mgt90">
              <div class="tb12 dn" >
                <div class="h56 bgf9 pdl30 pdr30"><input type="text" class="db ww100 bort bgt h56 lh56 cor9 in1" placeholder="手机号"></div>
                <div class="h56 bgf9 pdl30 pdr30 mgt3 ovh">
                  <input type="text" class="db fl w190 bort bgt h56 lh56 cor9 in1" placeholder="动态密码">
                  <a href="#" class="db fr w120 h30 lh30 bored145b mgt12 fs12 cored145b t-c">获取手机动态密码</a>
                </div>
              </div>
              <div class="tb12 db">
                <div class="h56 bgf9 pdl30 pdr30"><input type="text" class="db ww100 bort bgt h56 lh56 cor9 in1" placeholder="用户名" v-model="username"></div>
                <div class="h56 bgf9 pdl30 pdr30 mgt3 ovh">
                  <input type="password" class="db ww100 bort bgt h56 lh56 cor9 in1" placeholder="密码" v-model="password">
                  <!--<input type="text" class="db fl w190 bort bgt h56 lh56 cor9 in1" placeholder="密码">-->
                  <!--<a href="#" class="db fr w120 h30 lh30 bored145b mgt12 fs12 cored145b t-c">获取手机动态密码</a>-->
                </div>
              </div>
            </div>
            <div class="mgt78 w288 c-b">
              <input type="submit"  class="db ww100 h46 lh46 bort bged145b corw fs16" value="登录">
              <div class="mgt12 t-l">
                <div class="lab3 cor9" for="autoLogin">
                  <input id="autoLogin" name ="tesa" type="checkbox" checked><i></i>自动登录
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
      <!--底部-->
      <div class="bg333 fs14 corw mgt90">
        <div class="w1200 c-b mm100 ovh foot1">
          <a href="###" class="db h70 lh70 fl corw mgr30 hov7">关于我们</a>
          <a href="###" class="db h70 lh70 fl corw mgr30 hov7">购物流程</a>
          <a href="###" class="db h70 lh70 fl corw mgr30 hov7">配送方式</a>
          <a href="###" class="db h70 lh70 fl corw mgr30 hov7">支付方式</a>
          <a href="###" class="db h70 lh70 fl corw mgr30 hov7">售后流程</a>
          <a href="###" class="db h70 lh70 fl corw mgr30 hov7">特色服务</a>
        </div>
      </div>
      <div class="bg3d pdt30 pdb65 fs14 corw">
        <div class="w1200 c-b mm100 ovh foot2">
          <p class="fs12 corw">Copyright © 2010-2020 XXXXXXXXX公司  XXXXXX.com  保留一切权利。客服热线：400-888-8888</p>
          <p class="fs12 corw mgt5">京公网安备 1101010200111111 号 | 京ICP证111111号 | 食品流通许可证 SP110105111111111（1-1） | 营业执照 | 公示制度</p>
          <div class="mgt20 ovh">
            <a href="###" class="db fl mgr10 hov7"><img src="images/24-1.png" alt=""></a>
            <a href="###" class="db fl mgr10 hov7"><img src="images/24-2.png" alt=""></a>
            <a href="###" class="db fl mgr10 hov7"><img src="images/24-3.png" alt=""></a>
            <a href="###" class="db fl mgr10 hov7"><img src="images/24-4.png" alt=""></a>
            <a href="###" class="db fl mgr10 hov7"><img src="images/24-5.png" alt=""></a>
            <a href="###" class="db fl mgr10 hov7"><img src="images/24-2.png" alt=""></a>
            <a href="###" class="db fl mgr10 hov7"><img src="images/24-3.png" alt=""></a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "login",
      data(){
          return {
            username:"",
            password:""
          }
      },
      mounted() {
        // var $ul003 = $('.ul003>li');
        // $ul003.mouseover(function () {
        //   $(this).addClass('active').siblings().removeClass('active');
        //   var i = $ul003.index(this);
        //   $('.ul0004>li').eq(i).show().siblings('.ul0004>li').hide();
        // });
      },

      created(){

      },

      methods:{
        /**
         * 登录提交
         */
        submit(){
          var that = this;
          that.$axios({
            url:'/biz/login',
            method:'post',
            params:{
              username:that.username,
              password:that.password,
            }
          }).then((res) => {
            if(res.data.code == 200){
              var Authorization = res.headers.authorization;
              if( !Authorization ){
                that.$router.push({name:getRoleIndex(userInfo.roles[0].roleId)});
                return;
              }
              var userInfo = res.data.obj.userInfo;
              localStorage.setItem("userInfo",JSON.stringify(userInfo));
              localStorage.setItem("authorization",Authorization);
              //将userInfo信息放入configjs
              that.configJS.setUserInfo(userInfo);
              //将Authorization信息放入configjs
              that.configJS.setAuthorization(Authorization);
              //根据用户角色跳转到对应首页
              var indexUrl =  getRoleIndex(userInfo.roles[0].roleId);
              that.$router.push({name:indexUrl});
            }else if (res.data.code == 511){
               that.$message({
                 type: 'error',
                 message: '账号不存在!',
                 duration: 1000,
               })
            }else if (res.data.code == 512){
              that.$message({
                type: 'error',
                message: '账号被禁用!',
                duration: 1000,
              })
            }else if(res.data.code == 513){
              that.$message({
                type: 'error',
                message: '账号或密码错误!',
                duration: 1000,
              })
            }
          })
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

</script>

<style scoped>
input[type] {
    padding-left: 10px;
}
#bormy{
  border: solid 1px #ccc;
}
</style>
