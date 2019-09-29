<template>
<!--宠物医院首页-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <!--<div class="mbx">
              <ul class="ul11 clearfix">
                  <li><a href="###">病历记录></a></li>
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
                  <td class="ww50 h90">
                    <a href="###" class="db fs18 cor0 hov7">
                      <img src="../../../static/images/52-1.png" alt="">
                      <span class="mgl15">看病预约</span>
                      <span class="mgl30">22</span>
                    </a>
                  </td>
                  <td class="ww50 h90">
                    <a href="###" class="db fs18 cor0 hov7">
                      <img src="../../../static/images/52-1.png" alt="">
                      <span class="mgl15">免疫预约</span>
                      <span class="mgl30">22</span>
                    </a>
                  </td>
                </tr>
                <tr>
                  <td class="ww50 h90">
                    <a href="###" class="db fs18 cor0 hov7">
                      <img src="../../../static/images/52-1.png" alt="">
                      <span class="mgl15">办证预约</span>
                      <span class="mgl30">22</span>
                    </a>
                  </td>
                  <td class="ww50 h90">
                    <a href="###" class="db fs18 cor0 hov7">
                      <img src="../../../static/images/52-1.png" alt="">
                      <span class="mgl15">年检预约</span>
                      <span class="mgl30">22</span>
                    </a>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="k3 mgt20">
            <div class="fs16 cor0 h60 lh60 bgf8 pdl30 pdr30">上月育苗使用情况 <a href="###" class="dinb fr hov8"><img src="../../../static/images/b19.png" alt=""></a></div>
            <div class="pdb50">
              <div id="main1" style="max-width: 100%;height:380px;"></div>
            </div>
          </div>
        </div>
        <div class="fr ww46">
          <div id="calendar" class="calendar ww100 bord " style="height: 400px;"></div>
          <div class="mgt15 ovh">
            <a href="###" class="db fs18 cor0 hov7 mgt5 borff6655 ras10 h80 pdl54 pdr50">
              <img class="db fl mgt25 mgr15" src="../../../static/images/52-1.png" alt="">
              <span class="db fl h80 lh80">看病预约</span>
              <span class="db fr h80 lh80">22</span>
            </a>
            <a href="###" class="db fs18 cor0 hov7 mgt5 borff9d6d ras10 h80 pdl54 pdr50">
              <img class="db fl mgt25 mgr15" src="../../../static/images/52-1.png" alt="">
              <span class="db fl h80 lh80">免疫预约</span>
              <span class="db fr h80 lh80">22</span>
            </a>
            <a href="###" class="db fs18 cor0 hov7 mgt5 borffcf70 ras10 h80 pdl54 pdr50">
              <img class="db fl mgt25 mgr15" src="../../../static/images/52-1.png" alt="">
              <span class="db fl h80 lh80">办证预约</span>
              <span class="db fr h80 lh80">22</span>
            </a>
            <a href="###" class="db fs18 cor0 hov7 mgt5 bor33ccff ras10 h80 pdl54 pdr50">
              <img class="db fl mgt25 mgr15" src="../../../static/images/52-1.png" alt="">
              <span class="db fl h80 lh80">年检预约</span>
              <span class="db fr h80 lh80">22</span>
            </a>
          </div>
        </div>
      </div>

      <div class="mgt20 k3">
        <div class="fs16 cor0 h60 lh60 bgf8 pdl30 pdr30 ovh">客户增长趋势 <a href="###" class="dinb fr hov8"><img src="../../../static/images/b19.png" alt=""></a></div>
        <div id="main2" style="max-width: 100%;height:380px;"></div>
      </div>
    </div>
    <!--内容区END-->
  </div>
</template>

<script>
  // 引入 ECharts 主模块
  import $ from 'jquery';
  import echarts from 'echarts';
  //import calender from 'calender';
  // 引入柱状图
  import 'echarts/lib/chart/bar';
  // 引入提示框和标题组件
  import 'echarts/lib/component/tooltip';
  import 'echarts/lib/component/title';
    export default {
        name: "homePageHospital"
    }
//
//   setTimeout(chartOne,200)
//   setTimeout(chartTwo,200)
//     function chartOne() {
//       var myChart1 = echarts.init(document.getElementById('main1'));
//       let option = {
//
//         tooltip : {
//           trigger: 'item',
//           formatter: "{a} <br/>{b} : {c} ({d}%)"
//         },
//         series : [
//           {
//             name: '访问来源',
//             type: 'pie',
//             radius : '55%',
//             center: ['50%', '60%'],
//             data:[
//               {value:335, name:'瑞比克（40.08%）'},
//               {value:310, name:'优免疫（13.08%）'},
//               {value:335, name:'瑞陪儿安（9.13%）'},
//               {value:310, name:'英特威（5.27%）'},
//               {value:335, name:'维克（60.42%）'},
//               {value:335, name:'其他（60.42%）'}
//             ],
//             itemStyle: {
//               emphasis: {
//                 shadowBlur: 10,
//                 shadowOffsetX: 0,
//                 shadowColor: 'rgba(0, 0, 0, 0.5)'
//               }
//             }
//           }
//         ]
//       };
//       myChart1.setOption(option);
//     }
//
// function chartTwo() {
//   var myChart2 = echarts.init(document.getElementById('main2'));
//   let option = {
//     tooltip : {
//       trigger: 'axis'
//     },
//     /*    legend: {
//             data:['意向','预购','成交']
//         },
//        */
//     calculable : true,
//     xAxis : [
//       {
//         type : 'category',
//         boundaryGap : false,
//         data : ['2017-09','2017-10','2017-11','2017-12','2018-01','2018-02','2018-03','2018-04','2018-05','2018-06','2018-07','2018-08']
//       }
//     ],
//     yAxis : [
//       {
//         type : 'value'
//       }
//     ],
//     series : [
//       {
//         name:'数量',
//         type:'line',
//         smooth:true,
//         itemStyle: {normal: {areaStyle: {type: 'default'}}},
//         data:[160, 230, 160,260, 230, 310,260, 230, 210,140, 130, 80]
//       },
//     ]
//   };
//   myChart2.setOption(option);
// }




</script>

<style scoped>

</style>
