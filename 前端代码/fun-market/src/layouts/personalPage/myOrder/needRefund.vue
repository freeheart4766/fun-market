<script setup>
import { useRouter } from 'vue-router';
import { refund, order } from '../../../utils/pojo.js'

let router = useRouter();
let toMyOrder = () => {
    router.push('/personal/myOrder');
}
let refundPurpoes = [
    {
        value: '仅退款',
        label: '仅退款',
    },
    {
        value: '退货退款',
        label: '退货退款',
    },
    {
        value: '换货',
        label: '换货',
    },
];
let refundReason = [
    {
        value: '多拍/错拍/不喜欢',
        label: '多拍/错拍/不喜欢',
    },
    {
        value: '材质与商品描述不符',
        label: '材质与商品描述不符',
    },
    {
        value: '尺寸/容量与商品描述不符',
        label: '尺寸/容量与商品描述不符',
    },
    {
        value: '做工瑕疵',
        label: '做工瑕疵',
    },
    {
        value: '颜色/款式/图案与描述不符',
        label: '颜色/款式/图案与描述不符',
    },
    {
        value: '主商品破损',
        label: '主商品破损',
    },
    {
        value: '配件破损',
        label: '配件破损',
    },
    {
        value: '卖家发错货',
        label: '卖家发错货',
    },
    {
        value: '假冒品牌',
        label: '假冒品牌',
    },
    {
        value: '质量问题',
        label: '质量问题',
    },
];
</script>

<template>
    <div class="writeRefund-area">
        <div class="title">申请售后 :</div>
        <div class="router-return" @click="toMyOrder()">返回</div>
        <div class="line-top">
            <p class="title-top">商品信息 :</p>
            <div class="goods-description">
                <img class="goods-description-picture" v-bind:src="goodsShowPicture.pitcureOnePath">
                <p class="goods-name goods-info">{{ order.goodsName }}</p>
                <p class="goods-type goods-info">{{ order.goodsDescription }}</p>
            </div>
        </div>
        <div class="line-single">
            <p class="line-title">售后类型 :</p>
            <el-select class="service-select" v-model="refund.service" placeholder="请选择" size="large">
                <el-option v-for="item in refundPurpoes" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
        </div>
        <div class="line-single">
            <p class="line-title">售后原因 :</p>
            <el-select class="service-select" v-model="refund.reason" placeholder="请选择" size="large">
                <el-option v-for="item in refundReason" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
        </div>
        <div class="line-bottom">
            <p class="title-bottom">详细描述 :</p>
            <textarea class="text-details" v-model="refund.details" placeholder="请描述具体细节" maxlength="100"></textarea>
        </div>
        <div class="line-single">
            <button class="button">提交申请</button>
        </div>
    </div>
</template>

<style scoped>
.writeRefund-area {
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
.line-top {
    width: 500px;
    height: 100px;
    margin-top: 100px;
    float: left;
}
.title-top {
    width: 100px;
    height: 40px;
    font-size: 18px;
    font-weight: bolder;
    margin-top: 40px;
    float: left;
}
.goods-description {
    width: 230px;
    height: 100px;
    border: 2px rgb(193, 187, 187) solid;
    float: left;
}
.goods-description-picture {
    width: 80px;
    height: 80px;
    margin-left: 10px;
    margin-top: 10px;
    float: left;
}
.goods-info {
    width: 120px;
    margin-left: 10px;
    margin-top: 15px;
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
.line-single {
    width: 500px;
    height: 50px;
    margin-left: 170px;
    margin-top: 10px;
    float: left;
}
.line-title {
    width: 100px;
    height: 25px;
    font-size: 18px;
    font-weight: bolder;
    margin-top: 15px;
    float: left;
}
.service-select {
    margin-top: 5px;
}
.line-bottom {
    width: 500px;
    height: 100px;
    margin-left: 170px;
    margin-top: 10px;
    float: left;
}
.text-details {
    width: 300px;
    height: 90px;
    resize: none;
    font-size: 14px;
    outline: none;
    border: 2px rgb(212, 209, 209) solid;
    padding-left: 10px;
    padding-top: 10px;
}
.title-bottom {
    width: 100px;
    height: 25px;
    font-size: 18px;
    font-weight: bolder;
    margin-top: 35px;
    float: left;
}
.button {
    width: 120px;
    height: 40px;
    background-color: rgb(255, 122, 45);
    color: white;
    font-size: 16px;
    border-radius: 5px;
    border: none;
    cursor: pointer;
    margin-left: 150px;
    margin-top: 30px;
    float: left;
}
.button:hover {
    background-color: rgb(254, 140, 73);
}
</style>