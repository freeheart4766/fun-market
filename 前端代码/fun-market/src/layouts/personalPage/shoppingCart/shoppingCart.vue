<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router';

let router = useRouter();
let isEmpty = ref(false);
let shoppingCartNumber = ref();

let shoppingCart = reactive({
    shoppingCartId : '',
    customerId : '',
    goodsId : '',
    number : '',
})
let goodsInfo = reactive({
    storeName : '',
    goodsName : '',
    typesName : '',
    goodsCoverPath : '',
    goodsPrice : '',
    number : 1,
    pay : '',
})

let toGoods = () => {
    router.push('/goods');
}

//防止数量为空
let changeTotalPrice = () => {
    if(shoppingCart.number == null){
        shoppingCart.number = 1;
    }
}
</script>

<template>
    <div id="shoppingCart-area">
        <div id="shoppingCart-empty"  v-if="isEmpty == true">
            <div id="shoppingCart-icon-area">
                <svg t="1705739025060" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2650">
                    <path d="M848 712H318.4c-25.6 0-46.4-19.2-48-41.6L240 201.6c-1.6-25.6-16-46.4-38.4-57.6l-48-20.8c-12.8-4.8-25.6 0-32 12.8s0 25.6 12.8 32l46.4 20.8c6.4 3.2 11.2 9.6 11.2 17.6l28.8 468.8c3.2 48 46.4 86.4 96 86.4H848c12.8 0 24-11.2 24-24s-11.2-25.6-24-25.6z" p-id="2651" fill="rgb(255, 100, 44)"></path>
                    <path d="M884.8 265.6c-14.4-16-35.2-25.6-57.6-25.6H337.6c-12.8 0-24 11.2-24 24s11.2 24 24 24h489.6c8 0 16 3.2 20.8 9.6 4.8 6.4 8 14.4 8 22.4l-38.4 211.2v1.6c-1.6 14.4-12.8 24-27.2 25.6l-420.8 32c-12.8 1.6-22.4 12.8-22.4 25.6 1.6 12.8 11.2 22.4 24 22.4h1.6l419.2-32c36.8-3.2 67.2-30.4 70.4-67.2l38.4-212.8v-1.6c4.8-20.8-1.6-43.2-16-59.2z" p-id="2652" fill="rgb(255, 100, 44)"></path>
                    <path d="M305.6 856m-56 0a56 56 0 1 0 112 0 56 56 0 1 0-112 0Z" p-id="2653" fill="rgb(255, 100, 44)"></path>
                    <path d="M753.6 856m-56 0a56 56 0 1 0 112 0 56 56 0 1 0-112 0Z" p-id="2654" fill="rgb(255, 100, 44)"></path>
                </svg>
            </div>
            <div id="text-area">
                <p id="empty-description">购物车空空如也，去看看心仪的商品吧~</p>
                <router-link to="/" id="router-home">去购物</router-link>
            </div>
        </div>
        <div id="shoppingCart-occupied" v-else>
            <div id="cart-title">购物车 (全部<span>{{ shoppingCartNumber }}</span>)</div>
            <hr id="hr-top">
            <div id="operation">
                <div class="goods-title-single" id="goods-description-title">
                    <p class="title-content">商品信息</p>
                </div>
                <div class="goods-title-single" id="goods-price-title">
                    <p class="title-content">单价</p>
                </div>
                <div class="goods-title-single" id="goods-number-title">
                    <p class="title-content">数量</p>
                </div>
                <div class="goods-title-single" id="goods-totalPrice-title">
                    <p class="title-content">金额</p>
                </div>
                <div class="goods-title-single" id="goods-operation-title">
                    <p class="title-content">操作</p>
                </div>
            </div>
            <div id="goods-area">
                <el-scrollbar height="387px" always="true">
                    <div class="goods-single">
                        <div class="store-single">
                            <p class="store-title">店铺 :</p>
                            <p class="store-title">{{ goodsInfo.storeName }}</p>
                        </div>
                        <div class="goods-description goods-single-content">
                            <img v-bind:src="goodsInfo.goodsCoverPath" class="goods-img" @click="toGoods()">
                            <P class="description-content">{{ goodsInfo.goodsName }}</P>
                            <p class="description-type">{{ goodsInfo.typesName }}</p>
                        </div>
                        <div class="goods-price goods-single-content">
                            <p>￥{{ goodsInfo.goodsPrice }}</p>
                        </div>
                        <div class="goods-number goods-single-content">
                            <el-input-number class="number-update" v-model="shoppingCart.number" v-bind:min="1" @change="changeTotalPrice()"/>
                        </div>
                        <div class="goods-totalPrice goods-single-content">
                            <p >￥{{ goodsInfo.pay }}</p>
                        </div>
                        <div class="goods-operation goods-single-content-right">
                            <div class="operation-delete">删除</div>
                        </div>
                    </div>
                </el-scrollbar>
            </div>
            <hr id="hr-bottom">
            <div class="operation-bottom">
                <div id="price-total">
                    <p>合计 :</p>
                    <span>{{ allprice }}</span>
                </div>
                <div class="settlement">
                    <p>结算</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
