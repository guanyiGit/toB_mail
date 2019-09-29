<template>
  <div class="file">
    <el-upload
      class="avatar-uploader"
      ref="imgupload"
      :action="uploadUrl"
      :data="uploadData"
      :show-file-list="false"
      :auto-upload='false'
      :on-change="changeHandle">
      <img v-if="imageUrl" :src="imageUrl" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
  </div>
</template>

<script>
    export default {
        name: "imgUpl",
      data() {
        return {
          imageUrl: null,
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
        changeHandle(file) {
          this.imageUrl = URL.createObjectURL(file.raw);
        },
        upload({type,id}){
          if(type && id && this.imageUrl){
            this.uploadData.type = type
            this.uploadData.Id = id
            this.$refs.imgupload.submit();
          }
        }
      },
    }
</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .file{
    border: 1px dashed #aaa;
  }
</style>
