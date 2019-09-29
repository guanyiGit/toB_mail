<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="ovh">
      <div class="fl w600 k3">
        <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0">
          我的消息 <a href="$$$" class="dinb fr hov8"><img src="images/b19.png" alt=""></a>
        </div>
        <div class="pdt6 pdb15 pdl30 pdr30">
          <ul class="ul13">

            <li v-for="(item,index) in  msgList" :key="index" v-if="msgList && msgList.length>0">
              <a href="###" class="db cor3 h45 lh45 hov8 pdl24 pc3">
                {{item.infoContent}}<span class="fr cor9">{{item.creationTime | formatDate('yyyy/MM/dd')}}</span>
              </a>
            </li>
            <li v-if="!msgList || msgList.length==0">
              <a href="###" class="db cor3 h45 lh45 hov8 pdl24 pc3">
                暂无消息<span class="fr cor9">--/--/--</span>
              </a>
            </li>

          </ul>
        </div>
      </div>
      <div class="fr w370">
        <div>
          <div class="h80 lh80 pdl75 pdr30 borff6655 ras10 fs18 cor0 pc17">
            今日在线人数<span class="fr">{{todayCount}}</span>
          </div>
          <div class="h80 lh80 pdl75 pdr30 borff9d6d ras10 fs18 cor0 pc18 mgt5">
            今日设备在线数量<span class="fr">{{activeDeviceCount}}</span>
          </div>
          <div class="h80 lh80 pdl75 pdr30 borffcf70 ras10 fs18 cor0 pc19 mgt5">
            今日信息发布条数<span class="fr">{{toDayInfoCount}}</span>
          </div>
        </div>


        <div class="k3 mgt20">
          <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0">设备使用情况</div>
          <div class="h291">
            <div id="main1" ref="chart1" class="ww100 h291"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="k3 mgt20">
      <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0">
        用户在线趋势 <a href="$$$" class="dinb fr hov8"><img src="images/b19.png" alt=""></a>
      </div>
      <div class="h291">
        <div id="main2" class="ww100 h291"></div>
      </div>
    </div>
    <!--分页-->
    <!--<div class="fen c-b ovh mgt25 w426">
            <a href="###" class="db fl">1</a>
            <a href="###" class="db fl active">2</a>
            <a href="###" class="db fl">3</a>
            <a href="###" class="db fl">4</a>
            <a href="###" class="db fl">5</a>
            <a href="###" class="db fl"></a>
            <a href="###" class="db fl">99</a>
            <a href="###" class="db fl active2"></a>
            <a href="###" class="db fl"></a>
        </div>-->
    <!--分页-->
  </div>
  <!--内容区END-->
</template>

<script>
  import echarts from 'echarts'

  export default {
    name: "operatorIndex",
    data() {
      return {
        todayCount: 0,//今日在线人数
        activeDeviceCount: 0,//今日在线人数设备在线数量
        toDayInfoCount: 0,//今日信息发布条数
        deviceActiveScale: [],//设备使用情况
        userActiveTendency: {},//用户在线趋势
        msgList: [],//我的消息列表
      }
    },
    mounted() {

    },
    created() {
      findTodayOnlineCounts(this);
      dayDeviceActiveSum(this);
      findTodayInformationCount(this);
      findDeviceList(this);
      findStatisticLogUserTendency(this);
      getInformations(this);
    }

  }


  //1.今日在线人数
  function findTodayOnlineCounts(_this) {
    _this.$axios({url: "/biz/statistic/findTodayOnlineCounts",})
      .then(res => {
        if (res.data.meta.success) {
          _this.todayCount = res.data.data;
        }
      })
  }

  //今日设备在线数量
  function dayDeviceActiveSum(_this) {
    _this.$axios({
      url: "/biz/statistic/dayDeviceActiveSum",
      params: {"date": new Date()}
    }).then(res => {
      if (res.data.meta.success) {
        _this.activeDeviceCount = res.data.data;
      }
    })


  }

  //2.今日信息发布条数
  function findTodayInformationCount(_this) {
    _this.$axios({url: "/biz/statistic/findTodayInformationCount",})
      .then(res => {
        if (res.data.meta.success)
          _this.toDayInfoCount = res.data.data;
      })
  }

  //3.设备使用情况
  function findDeviceList(_this) {
    _this.$axios({url: "/biz/statistic/findDeviceList",})
      .then(res => {
        if(res.data.meta.success){
          _this.deviceActiveScale = res.data.data;
        }
      }).finally(_=>{initEchars1(_this);})
  }

  //4.用户在线趋势
  function findStatisticLogUserTendency(_this) {
    _this.$axios({url: "/biz/statistic/findStatisticLogUserTendency",})
      .then(res => {
        if(res.data.meta.success){
          _this.userActiveTendency = res.data.data;
        }
      }).finally(_=>{initEchars2(_this);})
  }

  //5.查看信息列表
  function getInformations(_this) {
    _this.$axios({
      url: "/biz/information/getInformations",
      params: {TypeNumber: null, status: 0, pageNum: 1, pageSize: 12, query: null}
    }).then(res => {
      if(res.data.status == 200){
        _this.msgList = res.data.data.list;
      }
      console.log(res)
    })

  }

  function initEchars1(_this) {
    let myChart1 = echarts.init(document.getElementById('main1'));
    let option = {
      title: {
        text: '设备使用情况',
        subtext: '大致比例图',
        x: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      series: [
        {
          name: '占比',
          type: 'pie',
          radius: '60%',
          center: ['50%', '60%'],
          data:_this.deviceActiveScale
            //[ {value: 335, name: '直接访问'},
            // {value: 310, name: '邮件营销'},]
          ,
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

  function initEchars2(_this) {
    let myChart2 = echarts.init(document.getElementById('main2'));
    let option = {
      tooltip: {
        trigger: 'axis'
      },
      calculable: true,
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          data:_this.userActiveTendency.logCounts,
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
          data:_this.userActiveTendency.logCounts,
          // data: [160, 230, 160, 260, 230, 310, 260, 230, 210, 140, 90, 20]
        },
      ]
    };
    myChart2.setOption(option);
  }
</script>

<style scoped>

</style>
