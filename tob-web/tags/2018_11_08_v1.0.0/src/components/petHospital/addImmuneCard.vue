<template>
    <!--免疫证办理-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="mbx">
        <ul class="ul11 clearfix">
          <li><router-link style="cursor:pointer;" :to="{name:'certificateDealt'}">免疫证办理></router-link></li>
          <li>免疫证办理</li>
        </ul>
      </div>
      <div class="mgt20 k3">
        <div class="pdt10 pdb20 pdr30">
          <!--<div class="ovh">-->
            <!--<div class="mgt10 ww50 fl">-->
              <!--<span class="db w100 fl h40 lh40 cor0 pdl30">免疫证号</span>-->
              <!--<div class="mgl100">-->
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
              <!--</div>-->
            <!--</div>-->
            <!--<div class="mgt10 ww50 fr">-->
              <!--<span class="db w90 fl h40 lh40 cor0 pdl30">签发机构</span>-->
              <!--<div class="mgl90">-->
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">签发日期</span>
              <div class="mgl100">
                <el-date-picker v-model="dogInfo.dogImmune.lssueTime" type="date" class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择签发日期" :format="datePattern"></el-date-picker>
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">有效期限<b style="color: red">&nbsp;*</b></span>
              <div class="mgl100">
                <el-date-picker v-model="dogInfo.dogImmune.startTime" type="date" class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择有效期限" :format="datePattern"></el-date-picker>
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w100 fl h40 lh40 cor0"><img src="../../../static/images/bg14.png" alt=""></span>
              <div class="mgl100">
                <el-date-picker v-model="dogInfo.dogImmune.endTime" type="date" class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择有效期限" :format="datePattern"></el-date-picker>
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="mgt20 k3">
        <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">犬只信息</div>
        <div class="pdt10 pdb20 pdr30">
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">犬名<b style="color: red">&nbsp;*</b></span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model.trim="dogInfo.dogName">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">品种<b style="color: red">&nbsp;*</b></span>
              <div class="mgl90">
                <select id="breed" @change="obtainBreed" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                  <option>请选择</option>
                  <option v-for="item in breedList" v-bind:value="item.dictionaryValue">{{item.dictionaryDescribe}}</option>
                </select>
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">性别<b style="color: red">&nbsp;*</b></span>
              <div class="mgl100 h40 lh40">
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                <label class="lab2 mgr40"><input v-model="dogInfo.dogGender" type="radio" name="abc" value="0"><i></i>雄</label>
                <label class="lab2"><input type="radio" v-model="dogInfo.dogGender" name="abc" value="1"><i></i>雌</label>
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">犬龄</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :disabled=true>
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">出生日期<b style="color: red">&nbsp;*</b></span>
              <div class="mgl100">
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                <el-date-picker v-model="dogInfo.dogImmune.birthTime" type="date" class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择出生日期" :format="datePattern"></el-date-picker>
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">毛色<b style="color: red">&nbsp;*</b></span>
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
              <span class="db w100 fl h40 lh40 cor0 pdl30">父体</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model.trim="dogInfo.fatherId">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">母体</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model.trim="dogInfo.motherId">
              </div>
            </div>
          </div>
          <div class="mgt20">
            <span class="db w100 fl h40 lh40 pdl30 cor0">上传照片</span>
            <div class="mgl100 ovh">

              <img-upload class="ovh" ref="imgup"></img-upload>

              <!--<ul class="ovh fl ul10">-->
                <!--<li class="fl"><img src="../../../static/images/28-1.png" alt=""></li>-->
                <!--<li class="fl"><img src="../../../static/images/28-2.png" alt=""></li>-->
                <!--<li class="fl"><img src="../../../static/images/28-3.png" alt=""></li>-->
              <!--</ul>-->
              <!--<div class="file fl rel w202 h160">-->
                <!--<input type="file" class="abs z10 w202 h160 left0 top0">-->
                <!--<img class="rel z1" src="../../../static/images/28-8.png">-->
                <!--<p class="abs cor9 ww100 z8 left0 top90 t-c">上传照片</p>-->
              <!--</div>-->
            </div>
          </div>

        </div>
      </div>
      <div class="mgt15 k3">
        <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">犬主信息</div>
        <div class="pdb20 pdr30 ovh">
          <div class="w950 pdt10">
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">犬主电话<b style="color: red">&nbsp;*</b></span>
                <div class="mgl100">
                  <input type="text" id="phone" @change="selectOwner" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model.trim="dogInfo.dogOwner.dogOwnerPhone">
                </div>
              </div>
            </div>
            <div id="remaining" style="display: none">
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">犬主姓名<b style="color: red">&nbsp;*</b></span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model.trim="dogInfo.dogOwner.dogOwnerName">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">性质<b style="color: red">&nbsp;*</b></span>
                <div class="mgl90">
                  <select v-model="dogInfo.dogOwner.dogOwnerType" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                    <option value="0">个人</option>
                    <option value="1">企业</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">证件类型<b style="color: red">&nbsp;*</b></span>
                <div class="mgl100">
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                  <select v-model="dogInfo.dogOwner.tDogOwnerCard.cardType" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                    <option value="0">身份证</option>
                    <option value="1">护照</option>
                    <option value="2">社保卡</option>
                    <option value="3">其他</option>
                  </select>
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">证件号码<b style="color: red">&nbsp;*</b></span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model.trim="dogInfo.dogOwner.tDogOwnerCard.cardNum">
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">性别<b style="color: red">&nbsp;*</b></span>
                <div class="mgl100 h40 lh40">
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                  <label class="lab2 mgr40"><input v-model="dogInfo.dogOwner.sex" type="radio" name="abc" value="0"><i></i>男</label>
                  <label class="lab2"><input type="radio" v-model="dogInfo.dogOwner.sex" name="abc" value="1"><i></i>女</label>
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">出生日期<b style="color: red">&nbsp;*</b></span>
                <div class="mgl90">
                  <el-date-picker v-model="dogInfo.dogOwner.birthDate" type="date" class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                  placeholder="选择出生日期" :format="datePattern"></el-date-picker>
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="dogInfo.dogOwner.birthDate">-->
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">现住区县<b style="color: red">&nbsp;*</b></span>
                <div class="mgl100">
                  <select id="district" @change="obtainDistrict" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                    <option v-for="item in disTrictList" v-bind:value="item.districtId">{{item.districtName}}</option>
                  </select>
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">所属部门</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :disabled=true>
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">所属社区</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model.trim="dogInfo.dogOwner.community">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">邮编<b style="color: red">&nbsp;*</b></span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model.trim="dogInfo.dogOwner.postalCode">
                </div>
              </div>
            </div>
            <div class="ovh">
              <!--<div class="mgt10 ww50 fl">-->
                <!--<span class="db w100 fl h40 lh40 cor0 pdl30">联系电话</span>-->
                <!--<div class="mgl100">-->
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="dogInfo.dogOwner.dogOwnerPhone">-->
                <!--</div>-->
              <!--</div>-->
              <!--<div class="mgt10 ww50 fr">-->
                <!--<span class="db w90 fl h40 lh40 cor0 pdl30">电子邮箱</span>-->
                <!--<div class="mgl90">-->
                  <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="dogInfo.dogOwner.eMail">-->
                <!--</div>-->
              <!--</div>-->
              <div class="mgt10 ww50 fl">
                <span class="db w90 fl h40 lh40 cor0 pdl30">电子邮箱<b style="color: red">&nbsp;*</b></span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model.trim="dogInfo.dogOwner.eMail">
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">详细地址<b style="color: red">&nbsp;*</b></span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model.trim="dogInfo.dogOwner.address">
                </div>
              </div>
            </div>
            <!--<div class="ovh">-->
              <!--<div class="mgt10 ww50 fl">-->
                <!--<span class="db w100 fl h40 lh40 cor0 pdl30">照片</span>-->
                <!--<div class="mgl100">-->
                  <!--<img src="../../../static/images/28-8.png" alt="">-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
            </div>
          </div>
        </div>
        <div class="t-c mgt40 mgb50">
          <!--<a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8">打印</a>-->
          <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" @click="save">提交</a>
          <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8" @click="fromReset">取消</a>
        </div>
      </div>

    </div>
    <!--内容区END-->
  </div>
