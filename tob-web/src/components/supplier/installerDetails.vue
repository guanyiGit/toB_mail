<template>
  <div class="wrap ww100 pdt100">
    <!--头部-->
    <div class="ww100 header">
      <div class="bgf2 pdl15 pdr15">
        <div class="w1200 c-b mm100 cor9 h40 lh40">欢迎进入xx商城后台管理系统！      客服热线：400-888-888</div>
      </div>
      <div class="bgdf pdl15 pdr15">
        <div class="w1200 c-b mm100 ovh pdt15 pdb15">
          <ul class="ul001 fr">
            <li class="fl">
              <a href="###" class="db cor3">
                <span><img src="images/2.png" alt=""></span>
                <span>张三(平台管理员）</span>
              </a>
            </li>
            <li class="fl">
              <a href="###" class="db cor3">我的消息</a>
            </li>
            <li class="fl">
              <a href="###" class="db cor3">退出登录</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!--头部END-->

    <div class="ww100 pdl15 pdr15 pdb130">
      <div class="w1200 c-b mm100 mgt30">
        <div class="ovh pdb10 bor-b-d">
          <p class="fl fs18 cor0">查看安装人员</p>
          <a href="###" class="db fr fs18 cor0">返回>></a>
        </div>
        <div class="mgt20 bor-d pdt30 pdb120">
          <div class="w600 c-b">
            <form action="#">
              <div class="ovh">
                <div class="fl w70 fs16 cor0 h40 lh40">编号：</div>
                <div class="mgl70">
                  <input class="db ww100 h40 lh40 bor-d" type="text" v-if="deliveryMan&&deliveryMan.id" v-model="deliveryMan.id">
                  <input class="db ww100 h40 lh40 bor-d" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w70 fs16 cor0 h40 lh40">姓名：</div>
                <div class="mgl70">
                  <input class="db ww100 h40 lh40 bor-d" type="text" v-if="deliveryMan&&deliveryMan.name" v-model="deliveryMan.name">
                  <input class="db ww100 h40 lh40 bor-d" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w70 fs16 cor0 h40 lh40">性别：</div>
                <div class="mgl70">
                  <input class="db ww100 h40 lh40 bor-d" type="text"v-if="deliveryMan&&deliveryMan.sex==0" value="男">
                  <input class="db ww100 h40 lh40 bor-d" type="text" v-else value="女">
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w70 fs16 cor0 h40 lh40">手机号：</div>
                <div class="mgl70">
                  <input class="db ww100 h40 lh40 bor-d" type="text" v-if="deliveryMan&&deliveryMan.phone_num" v-model="deliveryMan.phone_num">
                  <input class="db ww100 h40 lh40 bor-d" type="text" v-else>
                </div>
              </div>
              <div class="mgt200 t-c">
                <!--<input type="button" @click="addDeliveryman()" class="w100 btn btn-primary" value="保存">-->
                <a href="javascript:"class="w100 pdr30 btn btn-danger" @click="$router.back(-1)">取消</a>
                <!--<input type="button" class="w100 pdr30 btn btn-danger" value="取消">-->
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  export default {
    name: "installerDetails",
    data (){
      return {
        deliveryMan : {}
      }
    },methods:{

    },created(){
      initDate(this);
    }
  }
  //得到送货员对象
  function initDate(_this){
    _this.$axios({
      url:'/biz/tob-supplier/com/supplier/getDeliveryDetails'
      ,params:{id:_this.$route.params.id}
    }).then((ref)=>{
      if(ref.data.code==200){
        _this.deliveryMan=ref.data.delivery;
        console.log("**"+JSON.stringify(ref.data))
      }
    })
  }
</script>

<style scoped>

</style>
