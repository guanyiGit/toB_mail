<template>
  <!--服务网点注册-->
  <div>
    <!--投诉建议-->
    <form action="#">
      <div class="w1200 c-b mgt60">
        <div class="k2 w660 c-b ovh">
          <a href="###" class="db fl fs16 cor0 w220"><img class="v-m mgr10" src="../../../static/images/30.png" alt="">宠物医院提交申请</a>
          <a href="###" class="db fl fs16 cor0 w220 t-c active">平台审核</a>
          <a href="###" class="db fl fs16 cor0 w220 t-c">结束</a>
        </div>
      </div>
      <div class="w1200 c-b mgt30 bord">
        <p class="fs16 cor0 fb h60 lh60 bgf1 pdl30">基本信息</p>
        <div class="pdt10 pdb60 pdl30 pdr30">
          <div class="ovh">
            <div class="mgt10 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">医院名称<font color="red"> *</font></span>
              <el-tooltip class="item" effect="dark"
                          :disabled="data.organizationName&&data.organizationName.trim().length>=4?true:false"
                          content="医院名称需大于4个字符" placement="right">
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.organizationName">
                </div>
              </el-tooltip>
            </div>
            <div class="mgt10 w550 fr">
              <span class="db w90 fl h40 lh40 cor0">注册资本(万)<font color="red"> *</font></span>
              <el-tooltip class="item" effect="dark"
                          :disabled="data.organizationCapital&&/[^\d]/g.test(data.organizationCapital)?true:false"
                          content="输入值不合法" placement="right">
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.organizationCapital">
                </div>
              </el-tooltip>
            </div>
          </div>

          <div class="ovh">
            <div class="mgt10 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">注册号<font color="red"> *</font></span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.registerNumber">
              </div>
            </div>
            <div class="mgt10 w550 fr">
              <span class="db w90 fl h40 lh20 cor0">统一社会<br>信用代码<font color="red"> *</font></span>
              <el-tooltip class="item" effect="dark"
                          :disabled="data.creditCode&&/[^_IOZSVa-z\W]{2}\d{6}[^_IOZSVa-z\W]{10}/g.test(data.creditCode)?true:false"
                          content="输入值不合法" placement="right">
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.creditCode">
                </div>
              </el-tooltip>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">成立日期<font color="red"> *</font></span>
              <div class="mgl90">
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
                <el-date-picker v-model="data.establishDate" style="width: 100%;" type="date"
                                class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择成立日期" :format="datePattern"></el-date-picker>
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">联系人<font color="red"> *</font></span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.personLiable">
              </div>
            </div>
            <div class="mgt10 w550 fr">
              <span class="db w90 fl h40 lh40 cor0">联系电话<font color="red"> *</font></span>
              <el-tooltip class="item" effect="dark"
                          :disabled="data.phone&&/0?(13|14|15|17|18|19)[0-9]{9}/.test(data.phone)?true:false"
                          content="输入值不合法" placement="right">
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.phone">
                </div>
              </el-tooltip>
            </div>
          </div>
          <div class="ovh">
            <span class="db w90 fl h40 lh40 cor0 mgt10">所属地区</span>
            <!--<div class="city-picker-selector h40 lh40" id="city-picker-selector2" style=""></div>-->
            <v-distpicker wrapper="wrapper-city" address-container="test" :placeholders="citySelectData.placeholders"
                          :province="citySelectData.defaultVal" @province="provinceChanged"
                          @city="cityChanged" @area="areaChanged"
            ></v-distpicker>
          </div>
          <div class="ovh">
            <div class="mgt10">
              <span class="db w90 fl h40 lh40 cor0">详细地址<font color="red"> *</font></span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.address"
                       @change="addressChange">
              </div>
            </div>
          </div>
          <div class="mgt10">
            <span class="db w90 fl h40 lh40 cor0">经营范围<font color="red"> *</font></span>
            <div class="mgl90">
              <textarea class="ww100 h104 pdl20 borf1 ras4" v-model="data.scopeOfOperation"></textarea>
            </div>
          </div>
          <div class="mgt20">
            <span class="db w90 fl h40 lh40 cor0">上传照片</span>
            <div class="mgl90 ovh">
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
              <img-upload class="ovh" ref="imgup"></img-upload>
            </div>
          </div>

        </div>
      </div>
      <div class="w1200 c-b mgt18 bord">
        <p class="fs16 cor0 fb h60 lh60 bgf1 pdl30">证件信息</p>
        <div class="pdt10 pdb60 pdl30 pdr30">
          <p class="mgt20 cor0 h20 lh20"><span class="db fl mgr10 sp2 w39 h20 lh20 t-c corw fs12">1</span>动物诊疗许可证</p>
          <div class="ovh">
            <div class="mgt20 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">证件号码<font color="red"> *</font></span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.petHospitals[0].petHCardNum">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt20 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">有效期限</span>
              <div class="mgl90">
                <el-date-picker v-model="data.petHospitals[0].startTime" type="date"
                                class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择起始日期" :format="datePattern"></el-date-picker>
              </div>
            </div>
            <div class="mgt20 w550 fr">
              <!--<span class="db w100 fl h40 lh40 cor0"><img src="../../../static/images/bg14.png" alt=""></span>-->
              <div class="mgl90">
                <el-date-picker v-model="data.petHospitals[0].endTime" type="date"
                                class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择结束日期" :format="datePattern"></el-date-picker>
              </div>
            </div>
          </div>
          <div class="mgt20">
            <span class="db w90 fl h40 lh40 cor0">图片</span>
            <div class="mgl90">
              <!--<img src="../../../static/images/33.png" alt="">-->
              <img-upload class="ovh" ref="imgup1"></img-upload>
            </div>
          </div>

          <p class="mgt20 cor0 h20 lh20"><span class="db fl mgr10 sp2 w39 h20 lh20 t-c corw fs12">2</span>动物合格免疫证</p>
          <div class="ovh">
            <div class="mgt20 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">证件号码<font color="red"> *</font></span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.petHospitals[1].petHCardNum">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt20 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">有效期限</span>
              <div class="mgl90">
                <el-date-picker v-model="data.petHospitals[1].startTime" type="date"
                                class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择起始日期" :format="datePattern"></el-date-picker>
              </div>
            </div>
            <div class="mgt20 w550 fr">
              <!--<span class="db w100 fl h40 lh40 cor0"><img src="../../../static/images/bg14.png" alt=""></span>-->
              <div class="mgl90">
                <el-date-picker v-model="data.petHospitals[1].endTime" type="date"
                                class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择结束日期" :format="datePattern"></el-date-picker>
              </div>
            </div>
          </div>
          <div class="mgt20">
            <span class="db w90 fl h40 lh40 cor0">图片</span>
            <div class="mgl90">
              <!--<img src="../../../static/images/34.png" alt="">-->
              <img-upload class="ovh" ref="imgup2"></img-upload>
            </div>
          </div>

          <p class="mgt20 cor0 h20 lh20"><span class="db fl mgr10 sp2 w39 h20 lh20 t-c corw fs12">3</span>工商营业执照</p>
          <div class="ovh">
            <div class="mgt20 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">证件号码<font color="red"> *</font></span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.petHospitals[2].petHCardNum">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt20 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">有效期限</span>
              <div class="mgl90">
                <el-date-picker v-model="data.petHospitals[2].startTime" type="date"
                                class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择起始日期" :format="datePattern"></el-date-picker>
              </div>
            </div>
            <div class="mgt20 w550 fr">
              <!--<span class="db w100 fl h40 lh40 cor0"><img src="../../../static/images/bg14.png" alt=""></span>-->
              <div class="mgl90">
                <el-date-picker v-model="data.petHospitals[2].endTime" type="date"
                                class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择结束日期" :format="datePattern"></el-date-picker>
              </div>
            </div>
          </div>
          <div class="mgt20">
            <span class="db w90 fl h40 lh40 cor0">图片</span>
            <div class="mgl90">
              <!--<img src="../../../static/images/35.png" alt="">-->
              <img-upload class="ovh" ref="imgup3"></img-upload>
            </div>
          </div>

          <p class="mgt20 cor0 h20 lh20"><span class="db fl mgr10 sp2 w39 h20 lh20 t-c corw fs12">4</span>兽医资格证</p>
          <div class="ovh">
            <div class="mgt20 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">证件号码<font color="red"> *</font></span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="data.petHospitals[3].petHCardNum">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt20 w550 fl">
              <span class="db w90 fl h40 lh40 cor0">有效期限</span>
              <div class="mgl90">
                <el-date-picker v-model="data.petHospitals[3].startTime" type="date"
                                class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择起始日期" :format="datePattern"></el-date-picker>
              </div>
            </div>
            <div class="mgt20 w550 fr">
              <!--<span class="db w100 fl h40 lh40 cor0"><img src="../../../static/images/bg14.png" alt=""></span>-->
              <div class="mgl90">
                <el-date-picker v-model="data.petHospitals[3].endTime" type="date"
                                class="ww100 h40 lh40 borf1 ras4 dataSelect"
                                placeholder="选择结束日期" :format="datePattern"></el-date-picker>
              </div>
            </div>
          </div>
          <div class="mgt20">
            <span class="db w90 fl h40 lh40 cor0">图片</span>
            <div class="mgl90">
              <!--<img src="../../../static/images/36.png" alt="">-->
              <img-upload class="ovh" ref="imgup4"></img-upload>
            </div>
          </div>

          <div class="t-c mgt40">
            <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" @click="save">提交</a>
            <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5" @click="cancel">取消</a>
          </div>


        </div>
      </div>
    </form>
    <!--投诉建议END-->
  </div>

