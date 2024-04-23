import { reactive } from 'vue'

//登录和注册
let loginAndRegister = reactive({
    username : '',
    password : '',
    verifyCode : '',
})
//订单状态
let orderStatus = reactive({
    awaitingPayment : '',
    awaitingShipment : '',
    awaitingDelivery : '',
    awaitingEvaluation : '',
})
//顾客基本信息表
let customerInfo = reactive({
    customerId : '',
    nickname : '',
    gender : '',
    birthday : '',
    description : '',
    telephone : '',
    email : '',
    portraitPath : 'http://localhost:5173/src/assets/unlogged-avatar.png',
})
//购物车表
let shoppingCart = reactive({
    shoppingCartId : '',
    customerId : '',
    goodsId : '',
    number : '',
})
//商品表
let goods = reactive({
    goodsId : '',
    storeId : '',
    goodsName : '自行车',
    goodsType : '',
    goodsInventory : '',
    salesVolume : '625',
    goodsCoverPath : '',
})
//收货信息表
let receivingInfo = reactive({
    receivingId : '',
    customerId : '',
    receiverName : '',
    area : '',
    detailAddress : '',
    telephone : '',
    isDefault : '',
})
//订单表
let order = reactive({
    orderId : 10001,
    goodsId : 1001,
    customerId : '',
    receivingId : '',
    orderDate : '2023-12-25',
    goodsName : '捷安特自行车',
    goodsDescription : '27.5寸, 24速',
    goodsPrice : '125.5',
    number : 1,
    pay : 199.9,
    orderStatus : '待评价',
})
//评价表
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
//消息表
let message = reactive({
    messageId : 1001,
    storeId : 1001,
    customerId : '',
    senderId : 1001,
    messageContent : '您好，这里是天天特卖工厂店，请问有什么需求。',
    time : '2023-12-27T15:35:40',
});
//店铺表
let storeInfo = reactive({
    storeId : '',
    storeName : '',
    openingDate : '',
    portraitPath : '',
    storeCoverPicture : '',
})
//历史记录表
let history = reactive({
    historyId : 1001,
    customerId : '',
    goodsId : 1001,
    time : '2023-12-25',
})
//退款售后表
let refund = reactive({
    refundId : 1001,
    orderId : 10001,
    customerId : '',
    time: '2023-12-25',
    service : '仅退款',
    status : '已完成',
    reason : '',
    details : '',
})
//售后进展表
let refundProgress = reactive({
    refundId : '',
    stepOneTime : '2023-12-27T15:35:40',
    stepTwoTime : '',
    stepThreeTime : '',
    stepFourTime : '',
    stepFiveTime : '',
    stepSixTime : '',
})
//店铺评分表
let storeScore = reactive({
    sellerId : 1001,
    productDescription : '5.0',
    sellerService : '4.9',
    logisticsService : '4.8',
})





export {
    loginAndRegister,
    orderStatus,
    customerInfo,
    shoppingCart,
    goods,
    receivingInfo,
    order,
    evaluation,
    message,
    storeInfo,
    history,
    refund,
    refundProgress,
    storeScore,
}