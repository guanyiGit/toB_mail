<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="k4 ovh clearfix">
      <a class="db fl ovh">
        <div class="fl pdt40"><img src="/static/images/b18-1b.png" alt=""></div>
        <div class="mgl60 pdt30">
          <p class="fs30 corw fb">{{yesterdayCollectionDog}}</p>
          <p class="fs18 corw mgt6">昨日收容犬只</p>
        </div>
      </a>
      <a class="db fl ovh">
        <div class="fl pdt40"><img src="/static/images/b18-2b.png" alt=""></div>
        <div class="mgl60 pdt30">
          <p class="fs30 corw fb">{{yesterdayAdoptionDog}}</p>
          <p class="fs18 corw mgt6">昨日认养犬只</p>
        </div>
      </a>
      <a class="db fl ovh">
        <div class="fl pdt40"><img src="/static/images/b18-3b.png" alt=""></div>
        <div class="mgl60 pdt30">
          <p class="fs30 corw fb">{{yesterdayIllegalCounts}}</p>
          <p class="fs18 corw mgt6">昨日违法次数</p>
        </div>
      </a>
    </div>

    <div class="mgt20 ovh">

      <div class="fl w650 k3">
        <div class="h60 lh60 bgf8 pdl30 pdr30 ovh">
          <div class="fl ovh mgt15 mgb15 h30 dd1">
            <a  class="db h30 lh30 pdl35 pdr35 fl cor9 fs16 active">收容犬只分布</a>
            <a  class="db h30 lh30 pdl35 pdr35 fl cor9 fs16">全市犬只分布</a>
          </div>
          <a class="db fr"><img src="/static/images/b19.png" alt=""></a>
        </div>
        <div class="rel w650">
          <!--收容犬只分布轮播-->
          <div class="dd2 vs w650">
            <div id="Carousel1">
              <ul class="carouselUl1">
                <li>
                  <div class="ovh">
                    <div id="main1" class="h285 w650"></div>
                    <div class="w650 pdt80 pdl20 pdr20">
                      <table class="table7" width="100%" border="1">
                        <tbody>
                        <tr v-for="(item,index) in staticDogTypes" :key="index">
                          <td class="cor6 h40 ww25 t-c">{{item.name}}</td>
                          <td class="cor6 h40 ww25 t-c">{{item.value}}</td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </li>
                <li>
                  <div class="ovh">
                    <div id="main2"  class="h285 w650"></div>
                    <div class="w650 pdt80 pdl20 pdr20">
                      <table class="table7" width="100%" border="1">
                        <tbody>
                        <tr v-for="(item,index) in staticDogBreed" :key="index">
                          <td class="cor6 h40 ww16 t-c">{{item.name}}</td>
                          <td class="cor6 h40 ww16 t-c">{{item.value}}</td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </li>
              </ul>
              <div class="page1"></div>
            </div>
          </div>
          <!--收容犬只分布轮播-->






          <!--全市犬只分布轮播-->
          <div class="dd2 vd w650">
            <div id="Carousel2">
              <ul class="carouselUl2">
                <li>
                  <div class="ovh h285 pdl20">
                    <div id="main4" ref="echart3" class="h285 ww50 fl"></div>
                    <div class="ww40 fr pdt40 pdr20">
                      <table class="table7" width="100%" border="1">
                        <tbody>
                        <tr v-for="(item,index) in staticDogAreas" :key="index">
                          <td class="cor6 h40 ww50 t-c">{{item.name}}</td>
                          <td class="cor6 h40 ww50 t-c">{{item.value}}</td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                  <div class="ovh h285 pdl20">
                    <div id="main5" ref="echart4" class="h285 ww50 fl"></div>
                    <div class="ww40 fr pdt100 pdr20">
                      <table class="table7" width="100%" border="1">
                        <tbody>
                        <tr v-for="(item,index) in staticDogGenders" :key="index">
                          <td class="cor6 h40 ww50 t-c">{{item.name}}</td>
                          <td class="cor6 h40 ww50 t-c">{{item.value}}</td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </li>


                <li>
                  <div class="ovh">
                    <div id="main7" ref="echart7" class="h285 w650"></div>
                    <div class="w650 pdt40 pdl20 pdr20">
                      <table class="table7" width="100%" border="1">
                        <tbody>
                        <tr v-for="(item,index) in staticDogBreedAll" :key="index">
                          <td class="cor6 h40 ww16 t-c">{{item.name}}</td>
                          <td class="cor6 h40 ww16 t-c">{{item.value}}</td>
                        <!--  <td class="cor6 h40 ww16 t-c">贵宾犬</td>
                          <td class="cor6 h40 ww16 t-c">834</td>
                          <td class="cor6 h40 ww16 t-c">拉布拉多</td>
                          <td class="cor6 h40 ww16 t-c">834</td>-->
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </li>
              </ul>
              <div class="page2"></div>
            </div>
          </div>
          <!--全市犬只分布轮播-->
        </div>
      </div>
      <div class="fr w320">
        <div class="ovh k5 t-c">
          <a  class="db fl h144 pdt30">
            <p class="fs36 corff6655">{{totalCollection}}</p>
            <p class="fs18 cor6 mgt20">收容中</p>
          </a>
          <a  class="db fl h144 pdt30">
            <p class="fs36 corffa273">{{totalAdoption}}</p>
            <p class="fs18 cor6 mgt20">已认养</p>
          </a>
          <a  class="db fl h144 pdt30">
            <p class="fs36 cor41c7db">{{totalHandle}}</p>
            <p class="fs18 cor6 mgt20">已处理</p>
          </a>
        </div>
        <div class="k3 mgt20">
          <div class="h60 lh60 bgf8 pdl30 pdr30 fs16 cor0">
            上月违法次数<a  class="dinb fr"><img src="/static/images/bg17.png" alt=""></a>
          </div>
          <div class="h406">
            <ul>
              <li class="bor-be pdl30 pdr30 ovh pdt20 pdb20">
                <p class="fl h26 lh26 mgl13">违法次数</p>
                <p class="fr h26 lh26">{{staticDogIllegalTendencys}}</p>
              </li>
              <li class="bor-be pdl30 pdr30 ovh pdt20 pdb20" v-for="(item,index) in staticLastMonthDogIllegalCounts" :key="index">
              <!--  <span class="db fl w26 h26 lh26 t-c corw spp1">1</span>-->
                <p class="fl h26 lh26 mgl13">{{item.districtName}}</p>
                <p class="fr h26 lh26">{{item.illegalCounts}}</p>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="k3 mgt20">
      <div class="h60 lh60 bgf8 pdl15 pdr30 pdt15 pdb15 fs16 cor0">
        <a  class="db fl pdl15 pdr15 h30 lh30 ss1 cor9 active">收容犬只数量趋势</a>
        <a  class="db fl pdl15 pdr15 h30 lh30 ss1 cor9">全市犬只数量趋势</a>
        <select class="db fl w120 h30 lh30 bore ras4 mgl15 pdl10 cor3 pc13" v-model="month" @click="monthClick">
          <option value="12">近一年</option>
          <option value="24">近二年</option>
        </select><a  class="dinb fr"><img src="/static/images/bg17.png" alt=""></a>
      </div>
      <div class="rel pdb30">
        <div class="h285 w650 vs ss2">
          <div id="main8" class="h285 w650"></div>
        </div>
        <div class="h285 w650 vd ss2">
          <div id="main9" class="h285 w650"></div>
        </div>
      </div>
    </div>
    <div class="k3 mgt20">
      <div class="h60 lh60 bgf8 pdl30 pdr30 fs16 cor0">
        违法次数趋势<select class="w120 h30 lh30 bore ras4 mgt15 mgl15 pdl10 cor3 pc13" v-model="month2" @click="monthClick2">
        <option value="12">近一年</option>
        <option value="24">近二年</option>
      </select><a class="dinb fr"><img src="/static/images/bg17.png" alt=""></a>
      </div>
      <div class="pdb30">
        <div id="main10" class="h285 w650"></div>
      </div>
    </div>
  </div>
  <!--内容区END-->
