<script setup>
import { ref } from 'vue'
import { history, goods } from '../../../utils/pojo.js'
import { useRouter } from 'vue-router';
let router = useRouter();
let toGoods = () => {
    router.push('/goods');
}
let day = ref('');

let checkDay = () => {
    if(history.time != day.value){
        return true;
    }else{
        day.value = history.time;
        return false;
    }
}
</script>

<template>
    <div id="history-area">
        <el-scrollbar height="590px" always="true">
            <div class="history-single">
                <div class="history-day" v-if=checkDay()>{{ history.time }}</div>
                <div class="history-day" v-else>
                    <hr class="history-hr">
                </div>
                <div class="history-goods" @click="toGoods()">
                    <img class="history-goods-picture" v-bind:src="goodsShowPicture.pitcureOnePath">
                    <div class="history-goods-info">
                        <p class="goods-info-price">￥{{ goodsSingleTypes.goodsPrice }}</p>
                        <p class="goods-info-name">{{ goods.goodsName }}</p>
                    </div>
                </div>
            </div>
        </el-scrollbar>
    </div>
</template>

<style scoped>
#history-area {
    width: 810px;
    height: 590px;
    background-color: white;
    margin-left: 20px;
    margin-top: 20px;
    padding-left: 20px;
    padding-top: 20px;
    border-radius: 10px;
}
.history-single {
    width: 160px;
    height: 250px;
    padding-bottom: 20px;
    cursor: pointer;
    float: left;
}
.history-day {
    width: 160px;
    height: 40px;
    font-size: 25px;
    font-weight: bolder;
    padding-top: 10px;
}
.history-hr {
    margin-top: 20px;
    border: 1px rgb(223, 222, 222) solid;
}
.history-goods {
    width: 145px;
    height: 200px;
}
.history-goods-picture {
    width: 145px;
    height: 145px;
}
.history-goods-info {
    width: 144px;
    height: 55px;
    background-color: rgb(250, 250, 250);
    border: 1px rgb(223, 222, 222) solid;
    margin-top: -5px;
}
.goods-info-price {
    margin-left: 30px;
    margin-top: 5px;
    font-weight: bolder;
    font-size: 18px;
    color: rgb(255, 121, 76);
}
.goods-info-name {
    width: 124px;
    height: 20px;
    font-size: 13px;
    color: rgb(149, 146, 146);
    margin-left: 10px;
    margin-top: 5px;
    overflow: hidden;
}
</style>