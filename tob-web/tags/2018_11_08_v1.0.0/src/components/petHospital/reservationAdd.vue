<template>
  <!--内容区-->
  <div class="w980 fr pdb40">
    <div class="mbx">
      <ul class="ul11 clearfix" style="margin-bottom: 20px">
        <li>
          <router-link :to="{name:'hospital_reservationList'}">预约列表 ></router-link>
        </li>
        <li>新增预约</li>
      </ul>
    </div>
    <div class="k3">
      <form action="#">
        <div class="h60 lh60 bgf8 pdl30 pdr30 cor0 fs16 fb">新增预约</div>
        <div class="pdt10 pdb20 pdr30">
          <div class="ovh">
            <div class="mgt10 ww50 fl ovh">
              <span class="db w100 fl h40 lh40 cor0 pdl30">预约时间 <font color="red"> * </font></span>
              <div class="mgl100">
                <el-date-picker v-model="appointment.scheduleTime" type="datetime"
                                class="ww100 h40 lh40 borf1 ras4 pc6 picDate"
                                placeholder="选择预约时间"></el-date-picker>
              </div>
            </div>
            <div class="mgt10 ww50 fr ovh">
              <span class="db w90 fl h40 lh40 cor0 pdl20">预约业务<font color="red"> * </font></span>
              <div class="mgl90">
                <el-select class="ww100 h40 lh40  borf1 ras4" v-model="appointment.bizType" placeholder="请选择">
                  <el-option
                    v-for="(item,index) in bizTypes"
                    :key="index"
                    :label="item.dictionaryDescribe"
                    :value="item.dictionaryValue">
                  </el-option>
                </el-select>
              </div>
            </div>
          </div>
          <div class="ovh">
            <div class="mgt10 ww50 fl ovh">
              <span class="db w100 fl h40 lh40 cor0 pdl30">预约人<font color="red"> * </font></span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" v-model="appointment.appointmentName"
                       placeholder="预约姓名">
              </div>
            </div>
            <div class="mgt10 ww50 fr ovh">
              <span class="db w90 fl h40 lh40 cor0 pdl20">联系电话<font color="red"> * </font></span>
              <div class="mgl90">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" placeholder="预约人电话"
                       v-model="appointment.appointmentPhone">
              </div>
            </div>
          </div>

          <div class="ovh">
            <div class="mgt10 ww50 fl ovh">
              <span class="db w100 fl h40 lh40 cor0 pdl30">预约网点</span>
              <div class="mgl100">
                <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :value="serviceStation" disabled>
              </div>
            </div>
            <div class="mgt10 ww50 fr ovh">
              <span class="db w90 fl h40 lh40 cor0 pdl20">预约渠道<font color="red"> * </font></span>
              <div class="mgl90">
                <el-select class="ww100 h40 lh40  borf1 ras4" v-model="appointment.appointmentChannel"
                           placeholder="请选择">
                  <el-option
                    v-for="(item,index) in appointmentChannels"
                    :key="index"
                    :label="item.dictionaryDescribe"
                    :value="item.dictionaryValue">
                  </el-option>
                </el-select>
              </div>
            </div>
          </div>

          <div class="ovh pdt10">
            <div class="mgt10 ww100 fl">
              <span class="db w100 fl h40 lh40 cor0 pdl30">备注</span>
              <div class="mgl100">
                <textarea class="ww100 h140 borf1 pdl10 pdr10 pdt10 pdb10" v-model="appointment.remarks"></textarea>
              </div>
            </div>
          </div>
          <!--<div class="ovh">-->
          <!--<div class="mgt10 ww50 fl ovh">-->
          <!--<span class="db w100 fl h40 lh40 cor0 pdl30">预约网点</span>-->
          <!--<input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" :value="serviceStation" disabled>-->
          <!--<div class="mgl100">-->
          <!--&lt;!&ndash;<select class="ww100 h40 lh40 pdl20 borf1 ras4 pc4">&ndash;&gt;-->
          <!--&lt;!&ndash;<option value="1">瑞鹏宠物医院</option>&ndash;&gt;-->
          <!--&lt;!&ndash;<option value="1">瑞鹏宠物医院</option>&ndash;&gt;-->
          <!--&lt;!&ndash;</select>&ndash;&gt;-->
          <!--</div>-->
          <!--</div>-->
          <!--</div>-->

        </div>
        <div class="t-c mgt40 mgb50">
          <input type="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" value="提交"
                 @click.prevent="submit" style="cursor: pointer;">
          <a href="###" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5" @click.prevent="appointment={}">取消</a>
        </div>
      </form>
    </div>

  </div>
  <!--内容区END-->
</template>

<script>
  import configJS from '../../common/config'

  export default {
    name: "reservationAdd",
    data() {
      return {
        bizTypes: [],
        appointmentChannels: [],
        appointment: {}
      }
    },
    created() {
      initDataBizTypes(this)
      initDataAppointmentChannels(this)
    },
    computed: {
      serviceStation() {
        return configJS.userInfo.tOrganization.organizationName
      }
    },
    methods: {
      submit() {
        let {scheduleTime, bizType, appointmentName, appointmentPhone, appointmentChannel} = this.appointment;

        if (!scheduleTime || scheduleTime.getTime() < new Date().getTime()) {
          this.$alert("预约时间不正确！");
          return;
        }
        if (!bizType) {
          this.$alert("预约类型请选择！");
          return;
        }
        if (!appointmentName) {
          this.$alert("预约人姓名请填写！");
          return;
        }
        if (!appointmentPhone) {
          this.$alert("预约人电话请填写！");
          return;
        }
        if (!(/0?(13|14|15|17|18|19)[0-9]{9}/.test(appointmentPhone))) {
          this.$alert("手机号格式不正确！")
          return;
        }
        if (!appointmentChannel) {
          this.$alert("预约渠道请选择！");
          return;
        }

        this.appointment.orgId = configJS.userInfo.organizationId
        // this.appointment.userId = configJS.userInfo.userId

        this.$axios({
          url: '/biz/reservation',
          method: 'post',
          params: this.appointment,
          isloading: true
        }).then(res => {
          if (res.data.meta.success) {
            this.$alert("新增成功!")
          } else {
            this.$alert("新增失败!")
          }
        })

      }
    }
  }

  function initDataBizTypes(_this) {
    _this.$axios({
      url: '/biz/Tdictionary/biz_type'
    }).then(res => {
      if (res.data.meta.success) {
        _this.bizTypes = res.data.data;
      }
    })
  }

  function initDataAppointmentChannels(_this) {
    _this.$axios({
      url: '/biz/Tdictionary/appointment_channel'
    }).then(res => {
      if (res.data.meta.success) {
        _this.appointmentChannels = res.data.data;
      }
    })
  }
</script>

<style scoped>
  .picDate {
    width: 100%;
  }
</style>
