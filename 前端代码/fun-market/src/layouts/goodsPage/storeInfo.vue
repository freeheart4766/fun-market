<script setup>
import { useRouter } from 'vue-router'
import { axiosInstance } from '../../utils/request.js';
import { defineSignId } from '../../store/store.js'
import { onMounted, reactive } from 'vue';

let router = useRouter();
let defineSignIds = defineSignId();

let storeInfo = reactive({
    sellerId : '',
    storeName : '',
    portraitPath : '',
})
let storeScore = reactive({
    sellerId : '',
    productDescription : '5.0',
    sellerService : '4.9',
    logisticsService : '4.8',
})

let toMessage = () => {
    router.push('/personal/message');
}
let toStore = () => {
    defineSignIds.setSellerId(storeInfo.sellerId);
    router.push('/store');
}
//获取店铺信息
let getStoreInfoByCustomer = () => {
    let goodsId = defineSignIds.getGoodsId;
    axiosInstance.get(`/storeInfo/getStoreInfoByCustomer/${goodsId}`)
    .then((response) => {
        storeInfo.sellerId = response.data.data.sellerId;
        storeInfo.storeName = response.data.data.storeName;
        storeInfo.portraitPath = response.data.data.portraitPath;
        getPicture(storeInfo.portraitPath);
    })
}
//获取图片
let getPicture = (picturePath) => {
    let index = picturePath.lastIndexOf('\\');
    picturePath = picturePath.substring(index + 1);
    axiosInstance.get(`/system/getPicture/${picturePath}`,{responseType:'blob'})
    .then((response) => {
        storeInfo.portraitPath = URL.createObjectURL(new Blob([response.data]));
    })
    .catch((errors) => {
        console.log(errors);    
    });
}
//前往顶部
let toTop = () => {
    window.scrollTo({  
    top: 0,  
  });  
}
onMounted(() => {
    toTop();
    getStoreInfoByCustomer();
})
</script>

