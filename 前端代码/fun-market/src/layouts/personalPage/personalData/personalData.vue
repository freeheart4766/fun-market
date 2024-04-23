<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import HeadPictureUpload from './headPictureUpload.vue'
import { customerInfo, orderStatus } from '../../../utils/pojo.js'
import { axiosInstance } from '../../../utils/request.js'

let router = useRouter();

let dataCompleteness = ref(0);
let dataSecurity = ref(0);

let refundIncomplete = ref();

let birthdayInfo = ref(customerInfo.birthday.split(","));

//前往基本信息处
let toInfo = () => {
    window.scrollTo({  
    top: 240,  
    behavior: "smooth"  
  });
}
//前往账户安全处
let toSecurity = () => {
    window.scrollTo({  
    top: 1300,  
    behavior: "smooth"  
  });
}

let toBasicInfo = () => {
    router.push('/personal/basicInfo');
}
let toResetPassword = () => {
    router.push('/personal/resetPassword');
}
let toPayPassword = () => {
    router.push('/personal/payPassword');
}
let toAuthentication = () => {
    router.push('/personal/authentication');
}
//修改资料完整度
let checkDataCompleteness = () => {
    if(customerInfo.nickname != '' && customerInfo.nickname != null){
        dataCompleteness.value = dataCompleteness.value + 20;
    }
    if(customerInfo.gender != '' && customerInfo.gender != null){
        dataCompleteness.value = dataCompleteness.value + 15;
    }
    if(customerInfo.birthday != '' && customerInfo.birthday != null){
        dataCompleteness.value = dataCompleteness.value + 10;
    }
    if(customerInfo.description != '' && customerInfo.description != null){
        dataCompleteness.value = dataCompleteness.value + 15;
    }
    if(customerInfo.telephone != '' && customerInfo.telephone != null){
        dataCompleteness.value = dataCompleteness.value + 20;
    }
    if(customerInfo.email != '' && customerInfo.email != null){
        dataCompleteness.value = dataCompleteness.value + 20;
    } 
}
let checkDataCompletenessDelay = () => {
    setTimeout(() => checkDataCompleteness(),200)
}

let progressColors = [
    { color: 'rgb(246, 88, 88)', percentage: 40 },
    { color: 'rgb(246, 209, 88)', percentage: 80 },
    { color: 'rgb(49, 209, 17)', percentage: 100 },
]

//获取用户的基本信息
let getCustomerInfo = () => {
    axiosInstance.get('/customerInfo/getCustomerInfo')
    .then((response) => {
        customerInfo.customerId = response.data.data.customerId;
        customerInfo.nickname = response.data.data.nickname;
        customerInfo.gender = response.data.data.gender;
        customerInfo.birthday = response.data.data.birthday;
        customerInfo.description = response.data.data.description;
        customerInfo.telephone = response.data.data.telephone;
        customerInfo.email = response.data.data.email;
        birthdayInfo.value = customerInfo.birthday.split(",");
    })
    .catch((errors) => {
        console.log(errors);
    })
}
//查询售后未完成的订单数
let getRefundIncomplete = () => {
    axiosInstance.get('/refund/getRefundIncomplete')
    .then((response) => {
        if(response.data.data == '' || response.data.data == null){
            refundIncomplete.value = 0;
        }else{
            refundIncomplete.value = response.data.data;
        }
        
    })
}

onMounted(() => {
    checkDataCompletenessDelay();
    getCustomerInfo();
    getRefundIncomplete();
})
</script>

