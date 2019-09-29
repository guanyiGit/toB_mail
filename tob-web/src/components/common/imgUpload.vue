<template>
  <div class="file">
    <el-upload
      ref="imgupload"
      :action="uploadUrl"
      multiple
      :data="uploadData"
      show-file-list
      :limit="limit"
      :auto-upload='false'
      list-type="picture-card"
      :on-preview="successHandle"
      :on-remove="removHandlee"
      :on-exceed="maxFileHandle"
      :on-change="changeHandle">
      <div class="el-upload__text cont-icon" slot="trigger"><i class="el-icon-plus icon"></i></div>
      <div class="el-upload__text" slot="tip">
        &nbsp;&nbsp;<i class="el-icon-plus"></i>当前共选择 <b>{{fileList.length}}</b> 个文件
      </div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="item.url" :alt="item.name"
           v-if="fileList && fileList.length>0"
           v-for="(item,index) in fileList" :key="index">
    </el-dialog>
  </div>
</template>
<script>
  export default {
    name: "upload",
    data() {
      return {
        fileList: [],
        limit: 4,
        dialogVisible: false,
        uploadUrl:"/biz/tob-picture/biz/fileManager/uploadFileBatch",//上传的url
        uploadData:{
          type:'',
          Id:''
        }
      };
    },
    props: {

    },
    methods: {
      removHandlee(file, fileList) {
        // console.log(file, fileList);
      },
      successHandle(file) {
        this.dialogVisible = true;
        console.log(file)
      },
      maxFileHandle() {
        this.$alert('单次上传文件过多', '上传提示')
      },
      changeHandle(file, fileList) {
        this.fileList = fileList;
      },
      upload({type,id}){
        if(type && id && this.fileList.length>0){
          this.uploadData.type = type
          this.uploadData.id = id
          this.$refs.imgupload.submit();
        }
      }
    },
  }
</script>

<style scoped>
  .icon {
    font-size: 60px;
    vertical-align: middle;
  }
  .file{
    border: 1px dashed #aaa;
  }

</style>
