<script setup>
import { ref } from 'vue'
import { storeInfo, storeScore } from '../../utils/pojo.js'

let storeFollow = ref(0);

let changeFollowing = () => {
    if(storeFollow.value == 0){
        storeFollow.value = 1;
    }else {
        storeFollow.value = 0;
    }
}

let followWords = () => {
    if(storeFollow.value == 0){
        return '关注店铺';
    }else {
        return '取消关注';
    }
}
</script>

<template>
    <div class="store-all">
        <div class="store-info-area">
            <img class="store-picture" v-bind:src="storeInfo.portraitPath">
            <div class="store-info">
                <p class="store-name">{{ storeInfo.storeName }}</p>
                <el-tooltip effect="light" v-bind:content="followWords()" placement="right-start" >
                    <div class="follow-icon-area" @click="changeFollowing()">
                        <svg v-if="storeFollow == 0" t="1708506821326" class="follow-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3379">
                            <path d="M697.21791 135.568628c-90.775446 0-155.800415 81.796958-183.823656 125.059274-28.053939-43.262316-93.049233-125.059274-183.822632-125.059274-130.257669 0-236.19442 117.381407-236.19442 261.648139 0 67.460438 45.757137 164.17106 101.302197 214.784808 76.835969 98.719372 292.845675 276.43082 319.512011 276.43082 27.13808 0 238.512209-174.257789 316.736804-275.247878 56.519248-51.500954 102.498442-148.344606 102.498442-215.96775C933.426657 252.949013 827.47251 135.568628 697.21791 135.568628M877.732194 397.216767c0 53.951773-39.127136 134.464481-85.429695 175.805028-1.2689 1.153266-2.421142 2.422166-3.573385 3.811816-68.833715 89.683579-207.417003 200.922079-274.390347 249.644757C464.55185 790.038434 314.598606 676.495448 237.615281 576.479547c-1.033539-1.358951-2.24411-2.629897-3.51301-3.811816-46.127574-41.282219-85.045955-121.588219-85.045955-175.450964 0-113.571638 80.969103-205.984375 180.515307-205.984375 64.167438 0 125.944434 69.453838 158.694325 138.317229 4.339842 9.124821 13.967106 14.808264 25.12933 14.808264s20.788465-5.684466 25.157983-14.808264c32.720216-68.863391 94.466512-138.317229 158.665673-138.317229C796.761044 191.232392 877.732194 283.645129 877.732194 397.216767" fill="#000000" p-id="3380"></path>
                        </svg>
                        <svg v-else t="1708507017982" class="follow-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3629">
                            <path d="M697.21791 135.568628c-90.775446 0-155.800415 81.796958-183.823656 125.059274-28.053939-43.262316-93.049233-125.059274-183.822632-125.059274-130.257669 0-236.19442 117.381407-236.19442 261.648139 0 67.460438 45.757137 164.17106 101.302197 214.784808 76.835969 98.719372 292.845675 276.43082 319.512011 276.43082 27.13808 0 238.512209-174.257789 316.736804-275.247878 56.519248-51.500954 102.498442-148.344606 102.498442-215.96775C933.426657 252.949013 827.47251 135.568628 697.21791 135.568628" fill="#f42207" p-id="3630"></path>
                        </svg>
                    </div>
                </el-tooltip>
                <p class="open-date">开店日期 : {{ storeInfo.openingDate }}</p>
            </div>
            <p class="separator">|</p>
            <div class="score-area">
                <p class="score-title">宝贝描述</p>
                <p class="score-context">{{ storeScore.productDescription }}</p>
            </div>
            <div class="score-area">
                <p class="score-title">卖家服务</p>
                <p class="score-context">{{ storeScore.sellerService }}</p>
            </div>
            <div class="score-area">
                <p class="score-title">物流服务</p>
                <p class="score-context">{{ storeScore.logisticsService }}</p>
            </div>
            <div class="goods-search">
                <input class="goods-input" placeholder="搜索宝贝">
                <button class="search-button">店内搜索</button>
            </div>
        </div>
        <img class="store-cover" v-bind:src="storeInfo.storeCoverPicture">
    </div>
</template>

<style scoped>
.store-all {
    background-color: rgb(245, 245, 245);
}
.store-info-area {
    height: 100px;
    background-color: white;
}
.store-picture {
    width: 70px;
    height: 70px;
    border-radius: 35px;
    margin-left: 250px;
    margin-top: 15px;
    float: left;
}
.store-info {
    width: 170px;
    height: 70px;
    margin-left: 10px;
    margin-top: 20px;
    float: left;
}
.store-name {
    width: 130px;
    height: 30px;
    font-size: 18px;
    font-weight: bolder;
    float: left;
}
.follow-icon-area {
    width: 30px;
    height: 30px;
    margin-left: 5px;
    float: left;
}
.follow-icon {
    width: 30px;
    cursor: pointer;
}
.open-date {
    width: 150px;
    height: 20px;
    font-size: 14px;
    color: rgb(77, 77, 77);
    margin-top: 10px;
    float: left;
}
.separator {
    width: 5px;
    height: 40px;
    font-size: 25px;
    font-weight: lighter;
    text-align: right;
    color: rgb(231, 231, 226);
    margin-top: 30px;
    margin-right: 10px;
    float: left;
}
.score-area {
    width: 80px;
    height: 80px;
    margin-left: 10px;
    margin-top: 10px;
    float: left;
}
.score-title {
    width: 80px;
    height: 25px;
    text-align: center;
    color: rgb(113, 113, 113);
    margin-top: 15px;
}
.score-context {
    width: 80px;
    height: 25px;
    text-align: center;
    font-size: 18px;
}
.goods-search {
    width: 350px;
    height: 36px;
    border: 3px orangered solid;
    border-radius: 10px;
    margin-top: 30px;
    margin-left: 150px;
    float: left;
}
.goods-input {
    width: 250px;
    height: 35px;
    font-size: 18px;
    padding-right: 10px;
    margin-left: 10px;
    margin-top: 1px;
    border: none;
    outline: none;
    float: left;
}
.search-button {
    width: 75px;
    height: 30px;
    border-radius: 10px;
    border: none;
    cursor: pointer;
    background-color: orangered;
    color: white;
    margin-top: 3px;
    float: left;
}
.store-cover {
    width: 1000px;
    height: 300px;
    margin-left: 250px;
}
.picture-show {
    width: 1000px;
    height: 300px;
    margin-left: 250px;
}
.picture-show-single {
    width: 1000px;
    height: 300px;
}
</style>