</template>

<script>
  import VDistpicker from 'v-distpicker'

  //服务网点注册
  let datae = {
    provinceId: 440000,//省
    ownedCityId: 440500,//市
    districtId: 440507,//区
  }
  export default {
    name: "siteRegistration",
    data() {
      return {
        data: {
          organizationName: null,//医院名称
          organizationCapital: null,//注册资本
          registerNumber: null,//注册号
          creditCode: null,//统一社会信用代码
          establishDate: null,//成立时间
          provinceId: null,//省
          ownedCityId: null,//市
          districtId: null,//区
          phone: null,//联系电话
          personLiable: null,//联系人
          orgType: 2,//机构类型
          scopeOfOperation: null,//经营范围
          address: null,
          longitude: '',
          latitude: '',
          petHospitals: [
            {//动物诊疗许可证
              petHCardType: 0,
              petHCardNum: null,
              startTime: null,
              endTime: null
            },
            {//动物合格免疫证
              petHCardType: 1,
              petHCardNum: null,
              startTime: null,
              endTime: null
            },
            {//工商营业执照
              petHCardType: 2,
              petHCardNum: null,
              startTime: null,
              endTime: null
            },
            {//兽医资格证
              petHCardType: 3,
              petHCardNum: null,
              startTime: null,
              endTime: null
            }
          ]
        },
        datePattern: "yyyy年MM月dd日",
        citySelectData: {//地区选择器
          defaultVal: '青海省',
          placeholders: {
            province: '------- 省 --------',
            city: '-------- 市 --------',
            area: '-------- 区 --------'
          },
          select: {//被选择的城市信息

          }
        },
      }
    },
    components: {VDistpicker},
    created() {
    },
    watch: {},
    mounted() {
      var selector2 = $('#city-picker-selector2').cityPicker({
        dataJson: cityData,
        renderMode: true,
        search: false,
        autoSelected: true,
        code: 'cityCode',
        level: 3,
        onChoiceEnd: function () {
          datae.provinceId = this.values[0].id;
          datae.ownedCityId = this.values[1].id;
          datae.districtId = this.values[2].id;
        }
      });
    },
    methods: {
      //输入地址
      addressChange() {
        let temp = this.data.address
        if (temp && temp.trim().length > 3) {
          //输入完成查找地址坐标信息
          this.$axios({
            url: '/biz/serviceSite/searchPosition',
            params: {'addressName': this.data.address}
          }).then(res => {
            if (!res.data.meta.success) {
              this.$message("该地址无法获得详细坐标信息，搜索【附近的】无法找到！")
            } else {
              this.data.longitude = res.data.data.longitude;
              this.data.latitude = res.data.data.latitude;
            }
            console.log(this.data)
          })

        }
      },
      provinceChanged(e) {//省
        this.data.provinceId = e.code;
        //{code: "350300", value: "莆田市"}
      },
      cityChanged(e) {//市
        this.data.ownedCityId = e.code;
      },
      areaChanged(e) {//区
        this.data.districtId = e.code;
      },
      save() {
        if (!this.data.organizationName || this.data.organizationName.trim().length <= 2) {
          this.$alert("医院名称填写有误！")
          return;
        }
        if (!this.data.organizationCapital || !/[^\d]/g.test(this.data.organizationName)) {
          this.$alert("注册资金填写有误！")
          return;
        }
        if (!this.data.registerNumber || this.data.registerNumber.trim().length == 0) {
          this.$alert("注册号必须填写！")
          return;
        }
        if (!this.data.creditCode || !/[^_IOZSVa-z\W]{2}\d{6}[^_IOZSVa-z\W]{10}/g.test(this.data.creditCode)) {
          this.$alert("信用代码有误！")
          return;
        }
        if (!this.data.establishDate) {
          this.$alert("成立日期必须填写！")
          flag = false
        }
        if (!this.data.establishDate || this.data.establishDate >= new Date()) {
          this.$alert("成立日期有误！")
          return;
        }
        if (!this.data.personLiable || this.data.personLiable.trim().length == 0) {
          this.$alert("联系人不能为空！")
          return;
        }

        if (!this.data.phone || !/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test(this.data.phone)) {
          this.$alert("电话号码格式有误！");
          return;
        }

        if (!this.data.provinceId) {
          this.$alert('所属省会必须选择')
          return;
        }
        if (!this.data.ownedCityId) {
          this.$alert('所属地市必须选择')
          return;
        }
        if (!this.data.districtId) {
          this.$alert('所属区县必须选择')
          return;
        }
        if (!this.data.address || this.data.address.trim().length == 0) {
          this.$alert('地址信息必须填写！')
          return;
        }
        if (!this.data.scopeOfOperation || this.data.scopeOfOperation.trim().length == 0) {
          this.$alert('地址信息必须填写！')
          return;
        }

        if (!this.data.petHospitals[0].petHCardNum || this.data.petHospitals[0].petHCardNum.trim().length == 0) {
          this.$alert('诊疗许可证书号码填写有误！')
          return;
        }

        if (!this.data.petHospitals[1].petHCardNum || this.data.petHospitals[1].petHCardNum.trim().length == 0) {
          this.$alert('动物合格证书号码填写有误！')
          return;
        }

        if (!this.data.petHospitals[2].petHCardNum || this.data.petHospitals[2].petHCardNum.trim().length == 0) {
          this.$alert('工商营业执照号码填写有误！')
          return;
        }

        if (!this.data.petHospitals[3].petHCardNum || this.data.petHospitals[3].petHCardNum.trim().length == 0) {
          this.$alert('兽医资格证书号码填写有误！')
          return;
        }
        this.$axios({
          method: 'post',
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'  //这里加上头部信息
          },
          url: '/biz/serviceSite/add',
          data: JSON.stringify(this.data),
          isloading: true
        }).then((res) => {
          console.log(res)
          if (res.data.status == 200 && res.data.data != null && res.data.data != "") {
            console.log(res.data.data)
            this.$refs.imgup.upload({type: 9, id: res.data.data});//图片上传
            this.$refs.imgup1.upload({type: 8, id: res.data.data});//图片上传
            this.$refs.imgup2.upload({type: 8, id: res.data.data});//图片上传
            this.$refs.imgup3.upload({type: 8, id: res.data.data});//图片上传
            this.$refs.imgup4.upload({type: 8, id: res.data.data});//图片上传
            this.$alert("录入成功!", "添加提示");
            // this.$router.push({name: 'serviceGuide'});//其中certificateDealt是你定义的一个路由模块
          } else {
            this.$alert("录入失败!", "添加提示");
          }
        }).catch(_ => {
          this.$alert("录入失败!", "添加提示");
        })
      },
      cancel() {
        this.data = {
          organizationName: null,//医院名称
          organizationCapital: null,//注册资本
          registerNumber: null,//注册号
          creditCode: null,//统一社会信用代码
          establishDate: null,//成立时间
          provinceId: null,//省
          ownedCityId: null,//市
          districtId: null,//区
          phone: null,//联系电话
          personLiable: null,//联系人
          orgType: 2,//机构类型
          scopeOfOperation: null,//经营范围
          address: null,
          petHospitals: [
            {
              petHCardType: 0,
              petHCardNum: null,
              startTime: null,
              endTime: null
            },
            {
              petHCardType: 1,
              petHCardNum: null,
              startTime: null,
              endTime: null
            },
            {
              petHCardType: 2,
              petHCardNum: null,
              startTime: null,
              endTime: null
            },
            {
              petHCardType: 3,
              petHCardNum: null,
              startTime: null,
              endTime: null
            }
          ]
        }
        this.$router.push('serviceGuide');//其中certificateDealt是你定义的一个路由模块
      }
    }
  }
</script>

<style scoped>
  .wrapper-city, .distpicker-address-wrapper {
    margin-top: 10px;
  }

  .el-date-editor {
    width: 100%;
  }
</style>
