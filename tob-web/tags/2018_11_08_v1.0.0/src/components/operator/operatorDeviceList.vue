<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="ovh div1 h50 rel tablbaks">
      <p class="abs ww100 left0 bottom0"></p>
      <a href="javascript:void(0);" class="fl db fs16 cor3c3c3c w200 h50 lh50 t-c" :class="checkTab == 0?'active':''"
         @mouseover.prevent="checkTab = 0">已激活</a>
      <a href="javascript:void(0);" class="fl db fs16 cor3c3c3c w200 h50 lh50 t-c" :class="checkTab == 1?'active':''"
         @mouseover.prevent="checkTab = 1">未激活</a>
    </div>

    <div class="div12 db mgt20">
      <div class="ovh">
        <input type="file" class="uploadfile" accep="xls" @change="fileUpload" name="file" style="display: none;">
        <div class="fl cor6 h40 lh40">
          <a href="###" class="db fl hov8 corffa472 mgr35 pc9 pdl36" @click.prevent="downloadExample">模板下载</a>
          <a href="###" class="db fl hov8 corffa472 mgr35 pc9 pdl36" @click.prevent="fileUploadClick()">导入</a>
          <a href="###" v-if="checkTab==0" class="db fl hov8 corffa472 mgr35 pc9 pdl36"
             @click.prevent="exportData">导出</a>
          <a href="###" v-else class="db fl hov8 corffa472 mgr35 pc9 pdl36" @click.prevent="disabledMany">禁用
          </a>
        </div>
        <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
          <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="设备标题/品牌" v-model="keyword">
          <input type="submit" value="" class="in1 db abs z100 top0 bort right0">
        </form>
      </div>
      <div class="ovh k3 mgt20">
        <table class="table11" width="100%">
          <tbody>
          <tr>
            <td class="cor0 h60 bgf1" style="width: 50px;">
              <label class="lab3 mgr40"><input type="checkbox" v-model="checkAll"><i></i></label>
            </td>
            <td class="ww24">设备编号</td>
            <td>品牌</td>
            <td>型号</td>
            <td>出厂日期</td>
            <td>批次</td>
            <td>状态</td>
            <td>操作</td>
          </tr>
          <tr v-for="(item,index) in list" :key="index" v-if="list && list.length>0">
            <td>
              <label class="lab3 mgr40"><input type="checkbox" v-model="item.isCheckd"><i></i></label>
            </td>
            <td>{{item.deviceNumber}}</td>
            <td>{{item.deviceBrand}}</td>
            <td>{{item.tDeviceModel?item.tDeviceModel.deviceModelName:''}}</td>
            <td>{{item.productionTime | formatDate(datePattern)}}</td>
            <td>{{item.deviceBatch}}</td>
            <td>{{item.deviceStatus==0?'禁用':item.deviceStatus==1?'启用':'已注销'}}</td>
            <td>
              <a href="###" v-if="checkTab==0" @click.prevent="disabledSingle(item.deviceId)"
                 class="dinb hov8 cor009cff">禁用 </a>
              &nbsp; &nbsp; &nbsp;<router-link :to="{name:'operator_device_info',params:{'devNo':item.deviceNumber}}"
                                               class="dinb hov8 cor009cff">查看详情
            </router-link>
            </td>
          </tr>
          </tbody>
        </table>

      </div>
    </div>
    <!--分页-->
    <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
    <!--分页-->
  </div>
</template>

