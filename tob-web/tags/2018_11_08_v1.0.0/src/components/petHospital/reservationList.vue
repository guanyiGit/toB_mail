<template>
  <!--内容区-->
  <div class="w980 fr pdb40">
    <div class="ovh mgt20">
      <div class="fl cor6 h40 lh40">
        <router-link :to="{name:'hospital_reservationAdd'}" class="db fl hov8 corffa472 mgr35 pc9 pdl36">预约登记</router-link>
      </div>
      <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
        <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="网点名称">
        <input type="submit" value="" class="in1 db abs z100 top0 bort right0">
      </form>
    </div>
    <div class="k3 mgt20">
      <div class="div12 db">
        <table class="table11">
          <tbody>
          <tr>
            <td>预约时间</td>
            <td>预约业务</td>
            <td>预约人</td>
            <td>电话</td>
            <td>预约方式</td>
            <td>状态</td>
            <td>操作</td>
          </tr>

          <tr v-for="(item,index) in list" :key="index">
            <td v-if="item.creationTime">{{item.creationTime | formatDate(pattern)}}</td>
            <td v-else>--</td>
            <td>{{item.bizType==0?'看病':item.bizType==1?'办证':item.bizType==2?'年检':item.bizType==1?'预约':'其他'}}</td>
            <td>{{item.appointmentName}}</td>
            <td>{{item.appointmentPhone}}</td>
            <td>
              {{item.appointmentChanne==0?'电话预约':item.appointmentChanne==1?'网上预约':item.appointmentChanne==2?'门面预约':'其他'}}
            </td>
            <td>{{item.status==0?'未赴约':'已赴约'}}</td>
            <td class="btns">
              <a href="###" class="dinb cor009cff hov8" @click.prevent="remove(item.appointmentId)">删除</a>
              <a href="###" class="dinb cor009cff hov8" @click.prevent="changedStatus(index)">变更状态</a>
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
  <!--内容区END-->
</template>

<script>

  import configJS from '../../common/config'

  let params = {
    pageNum: 1,
    pageSize: 10,
    keyWord: ''
  }


  export default {
    name: "reservationList",
    data() {
      return {
        pattern: 'yyyy年MM月dd日 HH时mm分',
        list: [],
        totalCount: 0,
      }
    },
    created() {
      initData(this)
    },
    computed: {
      //分页参数
      pageData() {
        return {
          curPage: params.pageNum,//当前页吗
          totalCount: this.totalCount,//总条数
          pageSize: params.pageSize//页面大小}
        }
      }
    },
    methods: {
      //分页
      chekdpage(currentPage) {
        params.num = currentPage//页码
        initData(this)
      },
      //变更状态
      changedStatus(index) {
        let appointmentId = this.list[index].appointmentId
        let status = this.list[index].status == 1 ? 0 : 1;
        // let appointmentId = 1
        // let status = 0
        this.$axios({
          url: '/biz/reservation/modify',
          method: 'post',
          params: {appointmentId, status}
        }).then(res => {
          if (res.data.meta.success) {
            this.$message("修改成功！")
            initData(this)
          } else {
            this.$message("修改失败！")
          }
        })
      },
      remove(appointmentId) {
        this.$axios({
          url: '/biz/reservation/delete/' + appointmentId,
          isloading: true,
          method: 'post'
        }).then(res=>{
          if(res.data.meta.success){
            this.$alert("删除成功！")
            initData(this)
          }else{
            this.$alert("删除失败！")
          }
        })
      }
    }
  }

  function initData(_this) {
    if (!params.keyWord)
      delete  params.keyWord


    params.orgId = configJS.userInfo.organizationId;
    _this.$axios({
      url: '/biz/reservation/org',
      params
    }).then(res => {
      if (res.data.meta.success) {
        _this.list = res.data.data.list;
        _this.totalCount = res.data.data.total;
      }
    })
  }
</script>

<style scoped>
  .btns {
    display: flex;
    flex-flow: column nowrap;
    justify-content: center;
  }

  .btns a:first-child {
    margin-bottom: 5px;
  }
</style>
