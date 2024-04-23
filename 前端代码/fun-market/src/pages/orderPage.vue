<script setup>
import Top from '../components/top.vue'
import { onMounted, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { defineSignId } from '../store/store.js'
import { axiosInstance } from '../utils/request';
import { success } from '../utils/notification';

let router = useRouter();
let defineSignIds = defineSignId();
let itemList = ref();
let addressShow = ref(0);
let area = ref();
let goodsPicturePath = ref();

let receivingInfo = reactive({
    receivingId : '',
    customerId : '',
    receiverName : '',
    area : '',
    detailAddress : '',
    telephone : '',
    isDefault : '',
})
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
//前往商品信息页
let toGoods = () => {
    router.push('/goods');
}
//前往首页
let toHome = () => {
    router.push('/');
}
//将所有的地址信息展示出来
let selectAddress = () => {
    addressShow.value = 1;
}
//将新地址进行展示
let changeAddress = (receivingId) => {
    itemList.value.forEach((item) => {
        if(item.receivingId == receivingId){
            order.receivingId = item.receivingId;
            receivingInfo.receiverName = item.receiverName;
            receivingInfo.area = item.area;
            receivingInfo.detailAddress = item.detailAddress;
            receivingInfo.telephone = item.telephone;
            area.value = receivingInfo.area.replaceAll(',','');
            addressShow.value = 0;
        }
    });
}
//获取商品信息
let getGoods = () => {
    order.goodsId = defineSignIds.getGoodsId;
    axiosInstance.get(`/goods/getGoods/${order.goodsId}`)
    .then((response) => {
        order.goodsName = response.data.data.goodsName;
        order.goodsPicturePath = response.data.data.goodsCoverPath;
        getPicture(order.goodsPicturePath);
    })
}
//获取图片
let getPicture = (picturePath) => {
    let index = picturePath.lastIndexOf('\\');
    picturePath = picturePath.substring(index + 1);
    axiosInstance.get(`/system/getPicture/${picturePath}`,{responseType:'blob'})
    .then((response) => {
        goodsPicturePath.value = URL.createObjectURL(new Blob([response.data]));
    })
    .catch((errors) => {
        console.log(errors);    
    });
}
//获取商品款式信息
let getSingleTypes = () => {
    let goodsSingleTypesId = defineSignIds.getGoodsSingleTypesId;
    axiosInstance.get(`/goodsSingleTypes/getSingleTypes/${goodsSingleTypesId}`)
    .then((response) => {
        order.goodsSingleTypeName = response.data.data.typesName;
        order.goodsPrice = response.data.data.goodsPrice;
        order.pay = order.goodsPrice;
    })
}
//获取所有发地址信息
let getAllReceivingInfo = () => {
    axiosInstance.get('/receivingInfo/getAllReceivingInfo')
    .then((response) => {
        itemList.value = response.data.data;
        itemList.value.forEach((item) => {
            if(item.isDefault == '是'){
                showDefaultAddress(item);
            }
        });
    })
}
//展示默认地址，其余可供选择
let showDefaultAddress = (item) => {
    order.receivingId = item.receivingId;
    receivingInfo.receiverName = item.receiverName;
    receivingInfo.area = item.area;
    receivingInfo.detailAddress = item.detailAddress;
    receivingInfo.telephone = item.telephone;
    area.value = receivingInfo.area.replaceAll(',','');
}

//当修改数量时，自动修改商品总价
let changeNumber = () => {
    if(order.number == null){
        order.number = 1;
    }
    order.pay = order.goodsPrice * order.number;
}
//提交订单信息
let addOrder = () => {
    axiosInstance.post('/order/addOrder',order)
    .then((response) => {
        if(response.data.code == 200){
            success('提交','首页',toHome())
        }
    })
}
onMounted(() => {
    getGoods();
    getSingleTypes();
    getAllReceivingInfo();
})
</script>

<template>
    <div>
        <Top></Top>
        <div class="order-area">
            <p class="page-title">确认订单信息</p>
            <button class="cancel-buy" @click="toGoods()">取消购买</button>
            <div class="goods-info order-same">
                <p class="order-title order-goods">商品信息 :</p>
                <img class="goods-picture" v-bind:src="goodsPicturePath">
                <div class="goods-infomation">
                    <p class="goods-name">{{ order.goodsName }}</p>
                    <p class="goods-description">{{ order.goodsSingleTypeName }}</p>
                </div>
            </div>
            <div class="order-same line-same">
                <p class="order-title order-line">单价 :</p>
                <p class="order-words">￥{{ order.goodsPrice }}</p>
            </div>
            <div class="order-same line-same">
                <p class="order-title order-line">数量 :</p>
                <el-input-number class="goods-number" v-model="order.number" v-bind:min="1" @change="changeNumber()"/>
            </div>
            <div class="order-same line-same">
                <p class="order-title order-line">实付款 :</p>
                <p class="order-words pay">￥{{ order.pay }}</p>
            </div>
            <div class="order-same order-address">
                <p class="order-title order-address-p">收货地址 :</p>
                <div class="address-area">
                    <p class="address-left">{{ receivingInfo.receiverName }}</p>
                    <p class="address-left">{{ receivingInfo.telephone }}</p>
                    <p class="address-right">{{ area }}</p>
                    <p class="address-right">{{ receivingInfo.detailAddress }}</p>
                </div>
                <button class="address-button" @click="selectAddress()">更换地址</button>
            </div>
            <div class="address-show-area" v-if="addressShow == 1">
                <el-scrollbar height="280px" always="true">
                    <div class="address-show-single" v-for="address in itemList" :key="address.receivingId" @click="changeAddress(address.receivingId)">
                        <p class="address-show-single-name">{{ address.receiverName }}</p>
                        <P class="address-show-single-area">{{ address.area.replaceAll(',','') }}</P>
                        <P class="address-show-single-detail">{{ address.detailAddress }}</P>
                    </div>
                </el-scrollbar>
            </div>
            <div class="order-bottom">
                <button class="page-button" @click="addOrder()">提交订单</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
.order-area {
    width: 1000px;
    height: 600px;
    margin-left: 250px;
}
.page-title {
    width: 1000px;
    height: 35px;
    font-size: 25px;
    font-weight: bolder;
    text-align: center;
    margin-top: 30px;
}
.cancel-buy {
    position: absolute;
    top: 70px;
    right: 90px;
    width: 90px;
    height: 30px;
    cursor: pointer;
    border: none;
    border-radius: 5px;
    background-color: rgb(0, 140, 255);
    color: white;
}
.cancel-buy:hover {
    background-color: rgb(37, 157, 255);
}
.order-same {
    width: 1000px;
    border-top: 2px rgb(141, 141, 141) solid;
    border-left: 2px rgb(141, 141, 141) solid;
    border-right: 2px rgb(141, 141, 141) solid;
}
.goods-info {
    height: 100px;
    margin-top: 50px;
}
.order-title {
    width: 130px;
    height: 30px;
    font-size: 20px;
    margin-left: 20px;
    float: left;
}
.order-goods {
    padding-top: 35px;
    padding-bottom: 35px;
    border-right: 2px rgb(141, 141, 141) solid;
}
.goods-picture {
    width: 100px;
    height: 96px;
    margin-left: 10px;
    margin-top: 1px;
    float: left;
}
.goods-infomation {
    width: 600px;
    height: 100px;
    margin-left: 20px;
    float: left;
}
.goods-name, .goods-description {
    width: 600px;
    height: 25px;
    font-size: 18px;
    overflow: hidden;
    margin-top: 15px;
}
.line-same {
    height: 50px;
}
.order-line {
    padding-top: 10px;
    padding-bottom: 10px;
    border-right: 2px rgb(141, 141, 141) solid;
}
.order-words {
    width: 100px;
    height: 25px;
    font-size: 18px;
    font-weight: bolder;
    margin-top: 12px;
    margin-left: 10px;
    float: left;
}
.pay {
    color: orangered;
}
.goods-number {
    width: 150px;
    height: 35px;
    margin-top: 8px;
    margin-left: 10px;
}
.order-address {
    height: 130px;
}
.order-address-p {
    padding-top: 50px;
    padding-bottom: 50px;
    border-right: 2px rgb(141, 141, 141) solid;
}
.address-area {
    width: 600px;
    height: 130px;
    float: left;
}
.address-left {
    width: 150px;
    height: 30px;
    font-size: 18px;
    text-align: center;
    margin-left: 10px;
    margin-top: 10px;
    float: left;
}
.address-right {
    width: 310px;
    height: 30px;
    font-size: 18px;
    text-align: center;
    margin-left: 10px;
    margin-top: 10px;
    float: left;
}
.address-button {
    width: 100px;
    height: 30px;
    background-color: rgb(250, 250, 250);
    border-radius: 5px;
    border: 1px rgb(177, 176, 176) solid;
    cursor: pointer;
    margin-top: 80px;
    margin-left: 100px;
}
.address-button:hover {
    background-color: rgb(240, 240, 240);
}
.address-show-area {
    position: absolute;
    width: 400px;
    height: 280px;
    border: 2px solid rgb(240, 178, 178);
    border-radius: 10px;
    background-color: white;
    padding-top: 10px;
    padding-bottom: 10px;
    top: 180px;
    left: 800px;
}
.address-show-single {
    width: 350px;
    height: 50px;
    background-color: rgb(235, 235, 234);
    border: 2px solid rgb(235, 235, 234);
    font-size: 18px;
    text-align: center;
    margin-left: 25px;
    margin-bottom: 10px;
    cursor: pointer;
}
.address-show-single:hover {
    border: 2px solid orangered;
}
.address-show-single-name {
    width: 99px;
    height: 38px;
    border-right: 1px solid rgb(188, 188, 188);
    padding-top: 12px;
    float: left;
}
.address-show-single-area {
    width: 250px;
    height: 25px;
    border-bottom: 1px solid rgb(188, 188, 188);
    float: left;
}
.address-show-single-detail {
    width: 250px;
    height: 25px;
    float: left;
}
.order-bottom {
    border-top: 2px rgb(141, 141, 141) solid;
}
.page-button {
    width: 300px;
    height: 50px;
    background-color: rgb(255, 83, 21);
    border: none;
    border-radius: 10px;
    cursor: pointer;
    font-size: 20px;
    font-weight: bolder;
    color: white;
    margin-left: 350px;
    margin-top: 70px;
}
.page-button:hover {
    background-color: rgb(254, 97, 39);
}
</style>