</template>

<script>

  // 引入 ECharts 主模块
  //import echarts from 'echarts';
  // 引入柱状图
 // import 'echarts/lib/chart/bar';
  // 引入提示框和标题组件
 //import 'echarts/lib/component/tooltip';
 // import 'echarts/lib/component/title';
//  import Swiper from 'swiper'


    export default {
        name: "collectionPage",
        data() {
        return {
          month:12,
          month2:12,
          yesterdayCollectionDog: 0,//昨日收容犬只
          yesterdayAdoptionDog: 0,//昨日认养犬只
          yesterdayIllegalCounts: 0,//昨日违法数量
          totalCollection:0,//收容中
          totalAdoption:0,//已认养
          totalHandle:0,//已处理

          staticDogTypes: [],//犬类型分布
          staticDogBreed:[],//收容品种分布
          staticDogBreedAll:[],//全市收容品种分布
          staticDogAreas: [],//犬只区域分布
          staticDogGenders: [],//犬只性别分布
          staticDogCollHandle: [],//收容中,已认养,已处理的犬

          staticDogIllegalTendencys: 0,//上月违法次数
          staticLastMonthDogIllegalCounts: [],//上月违法次数区域分布
          //违法趋势折现图
          staticIllegal:{
            staticIllegalCount:[],
            staticIllegalDate:[]
          },
           //收容趋势折线图
          staticCollection:{
            staticCollectionCounts:[],
            staticCollectionDate:[]
          },
          //全市新增犬只折线图
          staticDogAll:{
            staticDogAllCount:[],
            staticDogAllDate:[]
          }
        }
      },
      methods:{
        monthClick:function () {
          findCollDogCountsTendency(this);
          findDogCountsTendency(this);
        },
        monthClick2:function () {
          findDogIllegalCountsTendency(this);
        },
      },
      mounted() {
        var $tab2= $('.dd1>a');
        $tab2.mouseover(function(){
          $(this).addClass('active').siblings().removeClass('active');
          var i= $tab2.index(this);
          $('.dd2').eq(i).addClass('vs').removeClass('vd');
          $('.dd2').eq(i).siblings('.dd2').addClass('vd').removeClass('vs')
        });
        setTimeout(()=>{
           initEcharts1(this);
          initEcharts2(this);
          initEcharts3(this);
           initEcharts4(this);
          initEcharts7(this);
          initEcharts8(this);
          initEcharts9(this);
          initEcharts10(this);
        },500)
      },
      created() {
        this.$axios.all([
          //统计昨日新增/注销犬只/违法次数
          initDatastatisticTitileCount(this),
          //统计收容犬只类型
         initDataStaticDogResultTypes(this),
          //统计收容犬只品种分布
          initDataStaticDogBreed(this),
          //全市收容犬只品种
         initDataStaticDogBreedAll(this),
          //区域分布
          initDataStaticDogAreas(this),
          //性别分布
         initDataStaticDogGenders(this),
          //统计收容中,已认养,已处理的犬
         initDataStaticDogSites(this),
          //上月违法次数
          initDataStaticIllegalCount(this),
          //违法趋势
         findDogIllegalCountsTendency(this),
          //收容犬只数量
         findCollDogCountsTendency(this),
          //全市新增犬只数量统计
          findDogCountsTendency(this),
        ])
          .then(this.$axios.spread(function (acct, perms) {
            // 两个请求现在都执行完成
          }));
      }
    }

  //统计昨日收容犬只/昨日认养犬只/昨日违法犬只
  function initDatastatisticTitileCount(_this) {
    _this.$axios({
      url: "/biz/statistic/findYesterDayCollAndLeave",
    }).then((res) => {
      if (res.data.meta.success) {
        _this.yesterdayCollectionDog = res.data.data.yesterdayCollDog
        _this.yesterdayAdoptionDog = res.data.data.yesterdayLeaveDog
        _this.yesterdayIllegalCounts = res.data.data.yesterdayIllegalCounts
      }
    })
  }

  //收容犬只类型分布
  function initDataStaticDogResultTypes(_this) {
    _this.$axios({url: '/biz/statistic/findCollDogType'})
      .then((res) => {
        if (res.data.meta.success) {
          _this.staticDogTypes = res.data.data;
        }
      })
  }

  //收容犬只品种分布
  function initDataStaticDogBreed(_this) {
    _this.$axios({url: '/biz/statistic/findCollDogBreed'})
      .then((res) => {
        if (res.data.meta.success) {
          _this.staticDogBreed = res.data.data;
        }
      })
  }
  //全市犬只品种分布
  function initDataStaticDogBreedAll(_this) {
    _this.$axios({url: '/biz/statistic/findDogBreedsCounts'})
      .then((res) => {
        if (res.data.meta.success) {
          _this.staticDogBreedAll = res.data.data;
        }
      })
  }

  //区域分布
  function initDataStaticDogAreas(_this) {
    _this.$axios({url: '/biz/statistic/findDogCountsByDistrict'})
      .then((res) => {
        if (res.data.meta.success)
        {
          _this.staticDogAreas = res.data.data;
        }
      })
  }

  //性别分布
  function initDataStaticDogGenders(_this) {
    _this.$axios({url: '/biz/statistic/findDogGenderCounts'})
      .then((res) => {
        if (res.data.meta.success) {
          _this.staticDogGenders = res.data.data;
        }
      })
  }

  //统计收容中,已认养,已处理的犬
  function initDataStaticDogSites(_this) {
    _this.$axios({url: '/biz/statistic/findCollLeaveHandelDog'})
      .then((res) => {
        if (res.data.meta.success) {
            if(res.data.data!=null){
                var tatolColl=0;
                var tatolA=0;
                var tatolC=0;
                var tatolH=0;
                var data=res.data.data;
                for(var i=0;i<data.length;i++){
                    if(data[i].result_type==0){
                      tatolColl=data[i].typeCounts;
                    }else if(data[i].result_type==2){
                      tatolA=data[i].typeCounts;
                    }else if(data[i].result_type==3){
                      tatolC=data[i].typeCounts;
                    }else if(data[i].result_type==4){
                      tatolH=data[i].typeCounts;
                    }
                }
            }
        }
        _this.totalCollection = tatolColl ;
        _this.totalAdoption = tatolA+tatolC;
        _this.totalHandle = tatolH;
      })
  }

  //收容犬只数量趋势
  function findCollDogCountsTendency(_this) {
    _this.$axios({url: '/biz/statistic/findCollDogCountsTendency', params: {"count": _this.month}})//TODO 年数
      .then((res) => {
        if (res.data.meta.success) {
          if(res.data.data.totalCollDogCountsTendency.length>0){
            var data=res.data.data.totalCollDogCountsTendency;
            var date=[];
            var count=[];
            for(var i=0;i<data.length;i++){
              date.push(data[i].collectionTime);
              count.push(data[i].collCounts)
            }
          }
          _this.staticCollection.staticCollectionDate = date;
          _this.staticCollection.staticCollectionCounts = count;
        }
      })
  }