<template>
    <div>
        <div id="first-area">
            <div id="head-sculpture">
                <img v-bind:src="customerInfo.portraitPath">
            </div>
            <div id="info-summarize">
                <p id="nickname">{{ customerInfo.nickname }}<span> ({{ customerInfo.customerId }})</span></p>
                <div id="data-area">
                    <p id="data-completeness">资料完整度:</p>
                    <el-progress    class="progress" 
                                    text-inside="true" 
                                    stroke-width="20" 
                                    v-bind:percentage="dataCompleteness" 
                                    v-bind:color="progressColors" />
                    <p id="data-to" @click="toInfo()">立即完善</p>
                </div>
                <div id="security-area">
                    <p id="account-security">账户安全级别:</p>
                    <el-progress    class="progress" 
                                    text-inside="true" 
                                    stroke-width="20" 
                                    v-bind:percentage="dataSecurity" 
                                    v-bind:color="progressColors" >
                        <span v-if="dataSecurity >= 80">高</span>
                        <span v-else-if="dataSecurity >= 40">中</span>
                        <span v-else>低</span>
                    </el-progress>
                    <p id="security-to" @click="toSecurity()">立即提升</p>
                </div>
            </div>
            <div id="logistics-status-area">
                <p class="logistics-status">待付款<span class="status-number">{{ orderStatus.awaitingPayment }}</span></p><span class="separator">|</span>
                <p class="logistics-status">待发货<span class="status-number">{{ orderStatus.awaitingShipment }}</span></p><span class="separator">|</span>
                <p class="logistics-status">待收货<span class="status-number">{{ orderStatus.awaitingDelivery }}</span></p><span class="separator">|</span>
                <p class="logistics-status">待评价<span class="status-number">{{ orderStatus.awaitingEvaluation }}</span></p><span class="separator">|</span>
                <p class="logistics-status">退款/售后<span class="status-number">{{ refundIncomplete }}</span></p>
            </div>
        </div>


        <div id="second-area">
            <div class="area-title">
                <p>基本信息 :</p>
            </div>
                <div class="simple-area">
                    <p class="info-title">昵称:</p>
                    <p class="info-same-one">{{ customerInfo.nickname }}</p>
                </div>
                <div class="simple-area">
                    <p class="info-title">性别:</p>
                    <p class="info-same-one">{{ customerInfo.gender }}</p>
                </div>
                <div class="simple-area">
                    <p class="info-title">生日:</p>
                    <p class="info-same-two birthday-left">{{ birthdayInfo[0] }}</p>
                    <p class="birthday-unit">年</p>
                    <p class="info-same-two">{{ birthdayInfo[1] }}</p>
                    <p class="birthday-unit">月</p>
                    <p class="info-same-two">{{ birthdayInfo[2] }}</p>
                    <p class="birthday-unit">日</p>
                </div>
                <div class="simple-area">
                    <p class="info-title">个性签名:</p>
                    <p class="info-same-third">{{ customerInfo.description }}</p>
                </div>
                <div class="simple-area">
                    <p class="info-title">电话:</p>
                    <p class="info-same-third">{{ customerInfo.telephone }}</p>
                </div>
                <div class="simple-area">
                    <p class="info-title">邮箱:</p>
                    <p class="info-same-third">{{ customerInfo.email }}</p>
                </div>
                <button id="input-submit" @click="toBasicInfo()">修改</button>

        </div>


        <HeadPictureUpload></HeadPictureUpload>


        <div id="forth-area">
            <div class="area-title">
                <p>账户安全 :</p>
            </div>
            <div class="forth-area-simple">
                <diV class="icon-simple">
                    <svg t="1705654362032" class="security-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2458">
                        <path d="M844.8 342.109091C844.8 153.6 695.854545 0 512 0S179.2 153.6 179.2 342.109091c-74.472727 0-132.654545 60.509091-132.654545 137.309091v409.6C46.545455 963.490909 107.054545 1024 179.2 1024h663.272727c74.472727 0 132.654545-60.509091 132.654546-137.309091V479.418182c2.327273-76.8-55.854545-137.309091-130.327273-137.309091z m-730.763636 162.909091c0-51.2 41.890909-95.418182 93.090909-95.418182h609.745454c51.2 0 93.090909 41.890909 93.090909 95.418182V861.090909c0 51.2-41.890909 95.418182-93.090909 95.418182H207.127273c-51.2 0-93.090909-41.890909-93.090909-95.418182V505.018182z m663.272727-162.909091H246.690909C246.690909 193.163636 365.381818 69.818182 512 69.818182c144.290909 0 260.654545 118.690909 265.309091 262.981818v9.309091z" fill="#ffffff" p-id="2459"></path>
                        <path d="M477.090909 772.654545v81.454546c0 18.618182 16.290909 34.909091 34.909091 34.909091s34.909091-16.290909 34.909091-34.909091v-81.454546c58.181818-16.290909 100.072727-69.818182 100.072727-132.654545 0-74.472727-60.509091-137.309091-132.654545-137.309091-74.472727 0-132.654545 60.509091-132.654546 137.309091-2.327273 62.836364 37.236364 116.363636 95.418182 132.654545z m34.909091-200.145454c37.236364 0 65.163636 30.254545 65.163636 67.490909 0 37.236364-30.254545 67.490909-65.163636 67.490909s-65.163636-30.254545-65.163636-67.490909c0-37.236364 27.927273-67.490909 65.163636-67.490909z" fill="#ffffff" p-id="2460"></path>
                    </svg>
                </diV>
                <div class="description-simple">
                    <p class="title-simple">登录密码</p>
                    <div class="state-simple">
                        <svg t="1705655909719" class="state-icon" viewBox="0 0 1497 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4534">
                            <path d="M1476.470467 150.422791 688.633041 938.260217C685.780224 950.315666 679.867532 961.795951 670.45784 971.205644L637.926532 1003.736952C610.985823 1030.700667 567.273316 1030.700667 540.332607 1003.736952L533.03952 996.466872C533.016514 996.420859 532.9705 996.397852 532.924487 996.351839L500.393179 963.820531C500.347166 963.774518 500.324159 963.728504 500.278146 963.705498L19.739648 483.167C-7.20106 456.203285-7.20106 412.513784 19.739648 385.550069L52.270956 353.01876C79.234672 326.055045 122.924173 326.055045 149.887888 353.01876L581.744456 784.875329 1346.322227 20.274551C1373.285942-6.689164 1416.975443-6.689164 1443.939158 20.274551L1476.470467 52.805859C1503.434182 79.769575 1503.434182 123.459076 1476.470467 150.422791Z" p-id="4535" fill="#ffffff"></path>
                        </svg>
                    </div>
                    <p class="context-simple">定期更换密码有助于账户安全</p>
                </div>
                <div class="operate-simple" @click="toResetPassword()">
                    <p class="operate-context">修改</p>
                </div>
            </div>
            <div class="forth-area-simple">
                <diV class="icon-simple">
                    <svg t="1705657362160" class="security-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="8953">
                        <path d="M838.4 172.8c-147.2 0-281.6-128-281.6-128C544 32 531.2 25.6 512 19.2c-19.2 0-32 6.4-44.8 19.2 0 0-134.4 128-281.6 128-38.4 0-64 32-64 70.4V448c0 192 108.8 352 352 537.6 19.2 19.2 51.2 19.2 76.8 0 249.6-185.6 352-345.6 352-537.6V236.8c0-32-25.6-64-64-64z m0 281.6c0 140.8-64 288-326.4 480-262.4-192-326.4-339.2-326.4-480V243.2C358.4 243.2 512 89.6 512 89.6s153.6 147.2 326.4 147.2v217.6z" fill="#ffffff" p-id="8954"></path>
                        <path d="M390.4 300.8c-12.8 12.8-12.8 32 0 38.4L492.8 448H384c-12.8 0-25.6 19.2-25.6 32s12.8 25.6 25.6 32h115.2v57.6H384c-19.2 0-32 12.8-32 32s12.8 32 32 32h115.2v108.8c0 12.8 12.8 25.6 25.6 25.6s25.6-12.8 25.6-25.6V627.2h115.2c19.2 0 32-12.8 32-32s-12.8-32-32-32H556.8V512h115.2c19.2 0 25.6-12.8 25.6-32 0-12.8-12.8-25.6-25.6-25.6H563.2l102.4-108.8c12.8-12.8 12.8-32 0-38.4-12.8-12.8-25.6-12.8-38.4 0L524.8 403.2 428.8 300.8c-12.8-12.8-25.6-12.8-38.4 0z" fill="#ffffff" p-id="8955"></path>
                    </svg>
                </diV>
                <div class="description-simple">
                    <p class="title-simple">支付密码</p>
                    <div class="state-simple">
                        <svg t="1705655909719" class="state-icon" viewBox="0 0 1497 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4534">
                            <path d="M1476.470467 150.422791 688.633041 938.260217C685.780224 950.315666 679.867532 961.795951 670.45784 971.205644L637.926532 1003.736952C610.985823 1030.700667 567.273316 1030.700667 540.332607 1003.736952L533.03952 996.466872C533.016514 996.420859 532.9705 996.397852 532.924487 996.351839L500.393179 963.820531C500.347166 963.774518 500.324159 963.728504 500.278146 963.705498L19.739648 483.167C-7.20106 456.203285-7.20106 412.513784 19.739648 385.550069L52.270956 353.01876C79.234672 326.055045 122.924173 326.055045 149.887888 353.01876L581.744456 784.875329 1346.322227 20.274551C1373.285942-6.689164 1416.975443-6.689164 1443.939158 20.274551L1476.470467 52.805859C1503.434182 79.769575 1503.434182 123.459076 1476.470467 150.422791Z" p-id="4535" fill="#ffffff"></path>
                        </svg>
                    </div>
                    <p class="context-simple">支付所购买商品时所使用</p>
                </div>
                <div class="operate-simple">
                    <p class="operate-context" @click="toPayPassword()">设置</p>
                </div>
            </div>
            <div class="forth-area-simple">
                <diV class="icon-simple">
                    <svg t="1705657517624" class="security-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="10046">
                        <path d="M288.384 994.496c-51.2 0-96-44.8-96-96v-774.4c0-57.6 44.8-96 96-96h454.4c44.8 0 89.6 38.4 89.6 96v780.8c0 51.2-44.8 96-96 96l-448-6.4z m-38.4-89.6c0 19.2 12.8 32 32 32h454.4c19.2 0 32-12.8 32-32v-89.6h-518.4v89.6z m524.8-147.2v-544h-524.8v544h524.8z m0-608v-25.6c0-19.2-12.8-32-32-32h-454.4c-19.2 0-32 12.8-32 32v32h518.4z" fill="#ffffff" p-id="10047"></path>
                        <path d="M429.184 136.896h160c6.4 0 12.8-6.4 12.8-12.8s0-19.2-12.8-19.2h-160c-6.4 0-12.8 6.4-12.8 12.8s6.4 19.2 12.8 19.2zM454.784 879.296c0 32 25.6 57.6 57.6 57.6s57.6-25.6 57.6-57.6-25.6-57.6-57.6-57.6-57.6 25.6-57.6 57.6z" fill="#ffffff" p-id="10048"></path>
                    </svg>
                </diV>
                <div class="description-simple">
                    <p class="title-simple">绑定手机</p>
                    <div class="state-simple">
                        <svg t="1705655909719" class="state-icon" viewBox="0 0 1497 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4534">
                            <path d="M1476.470467 150.422791 688.633041 938.260217C685.780224 950.315666 679.867532 961.795951 670.45784 971.205644L637.926532 1003.736952C610.985823 1030.700667 567.273316 1030.700667 540.332607 1003.736952L533.03952 996.466872C533.016514 996.420859 532.9705 996.397852 532.924487 996.351839L500.393179 963.820531C500.347166 963.774518 500.324159 963.728504 500.278146 963.705498L19.739648 483.167C-7.20106 456.203285-7.20106 412.513784 19.739648 385.550069L52.270956 353.01876C79.234672 326.055045 122.924173 326.055045 149.887888 353.01876L581.744456 784.875329 1346.322227 20.274551C1373.285942-6.689164 1416.975443-6.689164 1443.939158 20.274551L1476.470467 52.805859C1503.434182 79.769575 1503.434182 123.459076 1476.470467 150.422791Z" p-id="4535" fill="#ffffff"></path>
                        </svg>
                    </div>
                    <p class="context-simple">轻松找回密码，验证身份</p>
                </div>
                <div class="operate-simple" @click="toBasicInfo()">
                    <p class="operate-context">绑定</p>
                </div>
            </div>
            <div class="forth-area-simple">
                <diV class="icon-simple">
                    <svg t="1705657604859" class="security-icon-email" viewBox="0 0 1445 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="11177">
                        <path d="M1324.757233 0H121.075351C53.732292 0.542118-0.419238 57.163294 0.002409 126.494118v771.072C0.002409 967.439059 54.214174 1024 121.015115 1024H1324.576527C1391.618409 1024 1445.649468 967.499294 1445.649468 897.566118v-771.011765c0-69.752471-54.031059-126.494118-121.012706-126.494118L1324.757233 0z m-28.912941 90.593882l-480.075294 444.656942c-41.863529 39.032471-72.884706 58.428235-92.882824 58.428235-19.998118 0-51.079529-19.395765-92.882823-58.428235l-480.075295-444.656942H1295.844292zM89.993939 869.436235V172.754824L464.898409 516.216471l-374.964705 353.28h0.060235z m63.006117 63.969883l376.350118-349.06353 61.680941 56.500706c35.779765 32.647529 79.992471 49.513412 131.855059 49.513412 52.103529 0 96.015059-16.745412 131.915294-49.513412l61.680941-56.500706 376.350118 349.06353H153.000056z m1202.838589-63.909647L980.873939 516.216471l374.964706-343.521883v696.741647z" fill="#ffffff" p-id="11178"></path>
                    </svg>
                </diV>
                <div class="description-simple">
                    <p class="title-simple">验证邮箱</p>
                    <div class="state-simple">
                        <svg t="1705655909719" class="state-icon" viewBox="0 0 1497 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4534">
                            <path d="M1476.470467 150.422791 688.633041 938.260217C685.780224 950.315666 679.867532 961.795951 670.45784 971.205644L637.926532 1003.736952C610.985823 1030.700667 567.273316 1030.700667 540.332607 1003.736952L533.03952 996.466872C533.016514 996.420859 532.9705 996.397852 532.924487 996.351839L500.393179 963.820531C500.347166 963.774518 500.324159 963.728504 500.278146 963.705498L19.739648 483.167C-7.20106 456.203285-7.20106 412.513784 19.739648 385.550069L52.270956 353.01876C79.234672 326.055045 122.924173 326.055045 149.887888 353.01876L581.744456 784.875329 1346.322227 20.274551C1373.285942-6.689164 1416.975443-6.689164 1443.939158 20.274551L1476.470467 52.805859C1503.434182 79.769575 1503.434182 123.459076 1476.470467 150.422791Z" p-id="4535" fill="#ffffff"></path>
                        </svg>
                    </div>
                    <p class="context-simple">保障账户安全，轻松找回密码</p>
                </div>
                <div class="operate-simple" @click="toBasicInfo()">
                    <p class="operate-context">验证</p>
                </div>
            </div>
            <div class="forth-area-simple">
                <diV class="icon-simple">
                    <svg t="1705657858535" class="security-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="12274">
                        <path d="M960 896H64c-35.2 0-64-28.8-64-64V192c0-35.2 28.8-64 64-64h896c35.2 0 64 28.8 64 64v640c0 35.2-28.8 64-64 64z m32-704c0-19.2-12.8-32-32-32H64c-19.2 0-32 12.8-32 32v640c0 19.2 12.8 32 32 32h896c19.2 0 32-12.8 32-32V192z m-416 224h320v32H576v-32z m0-96h320v32H576v-32z m41.6 307.2l80 41.6 153.6-118.4c9.6-9.6 25.6-9.6 35.2 0 9.6 9.6 9.6 25.6 0 35.2l-172.8 172.8c-3.2 6.4-9.6 9.6-16 9.6s-12.8-3.2-19.2-6.4l-99.2-99.2c-9.6-9.6-9.6-25.6 0-35.2 12.8-12.8 28.8-12.8 38.4 0zM512 720c0 6.4 0 9.6-3.2 16h-32c0-6.4 3.2-9.6 3.2-16 0-80-70.4-144-160-144s-160 64-160 144c0 6.4 0 9.6 3.2 16h-32c-3.2-6.4-3.2-9.6-3.2-16 0-76.8 51.2-137.6 124.8-163.2-35.2-22.4-60.8-64-60.8-108.8 0-70.4 57.6-128 128-128s128 57.6 128 128c0 44.8-25.6 86.4-60.8 108.8 73.6 25.6 124.8 86.4 124.8 163.2zM416 448c0-54.4-41.6-96-96-96s-96 41.6-96 96 41.6 96 96 96 96-41.6 96-96z" fill="#ffffff" p-id="12275"></path>
                    </svg>
                </diV>
                <div class="description-simple">
                    <p class="title-simple">实名认证</p>
                    <div class="state-simple">
                        <svg t="1705655909719" class="state-icon" viewBox="0 0 1497 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4534">
                            <path d="M1476.470467 150.422791 688.633041 938.260217C685.780224 950.315666 679.867532 961.795951 670.45784 971.205644L637.926532 1003.736952C610.985823 1030.700667 567.273316 1030.700667 540.332607 1003.736952L533.03952 996.466872C533.016514 996.420859 532.9705 996.397852 532.924487 996.351839L500.393179 963.820531C500.347166 963.774518 500.324159 963.728504 500.278146 963.705498L19.739648 483.167C-7.20106 456.203285-7.20106 412.513784 19.739648 385.550069L52.270956 353.01876C79.234672 326.055045 122.924173 326.055045 149.887888 353.01876L581.744456 784.875329 1346.322227 20.274551C1373.285942-6.689164 1416.975443-6.689164 1443.939158 20.274551L1476.470467 52.805859C1503.434182 79.769575 1503.434182 123.459076 1476.470467 150.422791Z" p-id="4535" fill="#ffffff"></path>
                        </svg>
                    </div>
                    <p class="context-simple">用于提升账号的安全性和信任级别</p>
                </div>
                <div class="operate-simple" @click="toAuthentication()">
                    <p class="operate-context">实名</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
