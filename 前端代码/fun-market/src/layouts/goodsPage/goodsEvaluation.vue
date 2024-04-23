<script setup>
import { ref, reactive } from 'vue';
import { customerInfo } from '../../utils/pojo.js'

let evaluation = reactive({
    evaluationId : 10001,
    customerId : '',
    goodsId : 1001,
    date : '2023-12-25',
    evaluationLevel : '好评',
    productDescription : '5',
    sellerService : '4',
    logisticsService : '3',
    evaluationContent : '商品不错，下次还会再买。',
})

let changeActive = () => {
    let items = document.querySelectorAll('.select-words');
    items.forEach((item)=>{
        item.addEventListener('click',() => {
            items.forEach(item => {
                item.classList.remove('active');
            });
            item.classList.add('active');
        })
    });
}

let changeButtonActive = () => {
    let items = document.querySelectorAll('.kind-button');
    items.forEach((item)=>{
        item.addEventListener('click',() => {
            items.forEach(item => {
                item.classList.remove('active-button');
            });
            item.classList.add('active-button');
        })
    });
}

</script>

<template>
    <div class="goods-details-area">
        <div class="select-area" @mouseover="changeActive()">
            <p class="select-words" @click="changeSelectGoodsEvaluation()">宝贝评价</p>
        </div>
        <div class="goods-evaluation">
            <div class="evaluation-kind-area" @mouseover="changeButtonActive()">
                <button class="kind-button active-button">全部</button>
                <button class="kind-button">好评</button>
                <button class="kind-button">中评</button>
                <button class="kind-button">差评</button>
            </div>
            <div class="evaluation-single">
                <div class="customer-info">
                    <img class="customer-actor" v-bind:src="customerInfo.portraitPath">
                    <p class="customer-name">{{ customerInfo.nickname }}</p>
                    <p class="evaluation-date">{{ evaluation.date }}</p>
                </div>
                <div class="level-and-type">
                    <p class="evaluation-level">{{ evaluation.evaluationLevel }}</p>
                    <p class="goods-description">{{  }}</p>
                </div>
                <div class="evaluation-content">{{ evaluation.evaluationContent }}</div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.goods-details-area {
    width: 1200px;
    background-color: white;
    margin-left: 170px;
    border-bottom-left-radius: 20px;
    border-bottom-right-radius: 20px;
    padding-bottom: 50px;
    overflow: hidden;
}
.select-area {
    width: 1200px;
    height: 50px;
    border-bottom: 1px rgb(230, 230, 230) solid;
}
.select-words {
    width: 75px;
    height: 40px;
    font-size: 18px;
    text-align: center;
    cursor: pointer;
    padding-top: 8px;
    margin-left: 20px;
    margin-right: 10px;
    border-bottom: 3px orangered solid;
    float: left;
}
.goods-details {
    width: 1200px;
    padding-top: 50px;
}
.goods-details-picture {
    width: 500px;
    height: 600px;
    margin-left: 350px;
}
.goods-evaluation {
    width: 1200px;
    padding-top: 20px;
}
.evaluation-kind-area {
    width: 1200px;
    height: 60px;
}
.kind-button {
    width: 70px;
    height: 36px;
    border-radius: 18px;
    border: none;
    font-size: 16px;
    background-color: rgb(245, 245, 245);
    cursor: pointer;
    margin-left: 20px;
    float: left;
}
.kind-button:not(.active-button):hover {
    background-color: rgb(230, 230, 230);
}
.active-button {
    background-color: orangered;
    color: white;
}
.evaluation-single {
    width: 1160px;
    border-bottom: 1px rgb(226, 226, 226) solid;
    overflow: hidden;
    margin-left: 20px;
}
.customer-info {
    width: 500px;
    height: 50px;
}
.customer-actor {
    width: 50px;
    height: 50px;
    border-radius: 25px;
    float: left;
}
.customer-name {
    width: 200px;
    height: 25px;
    font-size: 18px;
    font-weight: bolder;
    margin-left: 60px;
    padding-top: 3px;
}
.evaluation-date {
    width: 100px;
    height: 18px;
    font-size: 13px;
    color: rgb(154, 154, 154);
    margin-left: 60px;
    margin-top: 2px;
}
.level-and-type {
    width: 500px;
    height: 30px;
}
.evaluation-level {
    width: 50px;
    height: 20px;
    font-size: 14px;
    color: rgb(69, 69, 69);
    margin-left: 30px;
    margin-top: 10px;
    float: left;
}
.goods-description {
    width: 300px;
    height: 20px;
    font-size: 14px;
    color: rgb(111, 111, 111);
    margin-left: 10px;
    margin-top: 10px;
    float: left;
}
.evaluation-content {
    width: 1000px;
    margin-top: 10px;
    padding-bottom: 20px;
    margin-left: 10px;
}
</style>