//全市犬只趋势
  function findDogCountsTendency(_this) {
    _this.$axios({url: '/biz/statistic/findDogCountsTendency', params: {"count": _this.month}})//TODO 年数
      .then((res) => {
        if (res.data.meta.success) {
            var data=res.data.data.totalMonthAdd;
            if(data.length>0){
              var date=[];
              var count=[];
              for(var i=0;i<data.length;i++){
                date.push(data[i].creationTime);
                count.push(data[i].dogCounts)
              }
            }
          _this.staticDogAll.staticDogAllCount=count;
          _this.staticDogAll.staticDogAllDate=data;
        }
      })
  }

  //上月违法次数
  function initDataStaticIllegalCount(_this) {
    _this.$axios({url: '/biz/statistic/findDogIllegalCountsTendency', params: {"count": 1}}) //TODO 月数
      .then((res) => {
        if (res.data.meta.success) {
            if(res.data.data.totalIllegalCountsTendency.size>0){
              _this.staticDogIllegalTendencys = res.data.data.totalIllegalCountsTendency[0].illegalCounts;
              _this.staticLastMonthDogIllegalCounts=res.data.data.illegalCountsTendency;
            }
        }
      })
  }


  //违法趋势
  function findDogIllegalCountsTendency(_this) {
    _this.$axios({url: '/biz/statistic/findDogIllegalCountsTendency', params: {"count": _this.month2}})//TODO 月数
      .then((res) => {
        if (res.data.meta.success) {
            var data=res.data.data.totalIllegalCountsTendency;
            if(data.length>0){
                var count=[];
                var date=[];
                for(var i=0;i<data.length;i++){
                  count.push(data[i].illegalCounts);
                  date.push(data[i].illegalDate)
                }
              _this.staticIllegal.staticIllegalCount=count;
              _this.staticIllegal.staticIllegalDate=date;
            }
        }
      })
  }


  //类型分布
  function initEcharts1(_this){
    var myChart1 = echarts.init(document.getElementById('main1'));
    let option = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
      },
      series: [
        {
          name:'访问来源',
          type:'pie',
          radius: ['30%', '60%'],
          label: {
            normal: {
              backgroundColor: '#eee',
              borderColor: '#aaa',
              borderWidth: 1,
              borderRadius: 4,
              rich: {
                a: {
                  color: '#999',
                  lineHeight: 22,
                  align: 'center'
                },
                hr: {
                  borderColor: '#aaa',
                  width: '100%',
                  borderWidth: 0.5,
                  height: 0
                },
                b: {
                  fontSize: 16,
                  lineHeight: 33
                },
                per: {
                  color: '#eee',
                  backgroundColor: '#334455',
                  padding: [2, 4],
                  borderRadius: 2
                }
              }
            }
          },
        data:_this.staticDogTypes
          /* data:[
             {value:635, name:'边境牧羊犬z'},
             {value:310, name:'德国牧羊犬'},
             {value:234, name:'长须牧羊犬'},
             {value:335, name:'其他'},
             {value:554, name:'威尔士柯基犬'}
           ]*/
        }
      ]
    };

    myChart1.setOption(option);
  }

  //品种分布
  function initEcharts2(_this){
    var myChart2 = echarts.init(document.getElementById('main2'));
    let option = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
      },

      series : [
        {
          name: '访问来源',
          type: 'pie',
          radius : '55%',
          center: ['50%', '60%'],
          data:_this.staticDogBreed,
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

    myChart2.setOption(option);
  }

  //全市品种分布
  function initEcharts7(_this){
    let set = _this.$refs.echart7
    let myChart7 = echarts.init(set);
    let option = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
      },
      series : [
        {
          name: '访问来源',
          type: 'pie',
          radius : '55%',
          center: ['50%', '60%'],
          data:_this.staticDogBreedAll,/*[
            {value:335, name:'中华田园犬（13.08%）'},
            {value:310, name:'哈士奇（13.08%）'},
            {value:335, name:'贵宾犬（9.13%）'},
            {value:310, name:'拉布拉多（5.27%）'},
            {value:335, name:'雪纳瑞（60.42%）'},
            {value:335, name:'博美（13.08%）'},
            {value:310, name:'比熊犬（13.08%）'},
            {value:335, name:'金毛（9.13%）'},
            {value:310, name:'边境牧羊犬（5.27%）'},
            {value:335, name:'萨摩耶（60.42%）'},
            {value:335, name:'其他（60.42%）'}
          ],*/
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

    myChart7.setOption(option);
  }

  //区域分布
  function initEcharts3(_this){
    let set = _this.$refs.echart3
    let myChart3 = echarts.init(set);

    let option = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
      },

      series: [
        {
          name:'访问来源',
          type:'pie',
          radius: ['40%', '60%'],
          label: {
            normal: {
              backgroundColor: '#eee',
              borderColor: '#aaa',
              borderWidth: 1,
              borderRadius: 4,
              rich: {
                a: {
                  color: '#999',
                  lineHeight: 22,
                  align: 'center'
                },
                hr: {
                  borderColor: '#aaa',
                  width: '100%',
                  borderWidth: 0.5,
                  height: 0
                },
                b: {
                  fontSize: 16,
                  lineHeight: 33
                },
                per: {
                  color: '#eee',
                  backgroundColor: '#334455',
                  padding: [2, 4],
                  borderRadius: 2
                }
              }
            }
          },
          data: _this.staticDogAreas
        }
      ]
    };

    myChart3.setOption(option);
  }

  //性别分布
  function initEcharts4(_this){
    let set = _this.$refs.echart4
    let myChart4 = echarts.init(set);
    let option = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
      },
      series: [
        {
          name:'访问来源',
          type:'pie',
          radius: ['40%', '60%'],
          label: {
            normal: {
              backgroundColor: '#eee',
              borderColor: '#aaa',
              borderWidth: 1,
              borderRadius: 4,
              rich: {
                a: {
                  color: '#999',
                  lineHeight: 22,
                  align: 'center'
                },
                hr: {
                  borderColor: '#aaa',
                  width: '100%',
                  borderWidth: 0.5,
                  height: 0
                },
                b: {
                  fontSize: 16,
                  lineHeight: 33
                },
                per: {
                  color: '#eee',
                  backgroundColor: '#334455',
                  padding: [2, 4],
                  borderRadius: 2
                }
              }
            }
          },
          data:_this.staticDogGenders
            /*[
            {value:635, name:'雌（63%）'},
            {value:310, name:'雄（37%）'},
          ]*/
        }
      ]
    };
    myChart4.setOption(option);
  }
