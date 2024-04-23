<script setup>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { axiosInstance } from '../../../utils/request.js'
import { defineSignId } from '../../../store/store.js'
import { error, successEasy } from '../../../utils/notification.js';

let router = useRouter();
let itemList = ref();
//读取共享域中的数据
let defineSignIds = defineSignId();
//前往添加收货信息页
let toAddAddress = () => {
    router.push('/personal/addAddress');
}
//前往修改收货信息页
let toUpdateAddress = (receivingId) => {
    defineSignIds.setReceivingInfoId(receivingId);
    router.push('/personal/updateAddress');
}
//获取所有的收货地址信息
let getAllReceivingInfo = () => {
    axiosInstance.get('/receivingInfo/getAllReceivingInfo')
    .then((response) => {
        itemList.value = response.data.data;
    })
    .catch((errors) => {
        console.log(errors);
    })
}
//删除收货信息
let deleteReceivingInfo = (receivingId) => {
    axiosInstance.delete(`/receivingInfo/deleteReceivingInfo/${receivingId}`)
    .then((response) => {
        if(response.data.code == 200){
            successEasy('删除','当前地址已删除');
            getAllReceivingInfo();
        }else{
            error('删除','网络异常，请重新进行删除操作')
        }
    })
    .catch((errors) => {
        console.log(errors);
    })
}
//设为默认地址
let setReceivingInfoDefault = (receivingIdTarget) => {
    let receivingInfo ={
        receivingId : receivingIdTarget,
        customerId : '',
        receiverName : '',
        area : '',
        detailAddress : '',
        telephone : '',
        isDefault : '',
    }
    axiosInstance.put('/receivingInfo/setReceivingInfoDefault',receivingInfo)
    .then((response) => {
        if(response.data.code == 200){
            successEasy('设置','默认地址已改变');
            getAllReceivingInfo();
        }else{
            error('设置','网络异常，请重新进行操作')
        }
    })
    .catch((errors) => {
        console.log(errors);
    })
}

onMounted(() => {
    getAllReceivingInfo();
})
</script>

<template>
    <div id="address-area">
        <div id="address-title">
            <div class="address-name title-same">收货人</div>
            <div class="address-area title-same">所在地区</div>
            <div class="address-detail title-same">详细地址</div>
            <div class="address-telephone title-same">手机</div>
            <div class="address-operation title-same">操作</div>
            <div class="address-default title-same">
                <button class="button-add" @click="toAddAddress()">新增收货地址</button>
            </div>
        </div>
        <div id="address-all">
            <el-scrollbar height="500px" always="true">
                <div class="adddress-single" v-for="receivingInfo in itemList" :key="receivingInfo.receivingId">
                    <div class="address-name address-same">
                        <p class="adddress-single-name">{{ receivingInfo.receiverName }}</p>
                    </div>
                    <div class="address-area address-same">
                        <p class="adddress-single-area">{{ receivingInfo.area.replaceAll(",","") }}</p>
                    </div>
                    <div class="address-detail address-same">
                        <p class="adddress-single-detail">{{ receivingInfo.detailAddress }}</p>
                    </div>
                    <div class="address-telephone address-same">
                        <p class="adddress-single-telephone">{{ receivingInfo.telephone }}</p>
                    </div>
                    <div class="address-operation address-same">
                        <p class="operation-same" @click="toUpdateAddress(receivingInfo.receivingId)">修改</p>
                        <el-popconfirm  title="确定要删除此订单吗?"
                                        confirm-button-text="否"
                                        cancel-button-text="是"
                                        confirm-button-type="danger"
                                        width="180"
                                        @cancel="deleteReceivingInfo(receivingInfo.receivingId)" >
                            <template #reference>
                                <p class="operation-same">删除</p>
                            </template>
                        </el-popconfirm>
                    </div>
                    <div class="address-default address-same-right">
                        <div class="adddress-single-default" v-if="receivingInfo.isDefault == '是'">默认地址</div>
                        <div class="adddress-single-else" v-else @click="setReceivingInfoDefault(receivingInfo.receivingId)">设为默认</div>
                    </div>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>

<style scoped>
#address-area {
    width: 830px;
    height: 590px;
    background-color: white;
    margin-left: 20px;
    margin-top: 20px;
    padding-top: 20px;
    border-radius: 10px;
}
#address-title {
    width: 790px;
    height: 50px;
    background-color: rgb(225, 221, 221);
    margin-left: 20px;
}
.title-same {
    height: 40px;
    float: left;
    text-align: center;
    font-size: 18px;
    padding-top: 10px;
}
.address-name {
    width: 120px;
}
.address-area {
    width: 135px;
}
.address-detail {
    width: 150px;
}
.address-telephone {
    width: 140px;
}
.address-operation {
    width: 120px;
}
.address-default {
    width: 120px;
}
#address-all {
    width: 790px;
    height: 500px;
    margin: 20px;
}
.adddress-single {
    width: 790px;
    height: 80px;
    background-color: rgb(245, 245, 245);
    margin-bottom: 10px;
}
.address-same {
    height: 80px;
    border-right: 1px rgb(220, 215, 215) solid;
    float: left;
}
.address-same-right {
    height: 80px;
    float: left;
}
.adddress-single-name, .adddress-single-telephone {
    text-align: center;
    margin-top: 30px;
}
.adddress-single-area {
    width: 105px;
    height: 45px;
    margin-left: 15px;
    margin-top: 20px;
    text-align: center;
    overflow: hidden;
}
.adddress-single-detail {
    width: 120px;
    height: 45px;
    margin-left: 15px;
    margin-top: 20px;
    text-align: center;
    overflow: hidden;
}
.operation-same {
    text-align: center;
    margin-top: 12px;
    cursor: pointer;
}
.operation-same:hover {
    color: rgb(254, 88, 27);
}
.adddress-single-default {
    width: 90px;
    height: 30px;
    text-align: center;
    background-color: rgb(252, 162, 130);
    color: red;
    border: 1px red solid;
    border-radius: 5px;
    margin-left: 15px;
    margin-top: 20px;
    padding-top: 8px;
}
.button-add {
    width: 80px;
    height: 30px;
    font-size: 12px;
    cursor: pointer;
    border-radius: 5px;
    border: 1px rgb(184, 178, 178) solid;
    background-color: rgb(252, 122, 57);
    color: white;
}
.button-add:hover {
    background-color: rgb(255, 148, 94);
}
.adddress-single-else {
    margin-left: 30px;
    margin-top: 30px;
    color: rgb(121, 121, 121);
    cursor: pointer;
}
.adddress-single-else:hover {
    color: rgb(255, 89, 43)
}
</style>