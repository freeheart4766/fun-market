<script setup>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';

let order = reactive({
    orderId : '',
    goodsId : '',
    customerId : '',
    receivingId : '',
    orderDate : '',
    number : 1,
    goodsName : '',
    goodsPicturePath : '',
    goodsSingleTypeName : '',
    goodsPrice : '',
    pay : '',
    orderStatus : '',
})

let router = useRouter();

let changeActive = () => {
    let items = document.querySelectorAll('.select-list');
    items.forEach((item)=>{
        item.addEventListener('click',() => {
            items.forEach(item => {
                item.classList.remove('active');
            });
            item.classList.add('active');
        })
    });
}

let toWriteEvaluation = () => {
    router.push('/personal/writeEvaluation');
}
let toNeedRefund = () => {
    router.push('/personal/needRefund');
}
let toGoods = () => {
    router.push('/goods');
}
let deleteOrder = () => {
}
</script>

<template>
    <div id="order-area">
        <div id="select-area" @mouseover="changeActive()">
            <div class="select-list active">所有订单</div>
            <span class="select-Separator">|</span>
            <div class="select-list">待付款</div>
            <span class="select-Separator">|</span>
            <div class="select-list">待发货</div>
            <span class="select-Separator">|</span>
            <div class="select-list">待收货</div>
            <span class="select-Separator">|</span>
            <div class="select-list">待评价</div>
        </div>
        <div id="select-input">
            <input id="input-area" placeholder="输入商品标题或订单号">
            <div id="select-button">订单搜索</div>
        </div>
        <div id="order-title">
            <div class="title-single order-goods">宝贝</div>
            <div class="title-single order-price">单价</div>
            <div class="title-single order-number">数量</div>
            <div class="title-single order-pay">实付款</div>
            <div class="title-single order-status">订单状态</div>
            <div class="title-single order-operation">操作</div>
        </div>
        <div id="order-goods-area">
            <el-scrollbar height="410px" always="true">
                <div class="order-single">
                    <div class="order-single-info">
                        <label class="select-single-label">
                            <div class="select-single">
                                <input type="checkbox" class="select-input">
                            </div>
                        </label>
                        <div class="order-date order-same">
                            {{ order.orderDate }}
                        </div>
                        <div class="order-id order-same">
                            订单号：{{ order.orderId }}
                        </div>
                        <div class="order-store order-same">
                            <P>店铺：{{ storeInfo.storeName }}</P>
                        </div>
                    </div>
                    <div class="order-goods order-single-same">
                        <img v-bind:src="order.goodsPicturePath" class="goods-image" @click="toGoods()">
                        <div class="order-description description-content">
                            {{ order.goodsName }}
                        </div>
                        <div class="order-description description-type">
                            {{ order.goodsDescription }}
                        </div>
                    </div>
                    <div class="order-price order-single-same">
                        <p>￥{{ order.goodsPrice }}</p>
                    </div>
                    <div class="order-number order-single-same">
                        <p>{{ order.number }}</p>
                    </div>
                    <div class="order-pay order-single-same">
                        <p>￥{{ order.pay }}</p>
                    </div>
                    <div class="order-status order-single-same">
                        <p>{{ order.orderStatus }}</p>
                    </div>
                    <div class="order-operation order-single-other">
                        <div class="confirm-button" v-if="order.orderStatus == '待收货'">确认收货</div>
                        <div class="confirm-button" v-if="order.orderStatus == '待评价'" @click="toWriteEvaluation()">去评价</div>
                        <el-popconfirm  title="确定要删除此订单吗?"
                                        confirm-button-text="否"
                                        cancel-button-text="是"
                                        confirm-button-type="danger"
                                        width="180"
                                        @confirm="deleteOrder()" >
                            <template #reference>
                                <p class="operation-button" v-if="order.orderStatus == '已完成'">删除订单</p>
                            </template>
                        </el-popconfirm>
                        <p class="operation-button" @click="toNeedRefund()">退款/售后</p>
                    </div>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>

