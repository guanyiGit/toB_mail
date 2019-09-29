<template>
  <!--犬只收容-已处理-查看详情-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="mbx">
        <ul class="ul11 clearfix">
          <li>
            <router-link href="###" :to="{name:'dogShelter'}">犬只收容></router-link>
          </li>
          <li>查看详情</li>
        </ul>
      </div>
      <form action="#">
        <div class="k3 mgt20">
          <table class="table10 t-c" width="100%" border="1">
            <tbody>
            <tr>
              <td class="w150">犬只编号</td>
              <td class="cor9 w340">{{dogInfo?dogInfo.dogCode:''}}</td>
              <td class="w150">犬舍编号</td>
              <td class="cor9">{{dogInfo?dogInfo.doghouseNum:''}}</td>
            </tr>
            <tr>
              <td>标示牌号</td>
              <td class="cor9">{{dogInfo?dogInfo.signboardCode:''}}</td>
              <td>宠物昵称</td>
              <td class="cor9">{{dogInfo?dogInfo.dogName:''}}</td>
            </tr>
            <tr>
              <td>品种</td>
              <td class="cor9">{{dogInfo?dogInfo.dogBreed:''}}</td>
              <td>性别</td>
              <td class="cor9">{{dogInfo?dogInfo.dogCode==0?'雄':'雌':''}}</td>
            </tr>
            <tr>
              <td>犬龄</td>
              <td class="cor9">{{dogInfo?dogInfo.dogAge:''}}</td>
              <td>毛色</td>
              <td class="cor9">{{dogInfo?dogInfo.dogColor:''}}</td>
            </tr>
            <tr>
              <td>体重</td>
              <td class="cor9">{{dogInfo?dogInfo.weight:''}}</td>
              <td>特点</td>
              <td class="cor9">{{dogInfo?dogInfo.dogCharacter:''}}</td>
            </tr>
            <tr>
              <td>健康状况</td>
              <td class="cor9">{{dogInfo?dogInfo.healthStatus==0?'健康':'不健康':''}}</td>
              <td>收容原因</td>
              <td class="cor9">{{dogInfo?dogInfo.collDogReason:''}}</td>
            </tr>
            <tr>
              <td>收容时间</td>
              <td class="cor9">{{dogInfo?dogInfo.collectionTime:''}}</td>
              <td colspan="2"></td>

            </tr>
            <tr>
              <td>备注</td>
              <td colspan="3">{{dogInfo?dogInfo.remarks:''}}</td>

            </tr>

            </tbody>
          </table>
          <div class="mgt20">
            <span class="db w120 fl h40 lh40 pdl50 cor0">犬只照片</span>
            <div class="mgl150 ovh">
              <ul v-if="dogInfo" class="fl ovh">
                <li class="fl mgr10"><img v-bind:src="dogInfo.thumbnailUrl" alt=""></li>
                <!--<li class="fl mgr10"><img src="../../../../static/images/28-2.png" alt=""></li>-->
              </ul>
              <div v-else class="file fl rel w202 h160">
                <!--<input type="file" class="abs z10 w202 h160 left0 top0">-->
                <img class="rel z1" src="../../../../static/images/28-8.png">
                <p class="abs cor9 ww100 z8 left0 top90 t-c">照片</p>
              </div>
            </div>
          </div>
        </div>
        <div class="k3 mgt20">
          <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
            处理信息
          </div>
          <div>
            <table class="table10 t-c" width="100%" border="1">
              <tbody>
              <tr>
                <td>处理时间</td>
                <td class="cor9">{{adoptInfo?adoptInfo.handleTime:''}}</td>
                <td>处理方式</td>
                <td class="cor9">{{adoptInfo?adoptInfo.handleWay:''}}</td>
              </tr>
              <tr>
                <td>处理原因</td>
                <td class="cor9 t-l pdl120" colspan="3">{{adoptInfo?adoptInfo.handleReason:''}}</td>
              </tr>
              <tr>
                <td>备注</td>
                <td class="cor9 t-l pdl120" colspan="3">{{adoptInfo?adoptInfo.remark:''}}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </form>
    </div>
    <!--内容区END-->
  </div>
</template>

<script>
    export default {
        name: "hasDealwith",
      data() {
        return {
          dogInfo:null,
          adoptInfo:null
        }
      },
      created() {
        queryDoginfo(this);
        queryAdoptInfo(this);
      },
      methods:{
      }
    }

    function queryDoginfo(_this) {
      _this.$axios({
        method: 'get',
        url: '/biz/collection/findOne',
        params: {collectionId:_this.$route.params.collectionId}
      }).then((res) => {
        if (res.data.status == 200) {
          if (res.data.data!=null) {
            _this.dogInfo=res.data.data
            var date=CalAge(res.data.data.collectionTime);
            _this.dogInfo.collectionTime=date;
          }
        }
      }).catch((err) => {
        console.log(err);
      })
    }

    function queryAdoptInfo(_this) {
      _this.$axios({
        method: 'get',
        url: '/biz/collection/findHandleCollectionById',
        params: {handleId:_this.$route.params.handleId}
      }).then((res) => {
        if (res.data.status == 200) {
          if (res.data.data!=null) {
            _this.adoptInfo=res.data.data
            var date=CalAge(res.data.data.handleTime);
            _this.adoptInfo.handleTime=date;
          }
        }
      }).catch((err) => {
        console.log(err);
      })
    }

    function CalAge(date) {
      if (date1==null || date1==undefined){
        return "";
      }
      var datetime = new Date(date);
      var year = datetime.getFullYear();
      var month = (datetime.getMonth() + 1) < 10 ? ("0" + (datetime.getMonth() + 1)) : (datetime.getMonth() + 1);
      var day = datetime.getDate() < 10 ? ("0" + datetime.getDate()) : (datetime.getDate());
      var hours = datetime.getHours() < 10 ? ("0" + datetime.getHours()) : (datetime.getHours());
      var min = datetime.getMinutes() < 10 ? ("0" + datetime.getMinutes()) : (datetime.getMinutes());
      return year + "年" + month + "月" + day + "日";
    }
</script>

<style scoped>

</style>
