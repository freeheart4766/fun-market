<script setup>
import { ref, onMounted } from 'vue'
import {useRouter} from 'vue-router'
import axios from 'axios'
import {getUsernameResult, getPasswordResult, getTwicePasswordResult, submit} from '../utils/checkResult.js'
import { loginAndRegister } from '../utils/pojo.js'
import { success, warning, error } from '../utils/notification.js'
import { unificationURL } from '../utils/request.js'

let router = useRouter()
let password2 = ref('')
let verifyCodeImage = ref('');
let verifyCodeAfter = ref('');

let checkUsername = () => {
    let usernameResult = getUsernameResult(loginAndRegister.username);
    if(usernameResult){
        usernameOccupy();
    }
}
let checkPassword = () => {
    getPasswordResult(loginAndRegister.password);
}
let checkTwicePassword = () => {
    getTwicePasswordResult(loginAndRegister.password,password2.value);
}
//将数据发送到后端进行保存
let register = () => {
    let checkUsernameAndPassword = submit(loginAndRegister.username,loginAndRegister.password);
    if(loginAndRegister.verifyCode == null){
        warning("验证码未输入")
    }
    if(checkUsernameAndPassword && password2 != null && loginAndRegister.verifyCode != null){
        axios.post(`${unificationURL}/customer/register`,
                    loginAndRegister,
                    {headers:{'verify':verifyCodeAfter.value}}
                )
        .then((response) => {
            if(response.data.code != 200){
                error("注册",response.data.message);
            }else{
                loginAndRegister.password = '';
                loginAndRegister.verifyCode = '';
                success("注册","登录页",toLogin());
            }
        })
        .catch((error) => {
            console.log(error);
            warning("网络异常，请重新提交");
        })
    }
}
let toLogin = () => {
    router.push('/login');
}
//异步发送请求，检测用户名是否占用
let usernameOccupy = () => {
    axios.get(`${unificationURL}/customer/usernameOccupy/${loginAndRegister.username}`)
    .then((response) => {
        if(response.data.code != 200){
            error("验证",response.data.message);
        }
    })
    .catch(() => {
        warning("网络异常");
    })
}
//获取验证码图片
let getVerifyCodeImage = () => {
    axios.get(`${unificationURL}/system/getVerifyCodeImage`,
                {responseType:'blob'},
                {headers:{'Access-Control-Allow-Headers':'verify'}}
            )
    .then((response) => {
        verifyCodeImage.value = URL.createObjectURL(new Blob([response.data]));
        verifyCodeAfter.value = response.headers['verify'];
    })
    .catch(() => {
        error("获取","验证码获取失败，请重新获取");
    })
}
//组件挂载完成后自动调用
onMounted(() => {
    getVerifyCodeImage();
})
</script>

<template>
    <div>
        <img src="../assets/header-logo.png" id="logo">
        <div id="main">
            <div id="register-area">
                <div id="username-arae">
                    <div class="icon">
                        <el-icon class="register-icon"><User /></el-icon>
                    </div>
                    <el-input class="input-area" v-model="loginAndRegister.username" type="text" placeholder="用户名 (4-10位的字母)" size="large" @blur="checkUsername()" />
                </div>
                <div class="password-arae">
                    <div class="icon">
                        <el-icon class="register-icon"><Lock /></el-icon>
                    </div>
                    <el-input class="input-area" v-model="loginAndRegister.password" type="password" placeholder="密码 (7-16位的字母或数字)" show-password size="large" @blur="checkPassword()" />
                </div>
                <div class="password-arae">
                    <div class="icon">
                        <el-icon class="register-icon"><Lock /></el-icon>
                    </div>
                    <el-input class="input-area" v-model="password2" type="password" placeholder="再输入一次密码" show-password size="large" @blur="checkTwicePassword()" />
                </div>
                <div id="verification-area">
                    <div class="icon">
                        <svg t="1704447194019" class="register-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3603">
                            <path d="M943.1 172c-2.4-0.2-245.1-25.3-413.8-147.8-5.1-3.7-11-5.6-17.3-5.6-6.2 0-12.2 1.9-17.3 5.6C326.9 146 83.3 171.8 80.9 172c-15.2 1.4-26.6 14.1-26.6 29.3 0 6.7 0.6 165.8 54.8 344.4 32.1 105.8 76.4 196.4 131.9 269.2 70.3 92.3 158.5 156 262 189.2 2.9 0.9 5.9 1.4 9 1.4s6.1-0.5 8.9-1.4c103.6-33.2 191.7-96.8 262-189.2 55.4-72.7 99.8-163.2 131.9-269.2 54.1-178.6 54.8-337.7 54.8-344.4C969.7 186.1 958.3 173.5 943.1 172zM910.1 227.2l-0.1 1.6c-2.9 58.1-13.4 174.4-51.4 299.9-66.7 220.1-183.1 360.1-346 416.1L512 945l-0.6-0.2C349 888.9 232.7 749.4 165.8 530.1c-39.8-130.5-49.4-254.2-51.8-301.4l-0.1-1.6 1.5-0.2c70.6-10.3 250.5-44.8 395.5-142.4l0.9-0.7 1 0.7C658 182.1 837.9 216.6 908.5 227L910.1 227.2z" p-id="3604" fill="#ffffff"></path>
                            <path d="M641.8 351 467 580.3l-89-76.1c-5.3-4.5-12.1-7-19.1-7-8.6 0-16.8 3.7-22.4 10.3-10.5 12.3-9.1 31 3.3 41.5l112.7 96.4c5.2 4.4 12.4 7 19.6 7 0.9 0 1.8 0 2.7-0.1 8-0.8 15.4-5 20.3-11.4l193.7-254c4.8-6.3 6.8-14 5.7-21.8-1-7.8-5.1-14.7-11.3-19.5C670.1 335.6 651.6 338.1 641.8 351z" p-id="3605" fill="#ffffff"></path>
                        </svg>
                    </div>
                    <el-input class="verification-input-area" v-model="loginAndRegister.verifyCode" type="text" placeholder="验证码" size="large"/>
                    <div class="verification-code" @click="getVerifyCodeImage()">
                        <img v-bind:src="verifyCodeImage">
                    </div>
                </div>
                <button id="register-button" @click="register()">注册</button>
            </div> 
        </div>
    </div>
</template>

<style scoped>
#logo {
    width: 120px;
    margin-left: 200px;
    margin-top: 20px;
}
#main {
    width: 100%;
    height: 420px;
    background-image: url(../assets/register-background.png);
    background-size: cover;
    margin-top: 20px;
    padding-top: 130px;
}
#register-area {
    width: 350px;
    height: 360px;
    background-color: rgb(245, 243, 240);
    margin-left: 900px;
}
#username-arae {
    padding-top: 40px;
    margin-left: 30px;
}
.password-arae, #verification-area {
    margin-top: 20px;
    margin-left: 30px;
}
.icon {
    width: 30px;
    height: 30px;
    background-color: rgb(223, 219, 215);
    padding: 5px;
    float: left;
}
.register-icon {
    font-size: 30px;
    color: white;
}
.input-area {
    width: 250px;
}
.verification-input-area {
    width: 150px;
    float: left;
}
.verification-code {
    width: 100px;
    height: 40px;
    cursor: pointer;
    float: left;
}
#register-button {
    width: 290px;
    height: 40px;
    margin-top: 20px;
    margin-left: 30px;
    border-radius: 10px;
    border: none;
    color: white;
    background-color: rgb(255, 97, 97);
    font-family: KaiTi;
    font-size: 20px;
    cursor: pointer;
}
#register-button:active {
    background-color: rgb(226, 63, 63);
}
</style>