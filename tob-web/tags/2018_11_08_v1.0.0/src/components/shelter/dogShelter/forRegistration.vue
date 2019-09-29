<template>
    <!--收容登记-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="mbx">
        <ul class="ul11 clearfix">
          <li>
            <router-link href="###" :to="{name:'dogShelter'}">犬只收容></router-link>
          </li>
          <li>收容登记</li>
        </ul>
      </div>
      <form action="#">
        <div class="mgt10 k3">

          <div class="pdt10 pdb20 pdr30 borb2">
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">犬只编号</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.dogCode">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">犬舍编号</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.doghouseNum">
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">犬只名称</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.dogName">
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">品种</span>
                <div class="mgl100">
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                  <select id="breed" @change="obtainBreed" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                    <option>请选择</option>
                    <option v-for="item in breedList" v-bind:value="item.dictionaryValue">{{item.dictionaryDescribe}}</option>
                  </select>
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">性别</span>
                <div class="mgl90 h40 lh40">
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                  <label class="lab2 mgr40"><input v-model="data.dogGender" type="radio" name="abc" value="0"><i></i>雄</label>
                  <label class="lab2"><input type="radio" v-model="data.dogGender" name="abc" value="1"><i></i>雌</label>
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">犬龄</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.dogAge">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">毛色</span>
                <div class="mgl90">
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                  <select id="color" @change="obtainColor" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                    <option>请选择</option>
                    <option v-for="item in colorList" v-bind:value="item.dictionaryValue">{{item.dictionaryDescribe}}</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">体重</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.weight">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">特点</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.dogCharacter">
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">健康状况</span>
                <div class="mgl100">
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="良好">-->
                  <select v-model="data.healthStatus" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                    <option value="0">健康</option>
                    <option value="1">不健康</option>
                  </select>
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">收容原因</span>
                <div class="mgl90">
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="流浪犬">-->
                  <select id="whyy" @change="obtainWhy" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                    <option v-for="item in why" v-bind:value="item.value">{{item.name}}</option>
                  </select>
                </div>
              </div>
            </div>
            <!--<div class="ovh">-->
              <!--<div class="mgt10 ww100 fl">-->
                <!--<span class="db w100 fl h40 lh40 cor0 pdl30">收容时间</span>-->
                <!--<div class="mgl100">-->
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
            <div class="ovh">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">上传照片</span>
                <div class="mgl100">
                  <!--<img src="../../../../static/images/28-8.png" alt="">-->
                  <img-upload class="ovh" ref="imgup"></img-upload>
                </div>
              </div>
            </div>

          </div>
        </div>
        <div class="t-c mgt40 mgb50">
          <a style="cursor:pointer;" @click="save" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort">保存</a>
          <a style="cursor:pointer;" @click="cancel" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</a>
        </div>
      </form>
    </div>
    <!--内容区END-->
  </div>
</template>

<script>
  //收容登记
  import $ from 'jquery'
  import config from '../../../common/config.js'
    export default {
        name: "forRegistration",
      data() {
        return {
          data:{
            dogCode:null,//犬只编号
            doghouseNum:null,//犬舍编号
            dogName:null,//犬只名称
            dogBreedType:null,//品种
            dogGender:null,//性别
            dogAge:null,//犬龄
            dogColorType:null,//毛色
            weight:null,//体重
            dogCharacter:null,//特点
            healthStatus:null,//健康状况
            collDogType:null,//收容原因
            remarks:null,//备注
            orgId:config.userInfo.organizationId,
            districtId:config.userInfo.districtId,
            operatorId:config.userInfo.userId
          },
          datePattern:"yyyy年MM月dd日",
          why:[],//收容原因
          breedList:[],//品种
          colorList:[],
          id:null
        }
      },
      created(){
        initSelectOption(this);
        initWhy(this);
        this.data.orgId=config.userInfo.organizationId,
        this.data.districtId=config.userInfo.districtId,
        this.data.operatorId=config.userInfo.userId
      },
      mounted(){
        console.log(this.$data)
      },
      methods:{
        save() {
          this.$axios({
            method:'post',
            headers:{
              'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'  //这里加上头部信息
            },
            url:'/biz/collection/save',
            params:this.data
          }).then((res)=>{
            if(res.data.status==200){
              // this.data.id=res.data.data
              this.$refs.imgup.upload({type:3,id:res.data.data});//图片上传
              this.$alert("提交成功!","添加提示");
              this.$router.push({name:'dogShelter'});//其中dogShelter是你定义的一个路由模块
            }else {
              this.$alert("提交失败!","添加提示");
            }
          }).catch((res)=>{
            this.$alert("提交失败!","添加提示");
          })
        },
        cancel(){
          this.data={
            dogCode:null,//犬只编号
            doghouseNum:null,//犬舍编号
            dogName:null,//犬只名称
            dogBreedType:null,//品种
            dogGender:null,//性别
            dogAge:null,//犬龄
            dogColorType:null,//毛色
            weight:null,//体重
            dogCharacter:null,//特点
            healthStatus:null,//健康状况
            collDogType:null,//收容原因
            remarks:null,//备注
            orgId:config.userInfo.organizationId,
            districtId:config.userInfo.districtId,
            operatorId:config.userInfo.userId
          }
          this.$router.push('dogShelter');
        },
        //获取犬只品种
        obtainBreed:function () {
          this.data.dogBreedType=$("#breed").val();
        },
        //获取原因
        obtainWhy:function () {
          this.data.collDogType=$("#whyy").val();
        },
        //获取毛色
        obtainColor:function () {
          this.data.dogColorType=$("#color").val();
        }
      }
    }

  //初始化毛色 品种等
  function initSelectOption(_this) {
    _this.$axios({
      method: 'get',
      url: '/biz/dogInfo/findBreedColorDistrict'
      // params: {collectionId:1}
    }).then((res) => {
      if (res.data.meta.success) {
        if (res.data.data!=null) {
          var dataa=res.data.data
            _this.breedList=dataa.breedList,
            _this.colorList=dataa.colorList
        }
      }
    })
  }

  //初始化收容原因
  function initWhy(_this) {
    _this.$axios({
      method: 'get',
      url: '/biz/adoption/selectdogPro',
      params: {logg:3}
    }).then((res) => {
      console.log('222')
      console.log(res)
      if (res.data!=null) {
          _this.why=res.data
      }
    })
  }
</script>

<style scoped>

</style>
