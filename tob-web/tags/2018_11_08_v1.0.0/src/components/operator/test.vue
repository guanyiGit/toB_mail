<template>
  <div>
    <h1>哈哈aaaa哈</h1>
    <input type="text" lable="请输入" v-model="msg">
    <input type="button" @click="tet" value="点我"/>
    <ul>
      <li v-for="(item,index) in  list" :key="index">{{item.name}} --- {{item.value}}</li>
    </ul>
    <hr>
    <img-upload :formData="datas"></img-upload>
    <button @click="xsub">点我</button>
    <hr/>
    <v-distpicker></v-distpicker>
  </div>
</template>

<script>

  import VDistpicker from 'v-distpicker'

  export default {
    name: "test",
    data() {
      return {
        msg: "顶我！！！1",
        list: [
          {name: 'name1', value: 1},
          {name: 'name2', value: 2},
          {name: 'name3', value: 3},
        ],
        datas: {
          url: '',
          uploadData: {}
        }
      }
    },
    methods: {
      tet: function () {
        var temp = this.msg
        ffff(temp)
      },
      xsub() {
        this.datas = {
          url: "/biz/fileManager/uploadFileBatch",//上传的url
          uploadData: {//上传参数
            type: 1,
            Id: 1
          },
        }
      }
    },
    components: {VDistpicker}

  }

  function ffff(e) {
    alert(e)

  }


  function test() {
    this.$axios({
      method: "post",
      url: '/biz/',
      data: {},
      headers: {
        "content-type": ""
      },
    }).then(function (res) {
      //成功的回调
    }).catch(function (err) {
      //失败的回调
    })
  }
</script>

<style scoped>

</style>
