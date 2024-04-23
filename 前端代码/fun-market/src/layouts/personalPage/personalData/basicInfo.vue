<script setup>
import { onMounted, reactive, ref } from 'vue'
import {getNicknameResult,getDescriptionResult,getEmailResult,getTelephoneResult,} from '../../../utils/checkResult.js'
import {success, error} from '../../../utils/notification.js'
import { useRouter } from 'vue-router'
import { customerInfo } from '../../../utils/pojo.js'
import { axiosInstance } from '../../../utils/request.js'

let router = useRouter();

let birthdayInfo = ref([]);
let birthdayShow = reactive({
    year : '',
    month : '',
    day : '',
})

//获取当前年份
let currentYear = new Date().getFullYear(); 
//从初始年份开始，直到今年
let years = Array.from({ length: 50 }, (_, idx) => currentYear - idx);
//月份
let months = [1,2,3,4,5,6,7,8,9,10,11,12]
//获取当月的天数
let days = ref()
let getDays = () => {
    days = Array.from({ length: new Date(birthdayInfo.value[0], birthdayInfo.value[1], 0).getDate() }, (_, idx) => idx + 1);
}

//将生日信息转为字符串
let transformBirthday = () => {
    birthdayInfo.value[0] = birthdayShow.year;
    birthdayInfo.value[1] = birthdayShow.month;
    birthdayInfo.value[2] = birthdayShow.day;
    customerInfo.birthday = birthdayInfo.value.toString();
}

//检察昵称的格式
let checkNickname = () => {
    getNicknameResult(customerInfo.nickname);
}
//检察个性签名的格式
let checkDescription = () => {
    getDescriptionResult(customerInfo.description);
}
//检察电话的格式
let checkTelephone = () => {
    getTelephoneResult(customerInfo.telephone);
}
//检察邮箱的格式
let checkEmail = () => {
    getEmailResult(customerInfo.email);
}

//修改用户的基本信息
let updateCustomerInfo = () => {
    axiosInstance.put('/customerInfo/updateCustomerInfo',customerInfo)
    .then((response) => {
        if(response.data.code != 200){
            error('提交','服务器出错了');
        }else {
            success('提交','个人信息页',toPersonal());
        }
    })
    .catch((errors) => {
        console.log(errors);
    })
}
//获取用户的基本信息
let getCustomerInfo = () => {
    axiosInstance.get('/customerInfo/getCustomerInfo')
    .then((response) => {
        customerInfo.customerId = response.data.data.customerId;
        customerInfo.gender = response.data.data.gender;
        customerInfo.birthday = response.data.data.birthday;
        customerInfo.description = response.data.data.description;
        customerInfo.telephone = response.data.data.telephone;
        customerInfo.email = response.data.data.email;
        //将生日信息转换为一个数组
        birthdayInfo = customerInfo.birthday.split(",");
        birthdayShow.year = birthdayInfo[0];
        birthdayShow.month = birthdayInfo[1];
        birthdayShow.day = birthdayInfo[2];
    })
    .catch((errors) => {
        console.log(errors);
    })
}

let toPersonal = () => {
    router.push('/personal');
}
onMounted(() => {
    getCustomerInfo();
})
</script>

<template>
    <div id="basic-info-area">
            <div class="area-title">
                <p>基本信息 :</p>
                <div class="router-return" @click="toPersonal()">返回</div>
            </div>
            <div class="simple-area">
                <p class="info-title">昵称:</p>
                <el-tooltip class="input-tooltip" v-model="customerInfo.nickname" effect="dark" content="2-14个字符, 可由中英文、数字组成" placement="right" trigger="click">
                    <input type="text" v-model="customerInfo.nickname" id="input-nickname" @blur="checkNickname()">
                </el-tooltip>
            </div>
            <div class="simple-area">
                <p id="info-title-sex">性别:</p>
                <input type="radio" name="sex" class="input-sex" v-model="customerInfo.gender" value="男"><p class="sex-value">男</p>
                <input type="radio" name="sex" class="input-sex" v-model="customerInfo.gender" value="女"><p class="sex-value">女</p>
            </div>
            <div class="simple-area">
                <p class="info-title">生日:</p>
                <el-select v-model="birthdayShow.year" placeholder="请选择" class="input-birthday" @change="transformBirthday()">
                    <el-option class="birthday-option" v-for="year in years" v-bind:key="year" v-bind:label="year" v-bind:value="year" />
                </el-select>
                <p class="birthday-unit">年</p>
                <el-select v-model="birthdayShow.month" placeholder="请选择" class="input-birthday" @change="transformBirthday()">
                    <el-option class="birthday-option" v-for="month in months" v-bind:key="month" v-bind:label="month" v-bind:value="month" />
                </el-select>
                <p class="birthday-unit">月</p>
                <el-select v-model="birthdayShow.day" placeholder="请选择" class="input-birthday" @focus="getDays()" @change="transformBirthday()">
                    <el-option class="birthday-option" v-for="day in days" v-bind:key="day" v-bind:label="day" v-bind:value="day" />
                </el-select>
                <p class="birthday-unit">日</p>
            </div>
            <div class="simple-area">
                <p class="info-title">个性签名:</p>
                <el-tooltip class="input-tooltip" effect="dark" content="40个字符以内, 可由中英文组成" placement="right" trigger="click">
                    <input type="text" v-model="customerInfo.description" id="input-description" @blur="checkDescription()">
                </el-tooltip>
            </div>
            <div class="simple-area">
                <p class="info-title">电话:</p>
                <input type="text" v-model="customerInfo.telephone" id="input-telephone" @blur="checkTelephone()">
            </div>
            <div class="simple-area">
                <p class="info-title">邮箱:</p>
                <input type="text" v-model="customerInfo.email" id="input-email" @blur="checkEmail()">
            </div>
            <input id="input-submit" type="submit" @click="updateCustomerInfo()">
        </div>
</template>

<style scoped>
#basic-info-area {
    width: 830px;
    height: 590px;
    margin-left: 20px;
    margin-top: 30px;
    padding-top: 10px;
    background-color: white;
    border-radius: 10px;
}
.area-title {
    width: 750px;
    height: 40px;
    margin-left: 50px;
    margin-top: 20px;
}
.area-title p {
    font-size: 20px;
    font-weight: bolder;
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
    float: right;
}
.router-return:hover {
    background-color: rgb(79, 160, 251);
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
#info-title-sex {
    width: 60px;
    height: 30px;
    font-size: 18px;
    padding-top: 2px;
    float: left;
}
#input-nickname , #input-telephone, #input-email {
    width: 200px;
    height: 30px;
    padding-left: 10px;
    font-size: 17px;
    float: left;
}
#input-description {
    width: 400px;
    height: 30px;
    padding-left: 10px;
    font-size: 17px;
    float: left;
}
.input-sex {
    width: 20px;
    height: 20px;
    margin-left: 20px;
    margin-top: 5px;
    float: left;
}
.sex-value {
    margin-left: 5px;
    margin-top: 5px;
    float: left;
}
.input-birthday {
    width: 120px;
    float: left;
}
.birthday-unit {
    margin-left: 5px;
    margin-right: 20px;
    margin-top: 5px;
    float: left;
}
.birthday-option {
    text-align: center;
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
</style>