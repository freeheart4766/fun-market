<script setup>
import { onMounted, reactive } from 'vue';
import { useRouter } from 'vue-router'
import { defineSignId } from '../../../store/store.js'
import { axiosInstance } from '../../../utils/request.js';
import { successEasy } from '../../../utils/notification';

let router = useRouter();
let defineSignIds = defineSignId();

let goodsSingleTypes = reactive({
    goodsSingleTypesId : '',
    goodsId : '',
    typesName : '',
    goodsPrice : '',
})

let toGoodsSingleType = () => {
    router.push('/sellerHome/goodsSingleType');
}

let updateSingleTypes = () => {
    axiosInstance.put('/goodsSingleTypes/updateSingleTypes',goodsSingleTypes)
    .then((response) => {
        if(response.data.code == 200){
            successEasy("修改");
            toGoodsSingleType();
        }
    })
    .catch((errors) => {
        console.log(errors);
    })
}

let getSingleTypes = () => {
    goodsSingleTypes.goodsSingleTypesId = defineSignIds.getGoodsSingleTypesId;
    axiosInstance.get(`/goodsSingleTypes/getSingleTypes/${goodsSingleTypes.goodsSingleTypesId}`)
    .then((response) => {
        goodsSingleTypes.typesName = response.data.data.typesName;
        goodsSingleTypes.goodsPrice = response.data.data.goodsPrice;
    })
    .catch((errors) => {
        console.log(errors);
    })
}
onMounted(() => {
    getSingleTypes();
})
</script>

<template>
    <div class="add-single-type-area">
        <div class="page-top">
            <p class="page-top-content">修改商品种类信息</p>
            <button class="page-top-button" @click="toGoodsSingleType()">返回</button>
        </div>
        <div class="add-same">
            <p class="word-same">种类信息：</p>
            <el-input class="input-area" v-model="goodsSingleTypes.typesName" size="large" />
        </div>
        <div class="add-same">
            <p class="word-same">价格：</p>
            <el-input class="input-area" v-model="goodsSingleTypes.goodsPrice" size="large" />
        </div>
        <div class="add-same">
            <button class="button" @click="updateSingleTypes()">提交</button>
        </div>
    </div>
</template>

<style scoped>
.add-single-type-area {
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
    margin-bottom: 80px;
}
.page-top-content {
    width: 300px;
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
.add-same {
    width: 600px;
    height: 60px;
    margin-left: 100px;
    margin-top: 20px;
}
.word-same {
    width: 100px;
    height: 30px;
    font-size: 18px;
    padding-top: 20px;
    float: left;
}
.input-area {
    width: 400px;
    font-size: 16px;
    margin-top: 10px;
    float: left;
}
.button {
    width: 200px;
    height: 40px;
    font-size: 18px;
    border: none;
    border-radius: 5px;
    color: white;
    background-color: orangered;
    margin-left: 250px;
    margin-top: 50px;
    cursor: pointer;
}
.button:hover {
    background-color: rgb(255, 100, 44);
}
</style>