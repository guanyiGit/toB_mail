<template>
    <!--收容所执法管理详情-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="mbx">
        <ul class="ul11 clearfix">
          <li>
            <router-link style="cursor:pointer;" :to="{name:'enforcementManagement'}">执法处罚></router-link>
          </li>
          <li>查看详情</li>
        </ul>
      </div>

      <div class="mgt20 k3">
        <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
          犬只/犬主基本信息
        </div>
        <div class="t-c">
          <table class="table10" width="100%" border="1">
            <tbody>
            <tr>
              <td class="ww20">犬名</td>
              <td class="cor9 ww30">{{data?data.dogName:''}}</td>
              <td class="ww20">品种</td>
              <td class="cor9 ww30">{{data?data.dogBreed:''}}</td>
            </tr>
            <tr>
              <td>性别</td>
              <td class="cor9">{{data?data.dogGender==0?'雄':'雌':''}}</td>
              <td>年龄</td>
              <td class="cor9">{{data?data.dogAge:''}}</td>
            </tr>
            <tr>
              <td>出生日期</td>
              <td v-if="data&&data.birthTime" class="cor9">{{data.birthTime | formatDate('yyyy-MM-dd')}}</td>
              <td v-else class="cor9"></td>
              <td>毛色</td>
              <td class="cor9">{{data?data.dogColor:''}}</td>
            </tr>
            <tr>
              <td>犬证号</td>
              <td class="cor9">{{data?data.dogCardNum:''}}</td>
              <td>标识号</td>
              <td class="cor9">{{data?data.deviceNumber:''}}</td>
            </tr>
            <tr>
              <td>犬主姓名</td>
              <td class="cor9">{{data?data.dogOwnerName:''}}</td>
              <td>身份证号</td>
              <td class="cor9">{{data?data.cardNum:''}}</td>
            </tr>
            <tr>
              <td>照片</td>
              <td class="cor9 pdt20 pdb20 pdl30" colspan="3">
                <p class="fl mgr10">
                  <img v-if="data&&data.thumbnailUrl" v-bind:src="data.thumbnailUrl" alt="">
                  <img v-else src="" alt="">
                </p>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>

      <div class="mgt20 k3">
        <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
          违法/处罚信息
        </div>
        <div>
          <table class="table10 t-c">
            <tbody>
            <tr>
              <td class="ww20">违法时间</td>
              <td class="cor9 ww30" v-if="data&&data.violationDate ">{{data.violationDate | formatDate('yyyy-MM-dd')}}</td>
              <td class="cor9 ww30" v-else></td>
              <td class="ww20">违法地点</td>
              <td class="cor9 ww30">{{data?data.violationPlace:''}}</td>
            </tr>
            <tr>
              <td class="ww20">违法行为</td>
              <td class="cor9 ww30">{{data?data.violationType:''}}</td>
              <td class="ww20">本次扣分</td>
              <td class="cor9 ww30">{{data?data.points:''}}分</td>
            </tr>
            <tr>
              <td class="ww20">是否收容</td>
              <td class="cor9 ww30">{{data?data.isCollection==0?'是':'否':''}}</td>
              <td class="ww20">处罚时间</td>
              <td v-if="data&&data.punishTime" class="cor9 ww30">{{data.punishTime | formatDate('yyyy-MM-dd')}}</td>
              <td v-else class="cor9 ww30"></td>
            </tr>

            <tr>
              <td class="ww20">备注</td>
              <td class="cor9 t-l pdl100" colspan="3">{{data?data.remarks:''}}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>

    </div>
    <!--内容区-->
  </div>
</template>

<script>
    export default {
        name: "enforcementManagementDetails",
      data(){
        return {
          data:JSON.parse(this.$route.params.info),
          datePattern:"yyyy年MM月dd日",
        }
      },
      created(){
      },
      mounted(){
        console.log(this.$data)
      },methods:{
        //通过项圈编码查当前积分及犬id、犬主id
        selectpointByNum(){
          this.$axios({
            method:'get',
            url:'/biz/violationPunish/findDeviceDogVoByDeviceCode',
            data:{deviceNumber:this.collarNum}
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
            this.$router.push('enforcementManagement');
        },
      },

    }
</script>

<style scoped>

</style>
