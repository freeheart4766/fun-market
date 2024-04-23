<script setup>
import Goods from './goods.vue';
</script>

<template>
    <div>
        <div id="goods-title-area">
            <svg t="1704707211421" id="title-icon-left" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="13399">
                <path d="M849.5 136.3c-20.2-10.4-45-2.4-55.4 17.7L444.6 832.4c-10.4 20.2-2.5 45 17.7 55.4 6 3.1 12.4 4.6 18.8 4.6 14.9 0 29.3-8.1 36.6-22.3l349.5-678.4c10.4-20.2 2.5-45-17.7-55.4zM479.4 295.9c-20.2-10.5-45-2.5-55.4 17.7L156.8 832.4c-10.4 20.2-2.5 45 17.7 55.4 6 3.1 12.4 4.6 18.8 4.6 14.9 0 29.3-8.1 36.6-22.3l267.2-518.7c10.4-20.3 2.5-45.1-17.7-55.5z" p-id="13400"></path>
            </svg>
            <p id="goods-title">为你推荐</p>
            <svg t="1704707588503" id="title-icon-right" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="13597">
                <path d="M174.5 887.7c20.2 10.4 45 2.4 55.4-17.7l349.5-678.4c10.4-20.2 2.5-45-17.7-55.4-6-3.1-12.4-4.6-18.8-4.6-14.9 0-29.3 8.1-36.6 22.3L156.8 832.4c-10.4 20.1-2.5 44.9 17.7 55.3zM544.6 728.1c20.2 10.5 45 2.5 55.4-17.7l267.2-518.7c10.4-20.2 2.5-45-17.7-55.4-6-3.1-12.4-4.6-18.8-4.6-14.9 0-29.3 8.1-36.6 22.3L526.9 672.7c-10.4 20.2-2.5 45 17.7 55.4z" p-id="13598"></path>
            </svg>
        </div>
        <Goods></Goods>
    </div>
</template>

<style scoped>
#goods-title-area {
    width: 1080px;
    height: 100px;
    margin-left: 30px;
    margin-top: 30px;
    float: left;
}
#title-icon-left {
    width: 35px;
    margin-left: 390px;
    margin-top: 25px;
    float: left;
}
#goods-title {
    font-family: KaiTi;
    font-size: 40px;
    margin-left: 10px;
    float: left;
    padding-top: 20px;
}
#title-icon-right {
    width: 35px;
    margin-left: 10px;
    margin-top: 30px;
    float: left;
}
</style>