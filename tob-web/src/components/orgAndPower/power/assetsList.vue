<template>
  <div class="wrap1 ww100 pdt30 pdl20 pdr20 pdb30">
    <div >
      <p class="fs18 h28 lh28 cor0">资产清单</p>
      <!--<ul class="ul003 mgt20">
        <li class="active" @click.prevent="checkTab = 1"><a href="###">组织管理</a></li>
        <li @click.prevent="checkTab = 2"><a href="###">用户管理</a></li>
        <li @click.prevent="checkTab = 3"><a href="###">角色管理</a></li>
      </ul>-->
      <ul>
        <li>
          <div class="ovh mgt20">
            <!--<div class="fl mgr30" v-if="isplatformAdmin">
              <select class="form-control ios0" @change="orgChange" v-model="orgParams.orgId">
                <option value="0" selected="selected">全部组织</option>
                <option v-for="(item,index) in orgList" :value="item.id">{{ item.orgName }}</option>
              </select>
            </div>
             <div class="fl mgr30" v-if="!isplatformAdmin">
               <select class="form-control ios0" @change="orgChange" v-model="orgParams.orgId">
                 <option v-for="(item,index) in orgList" :value="item.id">{{ item.orgName }}</option>
               </select>
             </div>-->
            <div class="fl mgr30">
              <select class="form-control ios0">
                <option value="0" selected="selected">全部组织</option>
              </select>
            </div>
            <!--<div class="fl mgr30">
              <select class="form-control ios0">
                <option>666</option>
              </select>
            </div>-->
            <div class="fr">
              <form action="" class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="用户名称/编号/手机号" v-model="userParams.query">
                  <span class="input-group-btn">
									<button type="submit" class="btn bged145b">
										<i class="glyphicon glyphicon-search corw"></i>
									</button>
								</span>
                </div>
              </form>
            </div>
          </div>
          <ul class="ulDiv ovh mgt30">
            <li class="fl mgr30">
              <!--<router-link :to="{name:'userAdd'}" class="db pdl29 corED145B ios2">新增用户</router-link>-->
            </li>
          </ul>
          <div class="mgt30">
            <table class="tab2">
              <tbody>
              <tr>
                <td>资产名称</td>
                <td>型号（与发票一致）</td>
                <td>单位</td>
                <td>数量</td>
                <td>原值单价（剔除税金）</td>
                <td>原值总价（剔除税金）</td>
                <td>资产使用部门（末级部门）</td>
                <td>末级部门编号</td>
                <td>技术编码</td>
                <!--<td>操作</td>-->
              </tr>
              <tr v-for="(item,index) in assetsList">
                <td>{{ item.assets_name }}</td>
                <td>{{ item.assets_model }}</td>
                <td>{{ item.unit }}</td>
                <td>{{ item.amount }}</td>
                <td>￥{{ item.price }}</td>
                <td>￥{{ item.sum_price}}</td>
                <td>{{ item.org_name }}</td>
                <td>{{ item.org_no }}</td>
                <td>{{ item.t_code}}</td>
                <!--<td>{{ item.status == 0?'启用':'禁用' }}</td>
                <td>
                  &lt;!&ndash;<a href="#" class="db btn btn-xs btn-primary">查看</a>&ndash;&gt;
                  &lt;!&ndash;<a href="#" class="db btn btn-xs mgt10 btn-success">编辑</a>&ndash;&gt;
                  <a href="#" class="db btn btn-xs  btn-info" @click.prevent="forbiddenUser(item.userId)"
                     v-if="item.status == 0">禁用</a>
                  <a href="#" class="db btn btn-xs  btn-info" @click.prevent="enableUser(item.userId)"
                     v-if="item.status == 1">启用</a>
                  <a href="#" class="db btn btn-xs btn-danger" @click.prevent="deleteUser(item.userId)">删除</a>
                </td>-->
              </tr>
              </tbody>
            </table>
          </div>
        </li>
      </ul>
    </div>
  </div>

</template>

<script>
  import config from '../../../common/config'
    export default {
        name: "assetsList"
      ,data(){
          return{
            userParams:{
              query:''
            },assetsList:''//资产清单集合
            ,orgId:config.userInfo.orgId//机构id
          }
      },created(){
        getAssetsList(this)
      }
    }
    //得到资产清单集合
    function getAssetsList(_this){
      console.log("用户登陆信息"+JSON.stringify(config.userInfo))
      _this.$axios({
        url:'/biz/tob-order/biz/order/getAssetsList'
        ,params:{'orgId':_this.orgId}
      }).then((ref)=>{
        if(ref.data.code==200){
          _this.assetsList=ref.data.obj;
          console.log("资产清单"+JSON.stringify(_this.assetsList))
        }
      })
    }
</script>

<style scoped>

</style>
