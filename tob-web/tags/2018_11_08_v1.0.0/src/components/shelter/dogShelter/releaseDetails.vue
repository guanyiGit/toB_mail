<template>
    <!--发布中详情-->
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
          <div class="pdb20 ovh pdr30">
            <div class="fl w950 pdt10">
              <div class="ovh">
                <div class="mgt10 ww50 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">犬只编号</span>
                  <div class="mgl100">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.dogCode:''" :disabled=true>
                  </div>
                </div>
                <div class="mgt10 ww50 fr">
                  <span class="db w90 fl h40 lh40 cor0 pdl30">犬舍编号</span>
                  <div class="mgl90">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.doghouseNum:''" :disabled=true>
                  </div>
                </div>
              </div>
              <div class="ovh">
                <div class="mgt10 ww50 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">标示牌号</span>
                  <div class="mgl100">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.signboardCode:''" :disabled=true>
                  </div>
                </div>
                <div class="mgt10 ww50 fr">
                  <span class="db w90 fl h40 lh40 cor0 pdl30">犬只名称</span>
                  <div class="mgl90">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.dogName:''" :disabled=true>
                  </div>
                </div>
              </div>
              <div class="ovh">
                <div class="mgt10 ww50 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">品种</span>
                  <div class="mgl100">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.dogBreed:''" :disabled=true>
                  </div>
                </div>
                <div class="mgt10 ww50 fr">
                  <span class="db w90 fl h40 lh40 cor0 pdl30">性别</span>
                  <div class="mgl90">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.dogCode==0?'雄':'雌':''" :disabled=true>
                  </div>
                </div>
              </div>
              <div class="ovh">
                <div class="mgt10 ww50 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">犬龄</span>
                  <div class="mgl100">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.dogAge:''" :disabled=true>
                  </div>
                </div>
                <div class="mgt10 ww50 fr">
                  <span class="db w90 fl h40 lh40 cor0 pdl30">毛色</span>
                  <div class="mgl90">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.dogColor:''" :disabled=true>
                  </div>
                </div>
              </div>
              <div class="ovh">
                <div class="mgt10 ww50 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">体重</span>
                  <div class="mgl100">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.weight:''" :disabled=true>
                  </div>
                </div>
                <div class="mgt10 ww50 fr">
                  <span class="db w90 fl h40 lh40 cor0 pdl30">特点</span>
                  <div class="mgl90">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.dogCharacter:''" :disabled=true>
                  </div>
                </div>
              </div>
              <div class="ovh">
                <div class="mgt10 ww50 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">健康状况</span>
                  <div class="mgl100">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.healthStatus==0?'健康':'不健康':''" :disabled=true>
                  </div>
                </div>
                <div class="mgt10 ww50 fr">
                  <span class="db w90 fl h40 lh40 cor0 pdl30">收容原因</span>
                  <div class="mgl90">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="依法收缴犬" v-bind:value="dogInfo?dogInfo.collDogReason:''" :disabled=true>
                  </div>
                </div>
              </div>
              <div class="ovh">
                <div class="mgt10 ww100 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">收容时间</span>
                  <div class="mgl100">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.collectionTime:''" :disabled=true>
                  </div>
                </div>
              </div>
              <div class="ovh">
                <div class="mgt10 ww100 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">备注</span>
                  <div class="mgl100">
                    <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="dogInfo?dogInfo.remarks:''" :disabled=true>
                  </div>
                </div>
              </div>
              <div class="ovh">
                <div class="mgt10 ww100 fl">
                  <span class="db w100 fl h40 lh40 cor0 pdl30">照片</span>
                  <div class="mgl100">
                    <img v-if="dogInfo" v-bind:src="dogInfo.thumbnailUrl" alt="">
                    <img v-else src="../../../../static/images/28-8.png">
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
        <div class="t-c mgt40 mgb50">
          <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8" @click="revocationBatch">撤回到收容库</a>
          <router-link style="cursor:pointer;" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5" :to="{name:'dogShelter'}">返回</router-link>
        </div>
      </form>
    </div>
    <!--内容区END-->
  </div>
</template>

<script>
    export default {
        name: "releaseDetails",
      data() {
        return {
          dogInfo:null
        }
      },
      created() {
        detail(this);
      },
      methods:{
        revocationBatch:function () {
          var AdoptionCollectionVOList=[];
          var obj={
            collectionId:this.$route.params.collectionId,
            adoptionPublicityId:this.$route.params.adoptionPublicityId
          }
          AdoptionCollectionVOList.push(obj);
          this.$confirm("确定撤回吗?", function(ok){
            if(ok){
              _this.$axios({
                method: 'get',
                url: '/biz/adoption/revocation',
                params: {AdoptionCollectionStr:JSON.stringify(AdoptionCollectionVOList)}
              }).then((res) => {
                if (res.data.status == 200) {
                  this.$alert("操作成功")
                  this.$router.push('dogShelter');
                }
              }).catch((err) => {
                console.log(err);
              })
            }
          })
        }
      }
    }
    function detail(_this) {
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
