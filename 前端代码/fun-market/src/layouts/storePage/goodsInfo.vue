<script setup>
import { goods } from '../../utils/pojo.js'
import { useRouter } from 'vue-router'
let router = useRouter()

let toGoods = () => {
    router.push('/goods');
}
</script>

<template>
    <div class="goods-area">
        <div class="goods-single" @click="toGoods()">
            <img class="goods-picture" v-bind:src="goodsShowPicture.pitcureOnePath">
            <p class="goods-price">￥{{ goodsSingleTypes.goodsPrice }}</p>
            <p class="goods-name">{{ goods.goodsName }}</p>
        </div>
    </div>
</template>

<style scoped>
.goods-area {
    width: 1020px;
    margin-left: 250px;
    margin-top: 30px;
    overflow: hidden;
}
.goods-single {
    width: 240px;
    height: 350px;
    background-color: white;
    border: 2px white solid;
    cursor: pointer;
    margin-right: 10px;
    margin-top: 10px;
    float: left;
}
.goods-single:hover {
    border: 2px orangered solid;
}
.goods-picture {
    width: 220px;
    height: 250px;
    margin-left: 10px;
    margin-top: 10px;
}
.goods-price {
    width: 150px;
    height: 30px;
    font-size: 25px;
    text-align: center;
    font-weight: bolder;
    margin-left: 35px;
}
.goods-name {
    width: 200px;
    height: 40px;
    font-size: 14px;
    margin-left: 20px;
    margin-top: 8px;
    overflow: hidden;
}
</style>