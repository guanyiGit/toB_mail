<template>
    <!--证件办理-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="ovh div1 h50 rel">
        <p class="abs ww100 left0 bottom0"></p>
        <a style="cursor:pointer;" class="fl db fs16 cor3c3c3c w200 h50 lh50 t-c active">免疫证</a>
        <a style="cursor:pointer;" class="fl db fs16 cor3c3c3c w200 h50 lh50 t-c">犬证</a>
      </div>

      <div class="div12 db">
        <div class="ovh mgt20">
          <div class="fl cor6 h40 lh40">
            <router-link style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pc9 pdl36" :to="{name:'hospital_ImmuneAdd'}">免疫证办理</router-link>
          </div>
          <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
            <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" @dblclick="$event.target.select()" @keyup.enter="search1" v-model="params1.searchKey" placeholder="犬证号/免疫证">
            <input value="" class="in1 db abs z100 top0 bort right0" @click.prevent="search1">
          </form>
        </div>

        <div class="mgt10 k3">
          <!--<div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">违法信息</div>-->
          <div class="t-c">
            <table class="table5" width="100%" border="1">
              <tbody>
              <tr>
                <td class="cor0 h60 bgf1">免疫证号</td>
                <td class="cor0 h60 bgf1">犬只名称</td>
                <td class="cor0 h60 bgf1">犬只品种</td>
                <td class="cor0 h60 bgf1">性别</td>
                <!--<td class="cor0 h60 bgf1">年龄</td>-->
                <td class="cor0 h60 bgf1">毛色</td>
                <td class="cor0 h60 bgf1">犬主姓名</td>
                <td class="cor0 h60 bgf1">电话</td>
                <td class="cor0 h60 bgf1">办理时间</td>
                <td class="cor0 h60 bgf1">办理人</td>
                <td class="cor0 h60 bgf1">操作</td>
              </tr>
              <tr v-for="(item,index) in infos1" :key="index">
                <td class="cor3">{{item?item.dogImmune?item.dogImmune.immuneCardNum?item.dogImmune.immuneCardNum:'':'':''}}</td>
                <td class="cor3">{{item?item.dogName?item.dogName:'':''}}</td>
                <td class="cor3" v-if="item&&item.dogBreedDictionary&&item.dogBreedDictionary.dictionaryDescribe">{{item.dogBreedDictionary.dictionaryDescribe}}</td>
                <td class="cor3" v-else></td>
                <td>{{item?item.dogGender==0?"雄":"雌":''}}</td>
                <td v-if="item&&item.dogColorDictionary&&item.dogColorDictionary.dictionaryDescribe">{{item.dogColorDictionary.dictionaryDescribe}}</td>
                <td v-else></td>
                <td class="cor3">{{item?item.dogOwner?item.dogOwner.dogOwnerName:'':''}}</td>
                <td class="cor3">{{item?item.dogOwner?item.dogOwner.dogOwnerPhone:'':''}}</td>
                <!--<td>{{item.age}}</td>-->
                <td v-if="item&&item.dogImmune&&item.dogImmune.lssueTime">{{item.dogImmune.lssueTime | formatDate('yyyy-MM-dd')}}</td>
                <td v-else></td>
                <td>{{item?item.dogImmune?item.dogImmune.tTUsers?item.dogImmune.tTUsers.name:'':'':''}}</td>
                <td class="t-c">
                  <router-link href="###" class="db cor009cff hov8" :to="{name:'hospital_ImmuneDatail',params:{'seqId':item.dogImmune.seqId}}">查看详情</router-link>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
        <!--分页-->
        <page-vue :pageData="pageData1" @chekdpageCallcak="chekdpage1"></page-vue>
        <!--分页-->
      </div>
      <div class="div12 dn">
        <div class="ovh mgt20">
          <div class="fl cor6 h40 lh40">
            <router-link style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pc9 pdl36" :to="{name:'dogManager_dogadd'}">犬证办理</router-link>
          </div>
          <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
            <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" @dblclick="$event.target.select()" @keyup.enter="search" v-model="params.input" placeholder="犬主姓名/身份证号/犬证号/免疫证号/标示牌号">
            <input type="submit" value="" class="in1 db abs z100 top0 bort right0" @click.prevent="search">
          </form>
        </div>

        <div class="mgt10 k3">
          <!--<div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">违法信息</div>-->
          <div class="t-c">
            <table class="table5" width="100%" border="1">
              <tbody>
              <tr>
                <td class="cor0 h60 bgf1">犬证号</td>
                <td class="cor0 h60 bgf1">犬只名称</td>
                <td class="cor0 h60 bgf1">犬只品种</td>
                <td class="cor0 h60 bgf1">犬主姓名</td>
                <td class="cor0 h60 bgf1">电话</td>
                <td class="cor0 h60 bgf1">办理时间</td>
                <td class="cor0 h60 bgf1">办理人</td>
                <td class="cor0 h60 bgf1">办理机构</td>
                <td class="cor0 h60 bgf1">操作</td>
              </tr>
              <tr v-for="(item,index) in infos" :key="index">
                <td>{{item.dogCardNum.length>10?item.dogCardNum.substring(0,10):item.dogCardNum}}</td>
                <td>{{item.dogName}}</td>
                <td>{{item.dogVarieties}}</td>
                <td>{{item.dogOwnerName}}</td>
                <td>{{item.dogOwnerPhone}}</td>
                <td>{{item.lssueTime | formatDate}}</td>
                <td>{{item.lssuerName}}</td>
                <td>{{item.lssueOrgName}}</td>
                <td class="t-c">
                  <!--<a href="###" class="dinb cor009cff hov8 mgr14">查看详情</a>-->
                  <router-link class="dinb cor009cff hov8 mgr14" :to="{name:'hospital_ImmuneDatail',params:{'dogId':item.dogId}}">查看详情</router-link>
                  <span class="cupo dinb cor009cff hov8" @click="alert.dialogVisible = true">续费</span>

                  <!--续费 弹窗-->
                  <el-dialog
                    title="续期提示"
                    :visible.sync="alert.dialogVisible"
                    width="30%" >
                    <div class="cont">
                      <span class="h100b">{{alert.msg}}</span>
                      <el-date-picker
                        v-model="alert.selectdDate"
                        type="date"
                        :format="dateRegular"
                        placeholder="选择日期">
                      </el-date-picker>
                    </div>
                    <span slot="footer" class="dialog-footer">
                  <el-button @click="alert.dialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="renewConfirm(item.dogId)">确 定</el-button>
                </span>
                  </el-dialog>
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
    </div>
    <!--内容区END-->
  </div>
