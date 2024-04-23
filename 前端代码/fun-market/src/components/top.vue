<script setup>
import { onMounted, ref } from 'vue'
import { customerInfo } from '../utils/pojo.js'
import { axiosInstance } from '../utils/request.js'

let userId = ref(window.sessionStorage.getItem('token'));

let getCustomerNickname = () => {
    if(userId != null){
        axiosInstance.get('/customerInfo/getCustomerName')
        .then((response) => {
            customerInfo.nickname = response.data.data;
        })
        .catch((errors) => {
            console.log(errors);
        })
    }
}
let exit = () => {
    window.sessionStorage.removeItem('token');
    location.reload();
}

onMounted(() => {
    getCustomerNickname();
})
</script>

<template>
    <div id="top">
        <ul id="nav-left">
            <li><router-link to="/" class="router-home">首页</router-link></li>
            <div v-if="userId != null" class="else-area">
                <li class="router-else">欢迎，<span>{{ customerInfo.nickname }}</span></li>
                <li class="router-left" @click="exit()">退出登录</li>
            </div>
            <div v-else class="else-area">
                <li><router-link to="/login" class="router-left">亲，<span>请登录</span></router-link></li>
                <li><router-link to="/register" class="router-left">免费注册</router-link></li>
            </div>
        </ul>
        <ul id="nav-right">
            <li id="myOrder"><router-link to="/personal">个人中心</router-link></li>
            <li><router-link to="/personal/shoppingCart">购物车</router-link></li>
            <li>
                <div id="outer">
                    <span id="word">收藏</span>
                    <div id="inner">
                        <router-link to="/personal/storeFollow">关注的店铺</router-link>
                        <router-link to="/personal/goodsCollection">收藏的宝贝</router-link>
                    </div>
                </div>
            </li>
            <li v-if="userId == null" id="sellerLogin"><router-link to="/sellerLogin">商家登录</router-link></li>
            <li v-if="userId == null" id="adminLogin"> <router-link to="/adminLogin">平台管理员登录</router-link></li>
        </ul>
    </div>
</template>

<style scoped>
#top {
    width: 100%;
    height: 40px;
    background-color: rgb(230, 230, 230);
    display:inline-block;
}
#nav-left {
    display: flex;
    list-style-type: none;
    margin: 0;
    padding-left: 200px;
    overflow: hidden;
    float: left;
}
.router-home:hover {
    color: red;
}
.router-left, .router-home {
    display: block;
    color: rgb(68, 63, 63);
    text-align: center;
    padding: 8px 10px;
    text-decoration: none;
    cursor: pointer;
    float: left;
}
.router-left span {
    color: rgb(233, 102, 15);
}
.router-left:hover {
    color: rgb(233, 66, 15);
}
.router-else {
    display: block;
    color: rgb(68, 63, 63);
    text-align: center;
    padding: 8px 10px;
    text-decoration: none;
    cursor: pointer;
    float: left;
}
.else-area {
    width: fit-content;
    min-width: 200px;
}
#nav-right {
    display: flex;
    list-style-type: none;
    margin: 0;
    padding-right: 200px;
    overflow: hidden;
    float: right;
}
#myOrder {
    padding-right: 20px;
}
#nav-right li a:hover:not(#inner a) , #word:hover {
    color: rgb(233, 66, 15);
}
#nav-right li a {
    display: block;
    color: rgb(68, 63, 63);
    text-align: center;
    padding: 8px 10px;
    text-decoration: none;
}
#word {
    display: block;
    color: rgb(68, 63, 63);
    text-align: center;
    padding: 10px 10px;
    text-decoration: none;
    width: 80px;
    cursor: pointer;
}
#outer {
    display: inline-block;
}
#inner {
    display:none;
    background-color: white;
    z-index: 1;
}
#inner a {
    color: black;
    text-decoration: none;
    display: block;
}
#inner a:hover {
    background-color: rgb(236, 234, 234);
}
#outer:hover #inner {
    display: block;
}
#outer:hover #word {
    background-color: rgb(243, 242, 242);
}
#sellerLogin , #adminLogin {
    padding-right: 10px;
}
</style>