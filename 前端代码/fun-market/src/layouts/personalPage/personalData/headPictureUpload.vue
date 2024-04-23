<script lang="ts" setup>
import type { UploadProps } from 'element-plus'
import { error, successEasy, warning } from '../../../utils/notification'
import { customerInfo } from '../../../utils/pojo.js'
import { axiosInstance } from '../../../utils/request.js'
import { ref } from 'vue';

/* 
    头像上传过程：
        1、用户选择文件后，检察文件的类型和大小。
        2、文件符合要求后，以二进制数据的形式保存在浏览器的内存中。
        3、点击“保存”按钮后，将二进制数据发送到后端服务器。
 */

let picturePath = ref(customerInfo.portraitPath);
let passMuster = ref(0);
let pictureFile = ref();
//上传头像之前，检察文件的类型和大小
const beforeUpload: UploadProps['beforeUpload'] = (rawFile) => {
    if(rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/jpg' && rawFile.type !== 'image/png') {
        warning('文件类型有误');
        return false;
    }else if(rawFile.size / 1024 / 1024 > 5) {
        warning('头像大小超过5M');
        return false;
    }
    passMuster.value = 1;
    picturePath.value = URL.createObjectURL(rawFile);
    pictureFile.value = rawFile;
    return true;
}
//将图片发送到后端保存
let uploadPicture = () => {
    if(passMuster.value == 1 && pictureFile.value){
        let formData = new FormData();
        formData.append('image',pictureFile.value,pictureFile.value.name);
        axiosInstance.post('/customerInfo/uploadPicture',formData)
        .then((response) => {
            if(response.data.code != 200){
                error('上传',response.data.message);
            }else{
                successEasy('上传','');
                getHeadPicture();
            }
        })
        .catch((errors) => {
            console.log(errors);    
        }); 
    }else{
        warning('图片有误，无法上传')
    }
}
//获取用户头像
let getHeadPicture = () => {
    axiosInstance.get('/customerInfo/getHeadPicture',{responseType:'blob'})
    .then((response) => {
        customerInfo.portraitPath = URL.createObjectURL(new Blob([response.data]));
    })
    .catch((errors) => {
        console.log(errors);    
    });
}

</script>

<template>
    <div id="third-area">
        <div id="upload-area">
            <el-upload  action="" 
                        v-bind:show-file-list="false"
                        v-bind:before-upload="beforeUpload">
                <el-button class="upload-button">上传头像</el-button>
                <p id="upload-tip">支持大小不超过 5M 的 jpg、jpeg、png 图片</p>
            </el-upload>
        </div>
        <div id="show-picture">
            <img v-bind:src="picturePath">
        </div>
        <div id="picture-preview">
            <p id="preview-title">效果预览</p>
            <p id="preview-description">你上传的图片会自动生成以下尺寸, 请注意是否清晰。</p>
            <img v-bind:src="picturePath" id="img-preview1">
            <img v-bind:src="picturePath" id="img-preview2">
        </div>
        <button id="picture-submit" @click="uploadPicture()">保存</button>
    </div>
</template>

<style scoped>
#third-area {
    width: 830px;
    height: 480px;
    margin-left: 20px;
    margin-top: 20px;
    padding-top: 10px;
    background-color: white;
    border-radius: 10px;
}
#upload-area {
    width: 500px;
    height: 50px;
    margin-left: 70px;
    margin-top: 20px;
}
.upload-button {
    width: 100px;
    height: 35px;
    background-color: rgb(250, 250, 250);
    color: black;
    border: 1px rgb(198, 194, 194) solid;
}
#upload-button:hover {
    background-color: rgb(255, 255, 255);
}
#upload-tip {
    font-size: 14px;
    margin-left: 20px;
}
#show-picture {
    width: 400px;
    height: 300px;
    background-color: rgb(238, 232, 232);
    margin-left: 70px;
    margin-top: 10px;
    float: left;
}
#show-picture img {
    width: 300px;
    height: 225px;
    margin-left: 50px;
    margin-top: 35px;
}
#picture-preview {
    width: 250px;
    height: 300px;
    background-color: white;
    margin-left: 500px;
    margin-top: 10px;
    border-left: 1px rgb(226, 211, 211) solid;
}
#picture-submit {
    width: 80px;
    height: 35px;
    margin-left: 200px;
    margin-top: 40px;
    font-size: 16px;
    cursor: pointer;
    border: none;
    border-radius: 5px;
    color: white;
    background-color: rgb(248, 83, 37);
}
#picture-submit:hover {
    background-color: rgb(254, 103, 61);
}
#preview-title {
    font-size: 18px;
    margin-left: 28px;
}
#preview-description {
    font-size: 14px;
    margin-top: 5px;
    margin-left: 28px;
}
#img-preview1 {
    width: 110px;
    height: 110px;
    margin-top: 20px;
    margin-left: 28px;
}
#img-preview2 {
    width: 80px;
    height: 80px;
    margin-top: 20px;
    margin-left: 28px;
}
</style>