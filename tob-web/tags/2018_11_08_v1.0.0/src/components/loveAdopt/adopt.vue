<template>
  <!--领养申请-->
    <div>
      <!--头-->
      <MainTop></MainTop>
      <div class="w1200 c-b mgt30">
        <div class="ovh">
          <div class="fl cor6 h40 lh40">
            <!--src="../../../static/images/7.png"-->
            <img class="mgr8 db fl" src="../../../static/images/7.png" alt="">
            <span class="fs18 cor6a4a22 fb mgr30 db fl"><router-link :to="{name:'loveAdopt'}">爱心领养</router-link>>>领养申请</span>
          </div>
        </div>
        <img class="db mgt10" src="../../../static/images/23.png" alt="">
      </div>

      <!---->
      <form @submit.prevent="submit">
        <div class="w1200 c-b mgt35 bord">
          <p class="fs16 cor0 h60 lh60 bgf1 pdl30">犬只信息</p>
          <div>
            <table class="table1" width="100%" border="1">
              <tbody>
              <tr>
                <td>犬名</td>
                <td>{{dogInfo?dogInfo.dogName:''}}</td>
                <td>品种</td>
                <td>{{dogInfo?dogInfo.dogBreed:''}}</td>
                <!--../../../static/images/38.png-->
                <td rowspan="4"><img v-bind:src="dogInfo?dogInfo.thumbnailUrl:''" alt=""></td>
              </tr>
              <tr>
                <td>性别</td>
                <td>{{dogInfo?dogInfo.dogGender==0?"雄":"雌":''}}</td>
                <td>毛色</td>
                <td>{{dogInfo?dogInfo.dogColor:''}}</td>
              </tr>
              <tr>
                <td>犬龄</td>
                <td>{{dogInfo?dogInfo.dogAge:''}}岁</td>
                <td>特点</td>
                <td>{{dogInfo?dogInfo.dogCharacter:''}}</td>
              </tr>
              <tr>
                <td colspan="4">备注：{{dogInfo?dogInfo.remarks:''}}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="mgt18 w1200 c-b bord">
          <p class="fs16 cor0 h60 lh60 bgf1 pdl30">领养申请人信息</p>
          <div class="pdt10 pdb60 pdl30 pdr30">
            <div class="ovh">
              <div class="mgt10 w550 fl">
                <span class="db w80 fl h40 lh40 cor0">姓名</span>
                <div class="mgl80">
                  <input v-model="person.applyerName" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="">
                </div>
              </div>
              <div class="mgt10 w550 fr">
                <span class="db w80 fl h40 lh40 cor0">性别</span>
                <div class="mgl80 h40 lh40">
                  <label class="lab2 mgr40"><input v-model="person.applyerSex" type="radio" name="abc" value="0"><i></i>男</label>
                  <label class="lab2"><input v-model="person.applyerSex" type="radio" name="abc" value="1"><i></i>女</label>
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 w550 fl">
                <span class="db w80 fl h40 lh40 cor0">证件类型</span>
                <div class="mgl80">
                  <select v-model="person.cardType" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4 pc4">
                    <option value="0">身份证</option>
                    <option value="1">房产证</option>
                    <option value="2">营业执照</option>
                    <option value="3">其他</option>
                  </select>
                </div>
              </div>
              <div class="mgt10 w550 fr">
                <span class="db w80 fl h40 lh40 cor0">证件号码</span>
                <div class="mgl80">
                  <input v-model="person.cardNumber" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="">
                </div>
              </div>
            </div>

            <div class="ovh">
              <div class="mgt10">
                <span class="db w80 fl h40 lh40 cor0">养犬地址</span>
                <div class="mgl80">
                  <input v-model="person.adoptionAddress" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="">
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 w550 fl">
                <span class="db w80 fl h40 lh40 cor0">联系电话</span>
                <div class="mgl80">
                  <input v-model="person.applyerPhone" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="">
                </div>
              </div>
              <div class="mgt10 w550 fr">
                <span class="db w80 fl h40 lh40 cor0">验证码</span>
                <div class="mgl80">
                  <input v-model="code" type="text" class="ww78 h40 lh40 pdl20 borf1 ras4" placeholder="">
                  <input type="text" class="fr ww20 h40 lh40 borf1 ras4 t-c bgfbad82 corw" value="点击获取" v-on:click="getCode">
                </div>
              </div>
            </div>
            <p class="fs14 corfbad82 mgt20">温馨提示：领养申请人需咨询领养工作具体情况，请在上班时间内电话联系：13800138000。感谢您对养犬管理工作的关心、支持和参与！</p>
            <div class="t-c mgt40">
              <input type="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" value="提交">
              <router-link style="cursor:pointer;" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5" :to="{name:'loveAdopt'}">取消</router-link>
            </div>
          </div>
        </div>
      </form>
      <!--end-->
      <div class="clearfix"></div>

      <!--友情链接-->
      <div class="mgt110 ovh w1200 c-b">
        <div class="fl w160 h72 pdl20 pdt17 bor-r">
          <p class="fs20 cor6b4a22 h22 lh22">友情链接</p>
          <p class="cor9 h16 lh16">Brand introduction</p>
        </div>
        <div class="mgl160">
          <ul class="ul07">
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9 hov8">狗狗网</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9 hov8">瑞鹏宠物中心</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9 hov8">邵阳宠物医院</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9 hov8">丰原宠物网</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9 hov8">全进宠物食品网</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9 hov8">邵阳宠物医院</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9 hov8">邵阳宠物医院</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9 hov8">狗狗网</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">瑞鹏宠物中心</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">邵阳宠物医院</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">狗狗网</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">全进宠物食品网</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">邵阳宠物医院</a>
            </li>
            <li class="fl">
              <a href="###" class="db h36 lh36 cor9">狗狗网</a>
            </li>
          </ul>
        </div>
      </div>
      <!--友情链接-->


      <!--分割线-->
      <div class="mgt110"><img class="db c-b" src="../../../static/images/6.png" alt="分割线"></div>
      <!--分割线END-->
      <!--底部-->
      <MainBottom></MainBottom>
    </div>