#shoppingCart-area {
    width: 830px;
    background-color: white;
    margin-left: 20px;
    margin-top: 20px;
    border-radius: 10px;
}
#shoppingCart-empty {
    height: 610px;
}
#shoppingCart-icon-area {
    width: 150px;
    height: 150px;
    padding-top: 200px;
    margin-left: 150px;
    float: left;
}
#text-area {
    padding-top: 250px;
}
#empty-description {
    font-size: 20px;
    margin-bottom: 10px;
}
#router-home {
    text-decoration: none;
}
#router-home:hover {
    color: rgb(255, 141, 48);
}
#cart-title {
    width: 500px;
    height: 50px;
    margin-left: 20px;
    padding-top: 20px;
    font-size: 20px;
}
#cart-title span {
    font-weight: bolder;
}
#hr-top {
    border: 1px rgb(220, 220, 220) solid;
}
#operation {
    width: 810px;
    height: 40px;
    padding-left: 80px;
}
.title-content {
    text-align: center;
    margin-top: 8px;
}
.goods-title-single {
    height: 40px;
    float: left;
}
#goods-description-title {
    width: 285px;
}
#goods-price-title {
    width: 100px;
}
#goods-number-title {
    width: 120px;
}
#goods-totalPrice-title {
    width: 100px;
}
#goods-operation-title {
    width: 120px;
}
#goods-area {
    width: 830px;
    height: 387px;
}
.goods-single {
    width: 790px;
    height: 151px;
    background-color: rgb(245, 245, 245);
    margin-left: 20px;
    margin-top: 20px;
    border-radius: 15px;
}
.store-single {
    width: 790px;
    height: 30px;
    border-bottom: 1px rgb(220, 215, 215) solid;
}
.store-title {
    padding-top: 5px;
    margin-left: 10px;
    float: left;
}
.goods-single-content {
    height: 120px;
    border-right: 1px rgb(220, 215, 215) solid;
    float: left;
}
.goods-single-content-right {
    height: 120px;
    float: left;
}
.goods-description {
    width: 285px;
}
.goods-price {
    width: 100px;
}
.goods-number {
    width: 120px;
}
.goods-totalPrice {
    width: 100px;
}
.goods-operation {
    width: 120px;
}
.goods-img {
    width: 90px;
    height: 90px;
    cursor: pointer;
    margin-top: 15px;
    margin-left: 15px;
    float: left;
}
.description-content {
    width: 145px;
    height: 40px;
    margin-left: 15px;
    margin-top: 20px;
    float: left;
    font-size: 14px;
    overflow: hidden;
}
.description-type {
    width: 145px;
    height: 20px;
    margin-left: 15px;
    margin-top: 15px;
    float: left;
    font-size: 14px;
    overflow: hidden;
}
.goods-price p {
    margin-left: 15px;
    margin-top: 45px;
}
.number-update {
    width: 110px;
    margin-left: 5px;
    margin-top: 40px;
}
.goods-totalPrice p {
    font-size: 17px;
    margin-left: 15px;
    margin-top: 45px;
    color: rgb(186, 36, 36);
}
.operation-delete {
    width: 50px;
    height: 30px;
    font-size: 18px;
    cursor: pointer;
    padding-left: 10px;
    margin-left: 30px;
    margin-top: 20px;
}
.operation-delete:hover {
    color: rgb(243, 23, 23);
}
#hr-bottom {
    border: 1px rgb(220, 220, 220) solid;
    margin-top: 30px;
}
.operation-bottom {
    width: 300px;
    height: 80px;
    margin-left: 500px;
}
#price-total {
    width: 150px;
    height: 40px;
    margin-top: 20px;
    margin-left: 30px;
    float: left;
}
#price-total p {
    margin-top: 10px;
    float: left;
}
#price-total span {
    font-size: 25px;
    color: red;
    margin-left: 10px;
    margin-top: 5px;
    float: left;
}
.settlement {
    width: 80px;
    height: 50px;
    margin-left: 15px;
    margin-top: 15px;
    border-radius: 20px;
    background-color: rgb(248, 83, 37);
    cursor: pointer;
    float: left;
}
.settlement:hover {
    background-color: rgb(250, 103, 62);
}
.settlement p {
    font-size: 18px;
    margin-left: 22px;
    margin-top: 12px;
    color: white;
}
</style>