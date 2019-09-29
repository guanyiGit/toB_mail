<template>
  <!--内容区-->
  <div class="w980 fr">

    <!--headler-->
    <div class="ovh">
      <div class="fl cor6 h40 lh40">
        <router-link :to="{name:'dogManager_ImmuneAdd'}" class="db fl hov8 corffa472 mgr35 pc9 pdl36">免疫登记</router-link>
      </div>
      <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
        <input type="text" @keyup.enter="search" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" @dblclick="$event.target.select()" v-model="params.input" placeholder="免疫证号">
        <input type="text" value="" class="seachClick in1 db abs z100 top0 bort right0" @click="search" >
      </form>
    </div>
    <!--headler end-->

    <!--table -->
    <div class="mgt10 k3">
      <!--<div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">违法信息</div>-->
      <div class="t-c">
        <table class="table11 t-c" width="100%">
          <tbody>
          <tr>
            <td>免疫时间</td>
            <td>免疫项目</td>
            <td>疫苗厂家及批号</td>
            <td>接种兽医</td>
            <td>犬只品种</td>
            <td>免疫证号</td>
            <td>犬主姓名</td>
            <td>电话</td>
            <td>操作</td>
          </tr>

          <tr v-for="(item,index) in mmuneList" :key="index">
            <td>{{item.fact_injection_date|formatDate(dataFormatPattern)}}</td>
            <td>{{item.vaccine_name}}</td>
            <td>{{item.vaccine_producer}}</td>
            <td>{{item.name}}</td>
            <td>{{item.dictionary_describe1}}</td>
            <td>{{item.immune_card_num}}</td>
            <td>{{item.dog_owner_name}}</td>
            <td>{{item.dog_owner_phone}}</td>
            <td>
              <router-link class="dinb cor009cff hov8" :to="{name:'dogManager_ImmuneDatail',params:{'VIId':item.vaccine_injection_id}}">查看详情</router-link>
            </td>
          </tr>

          </tbody>
        </table>
      </div>
    </div>
    <!--table end-->

    <!--分页-->
    <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
    <!--分页-->

  </div>
  <!--内容区END-->
</template>

<script>

  export default {
    name: "operatorMsgAuditList",
    data(){
      return {
        mmuneList:[],//免疫登记列表
        page:[],//分页对象
        params:{//请求参数
          num: 1,
          size:6,
          input:''
        },dataFormatPattern :'yyyy/MM/dd',

      }
    },
    created() {
      initData(this)
    },
    mounted(){
    },
    watch:{
    },
    methods:{
      search(){//搜索
        this.params.num = 1
        this.params.size = 6
        initData(this)
      },
      chekdpage(pageNum){
        this.params.num = pageNum
        initData(this)
      },
      selectAll(){//全选
        /*this.dogList.map(item=>{

        })*/
      }
    },
    computed:{
      pageData(){//分页参数
        let _this = this;
        return {
          curPage:_this.page.pageNum,//当前页吗
          totalCount:_this.page.totalCount,//总条数
          pageSize:_this.page.pageSize//页面大小
        }
      }
    }

  }

  function initData(_this) {
    /*if(!_this.params.input || _this.params.input.trim().length==0){
      delete _this.params.input//判断查询参数
    }*/
    _this.$axios({
      method: 'post',
      url: '/biz/dogInfo/tVaccineList',
      params: _this.params
    }).then((res) => {
      if (res.data.pageInfo.totalCount>0) {
        /*res.data.result.dogInfos.map(item=>{
          item.checkd = false//添加选中标示 默认未选中
        })*/
        _this.mmuneList = res.data.list;
        _this.page = res.data.pageInfo;

        //分页数据
        // this.pageData.curPage = _this.page.pageNum;//当前页吗
        // this.pageData.totalCount = _this.page.totalCount;//总条数
        // this.pageData.pageSize = _this.page.pageSize;//页面大小
      }else {
        _this.mmuneList = [];
      }
      console.log("+++++++++++++"+_this.mmuneList)
    }).catch((err) => {
      console.log(err);
    })
  }
</script>

<style scoped>
  .seachClick:hover{
    cursor: pointer;
  }

</style>
