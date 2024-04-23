<script setup>
import { ref } from 'vue'
import { storeInfo, message, customerInfo } from '../../../utils/pojo.js'


let date = ref(new Date(message.time).toISOString().slice(0,10).replaceAll("-","/"));
let datetime = ref(new Date(message.time).toISOString().slice(0,19).replace("T"," "));

let wordsNumber = ref(0)

//用户修改内容时调用的方法
let getInputNumber = () => {
    wordsNumber.value = messageInput.value.length;
}

let messageInput = ref();
</script>

<template>
    <div id="message-area">
        <div id="role-area">
            <el-scrollbar height="600px" always="true">
                <div class="role-single">
                    <img class="role-single-picture picture-same" v-bind:src="storeInfo.portraitPath">
                    <p class="store-name name-same">{{ storeInfo.storeName }}</p>
                    <p class="message-time time-same">{{ date }}</p>
                    <p class="store-last-message">{{ message.messageContent }}</p>
                </div>
            </el-scrollbar>
        </div>
        <div id="conversation-area">
            <div class="conversation-store-name conversation-type-same">{{ storeInfo.storeName }}</div>
            <div class="conversation-message conversation-type-same">
                <el-scrollbar height="400px" always="true">
                    <div class="message-sender" v-if="message.senderId == message.storeId">
                        <img class="role-store-picture picture-same" v-bind:src="storeInfo.portraitPath">
                        <div class="sender-info">
                            <p class="message-store-name message-name-same name-same">{{ storeInfo.storeName }}</p>
                            <p class="message-store-time message-time-same time-same">{{ datetime }}</p>
                        </div>
                        <div class="message-store-content message-content-same">{{ message.messageContent }}</div>
                    </div>
                    <div class="message-sender" v-else>
                        <img class="role-customer-picture picture-same" v-bind:src="customerInfo.portraitPath">
                        <div class="sender-info">
                            <p class="message-customer-name message-name-same name-same">{{ customerInfo.nickname }}</p>
                            <p class="message-customer-time message-time-same time-same">{{ datetime }}</p>
                        </div>
                        <div class="message-customer-content message-content-same">{{ message.messageContent }}</div>
                    </div>
                </el-scrollbar>
            </div>
            <div class="conversation-input conversation-type-same">
                <textarea   v-model="messageInput" 
                            id="input-area" 
                            placeholder="请输入消息,按Enter键或者点击发送按钮发送" 
                            maxlength="100" 
                            @input="getInputNumber()">
                </textarea>
                <p id="words-count">{{ wordsNumber }} / 100</p>
                <button id="input-send">发送</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
#message-area {
    width: 830px;
    height: 600px;
    background-color: white;
    margin-left: 20px;
    margin-top: 20px;
    border-radius: 10px;
    overflow: hidden;
}
#role-area {
    width: 275px;
    height: 590px;
    background-color: rgb(250, 250, 250);
    padding-top: 10px;
    border-right: 2px rgb(230, 230, 230) solid;
    float: left;
}
.role-single {
    width: 250px;
    height: 50px;
    background-color: white;
    margin-left: 10px;
    border-radius: 5px;
    cursor: pointer;
}
.role-single:hover {
    background-color: rgb(235, 235, 235);
}
.picture-same {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin: 5px;
    overflow: hidden;
}
.role-single-picture {
    float: left;
}
.name-same {
    height: 23px;
    overflow: hidden;
}
.store-name {
    width: 120px;
    margin-top: 5px;
    font-weight: bolder;
    float: left;
}
.time-same {
    height: 20px;
    font-size: 12px;
    overflow: hidden;
}
.message-time {
    width: 75px;
    margin-top: 5px;
    padding-top: 3px;
    float: left;
}
.store-last-message {
    width: 195px;
    height: 17px;
    font-size: 12px;
    overflow: hidden;
    float: left;
}
#conversation-area {
    width: 553px;
    height: 600px;
    background-color: rgb(248, 248, 248);
    float: left;
}
.conversation-type-same {
    width: 500px;
    margin-left: 20px;
}
.conversation-store-name {
    height: 30px;
    padding-top: 20px;
    font-size: 18px;
    border-bottom: 2px rgb(230, 230, 230) solid;
}
.conversation-message {
    height: 400px;
}
.message-sender {
    width: 500px;
    padding-bottom: 10px;
    overflow: hidden;
}
.role-store-picture {
    float: left;
}
.role-customer-picture {
    float: right;
}
.sender-info {
    width: 450px;
    height: 30px;
    float: left;
}
.message-name-same {
    width: fit-content;
    max-width: 100px;
    font-size: 13px;
    padding-top: 5px;
    padding-right: 10px;
}
.message-store-name {
    float: left;
}
.message-customer-name {
    float: right;
}
.message-time-same {
    width: 130px;
    padding-top: 6px;
    padding-bottom: 2px;
}
.message-store-time {
    float: left;
}
.message-customer-time {
    float: right;
}
.message-content-same {
    width: fit-content;
    max-width: 300px;
    height: fit-content;
    background-color: white;
    border-radius: 5px;
    padding-left: 10px;
    padding-top: 5px;
    padding-bottom: 8px;
}
.message-store-content {
    float: left;
}
.message-customer-content {
    float: right;
}
.conversation-input {
    height: 130px;
    border-top: 2px rgb(230, 230, 230) solid;
}
#input-area {
    width: 400px;
    height: 80px;
    resize: none;
    background-color: rgb(248, 248, 248);
    font-size: 16px;
    margin-top: 10px;
    margin-left: 20px;
    border: none;
    outline: none;
    float: left;
}
#words-count {
    width: 60px;
    height: 20px;
    font-size: 12px;
    margin-left: 10px;
    margin-top: 5px;
    float: left;
}
#input-send {
    width: 60px;
    height: 25px;
    border-radius: 12px;
    border: none;
    cursor: pointer;
    margin-top: 70px;
    margin-left: 5px;
    background-color: rgb(74, 238, 69);
    color: white;
    float: left;
}
#input-send:hover {
    background-color: rgb(91, 246, 85);
}
</style>