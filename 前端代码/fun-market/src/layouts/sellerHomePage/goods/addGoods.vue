<script lang="ts" setup>
import { useRouter } from 'vue-router'
import type { UploadProps } from 'element-plus'
import { reactive, ref } from 'vue';
import { error, success, successEasy, warning } from "../../../utils/notification.js";
import { axiosInstance } from '../../../utils/request.js'

let router = useRouter()

let goods = reactive({
    goodsId : '',
    goodsName : '',
    goodsCoverPath : '',
    goodsType : '',
    goodsInventory : '',
    goodsSalesVolume : '',
})

let goodsTypes = ['男装','运动','数码','女装','内衣','奢品','鞋类','箱包','百货','美妆','洗护','母婴','宠物','家装','珠宝','食品','生鲜','酒类','医药','保健','教育'];

let toGoods = () => {
    router.push('/sellerHome/goods');
}

//图片文件
let goodsCoverFile = ref();
//判断图片的格式是否合格
let passMuster = ref(0);

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
    goods.goodsCoverPath = URL.createObjectURL(rawFile);
    goodsCoverFile.value = rawFile;
    return true;
}
//将图片发送到后端保存
let uploadPicture = () => {
    if(passMuster.value == 1 && goodsCoverFile.value){
        let formData = new FormData();
        formData.append('image',goodsCoverFile.value,goodsCoverFile.value.name);
        axiosInstance.put('/goods/updateGoodsPicture',formData)
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
//提交信息
let submit = () => {
    axiosInstance.post('/goods/addGoods',goods)
    .then((response) => {
        if(response.data.code == 200){
            goods.goodsId = response.data.data.goodsId;
            uploadPicture();
            success('添加','商品信息页',toGoods())
        }
    })
}
</script>

<template>
    <div class="add-goods-area">
        <div class="page-top">
            <p class="page-top-content">新增商品信息</p>
            <button class="page-top-button" @click="toGoods()">返回</button>
        </div>
        <div class="goods-name">
            <p class="goods-title">商品名称：</p>
            <el-input class="goods-input-name" v-model="goods.goodsName" size="large" />
        </div>
        <div class="goods-type">
            <p class="goods-title">商品类别：</p>
            <el-select v-model="goods.goodsType" placeholder="请选择" class="goods-select" size="large">
                <el-option class="goods-type-option" v-for="goodsType in goodsTypes" v-bind:key="goodsType" v-bind:label="goodsType" v-bind:value="goodsType" />
            </el-select>
        </div>
        <div class="goods-inventory">
            <p class="goods-title">商品库存：</p>
            <el-input class="goods-input-inventory" v-model="goods.goodsInventory" size="large" />
        </div>
        <div class="goods-cover">
            <p class="goods-title">商品封面：</p>
            <el-upload  class="picture-area" 
                    action=""
                    :show-file-list="false"
                    :before-upload="beforeUpload">
                <img v-if="goods.goodsCoverPath" :src="goods.goodsCoverPath" class="goods-head-picture"/>
                <el-icon v-else class="goods-head-icon"><Plus /></el-icon>
            </el-upload>
        </div>
        <div class="goods-button">
            <button class="button" @click="submit()">提交</button>
        </div>
    </div>
</template>

<style scoped>
.add-goods-area {
    width: 1000px;
    height: 580px;
    background-color: white;
    margin-top: 20px;
    border-radius: 15px;
    padding-top: 30px;
    padding-left: 50px;
}
.page-top {
    width: 950px;
    height: 40px;
}
.page-top-content {
    width: 200px;
    height: 40px;
    font-size: 28px;
    font-weight: bolder;
    float: left;
}
.page-top-button {
    width: 80px;
    height: 30px;
    font-size: 17px;
    background-color: rgb(1, 154, 249);
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 5px;
    float: right;
}
.page-top-button:hover {
    background-color: rgb(36, 171, 255);
}
.goods-name {
    width: 600px;
    height: 50px;
    margin-top: 50px;
    margin-left: 50px;
}
.goods-title {
    width: 100px;
    height: 35px;
    font-size: 18px;
    margin-top: 5px;
    float: left;
}
.goods-input-name {
    width: 400px;
    font-size: 16px;
    margin-left: 20px;
    float: left;
}
.goods-select {
    width: 150px;
    margin-left: 20px;
    float: left;
}
.goods-type, .goods-inventory, .goods-cover{
    width: 600px;
    height: 50px;
    margin-top: 10px;
    margin-left: 50px;
}
.goods-type-option {
    text-align: center;
}
.goods-input-inventory {
    width: 150px;
    font-size: 16px;
    margin-left: 20px;
    float: left;
}
.picture-area {
    float: left;
}
.goods-head-picture {
    width: 120px;
    height: 120px;
    background-color: rgb(242, 242, 242);
    border: 1px solid rgb(88, 88, 88);
    margin-left: 20px;
}
.goods-head-icon {
    width: 120px;
    height: 120px;
    font-size: 30px;
    color: rgb(140, 147, 157);
    border: 1px solid rgb(88, 88, 88);
    margin-left: 20px;
}
.goods-button {
    width: 600px;
    height: 50px;
    margin-top: 120px;
    margin-left: 50px;
}
.button {
    width: 200px;
    height: 40px;
    font-size: 18px;
    border: none;
    border-radius: 5px;
    color: white;
    background-color: orangered;
    margin-left: 300px;
    cursor: pointer;
}
.button:hover {
    background-color: rgb(255, 100, 44);
}
</style>