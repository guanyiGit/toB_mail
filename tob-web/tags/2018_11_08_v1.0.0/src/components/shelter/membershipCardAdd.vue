<template>
  <!--内容区-->
  <div class="w980 fr">
    <form @submit.prevent="submit">
      <div class="mgt10 k3">
        <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
          证件信息
        </div>
        <div class="pdt10 pdb20 pdr30 borb2">
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">办理时间</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled
                       :value="new Date() | formatDate(datePattern)">
              </div>
            </div>
            <!--<div class="mgt10 ww50 fr">-->
              <!--<span class="db w90 fl h40 lh40 cor0 pdl30">签发机构</span>-->
              <!--<div class="mgl90">-->
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="inputOrgid">-->
              <!--</div>-->
            <!--</div>-->
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">有效期限<font color="red">&nbsp;*</font></span>
              <div class="mgl100">
                <el-date-picker v-model="menberCradInfo.upTime" type="date" class="ww100 h40 lh40  borf1 ras4 pikes"  placeholder="选择出生日期"  format="yyyy 年 MM 月 dd 日" > </el-date-picker>
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w100 fl h40 lh40 cor0"><img src="/static/images/bg14.png" alt=""></span>
              <div class="mgl100">
                <el-date-picker v-model="menberCradInfo.expireTime" type="date" class="ww100 h40 lh40  borf1 ras4 pikes"  placeholder="选择出生日期"  format="yyyy 年 MM 月 dd 日" > </el-date-picker>

                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="mgt10 k3">
        <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
          犬主信息
        </div>
        <div class="pdt10 pdb20 pdr30 borb2">
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">犬主电话<font color="red">&nbsp;*</font></span>
              <div class="mgl100">
                <el-tooltip class="item" effect="dark" content="输入电话号码搜索" placement="right">
                  <el-autocomplete
                    class="inline-input ww100 h40 lh40 ras4"
                    v-model="phoneKeyWord"
                    :fetch-suggestions="querySearchPhone"
                    placeholder="请输入内容搜索"
                    :trigger-on-focus="false"
                    @select="phoneSelectHandle"
                  ></el-autocomplete>
                </el-tooltip>
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">-->
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">犬主姓名</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :value="dogOnwrInfo.dogOwnerName" disabled>
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">性质</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :value="dogOnwrInfo.dogOwnerType==1?'个人':'企业'" disabled>
              </div>
            </div>
          </div>

          <!--<div class="ovh">-->
            <!--<div class="mgt10 ww50 fl">-->
              <!--<span class="db w100 fl h40 lh40 cor0 pdl30">证件类型</span>-->
              <!--<div class="mgl100">-->
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4"  disabled>-->
              <!--</div>-->
            <!--</div>-->
            <!--<div class="mgt10 ww50 fr">-->
              <!--<span class="db w90 fl h40 lh40 cor0 pdl30">证件号码</span>-->
              <!--<div class="mgl90">-->
                <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled>-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">性别</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :value="dogOnwrInfo.sex==0?'男':'女'" disabled>
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">出生日期</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :value="dogOnwrInfo.birthDate | formatDate(datePattern)" disabled>
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">邮编</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :value="dogOnwrInfo.postalCode" disabled>
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">电子邮箱</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :value="dogOnwrInfo.eMail" disabled>
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww100 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">详细地址</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :value="dogOnwrInfo.address" disabled>
              </div>
            </div>
          </div>

        </div>
      </div>
      <div class="t-c mgt40 mgb50">
        <!--<a href="###" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort">打印</a>-->
        <input type="submit"  class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" value="保存">
        <a href="###" @click.prevent="dogOnwrInfo={}" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</a>
      </div>
    </form>
  </div>
  <!--内容区END-->
</template>

<script>

  import  configJS from '../../common/config'

  export default {
    name: "membershipCardAdd",
    data() {
      return {
        datePattern: 'yyyy年MM月dd日',
        phoneKeyWord: null,//搜索的免疫证号
        phone: null,//
        menberCradInfo:{},//会员证信息
        dogOnwrInfo:{},//犬主信息
        alert:{//弹出框
          dialogVisible: false,//弹出框显示标示，
          msg:'ssss'//弹窗提示内容
        }
      }
    },
    methods: {
      //输入搜索
      querySearchPhone(queryString, cb) {
        this.phoneKeyWord = queryString;
        initSearchDogOwone(this, cb);
      },
      //免疫证号选择选择
      phoneSelectHandle(item) {
        if (item.value == '无匹配') return;
        this.dogOnwrInfo = item.address;
        console.log(item.address)
      },
      //保存
      submit(){
        let memberPhone = this.dogOnwrInfo.dogOwnerPhone;
        let issueOrgId = configJS.userInfo.organizationId;
        let issueId = configJS.userInfo.userId;
        let upTime = this.menberCradInfo.upTime;
        let expireTime = this.menberCradInfo.expireTime;
        let issueDate = new Date();
        let dogOwnerId = this.dogOnwrInfo.dogOwnerId;
        let creationTime = new Date();

        let saveParams = {
          memberPhone,
          issueOrgId,
          issueId,
          upTime,
          expireTime,
          issueDate,
          dogOwnerId,
          creationTime
        }

        this.$axios({
          url:'/biz/member',
          method:'post',
          params:saveParams
        }).then(res=>{
          if(res.data.meta.success){
            this.$alert('证书办理成功！');
          }else{
            this.$alert('办理失败！');
          }
        })

      }
    },
  }

  //查询犬主信息
  function initSearchDogOwone(_this, cb) {
    _this.$axios({
      url: '/biz/dogAndOwner/selectDogOwnersByPhone',
      params: {"phone": _this.phoneKeyWord}
    }).then((res) => {
      if (res.data.meta.success) {
        //输入框下拉逻辑
        if (cb) {
          let temp = [];
          res.data.data.map(item => {
            temp.push({"value": item.dogOwnerPhone, "address": item})//固定格式返回
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
.pikes{
  width: 100%;
  height: 39px;
}
</style>
