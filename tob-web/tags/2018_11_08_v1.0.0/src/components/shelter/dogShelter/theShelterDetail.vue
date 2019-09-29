<template>
    <!--收容中-查看详情-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="mbx">
        <ul class="ul11 clearfix">
          <li>
            <router-link style="cursor:pointer;" :to="{name:'dogShelter'}">犬只收容></router-link>
          </li>
          <li>查看详情</li>
        </ul>
      </div>
      <form action="#">
        <div class="mgt10 k3">
          <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0">
            犬只信息
          </div>

          <div>
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
              <tr>
                <td>照片</td>
                <td class="cor9 pdt20 pdb20 pdl30" colspan="3">
                  <p v-if="dogInfo&&dogInfo.thumbnailUrl" class="fl mgr10"><img class="rel z1" v-bind:src="dogInfo.thumbnailUrl"></p>
                  <p v-else class="fl mgr10"><img class="rel z1" src="../../../../static/images/28-8.png"></p>
                </td>
              </tr>
              </tbody>
            </table>
          </div>

        </div>

        <div class="t-c mgt140 mgb50">
          <router-link :to="{name:'claimToRegister',params:{collectionId:collectionId,dogId:dogId}}" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort">认领登记</router-link>
          <router-link :to="{name:'adoptionRegistration',params:{collectionId:collectionId,dogId:dogId}}" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort">领养登记</router-link>
          <router-link :to="{name:'handleRegistration',params:{collectionId:collectionId,dogId:dogId}}" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort">处理登记</router-link>
        </div>
      </form>
    </div>
    <!--内容区END-->
  </div>
</template>

<script>
    export default {
        name: "theShelterDetail",
      data() {
        return {
          dogInfo:null,
          collectionId:null,
          dogId:null
        }
      },
      created() {
        queryDoginfo(this);
      },
      methods:{
      }
    }

    function queryDoginfo(_this) {
      _this.collectionId=_this.$route.params.collectionId
      _this.$axios({
        method: 'get',
        url: '/biz/collection/findOne',
        params: {collectionId:_this.$route.params.collectionId}
      }).then((res) => {
        console.log("111")
        console.log(res)
        if (res.data.status == 200) {
          if (res.data.data!=null) {
            _this.dogInfo=res.data.data
            _this.dogId=res.data.data.dogId
            var date=CalAge(res.data.data.collectionTime);
            _this.dogInfo.collectionTime=date;
          }
        }
      }).catch((err) => {
        console.log(err);
      })
    }
</script>

<style scoped>

</style>
