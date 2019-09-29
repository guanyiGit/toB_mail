<template>
  <!--内容区-->
  <div class="w980 fr">
    <!--<div class="mbx">
            <ul class="ul11 clearfix">
                <li><a href="javascript:void(0)">病历记录></a></li>
                <li>病历记录</li>
            </ul>
        </div>-->
    <div class="ovh">
      <div class="fl ww53">
        <div class="k3">
          <div class="fs16 cor0 h60 lh60 bgf8 pdl30 pdr30">今日预约</div>
          <div>
            <table class="table12 t-c" width="100%">
              <tbody>
              <tr>
                <td class="ww50 h90 t-c">
                  <a href="javascript:void(0)" class="db fs18 cor0 hov7">
                    <img src="/static/images/52-1.png" alt="">
                    <span class="mgl15">看病预约</span>
                    <span class="mgl30">{{toDaySubs["0"]}}</span>
                  </a>
                </td>
                <td class="ww50 h90 t-c">
                  <a href="javascript:void(0)" class="db fs18 cor0 hov7">
                    <img src="/static/images/52-1.png" alt="">
                    <span class="mgl15">免疫预约</span>
                    <span class="mgl30">{{toDaySubs['3']}}</span>
                  </a>
                </td>
              </tr>
              <tr>
                <td class="ww50 h90 t-c">
                  <a href="javascript:void(0)" class="db fs18 cor0 hov7">
                    <img src="/static/images/52-1.png" alt="">
                    <span class="mgl15">办证预约</span>
                    <span class="mgl30">{{toDaySubs['1']}}</span>
                  </a>
                </td>
                <td class="ww50 h90 t-c">
                  <a href="javascript:void(0)" class="db fs18 cor0 hov7">
                    <img src="/static/images/52-1.png" alt="">
                    <span class="mgl15">年检预约</span>
                    <span class="mgl30">{{toDaySubs['2']}}</span>
                  </a>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="k3 mgt20">
          <div class="fs16 cor0 h60 lh60 bgf8 pdl30 pdr30">上月疫苗使用情况 <a href="javascript:void(0)"
                                                                       class="dinb fr hov8"><img
            src="/static/images/b19.png" alt=""></a></div>
          <div class="pdb50">
            <div ref="main1" style="max-width: 100%;height:380px;"></div>
          </div>
        </div>
      </div>
      <div class="fr ww46">
        <div id="calendar" class="calendar ww100 bord " style="height: 400px;"></div>
        <div class="mgt15 ovh">
          <a href="javascript:void(0)" class="db fs18 cor0 hov7 mgt5 borff6655 ras10 h80 pdl54 pdr50">
            <img class="db fl mgt25 mgr15" src="/static/images/52-1.png" alt="">
            <span class="db fl h80 lh80">昨日看病预约</span>
            <span class="db fr h80 lh80">{{yesterday.yesterdayTherapy}}</span>
          </a>
          <a href="javascript:void(0)" class="db fs18 cor0 hov7 mgt5 borff9d6d ras10 h80 pdl54 pdr50">
            <img class="db fl mgt25 mgr15" src="/static/images/52-1.png" alt="">
            <span class="db fl h80 lh80">昨日免疫预约</span>
            <span class="db fr h80 lh80">{{yesterday.yesterdayImmune}}</span>
          </a>
          <a href="javascript:void(0)" class="db fs18 cor0 hov7 mgt5 borffcf70 ras10 h80 pdl54 pdr50">
            <img class="db fl mgt25 mgr15" src="/static/images/52-1.png" alt="">
            <span class="db fl h80 lh80">昨日办证预约</span>
            <span class="db fr h80 lh80">{{yesterday.yesterdayCard}}</span>
          </a>
          <a href="javascript:void(0)" class="db fs18 cor0 hov7 mgt5 bor33ccff ras10 h80 pdl54 pdr50">
            <img class="db fl mgt25 mgr15" src="/static/images/52-1.png" alt="">
            <span class="db fl h80 lh80">昨日年检预约</span>
            <span class="db fr h80 lh80">{{yesterday.yesterdayInspection}}</span>
          </a>
        </div>
      </div>
    </div>

    <div class="mgt20 k3">
      <div class="fs16 cor0 h60 lh60 bgf8 pdl30 pdr30 ovh">客户增长趋势 <a href="javascript:void(0)" class="dinb fr hov8"><img
        src="/static/images/b19.png" alt=""></a></div>
      <div ref="main2" style="max-width: 100%;height:380px;"></div>
    </div>
  </div>
  <!--内容区END-->
