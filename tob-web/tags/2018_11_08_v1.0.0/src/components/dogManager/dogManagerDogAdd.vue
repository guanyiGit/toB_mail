<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li>
          <router-link :to="{name:'dogManager_list'}">犬证管理></router-link>
          <!--<a href="###">犬证管理></a>-->
        </li>
        <li>犬证办理</li>
      </ul>
    </div>
    <form @submit.prevent="submit">
      <div class="mgt10 k3">
        <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60"><font color="red">证件有效期</font></div>
        <div class="pdt10 pdb20 pdr30 borb2">

          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">有效期限<font color="red">&nbsp;*</font></span>
              <div class="mgl100">
                <el-date-picker v-model="cardDateStart" name="startTime" type="date" placeholder="选择日期" class="wd100b"
                                format="yyyy年MM月dd日">
                </el-date-picker>
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30"><img src="/static/images/bg14.png" alt=""></span>
              <div class="mgl90">
                <el-date-picker v-model="cardDateEnd" name="endTime" type="date" placeholder="选择日期" class="wd100b"
                                format="yyyy年MM月dd日">
                </el-date-picker>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="mgt10 k3">
        <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">证件信息</div>
        <div class="pdt10 pdb20 pdr30 borb2">
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">免疫证号<font color="red">&nbsp;*</font></span>
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
              <span class="db w90 fl h40 lh40 cor0 pdl30">签发机构</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled>
              </div>
            </div>
          </div>


          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">签发日期</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dogAllInfo.immuneCard && dogAllInfo.immuneCard.lssueTime)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dogAllInfo.immuneCard.lssueTime|formatDate('yyyy年MM月dd日')">
              </div>
            </div>
          </div>


          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">有效期限</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dogAllInfo.immuneCard && dogAllInfo.immuneCard.startTime)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dogAllInfo.immuneCard.startTime | formatDate('yyyy年MM月dd日')">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30"><img src="/static/images/bg14.png" alt=""></span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled>
              </div>
            </div>
          </div>

        </div>
        <div class="pdt10 pdb20 pdr30 borb2">
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">项圈编号<font color="red">&nbsp;*</font></span>
              <div class="mgl100">
                <el-tooltip class="item" effect="dark" content="输入设备编号搜索" placement="right">
                  <el-autocomplete
                    class="inline-input ww100 h40 lh40 ras4"
                    v-model="deviceNumKeyWord"
                    :fetch-suggestions="querySearchDeivce"
                    placeholder="请输入内容搜索"
                    :trigger-on-focus="false"
                    @select="deviceSelectHandle"
                  ></el-autocomplete>
                </el-tooltip>

                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="请输入搜索">-->
              </div>

            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">签发机构</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       :value="deviceInfo.tOrganization?deviceInfo.tOrganization.organizationName:''">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">出厂日期</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-if="!deviceInfo.productionTime">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="deviceInfo.productionTime |formatDate('yyyy年MM月dd日')">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">有效期限</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-if="!deviceInfo.startTime">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-else
                       :value="deviceInfo.startTime |formatDate('yyyy年MM月dd日')">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w100 fl h40 lh40 cor0"><img src="/static/images/bg14.png" alt=""></span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       :value="deviceInfo.startTime?'2037年1月1日':''">
              </div>
            </div>
          </div>
        </div>

      </div>


      <div class="mgt10 k3">
        <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">犬只信息</div>
        <div class="pdt10 pdb20 pdr30">
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">犬名</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       :value="dogAllInfo.dogInfo?dogAllInfo.dogInfo.dogName:''">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">品种</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       :value="dogAllInfo.dogInfo?dogAllInfo.dogInfo.breed:''">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">性别</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       :value="dogAllInfo.dogInfo && dogAllInfo.dogInfo.dogGender==0?'雄':dogAllInfo.dogInfo && dogAllInfo.dogInfo.dogGender==1?'雌':''">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">犬龄</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       :value="dogAllInfo.dogInfo?dogAllInfo.dogInfo.dogAge:''">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">出生日期</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       v-if="!(dogAllInfo.dogInfo && dogAllInfo.dogInfo.birthTime)">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                       :value="dogAllInfo.dogInfo.birthTime | formatDate('yyyy年MM月dd日')">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">毛色</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       :value="dogAllInfo.dogInfo?dogAllInfo.dogInfo.color:''">
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">父体</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled>
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">母体</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled>
              </div>
            </div>
          </div>
          <div class="mgt20">
            <span class="db w100 fl h40 lh40 pdl30 cor0">照片</span>
            <div class="mgl100 ovh">
              <ul class="ovh fl ul10">
                <li class="fl"><img src="/static/images/28-1.png" alt=""></li>
                <li class="fl"><img src="/static/images/28-2.png" alt=""></li>
                <li class="fl"><img src="/static/images/28-3.png" alt=""></li>
              </ul>
            </div>
          </div>

          <!--<div class="mgt20">-->
          <!--<span class="db w100 fl h40 lh40 pdl30 cor0">上传照片</span>-->
          <!--<div class=" ovh">-->
          <!--<img-upload class="ovh" ref="imgup"></img-upload>-->
          <!--</div>-->
          <!--</div>-->

        </div>
      </div>
      <div class="mgt15 k3">
        <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">犬主信息</div>
        <div class="pdb20 ovh">
          <div class="fl w730 pdt10">
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">犬主姓名</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                         :value="dogAllInfo.dogOwners ? dogAllInfo.dogOwners.dogOwnerName:''">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">机构性质</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                         :value="dogAllInfo.dogOwners&&dogAllInfo.dogOwnerType==0 ?'个人':dogAllInfo.dogOwners&&dogAllInfo.dogOwnerType==1?'企业':''">
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">证件类型</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :valuie="dogAllInfo.dogOwners&&dogAllInfo.cardType==0 ?'身份证':
                  dogAllInfo.dogOwners&&dogAllInfo.cardType==1?'房产证':dogAllInfo.dogOwners&&dogAllInfo.cardType==2?'营业执照':'其他'">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">证件号码</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                         :value="dogAllInfo.dogOwners ? dogAllInfo.dogOwners.cardNum:''">
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">性别</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                         :value="dogAllInfo.dogOwners&&dogAllInfo.sex==0 ?'男':dogAllInfo.dogOwners&&dogAllInfo.sex==1?'女':''">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">出生日期</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                         v-if="!(dogAllInfo.dogOwners && dogAllInfo.dogOwners.birthDate)">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else
                         :value="dogAllInfo.dogOwners.birthDate | formatDate('yyyy年MM月dd日')">
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">现住区县</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                         :value="dogAllInfo.dogOwners?dogAllInfo.dogOwners.districtName:''">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">邮编</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                         :value="dogAllInfo.dogOwners?dogAllInfo.dogOwners.postalCode:''">
                </div>
                <!--<span class="db w90 fl h40 lh40 cor0 pdl30">所属部门</span>-->
                <!--<div class="mgl90">-->
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled>-->
                <!--</div>-->
              </div>
            </div>
            <!--<div class="ovh">-->
            <!--<div class="mgt10 ww50 fl">-->
            <!--<span class="db w100 fl h40 lh40 cor0 pdl30">所属社区</span>-->
            <!--<div class="mgl100">-->
            <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled>-->
            <!--</div>-->
            <!--</div>-->
            <!--<div class="mgt10 ww50 fr">-->
            <!--<span class="db w90 fl h40 lh40 cor0 pdl30">邮编</span>-->
            <!--<div class="mgl90">-->
            <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :valuie="dogAllInfo.dogOwners?dogAllInfo.dogOwners.postalCode:''">-->
            <!--</div>-->
            <!--</div>-->
            <!--</div>-->
            <div class="ovh">
              <div class="mgt10 ww50 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">联系电话</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                         :value="dogAllInfo.dogOwners?dogAllInfo.dogOwners.dogOwnerPhone:''">
                </div>
              </div>
              <div class="mgt10 ww50 fr">
                <span class="db w90 fl h40 lh40 cor0 pdl30">电子邮箱</span>
                <div class="mgl90">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                         :value="dogAllInfo.dogOwners?dogAllInfo.dogOwners.eMail:''">
                </div>
              </div>
            </div>
            <div class="ovh">
              <div class="mgt10 ww100 fl">
                <span class="db w100 fl h40 lh40 cor0 pdl30">详细地址</span>
                <div class="mgl100">
                  <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                         :value="dogAllInfo.dogOwners?dogAllInfo.dogOwners.address:''">
                </div>
              </div>
            </div>
          </div>
          <div class="mgl730 pdt70">
            <img class="db c-b" src="/static/images/b16.png" alt="">
          </div>
        </div>

        <div class="t-c mgt40 mgb50 ">
          <input type="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort cupo" value="提交">
          <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5"
             @click.prevent="dogAllInfo={}">取消</a>
        </div>

        <div class="t-c mgt40 mgb50">
          <el-dialog
            title="提示"
            :visible.sync="alert.dialogVisible"
            width="30%">
            <span slot="footer" class="dialog-footer">
              <!--<el-button @click="alert.dialogVisible = false">取 消</el-button>-->
              <el-button type="primary" @click="alert.dialogVisible = false">确 定</el-button>
            </span>
          </el-dialog>
        </div>
      </div>

    </form>


  </div>
  <!--内容区END-->
