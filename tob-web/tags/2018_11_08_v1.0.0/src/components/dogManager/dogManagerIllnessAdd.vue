<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li><router-link :to="{name:'dogManager_IllnessList'}">病历记录></router-link></li>
        <li>新增病例</li>
      </ul>
    </div>
    <form action="#">
      <div class="mgt20 k3">
        <div class="h60 lh60 pdl30 bgf8 fs16 cor0 fb">基本信息</div>
        <div class="pdt10 pdr30" >
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w90 fl h40 lh40 cor0 pdl30">免疫证号</span>
              <div class="mgl100">
                <el-tooltip class="item" effect="dark" content="输入免疫证号搜索" placement="right">
                  <el-autocomplete
                    class="inline-input ww100 h40 lh40 ras4"
                    v-model="cardKeyWord"
                    :fetch-suggestions="querySearchCrad"
                    placeholder="请输入内容搜索"
                    :trigger-on-focus="false"
                    @select="cradSelectHandle"
                  ></el-autocomplete>
                </el-tooltip>
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">犬证号</span>
              <div class="mgl90" >
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dog.tDogCard && dog.tDogCard.dogCardNum)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dog.tDogCard.dogCardNum">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">犬主姓名</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dog.tDogOwner && dog.tDogOwner.dogOwnerName)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dog.tDogOwner.dogOwnerName">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">联系电话</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dog.tDogOwner && dog.tDogOwner.dogOwnerPhone)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dog.tDogOwner.dogOwnerPhone">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">犬只姓名</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dog.tDogInfo && dog.tDogInfo.dogName)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dog.tDogInfo.dogName">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">性别</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dog.tDogInfo && dog.tDogInfo.dogGender)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dog.tDogInfo.dogGender">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">犬只品种</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dog.tDogInfo && dog.tDogInfo.dogBreedDictionary.dictionaryDescribe)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dog.tDogInfo.dogBreedDictionary.dictionaryDescribe">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">犬龄</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dog.tDogInfo && dog.tDogInfo.birthTime)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dog.tDogInfo.birthTime">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">毛色</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dog.tDogInfo && dog.tDogInfo.dogColorDictionary.dictionaryDescribe)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dog.tDogInfo.dogColorDictionary.dictionaryDescribe">
              </div>
            </div>
          </div>

        </div>
        <div class="h60 lh60 pdl30 bgf8 fs16 cor0 fb">录入病历信息</div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">医生姓名</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="physicianName">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">症状描述</span>
            <div class="mgl90">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="symptom">
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">诊断</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="diagnose">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">治疗信息</span>
            <div class="mgl90">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="treatmentInfo">
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">备注信息</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="remarks">
            </div>
          </div>
        </div>
        <div class="t-c mgt40 mgb50">
          <input type="button" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" @click="tet" value="提交"/>
          <!--<input type="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" value="提交" @click="tet">-->
          <a href="/#/dogManager/IllnessList" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</a>
        </div>
      </div>
      <div class="ovh mgt20">
        <div class="fl cor6 h40 lh40">
          <a href="###" class="db fl hov8 corffa472 mgr35 pc9 pdl36">新增</a>
        </div>
      </div>

      <div class="mgt10 k3">
        <div class="h60 lh60 pdl30 bgf8 fs16 cor0 fb">病历记录</div>
        <div class="pdt10">
          <textarea class="h170 pdl15 pdr15 pdt10 pdb10 cor3 db ww100 bort"></textarea>
        </div>
      </div>
    </form>
  </div>
  <!--内容区END-->
</template>

<script>
  import  conf from "../../common/config"
  export default {
    name: "dogManagerIllnessAdd",
    data(){
      return{
        dog:{}//基本信息
        ,cardKeyWord: ''//免疫证输入值
        ,params: {
          input: ''
        },physicianName:""//医名称
        ,symptom:""//症状描述
        ,diagnose:""//诊断
        ,treatmentInfo:""//治疗信息
        ,remarks:""//备注信息
          //keyword
        //,dataFormatPattern :'yyyy年MM月dd日',
      }
    },
    created(){
      initData(this)

    },
    methods:{
      tet: function () {//点击提交添加病历信息
        add(this)
      },querySearchCrad(queryString, cb) {
        this.cardKeyWord = queryString;
        initSearchCardData(this, cb);
      },cradSelectHandle(item) {//免疫证号选择选择
        initData(this,item.value)
      },
      //搜索
      search(){
      //initData(this)
      }
    }
  }
  function initData(_this,immuneCardNum){
    if(!_this.params.input || _this.params.input.trim().length==0){
      //_this.params.input='1321654q';
    }else {
      //alert("搜索"+_this.params.input)
    }
    _this.$axios({
      url: '/biz/dogInfo/findDogInspectionByImmuneCode',//得到基本信息
      params: {"immuneCardNum":immuneCardNum}
    }).then((res) => {
      //console.log(res)
      _this.dog=res.data
      _this.dog.tDogInfo.dogGender=_this.dog.tDogInfo.dogGender==0?'公':'母'
      if(_this.dog.tDogInfo.birthTime!=null&&_this.dog.tDogInfo.birthTime!=''){
        _this.dog.tDogInfo.birthTime=CalAge(_this.dog.tDogInfo.birthTime);
      }
    }).catch((res)=>{
      console.log(res.message)
    });
  }
  function add(_this){
    if(_this.cardKeyWord==null||_this.cardKeyWord==''){
      alert("请填入免疫证号")
      return;
    }

    try{
      let dogCardNum=_this.dog.tDogCard.dogCardNum;//如果没有犬证号进入异常
      let tTherapy={};
      tTherapy.orgId=conf.userInfo.organizationId;
      tTherapy.physicianName=_this.physicianName
      tTherapy.dogId=_this.dog.tDogInfo.dogId
      tTherapy.dogOwnerId= _this.dog.tDogInfo.dogOwnerId
      tTherapy.operationId=conf.userInfo.userId
      tTherapy.diagnose=_this.diagnose
      tTherapy.treatmentInfo=_this.treatmentInfo
      tTherapy.remarks=_this.remarks
      tTherapy.symptom=_this.symptom
      _this.$axios({
        url: '/biz/dogInfo/insertTherapyRecord',
        params:tTherapy
      }).then((res) => {
        if(res.data>0){
          alert("添加成功")
          _this.$router.push({path:'IllnessList'})
        }
      }).catch((res)=>{
        alert("添加失败")
        console.log(res.message)
      });
    } catch (e) {
    alert("该免疫证号信息不完整，请联系管理！");
    } finally {
    }
  }
  function initSearchCardData(_this, cb) {
    _this.$axios({
      url: '/biz/dogInfo/selectImmuneCardNums',
      params: {"input": _this.cardKeyWord}
    }).then((res) => {
      if (res.data.code == '000000') {
        //输入框下拉逻辑
        if (cb) {
          let temp = [];
          res.data.result.map(item => {
            temp.push({"value": item, "address": item})//固定格式返回
          })
          cb(isNone(temp))
        }
      }
    }).catch(r => console.log(r))
  }

  //判断搜索有无结果
  function isNone(array) {
    if (!array || array.length == 0) {
      return [{
        value: '无匹配',
        address: '无匹配'
      }]
    }
    return array;
  }
  function CalAge(date1) {
    var datetime1 = new Date(date1);
    var datetime2 = new Date();
    var year1 = datetime1.getFullYear();
    var year2 = datetime2.getFullYear();
    var age=year2-year1+1;
    return age+"岁";
  }
</script>

<style scoped>

</style>
