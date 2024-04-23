<script setup>
import Top from '../components/top.vue';
import Header from '../components/header.vue';
import Sidebar from '../components/sidebar.vue';
import Show from '../layouts/homePage/show.vue';
</script>

<template>
    <div>
        <Top id="top"></Top>
        <Header id="header"></Header>
        <Sidebar></Sidebar>
        <Show></Show>
    </div>
</template>

<style scoped>
#top {
    position: absolute;
}
#header{
    padding-top: 40px;
}
</style>