<script setup>
import { useRouter } from 'vue-router'
let router = useRouter()

let toPersonalData = () => {
    router.push('/personal');
}
let toshoppingCart = () => {
    router.push('/personal/shoppingCart');
}
let toMyOrder = () => {
    router.push('/personal/myOrder');
}
let toGoodsCollection = () => {
    router.push('/personal/goodsCollection');
}
let toStoreFollow = () => {
    router.push('/personal/storeFollow');
}
let toMessage = () => {
    router.push('/personal/message');
}
let toHistory = () => {
    router.push('/personal/history');
}
let toEvaluation = () => {
    router.push('/personal/evaluation');
}
let toRefund = () => {
    router.push('/personal/refund');
}
let toAddress = () => {
    router.push('/personal/address');
}


let changeActive = () => {
    let items = document.querySelectorAll('.menu-single');
    items.forEach((item)=>{
        item.addEventListener('click',() => {
            items.forEach(item => {
                item.classList.remove('active');
            });
            item.classList.add('active');
        })
    });
}
</script>

<template>
    <div id="menu-area" @mouseover="changeActive()">
        <div class="menu-single active" @click="toPersonalData()">
            <p class="menu-word">个人信息</p>
        </div>
        <div class="menu-single" @click="toshoppingCart()">
            <p class="menu-word">购物车</p>
        </div>
        <div class="menu-single" @click="toMyOrder()">
            <p class="menu-word">我的订单</p>
        </div>
        <div class="menu-single" @click="toGoodsCollection()">
            <p class="menu-word">宝贝收藏</p>
        </div>
        <div class="menu-single" @click="toStoreFollow()">
            <p class="menu-word">店铺关注</p>
        </div>
        <div class="menu-single" @click="toMessage()" id="abc">
            <p class="menu-word">消息</p>
        </div>
        <div class="menu-single" @click="toHistory()">
            <p class="menu-word">历史浏览</p>
        </div>
        <div class="menu-single" @click="toEvaluation()">
            <p class="menu-word">我的评价</p>
        </div>
        <div class="menu-single" @click="toRefund()">
            <p class="menu-word">退款售后</p>
        </div>
        <div class="menu-single" @click="toAddress()">
            <p class="menu-word">地址管理</p>
        </div>
    </div>
</template>

<style scoped>
#menu-area {
    width: 100px;
    height: 650px;
    background-color: rgb(250, 250, 250);
    position: fixed;
    top: 65px;
}
.menu-single {
    width: 100px;
    height: 65px;
    cursor: pointer;
}
.menu-single:hover {
    background-color: rgb(226, 223, 219);
}
.menu-word {
    font-family: KaiTi;
    font-size: 20px;
    text-align: center;
    padding-top: 20px;
}
.active {
    background-color: rgb(226, 223, 219);
}
</style>