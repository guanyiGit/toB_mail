<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">

    <div>
      <p class="fs18 h28 lh28 cor0">供应商管理</p>
      <ul class="ul0004">
        <!--内容1-->
        <li class="db" style="height: 650px">
          <div class="ovh mgt20">
            <ul class="ulDiv fl ovh">
              <li class="fl mgr30">
                <router-link :to="{name:'supplierAdd'}"class="db pdl29 corED145B ios2">新增供应商</router-link>
                <!--<a href="###" class="db pdl29 corED145B ios2">新增供应商</a></li>-->
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios4">导入</a></li>
              <!--<li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios7">批量入库</a></li>-->
              <li class="fl mgr30"><a href="###" class="db pdl29 corED145B ios5">模板下载</a></li>
            </ul>
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" v-model="input" class="form-control" placeholder="供应商名称/编号">
                  <span class="input-group-btn">
									<button type="submit" @click="select" class="btn bged145b">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                </div>
              </form>
            </div>
          </div>
          <div class="mgt30">
            <table class="tab2" style="text-align: center;">
              <tbody>
              <tr>
                <td>供应商编号</td>
                <td>供应商名称</td>
                <td>供应商分类</td>
                <td>联系人</td>
                <td>联系电话</td>
                <td>创建时间</td>
                <td>操作</td>
              </tr>
              <!--<tr>-->
              <tr v-for="(item,index) in list " :key="index">
                <td>{{item.sup_id?item.sup_id:''}}</td>
                <td>{{item.sup_name?item.sup_name:''}}</td>
                <td>{{item.sc_name?item.sc_name:''}}</td>
                <td>{{item.legal_person?item.legal_person:''}}</td>
                <td>{{item.phone?item.phone:''}}</td>
                <td v-if="item.create_time">{{item.create_time}}</td>
                <td v-else></td>
                <td>
                  <router-link :to="{name:'supplierDetails',params:{'id':item.org_id}}"class="btn btn-xs btn-primary">查看</router-link>
                  <router-link :to="{name:'supplierUpd',params:{'id':item.org_id}}"class="btn btn-xs btn-info">编辑</router-link>
                  <a href="javascript:" class="btn btn-xs btn-danger" @click="del(item.sup_id)">删除</a>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <!--分页-->
          <div class="fen c-b ovh mgt30 t-c w410">
            <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
            <!--<a href="###" class="db fl fs14 cor0">1</a>
            <a href="###" class="db fl fs14 cor0 active">2</a>
            <a href="###" class="db fl fs14 cor0">3</a>
            <a href="###" class="db fl fs14 cor0">4</a>
            <a href="###" class="db fl fs14 cor0">5</a>
            <a href="###" class="db fl fs14 cor0"><img src="images/7.png" alt=""></a>
            <a href="###" class="db fl fs14 cor0">99</a>
            <a href="###" class="db fl fs14 cor0 curr"></a>
            <a href="###" class="db fl fs14 cor0"></a>-->
          </div>
          <!--分页END-->
        </li>
      </ul>
    </div>

  </div>
</template>

<script>
  import config from '../../common/config'
    export default {
        name: "supplierList"
          ,data(){
          return{
            userId:config.userInfo.userId,
            input:''
            , list:{}
            ,pageInfo:{
              pageSize:8
              ,pageNum:1
              ,input:''
            }
            /*,dataFormatPattern :'yyyy年MM月dd日',*/
          }
      },created(){
          initData(this)
      },methods:{
        select:function(){
          this.pageInfo.pageNum=1;
          initData(this)
        },chekdpage(pageNum){
          this.pageInfo.pageNum=pageNum
          initData(this)
        },del(id) {
          delSupplier(this,id)
        }
      },computed:{
          pageData(){
            return{
              curPage:this.pageInfo.pageNum,//当前页吗
              totalCount: this.pageInfo.totalCount?this.pageInfo.totalCount:0,//总条数
              pageSize: this.pageInfo.pageSize,//页面大小
            }
          }
      }
    }
    //得到供应商集合
    function initData(_this){
      console.log("用户登陆信息"+JSON.stringify(config.userInfo))
      _this.$axios({//根据登陆用户id得到供应商列表
        url:"/biz/tob-supplier/com/supplier/getSupplierList"
        ,params:{input:_this.input,'userId':_this.userId,start:_this.pageInfo.pageNum,size:_this.pageInfo.pageSize}
      }).then((ref)=>{
        console.log("**"+JSON.stringify(ref.data))
        if(ref.data.code==200){
          _this.list=ref.data.map.list;
          _this.pageInfo=ref.data.map.pageInfo
          console.log("**"+JSON.stringify(_this.list))
        }
      })
    }
    //删除供应商
    function delSupplier(_this,id){
      _this.$confirm("确认删除吗？",{
      }).then(() => {
        _this.$axios({
          url:'/biz/tob-supplier/com/supplier/delSupplier'
          ,params:{"id":id}
        }).then((ref)=>{
          console.log(JSON.stringify(ref.data))
          if(ref.data.code==200){
            _this.$message("删除成功")
            _this.pageInfo.pageNum=1
            _this.input=''
            initData(_this)
          }
        })
      })
    }
</script>

<style scoped>

</style>