<style scoped>
#order-area {
    width: 830px;
    background-color: white;
    margin-left: 20px;
    margin-top: 20px;
    padding-bottom: 10px;
    border-radius: 10px;
}
#select-area {
    width: 790px;
    height: 35px;
    margin-left: 20px;
    padding-top: 20px;
}
.select-list {
    width: 100px;
    height: 35px;
    font-size: 18px;
    font-weight: bolder;
    text-align: center;
    cursor: pointer;
    float: left;
}
.select-list:hover {
    color: rgb(255, 39, 39);
}
.select-Separator {
    color: rgb(193, 189, 189);
    float: left;
}
.active {
    border-bottom: 3px red solid;
    color: rgb(255, 39, 39);
}
#select-input {
    width: 290px;
    height: 30px;
    margin-left: 20px;
    margin-top: 20px;
    padding-top: 5px;
    border: 1px black solid;
}
#input-area {
    width: 200px;
    height: 25px;
    border: none;
    outline: none;
    padding-left: 10px;
    float: left;
}
#select-button {
    width: 70px;
    height: 22px;
    font-size: 13px;
    text-align: center;
    background-color: rgb(235, 230, 230);
    border: 1px rgb(163, 162, 162) solid;
    padding-top: 4px;
    margin-left: 5px;
    cursor: pointer;
    float: left;
}
#order-title {
    width: 790px;
    height: 50px;
    background-color: rgb(234, 228, 228);
    margin-left: 20px;
    margin-top: 20px;
}
.title-single {
    height: 35px;
    text-align: center;
    padding-top: 15px;
    float: left;
}
.order-goods {
    width: 295px;
}
.order-price {
    width: 100px;
}
.order-number {
    width: 80px;
}
.order-pay {
    width: 100px;
}
.order-status {
    width: 100px;
}
.order-operation {
    width: 110px;
}
#order-goods-area {
    height: 400px;
    margin-bottom: 8px;
    margin-top: 10px;
}
.order-single {
    width: 790px;
    height: 171px;
    background-color: rgb(245, 245, 245);
    margin-left: 20px;
    margin-top: 20px;
    border-radius: 5px;
}
.order-single-info {
    height: 40px;
    border-bottom: 1px rgb(220, 215, 215) solid;
}
.select-single {
    width: 50px;
    height: 40px;
    float: left;
}
.select-input {
    width: 18px;
    height: 18px;
    margin-top: 12px;
    margin-left: 15px;
    cursor: pointer;
}
.select-input:checked::after {
    content: "✓";
    color: red;
    font-size: 10px;
    padding-left: 3px;
    padding-bottom: 1px;
    background-color: white;
    border:2px red solid;
    border-radius:2px;
    display: block;
    height: 14px;
}
.select-single-label {
    cursor: pointer;
}
.order-same {
    height: 35px;
    padding-top: 10px;
    padding-left: 20px;
    float: left;
}
.order-date {
    width: 120px;
}
.order-id {
    width: 200px;
}
.order-store {
    width: 200px;
}
.order-single-same {
    height: 120px;
    border-right: 1px rgb(220, 215, 215) solid;
    float: left;
}
.order-single-other {
    height: 120px;
    float: left;
}
.goods-image {
    width: 100px;
    height: 100px;
    cursor: pointer;
    margin-left: 15px;
    margin-top: 10px;
    float: left;
}
.order-description {
    width: 150px;
    margin-left: 10px;
    margin-top: 20px;
    font-size: 14px;
    overflow: hidden;
    float: left;
}
.description-content {
    height: 40px;
}
.description-type {
    height: 20px;
}
.order-price p, .order-pay p{
    margin-top: 50px;
    margin-left: 15px;
}
.order-number p {
    margin-top: 50px;
    margin-left: 35px;
}
.order-status p {
    margin-top: 50px;
    margin-left: 25px;
}
.confirm-button {
    width: 90px;
    height: 30px;
    background-color: orangered;
    color: white;
    text-align: center;
    padding-top: 5px;
    border-radius: 10px;
    margin-top: 10px;
    margin-left: 10px;
    cursor: pointer;
}
.confirm-button:hover {
    background-color: rgb(255, 100, 44);
}
.operation-button {
    text-align: center;
    margin-top: 10px;
    cursor: pointer;
}
.operation-button:hover {
    color: orangered;
}
</style>