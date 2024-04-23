<script setup>
import { goods } from '../../../utils/pojo.js'
import { useRouter } from 'vue-router';
let router = useRouter();
let toGoods = () => {
    router.push('/goods');
}
</script>

<template>
    <div id="goodsCollection-area">
        <div id="select-area">
            <input id="input-area" placeholder="输入商品名称">
            <div id="select-button">搜索</div>
        </div>
        <div id="goods-area">
            <el-scrollbar height="535px" always="true">
                <div class="goods-single" @click="toGoods()">
                    <img class="goods-single-picture" v-bind:src="goodsShowPicture.pitcureOnePath">
                    <p class="goods-single-name">{{ goods.goodsName }}</p>
                    <p class="goods-single-price">￥{{ goodsSingleTypes.goodsPrice }}</p>
                    <div class="goods-delete">
                        <el-tooltip effect="dark"
                                    content="删除这个宝贝" >
                            <el-icon class="delete-icon"><Delete /></el-icon>
                        </el-tooltip>
                    </div>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>

<style scoped>
#goodsCollection-area {
    width: 830px;
    height: 590px;
    background-color: white;
    margin-left: 20px;
    padding-top: 20px;
    margin-top: 20px;
    border-radius: 10px;
}
#select-area {
    width: 290px;
    height: 30px;
    margin-left: 20px;
    padding-top: 5px;
    border: 1px black solid;
}
#input-area {
    width: 200px;
    height: 25px;
    border: none;
    outline: none;
    padding-left: 10px;
    float: left;
}
#select-button {
    width: 70px;
    height: 21px;
    font-size: 13px;
    text-align: center;
    background-color: rgb(235, 230, 230);
    border: 1px rgb(163, 162, 162) solid;
    padding-top: 3px;
    margin-left: 5px;
    cursor: pointer;
    float: left;
}
#goods-area {
    height: 535px;
    margin-top: 20px;
}
.goods-single {
    width: 180px;
    height: 240px;
    background-color: rgb(250, 250, 250);
    border: 2px rgb(220, 215, 215) solid;
    margin-left: 20px;
    margin-bottom: 20px;
    cursor: pointer;
    float: left;
}
.goods-single:hover {
    border: 2px orangered solid;
}
.goods-single-picture {
    width: 140px;
    height: 140px;
    margin-left: 20px;
    margin-top: 20px;
}
.goods-single-name {
    width: 140px;
    height: 20px;
    margin-left: 20px;
    margin-top: 10px;
    font-size: 14px;
    overflow: hidden;
}
.goods-single-price {
    width: 140px;
    height: 20px;
    margin-left: 20px;
    margin-top: 10px;
    font-size: 18px;
    text-align: center;
    font-weight: bolder;
    float: left;
}
.goods-delete {
    width: 25px;
    height: 25px;
    background-color: rgb(81, 76, 76);
    margin-left: 155px;
    margin-top: 21px;
    opacity: 0;
}
.delete-icon {
    margin-top: 5px;
    color: white;
}
.goods-single:hover .goods-delete {
    opacity: 1;
}
</style>