</template>

<script>
  import globalConf from '@/common/config'

  export default {
    name: "dogManagerDogAdd",
    data() {
      return {
        cardKeyWord: '',//免疫证输入值
        deviceNumKeyWord: '',//设备编号输入值
        deviceInfo: {},//选择的设备信息
        dogAllInfo: {},//犬的所有信息
        cardDateStart: '',//证书期限 开始时间
        cardDateEnd: '',//证书期限 结束时间
        alert: {//弹出框
          dialogVisible: false,//弹出框显示标示，
          msg: 'ssss'//弹窗提示内容
        }
      }
    },
    methods: {
      querySearchCrad(queryString, cb) {
        this.cardKeyWord = queryString;
        initSearchCardData(this, cb);
      },
      querySearchDeivce(queryString, cb) {
        this.deviceNumKeyWord = queryString;
        initSearchDeviceData(this, cb)
      },
      deviceSelectHandle(item) {//设备编号选择
        if (!item || item.value === '无匹配') return
        this.deviceInfo = item.address
        console.log(this.deviceInfo)
      },
      cradSelectHandle(item) {//免疫证号选择选择
        this.$axios({
          url: '/biz/dogCard/selectDogCardInfo',
          params: {'cardNum': item.value}
        }).then((res) => {
          console.log(res)
          if (res.data.code == '000000') {
            this.dogAllInfo = res.data.result;
          }
        }).catch((res) => {
          console.log(res)
        })
      },
      submit(e) {//提交表单
        let loadingInstance = this.$loading({
          lock: true,
          text: '正在处理，请稍后...',
          background: 'rgba(0, 0, 0, 0.3)'
        })
        try {
          let dogId = this.dogAllInfo.dogInfo.dogId//犬id
          let dogOwnerId = this.dogAllInfo.dogOwners.dogOwnerId//犬主id

          // let memberCardId //会员证id
          // let dogCardNum //证书编号
          // let useStatus//使用状态
          // let expireTime = this.dogAllInfo.cardDateEnd;//设备过期时间

          let lssueOrgId = globalConf.userInfo.organizationId//发证机构id
          let lssuerId = globalConf.userInfo.userId//签发人，办理人
          let lssueTime = new Date();//犬证签发时间
          let phone = this.dogAllInfo.dogOwners.dogOwnerPhone;//犬主电话
          let name = this.dogAllInfo.dogOwners.dogOwnerName;//姓名
          let gender = this.dogAllInfo.dogOwners.sex;//性别
          let email = this.dogAllInfo.dogOwners.eMail;//邮箱
          // let orgType;//机构类型

          let startTime = this.cardDateStart//犬证有效期开始时间
          let endTime = this.cardDateEnd//犬证有效期结束时间
          let deviceId = this.deviceInfo.deviceId///设备id
          let creationTime = new Date();//设备创建时间/有效起始时间

          if (!startTime || !endTime) {
            this.$alert("您还没有选择证书有效期！");
            return;
          }

          if (!deviceId) {
            this.$alert("请选择一个设备");
            return;
          }

          if (!dogOwnerId) {
            this.$alert("请选择一个免疫证信息");
            return;
          }

          //发请求办证
          this.$axios({
            method: "post",
            headers: {
              'Content-Type': 'application/json;charset=UTF-8'  //这里加上头部信息
            },
            url: '/biz/dogCard/insertDogCard',
            data: JSON.stringify({
              dogId,
              dogOwnerId,
              startTime,
              endTime,
              lssueTime,
              lssueOrgId,
              lssuerId,
              deviceId,
              creationTime,
              phone,
              name,
              gender,
              email
            })
          }).then((res) => {
            console.log(res)
            if (res.data.code == '000000') {
              this.alert.msg = "办理成功！";
              //图片上传
              this.$refs.imgup.upload({type: 2, id: dogAllInfo.dogInfo.dogId})
            }
          }).catch(_ => {
            this.alert.msg = "服务器繁忙！";
          })
        } catch (e) {
          this.alert.msg = "信息填写不完整！";
          // setTimeout(()=>{this.alert.dialogVisible = !this.alert.dialogVisible},1000)
        } finally {
          // this.alert.dialogVisible = !this.alert.dialogVisible;
          this.$nextTick(() => loadingInstance.close());
          this.$alert(this.alert.msg)
        }
      },
      handleClose(done) {
        this.$confirm('确认关闭？').then(_ => {
          done();
        }).catch(_ => {
        });
      }
    },
    mounted() {
      // this.searchCardList = this.loadAll();
    },
    computed: {},
    filters: {},
    watch: {
      deviceNumKeyWord(newVal, oldVal) {
        if (!newVal || newVal.trim().length == 0) this.deviceInfo = {};
      },
      phoneKeyWord(newVal, oldVal) {
        if (!newVal || newVal.trim().length == 0) this.dogAllInfo = {};
      },
      cardDateEnd(newVal) {
        if (newVal && newVal.getTime() < this.cardDateStart) {
          this.$alert("结束日期不能小于开始日期")
          this.cardDateEnd = '';
        }
      },
      cardDateStart(newVal) {
        if (newVal && newVal.getTime() > this.cardDateEnd) {
          this.$alert("始日期不能大于结束日期")
          this.cardDateStart = '';
        }
      },
    }
  }


  function initSearchCardData(_this, cb) {
    _this.$axios({
      url: '/biz/dogCard/selectImmuneCardNums',
      params: {"input": _this.phoneKeyWord}
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

  function initSearchDeviceData(_this, cb) {
    _this.$axios({
      url: '/biz/device/search/' + _this.deviceNumKeyWord,
      params: {"isActive": true}
    }).then((res) => {
      if (res.data.meta.success && res.data.data) {
        //输入框下拉逻辑
        if (cb) {
          let temp = [];
          res.data.data.map(item => {
            temp.push({"value": item.deviceNumber, "address": item})//固定格式返回
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
</script>

<style scoped>
  .wd100b {
    width: 100%;
    display: inline-block;
    vertical-align: middle;
  }

  .cupo {
    cursor: pointer;
  }


</style>
