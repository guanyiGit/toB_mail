<template>
    <!--执法管理-违法登记-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="mbx">
        <ul class="ul11 clearfix">
          <li>
            <router-link style="cursor:pointer;" :to="{name:'hospice_enforcementManagement'}">执法管理></router-link>
          </li>
          <li>违法登记</li>
        </ul>
      </div>
      <div class="mgt20 k3 pdb240">
        <form action="#">
          <div class="pdt10 pdb20 pdr30">
            <div class="ovh pdt10">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">犬只标识号</span>
                <div class="mgl130">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="collarNum" @change="selectpointByNum">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w110 fl h40 lh40 cor0 pdl30">当前积分：</span>
                <div class="mgl110 h40 lh40">
                  <span class="fl db mgr30">{{integral}}</span>
                  <a style="cursor:pointer;" class="db fl corffa472 t-u hov8" @click="checkHistory">点击查看历史记录</a>
                </div>
              </div>
            </div>
            <div class="ovh pdt10">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">违法时间</span>
                <div class="mgl130">
                  <el-date-picker v-model="violation.violationDate" type="date" class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                  placeholder="选择违法时间" :format="datePattern"></el-date-picker>
                  <!--<input id="inp1" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4 bgpc17">-->
                </div>
              </div>
            </div>
            <div class="ovh pdt10">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">违法地点</span>
                <div class="mgl130">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="violation.violationPlace">
                </div>
              </div>
            </div>
            <div class="ovh pdt10">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">违法行为</span>
                <div class="mgl130">
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                  <select v-model="violation.violationType" class="ww100 h40 lh40 pdl20 borf1 ras4">
                    <option value="1">不牵犬绳</option>
                    <option value="0">溜犬不及时清理犬只粪便</option>
                    <option value="2">犬只扰民</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="ovh pdt10">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">本次扣分</span>
                <div class="mgl130">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="violation.points">
                </div>
              </div>
            </div>
            <div class="ovh pdt10">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">是否收容</span>
                <div class="mgl130 h40 lh40" id="lab1">
                  <label class="lab2 mgr40"><input type="radio" name="abc2" value="0" v-model="violation.isCollection" checked><i></i>是</label>
                  <label class="lab2"><input type="radio" name="abc2" v-model="violation.isCollection" value="1"><i></i>否</label>
                </div>
              </div>
            </div>
            <div class="ovh pdt10">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">处罚时间</span>
                <div class="mgl130">
                  <!--<input id="inp2" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4 bgpc17">-->
                  <el-date-picker v-model="violation.punishTime" type="date" class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                  placeholder="选择处罚时间" :format="datePattern"></el-date-picker>
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
    <!--弹窗   说明。.db--显示，.dn--隐藏-->
    <div class="T db" style="display: none" @click="hidee">
      <form action="">

        <!--确认领养-->
        <div class="tan6 abs bgw k3 db">
          <table class="table5 t-c">
            <tbody>
            <tr>
              <td class="cor0 h60 bgf1">违法时间</td>
              <td class="cor0 h60 bgf1">违法地点</td>
              <td class="cor0 h60 bgf1">违法行为</td>
              <td class="cor0 h60 bgf1">本次扣分</td>
              <td class="cor0 h60 bgf1">是否收容</td>
            </tr>
            <tr v-for="item in list">
              <td>{{item.violationDate | formatDate('yyyy-MM-dd')}}</td>
              <td>{{item.violationPlace}}</td>
              <td>{{item.dictionaryDescribe}}</td>
              <td>{{item.points}}分</td>
              <td>{{item.isCollection==0?'是':'否'}}</td>
            </tr>
            </tbody>
          </table>
        </div>
        <!--确认领养-->
      </form>
    </div>
    <!--弹窗END-->
  </div>
</template>

