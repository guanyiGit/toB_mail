<template>
  <!--内容区-->
  <div class="w980 fr">
    <form action="#">
      <div class="mgt10 k3">
        <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
          设备信息
        </div>
        <div class="pdt10 pdb20 pdr30 borb2">

          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">设备编号</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="devieInfo.device?devieInfo.device.deviceNumber:''">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w90 fl h40 lh40 cor0 pdl30">办理机构</span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="devieInfo.device?devieInfo.device.orgName:'无'">
              </div>
            </div>
          </div>

          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">设备状态</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="devieInfo.device&&devieInfo.device.deviceStatus==0?'禁用':devieInfo.device&&devieInfo.device.deviceStatus==1?'启用':'已注销'">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w100 fl h40 lh40 cor0 pdl30">设备类型</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="devieInfo.device&&devieInfo.device.deviceType==0?'RF':'项圈'">
              </div>
            </div>
          </div>

          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">设备厂商</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w100 fl h40 lh40 cor0 pdl30">型号</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
              </div>
            </div>
          </div>

          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">录入日期</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="devieInfo.device.creationTime | formatDate(datePattern)" v-if="devieInfo.device && devieInfo.device.creationTime">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled value="无" v-else>
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w100 fl h40 lh40 cor0 pdl30">出厂日期</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="devieInfo.device.productionTime | formatDate(datePattern)" v-if="devieInfo.device && devieInfo.device.productionTime">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled value="无" v-else>
              </div>
            </div>
          </div>

          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">批次</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="devieInfo.device?devieInfo.device.deviceBatch:'无'">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w100 fl h40 lh40 cor0 pdl30">工作模式</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="devieInfo.device&&devieInfo.device.deviceWorkPattern==0?'正常模式':'省电模式'">
              </div>
            </div>
          </div>

          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">上报间隔</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="devieInfo.device.uploadIntervalTime |formatDate(datePattern)" v-if="devieInfo.device && devieInfo.device.uploadIntervalTime">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled value="无">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w100 fl h40 lh40 cor0 pdl30">imei号</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="devieInfo.device?devieInfo.device.imei:'无'">
              </div>
            </div>
          </div>
          <!--
          <div class="ovh">
            <div class="mgt10 ww50 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">有效期限</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
              </div>
            </div>
            <div class="mgt10 ww50 fr">
              <span class="db w100 fl h40 lh40 cor0"><img src="images/bg14.png" alt=""></span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
              </div>
            </div>
          </div>
          -->
        </div>
      </div>
      <div class="t-c mgt40 mgb50">
      <a @click.prevent="$router.go(-1)" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort goBack">返回</a>
      <!--<input type="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" value="保存">-->
      <!--<a href="###" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</a>-->
      </div>
    </form>
  </div>
  <!--内容区END-->
</template>

<script>
  export default {
    name: "operatorDeviceInfo",
    data() {
      return {
        datePattern:'yyyy年MM与dd日',
        devieInfo: {},//设备信息
      }
    },
    created() {
      initData(this)
    }
  }

  function initData(_this) {
    let deviceNum = _this.$route.params.devNo
    _this.$axios({
      url: '/biz/dogCard/selectDogCardInfoByDevice',
      params: {deviceNum}
    }).then(res => {
      if (res.data.code == '000000') {
        _this.devieInfo = res.data.result;
      }
      console.log(res)
    })
  }
</script>

<style scoped>
.goBack{
  cursor: pointer;
}
</style>
