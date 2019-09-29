<template>
    <!--确认领养-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <form action="#">
        <div class="mgt10 k3">
          <table class="table5 t-c" width="100%" border="1">
            <tbody>
            <tr>
              <td class="cor0 h60 bgf1">
                <!--<label class="lab3 mgr40"><input type="checkbox"><i></i></label>-->
              </td>
              <td class="cor0 h60 bgf1 ww8">姓名</td>
              <td class="cor0 h60 bgf1">性别</td>
              <td class="cor0 h60 bgf1">证件类型</td>
              <td class="cor0 h60 bgf1 ww18">身份证号</td>
              <td class="cor0 h60 bgf1 ww16">联系电话</td>
              <td class="cor0 h60 bgf1">申请时间</td>
              <td class="cor0 h60 bgf1">养犬地址</td>
            </tr>
            <tr v-for="(item,index) in adoptList" :key="index">
              <td>
                <label class="lab3 mgr40"><input type="radio" name="nam" v-model="item.isCheckd"><i></i></label>
              </td>
              <td>{{item.applyerName}}</td>
              <td>{{item.applyerSex==0?'雄':'雌'}}</td>
              <td>{{item.cardType==0?'身份证':item.cardType==1?'房产证':item.cardType==2?'营业执照':'其他'}}</td>
              <td>{{item.cardNumber}}</td>
              <td>{{item.applyerPhone}}</td>
              <td>{{item.adoptionTime | formatDate('yyyy-MM-dd')}}</td>
              <td class="t-c">{{item.adoptionAddress}}</td>
            </tr>
            </tbody>
          </table>
          <div class="t-c mgt40 mgb50">
            <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" @click="applySuer">确认领养</a>
            <router-link :to="{name:'dogShelter'}" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8 mgl5">取消</router-link>
          </div>
        </div>
      </form>
    </div>
    <!--内容区END-->
  </div>
</template>

<script>
    export default {
        name: "confirmToAdopt",
      data(){
          return{
            adoptList:[]
          }
      },
      created() {
        initData(this)
      },
      methods: {
        applySuer() {
          let obj={
            collectionId:null,
            dogPublicityId:null,
            adoptionRecordsId:null
          }
          this.adoptList.map(item => {
            if (item && item.isCheckd) {//所有选中的
              obj.collectionId=item.collectionId
              obj.dogPublicityId=item.adoptionPublicityId
              obj.adoptionRecordsId=item.adoptionRecordsId
            }
          })
          if (obj.collectionId!=null && obj.dogPublicityId!=null && obj.adoptionRecordsId!=null) {
            this.$confirm("确定领养吗?", function(ok){
              if (ok) {
                this.$axios({
                  url: '/biz/adoption/applySuer',
                  method: 'get',
                  params: obj
                }).then((res) => {
                  if (res.data.status == 200) {
                    if (res.data.data!=null) {
                      this.$router.push('dogShelter');
                    }
                  }
                }).catch((res) => {
                  console.log(res)
                })
              }
            })
          }else{
            this.$alert("未选择！")
          }
        }
      }
    }

    function initData(_this) {
      _this.$axios({
        url: '/biz/adoption/findTAdoptionApply',
        method: 'get',
        params: {"adoptionPublicityId":_this.$route.params.adoptionPublicityId}
      }).then((res) => {
        if (res.data.status == 200) {
          if (res.data.data!=null) {
            _this.adoptList = res.data.data
          }
        }
      }).catch((res) => {
        console.log(res)
      })
    }
</script>

<style scoped>

</style>
