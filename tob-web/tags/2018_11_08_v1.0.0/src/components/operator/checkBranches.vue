<template>
  <!--查看服务网点-->
  <div class="w980 fr">
    <!--内容区-->
    <div class="mbx">
      <ul class="ul11 clearfix top-nav">
        <li>
          <router-link style="cursor:pointer;" :to="{name:'serviceNetworkAudit'}">网点列表></router-link>
        </li>
        <li>网点详情</li>
      </ul>
    </div>
    <div class="k2 w660 c-b ovh">
      <a style="cursor:pointer;" class="db fl fs16 cor0 w220"><img class="v-m mgr10" src="../../../static/images/30.png"
                                                                   alt="">宠物医院提交申请</a>
      <a style="cursor:pointer;" class="db fl fs16 cor0 w220 t-c active">平台审核</a>
      <a style="cursor:pointer;" class="db fl fs16 cor0 w220 t-c">结束</a>
    </div>

    <div class="mgt20 k3">
      <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
        基本信息
      </div>
      <div class="t-c">
        <table class="table10" width="100%" border="1">
          <tbody>
          <tr>
            <td>医院名称</td>
            <td class="cor9">{{info?info.organizationName?info.organizationName:'':''}}</td>
            <td>注册资本</td>
            <td class="cor9">{{info?info.organizationCapital?info.organizationCapital:'':''}}万</td>
          </tr>
          <tr>
            <td>注册号</td>
            <td class="cor9">{{info?info.registerNumber?info.registerNumber:'':''}}</td>
            <td>统一社会信用代码</td>
            <td class="cor9">{{info?info.creditCode?info.creditCode:'':''}}</td>
          </tr>
          <tr>
            <td>成立日期</td>
            <td v-if="info" class="cor9">{{info.establishDate | formatDate(datePattern)}}</td>
            <td v-else class="cor9"></td>
            <td>所属省份</td>
            <td class="cor9">{{info?info.province?info.province:'':''}}</td>
          </tr>
          <tr>
            <td>所属城市</td>
            <td class="cor9">{{info?info.ownedCity?info.ownedCity:'':''}}</td>
            <td>所属区/县</td>
            <td class="cor9">{{info?info.district?info.district:'':''}}</td>
          </tr>
          <tr>
            <td>联系人</td>
            <td class="cor9">{{info?info.personLiable?info.personLiable:'':''}}</td>
            <td>联系电话</td>
            <td class="cor9">{{info?info.phone?info.phone:'':''}}</td>
          </tr>
          <tr>
            <td>详细地址</td>
            <td class="cor9 t-l" style="padding-left: 10%;" colspan="3">{{info?info.address?info.address:'':''}}</td>
          </tr>
          <tr>
            <td>经营范围</td>
            <td class="cor9 t-l" style="padding-left: 14%;" colspan="3">
              {{info?info.scopeOfOperation?info.scopeOfOperation:'':''}}
            </td>
          </tr>
          <tr>
            <td>店铺照片</td>
            <td class="cor9 pdt20 pdb20 pdl30" colspan="3">
              <p class="fl mgr10"><img src="../../../static/images/28-1.png" alt=""></p>
              <p class="fl"><img src="../../../static/images/28-2.png" alt=""></p>
            </td>
          </tr>

          </tbody>
        </table>
      </div>
    </div>

    <div class="mgt20 k3">
      <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
        证件信息
      </div>
      <div>
        <table class="table10 t-c">
          <tbody>
          <tr>
            <td colspan="4"><span class="dinb mgr10 sp3">1</span>动物诊断许可证</td>
          </tr>
          <tr>
            <td>证件号</td>
            <td>{{certificate1?certificate1.petHCardNum?certificate1.petHCardNum:'':''}}</td>
            <td>有效期限</td>
            <td v-if="certificate1&&certificate1.startTime">{{certificate1.startTime | formatDate(datePattern)}}</td>
            <td v-else></td>
            <td v-if="certificate1&&certificate1.endTime">{{certificate1.endTime | formatDate(datePattern)}}</td>
            <td v-else></td>
          </tr>
          <tr>
            <td class="pdt20 pdb20" colspan="5">
              <p class="fl pdl60 pdr100">图片</p>
              <p v-if="certificate1&&certificate1.images.length>0" class="fl">
                <img v-for="item in certificate1.images" v-bind:src="item.thumbnailUrl" alt="">
              </p>
              <p v-else class="fl">
                <img src="../../../static/images/28-8.png" alt="">
              </p>
            </td>
          </tr>

          <tr>
            <td colspan="4"><span class="dinb mgr10 sp3">2</span>动物防疫许可证</td>
          </tr>
          <tr>
            <td>证件号</td>
            <td>{{certificate2?certificate2.petHCardNum?certificate2.petHCardNum:'':''}}</td>
            <td>有效期限</td>
            <td v-if="certificate1&&certificate1.startTime">{{certificate1.startTime | formatDate(datePattern)}}</td>
            <td v-else></td>
            <td v-if="certificate1&&certificate1.endTime">{{certificate1.endTime | formatDate(datePattern)}}</td>
            <td v-else></td>
          </tr>
          <tr>
            <td class="pdt20 pdb20" colspan="5">
              <p class="fl pdl60 pdr100">图片</p>
              <p v-if="certificate1&&certificate1.images.length>0" class="fl">
                <img v-for="item in certificate1.images" v-bind:src="item.thumbnailUrl" alt="">
              </p>
              <p v-else class="fl">
                <img src="../../../static/images/28-8.png" alt="">
              </p>
            </td>
          </tr>
          <tr>
            <td colspan="4"><span class="dinb mgr10 sp3">3</span>工商营业执照</td>
          </tr>
          <tr>
            <td>证件号</td>
            <td>{{certificate3?certificate3.petHCardNum?certificate3.petHCardNum:'':''}}</td>
            <td>有效期限</td>
            <td v-if="certificate1&&certificate1.startTime">{{certificate1.startTime | formatDate(datePattern)}}</td>
            <td v-else></td>
            <td v-if="certificate1&&certificate1.endTime">{{certificate1.endTime | formatDate(datePattern)}}</td>
            <td v-else></td>
          </tr>
          <tr>
            <td class="pdt20 pdb20" colspan="5">
              <p class="fl pdl60 pdr100">图片</p>
              <p v-if="certificate1&&certificate1.images.length>0" class="fl">
                <img v-for="item in certificate1.images" v-bind:src="item.thumbnailUrl" alt="">
              </p>
              <p v-else class="fl">
                <img src="../../../static/images/28-8.png" alt="">
              </p>
            </td>
          </tr>
          <tr>
            <td colspan="4"><span class="dinb mgr10 sp3">4</span>兽医资格证</td>
          </tr>
          <tr>
            <td>证件号</td>
            <td>{{certificate4?certificate4.petHCardNum?certificate4.petHCardNum:'':''}}</td>
            <td>有效期限</td>
            <td v-if="certificate1&&certificate1.startTime">{{certificate1.startTime | formatDate(datePattern)}}</td>
            <td v-else></td>
            <td v-if="certificate1&&certificate1.endTime">{{certificate1.endTime | formatDate(datePattern)}}</td>
            <td v-else></td>
          </tr>
          <tr>
            <td class="pdt20 pdb20" colspan="4">
              <p class="fl pdl60 pdr100">图片</p>
              <p v-if="certificate1&&certificate1.images.length>0" class="fl">
                <img v-for="item in certificate1.images" v-bind:src="item.thumbnailUrl" alt="">
              </p>
              <p v-else class="fl">
                <img src="../../../static/images/28-8.png" alt="">
              </p>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div v-if="info&&info.failReason" style="margin-top: 20px">不通过原因：{{info?info.failReason?info.failReason:'':''}}
    </div>
  </div>
