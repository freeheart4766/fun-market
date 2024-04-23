<script setup>
import { refund, order } from '../../../utils/pojo.js'
import { useRouter } from 'vue-router';

let router = useRouter();

let toRefundInfo = () => {
    router.push('/personal/refundInfo');
}

</script>

<template>
    <div id="refund-area">
        <div id="refund-title">
            <div class="goods-description refund-title-same">宝贝描述</div>
            <div class="refund-day refund-title-same">申请时间</div>
            <div class="refund-service refund-title-same">服务类型</div>
            <div class="refund-status refund-title-same">当前状态</div>
            <div class="refund-operation refund-title-same">操作</div>
        </div>
        <div id="goods-area">
            <el-scrollbar height="500px" always="true">
                <div class="refund-single">
                    <div class="refund-info">
                        <p class="info-id">售后号: {{ refund.refundId }}</p>
                        <p class="info-id">订单号: {{ refund.orderId }}</p>
                    </div>
                    <div class="goods-description refund-same">
                        <img class="goods-description-picture" v-bind:src="goodsShowPicture.pitcureOnePath">
                        <p class="goods-name goods-info">{{ order.goodsName }}</p>
                        <p class="goods-type goods-info">{{ order.goodsDescription }}</p>
                    </div>
                    <div class="refund-day refund-same">
                        <p class="refund-data-same">{{ refund.time }}</p>
                    </div>
                    <div class="refund-service refund-same">
                        <p class="refund-data-same">{{ refund.service }}</p>
                    </div>
                    <div class="refund-status refund-same">
                        <p class="refund-data-same">{{ refund.status }}</p>
                    </div>
                    <div class="refund-operation refund-same">
                        <p class="refund-data-same refund-single-opertion" @click="toRefundInfo()">查看</p>
                    </div>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>

<style scoped>
#refund-area {
    width: 830px;
    height: 590px;
    background-color: white;
    margin-left: 20px;
    margin-top: 20px;
    padding-top: 20px;
    border-radius: 10px;
}
#refund-title {
    width: 790px;
    height: 50px;
    margin-left: 20px;
    background-color: rgb(225, 221, 221);
}
.refund-title-same {
    height: 40px;
    text-align: center;
    font-size: 18px;
    padding-top: 10px;
    float: left;
}
.goods-description {
    width: 250px;
}
.refund-day {
    width: 140px;
}
.refund-service {
    width: 140px;
}
.refund-status {
    width: 136px;
}
.refund-operation {
    width: 120px;
}
#goods-area {
    width: 790px;
    height: 500px;
    margin-left: 20px;
    margin-top: 20px;
}
.refund-single {
    width: 790px;
    height: 121px;
    background-color: rgb(245, 245, 245);
    margin-bottom: 20px;
}
.refund-info {
    width: 790px;
    height: 30px;
    border-bottom: 1px rgb(220, 215, 215) solid;
}
.info-id {
    width: 120px;
    height: 25px;
    font-size: 14px;
    margin-left: 10px;
    padding-top: 5px;
    float: left;
}
.refund-same {
    height: 90px;
    border-right: 1px rgb(220, 215, 215) solid;
    float: left;
}
.goods-description-picture {
    width: 80px;
    height: 80px;
    margin-left: 5px;
    margin-top: 5px;
    float: left;
}
.goods-info {
    width: 120px;
    margin-left: 10px;
    margin-top: 10px;
    font-size: 14px;
    overflow: hidden;
    float: left;
}
.goods-name {
    height: 40px;
}
.goods-type {
    height: 20px;
}
.refund-data-same {
    text-align: center;
    font-size: 18px;
    margin-top: 30px;
    overflow: hidden;
}
.refund-single-opertion {
    cursor: pointer;
}
.refund-single-opertion:hover {
    color: rgb(254, 88, 27);
}
</style>