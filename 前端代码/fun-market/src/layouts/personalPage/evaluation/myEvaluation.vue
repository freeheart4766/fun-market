<script setup>
import { useRouter } from 'vue-router';
import { evaluation, order } from '../../../utils/pojo.js'

let router = useRouter();

let toEvaluation = () => {
    router.push('/personal/evaluation');
}
</script>

<template>
    <div class="my-evaluation-area">
        <div class="title">评价详情:</div>
        <div class="router-return" @click="toEvaluation()">返回</div>
        <div class="evaluation-all">
            <div class="evaluation-goods">
                <p class="goods-title">宝贝信息 :</p>
                <img class="goods-picture" v-bind:src="goodsShowPicture.pitcureOnePath">
                <p class="goods-name goods-same">{{ order.goodsName }}</p>
                <p class="goods-description goods-same">{{ order.goodsDescription }}</p>
            </div>
            <div class="line-single">
                <p class="line-title">时间 :</p>
                <p class="evaluation-same">{{ evaluation.date }}</p>
            </div>
            <div class="line-single">
                <p class="line-title">综合评价 :</p>
                <p class="evaluation-same">{{ evaluation.evaluationLevel }}</p>
            </div>
            <div class="line-single">
                <p class="line-title">宝贝描述 :</p>
                <el-rate v-model="evaluation.productDescription" 
                         class="icon-star" 
                         v-bind:texts="['不满意', '待改进', '一般', '好', '非常棒']" 
                         show-text
                         disabled />
            </div>
            <div class="line-single">
                <p class="line-title">卖家服务 :</p>
                <el-rate v-model="evaluation.sellerService" 
                         class="icon-star" 
                         v-bind:texts="['不满意', '待改进', '一般', '好', '非常棒']" 
                         show-text
                         disabled />
            </div>
            <div class="line-single">
                <p class="line-title">物流服务 :</p>
                <el-rate v-model="evaluation.logisticsService" 
                         class="icon-star" 
                         v-bind:texts="['不满意', '待改进', '一般', '好', '非常棒']" 
                         show-text
                         disabled />
            </div>
            <div class="evaluation-context">
                <p class="context-title">评论内容 :</p>
                <el-popover placement="right" 
                            v-bind:width="250" 
                            trigger="hover" 
                            v-bind:content="evaluation.evaluationContent" >
                    <template #reference>
                        <p class="context">{{ evaluation.evaluationContent }}</p>
                    </template>
                </el-popover>
            </div>
        </div>
    </div>
</template>

<style scoped>
.my-evaluation-area {
    width: 830px;
    height: 590px;
    margin-left: 20px;
    margin-top: 30px;
    padding-top: 10px;
    background-color: white;
    border-radius: 10px;
}
.title {
    width: 120px;
    height: 30px;
    font-size: 25px;
    font-weight: bolder;
    margin-left: 50px;
    margin-top: 20px;
    float: left;
}
.router-return {
    width: 80px;
    height: 30px;
    background-color: rgb(37, 136, 248);
    text-align: center;
    color: white;
    border-radius: 5px;
    padding-top: 5px;
    cursor: pointer;
    margin-right: 30px;
    margin-top: 20px;
    float: right;
}
.router-return:hover {
    background-color: rgb(79, 160, 251);
}
.evaluation-all {
    width: 500px;
    height: 410px;
    margin-left: 150px;
    margin-top: 100px;
}
.evaluation-goods {
    width: 430px;
    height: 100px;
    border: 2px rgb(206, 203, 203) solid;
}
.goods-title {
    width: 100px;
    height: 65px;
    font-size: 17px;
    border-right: 2px rgb(206, 203, 203) solid;
    padding-top: 35px;
    margin-left: 10px;
    float: left;
}
.goods-picture{
    width: 100px;
    height: 100px;
    float: left;
}
.goods-same {
    width: 200px;
    margin-left: 10px;
    margin-top: 15px;
    overflow: hidden;
    float: left;
}
.goods-name {
    height: 40px;
}
.goods-description {
    height: 20px;
}
.line-single {
    width: 430px;
    height: 50px;
    border-left: 2px rgb(206, 203, 203) solid;
    border-right: 2px rgb(206, 203, 203) solid;
    border-bottom: 2px rgb(206, 203, 203) solid;
}
.line-title {
    width: 100px;
    height: 35px;
    font-size: 17px;
    border-right: 2px rgb(206, 203, 203) solid;
    padding-top: 15px;
    margin-left: 10px;
    float: left;
}
.evaluation-same {
    width: 200px;
    height: 35px;
    padding-top: 15px;
    margin-left: 5px;
    float: left;
}
.icon-star {
    margin-top: 10px;
    margin-left: 5px;
}
.evaluation-context {
    width: 430px;
    height: 60px;
    border-left: 2px rgb(206, 203, 203) solid;
    border-right: 2px rgb(206, 203, 203) solid;
    border-bottom: 2px rgb(206, 203, 203) solid;
}
.context-title {
    width: 100px;
    height: 40px;
    font-size: 17px;
    border-right: 2px rgb(206, 203, 203) solid;
    padding-top: 20px;
    margin-left: 10px;
    float: left;
}
.context {
    width: 280px;
    height: 40px;
    margin-top: 10px;
    margin-left: 5px;
    overflow: hidden;
    cursor: default;
    float: left;
}

</style>