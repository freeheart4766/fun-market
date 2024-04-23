<script setup>
import { useRouter } from 'vue-router'
let router = useRouter()

let toStore = () => {
    router.push('/sellerHome');
}
let toGoods = () => {
    router.push('/sellerHome/goods');
}
let toMessage = () => {
    router.push('/sellerHome/message');
}
let toOrder = () => {
    router.push('/sellerHome/order');
}
let toRefund = () => {
    router.push('/sellerHome/refund');
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
        <div class="menu-single active" @click="toStore()">
            <p class="menu-word">店铺信息</p>
        </div>
        <div class="menu-single" @click="toGoods()">
            <p class="menu-word">商品信息</p>
        </div>
        <div class="menu-single" @click="toMessage()">
            <p class="menu-word">消息</p>
        </div>
        <div class="menu-single" @click="toOrder()">
            <p class="menu-word">订单处理</p>
        </div>
        <div class="menu-single" @click="toRefund()">
            <p class="menu-word">售后处理</p>
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