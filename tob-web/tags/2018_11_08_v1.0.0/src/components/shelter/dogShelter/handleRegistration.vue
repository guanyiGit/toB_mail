<template>
    <!--处理登记-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="mbx">
        <ul class="ul11 clearfix">
          <li>
            <router-link style="cursor:pointer;" :to="{name:'dogShelter'}">犬只收容></router-link>
          </li>
          <li>处理登记</li>
        </ul>
      </div>
      <div class="mgt10 k3">
        <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">处理登记</div>
        <form action="#">
          <div class="t-c">
            <div class="ovh pdr30">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">处理时间</span>
                <div class="mgl100">
                  <!--<input id="inp3" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4 pc6">-->
                  <el-date-picker v-model="data.handleTime" type="date" class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                  placeholder="选择处理时间" :format="datePattern"></el-date-picker>
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">处理方式</span>
                <div class="mgl90">
                  <input v-model="data.handleWay" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                </div>
              </div>
            </div>
            <div class="ovh pdr30">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">处理原因</span>
                <div class="mgl100">
                  <input v-model="data.handleReason" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                </div>
              </div>
            </div>
            <div class="ovh pdr30">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">备注</span>
                <div class="mgl100">
                  <input v-model="data.remark" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                </div>
              </div>
            </div>


            <div class="t-c mgt40 mgb50">
              <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" @click="save">提交</a>
              <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5" @click="cancel">取消</a>
            </div>
          </div>

        </form>
      </div>
    </div>
    <!--内容区END-->
  </div>
</template>

<script>
  //处理登记
  import config from '../../../common/config.js'
    export default {
        name: "handleRegistration",
      data() {
        return {
          data:{
            collectionId:this.$route.params.collectionId,//收容id
            dogId:this.$route.params.dogId,//犬id
            handleReason:null,//处理原因
            handleTime:null,//处理时间
            remark:null,//处理备注
            handleWay:null,//处理方式
            operatorId:config.userInfo.userId//登陆用户id
          },
          datePattern:"yyyy年MM月dd日",
        }
      },
      created(){
        this.data.collectionId=this.$route.params.collectionId
        this.data.dogId=this.$route.params.dogId
      }
      ,mounted(){
        console.log(this.$data)
      },methods:{
        save() {
          console.log(this.data.handleTime);
          this.$axios({
            method:'post',
            headers:{
              'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'  //这里加上头部信息
            },
            url:'/biz/collection/handle',
            params:this.data
          }).then((res)=>{
            if(res.data.status==200){
              this.$alert("提交成功!","添加提示");
              this.$router.push({name:'dogShelter'});//其中dogShelter是你定义的一个路由模块
              // this.$router.back(-1);
            }else {
              this.$alert("提交失败!","添加提示");
            }
          }).catch((res)=>{
            this.$alert("提交失败!","添加提示");
          })
        },
        cancel(){
          this.data={
            collectionId:'',//收容id
            dogId:'',//犬id
            handleReason:'',//处理原因
            handleTime:'',//处理时间
            remark:'',//处理备注
            handleWay:'',//处理方式
            operatorId:''//登陆用户id
          }
          // this.$router.push('dogShelter');
          this.$router.back(-1);
        }
      }
    }
</script>

<style scoped>
  .dataSelect{
    width: 100%;
    height: 39px;
    border: 0px;
  }
</style>
