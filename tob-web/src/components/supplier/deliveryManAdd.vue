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
          <p class="fl fs18 cor0">新增送货人员</p>
          <a href="###" class="db fr fs18 cor0">返回>></a>
        </div>
        <div class="mgt20 bor-d pdt30 pdb120">
          <div class="w600 c-b">
            <form action="#">
              <div class="ovh">
                <div class="fl w70 fs16 cor0 h40 lh40">编号：</div>
                <div class="mgl70"><input disabled value="自动生成" class="db ww100 h40 lh40 bor-d" type="text"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w70 fs16 cor0 h40 lh40">姓名：</div>
                <div class="mgl70"><input v-model="deliInstManDto.name" class="db ww100 h40 lh40 bor-d" type="text"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w70 fs16 cor0 h40 lh40">性别：</div>
                <div class="mgl70">
                  <select v-model="deliInstManDto.sex" class="db ww100 h40 lh40 pdl15 pdr15 ios0 bor-d">
                    <option value="0">男</option>
                    <option value="1">女</option>
                  </select>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w70 fs16 cor0 h40 lh40">手机号：</div>
                <div class="mgl70"><input @blur="isPhone" v-model="deliInstManDto.phone_num" class="db ww100 h40 lh40 bor-d" type="text"></div>
              </div>
              <div class="mgt200 t-c">
                <input type="button" :disabled="isDisabled" @click="addDeliveryman()" class="w100 btn btn-primary" value="保存">
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
  import config from '../../common/config'
  export default {
    name: "deliveryManAdd",
    data (){
      return {
        isDisabled:false,
        deliInstManDto : {
          supplier_id:config.userInfo.orgId,//供应商id
          dimNo : '',
          type : 0,
          name : '',
          sex  : 0,
          phone_num : ''
        },isPhones:true
      }
    },created(){
      if(this.deliInstManDto.supplier_id==1){
        this.deliInstManDto.supplier_id=38
      }
    },
    methods : {
      addDeliveryman : function () {
        if(this.deliInstManDto.name==null||this.deliInstManDto.name==''){
          this.$message("姓名不能为空")
          return;
        }
        if(!(/^1[34578]\d{9}$/.test(this.deliInstManDto.phone_num))){
          this.$message("手机号码有误，请重填");
          return;
        }
        if(!this.isPhones){
          this.$message("该手机已经被注册")
          return
        }
        this.isDisabled=true
        console.log(JSON.stringify(this.deliInstManDto))
        this.$axios({
          method : 'post',
          // headers:{
          //   'Content-Type': 'application/json;charset=UTF-8'  //这里加上头部信息
          // },
          url:'/biz/tob-supplier/com/supplier/addDdelivery',
          // params : {'delivery':JSON.stringify(this.deliInstManDto)}
          params : this.deliInstManDto
        }).then((res) => {
          if(res.data.code==200){
            this.$alert("添加成功")
            this.$router.push({name:'basic'})
          }
          console.log(res)

        }).finally(()=>{
          this.isDisabled=false
        })
      },isPhone(){
        isPhone(this)
      }
    }
  }
  function isPhone(_this){
    if(!(/^1[34578]\d{9}$/.test(_this.deliInstManDto.phone_num))){
      return;
    }
    _this.isPhones=true;
    _this.$axios({
      url:'/biz/tob-supplier/com/supplier/getDeliveryByPhone'
      ,params:{'phone':_this.deliInstManDto.phone_num}
    }).then((ref)=>{
      if(ref.data.code==200){
        _this.isPhones=false
        _this.$message("该手机已经被注册")
      }
    })
  }
</script>

<style scoped>

</style>