<script>
  import $ from 'jquery';
  import configJS from '../../common/config'
  import {sendHttp} from '../../../static/js/sendHttp'

  //请求参数pageData
  let params = {
    offset: 1,
    limit: 10,
    deviceStatus: 1,//【0禁用，1启用,2注销】
    keyWord: null,
  }
  export default {
    name: "operatorMsgAuditLis",
    data() {
      return {
        checkTab: 0,//页签tab
        datePattern: 'yyyy年MM与dd日',
        totalCount: 0,//总条数
        list: [],//资讯列表
        keyword: null,//搜索关键字
        checkAll: false,//全选
      }
    },
    methods: {
      //分页
      chekdpage(currentPage) {
        params.offset = currentPage//页码
        initData(this)
      },
      //单个禁用
      disabledSingle(deviceId) {
        let deviceIds = [deviceId]
        let status = 0;
        disableHandle(this, deviceIds, status);
      },
      //多项禁用
      disabledMany() {
        if (this.list.some(e => e.isCheckd)) {
          let deviceIds = this.list.map(e => {
            if (e.isCheckd)
              return e.deviceId;
          })
          let status = 0;
          disableHandle(this, deviceIds, status)
        } else {
          this.$message("您还没有选中！")
        }
      },
      //模板下载
      downloadExample() {
        window.location.href = `${configJS.basicUrl}/device/export?userId=${configJS.userInfo.userId}`
      },
      fileUploadClick() {
        $('.uploadfile').trigger('click')
      },
      //excel上传
      fileUpload(e) {
        let files = e.currentTarget.files
        if (files && files.length == 1) {
          if (/.xls/i.test(files[0].name)) {
            let formdata = new FormData();
            formdata.append('file', files[0]);
            this.$axios({
              url: '/biz/device/import',
              method: 'post',
              headers: {'Content-Type': 'multipart/form-data'},
              params: {"orgId": configJS.userInfo.organizationId},
              data: formdata
            }).then(res => {
              if (res.data.meta.success) {
                this.$alert("上传成功！");
              } else {
                this.$alert(res.data.meta.message);
              }
            })
          } else {
            this.$message("请填写“device.xls”模板后上传！");
          }
        } else {
          this.$message("一次只能上传一个文件！")
        }
        //清空输入框
        e.currentTarget.value = '';
      },
      //导出设备数据
      exportData() {
        if (this.list.some(e => e.isCheckd)) {
          let paramDatas = this.list.filter(e => e.isCheckd)
          //导出数据
          let baseUrl = configJS.basicUrl
          sendHttp(baseUrl + "/device/export/data", "post", {"paramStr": JSON.stringify(paramDatas)});
        } else {
          this.$message("您还没有选中！")
        }
      }

    },
    computed: {
      //分页参数
      pageData() {
        return {
          curPage: params.offset,//当前页吗
          totalCount: this.totalCount,//总条数
          pageSize: params.limit//页面大小}
        }
      },
    },
    created() {
      initData(this)
    },
    watch: {
      checkTab(newVal, oldVal) {
        if (newVal != oldVal) {
          newVal = oldVal
          params.deviceStatus = newVal;//切换tab
          initData(this)
        }
      },
      keyword(newVal, oldVal) {
        if (newVal != oldVal) {
          params.keyWord = newVal;
          initData(this)
        }
      },
      //全选
      checkAll(newVal) {
        for (let temp of this.list) {
          temp.isCheckd = !!newVal;
        }
      },
    }

  }

  //禁用
  function disableHandle(_this, deviceIds, status) {
    _this.$axios({
      url: '/biz/device/lists/' + status,
      method: 'post',
      params: {"deviceIds": deviceIds.join()}
    }).then(res => {
      if (res.data.meta.success) {
        this.$message('操作成功！');
      } else {
        this.$message('操作失败！');
      }
    })
  }

  function initData(_this) {
    if (!params.keyWord)
      delete  params.keyWord
    _this.$axios({
      url: '/biz/device/list',
      method: 'get',
      params: params
    }).then(res => {
      if (res.data.meta.success) {
        console.log(res.data)
        for (let temp of res.data.data.list) {
          temp.isCheckd = false;//默认不选中
        }
        _this.totalCount = res.data.data.total;
        _this.list = res.data.data.list;
      }
    })


  }

</script>

<style scoped>

</style>
