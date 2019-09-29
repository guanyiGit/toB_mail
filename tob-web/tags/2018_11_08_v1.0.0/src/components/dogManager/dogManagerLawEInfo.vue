<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li>
          <router-link :to="{name:'dogManager_LawEList'}">执法处罚&nbsp;&gt;&nbsp;</router-link>
        </li>
        <li>查看详情</li>
      </ul>
    </div>

    <div class="mgt20 k3">
      <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
        犬只/犬主基本信息
      </div>
      <div class="t-c">
        <table class="table10" width="100%" border="1">
          <tbody>
          <tr>
            <td class="ww20">犬名</td>
            <td class="cor9 ww30">{{violationInfo?violationInfo.dogName:''}}</td>
            <td class="ww20">品种</td>
            <td class="cor9 ww30">{{violationInfo?violationInfo.dogBreed:''}}</td>
          </tr>
          <tr>
            <td>性别</td>
            <td class="cor9">{{violationInfo&&violationInfo.dogGender==0?'雄':'雌'}}</td>
            <td>年龄</td>
            <td class="cor9">{{violationInfo&&violationInfo.dogAge?violationInfo.dogAge:'0'+' 岁'}}</td>
          </tr>
          <tr>
            <td>出生日期</td>
            <td class="cor9" v-if="violationInfo&&violationInfo.birthTime">{{violationInfo.birthTime |
              formatDate(pattern)}}
            </td>
            <td class="cor9" v-else></td>
            <td>毛色</td>
            <td class="cor9">{{violationInfo?violationInfo.dogColor:''}}</td>
          </tr>
          <tr>
            <td>犬证号</td>
            <td class="cor9">{{violationInfo?violationInfo.dogCardNum:''}}</td>
            <td>标识号</td>
            <td class="cor9">{{violationInfo?violationInfo.deviceNumber:''}}</td>
          </tr>
          <tr>
            <td>犬主姓名</td>
            <td class="cor9">{{violationInfo?violationInfo.dogOwnerName:''}}</td>
            <td>身份证号</td>
            <td class="cor9">{{violationInfo?violationInfo.cardNum:''}}</td>
          </tr>
          <tr>
            <td>照片</td>
            <td class="cor9 pdt20 pdb20 pdl30" colspan="3">
              <p class="fl mgr10"><img :src="violationInfo?violationInfo.thumbnailUrl:'/static/images/28-1.png'" alt="">
              </p>
            </td>
          </tr>

          </tbody>
        </table>
      </div>
    </div>

  </div>
  <!--内容区END-->
</template>

<script>
  export default {
    name: "dogManagerLawEInfo",
    data() {
      return {
        pattern: 'yyyy年MM月dd日',
        violationInfo: {},
      }
    },
    created() {
      initData(this);
    },
  }

  function initData(_this) {
    _this.$axios({
      url: '/biz/violationPunish/findOne',
      params: {'violationId': _this.$route.params.violationId}
    }).then(res => {
      if (res.data.status == 200) {
        _this.violationInfo = res.data.data;
        console.log(res)
      }
    })
  }
</script>

<style scoped>
  td {
    text-align: center;
  }
</style>
