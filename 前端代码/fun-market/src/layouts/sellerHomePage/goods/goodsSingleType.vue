<script setup>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router'
import { defineSignId } from '../../../store/store.js'
import { successEasy } from '../../../utils/notification.js';
import { axiosInstance } from '../../../utils/request.js';

let router = useRouter();
let defineSignIds = defineSignId();

let goodsId = ref();
let itemList = ref();

let toGoods = () => {
    router.push('/sellerHome/goods');
}
let toAddGoodsSingleType = () => {
    router.push('/sellerHome/addGoodsSingleType');
}
let toUpdateGoodsSingleType = (goodsSingleTypesId) => {
    defineSignIds.setGoodsSingleTypesId(goodsSingleTypesId);
    router.push('/sellerHome/updateGoodsSingleType');
}
//删除商品种类信息
let deleteGoods = (goodsSingleTypesId) => {
    axiosInstance.delete(`/goodsSingleTypes/deleteSingleTypes/${goodsSingleTypesId}`)
    .then((response) => {
        if(response.data.code == 200){
            successEasy("删除");
            getAllSingleTypes();
        }
    })
    .catch((errors) => {
        console.log(errors);
    })
}
//获取所有的商品种类信息
let getAllSingleTypes = () => {
    goodsId.value = defineSignIds.getGoodsId;
    axiosInstance.get(`/goodsSingleTypes/getAllSingleTypes/${goodsId.value}`)
    .then((response) => {
        itemList.value = response.data.data;
    })
    .catch((errors) => {
        console.log(errors);
    })
}
onMounted(() => {
    getAllSingleTypes();
})
</script>

<template>
    <div class="goods-single-type-area">
        <div class="page-top">
            <p class="page-top-content">商品种类信息</p>
            <button class="page-top-button" @click="toGoods()">返回</button>
        </div>
        <div class="type-title">
            <p class="title-name title-same">种类信息</p>
            <p class="title-price title-same">价格</p>
            <p class="title-operation title-same">操作
                <button class="button-title" @click="toAddGoodsSingleType()">新增种类信息</button>
            </p>
        </div>
        <div class="type-single-area">
            <el-scrollbar height="400px" always="true">
                <div class="type-single" v-for="goodsSingleTypes in itemList" :key="goodsSingleTypes.goodsSingleTypesId">
                    <div class="title-name single-same">{{ goodsSingleTypes.typesName }}</div>
                    <div class="title-price single-same">￥{{ goodsSingleTypes.goodsPrice }}</div>
                    <div class="single-operation">
                        <button class="type-single-button" @click="toUpdateGoodsSingleType(goodsSingleTypes.goodsSingleTypesId)">修改</button>
                        <el-popconfirm  title="确定要删除此样式吗?"
                                        confirm-button-text="否"
                                        cancel-button-text="是"
                                        confirm-button-type="danger"
                                        width="180"
                                        @cancel="deleteGoods(goodsSingleTypes.goodsSingleTypesId)">
                            <template #reference>
                                <button class="type-single-button">删除</button>
                            </template>
                        </el-popconfirm>
                    </div>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>

<style scoped>
.goods-single-type-area {
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
.type-title {
    width: 800px;
    height: 50px;
    background-color: rgb(232, 222, 222);
    margin-top: 50px;
    margin-left: 50px;
}
.title-same {
    height: 40px;
    font-size: 20px;
    text-align: center;
    padding-top: 10px;
    float: left;
}
.title-name {
    width: 300px;
    border-right: 2px solid rgb(213, 213, 213);
}
.title-price {
    width: 200px;
    border-right: 2px solid rgb(213, 213, 213);
}
.title-operation {
    width: 290px;
}
.button-title {
    width: 100px;
    height: 30px;
    float: right;
    margin-right: 10px;
    color: white;
    background-color: rgb(255, 0, 38);
    border: none;
    border-radius: 10px;
    cursor: pointer;
}
.type-single-area {
    width: 800px;
    height: 400px;
    margin-left: 50px;
    margin-top: 20px;
}
.type-single {
    width: 800px;
    height: 60px;
    background-color: rgb(242, 237, 237);
    margin-bottom: 20px;
}
.single-same {
    height: 40px;
    text-align: center;
    font-size: 17px;
    padding-top: 20px;
    float: left;
}
.single-operation {
    width: 290px;
    height: 60px;
    float: left;
}
.type-single-button {
    width: 70px;
    height: 35px;
    margin-left: 50px;
    margin-top: 12px;
    font-size: 16px;
    color: white;
    background-color: rgb(255, 106, 0);
    border: none;
    border-radius: 10px;
    cursor: pointer;
}
.type-single-button:hover {
    background-color: rgb(255, 129, 39);
}
</style>