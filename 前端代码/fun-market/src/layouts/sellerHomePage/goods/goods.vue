<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from 'vue-router'
import { axiosInstance } from "../../../utils/request";
import { defineSignId } from '../../../store/store.js'
import { successEasy } from "../../../utils/notification";

let router = useRouter();
let defineSignIds = defineSignId();

//商品信息的集合
let itemList = ref();

let toAddGoods = () => {
    router.push('/sellerHome/addGoods');
}
let toUpdateGoods = (goodsId) => {
    defineSignIds.setGoodsId(goodsId);
    router.push('/sellerHome/updateGoods');
}
let toGoodsSingleType = (goodsId) => {
    defineSignIds.setGoodsId(goodsId);
    router.push('/sellerHome/goodsSingleType');
}

//删除商品信息
let deleteGoods = (goodsId) => {
    axiosInstance.delete(`/goods/deleteGoods/${goodsId}`)
    .then((response) => {
        if(response.data.code == 200){
            successEasy('删除','');
            getAllGoods();
        }
    })
}

//获取所有的商品信息
let getAllGoods = () => {
    axiosInstance.get('/goods/getAllGoods')
    .then((response) => {
        itemList.value = response.data.data;
        itemList.value.forEach((item) => {
            getPicture(item.goodsCoverPath,item);
        });
    })
    .catch((errors) => {
        console.log(errors);
    })
}
//获取图片
let getPicture = (picturePath,item) => {
    let index = picturePath.lastIndexOf('\\');
    picturePath = picturePath.substring(index + 1);
    axiosInstance.get(`/system/getPicture/${picturePath}`,{responseType:'blob'})
    .then((response) => {
        item.goodsCoverPath = URL.createObjectURL(new Blob([response.data]));
    })
    .catch((errors) => {
        console.log(errors);    
    });
}

onMounted(() => {
    getAllGoods();
})
</script>

<template>
    <div class="goods-area">
        <div class="goods-title">
            <p class="title-content title-name">商品名称</p>
            <p class="title-content title-cover">封面</p>
            <p class="title-content title-type">商品类别</p>
            <p class="title-content title-inventory">库存</p>
            <p class="title-content title-salesVolume">销量</p>
            <p class="title-content title-operation">操作
                <button class="title-button" @click="toAddGoods()">新增商品</button>
            </p>
        </div>
        <div class="goods-show-area">
            <el-scrollbar height="510px" always="true">
                <div class="goods-single" v-for="goods in itemList" :key="goods.goodsId">
                    <div class="goods-single-content title-name">
                        <p class="goods-single-name">{{ goods.goodsName }}</p>
                    </div>
                    <div class="goods-single-content title-cover">
                        <img v-bind:src="goods.goodsCoverPath" class="goods-single-cover">
                    </div>
                    <div class="goods-single-content title-type">
                        <p class="goods-single-type">{{ goods.goodsType }}</p>
                    </div>
                    <div class="goods-single-content title-inventory">
                        <p class="goods-single-inventory">{{ goods.goodsInventory }}</p>
                    </div>
                    <div class="goods-single-content title-salesVolume">
                        <p class="goods-single-salesVolume">{{ goods.salesVolume }}</p>
                    </div>
                    <div class="goods-single-content title-operation">
                        <div class="operation-left">
                            <button class="goods-single-button" @click="toUpdateGoods(goods.goodsId)">修改</button>
                            <el-popconfirm  title="确定要删除此商品吗?"
                                            confirm-button-text="否"
                                            cancel-button-text="是"
                                            confirm-button-type="danger"
                                            width="180"
                                            @cancel="deleteGoods(goods.goodsId)">
                                <template #reference>
                                    <button class="goods-single-button">删除</button>
                                </template>
                            </el-popconfirm>
                        </div>
                        <button class="operation-right" @click="toGoodsSingleType(goods.goodsId)">种类信息</button>
                    </div>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>

<style scoped>
.goods-area {
    width: 1000px;
    height: 580px;
    background-color: white;
    margin-top: 20px;
    border-radius: 15px;
    padding-top: 30px;
    padding-left: 50px;
}
.goods-title {
    width: 950px;
    height: 50px;
    background-color: rgb(222, 215, 215);
}
.title-content {
    height: 40px;
    font-size: 20px;
    text-align: center;
    padding-top: 10px;
    float: left;
}
.title-name {
    width: 220px;
    border-right: 2px solid rgb(200, 191, 191);
}
.title-cover, .title-type, .title-inventory, .title-salesVolume {
    width: 130px;
    border-right: 2px solid rgb(200, 191, 191);
}
.title-operation {
    width: 200px;
}
.title-button {
    width: 70px;
    height: 30px;
    float: right;
    margin-right: 10px;
    color: white;
    background-color: rgb(255, 0, 38);
    border: none;
    border-radius: 10px;
    cursor: pointer;
}
.title-button:hover {
    background-color: rgb(255, 60, 89);
}
.goods-show-area {
    width: 950px;
    height: 510px;
    margin-top: 20px;
}
.goods-single {
    width: 950px;
    height: 100px;
    background-color: rgb(245, 245, 245);
    margin-bottom: 20px;
}
.goods-single-content {
    height: 100px;
    float: left;
}
.goods-single-name, .goods-single-type, .goods-single-inventory, .goods-single-salesVolume {
    text-align: center;
    font-size: 20px;
    margin-top: 35px;
}
.goods-single-cover {
    width: 80px;
    height: 80px;
    margin-left: 25px;
    margin-top: 10px;
}
.operation-left {
    width: 100px;
    float: left;
}
.goods-single-button {
    width: 70px;
    height: 35px;
    margin-top: 10px;
    margin-left: 20px;
    font-size: 16px;
    color: white;
    background-color: rgb(255, 106, 0);
    border: none;
    border-radius: 10px;
    cursor: pointer;
}
.goods-single-button:hover {
    background-color: rgb(255, 129, 39);
}
.operation-right {
    width: 80px;
    height: 35px;
    margin-top: 30px;
    margin-left: 10px;
    font-size: 16px;
    color: white;
    background-color: rgb(255, 106, 0);
    border: none;
    border-radius: 10px;
    cursor: pointer;
    float: left;
}
</style>