<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li><router-link :to="{name:'dogManager_ImmuneList'}">免疫登记></router-link></li>
        <li>免疫登记</li>
      </ul>
    </div>
    <div class="mgt20 k3">
      <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">基本信息</div>
      <div class="pdt10 pdb20 pdr30">
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
            <span class="db w90 fl h40 lh40 cor0 pdl30">犬主姓名</span>
            <div class="mgl90">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                     v-if="!(dog.tDogOwner && dog.tDogOwner.dogOwnerName)">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                     :value="dog.tDogOwner.dogOwnerName">
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">联系电话</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                     v-if="!(dog.tDogOwner && dog.tDogOwner.dogOwnerPhone)">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                     :value="dog.tDogOwner.dogOwnerPhone">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">地址</span>
            <div class="mgl90">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                     v-if="!(dog.tDogOwner && dog.tDogOwner.address)">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                     :value="dog.tDogOwner.address">
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
            <span class="db w100 fl h40 lh40 cor0 pdl30">性别</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                     v-if="!(dog.tDogInfo && dog.tDogInfo.dogGender)">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                     :value="dog.tDogInfo.dogGender">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">毛色</span>
            <div class="mgl90">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                     v-if="!(dog.tDogInfo && dog.tDogInfo.dogColorDictionary.dictionaryDescribe)">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                     :value="dog.tDogInfo.dogColorDictionary.dictionaryDescribe">
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">免疫项目</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="vaccine_name">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">接种兽医</span>
            <div class="mgl90">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="userName" disabled>
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh20 cor0 pdl30">疫苗厂家<br>及批号</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="vaccine_producer">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">免疫时间</span>
            <div class="mgl90">
              <el-date-picker  v-model="cardDateStart" name="startTime" type="date" placeholder="选择日期" class="wd100b"
                               format="yyyy年MM月dd日">
              </el-date-picker>
            </div>
          </div>

        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w90 fl h40 lh20 cor0 pdl30">下次免疫<br>时间</span>
            <div class="mgl100">
              <el-date-picker  v-model="nextDateStart" name="startTime" type="date" placeholder="选择日期" class="wd100b"
                               format="yyyy年MM月dd日">
              </el-date-picker>
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww100 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">备注</span>
            <div class="mgl100">
              <textarea class="ww100 h140 pdl20 borf1 ras4" v-model="remarks"></textarea>
            </div>
          </div>
        </div>
      </div>
      <div class="t-c mgt40 mgb50">
        <!--<input type="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" value="保存">-->
        <input type="button" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" @click="tet" value="保存"/>
        <a href="/#/dogManager/ImmuneList" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</a>
      </div>
    </div>
  </div>
  <!--内容区END-->
</template>

<script>
  import  conf from "../../common/config"
    export default {
        name: "dogManagerImmuneAdd"
      ,data(){
          return{
            dog:{}//狗基本信息
            ,cardKeyWord: ''//免疫证输入值
            ,userName:conf.userInfo.name//接种兽医
            ,params: {
              input: ''
            },vaccine_name: ''//疫苗名称
            ,vaccine_producer: ''//疫苗厂家
            ,cardDateStart: ''//免疫时间

            ,nextDateStart: ''//下次免疫时间
            ,remarks:''//备注

          }
      },created(){
      },methods:{
        tet: function () {//点击提交添加病历信息
          add(this)
        },querySearchCrad(queryString, cb) {
        this.cardKeyWord = queryString;
        initSearchCardData(this, cb);
        },cradSelectHandle(item) {//免疫证号选择选择
          initData(this,item.value)
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
        console.log(res)
        _this.dog=res.data

        _this.dog.tDogInfo.dogGender=_this.dog.tDogInfo.dogGender==0?'公':'母'
        if(_this.dog.tDogInfo.birthTime!=null&&_this.dog.tDogInfo.birthTime!=''){
          _this.dog.tDogInfo.birthTime=CalAge(_this.dog.tDogInfo.birthTime);
        }
      }).catch((res)=>{
        console.log(res.message)
      });
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
    function add(_this){
      try{
        let tVaccine={};
        tVaccine.dog_id= _this.dog.tDogInfo.dogId
        tVaccine.org_id=conf.userInfo.organizationId;
        tVaccine.ref_id=_this.dog.tImmuneCard.seqId
        tVaccine.vaccine_name= _this.vaccine_name
        tVaccine.vaccine_producer=_this.vaccine_producer
        tVaccine.first_injection_date=_this.cardDateStart

        tVaccine.fact_injection_date=_this.cardDateStart
        tVaccine.next_injection_date=_this.nextDateStart
        tVaccine.operator_id=conf.userInfo.userId
        tVaccine.remarks=_this.remarks
        if(_this.cardKeyWord==null||_this.cardKeyWord==''){
          alert("请输入免疫证号")
          return
        }
        if(tVaccine.fact_injection_date==null||tVaccine.fact_injection_date==''){
          alert("请输入免疫时间")
          return
        }
        if(tVaccine.vaccine_name==null||tVaccine.vaccine_name==''){
          alert("请输入免疫项目")
          return
        }
        _this.$axios({
          url: '/biz/dogInfo/addTVaccine',
          params:tVaccine
        }).then((res) => {
          console.log(res.data)
          if(res.data){
            alert("添加成功")
            _this.$router.push({path:'ImmuneList'})
          }else {
            alert("添加失败")
          }
        }).catch((res)=>{
          alert("添加失败")
          console.log(res.message)
        });
      } catch (e) {
        alert("信息填写不完整！");
      } finally {
      }
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