//收容犬只数量趋势查询
  function initEcharts8(_this) {
    var myChart8 = echarts.init(document.getElementById('main8'));
    var option = {
      tooltip: {
        trigger: 'axis'
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: _this.staticCollection.staticCollectionDate
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          type:'line',
          stack: '总量',
          data:_this.staticCollection.staticCollectionCounts
        },
      ]
    };
    myChart8.setOption(option);

  }
//全市犬只数量趋势
  function initEcharts9(_this) {
  var myChart9 = echarts.init(document.getElementById('main9'));
  var option = {
     tooltip: {
     trigger: 'axis'
     },
     grid: {
     left: '3%',
     right: '4%',
     bottom: '3%',
     containLabel: true
     },
     xAxis: {
     type: 'category',
     boundaryGap: false,
     data:_this.staticDogAll.staticDogAllDate
     },
     yAxis: {
     type: 'value'
     },
     series: [
       {
         type:'line',
         stack: '总量',
         data:_this.staticDogAll.staticDogAllCount
       },
     ]
  };
     myChart9.setOption(option);

  }

//违法次数趋势
  function initEcharts10(_this) {
    var myChart10 = echarts.init(document.getElementById('main10'));
    var option = {
      tooltip: {
        trigger: 'axis'
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data:  _this.staticIllegal.staticIllegalDate
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          type:'line',
          stack: '总量',
          data: _this.staticIllegal.staticIllegalCount
        },
      ]
    };
    myChart10.setOption(option);

  }
</script>

<style scoped>

</style>
