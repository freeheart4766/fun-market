<script setup>
import { ref, reactive } from 'vue';
import { pcaTextArr } from "element-china-area-data";
import { useRouter } from 'vue-router';
import { axiosInstance } from '../../../utils/request.js'
import { error, successEasy } from '../../../utils/notification.js';

let receivingInfo = reactive({
    receivingId : '',
    customerId : '',
    receiverName : '',
    area : '',
    detailAddress : '',
    telephone : '',
    isDefault : '',
})

let router = useRouter();

let area = ref(receivingInfo.area.split(","));

let toAddress = () => {
    router.push('/personal/address');
}
//将选择地区后生成的数组转换为字符串
let updateAddress = () => {
    receivingInfo.area = area.value.toString();
}
//新增收货地址
let addReceivingInfo = () => {
    axiosInstance.post('/receivingInfo/addReceivingInfo',receivingInfo)
    .then((response) => {
        if(response.data.code == 200){
            successEasy('添加','收货地址信息已添加');
            toAddress();
        }else{
            error('添加','请重新提交');
        }
    })
    .catch((errors) => {
        console.log(errors);
    })
}
</script>

<template>
    <div class="address-area">
        <div class="title">收货地址:</div>
        <div class="router-return" @click="toAddress()">返回</div>
        <div class="line-single-top">
            <p class="single-title">收货人姓名 :</p>
            <el-input class="single-same-one" v-model="receivingInfo.receiverName" type="text" size="large" />
        </div>
        <div class="line-single">
            <p class="single-title">所在地区 :</p>
            <el-cascader v-model="area" v-bind:options="pcaTextArr" style="margin-top: 5px;" size="large" placeholder="请选择" @change="updateAddress()"/>
        </div>
        <div class="line-single">
            <p class="single-title">详细地址 :</p>
            <el-input class="single-context" v-model="receivingInfo.detailAddress" type="text" size="large" />
        </div>
        <div class="line-single">
            <p class="single-title">手机号 :</p>
            <el-input class="single-same-one" v-model="receivingInfo.telephone" type="text" size="large" />
        </div>
        <div class="line-single">
            <p class="single-title">默认地址 :</p>
            <el-switch
                class="address-default-switch"
                v-model="receivingInfo.isDefault"
                size="large"
                style="--el-switch-on-color: #13ce66;"
                active-value="是"
                inactive-value="否"
                inline-prompt
                active-text="是"
                inactive-text="否"/>
        </div>
        <button class="button" @click="addReceivingInfo()">保存</button>
    </div>
</template>

<style scoped>
.address-area {
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
.line-single-top {
    width: 600px;
    height: 50px;
    margin-top: 100px;
    margin-left: 100px;
}
.line-single {
    width: 600px;
    height: 50px;
    margin-top: 20px;
    margin-left: 100px;
}
.single-title {
    width: 120px;
    height: 35px;
    font-size: 18px;
    padding-top: 15px;
    float: left;
}
.single-context {
    width: 400px;
    height: 40px;
    font-size: 17px;
    margin-top: 5px;
    float: left;
}
.single-same-one {
    width: 200px;
    height: 40px;
    font-size: 17px;
    margin-top: 5px;
    float: left;
}
.address-default-switch {
    margin-top: 7px;
}
.button {
    width: 200px;
    height: 50px;
    background-color: rgb(255, 139, 50);
    font-size: 20px;
    color: white;
    border-radius: 10px;
    border: none;
    margin-top: 50px;
    margin-left: 300px;
    cursor: pointer;
}
.button:hover {
    background-color: rgb(255, 158, 84);
}
</style>