</template>

<script>
  import $$ from 'jquery'
  import  conf from '../../common/config'
    export default {
        name: "certificateDealt",
      data() {
        return {
          params: {
            num: 1,//页码
            size: 10,//页面大小
            input: '',  //keyword
          },
          params1: {
            index: 0, // SQL语句起始索引
            pageSize : 10, // 每页显示数量
            orgId:conf.userInfo.organizationId,
            searchKey: '',  //keyword
          },
          pageData:{
            curPage:1,//当前页吗
            totalCount:1,//总条数
            pageSize:1//页面大小
          },
          pageData1:{
            curPage:1,//当前页吗
            totalCount:1,//总条数
            pageSize:1//页面大小
          },
          infos:[],//信息列表
          infos1:[],//信息列表
          keyWord:'',//搜索关键字
          keyWord1:'',//搜索关键字
          alert:{//弹出框
            dialogVisible: false,//弹出框显示标示，
            msg:'续费至：',//弹窗提示内容,
            selectdDate:''
          },
          dateRegular:"yyyy年MM月dd日"
        }
      },
      created() {
        initData(this)
        initData1(this)
      },
      computed:{

      },
      mounted(){
        var $tab12= $$('.div1>a');
        $tab12.mouseover(function(){
          $$(this).addClass('active').siblings().removeClass('active');
          var i= $tab12.index(this);
          $$('.div12').eq(i).show().siblings('.div12').hide();
        });
      },
      methods:{
        chekdpage(pageNum){
          this.params.num = pageNum//页码
          initData(this)
        },
        chekdpage1(pageNum){
          this.params1.index = pageNum//页码
          initData1(this)
        },
        search(){
          initData(this)
        },
        search1(){
          initData1(this)
        },
        renewConfirm(dogId){//续费确认
          let alertMdg = "续费失败";//提示窗信息
          if(this.alert.selectdDate){
            this.$axios({//续费请求
              url:'/biz/dogCard/updateCardDate',
              params:{"dogId":dogId,'endDate':this.alert.selectdDate}
            }).then((res)=>{
              if(res.data.code='000000'){
                alertMdg="续费成功！"
              }
            }).catch(_=>{}).finally(_=>{
              this.alert.dialogVisible = false;
              this.$alert(alertMdg, '续费结果', {
                confirmButtonText: '确定',
              });
            })
          }
        }
      },
      watch:{
        keyWord(newVal,oldVal){
          this.params.input = newVal;
        },
        keyWord1(newVal,oldVal){
          this.params1.searchKey = newVal;
        },
      }
    }

  function initData(_this) {
    if(!_this.params.input || _this.params.input.trim().length==0){
      delete _this.params.input
    }
    _this.$axios({
      url: '/biz/dogCard/selectDogCardHandleList',
      params:_this.params
    }).then((res) => {
      if(res.data){
        _this.pageData.curPage= res.data.page//当前页吗
        _this.pageData.totalCount= res.data.total//总条数
        _this.pageData.pageSize= _this.params.size//页面大小
        _this.infos = res.data.rows
      }
    }).catch((res) => {
      //console.log(res)
    })
  }

  function initData1(_this) {
    if(!_this.params1.searchKey || _this.params1.searchKey.trim().length==0){
      delete _this.params1.searchKey
    }
    _this.$axios({
      url: '/biz/dogInfo/findImmuneCardList',
      params:_this.params1
    }).then((res) => {
      console.log(res)
      if(res.data){
        _this.pageData1.curPage= res.data.page//当前页吗
        _this.pageData1.totalCount= res.data.total//总条数
        _this.pageData1.pageSize= _this.params1.pageSize//页面大小
        _this.infos1 = res.data.rows
      }
    }).catch((res) => {
      //console.log(res)
    })
  }
</script>

<style scoped>
  [type="submit"]{
    cursor: pointer;
  }
  .h100b{
    height: 100%;
    margin-right: 10px;
  }
  .cupo{
    cursor: pointer;
  }
  .cont{
    display: flex;
    align-items: center;
    justify-content: center;
  }
  td:first-child{padding-left: 10px}
</style>
