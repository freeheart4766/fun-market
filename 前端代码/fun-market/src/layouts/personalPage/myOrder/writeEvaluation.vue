<script setup>
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { evaluation, order } from '../../../utils/pojo.js'
let router = useRouter();

let toMyOrder = () => {
    router.push('/personal/myOrder');
}

let changeActive = () => {
    let items = document.querySelectorAll('.level-single');
    items.forEach((item)=>{
        item.addEventListener('click',() => {
            items.forEach(item => {
                item.classList.remove('active');
            });
            item.classList.add('active');
        })
    });
}

let starLevel = ['非常差', '差', '一般', '好', '非常棒'];

let levelGood = () => {
    evaluation.evaluationLevel = '好评';
}
let levelmiddle = () => {
    evaluation.evaluationLevel = '中评';
}
let levelBad = () => {
    evaluation.evaluationLevel = '差评';
}

let wordsNumber = computed(() => {
    return evaluation.evaluationContent.length;
})
</script>

<template>
    <div class="evaluation-area">
        <div class="goods-area">
            <img class="goods-picture" v-bind:src="goodsShowPicture.pitcureOnePath">
            <p class="goods-name goods-same">{{ order.goodsName }}</p>
            <p class="goods-description goods-same">{{ order.goodsDescription }}</p>
        </div>
        <div class="evaluation-all-area">
            <div class="evaluation-single-area" @mouseover="changeActive()">
                <p class="level-title">综合评价</p>
                <div class="level-single" @click="levelBad()">
                    <svg t="1707203732945" class="level-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="7613">
                        <path d="M927.623529 322.258824c-33.129412-48.188235-75.294118-90.352941-126.494117-129.505883-177.694118-123.482353-394.541176-117.458824-487.905883 15.058824-15.058824 21.082353-27.105882 45.176471-33.129411 69.270588-3.011765 3.011765-6.023529 3.011765-9.035294 6.023529-6.023529 6.023529-45.176471 54.211765-33.129412 162.635294 15.058824 138.541176 105.411765 310.211765 271.058823 508.988236 6.023529 6.023529 15.058824 12.047059 24.094118 12.047059 6.023529 0 12.047059-3.011765 18.070588-6.02353 12.047059-9.035294 15.058824-30.117647 3.011765-42.164706-162.635294-198.776471-225.882353-340.329412-249.976471-433.694117 33.129412 69.270588 87.341176 132.517647 159.62353 183.717647 54.211765 36.141176 111.435294 63.247059 165.647059 75.294117 36.141176 9.035294 66.258824-126.494118 66.258823-126.494117s150.588235 12.047059 174.682353-24.094118c24.094118-36.141176-36.141176-174.682353-36.141176-174.682353s114.447059-66.258824 93.364705-96.37647z" p-id="7614"></path>
                        <path d="M870.4 752.941176c-6.023529 0-159.623529 6.023529-289.129412 132.517648-12.047059 12.047059-12.047059 30.117647 0 42.164705 6.023529 6.023529 15.058824 9.035294 21.082353 9.035295 6.023529 0 15.058824-3.011765 21.082353-9.035295 114.447059-108.423529 246.964706-114.447059 249.976471-114.447058 18.070588 0 30.117647-15.058824 30.117647-30.117647-3.011765-18.070588-18.070588-30.117647-33.129412-30.117648zM150.588235 752.941176c-18.070588 0-30.117647 12.047059-30.117647 30.117648s12.047059 30.117647 30.117647 30.117647c0 0 135.529412 6.023529 249.976471 114.447058 6.023529 6.023529 12.047059 9.035294 21.082353 9.035295 9.035294 0 15.058824-3.011765 21.082353-9.035295 12.047059-12.047059 12.047059-30.117647 0-42.164705-132.517647-126.494118-286.117647-132.517647-292.141177-132.517648z" p-id="7615"></path>
                    </svg>
                    <p class="level-context">差评</p>
                </div>
                <div class="level-single" @click="levelmiddle()">
                    <svg t="1707203473734" class="level-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5594">
                        <path d="M752.97792 287.75424c0 0 2.52928-171.52512-58.54208-198.272-61.06624-26.74176-196.2496 89.19552-196.2496 89.19552s-156.83584-158.1568-175.2064-116.20352C277.57056 166.18496 240.64 246.41024 214.528 330.5984c-28.0832 166.07232 28.37504 291.10272 153.12384 366.97088-2.33984 5.46816-4.21888 12.47232-5.504 21.26848-3.05152 72.03328 56.68352 223.78496 80.59904 264.72448 30.85312 0 79.26272 3.0464 79.26272 3.0464s-77.26592-146.82624-81.93024-253.6192c143.70304 54.02112 282.57792 2.93888 393.71264-137.4976 50.48832-63.7952 87.08608-149.6064 129.46944-246.38976C978.51904 314.24512 752.97792 287.75424 752.97792 287.75424z" p-id="5595"></path>
                        <path d="M541.66016 950.87104c29.96736 10.71104 46.85824 6.30784 65.57696 2.70848 52.35712-88.51456 133.02272-107.29984 246.99392-111.52896 5.5808-19.34336 17.53088-33.26976 11.13088-52.5312C794.38848 787.35872 613.7088 785.43872 541.66016 950.87104z" p-id="5596"></path>
                        <path d="M76.30848 789.69856c-15.73888 17.98656-3.23072 49.00864 0 62.59712 121.05728-28.05248 210.3296 44.24192 241.73056 102.1696 15.744-0.1792 55.49568-5.12 69.7088-10.15296C385.5872 892.22144 268.79488 770.37056 76.30848 789.69856z" p-id="5597"></path>
                    </svg>
                    <p class="level-context" id="abc">中评</p>
                </div>
                <div class="level-single" @click="levelGood()">
                    <svg t="1707203845065" class="level-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="8667">
                        <path d="M405.534 932.128c-8.8 0-17.567-3.585-23.902-10.69-83.232-93.215-217.857-94.11-220.639-94.11-0.097 0-0.097 0-0.192 0-17.567 0-31.905-14.18-32.032-31.745-0.128-17.665 14.017-32.065 31.647-32.255 6.657-0.355 165.665-0.195 268.959 115.485 11.777 13.185 10.625 33.41-2.56 45.185C420.734 929.438 413.119 932.128 405.534 932.128zM620.128 932.063c-7.78 0-15.585-2.815-21.76-8.545-12.96-12-13.76-32.255-1.73-45.215 106.975-115.52 262.749-115.395 268.894-115.135 17.695 0.19 31.875 14.655 31.68 32.32-0.19 17.565-14.465 31.68-32 31.68-0.095 0-0.16 0-0.255 0l0 0c-2.655 0-134.56 0.86-221.374 94.62C637.308 928.638 628.703 932.063 620.128 932.063zM563.709 769.308 462.304 769.308c-3.233 0-6.37-0.48-9.345-1.405-108.255-13.89-200.254-76.705-259.552-177.44-68.832-116.992-84.607-268.577-39.2-377.217 4.897-11.68 16.225-19.392 28.897-19.647l9.63-0.095c60.96 0 117.89 11.52 169.697 34.302 37.44-68.447 80.192-120.382 127.327-154.687 2.495-2.175 5.345-4 8.512-5.345l0 0c9.887-4.287 21.377-3.36 30.432 2.53 1.565 1.022 3.04 2.175 4.385 3.455 47.26 34.687 88.83 85.855 126.46 155.745 52.835-23.872 111.075-35.97 173.635-35.97l9.79 0.097c12.675 0.255 24 7.967 28.895 19.647 45.41 108.672 29.665 260.254-39.23 377.249-59.295 100.67-151.265 163.52-259.489 177.44C570.109 768.798 566.974 769.308 563.709 769.308zM512.766 959.998c-17.662 0-32-14.305-32-32l0-190.69c0-17.695 14.337-32 32-32s32.002 14.305 32.002 32l0 190.69C544.734 945.693 530.429 959.998 512.766 959.998z" p-id="8668"></path>
                    </svg>
                    <p class="level-context">好评</p>
                </div>
            </div>
            <div class="evaluation-single-area">
                <p class="level-title">宝贝描述</p>
                <el-rate v-model="evaluation.productDescription" 
                         class="icon-star" 
                         v-bind:texts="starLevel" 
                         show-text />
            </div>
            <div class="evaluation-single-area">
                <p class="level-title">卖家服务</p>
                <el-rate v-model="evaluation.sellerService" 
                         class="icon-star" 
                         v-bind:texts="starLevel" 
                         show-text />
            </div>
            <div class="evaluation-single-area">
                <p class="level-title">物流服务</p>
                <el-rate v-model="evaluation.logisticsService" 
                         class="icon-star" 
                         v-bind:texts="starLevel" 
                         show-text />
            </div>
        </div>
        <div class="evaluation-context">
            <textarea v-model="evaluation.evaluationContent" 
                      class="evaluation-input" 
                      placeholder="展开说说对商品的想法吧..." 
                      maxlength="100">
            </textarea>
            <p class="words-count">{{ wordsNumber }} / 100</p>
        </div>
        <div class="button-area">
            <div class="evaluation-button">
                <svg t="1707290739107" class="release-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2644">
                    <path d="M300.6 900.6c-5.1-4.9-8.3-11.8-8.3-19.5V698.9c-1.1-8.1 1.4-16.7 7.7-22.9l268.5-268.5c10.6-10.6 27.8-10.6 38.4 0 10.6 10.6 10.6 27.8 0 38.4L346.5 706.2v114.1L456 710.7c10.6-10.6 27.8-10.6 38.4 0l99.4 99.4 157.8-532.5-532.4 157.9 82.1 82.1c1 0.8 2 1.6 2.9 2.5 10.6 10.6 10.6 27.8 0 38.4-10.5 10.5-27.5 10.6-38.1 0.2l-0.2 0.2-161.7-161.7L828.4 201 632.3 925.2l-157-157-134.5 134.5c-10.6 10.6-27.8 10.6-38.4 0-0.6-0.7-1.3-1.4-1.8-2.1z" p-id="2645" fill="#ffffff"></path>
                </svg>
                <p class="release-word">发布</p>
            </div>
            <button class="button-return" @click="toMyOrder()">取消并返回</button>
        </div>
    </div>
