<script setup>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router'
import { getSearchContentResult } from '../utils/checkResult.js'
import { warning } from '../utils/notification.js';
import { defineSignId } from '../store/store.js'

let router = useRouter();
let searchContent = ref();
let defineSignIds = defineSignId();

let toSearchPage = () => {
    if(searchContent.value == null || searchContent.value == ''){
        warning('请输入搜索内容');
    }else{
        let searchContentResult = getSearchContentResult(searchContent.value);
        if(searchContentResult){
            defineSignIds.setGoodsName(searchContent.value);
            console.log(defineSignIds.getGoodsName)
            router.push('/search');
        }
    }
}
let getGoodsName = () => {
    let goodsName = defineSignIds.getGoodsName;
    if(goodsName != null || goodsName != ''){
        searchContent.value = goodsName;
    }
}
onMounted(() => {
    getGoodsName();
})
</script>

<template>
    <div id="header">
        <img id="header-logo" src="../assets/header-logo.png">
        <div id="searchArea">
            <input id="search" placeholder="搜索宝贝" v-model="searchContent">
            <button id="searchButton" @click="toSearchPage()"><Search class="buttonWord"/>搜索</button>
        </div>
    </div>
</template>

<style scoped>
#header {
    height: 120px;
    background-color: rgb(245, 239, 239);
}
#header-logo {
    width: 150px;
    margin-left: 250px;
    margin-top: 10px;
    cursor: pointer;
    float: left;
}
#searchArea {
    width: 723px;
    height: 50px;
    margin-top: 35px;
    margin-left: 500px;
    border:2px solid red;
    border-radius: 10px;
    background-color: white;
}
#search {
    width: 600px;
    height: 50px;
    padding-left: 20px;
    font-size: large;
    border: 0;
    border-radius: 10px;
    outline: none;
}
#searchButton {
    background-color: rgb(253, 144, 36);
    width: 100px;
    height: 44px;
    border: none;
    border-radius: 10px;
    font-size: 15px;
    cursor: pointer;
}
#searchButton:hover {
    background-color: rgb(255, 159, 63);
}
.buttonWord {
    width: 15px; 
    height: 15px; 
    margin-right: 8px;
}
</style>