</template>

<script>
  export default {
    name: "checkBranches",
    data() {
      return {
        info: null,
        datePattern: 'yyyy年MM与dd日',
        certificate1: null,//获取动物诊疗许可证
        certificate2: null,//动物防疫合格证
        certificate3: null,//工商营业执照
        certificate4: null,//兽医资格证
      }
    },
    mounted(){
      console.log(this.$data)
    },
    created() {
      this.$axios({
        url: '/biz/serviceSite/getServiceSite',
        method: 'post',
        params: {organizationId: this.$route.params.organizationId}
      }).then(res => {
        if (res.data.status == 200) {
          this.info = res.data.data;
        }
      }),
        selectCertificate(this);
    },
    methods: {
      notThrough() {
        this.$axios({
          url: '/biz/information/examine',
          method: 'post',
          params: {status: 3, informationId: this.$route.params.informationId}
        }).then(res => {
          if (res.data.status == 200) {
            this.$alert("提交成功！");
            this.$router.push('operatorMsgAuditLis');//其中operatorMsgAuditLis是你定义的一个路由模块
          }
        })
      },
      through() {
        this.$axios({
          url: '/biz/information/examine',
          method: 'post',
          params: {status: 1, informationId: this.$route.params.informationId}
        }).then(res => {
          if (res.data.status == 200) {
            this.$alert("提交成功！");
            this.$router.push('operatorMsgAuditLis');//其中operatorMsgAuditLis是你定义的一个路由模块
          }
        })
      },
      cancel() {
        this.info = null;
        this.$router.push('operatorMsgAuditLis');//其中operatorMsgAuditLis是你定义的一个路由模块
      }
    }
  }

  //查询证件信息
  function selectCertificate(_this) {
    _this.$axios({
      url: '/biz/serviceSite/selectSiteCard',
      method: 'post',
      params: {organizationId: _this.$route.params.organizationId}
    }).then(res => {
      if (res.data.status == 200 && res.data.data != null) {
        var list = res.data.data;
        for (var i; i < list.list; i++) {
          if (list[i].cardType == 0) {
            certificate1 = list[i];
          } else if (list[i].cardType == 1) {
            certificate2 = list[i];
          } else if (list[i].cardType == 2) {
            certificate3 = list[i];
          } else if (list[i].cardType == 3) {
            certificate4 = list[i];
          }
        }
      }
    })
  }
</script>

<style scoped>
.top-nav{
  margin-bottom: 34px;
}
</style>
