<script setup>
import { ref, onMounted } from 'vue'
import { customerInfo, orderStatus } from '../../utils/pojo.js'
import { axiosInstance, unificationURL } from '../../utils/request.js'
import axios from 'axios';
import { useRouter } from 'vue-router'
import { defineSignId } from '../../store/store.js'

let router = useRouter();
let defineSignIds = defineSignId();
let userId = ref(window.sessionStorage.getItem('token'));
let itemList = ref();

let toGoods = (goodsId) => {
    defineSignIds.setGoodsId(goodsId);
    router.push('/goods');
}

//获取用户昵称
let getCustomerInfo = () => {
    if(userId != null){
        axiosInstance.get('/customerInfo/getCustomerName')
        .then((response) => {
            customerInfo.nickname = response.data.data;
        })
        .catch((error) => {
            console.log(error);
        })
    }
}
//获取订单各种状态的个数
let getOrderStatus = () => {
    if(userId != null){
        axiosInstance.get('/order/getOrderStatus')
        .then((response) => {
            orderStatus.awaitingPayment = response.data.data.awaitingPayment;
            orderStatus.awaitingShipment = response.data.data.awaitingShipment;
            orderStatus.awaitingDelivery = response.data.data.awaitingDelivery;
            orderStatus.awaitingEvaluation = response.data.data.awaitingEvaluation;
        })
        .catch((errors) => {
            console.log(errors);
        })
    }
}

//获取用户头像
let getHeadPicture = () => {
    axiosInstance.get('/customerInfo/getHeadPicture',{responseType:'blob'})
    .then((response) => {
        if(response.data.size > 100){
            customerInfo.portraitPath = URL.createObjectURL(new Blob([response.data]));
        }
    })
    .catch((errors) => {
        console.log(errors);    
    });
}

//获取展示的5张商品图片
let getBestSellingProductsTopFive = () => {
    axios.get(`${unificationURL}/goods/getBestSellingProductsTopFive`)
    .then((response) => {
        itemList.value = response.data.data;
        itemList.value.forEach((item) => {
            getPicture(item.goodsCoverPath,item);
        });
    })
    .catch((errors) => {
        console.log(errors);    
    });
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

onMounted(() => {
    getCustomerInfo();
    getOrderStatus();
    getHeadPicture();
    getBestSellingProductsTopFive();
})
</script>

<template>
    <div id="main">
        <div id="carousel">
            <el-carousel :interval="5000" trigger="click">
                <el-carousel-item v-for="goods in itemList" :key="goods.goodsId">
                    <img v-bind:src="goods.goodsCoverPath" class="picture" @click="toGoods(goods.goodsId)">
                </el-carousel-item>
            </el-carousel>
        </div>
        <div id="user">
            <div id="head-portrait">
                <img v-if="userId == null" src="../../assets/unlogged-avatar.png">
                <img v-else v-bind:src="customerInfo.portraitPath">
            </div>
            <p v-if="userId == null" id="user-message">hi ,你好!</p>
            <p v-else id="user-message">hi ,{{ customerInfo.nickname }}!</p>
            <div v-if="userId == null" class="login-operation">
                <router-link to="/login">
                    <button class="user-button">登录</button>
                </router-link>
                <router-link to="/register">
                    <button class="user-button">注册</button>
                </router-link>
            </div>
            <div v-else class="login-operation">
                <div class="status-single">
                    <p class="status-number">{{ orderStatus.awaitingPayment }}</p>
                    <p class="status-word">待付款</p>
                </div>
                <div class="status-single">
                    <p class="status-number">{{ orderStatus.awaitingShipment }}</p>
                    <p class="status-word">待发货</p>
                </div>
                <div class="status-single">
                    <p class="status-number">{{ orderStatus.awaitingDelivery }}</p>
                    <p class="status-word">待收货</p>
                </div>
                <div class="status-single">
                    <p class="status-number">{{ orderStatus.awaitingEvaluation }}</p>
                    <p class="status-word">待评价</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
#main {
    float: left;
}
#carousel {
    width: 500px;
    margin-left: 30px;
    border-radius: 20px;
    cursor: pointer;
    float: left;
}
.picture {
    width: 500px;
    height: 300px;
    border-radius: 20px;
}
#user {
    width: 220px;
    height: 300px;
    margin-left: 30px;
    border-radius: 20px;
    background-color: rgb(249, 248, 247);
    float: left;
}
#head-portrait {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    margin: auto;
    margin-top: 40px;
    background-color: black;
}
#head-portrait img {
    width: 80px;
    height: 80px;
    border-radius: 50%;
}
#user-message {
    margin-top: 25px;
    font-size: 25px;
    text-align: center;
}
.user-button {
    height: 40px;
    width: 60px;
    margin-left: 30px;
    margin-top: 30px;
    border-radius: 10px;
    border: none;
    font-size: 15px;
    background-color: rgb(253, 142, 57);
    cursor: pointer;
}
.user-button:hover {
    background-color: rgb(255, 158, 84);
}
.login-operation {
    width: 220px;
    height: 100px;
}
.status-single {
    width: 40px;
    height: 70px;
    padding-top: 20px;
    margin-left: 12px;
    float: left;
}
.status-number {
    text-align: center;
    color: orangered;
    font-size: 18px;
    font-weight: bolder;
    margin-top: 15px;
}
.status-word {
    font-size: 13px;
    margin-top: 5px;
}
</style>