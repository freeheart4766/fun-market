<script setup>
import { ref, reactive, onMounted } from 'vue';
import { useRouter } from 'vue-router'
import { axiosInstance } from '../../utils/request';
import { defineSignId } from '../../store/store.js'

let router = useRouter();
let defineSignIds = defineSignId();
//商品种类信息的集合
let itemList = ref();
//用户登录状态
let loginStatus = ref();

let toOrder = () => {
    router.push('/order');
}

let goodsNumber = ref(1);
let goodsCollection = ref(0);

let goods = reactive({
    goodsId : '',
    sellerId : '',
    goodsName : '',
    goodsType : '',
    goodsInventory : '',
    salesVolume : '',
    goodsCoverPath : '',
})
//将销量转换展示形式
let salesNumber = () => {
    if(goods.salesVolume / 10000 > 1){
        return Math.floor(goods.salesVolume / 10000) * 10000;
    }else if(goods.salesVolume > 1000){
        return Math.floor(goods.salesVolume / 1000) * 1000;
    }else if(goods.salesVolume > 100){
        return Math.floor(goods.salesVolume / 100) * 100;
    }else if(goods.salesVolume > 10){
        return Math.floor(goods.salesVolume / 10) * 10;
    }else {
        return goods.salesVolume;
    }
}
//修改分类点击后的样式
let changeTypeActive = () => {
    let items = document.querySelectorAll('.types-single');
    let itemsOther = document.querySelectorAll('.picture-single-small');
    items.forEach((item)=>{
        item.addEventListener('click',() => {
            items.forEach(item => {
                item.classList.remove('active-type');
            });
            itemsOther.forEach(item => {
                item.classList.remove('active');
            });
            item.classList.add('active-type');
        })
    });
}
//当数量为空时，将数量设为1
let changeNumber = () => {
    if(goodsNumber.value == null){
        goodsNumber.value = 1;
    }
}
//改变收藏的样式
let changeCollection = () => {
    let collectionWords = document.getElementById("words");
    if(goodsCollection.value == 0){
        goodsCollection.value = 1;
        collectionWords.style.color = 'red';
    }else {
        goodsCollection.value = 0;
        collectionWords.style.color = 'black';
    }
}

//未登录的顾客获取商品信息
let getGoodsByCustomerWithoutLogin = () => {
    axiosInstance.get(`/goods/getGoodsByCustomerWithoutLogin/${goods.goodsId}`)
    .then((response) => {
        goods.goodsName = response.data.data.goodsName;
        goods.goodsInventory = response.data.data.goodsInventory;
        goods.salesVolume = response.data.data.salesVolume;
        goods.goodsCoverPath = response.data.data.goodsCoverPath;
        getPicture(goods.goodsCoverPath);
    })
}
//已登录的顾客获取商品信息
let getGoodsByCustomer = () => {
    axiosInstance.get(`/goods/getGoodsByCustomer/${goods.goodsId}`)
    .then((response) => {
        goods.goodsName = response.data.data.goodsName;
        goods.goodsInventory = response.data.data.goodsInventory;
        goods.salesVolume = response.data.data.salesVolume;
        goods.goodsCoverPath = response.data.data.goodsCoverPath;
        getPicture(goods.goodsCoverPath);
    })
}
//获取图片
let getPicture = (picturePath) => {
    let index = picturePath.lastIndexOf('\\');
    picturePath = picturePath.substring(index + 1);
    axiosInstance.get(`/system/getPicture/${picturePath}`,{responseType:'blob'})
    .then((response) => {
        goods.goodsCoverPath = URL.createObjectURL(new Blob([response.data]));
    })
    .catch((errors) => {
        console.log(errors);    
    });
}
//根据是否登录，获取商品信息
let getGoodsInfo = () => {
    loginStatus.value = window.sessionStorage.getItem('token');
    goods.goodsId = defineSignIds.getGoodsId;
    if(loginStatus.value == null || loginStatus.value == ''){
        getGoodsByCustomerWithoutLogin();
    }else{
        getGoodsByCustomer();
        getAllSingleTypes();
    }
}
//当用户登录后，获取此商品的所有种类信息
let getAllSingleTypes = () => {
    axiosInstance.get(`/goodsSingleTypes/getAllSingleTypes/${goods.goodsId}`)
    .then((response) => {
        itemList.value = response.data.data;
    })
}
//当点击商品种类时，将id写入Pinia
let setGoodsSingleType = (goodsSingleTypesId) => {
    defineSignIds.setGoodsSingleTypesId(goodsSingleTypesId);
}
onMounted(() => {
    getGoodsInfo();
})
</script>

