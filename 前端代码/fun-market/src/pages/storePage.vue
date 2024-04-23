<script setup>
import Top from '../components/top.vue'
import Sidebar from '../components/sidebar.vue'
import StoreInfo from '../layouts/storePage/storeInfo.vue'
import GoodsInfo from '../layouts/storePage/goodsInfo.vue';
</script>

<template>
    <div class="store-area">
        <Top></Top>
        <Sidebar></Sidebar>
        <StoreInfo></StoreInfo>
        <GoodsInfo></GoodsInfo>
    </div>
</template>

<style scoped>
.store-area {
    background-color: rgb(234, 228, 238);
    padding-bottom: 50px;
}
</style>