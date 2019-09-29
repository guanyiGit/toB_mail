<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="ovh">
      <div class="fl cor6 h40 lh40">
        <a style="cursor:pointer;" class="db fl hov8  corffa472 mgr35 pc9 pdl36" @click.prevent="enableSetFence"
           :class="mapClickEvn ? 'btnactive':'btnDisable'">围栏设置</a>
        <a style="cursor:pointer;" class="db fl hov8  corffa472 mgr35 pc9 pdl36" @click.prevent="disableSetFence"
           :class="mapClickEvn ? 'btnDisable':'btnactive'">完成</a>
        <!--<a href="###" class="db fl hov8 corffa472 pc8 pdl36" @click.prevent="falsh">刷新</a>-->
      </div>
    </div>

    <!--地图-->
    <div class="mgt10 k3 hb100" id="container-map" :class="mapClickEvn?'borRed':''"></div>

    <!--<div class="k3 hb100" id="container-map">-->
    <!--&lt;!&ndash;<img src="/static/images/bg3.jpg" alt="">&ndash;&gt;-->
    <!--</div>-->

    <!--对话框-->
    <el-dialog title="设置围栏名称" :visible.sync="dialogTableVisible">
      <el-input :label="'围栏'+(index+1)+'名称'" v-model="item.fenceName" autocomplete="off"
                :placeholder="'请输入围栏'+(index+1)+'名称'" v-for="(item,index) in fences" :key="index"></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取 消</el-button>
        <el-button type="primary" @click="addFences">确 定</el-button>
      </div>
    </el-dialog>

  </div>
  <!--内容区END-->
</template>