</template>

<style scoped>
.evaluation-area {
    width: 830px;
    height: 590px;
    margin-left: 20px;
    margin-top: 30px;
    padding-top: 10px;
    background-color: white;
    border-radius: 10px;
}
.goods-area {
    width: 580px;
    height: 150px;
    background-color: rgb(245, 245, 245);
    margin-left: 120px;
    margin-top: 50px;
    border: 3px rgb(142, 140, 140) solid;
}
.goods-picture {
    width: 150px;
    height: 150px;
    float: left;
}
.goods-same {
    width: 350px;
    font-size: 18px;
    margin-left: 30px;
    float: left;
}
.goods-name {
    height: 50px;
    margin-top: 25px;
}
.goods-description {
    height: 25px;
    margin-top: 30px;
}
.evaluation-all-area {
    width: 420px;
    height: 200px;
    margin-left: 70px;
    margin-top: 30px;
    float: left;
}
.evaluation-single-area {
    width: 420px;
    height: 30px;
    margin-bottom: 20px;
}
.level-title {
    font-size: 25px;
    font-family: 'KaiTi';
    font-weight: bolder;
    float: left;
}
.level-single {
    width: 75px;
    height: 30px;
    margin-left: 15px;
    cursor: pointer;
    color: rgb(200, 198, 198);
    float: left;
}
.level-single:hover {
    color: rgb(249, 127, 52);
}
.level-single:hover .level-icon{
    fill: rgb(249, 127, 52);
}
.active {
    color: rgb(249, 127, 52);
}
.active .level-icon {
    fill: rgb(249, 127, 52);
}
.level-context {
    width: 50px;
    height: 30px;
    font-size: 25px;
    font-family: 'KaiTi';
    font-weight: bolder;
    float: left;
}
.level-icon {
    width: 25px;
    height: 25px;
    fill: rgb(200, 198, 198);
    cursor: pointer;
    float: left;
}
.icon-star {
    margin-left: 35px;
    font-weight: bolder;
}
.evaluation-context {
    width: 300px;
    height: 200px;
    border: 2px rgb(219, 216, 216) solid;
    margin-top: 30px;
    margin-left: -30px;
    float: left;
}
.evaluation-input {
    width: 200px;
    height: 170px;
    font-size: 16px;
    margin: 15px;
    resize: none;
    border: none;
    outline: none;
    float: left;
}
.words-count {
    width: 60px;
    height: 20px;
    font-size: 12px;
    margin-right: 5px;
    margin-top: 5px;
    text-align: right;
    float: right;
}
.button-area {
    width: 400px;
    height: 50px;
    margin-left: 300px;
    margin-top: 30px;
    float: left;
}
.evaluation-button {
    width: 150px;
    height: 50px;
    border-radius: 10px;
    border: none;
    background-color: rgb(248, 83, 37);
    cursor: pointer;
    float: left;
}
.evaluation-button:hover {
    background-color: rgb(254, 103, 61);
}
.release-icon {
    width: 30px;
    height: 30px;
    margin-left: 40px;
    margin-top: 8px;
    float: left;
}
.release-word {
    width: 50px;
    font-size: 20px;
    color: white;
    margin-top: 10px;
    float: left;
}
.button-return {
    width: 100px;
    height: 30px;
    border-radius: 5px;
    background-color: rgb(37, 136, 248);
    color: white;
    cursor: pointer;
    border: none;
    margin-left: 150px;
    margin-top: 20px;
    float: left;
}
.button-return:hover {
    background-color: rgb(79, 160, 251);
}
</style>