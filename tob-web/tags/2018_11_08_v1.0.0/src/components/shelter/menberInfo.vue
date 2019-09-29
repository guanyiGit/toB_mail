<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li><router-link :to="{name:'hospice_memberCardList'}">会员证管理></router-link></li>
        <li>查看详情</li>
      </ul>
    </div>
    <div class="mgt20 k3">
      <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
        证件信息
      </div>
      <div>
        <table class="table10 t-c">
          <tbody>
          <tr>
            <td class="ww20">免疫证号</td>
            <td class="cor9 ww30">{{cradInfo.memberCardNo}}</td>
            <td class="ww20">签发机构</td>
            <td class="cor9 ww30">{{cradInfo.tOrganization?cradInfo.tOrganization.organizationName:''}}</td>
          </tr>
          <tr>
            <td class="ww20">办理日期</td>
            <td class="cor9 ww30">{{cradInfo.creationTime |formatDate(datePattern)}}</td>
          </tr>
          <tr>
            <td class="ww20">签发日期</td>
            <td class="cor9 ww30">{{cradInfo.issueDate|formatDate(datePattern)}}</td>
            <td class="ww20">有效期限</td>
            <td class="cor9 ww30">{{cradInfo.expireTime|formatDate(datePattern)}}</td>
          </tr>

          </tbody>
        </table>
      </div>
    </div>
    <div class="mgt20 k3">
      <div class="h56 lh56 bgf8 pdl30 pdr30 fs16 cor0 fb">
        犬主信息
      </div>
      <div class="t-c" >
        <table class="table10" width="100%" border="1">
          <tbody>
          <tr>
            <td class="ww20">犬主姓名</td>
            <td class="cor9 ww30">{{cradInfo.tDogOwner?cradInfo.tDogOwner.dogOwnerName:''}}</td>
            <td class="ww20">性质</td>
            <td class="cor9 ww30">{{cradInfo.tDogOwner&&cradInfo.tDogOwner.dogOwnerType==1?'个人':'企业'}}</td>
          </tr>
          <tr>
            <td>证件类型</td>
            <td class="cor9">{{cradInfo.tDogOwnerCards &&cradInfo.tDogOwnerCards.length>0&&
              cradInfo.tDogOwnerCards[0].cardType==0?'身份证':
              cradInfo.tDogOwnerCards &&cradInfo.tDogOwnerCards.length>0&& cradInfo.tDogOwnerCards[0].cardType==1?'房产证':
              cradInfo.tDogOwnerCards &&cradInfo.tDogOwnerCards.length>0&&
              cradInfo.tDogOwnerCards[0].cardType==2?'营业执照':'其他'}}
            </td>
            <td>证件号码</td>
            <td class="cor9">
              {{cradInfo.tDogOwnerCards&&cradInfo.tDogOwnerCards.length>0?cradInfo.tDogOwnerCards[0].card_num:''}}
            </td>
          </tr>
          <tr>
            <td>性别</td>
            <td class="cor9">{{cradInfo.tDogOwner&&cradInfo.tDogOwner.sex==0?'男':'女'}}</td>
            <td>出生日期</td>
            <td class="cor9" v-if="cradInfo.tDogOwner&&cradInfo.tDogOwner.birthDate">{{cradInfo.tDogOwner.birthDate
              |formatDate(datePattern)}}
            </td>
            <td class="cor9" v-else></td>
          </tr>
          <!--<tr>-->
          <!--<td>现住区县</td>-->
          <!--<td class="cor9">461315498</td>-->
          <!--<td>所属部门</td>-->
          <!--<td class="cor9">16345667893</td>-->
          <!--</tr>-->
          <tr>
            <td>住址</td>
            <td class="cor9">{{cradInfo.tDogOwner?cradInfo.tDogOwner.address:''}}</td>
          </tr>
          <tr>
            <td>联系电话</td>
            <td class="cor9">{{cradInfo.tUsers?cradInfo.tUsers.phone:''}}</td>
            <td>电子邮箱</td>
            <td class="cor9">{{cradInfo.tUsers?cradInfo.tUsers.email:''}}</td>
          </tr>
          <!--<tr>-->
          <!--<td>照片</td>-->
          <!--<td class="cor9 pdt20 pdb20 pdl30" colspan="3">-->
          <!--<p class="fl mgr10"><img src="images/b16.png" alt=""></p>-->

          <!--</td>-->
          <!--</tr>-->

          </tbody>
        </table>
      </div>
    </div>
    <!--<div class="t-c mgt40 mgb50">
            <input type="submit" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" value="保存">
            <a href="###" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</a>
        </div>-->
  </div>
  <!--内容区END-->
</template>

<script>
  export default {
    name: "menberInfo",
    data() {
      return {
        datePattern: 'yyyy年MM月dd日',
        cradInfo: {},//证信息
      }
    },
    created() {
      let memberCardId = this.$route.params.cardId;
      this.$axios({
        url: '/biz/member/one',
        params: {"memberCardId": memberCardId}
      }).then(res => {
        if (res.data.meta.success) {
          this.cradInfo = res.data.data;
        }
        console.log(res)
      })

    }
  }
</script>

<style scoped>
  td {
    text-align: center;
  }

</style>
