<script setup>
import { useRouter } from 'vue-router';

let router = useRouter()

let quit = () => {
    window.sessionStorage.removeItem('token');
    router.push('/');
}
</script>

<template>
    <div id="top-area">
        <p id="top-title">我的趣集</p>
        <div class="router-area"><a class="router-to" @click="quit()">退出登录</a></div>
        <div class="router-area"><router-link to="/" class="router-to">趣集网首页</router-link></div>
    </div>
</template>

<style scoped>
#top-area {
    width: 100%;
    height: 65px;
    background-color: rgb(244, 79, 34);
    overflow: hidden;
    position: fixed;
    z-index: 2;
}
#top-title {
    font-family: KaiTi;
    font-size: 35px;
    color: white;
    margin-left: 200px;
    padding-top: 10px;
    cursor:default;
    float: left;
}
.router-area {
    width: 120px;
    height: 30px;
    margin-right: 50px;
    margin-top: 30px;
    float: right;
    cursor: pointer;
}
.router-to {
    font-family: KaiTi;
    font-size: 20px;
    color: white;
    text-decoration: none;
}
</style>