<template>
    <div class="goods-info-area">
        <div class="picture-area">
            <div class="picture-big">
                <img class="picture-middle" v-bind:src="goods.goodsCoverPath">
            </div>
        </div>
        <div class="info-area">
            <p class="goods-name">{{ goods.goodsName }}</p>
            <div class="sales-number">
                <p class="sales-content">已售 {{ salesNumber() }}</p>
                <p class="sales-content" v-if="goods.salesVolume > 10">+</p>
            </div>
            <div class="goods-type">
                <p class="goods-title">分类 :</p>
                <div class="types-area" @mouseover="changeTypeActive()">
                    <div class="types-single" v-for="goodsSingleTypes in itemList" :key="goodsSingleTypes.goodsSingleTypesId" @click="setGoodsSingleType(goodsSingleTypes.goodsSingleTypesId)">
                        <p class="types-word-single">{{ goodsSingleTypes.typesName }}</p>
                        <p class="types-price-single">￥{{ goodsSingleTypes.goodsPrice }}</p>
                    </div>
                </div>
            </div>
            <div class="goods-type" v-if="loginStatus != null && loginStatus != ''">
                <p class="goods-title">数量 :</p>
                <el-input-number class="goods-number" v-model="goodsNumber" v-bind:min="1" @change="changeNumber()"/>
                <p class="goods-goods-number" v-if="goods.goodsInventory >= goodsNumber">有货</p>
                <p class="goods-goods-number" v-else>库存不足</p>
            </div>
            <div class="goods-type" v-if="loginStatus != null && loginStatus != ''">
                <button class="goods-button buy-now" @click="toOrder()">立即购买</button>
                <button class="goods-button buy-later">加入购物车</button>
                <p class="button-separation">|</p>
                <div class="goods-collection-area" @click="changeCollection()">
                    <svg v-if="goodsCollection == 0" t="1708406780130" class="collection-icon-no" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6378" id="mx_n_1708406780131">
                        <path d="M928.891803 415.759306c-4.862772-14.704022-18.294016-25.021413-35.998232-27.56697L655.722604 354.079209 549.658066 141.429964c-7.909044-15.885669-21.976165-25.384918-37.634515-25.384918s-29.737759 9.499249-37.634515 25.362391L368.313235 354.079209 131.141244 388.192336c-17.726743 2.545557-31.180514 12.862948-36.009496 27.589497-4.841269 14.726549-0.057342 30.908142 12.738025 43.247848L279.510565 624.578952l-40.52105 233.762214c-2.420634 13.95551 0.670692 26.818458 8.693395 36.226574 7.260879 8.545945 18.055434 13.409741 29.6241 13.409741 7.499461 0 15.090054-1.95371 22.578252-5.864202L512.023551 791.754266l212.127027 110.359013c19.022049 9.907808 40.226151 6.500079 52.202352-7.545539 8.043182-9.408117 11.089454-22.271065 8.682131-36.226574l-40.498523-233.762214L916.165041 459.029681C928.982935 446.643897 933.710544 430.485856 928.891803 415.759306zM690.719406 592.580296c-7.13698 6.909662-10.409547 16.81747-8.726162 26.591139l36.77132 212.263213-192.607334-100.221839c-8.861324-4.635453-19.408081-4.635453-28.270429 0l-192.627814 100.221839 36.793847-212.263213c1.692601-9.77367-1.568702-19.681477-8.726162-26.591139l-155.846254-150.32303 215.365803-30.975723c9.885281-1.431492 18.431226-7.568066 22.862912-16.431438l96.314419-193.104978 96.315443 193.104978c4.430662 8.863372 12.976607 14.998922 22.861888 16.431438l215.37809 30.975723L690.719406 592.580296z" fill="#000000" p-id="6379"></path>
                    </svg>
                    <svg v-else t="1708406947130" class="collection-icon-yes" viewBox="0 0 1110 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6808">
                        <path d="M881.74273 1023.520254C877.699159 1023.520254 873.655588 1022.595031 869.954693 1020.710315L554.624717 860.132586 239.329009 1020.710315C230.830657 1025.062294 220.413323 1024.342675 212.600322 1018.8256 204.787321 1013.342793 200.880821 1004.02202 202.525663 994.804049L262.733744 654.630101 7.680386 413.72924C0.758341 407.184138-1.74319 397.417887 1.238087 388.542592 4.253631 379.633029 12.169434 373.156463 21.73008 371.785761L374.240345 322.166352 531.871066 12.661859C540.403685-4.12924 568.880017-4.12924 577.378369 12.661859L735.043357 322.166352 1087.553622 371.785761C1097.114268 373.156463 1105.030072 379.633029 1108.045616 388.542592 1110.992625 397.417887 1108.525362 407.184138 1101.603317 413.72924L846.51569 654.630101 906.75804 994.804049C908.402882 1004.02202 904.462114 1013.342793 896.683381 1018.8256 892.262867 1021.943947 887.019932 1023.520254 881.74273 1023.520254Z" p-id="6809" fill="rgb(255, 77, 77)"></path>
                    </svg>
                    <p id="words" class="collection-word">收藏</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.goods-info-area {
    width: 1200px;
    background-color: white;
    margin-left: 170px;
    border-top-left-radius: 20px;
    border-top-right-radius: 20px;
    margin-top: 15px;
    padding-top: 15px;
    padding-bottom: 50px;
    overflow: hidden;
}
.picture-area {
    width: 450px;
    height: 510px;
    margin-left: 15px;
    overflow: hidden;
    float: left;
}
.picture-big {
    width: 450px;
    height: 420px;
    border-radius: 10px;
    background-color: rgb(240, 240, 240);
}
.picture-middle {
    width: 350px;
    height: 420px;
    border-radius: 10px;
    margin-left: 50px;
}
.info-area {
    width: 670px;
    margin-left: 30px;
    padding-bottom: 30px;
    float: left;
}
.goods-name {
    width: 670px;
    height: 30px;
    font-size: 20px;
    font-weight: bolder;
    overflow: hidden;
    margin-top: 5px;
}
.sales-number {
    width: 300px;
    height: 20px;
    color: rgb(180, 180, 180);
}
.sales-content {
    float: left;
}
.goods-type {
    width: 670px;
    margin-top: 15px;
    overflow: hidden;
}
.goods-title {
    width: 60px;
    height: 30px;
    color: rgb(119, 119, 119);
    margin-top: 8px;
    float: left;
}
.types-area {
    width: 500px;
    float: left;
}
.types-single {
    width: fit-content;
    max-width: 400px;
    height: 40px;
    background-color: rgb(240, 240, 240);
    border-radius: 5px;
    margin-bottom: 10px;
    padding-right: 10px;
    cursor: pointer;
    border: 1px rgb(240, 240, 240) solid;
}
.types-single:not(.active-type):hover {
    background-color: rgb(230, 230, 230);
}
.types-word-single {
    height: 20px;
    margin-top: 10px;
    margin-left: 10px;
    float: left;
}
.active-type{
    border: 1px orangered solid;
    color: orangered;
    background-color: rgb(255, 223, 223);
}
.types-price-single {
    height: 20px;
    margin-top: 10px;
    margin-left: 50px;
    color: rgb(255, 81, 17);
    float: left;
}
.goods-number {
    width: 120px;
    margin-top: 5px;
    float: left;
}
.goods-goods-number {
    font-size: 14px;
    color: rgb(120, 120, 120);
    margin-top: 10px;
    margin-left: 10px;
    float: left;
}
.goods-button {
    width: 150px;
    height: 50px;
    font-size: 18px;
    color: white;
    border-radius: 25px;
    border: none;
    cursor: pointer;
    margin-top: 20px;
    margin-right: 20px;
    float: left;
}
.buy-now {
    background-color: red;
}
.buy-later {
    background-color: orange;
}
.button-separation {
    margin-top: 32px;
    font-size: 18px;
    font-weight: lighter;
    color: rgb(179, 178, 178);
    float: left;
}
.goods-collection-area {
    width: 80px;
    height: 30px;
    border-radius: 15px;
    margin-top: 30px;
    margin-left: 20px;
    cursor: pointer;
    float: left;
}
.goods-collection-area:hover {
    background-color: rgb(235, 235, 235);
}
.collection-icon-no {
    width: 23px;
    margin-left: 10px;
    margin-top: 3px;
    float: left;
}
.collection-icon-yes {
    width: 20px;
    margin-left: 11px;
    margin-top: 5px;
    margin-right: 2px;
    float: left;
}
.collection-word {
    margin-top: 5px;
    margin-left: 2px;
    float: left;
}
</style>