<script setup>
import { onMounted, ref, reactive } from "vue";
import { successEasy } from "../../../utils/notification.js";
import { axiosInstance } from "../../../utils/request.js";
import StoreAvator from './storeAvator.vue';
import StoreCover from './storeCover.vue';

let storeNameResult = ref(1);

let storeInfo = reactive({
    storeId : '',
    storeName : '',
    openingDate : '',
    portraitPath : '',
    storeCoverPicture : '',
})

//修改店铺名称
let updateName = () => {
    axiosInstance.put('/storeInfo/updateStoreName',storeInfo)
    .then((response) => {
        if(response.data.code != 200){
            error('提交',response.data.message);
        }else {
            successEasy('提交','');
            storeNameResult.value = 1;
            getStoreInfo();
        }
    })
}
let changeStatus = () => {
    storeNameResult.value = 0;
}

//获取店铺信息
let getStoreInfo = () => {
    axiosInstance.get('/storeInfo/getStoreInfo')
    .then((response) => {
        storeInfo.storeName = response.data.data.storeName;
        storeInfo.portraitPath = response.data.data.portraitPath;
        storeInfo.storeCoverPicture = response.data.data.storeCoverPicture;
    })
}

onMounted(() => {
    getStoreInfo();
})
</script>

<template>
    <div class="store-area">
        <div class="store-name">
            <p class="store-name-title">店铺名称：</p>
            <div class="store-name-else" v-if="storeNameResult == 0">
                <el-input class="store-name-input" v-model="storeInfo.storeName" type="text" size="large" />
                <button class="store-name-submit" @click="updateName()">提交</button>
            </div>
            <div class="store-name-else" v-else>
                <p class="store-name-content">{{ storeInfo.storeName }}</p>
                <button class="store-name-submit" @click="changeStatus()">修改</button>
            </div>
        </div>
        <StoreAvator></StoreAvator>
        <StoreCover></StoreCover>
    </div>
</template>

<style scoped>
.store-area {
    width: 1000px;
    height: 580px;
    background-color: white;
    margin-top: 20px;
    border-radius: 15px;
    padding-top: 30px;
    padding-left: 50px;
}
.store-name {
    width: 610px;
    height: 50px;
    margin-top: 30px;
}
.store-name-title {
    width: 100px;
    height: 30px;
    font-size: 20px;
    padding-top: 10px;
    float: left;
}
.store-name-else {
    width: 500px;
    height: 50px;
    float: left;
    margin-left: 10px;
}
.store-name-input {
    width: 300px;
    margin-top: 5px;
    font-size: 18px;
    float: left;
}
.store-name-submit {
    width: 60px;
    height: 35px;
    margin-left: 20px;
    margin-top: 8px;
    background-color: orangered;
    color: white;
    font-size: 15px;
    border-radius: 10px;
    border: none;
    cursor: pointer;
    float: left;
}
.store-name-submit:hover {
    background-color: rgb(255, 90, 30);
}
.store-name-content {
    width: 300px;
    height: 30px;
    font-size: 18px;
    margin-top: 10px;
    float: left;
}

</style>