</template>

<script>
  import $ from 'jquery'
  import config from '../../common/config'
    export default {
        name: "addImmuneCard",
      data() {
        return {
          dogInfo:
            {dogName:null,//犬名
              dogBreedType:null,//犬品种
              dogGender:null,//犬性别
              birthTime:null,//犬只出生日期
              dogColorType:null,//犬只颜色
              fatherId:null,//父本
              motherId:null,  //母本
              dogImmune: {//免疫证信息
                  lssuerId:config.userInfo.userId,//签证人id
                  immuneCardNum:null,//免疫证号
                  lssueTime:null,//签发日期
                  startTime:null,//生效时间
                  endTime:null,//失效时间
                  lssueOrgId:config.userInfo.organizationId//签发机构
                },
              dogOwner: {//犬主信息
                  dogOwnerId:null,
                  dogOwnerName:null,//犬主姓名
                  dogOwnerType:null,//性质
                  sex:null,//性别
                  birthDate:null,//出生日期
                  districtId:null,//现住区县
                  community:null,//所属社区
                  postalCode:null,//邮编
                  dogOwnerPhone:null,//电话
                  eMail:null,//邮件
                  address:null,//地址
                  tDogOwnerCard:{
                    cardType:null,//证件类型
                    cardNum:null//证件号码
                  }
                }
            },
          Id:null,
          type:2,
          datePattern:"yyyy年MM月dd日",
          disTrictList:null,
          breedList:[],
          colorList:[],
          datas:{
            url:'',
            uploadData:null
          }
        }
      },


      created() {
        initSelectOption(this);
      },
      watch: {
        formData() {
          console.log(this.dogInfo)
        }
      },
      methods:{
        save(){
          let alertMsg = '录入信息有误！';
          let flag = true;
          // let {phone, blackName } =  this.formData;
          if(!this.dogInfo.dogOwner.dogOwnerPhone || !/^1[34578]\d{9}$/.test(this.dogInfo.dogOwner.dogOwnerPhone) ){
            alertMsg = "电话号码格式有误！";
            flag = false
          }
          if(!this.dogInfo.dogOwner.eMail || !/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(this.dogInfo.dogOwner.dogOwnerPhone) ){
            alertMsg = "邮箱格式有误！";
            flag = false
          }
          if(!this.dogInfo.dogImmune.startTime){
            alertMsg='有效期限必须填写'
            flag = false
          }
          if(!this.dogInfo.dogImmune.endTime){
            alertMsg='有效期限必须填写'
            flag = false
          }
          if(!this.dogInfo.dogBreedType){
            alertMsg='犬只品种必须选择'
            flag = false
          }
          if(!this.dogInfo.dogColorType){
            alertMsg='犬只毛色必须选择'
            flag = false
          }
          if (!flag) {
            this.$alert(alertMsg);
          }
          if(flag){
            this.$axios({
              method:'post',
              // headers:{
              //   "content-type": "application/json;charset=utf-8",
              // },
              headers:{
                'Content-Type': 'application/json;charset=UTF-8'  //这里加上头部信息
              },
              url:'/biz/dogInfo/insertImmuneMsg',
              data:JSON.stringify(this.dogInfo)
            }).then((res)=>{
              if(res.data.data!=null && res.data.data!=""){
                console.log("111")
                console.log(res.data.data)
                this.Id=res.data.data;
                this.$refs.imgup.upload({type:2,id:res.data.data});//图片上传
                this.$alert("录入成功!","添加提示");
                this.$router.push('certificateDealt');//其中certificateDealt是你定义的一个路由模块
              }else {
                this.$alert("录入失败!","添加提示");
              }
            }).catch(_=>{
              this.$alert("录入失败!","添加提示");
            })
          }
        },
        fromReset(){
          this.dogInfo= {dogName:'',//犬名
            dogBreedType:'',//犬品种
            dogGender:'',//犬性别
            birthTime:'',//犬只出生日期
            dogColorType:'',//犬只颜色
            fatherId:'',//父本
            motherId:'',  //母本
            dogImmune:{//免疫证信息
            lssuerId:'',//签证人id
              immuneCardNum:'',//免疫证号
              lssueTime:'',//签发日期
              startTime:'',//生效时间
              endTime:'',//失效时间
              lssueOrgId:''//签发机构
          },
            dogOwner: {//犬主信息
              dogOwnerName:'',//犬主姓名
                dogOwnerType:'',//性质
                sex:'',//性别
                birthDate:'',//出生日期
                districtId:'',//现住区县
                community:'',//所属社区
                postalCode:'',//邮编
                dogOwnerPhone:'',//电话
                eMail:'',//邮件
                address:'',//地址
                tDogOwnerCard:{
                cardType:'',//证件类型
                  cardNum:''//证件号码
              }
            }
          }
          console.log(this.dogInfo)
          this.$router.push('certificateDealt');//其中certificateDealt是你定义的一个路由模块
        },
        CalAge:function (date1) {
          if (date1==null || date1==undefined){
            return "";
          }
          var datetime1 = new Date(date1);
          var datetime2 = new Date();
          var year1 = datetime1.getFullYear();
          var year2 = datetime2.getFullYear();
          var age=year2-year1+1;
          return age+"岁";
        },
        //获取犬只品种
        obtainBreed:function () {
          this.dogInfo.dogBreedType=$("#breed").val();
        },
        //获取社区
        obtainDistrict:function () {
          this.dogInfo.dogOwner.districtId=$("#district").val();
        },
        //获取毛色
        obtainColor:function () {
          this.dogInfo.dogColorType=$("#color").val();
        },
        //根据电话获取犬主信息
        selectOwner:function () {
          this.$axios({
            method:'get',
            url:'/biz/dogAndOwner/selectDogOwnersByPhoneee',
            params:{"phone":$("#phone").val().trim()}
          }).then((res) => {
            console.log(res)
            if (res.data.data!=null && JSON.stringify(res.data.data) !="[]") {
              if (res.data.data[0].dogOwnerId!=null) {
                this.dogInfo.dogOwner.dogOwnerId=res.data.data[0].dogOwnerId;
                this.dogInfo.dogOwner.dogOwnerName=res.data.data[0].dogOwnerName;
                this.dogInfo.dogOwner.dogOwnerType=res.data.data[0].dogOwnerType;
                this.dogInfo.dogOwner.sex=res.data.data[0].sex;
                this.dogInfo.dogOwner.districtId=res.data.data[0].districtId;
                this.dogInfo.dogOwner.postalCode=res.data.data[0].postalCode;
                this.dogInfo.dogOwner.dogOwnerPhone=res.data.data[0].dogOwnerPhone;
                this.dogInfo.dogOwner.eMail=res.data.data[0].eMail;
                this.dogInfo.dogOwner.address=res.data.data[0].address;
                $("#remaining input").prop("disabled",true);
                $("#remaining").show();
              }
            }else {
              $("#remaining input").prop("disabled",false);
              $("#remaining input").val("");
              $("#remaining").show();
            }
          })
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
            _this.disTrictList=dataa.disTrictList,
            _this.breedList=dataa.breedList,
            _this.colorList=dataa.colorList
          }
        }
      })
    }

</script>

<style scoped>
  select{
    height: 39px;
  }
  .dataSelect{
    width: 100%;
    height: 39px;
    border: 0px;
  }
</style>
