<script setup>
import { ref, reactive } from 'vue';
import { getPasswordResult, getTwicePasswordResult } from '../../../utils/checkResult.js'
import { useRouter } from 'vue-router';
import { customerInfo } from '../../../utils/pojo.js'
import { axiosInstance } from '../../../utils/request.js'
import { successEasy, error } from '../../../utils/notification.js'

let router = useRouter();

let toPersonal = () => {
    router.push('/personal');
}
let resetPassword = reactive({
    //输入的验证码
    verifyCode : '',
    //后端的加密的验证码
    encryptionVerifyCode : '',
    //新密码
    passwordNew : '',
})
//选择验证码发送渠道
let checkMethod = ref('');
let passwordDouble = ref('');
let checkPassword = () => {
    getPasswordResult(resetPassword.passwordNew);
}
let checkTwicePassword = () => {
    getTwicePasswordResult(resetPassword.passwordNew,passwordDouble.value);
}
//获取邮箱验证码
let getEmailVerifyCode = () => {
    axiosInstance.get(`/system/getEmailVerifyCode/${customerInfo.email}`)
    .then((response) => {
        resetPassword.encryptionVerifyCode = response.data.data;
        successEasy('验证码发送','请及时填写');
    })
    .catch((errors) => {
        console.log(errors);
        error('验证码获取','请重新获取');
    })
}
//获取短信验证码
let getMessageVerifyCode = () => {
    axiosInstance.get(`/system/getMessageVerifyCode/${customerInfo.telephone}`)
    .then((response) => {
        resetPassword.encryptionVerifyCode = response.data.data;
        successEasy('验证码发送','请及时填写');
    })
    .catch((errors) => {
        console.log(errors);
        error('验证码获取','请重新获取');
    })
}
//根据不同类型获取验证码
let getVerifyCode = () => {
    if(checkMethod.value == 'email'){
        getEmailVerifyCode();
    }else{
        getMessageVerifyCode();
    }
}
//提交修改支付密码的请求
let changePayPassword = () => {
    axiosInstance.put('/customer/changePayPassword',resetPassword)
    .then((response) => {
        if(response.data.code != 200){
            error('修改密码',response.data.message)
        }else{
            successEasy('密码修改','密码已修改');
            toPersonal();
        }
    })
}
</script>

<template>
    <div class="pay-password-area">
        <div class="title">支付密码:</div>
        <div class="router-return" @click="toPersonal()">返回</div>
        <div class="choose-method">
            <p class="choose-method-context">手机号验证</p>
            <el-switch
                class="choose-method-switch"
                v-model="checkMethod"
                size="large"
                style="--el-switch-on-color: #4967ff; --el-switch-off-color: #13ce66"
                active-value="email"
                inactive-value="telephone"/>
            <p class="choose-method-context">邮箱验证</p>
        </div>
        <div class="line-single">
            <div v-if="checkMethod == 'telephone'">
                <p class="choose-title">手机号: </p>{{ customerInfo.telephone }}
            </div>
            <div v-else>
                <p class="choose-title">邮箱: </p>{{ customerInfo.email }}
            </div>
        </div>
        <div class="line-single">
            <el-input class="choose-input" v-model="resetPassword.verifyCode" placeholder="请输入验证码" size="large" />
            <button class="choose-button" @click="getVerifyCode()">获取验证码</button>
        </div>
        <div class="line-single">
            <el-tooltip class="input-tooltip" effect="dark" content="7-16个字符, 可由字母和数字组成" placement="right" trigger="click">
                <el-input class="password-input" v-model="resetPassword.passwordNew" type="password" placeholder="请输入新的支付密码" show-password size="large" @blur="checkPassword()" />
            </el-tooltip>
        </div>
        <div class="line-single">
            <el-input class="password-input" v-model="passwordDouble" type="password" placeholder="请再输入一次新密码" show-password size="large" @blur="checkTwicePassword()" />
        </div>
        <div class="line-single">
            <button class="submit" @click="changePayPassword()">提交</button>
        </div>
    </div>
</template>

<style scoped>
.pay-password-area {
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
.choose-method {
    width: 300px;
    height: 40px;
    margin-left: 200px;
    margin-top: 100px;
}
.choose-method-context {
    width: 100px;
    height: 30px;
    font-size: 18px;
    margin-top: 7px;
    float: left;
}
.choose-method-switch {
    margin-right: 10px;
    float: left;
}
.line-single {
    width: 300px;
    height: 33px;
    font-size: 18px;
    margin-left: 200px;
    margin-top: 30px;
    padding-top: 7px;
}
.choose-title {
    width: 80px;
    float: left;
}
.choose-input {
    height: 40px;
    width: 135px;
    font-size: 17px;
    padding-top: 1px;
    padding-bottom: 1px;
}
.choose-button {
    width: 100px;
    height: 38px;
    background-color: rgb(255, 124, 42);
    color: white;
    cursor: pointer;
    border: none;
}
.choose-button:hover {
    background-color: rgb(255, 150, 85);
}
.password-input {
    width: 200px;
    height: 40px;
    font-size: 17px;
    padding-top: 1px;
    padding-bottom: 1px;
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
    margin-top: 20px;
}
.submit:hover {
    background-color: rgb(255, 110, 47);
}
</style>