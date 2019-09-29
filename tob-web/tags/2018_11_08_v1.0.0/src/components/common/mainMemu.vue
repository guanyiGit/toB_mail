<template>
  <!--侧边栏-->
  <router-link class="w200 fl bor6b4a22 t-c" tag="div" :to="{name:mumeTitile.link}">
    <!--<div class="w200 fl bor6b4a22 t-c">-->
    <div class="tit pdt30 pdb40 bg6b4a22">
      <img :src="mumeTitile.imgUrl" alt="" class="db c-b">
      <p class="fs30 corw fsd mgt14">{{mumeTitile.resourceName}}</p>
      <p class="fs16 corw ">管理平台</p>
    </div>

    <div class="ulnav mgb30">
      <li v-for="(item,index) in memuList" :key="index" v-if="item.level && item.level==2 && item.seq>1"
          :class="{active:index==activeIndex}">
        <!--<a router-link :to="{'name':item.link}" class="cor3c3c3c h52 lh52" @click.prevent="activeIndex=index">{{item.resourceName}}<span-->
        <router-link :to="{name:item.link}" class="cor3c3c3c h52 lh52" @click.prevent="activeIndex=index">
          {{item.resourceName}}<span
          class="dn">→</span></router-link>
      </li>
    </div>
    <!--</div>-->
  </router-link>
  <!--侧边栏END-->
</template>

<script>
  import configJS from '@/common/config'

  export default {
    name: "mainMemu",
    data() {
      return {
        memuList: configJS.resource.memu,
        activeIndex: 0,//激活的菜单下标
      }
    },
    created() {
      //判断用户角色信息
      let roleIds = configJS.userInfo.tRoles.map(e => e.roleId);
      let toPath = this.$route.path;
      if (roleIds && roleIds.length == 1 && toPath) {
        let roleId = roleIds[0];
        if (roleId == 1 && toPath.indexOf("/dogManager/") < 0) {//犬只办
          this.$router.replace({'name': 'dogManager_index'})
        } else if (roleId == 2 && toPath.indexOf("/hospice/") < 0) {//收容所
          this.$router.replace({'name': 'hospice_index'})
        }else if (roleId == 3 ) {//犬主
          this.$router.replace({'name': 'index'})
        }else if (roleId == 4 && toPath.indexOf("/hospital/") < 0) {//宠物医院
          this.$router.replace({'name': 'hospital_index'})
        }else if (roleId == 5 && toPath.indexOf("/operator/") < 0) {//运营方
          this.$router.replace({'name': 'operator_index'})
        }
      }
    },
    computed: {
      mumeTitile() {//菜单titile
        let mumeTitile = {
          resourceName: '菜单',
          link: 'index'
        }
        let imgUrl = '';
        this.memuList.map(item => {
          //主菜单名称
          if (item && item.seq == 0 && item.level == 1) {
            mumeTitile = item;
          }
          //背景图
          if (item && item.seq == 0 && item.level == 1) {
            imgUrl = item.description;
          }
        })
        mumeTitile.imgUrl = imgUrl;
        return mumeTitile;
      }
    },
    methods: {}
  }
</script>
<style scoped>
  .ulnav a:hover {
    cursor: pointer;
  }

  .bor6b4a22 {
    cursor: pointer;
  }

  li:last-child {
    border-bottom: dashed 1px #9F8A72;
  }

</style>