#first-area {
    width: 830px;
    height: 200px;
    margin-left: 20px;
    margin-top: 20px;
    padding-top: 20px;
    background-color: white;
    border-radius: 10px;
}
#head-sculpture {
    width: 110px;
    height: 110px;
    border-radius: 50%;
    margin-left: 50px;
    background-color: rgb(209, 209, 209);
    float: left;
    box-shadow: 0 0 4px 2px rgba(0, 0, 0, 0.2), 0 2px 20px 0 rgba(0, 0, 0, 0.19);
}
#head-sculpture img {
    width: 110px;
    height: 110px;
    border-radius: 50%;
}
#info-summarize {
    width: 500px;
    height: 110px;
    margin-left: 170px;
}
#nickname {
    margin-top: 5px;
    font-size: 20px;
    font-weight: bold;
}
#data-area {
    width: 500px;
    height: 25px;
    margin-top: 12px;
}
#data-completeness {
    float: left;
}
.progress {
    width: 150px;
    margin-left: 10px;
    margin-top: 1px;
    float: left;
}
#data-to {
    color: rgb(91, 82, 82);
    margin-left: 20px;
    float: left;
}
#data-to:hover {
    color: rgb(242, 114, 45);
    cursor: pointer;
}
#security-area {
    width: 500px;
    height: 25px;
    margin-top: 5px;
}
#account-security {
    margin-top: 2px;
    float: left;
}
#security-to {
    color: rgb(91, 82, 82);
    margin-left: 190px;
    margin-top: -20px;
    float: left;
}
#security-to:hover {
    color: rgb(242, 114, 45);
    cursor: pointer;
}
#logistics-status-area {
    width: 770px;
    height: 50px;
    margin-top: 10px;
    margin-left: 30px;
    background-color: rgb(241, 240, 238);
}
.logistics-status {
    width: 150px;
    height: 50px;
    text-align: center;
    margin-top: 15px;
    float: left;
}
.status-number {
    margin-left: 10px;
}
.separator {
    width: 4px;
    height: 50px;
    margin-top: 13px;
    color: rgb(173, 168, 168);
    float: left;
}