<script>

  import AMap from 'AMap';
  import configJS from '../../common/config';

  export default {
    name: "dogManagerFence",
    data() {
      return {
        curPostion: [116.403322, 29.900255],//中心点坐标
        mapMousemoveEvn: null,//地图上鼠标移动事件
        fences: [],//新增的围栏信息
        dialogTableVisible: false,
        mapPolygonList: [],//地图所有矩形围栏信息
        mapInstance: null,//地图对象
        mapClickEvn: null,//地图上的点击事件
        addPints: [],//新增的禁养区域坐标
        polygonEditor: '',  //多边形编辑事件
        beginMarks: [], //标记点集合
        polygon:"",//存储新添加的多边形
        selectPolygon:""//存储选中多边形对象
      }
    },
    created() {
    },
    mounted() {
      initMap(this)
      getPosition(this);
      this.mapPolygon();

    },
    methods: {
      //开始设置围栏
      enableSetFence() {
        //地图设置点击事件
        //this.mapClickEvn = mapAddClick(this)
        this.mapAddClick();
        //鼠标移动事件
        // this.mapClickMousemoveEvn = AMap.event.addListener(this.map,"mousemove",()=>{})
      },

      //完成按钮
      disableSetFence() {
        //结束多边形编辑事件
        var selectPolygon = this.selectPolygon;
        if(selectPolygon){
          var extData = selectPolygon.getExtData();
          if(!extData.fenceId){
            //添加围栏名称
            this.addFencesName();
          }
        }
      },

      //添加围栏
      addFences() {
        var that = this;
        that.dialogTableVisible = false
        that.$axios({
          url: "/biz/fence/addFences",
          headers: {
            "content-type": "application/json"
          },
          method: "post",
          data: that.fences
        }).then((res) => {
          if (res.data.meta.success) {
            that.$message({
              type: 'success',
              message: '新增犬只禁养电子围栏成功'
            });
            //清空fences
            that.fences = [];
            //为当前多边形添加fenceId
            that.selectPolygon.setExtData(
              {
                fenceId: ""//暂未获取
              }
            );
            //关闭编辑事件
            that.polygonEditor.close();
          }
        })
      },

      //开启地图点击事件监听
      mapAddClick() {
        this.mapClickEvn = AMap.event.addListener(this.mapInstance, "click", this.mapOnClick)
      },

      //map点击事件
      mapOnClick(e) {
        this.beginMarks.push(this.addMarker(e.lnglat));
        this.addPints.push(e.lnglat);
        //绑定地图右键点击事件
        AMap.event.addListener(this.mapInstance, "rightclick", this.mapOnRightClick)
      },

      //地图右键点击事件
      mapOnRightClick() {
        //如果标记点存在且大于二
        if (this.beginMarks && this.beginMarks.length > 2) {
          var polygon = this.createPolygon(this.addPints);
          //开启地图编辑事件
          this.polygonEditor = this.createEditor(polygon);
          this.clearMarks();
          //移除地图点击事件监听
          AMap.event.removeListener(this.mapClickEvn);
        }
      },

      //创建多边形
      createPolygon(arr,fenceId) {
        var that = this;
        var polygon = new AMap.Polygon({
          path: arr,
          fillColor: '#FFAA00', // 多边形填充颜色
          fillOpacity: 0.2,//填充透明度
          borderWeight: 2, // 线条宽度，默认为 1
          strokeColor: 'red', // 线条颜色
          strokeOpacity: 0.5,//线条透明度
          extData: {
            fenceId: fenceId
          }
        });
        this.mapInstance.add(polygon);
        //绑定多边形对象点击时间
        polygon.on("click", function (e) {
          //移除原来的编辑对象
          if(that.polygonEditor){
            that.polygonEditor.close();
          }
          that.polygonEditor = that.createEditor(polygon);
          //获取选中的多边形对象
          that.selectPolygon = polygon;
        });
        //绑定右键点击事件
        polygon.on("rightclick", function (e) {
          that.createContextMenu(polygon, e)
        })
        return polygon;
      },

      //创建地图编辑事件
      createEditor(polygon) {
        this.selectPolygon = polygon;
        var polygonEditor = new AMap.PolyEditor(this.mapInstance, polygon);
        polygonEditor.open();
        //监听编辑事件结束
        AMap.event.addListener(polygonEditor, 'end', this.polygonEnd);
        return polygonEditor;
      },

      polygonEnd(e){

      },

      //添加围栏名称
      addFencesName() {
        //有围栏信息才可以提交
        if (this.addPints && this.addPints.length > 0) {
          let coordinates = this.addPints.map((item, index) => {
            return {
              seq: index,
              longitude: item.lng,
              latitude: item.lat
            }
          })
          let fences = {
            coordinates: coordinates,
            fenceName: '',
            operatorId: configJS.userInfo.userId,
            fenceType: 0,
            status: 1
          }
          this.fences.push(fences);
          this.dialogTableVisible = true;
        }
      },

      //创建右键菜单
      createContextMenu(polygon, e) {
        var that = this;
        var contextMenu = new AMap.ContextMenu();//创建右键菜单
        //获取围栏id
        var extData = polygon.getExtData();
        //添加菜单元素
        contextMenu.addItem("删除", function () {
          //删除多变形之前，取消编辑状态
          if(that.polygonEditor){
            that.polygonEditor.close();
          }
          that.deleteFence(polygon,extData.fenceId)
        }, 0);
        contextMenu.addItem("编辑", function () {
          that.createEditor(polygon);
        }, 0);
        contextMenu.open(that.mapInstance, e.lnglat);
      },

      //删除围栏
      deleteFence(polygon,fenceId){
        this.$axios({
          url: "/biz/fence/deleteFence",
          method: 'post',
          params:{
            fenceId:fenceId
          }
        }).then(res => {
          if (res.data.status == 200) {
            this.$message({
              type: 'success',
              message: '删除围栏成功！'
            });
          }
        })
        this.mapInstance.remove(polygon);
      },

      //围栏信息
      mapPolygon() {
        //查询围栏信息
        this.$axios({
          url: '/biz/fence/selectFenceRefCoordinate',
          method: "POST",
          params: {"operatorId": configJS.userInfo.userId}
        }).then((res) => {
          if (res.data.status == '200' && res.data.data && res.data.data.length > 0) {
            let paths = res.data.data.map(item => {
              let path = item.coordinates.map(e => {//每个围栏信息坐标集
                return new AMap.LngLat(e.longitude, e.latitude)
              })
              this.createPolygon(path,item.fenceId);
            })
          }
        })
      },

      addMarker(lnglat) {
        var marker = new AMap.Marker({
          //icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",   //bug 不能自定义标记
          position: lnglat,
        });
        marker.setMap(this.mapInstance);
        return marker;
      },

      //清除marks
      clearMarks() {
        this.mapInstance.remove(this.beginMarks);
      }


    },
    watch: {}
  }

  //临时线条
  let polylineTemp = null;

  //画直线
  function addpolygon(_this) {
    _this.mapMousemoveEvn = AMap.event.addListener(_this.mapInstance, "mousemove", (e) => {
      if (polylineTemp) {//清楚临时线条
        _this.mapInstance.remove(polylineTemp)
      }
      //点击的经纬度
      let {P: lng, O: lat} = e.lnglat;
      // 创建折线实例
      polylineTemp = new AMap.Polyline({
        path: [
          points,//点击的起始点
          new AMap.LngLat(lng, lat)
        ],
        strokeColor: "#F00",
        strokeOpacity: 0.4,
        strokeWeight: 3,
        strokeStyle: "dashed",
        strokeDasharray: [10, 5]
      });

      // 将折线添加至地图实例
      _this.mapInstance.add(polylineTemp)
    })
  }


  //获取当前坐标
  function getPosition(_this) {
    AMap.plugin('AMap.Geolocation', function () {
      let geolocation = new AMap.Geolocation({
        enableHighAccuracy: true,//是否使用高精度定位，默认:true
        timeout: 10000,          //超过10秒后停止定位，默认：无穷大
        maximumAge: 0,           //定位结果缓存0毫秒，默认：0
        convert: true,           //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
        showButton: true,        //显示定位按钮，默认：true
        buttonPosition: 'LB',    //定位按钮停靠位置，默认：'LB'，左下角
        buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
        showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
        showCircle: true,        //定位成功后用圆圈表示定位精度范围，默认：true
        panToLocation: true,     //定位成功后将定位到的位置作为地图中心点，默认：true
        zoomToAccuracy: false      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
      });
      _this.mapInstance.addControl(geolocation);
      geolocation.getCurrentPosition()
      // geolocation.getCurrentPosition((status,result)=>{});
      AMap.event.addListener(geolocation, 'complete', (e) => {
        // let curPostion = [e.position.P,e.position.O]
        // _this.curPostion = curPostion
        // console.log(e)
      });//返回定位信息
      AMap.event.addListener(geolocation, 'error', (e) => {//返回定位出错信息
      });
    });
  }

  function initMap(_this) {
    _this.mapInstance = new AMap.Map("container-map", {
      resizeEnable: true,
      center: _this.curPostion,//地图中心点
      zoom: 16, //地图显示的缩放级别
    });
  }

  //将json字符转化为坐标数组
  function json2arr(json) {
    var arr = JSON.parse(json);
    var res = [];
    for (var i = 0; i < arr.length; i++) {
      var line = [];
      line.push(arr[i].lng);
      line.push(arr[i].lat);
      res.push(line);
    }
    ;
    return res;
  }
</script>

<style scoped>
  .hb100 {
    height: 838px;
  }

  .borRed {
    border: solid 1px red;
  }

  .btnactive {
    color: #ccc;
    cursor: auto;
  }

  .btnDisable {
    color: rgb(285, 163, 118);
  }
</style>
