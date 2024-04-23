<script setup>
import { ref } from "vue";

let noticeContent = ref('');

</script>

<template>
    <div id="notice">
        <p id="title">公告</p>
        <p id="content">新鲜事儿都在这里！</p>
    </div>
</template>

<style scoped>
#notice {
    width: 750px;
    height: 90px;
    background-color: rgb(245, 241, 241);
    margin-top: 20px;
    margin-left: 30px;
    border-radius: 20px;
    float: left;
}
#title {
    font-family: Kaiti;
    font-size: 25px;
    margin-left: 20px;
    margin-top: 10px;
}
#content {
    width: 600px;
    font-family: Kaiti;
    font-size: 20px;
    margin-left: 80px;
    margin-top: 10px;
    overflow: hidden;
}
</style>