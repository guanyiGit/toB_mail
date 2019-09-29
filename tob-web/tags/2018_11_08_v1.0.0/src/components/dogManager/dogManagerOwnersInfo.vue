<template>
  <!--内容区-->
  <div class="w980 fr">
    <div class="mbx">
      <ul class="ul11 clearfix">
        <li><router-link :to="{name:'dogManager_owners_list'}">犬主档案></router-link></li>
        <li>查看详情</li>
      </ul>
    </div>

    <div class="mgt15 k3">
      <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">证件信息</div>
      <div v-if="dogOwnerInfo.tMemberCard" class="pdt10 pdb20 pdr30 borb2">
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">会员证号</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="dogOwnerInfo.tMemberCard?dogOwnerInfo.tMemberCard.memberCardNo:''">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">签发时间</span>
            <div class="mgl90">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4"
                     disabled v-if="dogOwnerInfo.tMemberCard&&dogOwnerInfo.tMemberCard.issueDate" :value="dogOwnerInfo.tMemberCard.issueDate | formatDate(datePattern)">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else>
            </div>
          </div>
        </div>

        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">签发日期</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
            </div>
          </div>
        </div>

        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">有效期限</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4"
                     disabled v-if="dogOwnerInfo.tMemberCard&&dogOwnerInfo.tMemberCard.upTime" :value="dogOwnerInfo.tMemberCard.upTime | formatDate(datePattern)">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else>
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30"><img src="/static/images/bg14.png" alt=""></span>
            <div class="mgl90">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4"
                     disabled v-if="dogOwnerInfo.tMemberCard&&dogOwnerInfo.tMemberCard.expireTime" :value="dogOwnerInfo.tMemberCard.expireTime | formatDate(datePattern)">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else>
            </div>
          </div>
        </div>
      </div>
      <div v-else class="none">无数据</div>
    </div>

    <div class="mgt10 k3">
      <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">犬主信息</div>
      <div class="pdt10 pdb20 pdr30 borb2">
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">犬主姓名</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="dogOwnerInfo.dogOwners?dogOwnerInfo.dogOwners.dogOwnerName:''">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">性质</span>
            <div class="mgl90">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="dogOwnerInfo.dogOwners&&dogOwnerInfo.dogOwners.dogOwnerType==0?'个人':'企业'">
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">证件类型</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4"
                     disabled :value="dogOwnerInfo.dogOwners&&dogOwnerInfo.dogOwners.cardType==0?'身份证':
                     dogOwnerInfo.dogOwners&&dogOwnerInfo.dogOwners.cardType==1?'房产证':
                     dogOwnerInfo.dogOwners&&dogOwnerInfo.dogOwners.cardType==2?'营业执照':'其他'">

            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">证件号码</span>
            <div class="mgl90">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="dogOwnerInfo.dogOwners?dogOwnerInfo.dogOwners.cardNum:''">
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">性别</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="dogOwnerInfo.dogOwners&&dogOwnerInfo.dogOwners.sex==0?'男':'女'">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">出生日期</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4"
                     disabled v-if="dogOwnerInfo.dogOwners&&dogOwnerInfo.dogOwners.birthDate" :value="dogOwnerInfo.dogOwners.birthDate |formatDate(datePattern)">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled v-else>
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">现住区县</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="dogOwnerInfo.dogOwners?dogOwnerInfo.dogOwners.districtName:''">
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">所属社区</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="dogOwnerInfo.dogOwners?dogOwnerInfo.dogOwners.community:''">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">邮编</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="dogOwnerInfo.dogOwners?dogOwnerInfo.dogOwners.postalCode:''">
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">联系电话</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="dogOwnerInfo.dogOwners?dogOwnerInfo.dogOwners.dogOwnerPhone:''">
            </div>
          </div>
          <div class="mgt10 ww50 fr">
            <span class="db w90 fl h40 lh40 cor0 pdl30">电子邮箱</span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4" disabled :value="dogOwnerInfo.dogOwners?dogOwnerInfo.dogOwners.eMail:''">
            </div>
          </div>
        </div>
        <div class="ovh">
          <div class="mgt10 ww50 fl">
            <span class="db w100 fl h40 lh40 cor0 pdl30">详细地址 </span>
            <div class="mgl100">
              <input type="text" class="ww100 h40 lh40 pdl20 borf1 ras4"  disabled :value="dogOwnerInfo.dogOwners?dogOwnerInfo.dogOwners.address:''">
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="mgt10 k3">
      <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">犬只信息</div>
      <div v-if="dogOwnerInfo.myDogs">
        <table class="table2" width="100%" border="1">
          <tbody>
          <tr>
            <td>犬名</td>
            <td>品种</td>
            <td>性别</td>
            <td>毛色</td>
            <td>出生日期</td>
            <td>登记日期</td>
            <td>操作</td>
          </tr>
          <tr v-if="dogOwnerInfo.myDogs && dogOwnerInfo.myDogs.length>0" v-for="(item,index) in dogOwnerInfo.myDogs" :key="index">
            <td>{{item.dogName}}</td>
            <td>{{item.breed}}</td>
            <td>{{item.dogGender==0?'雄':'雌'}}</td>
            <td>{{item.birthday | formatDate(datePattern)}}</td>
            <td>{{item.lssueTime |formatDate(datePattern)}}</td>
            <td>{{item.injectionDate |formatDate(datePattern)}}</td>
            <td><router-link :to="{name: 'dogManager_info',params:{dogId:item.dogId}}"><font color="blue">查看详情</font></router-link></td>
          </tr>
          </tbody>
        </table>
      </div>
      <div v-else class="none">无数据</div>
    </div>

  </div>
  <!--内容区END-->
</template>

<script>
  export default {
    name: "dogManagerOwnersInfo",
    data() {
      return {
        datePattern:'yyyy年MM月dd日',
        dogOwnerId: 1,
        dogOwnerInfo: {},//犬主信息
      }
    },
    created() {
      initData(this)
      // let ownerId = this.$route.params.id
    }

  }

  function initData(_this) {
    _this.$axios({
      url: "/biz/dogAndOwner/selectDogOwnerAllInfo",
      params: {'ownerId': _this.dogOwnerId}
    }).then(res => {
      if(res.data.code = '000000'){
        _this.dogOwnerInfo = res.data.result;
      }
      console.log(res)
    })
  }
</script>

<style scoped>
.none{margin: 30px 0 30px 30px;}
</style>