#second-area {
    width: 830px;
    height: 550px;
    margin-left: 20px;
    margin-top: 20px;
    padding-top: 10px;
    background-color: white;
    border-radius: 10px;
}
.area-title {
    width: 650px;
    height: 40px;
    margin-left: 50px;
    margin-top: 20px;
}
.area-title p {
    font-size: 20px;
    font-weight: bolder;
}
.simple-area {
    width: 650px;
    height: 50px;
    margin-left: 70px;
    margin-top: 15px;
}
.info-title {
    width: 80px;
    height: 30px;
    font-size: 18px;
    padding-top: 2px;
    float: left;
}
.info-same-one {
    width: 300px;
    height: 30px;
    font-size: 18px;
    padding-top: 2px;
    margin-left: 20px;
    float: left;
}
.info-same-two {
    margin-top: 5px;
    float: left;
}
.info-same-third {
    width: 500px;
    height: 30px;
    font-size: 18px;
    padding-top: 2px;
    overflow: hidden;
    margin-left: 20px;
    float: left;
}
.birthday-left {
    margin-left: 20px;
}
.birthday-unit {
    margin-left: 5px;
    margin-right: 10px;
    margin-top: 5px;
    float: left;
}
#input-submit {
    width: 150px;
    height: 40px;
    margin-left: 320px;
    margin-top: 20px;
    font-size: 18px;
    border: none;
    color: white;
    background-color: rgb(248, 83, 37);
    border-radius: 5px;
    cursor: pointer;
}
#input-submit:hover {
    background-color: rgb(254, 103, 61);
}





