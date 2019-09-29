<template>
    <!--待确认领养查看详情-->
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

      <div class="mgt10 k3">
        <div class="ovh pdb30">
          <div class="w730 fl">
            <div class="ovh pdt10">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">犬只编号</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.dogCode:''">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">犬舍编号</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.doghouseNum:''">
                </div>
              </div>
            </div>
            <div class="ovh pdt10">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">标识牌号</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.signboardCode:''">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">宠物昵称</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.dogName:''">
                </div>
              </div>
            </div>
            <div class="ovh pdt10">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">品种</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.dogBreed:''">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">性别</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.dogGender=0?'雄':'雌':''">
                </div>
              </div>
            </div>
            <div class="ovh pdt10">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">犬龄</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.dogAge:''">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">毛色</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.dogColor:''">
                </div>
              </div>
            </div>
            <div class="ovh pdt10">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">体重</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.weight:''">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">特点</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.dogCharacter:''">
                </div>
              </div>
            </div>

            <div class="ovh pdt10">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">健康状况</span>
                <div class="mgl100">
                  <select type="text" class="ww100 h40 lh40 pdl20 borf1 ras4 pc4">
                    <option value="0">良好</option>
                    <option value="1">差</option>
                  </select>
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">收容原因</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?detail.collDogReason:''">
                  <!--<select type="text" class="ww100 h40 lh40 pdl20 borf1 ras4 pc4">-->
                    <!--<option value="1">依法收容犬</option>-->
                    <!--<option value="1">依法收容犬</option>-->
                  <!--</select>-->
                </div>
              </div>
            </div>

            <div class="ovh pdt10">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">收容时间</span>
                <div class="mgl100">
                  <!--<input id="date" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-bind:value="detail?(detail.collectionTime | formatDate('yyyy-MM-dd')):''">-->
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-if="!detail || !detail.collectionTime">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-else :value="detail.collectionTime | formatDate('yyyy-MM-dd')">
                </div>
              </div>
            </div>

            <div class="ovh pdt10">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">备注</span>
                <div class="mgl100">
                  <textarea class="ww100 h140 borf1 pdl10 pdr10 pdt10 pdb10" v-bind:value="detail?detail.remarks:''"></textarea>
                </div>
              </div>
            </div>

          </div>
          <div class="mgl730 pdt60"><img class="c-b" src="../../../../static/images/b22.png" alt=""></div>
        </div>
      </div>
      <div class="mgt20 k3 t-c">
        <table class="table5" width="100%" border="1">
          <tbody>
          <tr>
            <td class="cor0 h60 bgf1">姓名</td>
            <td class="cor0 h60 bgf1">性别</td>
            <td class="cor0 h60 bgf1">证件类型</td>
            <td class="cor0 h60 bgf1">证件号码</td>
            <td class="cor0 h60 bgf1">联系电话</td>
            <td class="cor0 h60 bgf1">申请时间</td>
            <td class="cor0 h60 bgf1">养犬地址</td>
            <td class="cor0 h60 bgf1">操作</td>
          </tr>
          <tr v-for="item in list">
            <td>{{item.applyerName}}</td>
            <td>{{item.applyerSex==0?"雄":"雌"}}</td>
            <td>{{item.cardType==0?"身份证":item.cardType==1?"房产证":item.cardType==2?"营业执照":"其他"}}</td>
            <td>{{item.cardNumber}}</td>
            <td>{{item.applyerPhone}}</td>
            <td>{{item.creationTime | formatDate('yyyy-MM-dd')}}</td>
            <td>{{item.adoptionAddress}}</td>
            <td class="t-c">
              <a style="cursor:pointer;" class="dinb cor009cff hov8" v-on:click="applySuer(item.collectionId,item.dogPublicityId,item.adoptionRecordsId)">确定领养</a>
            </td>
          </tr>
          <!--<tr>-->
            <!--<td>张三</td>-->
            <!--<td>男</td>-->
            <!--<td>身份证</td>-->
            <!--<td>43023419880612345</td>-->
            <!--<td>13800138000</td>-->
            <!--<td>2017/03/02</td>-->
            <!--<td>长安街</td>-->
            <!--<td class="t-c">-->
              <!--<a href="###" class="dinb cor009cff hov8">确定领养</a>-->
            <!--</td>-->
          <!--</tr>-->
          </tbody>
        </table>
      </div>
    </div>
    <!--内容区END-->
  </div>

</template>

<script>
  export default {
        name: "pendingAdoptionDetails",
    data() {
      return {
        detail: null,//详情
        list:[]
      }
    },
    created() {
      selectDetail(this);
      pendingAdoption(this);
    },
    mounted() {
      console.log(this.$data)
    },
    methods: {
      //点击确认领养
      applySuer:function (collectionId,dogPublicityId,adoptionRecordsId) {
        var obj={
          collectionId:collectionId,
          dogPublicityId:dogPublicityId,
          adoptionRecordsId:adoptionRecordsId
        }
        this.$confirm('确定选择此领养人吗？').then(_ => {
          this.$axios({
            method: 'post',
            headers:{
              'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'  //这里加上头部信息
            },
            url: '/biz/adoption/applySuer',
            params:obj
          }).then((res) => {
            if (res.data.status == 200) {
              // _this.detail=res.data.data
            }
          }).catch((res) => {
            console.log(res)
          })
        }).catch(_ => {
        });
      }
    }
    }
    //查看详情
  function selectDetail(_this) {
    _this.$axios({
      url: '/biz/collection/findOne',
      method: 'get',
      params: {collectionId:_this.$route.params.collectionId}
    }).then((res) => {
      console.log(res)
      if (res.data.status == 200) {
        _this.detail=res.data.data
      }
    }).catch((res) => {
      console.log(res)
    })
  }
  //待确认领养
  function pendingAdoption(_this) {
    _this.$axios({
      url: '/biz/adoption/findTAdoptionApply',
      method: 'get',
      params: {adoptionPublicityId:_this.$route.params.adoptionPublicityId}
    }).then((res) => {
      console.log(res)
      if (res.data.status == 200) {
        _this.list=res.data.data
      }
    }).catch((res) => {
      console.log(res)
    })
  }

</script>

<style scoped>

</style>
