<template>
    <!--头部END-->

    <div class="ww100 pdl15 pdr15 pdb130">
      <div class="w1200 c-b mm100 mgt30">
        <div class="ovh pdb10 bor-b-d">
          <p class="fl fs18 cor0">新增供应商</p>
          <a href="###" class="db fr fs18 cor0">返回>></a>
        </div>
        <div class="mgt20 bor-d pdt30 pdb50">
          <div class="w600 c-b">
            <form action="#">
              <div class="ovh">
                <div class="fl w130 fs16 cor0 h40 lh40">供应商名称：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text" v-model="supplier.sup_name"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">地址：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.address"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">供应商分类：</div>
                <div class="mgl130">
                  <select class="db bor-d ww100 h40 lh40 pdl15 pdr15 ios0" v-model="supplier.sc_id">
                    <option value="">请选择</option>
                    <option v-for="(item,index) in category" v-bind:value="item.sc_id">{{item.sc_name}}</option>
                  </select>
                </div>
              </div>

              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">法人代表：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text" v-model="supplier.legal_person"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">联系人：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.linkman"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">联系电话：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.phone"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">电子邮件：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.email"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">社会信用代码：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.social_credit_code"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">注册资本：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.registered_assets"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">占地面积：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.floor_area"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">成立日期：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.date_establishment"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">营业额：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.business_volume"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">银行信用状况：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.bank_credit_status"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">人力资源状况：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.human_resource_situation"></div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">设备状况：</div>
                <div class="mgl130"><input class="db bor-d ww100 h40 lh40" type="text"v-model="supplier.status_equipment"></div>
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
                  <textarea class="ww100 h100 pdl10 pdr10 pdt10 pdb10 bor-d"v-model="supplier.product_serve"></textarea>
                </div>
              </div>
              <div class="ovh mgt20">
                <div class="fl w130 fs16 cor0 h40 lh40">主要协议条款：</div>
                <div class="mgl130">
                  <textarea class="ww100 h100 pdl10 pdr10 pdt10 pdb10 bor-d"v-model="supplier.articles_agreement"></textarea>
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
                <input type="button" class="w100 btn btn-primary" value="保存" @click="add">
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
  import config from  '../../common/config'
    export default {
        name: "supplierAdd"
      ,data(){
          return{
            supplier:{//供应商对象
              pid:config.userInfo.orgId,
              sup_name:''
              ,address:''
              ,sc_id:1//供应商分类id
              ,legal_person:''//法人代表
              ,linkman:''//联系人
              ,phone:''//联系电话
              ,email:''//电子邮件
              ,social_credit_code:''//社会信用代码
              ,registered_assets:0//注册资本
              ,floor_area:0//占地面积
              ,date_establishment:new Date().toLocaleDateString().replace(/\//g, "-")//成立日期
              ,business_volume:0//营业额
              ,bank_credit_status:''//银行信用状况
              ,human_resource_situation:''//人力资源状况
              ,status_equipment:''//设备状况
              ,business_scope:''//经营范围
              ,product_serve:''//主要产品及服务
              ,articles_agreement:''//主要协议条款
              ,type:1
            },category:{}//供应商分类集合

            ,categoryType:{}//经营范围
            ,orgId:config.userInfo.orgId
          }
      },created(){
        getCategory(this)
        getCategoryType(this)
      },methods:{
        add:function(){
          addSupplier(this);
        }
      }
    }
    //添加供应商
    function addSupplier(_this){
      if(_this.supplier.sup_name==null||_this.supplier.sup_name==''){
        _this.$alert("供应商名称不能为空")
        return
      }
      if(_this.supplier.sc_id==null||_this.supplier.sc_id==''){
        _this.supplier.sc_id=0;
      }
      _this.supplier.org_name=_this.supplier.sup_name//添加到组织表的组织名称（供应商名称）
      _this.supplier.create_by=config.userInfo.userId;//添加到组织表的创建人id
      console.log(_this.supplier)
      _this.$axios({
        url:'/biz/tob-supplier/com/supplier/addSupplier'
        ,params:{supplier:JSON.stringify(_this.supplier)}
      }).then((ref)=>{
        if(ref.data.code==200){
          alert("添加成功")
          _this.$router.push({path:'supplierList'})
        }
      })
    }
    //根据机构id查询供应商分类
    function getCategory(_this){
      if(_this.orgId!=null&&_this.orgId!=''){
        _this.$axios({
          url:'/biz/tob-supplier/com/category/getCategroyList'
          ,params:{"orgId":_this.orgId}
        }).then((ref)=>{
          if(ref.data.code==200){
            console.log("**"+JSON.stringify(ref.data))
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
</script>

<style scoped>

</style>
