<script lang="ts" setup>
import { ref, onMounted } from "vue";
import type { UploadProps } from 'element-plus'
import { error, successEasy } from "../../../utils/notification.js";
import { axiosInstance } from '../../../utils/request.js'
import { warning } from '../../../utils/notification.js'

//图片路径
let storeCoverPath = ref();
//图片文件
let storeCoverFile = ref();
//判断图片的格式是否合格
let passMuster = ref(0);
//服务器中图片的地址
let behindPicturePath = ref();

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
    storeCoverPath.value = URL.createObjectURL(rawFile);
    storeCoverFile.value = rawFile;
    return true;
}
//将图片发送到后端保存
let uploadPicture = () => {
    if(passMuster.value == 1 && storeCoverFile.value){
        let formData = new FormData();
        formData.append('image',storeCoverFile.value,storeCoverFile.value.name);
        axiosInstance.put('/storeInfo/updateStoreCover',formData)
        .then((response) => {
            if(response.data.code != 200){
                error('上传',response.data.message);
            }else{
                successEasy('上传','');
            }
        })
        .catch((errors) => {
            console.log(errors);    
        }); 
    }else{
        warning('图片有误，无法上传')
    }
}
//获取店铺头像
let getCoverPicture = () => {
    axiosInstance.get(`/system/getPicture/${behindPicturePath.value}`,{responseType:'blob'})
    .then((response) => {
        storeCoverPath.value = URL.createObjectURL(new Blob([response.data]));
    })
    .catch((errors) => {
        console.log(errors);    
    });
}
//获取店铺信息
let getStoreInfo = () => {
    axiosInstance.get('/storeInfo/getStoreInfo')
    .then((response) => {
        let index = (response.data.data.storeCoverPicture).lastIndexOf('\\');
        behindPicturePath.value = (response.data.data.storeCoverPicture).substring(index + 1);
        getCoverPicture();
    })
}

onMounted(() => {
    getStoreInfo();
})
</script>

<template>
    <div class="store-cover">
        <p class="store-cover-title">店铺封面：</p>
        <el-upload  class="picture-area" 
                    action=""
                    :show-file-list="false"
                    :before-upload="beforeUpload">
            <img v-if="storeCoverPath" :src="storeCoverPath" class="store-cover-picture"/>
            <el-icon v-else class="store-cover-icon"><Plus /></el-icon>
        </el-upload>
        <button class="store-cover-button" @click="uploadPicture()">更新</button>
    </div>
</template>

<style scoped>
.store-cover {
    width: 600px;
    height: 120px;
    margin-top: 50px;
}
.store-cover-title {
    width: 100px;
    height: 30px;
    font-size: 20px;
    margin-top: 40px;
    float: left;
}
.picture-area {
    float: left;
}
.store-cover-picture {
    width: 300px;
    height: 100px;
    background-color: rgb(242, 242, 242);
    border: 1px solid rgb(88, 88, 88);
    margin-left: 20px;
    margin-top: 10px;
}
.store-cover-icon {
    width: 300px;
    height: 100px;
    font-size: 30px;
    color: rgb(140, 147, 157);
    border: 1px solid rgb(88, 88, 88);
    margin-left: 20px;
    margin-top: 10px;
}
.store-cover-button {
    width: 70px;
    height: 35px;
    border-radius: 10px;
    border: none;
    font-size: 17px;
    color: white;
    background-color: orangered;
    cursor: pointer;
    margin-left: 50px;
    margin-top: 40px;
}
.store-cover-button:hover {
    background-color: rgb(255, 90, 30);
}
</style>