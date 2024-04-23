<script setup>
import Top from '../components/top.vue'
import Sidebar from '../components/sidebar.vue'
import StoreInfo from '../layouts/goodsPage/storeInfo.vue'
import GoodsInfo from '../layouts/goodsPage/goodsInfo.vue'
import GoodsEvaluation from '../layouts/goodsPage/goodsEvaluation.vue'
</script>

<template>
    <div class="goods-page">
        <Top></Top>
        <Sidebar></Sidebar>
        <StoreInfo></StoreInfo>
        <GoodsInfo></GoodsInfo>
        <GoodsEvaluation></GoodsEvaluation>
    </div>
</template>

<style scoped>
.goods-page {
    background-color: rgb(235, 221, 221);
    padding-bottom: 50px;
}
</style>