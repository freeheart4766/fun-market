<script setup>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { axiosInstance, unificationURL } from '../../utils/request';
import axios from 'axios';
import { defineSignId } from '../../store/store.js'

let loginStatus = ref();
let router = useRouter();
let defineSignIds = defineSignId();
let itemList = ref();

//获取商品信息
let getGoodsInfo = () => {
    loginStatus.value = window.sessionStorage.getItem("token");
    if(loginStatus.value == null || loginStatus.value == ''){
        getBestSellingProductsTopThirty();
    }else{
        getBestSellingProductsTopThirty();
        //getGoodsByCustomer();
    }
}
//顾客未登录时获取销量最高的30种商品
let getBestSellingProductsTopThirty = () => {
    axios.get(`${unificationURL}/goods/getBestSellingProductsTopThirty`)
    .then((response) => {
        itemList.value = response.data.data;
        itemList.value.forEach((item) => {
            getPicture(item.goodsCoverPath,item);
            getMinPrice(item.goodsId,item);
        });
    })
    .catch((errors) => {
        console.log(errors);    
    });
}
//顾客已登录时，根据推荐算法获取商品信息
let getGoodsByCustomer = () => {

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

let toGoods = (goodsId) => {
    defineSignIds.setGoodsId(goodsId);
    router.push('/goods');
}

onMounted(() => {
    getGoodsInfo();
})
</script>

<template>
    <div id="goods-area-all">
        <div class="goods-area-single" v-for="goods in itemList" :key="goods.goodsId" @click="toGoods(goods.goodsId)">
            <img class="goods-image" v-bind:src="goods.goodsCoverPath">
            <div class="goods-name">{{ goods.goodsName }}</div>
            <div class="goods-price">
                <span>￥</span>
                {{ goods.goodsPrice }}
            </div>
        </div>
    </div>
</template>

<style scoped>
#goods-area-all {
    width: 1120px;
    margin-left: 30px;
    float: left;
}
.goods-area-single {
    width: 340px;
    height: 150px;
    background-color: rgb(230, 230, 230);
    border: 1px solid white;
    margin-right: 30px;
    margin-bottom: 30px;
    float: left;
    border-radius: 15px;
    cursor: pointer;
}
.goods-area-single:hover {
    background-color: rgb(235, 235, 235);
    border: 1px solid orangered;
}
.goods-image {
    width: 150px;
    height: 130px;
    margin-left: 10px;
    margin-top: 10px;
    border-radius: 15px;
    float: left;
}
.goods-name {
    width: 150px;
    height: 30px;
    margin-left: 15px;
    margin-top: 25px;
    font-size: 20px;
    float: left;
    overflow: hidden;
}
.goods-price {
    width: 150px;
    height: 30px;
    margin-left: 15px;
    margin-top: 35px;
    font-size: 22px;
    float: left;
}
</style>