<script>
  import $ from 'jquery'
  import config from '../../common/config.js'
    export default {
        name: "illegalRegistration",
      data(){
          return {
            violation:{
              violationDate:null,
              violationPlace:null,
              violationType:null,
              points:null,
              isCollection:null,
              punishTime:null,
              dogId:null,
              dogOwnerId:null,
              orgId:config.userInfo.organizationId,
              operatorId:config.userInfo.userId,
              districtId:config.userInfo.districtId
            },
            collarNum:null,//项圈编号
            integral:null,//积分
            datePattern:"yyyy年MM月dd日",
            list:[]
          }
      },
      created(){
      },
      mounted(){
        console.log(this.$data)
      },
      methods:{
        //通过项圈编码查当前积分及犬id、犬主id
        selectpointByNum(){
          console.log(this.collarNum)
          this.$axios({
            method:'get',
            url:'/biz/violationPunish/findDeviceDogVoByDeviceCode',
            params:{deviceNumber:this.collarNum}
          }).then((res)=>{
            console.log(res)
            if(res.data.status==200 && res.data.data!=null){
              this.integral=res.data.data.deductionSum
              this.violation.dogId=res.data.data.dogId;
              this.violation.dogOwnerId=res.data.data.dogOwnerId;
            }else {
              this.$alert("犬只不存在!");
            }
          }).catch((res)=>{
          })
        },
        save() {
          if(this.collarNum=='' || this.collarNum==null){
            this.$alert("犬只标识不能为空");
            return false;
          }
          if(this.violation.violationDate=='' || this.violation.violationDate==null){
            this.$alert("违法时间不能为空");
            return false;
          }
          if(this.violation.violationPlace=='' || this.violation.violationPlace==null){
            this.$alert("违法地点不能为空");
            return false;
          }
          if(this.violation.isCollection=='' || this.violation.isCollection==null){
            this.$alert("请选择是否收容");
            return false;
          }
          if(this.violation.punishTime=='' || this.violation.punishTime==null){
            this.$alert("处罚时间不能为空");
            return false;
          }
          //校验分数
          if(this.violation.points=='' || this.violation.points==null){
            if(!isNonnegativeInteger(this.violation.points)){
              this.$alert("本次扣分填写有误");
              return false;
            }
          }
          this.$axios({
            method:'post',
            headers:{
              "content-type": "application/json;charset=utf-8",
            },
            url:'/biz/violationPunish/save',
            data:JSON.stringify(this.violation)
          }).then((res)=>{
            if(res.data.status==200){
              this.$alert("提交成功!","添加提示");
                this.violation={
                  violationDate:null,
                  violationPlace:null,
                  violationType:null,
                  points:null,
                  isCollection:null,
                  punishTime:null,
                  dogId:null,
                  dogOwnerId:null,
                  orgId:config.userInfo.organizationId,
                  operatorId:config.userInfo.userId,
                  districtId:config.userInfo.districtId
                },
                this.collarNum=null,//项圈编号
                this.integral=null,//积分
                  this.$router.push({name:'enforcementManagement'});//其中dogShelter是你定义的一个路由模块
            }else {
              this.$alert("提交失败!","添加提示");
            }
          }).catch((res)=>{
            this.$alert("提交失败!","添加提示");
          })
        },
        cancel(){
          this.violation={
            violationDate:null,
            violationPlace:null,
            violationType:null,
            points:null,
            isCollection:null,
            punishTime:null,
            dogId:null,
            dogOwnerId:null,
            orgId:config.userInfo.organizationId,
            operatorId:config.userInfo.userId,
            districtId:config.userInfo.districtId
          },
            this.collarNum=null,//项圈编号
            this.integral=null,//积分
          this.$router.push({name:'enforcementManagement'});
        },
        checkHistory(){
          $(".T").show();
          this.$axios({
            method:'get',
            url:'/biz/violationPunish/findViolationPunishListByDogId',
            params:{dogId:this.violation.dogId}
          }).then((res)=>{
            console.log(res)
            if(res.data.status==200 && res.data.data!=null){
              this.list=res.data.data
            }else {
              this.$alert("犬只不存在!");
            }
          }).catch((res)=>{
          })
        },
        hidee(){
          $(".T").hide();
        }
      }
    }
  //非负整数
  function isNonnegativeInteger(num){
    var reg = /(^[1-9]+\d*$)|(^0$)/;
    return reg.test(num);
  }
</script>

<style scoped>

</style>
