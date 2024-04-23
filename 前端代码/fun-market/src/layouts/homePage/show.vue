<script setup>
import Sort from './sort.vue';
import Main from './main.vue';
import Notice from './notice.vue';
import GoodsArea from './goodsArea.vue';
</script>

<template>
    <div id="show-all">
        <div id="show-live">
            <Sort id="sort"></Sort>
            <Main></Main>
            <Notice></Notice>
            <GoodsArea></GoodsArea>
        </div>
    </div>
</template>

<style scoped>
#show-all {
    width: 100%;
    background-color: rgb(245, 239, 239);
}
#show-live {
    width: 75%;
    background-color: white;
    margin: auto;
    border-top-left-radius: 30px;
    border-top-right-radius: 30px;
    padding-top: 30px;
    overflow: hidden;
}
</style>