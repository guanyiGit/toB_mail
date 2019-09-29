<template>
    <!--认领登记-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="mbx">
        <ul class="ul11 clearfix">
          <li>
            <router-link href="###" :to="{name:'dogShelter'}">犬只收容></router-link>
          </li>
          <li>认领登记</li>
        </ul>
      </div>
      <form action="#">
        <div class="mgt10 k3">
          <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0">
            认领登记
          </div>
          <div class="ovh pdb30">
            <div class="fl w980 pdr30">
              <div class="ovh pdt10">
                <div class="mgt10 ww50 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">认领时间</span>
                  <div class="mgl100">
                    <!--<input id="inp1" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4 pc6">-->
                    <el-date-picker v-model="data.handleTime" type="date" class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                    placeholder="选择处理时间" :format="datePattern"></el-date-picker>
                  </div>
                </div>
                <div class="mgt10 ww50 fr">
                  <span class="db w90 fl h40 lh40 cor0 pdl30">认领人</span>
                  <div class="mgl90">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.claimName">
                  </div>
                </div>
              </div>
              <div class="ovh pdt10">
                <div class="mgt10 ww50 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">证件类型</span>
                  <div class="mgl100">
                    <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                    <select v-model="data.cardType" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                      <option value="0">身份证</option>
                      <option value="1">护照</option>
                      <option value="2">社保卡</option>
                      <option value="3">其他</option>
                    </select>
                  </div>
                </div>
                <div class="mgt10 ww50 fr">
                  <span class="db w90 fl h40 lh40 cor0 pdl30">证件号码</span>
                  <div class="mgl90">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.cardNum">
                  </div>
                </div>
              </div>
              <div class="ovh pdt10">
                <div class="mgt10 ww50 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">联系电话</span>
                  <div class="mgl100">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.phone">
                  </div>
                </div>
                <div class="mgt10 ww50 fr">
                  <span class="db w90 fl h40 lh40 cor0 pdl30">养犬地址</span>
                  <div class="mgl90">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.adress">
                  </div>
                </div>
              </div>


              <div class="ovh pdt10">
                <div class="mgt10 ww100 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">备注</span>
                  <div class="mgl100">
                    <textarea class="ww100 h100 borf1 pdl10 pdr10 pdt10 pdb10" v-model="data.remark"></textarea>
                  </div>
                </div>
              </div>

            </div>

          </div>

          <div class="t-c mgt140 mgb50">
            <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" @click="save">提交</a>
            <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5" @click="cancel">取消</a>
          </div>
        </div>
      </form>
    </div>
    <!--内容区END-->
  </div>
</template>

<script>
  //认领登记
  import config from '../../../common/config.js'
    export default {
        name: "claimToRegister",
      data() {
        return {
          data:{
            collectionId:this.$route.params.collectionId,//收容id
            dogId:this.$route.params.dogId,//狗id
            claimName:null,
            cardType:null,//证件类型
            cardNum:null,//证件号码,
            phone:null,//认领人电话
            adress:null,//养犬地址
            remark:null,//备注
            handleTime:null,//认领时间
            operatorId:config.userInfo.userId
          },
          datePattern:"yyyy年MM月dd日",
        }
      },
      created(){
        // this.data.collectionId=this.$route.params.collectionId
        // this.data.dogId=this.$route.params.dogId
      }
      ,mounted(){
          console.log(this.$route.params.dogId)
        console.log(this.$data)
      },
      methods:{
        save() {
          this.$axios({
            method:'post',
            headers:{
              'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'  //这里加上头部信息
            },
            url:'/biz/collection/claim',
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
            collectionId:null,//收容id
            dogId:null,//狗id
            claimName:null,
            cardType:null,//证件类型
            cardNum:null,//证件号码,
            phone:null,//认领人电话
            adress:null,//养犬地址
            remark:null,//备注
            handleTime:null,//认领时间
            operatorId:null
          }
          // this.$router.push('dogShelter');
          this.$router.back(-1)
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
