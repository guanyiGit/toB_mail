<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li>
          <router-link :to="{name:'dogManager_blacklist'}">黑名单&gt;</router-link>
        </li>
        <li>查看详情</li>
      </ul>
    </div>

    <div class="mgt10 k3">
      <table class="table6" width="100%" border="1">
        <tbody>
        <tr>
          <td>姓名</td>
          <td class="cor9">{{blackInfo.blackName}}</td>
          <td></td>
          <td></td>
          <td rowspan="10"><img src="/static/images/b16.png" alt=""></td>
        </tr>
        <tr>
          <td>证件类型</td>
          <td>{{blackInfo.cardType==0?'身份证':blackInfo.cardType==1?'房产证':blackInfo.cardType==2?'营业执照':'其他'}}</td>
          <td>证件号</td>
          <td>{{blackInfo.cardNum}}</td>
        </tr>
        <tr>
          <td>性别</td>
          <td>{{blackInfo.sex==0?'男':blackInfo.sex==1?'女':'-'}}</td>
          <td>出生日期</td>
          <td>{{blackInfo.birthData | formatDate("yyyy年MM月dd日")}}</td>
        </tr>

        <tr>
          <td>现住区县</td>
          <td>{{blackInfo.county}}</td>
          <td>所属部门</td>
          <td>{{blackInfo.dep}}</td>
        </tr>
        <tr>
          <td>所属社区</td>
          <td>{{blackInfo.community}}</td>
          <td>邮编</td>
          <td>{{blackInfo.postcode}}</td>
        </tr>
        <tr>
          <td>联系电话</td>
          <td>{{blackInfo.phone}}</td>
          <td>电子邮箱</td>
          <td>{{blackInfo.email}}</td>
        </tr>
        <tr>
          <td>详细地址</td>
          <td colspan="3">{{blackInfo.address}}</td>
        </tr>

        <tr>
          <td colspan="4">列入黑名单原因<span class="mgl30">{{blackInfo.joinCause}}</span></td>
        </tr>
        <tr>
          <td colspan="4">备注 <span class="mgl30">{{blackInfo.remark?blackInfo.remark:'除暴安良是我们做市民的责任，行善积德是我本人的兴趣'}}</span></td>
        </tr>
        </tbody>
      </table>
    </div>

  </div>
  <!--内容区END-->
</template>

<script>
  export default {
    name: "dogManagerBlackInfo",
    data() {
      return{
        blackInfo:{}//详情
      }
    },
    mounted() {
      console.log(this.$data)
    },
    created(){
      initData(this);
    }
  }

  function initData(_this) {
    _this.$axios({
      url: '/biz/balck/findOne',
      params: {"blackId": _this.$route.params.blackId}
    }).then((res) => {
      if (res.data.data != null && res.data.status == 200) {
        _this.blackInfo = res.data.data
      }
    }).catch((res)=>{
      console.log(res.message)
    })
  }
</script>

<style scoped>

</style>
