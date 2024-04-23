<script setup>
import { useRouter } from 'vue-router';
import { evaluation, order } from '../../../utils/pojo.js'

let router = useRouter();

let toMyEvaluation = () => {
    router.push('/personal/myEvaluation');
}
</script>

<template>
    <div id="evaluation-area">
        <div id="evaluation-title">
            <div class="goods-description title-same">宝贝描述</div>
            <div class="goods-evaluation-level title-same">评价等级</div>
            <div class="goods-content title-same">评价内容</div>
            <div class="evaluation-time title-same">评价时间</div>
            <div class="evaluation-operation title-same">操作</div>
        </div>
        <div id="evaluation-all-area">
            <el-scrollbar height="520px" always="true">
                <div class="evaluation-single">
                    <div class="goods-description goods-same">
                        <img class="goods-description-picture" v-bind:src="goodsShowPicture.pitcureOnePath">
                        <p class="goods-name goods-info">{{ order.goodsName }}</p>
                        <p class="goods-type goods-info">{{ order.goodsDescription }}</p>
                    </div>
                    <div class="goods-evaluation-level goods-same">
                        <p class="evaluation-level-content text-type-same">{{ evaluation.evaluationLevel }}</p>
                    </div>
                    <div class="goods-content goods-same">
                        <p class="goods-evaluation-content">{{ evaluation.evaluationContent }}</p>
                    </div>
                    <div class="evaluation-time goods-same">
                        <p class="text-type-same">{{ evaluation.date }}</p>
                    </div>
                    <div class="evaluation-operation goods-same-right">
                        <p class="evaluation-view text-type-same" @click="toMyEvaluation()">查看</p>
                    </div>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>

<style scoped>
#evaluation-area {
    width: 830px;
    height: 590px;
    background-color: white;
    margin-left: 20px;
    margin-top: 20px;
    border-radius: 10px;
    padding-top: 20px;
}
#evaluation-title {
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
.goods-description {
    width: 230px;
}
.goods-evaluation-level {
    width: 106px;
}
.goods-content {
    width: 230px;
}
.evaluation-time {
    width: 120px;
}
.evaluation-operation {
    width: 100px;
}
#evaluation-all-area {
    margin-top: 20px;
    height: 500px;
}
.evaluation-single {
    width: 790px;
    height: 100px;
    background-color: rgb(240, 240, 240);
    margin-left: 20px;
    margin-bottom: 20px;
    border-radius: 5px;
}
.goods-same {
    height: 100px;
    float: left;
    border-right: 1px rgb(220, 215, 215) solid;
}
.goods-same-right {
    height: 100px;
    float: left;
}
.goods-description-picture {
    width: 80px;
    height: 80px;
    margin-left: 10px;
    margin-top: 10px;
    float: left;
}
.goods-info {
    width: 120px;
    margin-left: 10px;
    margin-top: 15px;
    font-size: 14px;
    overflow: hidden;
    float: left;
}
.goods-name {
    height: 40px;
}
.goods-type {
    height: 20px;
}
.evaluation-level-content {
    font-size: 20px;
}
.goods-evaluation-content {
    width: 190px;
    height: 60px;
    font-size: 14px;
    overflow: hidden;
    margin: 20px;
}
.text-type-same {
    text-align: center;
    margin-top: 35px;
}
.evaluation-view {
    font-size: 18px;
    cursor: pointer;
}
.evaluation-view:hover {
    color: rgb(254, 88, 27);
}
</style>