</template>

<script>

  import echarts from 'echarts'
  import configJS from '../../common/config'

  export default {
    name: "petHospitalIndex",
    data() {
      return {
        toDaySubs: {//今日看病,办证,免疫,年检
          '0': 0,//看病
          '1': 0,//办证
          '2': 0,//办证
          '3': 0,//年检
        },
        yesterday: {},//昨日看病,办证,免疫,年检
        LastMonthVaccine: {},//上月疫苗使用情况
        CustomerCounts: {},//客户增长趋势
      }
    },
    mounted() {
      initEcharts1(this)
      initEcharts2(this)
      $('#calendar').calendar({
        ifSwitch: true, // 是否切换月份
        hoverDate: true, // hover是否显示当天信息
        backToday: true // 是否返回当天
      });
    },
    created() {
      findAppointmentMsg(this);
      findHospitalYesterdayCount(this);
      findLastMonthVaccine(this);
      findCustomerCounts(this);
    }
  }

  //1.今日预约
  function findAppointmentMsg(_this) {
    _this.$axios({
      url: '/biz/statistic/findAppointmentMsg',
      params: {
        orgId: configJS.userInfo.organizationId
      }
    })
      .then(res => {
        if (res.data.meta.success && res.data.meta.data) {
          _this.toDaySubs = res.data.meta.data;
        }
      }).catch(_ => {
    })
  }

  //2.昨日看病,办证,免疫,年检
  function findHospitalYesterdayCount(_this) {
    _this.$axios({
      url: '/biz/statistic/findHospitalYesterdayCount',
      params: {
        orgId: configJS.userInfo.organizationId
      }
    })
      .then(res => {
        if (res.data.meta.success) {
          _this.yesterday = res.data.data;
        }
      }).catch(_ => {
    })
  }

  //3.上月疫苗使用情况
  function findLastMonthVaccine(_this) {
    _this.$axios({
      url: '/biz/statistic/findLastMonthVaccine',
      params: {
        orgId: configJS.userInfo.organizationId
      }
    })
      .then(res => {
        if (res.data.meta.success) {
          _this.LastMonthVaccine = res.data.data;
        }
      }).catch(_ => {
    })
  }

  //4.客户增长趋势
  function findCustomerCounts(_this) {
    _this.$axios({
      url: '/biz/statistic/findCustomerCounts',
      params: {
        orgId: configJS.userInfo.organizationId
      }
    })
      .then(res => {
        if (res.data.meta.success && res.data.data) {
          var timeArray = [];
          var countArray = [];
          for (let temp of res.data.data) {
            timeArray.push(temp.creation_time);
            countArray.push(temp.customerCount);
          }
          console.log(res)
        }
      }).catch(_ => {
    })
  }


  //育苗使用情况
  function initEcharts1(_this) {
    let myChart1 = echarts.init(_this.$refs.main1);
    let option = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      series: [
        {
          name: '访问来源',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: _this.LastMonthVaccine,
          // data: [
          //   {value: 335, name: '瑞比克（40.08%）'},
          //   {value: 310, name: '优免疫（13.08%）'},
          //   {value: 335, name: '瑞陪儿安（9.13%）'},
          //   {value: 310, name: '英特威（5.27%）'},
          //   {value: 335, name: '维克（60.42%）'},
          //   {value: 335, name: '其他（60.42%）'}
          // ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    myChart1.setOption(option);
  }

  //客户增长趋势
  function initEcharts2(_this) {
    let myChart2 = echarts.init(_this.$refs.main2);
    let option = {
      tooltip: {
        trigger: 'axis'
      },
      /*    legend: {
              data:['意向','预购','成交']
          },
         */
      calculable: true,
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          data: _this.CustomerCounts.timeArray,
          // data: ['2017-09', '2017-10', '2017-11', '2017-12', '2018-01', '2018-02', '2018-03', '2018-04', '2018-05', '2018-06', '2018-07', '2018-08']
        }
      ],
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [
        {
          name: '数量',
          type: 'line',
          smooth: true,
          itemStyle: {normal: {areaStyle: {type: 'default'}}},
          data: _this.CustomerCounts.countArray,
          // data: [160, 230, 160, 260, 230, 310, 260, 230, 210, 140, 130, 80]
        },
      ]
    };
    myChart2.setOption(option);
  }
</script>

<style scoped>

</style>