#forth-area {
    width: 830px;
    height: 500px;
    margin-left: 20px;
    margin-top: 20px;
    padding-top: 10px;
    background-color: white;
    border-radius: 10px;
}
.forth-area-simple {
    width: 650px;
    height: 60px;
    margin-left: 70px;
    margin-top: 20px;
}
.icon-simple {
    width: 60px;
    height: 60px;
    background-color:rgb(78, 234, 91);
    border-radius: 50%;
    float: left;
}
.security-icon {
    width: 36px;
    margin: 12px;
}
.security-icon-email {
    width: 36px;
    margin-left: 12px;
    margin-top: 16px;
}
.description-simple {
    width: 250px;
    height: 60px;
    float: left;
}
.title-simple {
    font-size: 18px;
    font-weight: bolder;
    margin-left: 20px;
    margin-top: 10px;
    float: left;
}
.context-simple {
    font-size: 15px;
    margin-left: 20px;
    float: left;
}
.state-simple {
    width: 25px;
    height: 25px;
    background-color:rgb(42, 231, 57);
    border-radius: 50%;
    margin-left: 10px;
    margin-top: 10px;
    float: left;
}
.state-icon {
    width: 20px;
    height: 20px;
    margin-left: 2px;
    margin-top: 3px;
}
.operate-simple {
    width: 60px;
    height: 40px;
    background-color:rgb(246, 169, 81);
    border-radius: 5px;
    margin-left: 200px;
    margin-top: 20px;
    float: left;
    cursor: pointer;
}
.operate-simple:hover {
    background-color:rgb(250, 182, 105);
}
.operate-simple:active {
    background-color:rgb(233, 147, 48);
}
.operate-context {
    margin-left: 15px;
    margin-top: 8px;
}
</style>