<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { refund, order, refundProgress } from '../../../utils/pojo.js'

let router = useRouter();

let toRefund = () => {
    router.push('/personal/refund');
}

let process = ['买家发起售后服务申请','卖家审核售后服务申请','买家寄回商品后上传物流信息','商家处理售后服务','用户确认售后服务结果','已完成'];
let progressNumber = ref(1);
</script>

<template>
    <div class="refund-info-area">
        <div class="title">售后详情:</div>
        <div class="router-return" @click="toRefund()">返回</div>
        <div class="refund-true-area">
            <div class="progress-area">
                <el-steps v-bind:space="80" v-bind:active="progressNumber" finish-status="success" direction="vertical">
                    <el-step v-bind:title="process[0]" v-bind:description="refundProgress.stepOneTime.replace('T',' ')" />
                    <el-step v-bind:title="process[1]" v-bind:description="refundProgress.stepTwoTime.replace('T',' ')" />
                    <el-step v-bind:title="process[2]" v-bind:description="refundProgress.stepThreeTime.replace('T',' ')" />
                    <el-step v-bind:title="process[3]" v-bind:description="refundProgress.stepFourTime.replace('T',' ')" />
                    <el-step v-bind:title="process[4]" v-bind:description="refundProgress.stepFiveTime.replace('T',' ')" />
                    <el-step v-bind:title="process[5]" v-bind:description="refundProgress.stepSixTime.replace('T',' ')" />
                </el-steps>
            </div>
            <div class="info-area">
                <div class="line-top">
                    <p class="title-top">商品信息 :</p>
                    <div class="goods-info">
                        <img class="goods-picture" v-bind:src="goodsShowPicture.pitcureOnePath" >
                        <p class="goods-name goods-same">{{ order.goodsName }}</p>
                        <p class="goods-description goods-same">{{ order.goodsDescription }}</p>
                    </div>
                </div>
                <div class="line-single">
                    <p class="line-title">售后号 :</p>
                    <p class="lint-content">{{ refund.refundId }}</p>
                </div>
                <div class="line-single">
                    <p class="line-title">订单号 :</p>
                    <p class="lint-content">{{ refund.orderId }}</p>
                </div>
                <div class="line-single">
                    <p class="line-title">申请时间 :</p>
                    <p class="lint-content">{{ refund.time }}</p>
                </div>
                <div class="line-single">
                    <p class="line-title">服务类型 :</p>
                    <p class="lint-content">{{ refund.service }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.refund-info-area {
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
.refund-true-area {
    width: 600px;
    height: 400px;
    margin-left: 100px;
    margin-top: 100px;
}
.progress-area, .info-area {
    width: 300px;
    height: 400px;
    float: left;
}
.line-top {
    width: 310px;
    height: 80px;
    margin-top: 20px;
    border: 2px rgb(218, 215, 215) solid;
}
.title-top {
    width: 100px;
    height: 55px;
    font-size: 18px;
    border-right: 2px rgb(218, 215, 215) solid;
    padding-top: 25px;
    padding-left: 10px;
    float: left;
}
.goods-info {
    width: 195px;
    height: 80px;
    float: left;
}
.goods-picture {
    width: 80px;
    height: 80px;
    float: left;
}
.goods-same {
    width: 100px;
    font-size: 13px;
    margin-left: 10px;
    margin-top: 10px;
    overflow: hidden;
    float: left;
}
.goods-name {
    height: 35px;
}
.goods-description {
    height: 15px;
}
.line-single {
    width: 310px;
    height: 60px;
    border-left: 2px rgb(218, 215, 215) solid;
    border-right: 2px rgb(218, 215, 215) solid;
    border-bottom: 2px rgb(218, 215, 215) solid;
}
.line-title {
    width: 100px;
    height: 40px;
    font-size: 18px;
    border-right: 2px rgb(218, 215, 215) solid;
    padding-top: 20px;
    padding-left: 10px;
    float: left;
}
.lint-content {
    width: 150px;
    font-size: 17px;
    margin-top: 20px;
    margin-left: 10px;
    float: left;
}
</style>