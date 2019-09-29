<template>
  <div class="wrap ww100 pdt100" style="padding-top: 0px">
    <!--头部-->
    <div class="ww100 header">
      <div class="bgf2 pdl15 pdr15">
        <div class="w1200 c-b mm100 cor9 h40 lh40">欢迎进入xx商城后台管理系统！      客服热线：400-888-888</div>
      </div>
      <div class="bgdf pdl15 pdr15">
        <div class="w1200 c-b mm100 ovh pdt15 pdb15">
          <ul class="ul001 fr">
            <li class="fl">
              <a href="###" class="db cor3">
                <span><img src="images/2.png" alt=""></span>
                <span>张三(平台管理员）</span>
              </a>
            </li>
            <li class="fl">
              <a href="###" class="db cor3">我的消息</a>
            </li>
            <li class="fl">
              <a href="###" class="db cor3">退出登录</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!--头部END-->

    <div class="ww100 pdl15 pdr15 pdb130">
      <div class="w1200 c-b mm100 mgt30">
        <div class="ovh pdb10 bor-b-d">
          <p class="fl fs18 cor0">查看供应商</p>
          <a href="###" class="db fr fs18 cor0">返回>></a>
        </div>
        <div class="mgt20 bor-d pdt30 pdb50">
          <div class="w600 c-b">
            <form action="#">
              <div class="ovh">
                <div class="fl w130 fs16 cor0 h40 lh40">供应商名称：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.sup_name" v-model="supplier.sup_name">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">地址：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.address" v-model="supplier.address">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">供应商分类：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.sc_name" v-model="supplier.sc_name">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>

              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">法人代表：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.legal_person" v-model="supplier.legal_person">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">联系人：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.linkman" v-model="supplier.linkman">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">联系电话：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.phone" v-model="supplier.phone">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">电子邮件：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.email" v-model="supplier.email">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">社会信用代码：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.social_credit_code" v-model="supplier.social_credit_code">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">注册资本：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.registered_assets" v-model="supplier.registered_assets">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">占地面积：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.floor_area" v-model="supplier.floor_area">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">成立日期：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.date_establishment" v-model="supplier.date_establishment">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">营业额：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.business_volume" v-model="supplier.business_volume">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">银行信用状况：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.bank_credit_status" v-model="supplier.bank_credit_status">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">人力资源状况：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.human_resource_situation" v-model="supplier.human_resource_situation">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">设备状况：</div>
                <div class="mgl130">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-if="supplier&&supplier.status_equipment" v-model="supplier.status_equipment">
                  <input class="db bor-d ww100 h40 lh40" type="text" v-else>
                </div>
                </div>
              <div class="ovh">
                <div class="fl w130 fs16 cor0 h40 lh40 pdt13">经营范围</div>
                <div class="mgl130 pdt10">
                  <p class="fl mgt10 w140" v-for="(item,index) in categoryType">
                    <label class="lab2"><input type="checkbox" name="inpt"><i></i></label>
                  </p>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">主要产品及服务：</div>
                <div class="mgl130">
                  <textarea class="ww100 h100 pdl10 pdr10 pdt10 pdb10 bor-d">{{supplier&&supplier.product_serve?supplier.product_serve:''}}</textarea>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">主要协议条款：</div>
                <div class="mgl130">
                  <textarea class="ww100 h100 pdl10 pdr10 pdt10 pdb10 bor-d">{{supplier&&supplier.articles_agreement?supplier.articles_agreement:''}}</textarea>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">证件执照</div>
                <div class="mgl130">
                  <ul class="ovh ul004">
                    <li class="fl"><a href="###" class="db"><img src="images/16-1.jpg" alt=""></a></li>
                    <li class="fl"><a href="###" class="db"><img src="images/16-2.jpg" alt=""></a></li>
                    <li class="fl"><a href="###" class="db"><img src="images/16-3.jpg" alt=""></a></li>
                    <li class="fl"><a href="###" class="db"><img src="images/16-4.jpg" alt=""></a></li>
                  </ul>
                </div>
              </div>
              <div class="mgt40 t-c">
                <!--<input type="button" class="w100 btn btn-primary" value="保存" @click="add">-->
                <a href="javasscript:"class="w100 pdr30 btn btn-danger" @click="$router.back(-1)">取消</a>
                <!--<input type="button" class="w100 pdr30 btn btn-danger" value="取消">-->
              </div>
            </form>
          </div>
        </div>

      </div>
    </div>

  </div>
</template>

<script>
  import $ from 'jquery'
  import config from  '../../common/config'
  export default {
    name: "supplierDetails"
    ,data(){
      return{
        supplier:{//供应商对象
          /*sup_name:''
          ,address:''
          ,sc_id:1//供应商分类id
          ,legal_person:''//法人代表
          ,linkman:''//联系人
          ,phone:''//联系电话
          ,email:''//电子邮件
          ,social_credit_code:''//社会信用代码
          ,registered_assets:''//注册资本
          ,floor_area:''//占地面积
          ,date_establishment:new Date()//成立日期
          ,business_volume:''//营业额
          ,bank_credit_status:''//银行信用状况
          ,human_resource_situation:''//人力资源状况
          ,status_equipment:''//设备状况
          ,business_scope:''//经营范围
          ,product_serve:''//主要产品及服务
          ,articles_agreement:''//主要协议条款
          ,org_id:config.userInfo.orgId*/
        },category:{}//供应商分类集合

        ,categoryType:{}//经营范围
        ,orgId:config.userInfo.orgId
      }
    },created(){
      getCategory(this)
      getCategoryType(this)
      initData(this)
    },methods:{

    }
  }

  //根据机构id查询供应商分类
  function getCategory(_this){
    if(_this.orgId!=null&&_this.orgId!=''){
      _this.$axios({
        url:'/biz/tob-supplier/com/category/getCategroyList'
        ,params:{"orgId":_this.orgId}
      }).then((ref)=>{
        if(ref.data.code==200){
          console.log("供应商分类"+JSON.stringify(ref.data))
          _this.category=ref.data.list;
        }
      })
    }
  }
  //根据机构id查询供应商分类
  function getCategoryType(_this){
    if(_this.orgId!=null&&_this.orgId!=''){
      _this.$axios({
        url:'/biz/tob-supplier/com/category/getCategoryType'
        ,params:{"orgId":_this.orgId}
      }).then((ref)=>{
        if(ref.data.code==200){
          console.log("**"+JSON.stringify(ref.data))
          _this.categoryType=ref.data.list;
        }
      })
    }
  }
  function initData(_this){

    let aa=_this.$route.params.id;
    _this.$axios({
      url:'/biz/tob-supplier/com/supplier/getSupplierById'
      ,params:{"id":aa}
    }).then((ref)=>{
      if(ref.data.code==200){
       /* var timestamp4 = new Date(1472048779952);//直接用 new Date(时间戳) 格式转化获得当前时间
      console.log(timestamp4);
      console.log(timestamp4.toLocaleDateString().replace(/\//g, "-"));*/

        _this.supplier=ref.data.map;
        console.log("供应商详情"+JSON.stringify(_this.supplier));
        if(_this.supplier.date_establishment){
          _this.supplier.date_establishment=new Date(_this.supplier.date_establishment).toLocaleDateString().replace(/\//g, "-")
        }
      }
    })
  }
</script>

<style scoped>

</style>