<template>
    <div class="store-area">
        <img class="store-picture" v-bind:src="storeInfo.portraitPath">
        <p class="store-name">{{ storeInfo.storeName }}</p>
        <p class="separator">|</p>
        <div class="score-area">
            <p class="score-title">宝贝描述</p>
            <p class="score-context">{{ storeScore.productDescription }}</p>
        </div>
        <div class="score-area">
            <p class="score-title">卖家服务</p>
            <p class="score-context">{{ storeScore.sellerService}}</p>
        </div>
        <div class="score-area">
            <p class="score-title">物流服务</p>
            <p class="score-context">{{ storeScore.logisticsService }}</p>
        </div>
        <div class="button-to" @click="toStore()">
            <svg t="1706262707507" class="store-icon-right icon-same" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2522">
                <path d="M940.960749 425.143816c0.093121-17.86488-2.838651-35.791158-8.207939-52.113915-0.061398-0.463558-0.153496-0.89437-0.246617-1.26583-0.370437-1.788739-0.86367-3.517103-1.573845-5.183046L858.609159 186.54565c-12.527314-37.334303-48.50369-60.228733-90.589205-60.32083L260.369006 126.22482c-42.641169 0-75.778894 22.678512-87.781252 58.531068L94.833879 368.463909c-0.370437 1.079588-0.771573 2.684132-1.110287 4.319375-5.646604 17.339924-8.485255 35.143405-8.485255 53.008284 0.047072 47.364751 19.658735 91.617627 53.040007 123.1856l0.214894 269.90315c0 43.50484 35.452443 78.895885 79.049381 78.895885l580.622914-0.370437c43.535539-0.093121 78.957283-35.57524 78.957283-79.111802l-0.058328-260.299421C917.076782 526.08888 940.986331 477.395878 940.960749 425.143816zM798.072411 835.695287l-580.529793 0.370437c-9.564843 0-17.339924-7.713682-17.339924-17.217127l-0.185218-232.914724c16.764825 5.293563 35.163871 7.983835 55.045687 7.983835 50.632167-0.153496 97.531314-22.400173 129.496329-60.47535 31.81152 37.766139 78.340229 59.858296 128.880299 60.166311 50.138934-0.401136 96.543823-22.586414 128.200824-60.32083 31.965016 38.075177 78.926584 60.228733 129.805368 60.228733 15.559371-0.077771 30.233582-1.77646 43.916209-5.060249l0.051165 229.867318C815.413358 827.889507 807.606555 835.664587 798.072411 835.695287zM823.065641 520.114818c-13.977339 7.652284-31.410384 11.570523-51.68208 11.69332-37.58092 0-71.828932-19.068288-91.823312-51.434439-1.419326-3.055592-3.795443-8.114818-8.238638-12.989849-5.090948-5.708002-14.346753-12.464893-29.588899-12.464893-12.279674 0-23.418362 5.028527-29.712719 12.743232-4.16588 4.689812-6.448876 9.348925-8.084119 12.742209-19.74674 31.966039-53.718459 51.218522-90.527806 51.496861-37.272905-0.215918-71.335698-19.407002-91.206258-51.619658-1.388627-2.838651-3.703345-7.621585-7.312546-11.663644-14.502295-17.309224-46.960545-16.414855-59.610656-1.326205-4.659113 5.090948-7.096628 10.212596-8.670473 13.700023-19.931958 31.965016-54.211692 51.126424-91.607394 51.249221-19.931958 0-36.994566-3.734044-50.755987-11.07729l-0.030699 0c0 0 0 0-0.030699 0-35.297924-18.789948-57.235562-55.32198-57.266261-95.309716 0-11.81714 1.974981-23.727401 5.92392-35.328623 0.277316-0.832971 0.523933-1.759063 0.740874-2.715855l76.581166-181.054589c1.573845-4.628414 6.325056-18.72855 30.144554-18.72855l507.805468 0c9.780761 0.586354 26.628474 2.313695 32.613792 19.931958l71.706135 178.555675c0.278339 1.295506 0.617054 2.529613 0.926092 3.547803 3.980661 11.631922 5.954619 23.449062 5.954619 35.175127C879.375112 464.761116 857.80791 501.107929 823.065641 520.114818z" fill="#000000" p-id="2523"></path>
            </svg>
            <p class="store-content">进入店铺</p>
        </div>
        <div class="button-to" @click="toMessage()">
            <svg t="1708225508751" class="store-icon-left icon-same" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6316">
                <path d="M512 1023.999997c-279.468493 0-506.831581-227.364647-506.831581-506.831581 0-107.898985 33.386946-210.782677 96.650443-297.759655 5.715501-21.372698-0.809371-103.31099-18.673235-159.37436-5.038686-15.883322-1.124386-33.274663 10.259831-45.468251 11.474667-12.149923 28.43715-17.212 44.725938-13.229084 62.723917 15.501249 108.349676 43.150861 141.061366 65.51383 71.588011-37.053287 151.455312-56.514061 232.807239-56.514061 279.468493 0 506.831581 227.363088 506.831581 506.831581C1018.831581 796.63535 791.468493 1023.999997 512 1023.999997zM189.920226 119.856122c8.279289 55.165109 11.114427 120.385756-11.923798 151.478704-52.780661 71.566179-80.676671 156.584447-80.676671 245.83359 0 228.648101 186.011869 414.680243 414.680243 414.680243S926.680243 745.816517 926.680243 517.168416c0-228.646541-186.011869-414.680243-414.680243-414.680243-74.87228 0-148.171043 20.112637-211.974121 58.20142-15.613532 9.291392-35.141363 8.527246-50.034414-1.799642l-6.975562-4.927962C228.302192 143.613267 210.889018 131.329229 189.920226 119.856122z" fill="#000000" p-id="6317"></path>
                <path d="M544.173669 337.951209c-32.083219 0-58.181147 29.404029-58.181147 65.580888l0 68.392633c0 36.176859 26.096368 65.580888 58.181147 65.580888 31.991209 0 58.132803-29.404029 58.132803-65.580888l0-68.392633C602.306471 367.355238 576.163319 337.951209 544.173669 337.951209L544.173669 337.951209z" fill="#000000" p-id="6318"></path>
                <path d="M748.632648 337.951209c-32.081659 0-58.182706 29.404029-58.182706 65.580888l0 68.392633c0 36.176859 26.097928 65.580888 58.182706 65.580888 31.991209 0 58.132803-29.404029 58.132803-65.580888l0-68.392633C806.76545 367.355238 780.623857 337.951209 748.632648 337.951209L748.632648 337.951209z" fill="#000000" p-id="6319"></path>
            </svg>
            <p class="store-content">联系客服</p>
        </div>
    </div>
</template>

<style scoped>
.store-area {
    width: 1200px;
    height: 80px;
    background-color: white;
    margin-left: 170px;
    border-radius: 40px;
    margin-top: 15px;
    overflow: hidden;
}
.store-picture {
    width: 60px;
    height: 60px;
    margin: 10px;
    border-radius: 30px;
    float: left;
}
.store-name {
    width: auto;
    height: 30px;
    font-size: 20px;
    font-weight: bolder;
    margin-top: 15px;
    float: left;
}
.separator {
    width: 25px;
    height: 40px;
    font-size: 25px;
    font-weight: lighter;
    text-align: right;
    color: rgb(231, 231, 226);
    margin-top: 20px;
    float: left;
}
.score-area {
    width: 80px;
    height: 80px;
    margin-left: 10px;
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
.button-to {
    width: 110px;
    height: 40px;
    background-color: rgb(250, 250, 250);
    border: 1px rgb(228, 228, 228) solid;
    border-radius: 20px;
    cursor: pointer;
    margin-top: 20px;
    margin-right: 20px;
    float: right;
}
.icon-same {
    margin-left: 12px;
    float: left;
}
.store-icon-right {
    width: 20px;
    margin-top: 10px;
}
.store-icon-left {
    width: 17px;
    margin-top: 11px;
}
.store-content {
    margin-top: 8px;
    margin-left: 5px;
    float: left;
}
</style>