</template>

<script>
  let params = {
    phone:null,
  }
  var thiss='';
    export default {
        name: "adopt",
      data() {
        return {
          dogInfo: null,//犬信息
          person:{
            applyerName:null,
            applyerSex:null,
            cardType:null,
            cardNumber:null,
            adoptionAddress:null,
            applyerPhone:null,
            dogPublicityId:this.$route.params.dogPublicityId,
            collectionId:this.$route.params.collectionId
          },
          code:null
        }
      },
      created() {
        getDogInfo(this);
      },beforeCreate(){
        thiss=this;
      },
      methods: {
        //获取验证码
        getCode:function () {
          if(thiss.person.applyerPhone==null || thiss.person.applyerPhone==""){
            this.$alert("联系电话不能为空");
            return;
          }
          if(!thiss.person.applyerPhone || !/^1[34578]\d{9}$/.test(thiss.person.applyerPhone)){
            this.$alert("电话号码格式有误!");
            return;
          }
          thiss.$axios({
            method: 'get',
            url: '/biz/adoption/getCode',
            params: {phone:thiss.person.applyerPhone}
          }).then((res) => {
            if (res.data.status == 200) {
              this.$alert("验证码已发送。。。");
            }else {
              this.$alert("获取验证码失败");
            }
          })
        },
        submit:function () {
          console.log(thiss.person)
          submitApply();
          //验证验证码
          // thiss.$axios({
          //   method: 'get',
          //   url: '/biz/adoption/checkCode',
          //   params: {phone:thiss.code}
          // }).then((res) => {
          //   if (res.data.status == 200) {
          //     submitApply();
          //   }else {
          //     this.$alert("验证码不正确！");
          //   }
          // })
        }
      }
    }

  //提交申请
  function submitApply() {
    thiss.$axios({
      method: 'post',
      headers:{
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'  //这里加上头部信息
      },
      url: '/biz/adoption/apply',
      params: thiss.person
    }).then((res) => {
      if (res.data.status == 200) {
        thiss.$alert("申请成功");
        thiss.$router.push({name:'loveAdopt'})
      }else {
        thiss.$alert("申请失败");
      }
    })
  }
    //获取犬只信息
  function getDogInfo(_this) {
    _this.$axios({
      method: 'get',
      url: '/biz/collection/findOne',
      params: {collectionId:_this.$route.params.collectionId}
    }).then((res) => {
      if (res.data.status == 200) {
        _this.dogInfo=res.data.data
      }
    })
  }

</script>

<style scoped>

</style>
