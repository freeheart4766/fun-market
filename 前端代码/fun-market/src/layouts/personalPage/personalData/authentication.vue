<script setup>
import { ref, reactive, onMounted } from 'vue';
import { getIDNumberResult, getTrueNameResult } from '../../../utils/checkResult.js'
import { useRouter } from 'vue-router';
import { axiosInstance } from '../../../utils/request.js'
import { error, success } from '../../../utils/notification.js';

let router = useRouter();

let toPersonal = () => {
    router.push('/personal');
}
//是否实名
let authenticationStatus = ref(0);

let authentication = reactive({
    customerId : '',
    trueName : '',
    idNumber : '',
})
let checkTrueName = () => {
    getTrueNameResult(authentication.trueName);
}
let checkIDNumber = () => {
    getIDNumberResult(authentication.idNumber);
}
//获取是否实名的信息
let getAuthenticationStatus = () => {
    axiosInstance.get('/authentication/getAuthenticationStatus')
    .then((response) => {
        if(response.data.data == 1){
            authenticationStatus.value = 1;
        }
    })
    .catch((errors) => {
        console.log(errors);
    })
}
//将实名信息提交到后端保存
let addAuthentication = () => {
    axiosInstance.post('/authentication/addAuthentication',authentication)
    .then((response) => {
        if(response.data.code == 200){
            success('实名信息','您已实名',toPersonal());
        }else{
            error('提交',response.data.message);
        }
    })
    .catch((errors) => {
        console.log(errors);
    })
}
onMounted(() => {
    getAuthenticationStatus();
})
</script>

<template>
    <div class="authentication-area">
        <div class="title">实名认证:</div>
        <div class="router-return" @click="toPersonal()">返回</div>
        <div class="authentication-yes" v-if="authenticationStatus == 1">您已实名!</div>
        <div class="authentication-no" v-else>
            <div class="input-area">
                <p class="input-title">姓名:</p>
                <el-input class="authentication-input" v-model="authentication.trueName" size="large" @blur="checkTrueName()" />
            </div>
            <div class="input-area">
                <p class="input-title">身份证号:</p>
                <el-input class="authentication-input" v-model="authentication.idNumber" size="large" @blur="checkIDNumber()" />
            </div>
            <button class="submit" @click="addAuthentication()">提交</button>
        </div>
    </div>
</template>

<style scoped>
.authentication-area {
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
.authentication-yes {
    width: 200px;
    height: 50px;
    font-size: 40px;
    font-weight: bolder;
    margin-top: 250px;
    margin-left: 300px;
}
.authentication-no {
    width: 370px;
    height: 300px;
    margin-left: 200px;
    margin-top: 150px;
    padding-left: 50px;
    padding-top: 30px;
    border: 5px rgb(201, 201, 201) solid;
    border-radius: 15px;
}
.input-area {
    width: 400px;
    height: 50px;
    margin-top: 20px;
}
.input-title {
    width: 90px;
    height: 30px;
    font-size: 18px;
    padding-top: 7px;
    float: left;
}
.authentication-input {
    width: 220px;
    height: 40px;
    font-size: 17px;
}
.submit {
    width: 150px;
    height: 40px;
    font-size: 20px;
    border-radius: 5px;
    border: none;
    background-color: rgb(255, 89, 18);
    color: white;
    cursor: pointer;
    margin-left: 80px;
    margin-top: 50px;
}
.submit:hover {
    background-color: rgb(255, 110, 47);
}
</style>