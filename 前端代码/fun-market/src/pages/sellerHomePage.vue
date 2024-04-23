<script setup>
import SellerTop from '../layouts/sellerHomePage/sellerTop.vue';
import Menu from '../layouts/sellerHomePage/menu.vue'
</script>

<template>
    <div class="seller-home">
        <SellerTop></SellerTop>
        <Menu class="menu"></Menu>
        <div class="router-to">
            <router-view></router-view>
        </div>
    </div>
</template>

<style scoped>
.seller-home {
    background-color: rgb(241, 239, 239);
}
.menu {
    margin-left: 100px;
}
.router-to {
    width: 1200px;
    height: 655px;
    margin-left: 220px;
    padding-top: 65px;
}
</style>