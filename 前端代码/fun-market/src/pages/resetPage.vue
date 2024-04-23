<script setup>
import {ref,reactive} from 'vue'
import {getUsernameResult,getPasswordResult,getTwicePasswordResult,getTelephoneResult,submit} from '../utils/checkResult.js'
import {useRouter} from 'vue-router'

let data = reactive({
    username:'',
    password:''
})
let password2 = ref('')
let telephone = ref('')
let verifiCode = ref('')
let router = useRouter()

let checkUsername = () => {
    getUsernameResult(data.username);
}
let checkPassword = () => {
    getPasswordResult(data.password);
}
let checkTwicePassword = () => {
    getTwicePasswordResult(data.password,password2.value);
}
let checkTelephone = () => {
    getTelephoneResult(telephone.value);
}
let reset = () => {
    submit(data.username,data.password,'密码重置','登录页',toLogin);
}
let toLogin = () => {
    router.push('/login');
}
</script>

<template>
    <div>
        <img src="../assets/header-logo.png" id="logo">
        <div id="main">
            <div id="reset-area">
                <div id="username-arae">
                    <div class="icon">
                        <el-icon class="reset-icon"><User /></el-icon>
                    </div>
                    <el-input class="input-area" v-model="data.username" type="text" placeholder="用户名" size="large" @blur="checkUsername()" />
                </div>
                <div class="password-arae">
                    <div class="icon">
                        <el-icon class="reset-icon"><Lock /></el-icon>
                    </div>
                    <el-input class="input-area" v-model="data.password" type="password" placeholder="新密码 (7-16位的字母或数字)" show-password size="large" @blur="checkPassword()" />
                </div>
                <div class="password-arae">
                    <div class="icon">
                        <el-icon class="reset-icon"><Lock /></el-icon>
                    </div>
                    <el-input class="input-area" v-model="password2" type="password" placeholder="再输入一次新密码" show-password size="large" @blur="checkTwicePassword()" />
                </div>
                <div id="telephone-arae">
                    <div class="icon">
                        <svg t="1704529151994" class="reset-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="8578">
                            <path d="M256 70.4A57.9072 57.9072 0 0 0 198.4 128v768A57.9072 57.9072 0 0 0 256 953.6h512A57.9328 57.9328 0 0 0 825.6 896V128A57.9072 57.9072 0 0 0 768 70.4zM256 0h512a128 128 0 0 1 128 128v768a128 128 0 0 1-128 128H256a128 128 0 0 1-128-128V128a128 128 0 0 1 128-128z m166.4 768h179.2a38.4 38.4 0 0 1 0 76.8h-179.2a38.4 38.4 0 0 1 0-76.8z m0 0" p-id="8579" fill="#ffffff"></path>
                        </svg>
                    </div>
                    <el-input class="input-area" v-model="telephone" type="text" placeholder="手机号" size="large" @blur="checkTelephone()" />
                </div>
                <div id="verification-area">
                    <div class="icon">
                        <svg t="1704447194019" class="reset-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3603">
                            <path d="M943.1 172c-2.4-0.2-245.1-25.3-413.8-147.8-5.1-3.7-11-5.6-17.3-5.6-6.2 0-12.2 1.9-17.3 5.6C326.9 146 83.3 171.8 80.9 172c-15.2 1.4-26.6 14.1-26.6 29.3 0 6.7 0.6 165.8 54.8 344.4 32.1 105.8 76.4 196.4 131.9 269.2 70.3 92.3 158.5 156 262 189.2 2.9 0.9 5.9 1.4 9 1.4s6.1-0.5 8.9-1.4c103.6-33.2 191.7-96.8 262-189.2 55.4-72.7 99.8-163.2 131.9-269.2 54.1-178.6 54.8-337.7 54.8-344.4C969.7 186.1 958.3 173.5 943.1 172zM910.1 227.2l-0.1 1.6c-2.9 58.1-13.4 174.4-51.4 299.9-66.7 220.1-183.1 360.1-346 416.1L512 945l-0.6-0.2C349 888.9 232.7 749.4 165.8 530.1c-39.8-130.5-49.4-254.2-51.8-301.4l-0.1-1.6 1.5-0.2c70.6-10.3 250.5-44.8 395.5-142.4l0.9-0.7 1 0.7C658 182.1 837.9 216.6 908.5 227L910.1 227.2z" p-id="3604" fill="#ffffff"></path>
                            <path d="M641.8 351 467 580.3l-89-76.1c-5.3-4.5-12.1-7-19.1-7-8.6 0-16.8 3.7-22.4 10.3-10.5 12.3-9.1 31 3.3 41.5l112.7 96.4c5.2 4.4 12.4 7 19.6 7 0.9 0 1.8 0 2.7-0.1 8-0.8 15.4-5 20.3-11.4l193.7-254c4.8-6.3 6.8-14 5.7-21.8-1-7.8-5.1-14.7-11.3-19.5C670.1 335.6 651.6 338.1 641.8 351z" p-id="3605" fill="#ffffff"></path>
                        </svg>
                    </div>
                    <el-input class="verification-input-area" v-model="verifiCode" type="text" placeholder="验证码" size="large"/>
                    <button id="verification-button">获取验证码</button>
                </div>
                <button id="reset-button" @click="reset()">重置密码</button>
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
    height: 450px;
    background-image: url(../assets/reset-background.png);
    background-size: cover;
    margin-top: 20px;
    padding-top: 100px;
}
#reset-area {
    width: 350px;
    height: 415px;
    background-color: rgb(245, 243, 240);
    margin-left: 900px;
}
#username-arae {
    padding-top: 40px;
    margin-left: 30px;
}
.password-arae, #telephone-arae, #verification-area {
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
.reset-icon {
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
#verification-button {
    width: 100px;
    height: 40px;
    background-color: rgb(254, 152, 80);
    float: left;
    cursor: pointer;
    border: none;
}
#verification-button:active {
    background-color: rgb(227, 108, 23);
}
#reset-button {
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
#reset-button:active {
    background-color: rgb(226, 63, 63);
}
</style>