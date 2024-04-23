<script setup>
import Top from '../components/top.vue';
import Header from '../components/header.vue';
import Sidebar from '../components/sidebar.vue';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router'
import { axiosInstance } from '../utils/request';
import { defineSignId } from '../store/store.js'

let router = useRouter()
let defineSignIds = defineSignId();
//商品种类信息的集合
let itemList = ref();

//前往商品信息页
let toGoodsPage = (goodsId) => {
    defineSignIds.setGoodsId(goodsId);
    router.push('/goods');
}
//模糊查询符合名称要求的所有商品信息
let getGoodsByName = () => {
    let goodsName = defineSignIds.getGoodsName;
    axiosInstance.get(`/goods/getGoodsByName/${goodsName}`)
    .then((response) => {
        itemList.value = response.data.data;
        itemList.value.forEach((item) => {
            getPicture(item.goodsCoverPath,item);
            getMinPrice(item.goodsId,item);
        });
    })
}
//查询此种类的商品
let getGoodsByType = () => {
    let goodsType = defineSignIds.getGoodsType;
    axiosInstance.get(`/goods/getGoodsByType/${goodsType}`)
    .then((response) => {
        itemList.value = response.data.data;
        itemList.value.forEach((item) => {
            getPicture(item.goodsCoverPath,item);
            getMinPrice(item.goodsId,item);
        });
    })
}
//获取图片
let getPicture = (picturePath,item) => {
    let index = picturePath.lastIndexOf('\\');
    picturePath = picturePath.substring(index + 1);
    axiosInstance.get(`/system/getPicture/${picturePath}`,{responseType:'blob'})
    .then((response) => {
        item.goodsCoverPath = URL.createObjectURL(new Blob([response.data]));
    })
    .catch((errors) => {
        console.log(errors);    
    });
}
//获取商品的最低价格
let getMinPrice = (goodsId,item) => {
    axiosInstance.get(`/goodsSingleTypes/getMinPrice/${goodsId}`)
    .then((response) => {
        item.goodsPrice = response.data.data;
    })
}
//判断是什么渠道进入搜索页的
let checkChannel = () => {
    let goodsType = defineSignIds.getGoodsType;
    if(goodsType != null && goodsType != ''){
        getGoodsByType();
        defineSignIds.setGoodsType('');
    }else{
        getGoodsByName();
    }
}
onMounted(() => {
    checkChannel();
})
</script>

<template>
    <div>
        <Top id="top"></Top>
        <Header id="header"></Header>
        <Sidebar></Sidebar>
        <div class="goods-area">
            <div class="goods-single" v-for="goods in itemList" :key="goods.goodsId" @click="toGoodsPage(goods.goodsId)">
                <img v-bind:src="goods.goodsCoverPath" class="goods-picture">
                <div class="goods-name">{{ goods.goodsName }}</div>
                <div class="goods-price">￥{{ goods.goodsPrice }}</div>
            </div>
        </div>
    </div>
</template>

<style scoped>
#top {
    position: absolute;
}
#header{
    padding-top: 40px;
}
.goods-area {
    width: 1216px;
    height: 500px;
    margin-left: 160px;
    margin-top: 20px;
}
.goods-single {
    width: 220px;
    height: 350px;
    border: 2px solid rgb(230, 230, 230);
    margin-right: 20px;
    margin-bottom: 20px;
    cursor: pointer;
    float: left;
}
.goods-single:hover {
    border: 2px solid orangered;
}
.goods-picture {
    width: 214px;
    height: 230px;
    margin-left: 3px;
    margin-top: 3px;
}
.goods-name {
    width: 210px;
    height: 45px;
    font-size: 17px;
    text-align: center;
    margin-left: 5px;
    margin-top: 5px;
    overflow: hidden;
}
.goods-price {
    width: 210px;
    height: 35px;
    margin-top: 15px;
    margin-left: 5px;
    text-align: center;
    font-size: 25px;
    font-weight: bolder;
    color: rgb(255, 24, 24);
}
</style>