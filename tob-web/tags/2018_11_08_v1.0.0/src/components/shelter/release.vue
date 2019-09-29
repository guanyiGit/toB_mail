<template>
    <!--信息发布-发布-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="mbx">
        <ul class="ul11 clearfix">
          <li>
            <a style="cursor:pointer;" @click="fromReset">信息管理></a>
          </li>
          <li>信息发布</li>
        </ul>
      </div>
      <form action="#">

        <div class="mgt15 k3">
          <div class="fs16 cor0 fb pdl30 bgf1 h60 lh60">信息发布</div>
          <div class="pdb120 ovh">
            <div class="ovh pdt10 pdl30 pdr50">
              <div class="mgt10 ww100">
                <span class="db w60 fl h40 lh40 cor0">标题</span>
                <div class="mgl60">
                  <input id="title" type="text" class="ww100 h40 lh40 pdl20 borf1 ras4">
                </div>
              </div>
              <div class="mgt10 ww100">
                <span class="db w60 fl h40 lh40 cor0">类型</span>
                <div class="mgl60">
                  <select id="infoType" class="ww100 h40 lh40 pdl20 borf1 ras4 pc4">
                    <option>全部</option>
                    <option value=0>政策法规</option>
                    <option value=1>动态资讯</option>
                    <option value=2>养犬宣传</option>
                    <option value=3>服务指南</option>
                  </select>
                </div>
              </div>
              <div class="mgt10 ww100">
                <span class="db w60 fl h40 lh40 cor0">内容</span>
                <div class="mgl60">
                  <div style="width: 100%;">
                    <!--<script id="editor" type="text/plain" style="width:100%;height:300px;"></script>-->
                    <div id="editor" style="width:100%;height:400px;z-index:-999"></div>
                    </div>
                    </div>
                    </div>
                    </div>
                    </div>
                    </div>
                    </form>
      <div class="t-c mgt40 mgb50">
        <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c corw bgfbad82 ras4 hov8 bort" @click="save">提交</a>
        <a style="cursor:pointer;" class="dinb w140 h40 lh40 t-c cor01 bgf1 ras4 hov8" @click="fromReset">取消</a>
      </div>
                    </div>
                    <!--内容区END-->
  </div>
</template>

<script>
  import $ from 'jquery'
  import E from 'wangeditor';
  let parms={
    information:{
      operatorId:1,//发布人id
      informationType:null,//类型
      infoTitile:null,//标题
      infoContent:null//内容
    },
    picParm:{
      Id:null,
      type: null
    },
    list:[],
    imgsrcs:[]
  }
  let editor=null;
  export default {
    name: "release",
    created(){

    },
    methods: {
          save:function() {
            $("#editor img").each(function() {
              parms.imgsrcs.push($(this).attr("src"));
            });
            //查图片id
            this.$axios({
              method:'post',
              headers:{
                "content-type": "application/json;charset=utf-8",
              },
              url: '/biz/dogCard/selectIdsByurl',
              data:JSON.stringify(parms.imgsrcs)
            }).then((res) => {
              if (res.data.code == "000000" && res.data.result!=null && JSON.stringify(res.data.result)!="[]") {
                parms.list=res.data.result;
                inputData(this);
              }
            }).catch(_ => { })
        },
      fromReset:function () {//取消
        this.$router.back(-1);
      }
    },
    mounted() {
      editor = new E('#editor');
      editor.customConfig.uploadImgServer = '/biz/fileManager/uploadSinglePic';
      editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024;
      editor.customConfig.uploadImgMaxLength = 5;
      editor.customConfig.uploadImgParams = {
        // 如果版本 <=v3.1.0 ，属性值会自动进行 encode ，此处无需 encode
        // 如果版本 >=v3.1.1 ，属性值不会自动 encode ，如有需要自己手动 encode
        type: parms.picParm.Id,
        Id: parms.picParm.type
      }
      editor.customConfig.uploadImgHooks = {
        customInsert: function (insertImg, result, editor) {
          // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
          // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

          // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
          var url =result.url;
          console.log(url)
          insertImg(url);

          // result 必须是一个 JSON 格式字符串！！！否则报错
        }
      }
      editor.create();
    }
    }

//录入数据
  function inputData(_this) {
    parms.information.informationType=parseInt($("#infoType").val());
    parms.information.infoTitile=$("#title").val();
    parms.information.infoContent=editor.txt.html();
    _this.$axios({
      method:'post',
      url: '/biz/information/add',
      //dataType:"json",
      params:{information:JSON.stringify(parms.information),imageIds:JSON.stringify(parms.list)}
    }).then((res) => {
      console.log('111')
      console.log(res)
      if (res.data.status == 200) {
        this.$alert("发布信息成功！")
        _this.$router.back(-1);
      }
    }).catch(_ => { })
  }

</script>

<style scoped>

</style>
