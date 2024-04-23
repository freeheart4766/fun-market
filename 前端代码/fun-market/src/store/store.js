import { defineStore } from 'pinia';

export const defineSignId = defineStore(
    {
        id : 'signIdPinia',
        state : () => {
            return {
                receivingInfoId : '',
                goodsId : '',
                goodsName : '',
                goodsType : '',
                goodsSingleTypesId : '',
                sellerId : '',
            }
        },
        getters : {
            getReceivingInfoId(){
                return this.receivingInfoId;
            },
            getGoodsId(){
                return this.goodsId;
            },
            getGoodsName(){
                return this.goodsName;
            },
            getGoodsType(){
                return this.goodsType;
            },
            getGoodsSingleTypesId(){
                return this.goodsSingleTypesId;
            },
            getSellerId(){
                return this.sellerId;
            },
        },
        actions : {
            setReceivingInfoId(receivingInfoIdNew){
                this.receivingInfoId = receivingInfoIdNew;
            },
            setGoodsId(goodsIdNew){
                this.goodsId = goodsIdNew;
            },
            setGoodsName(goodsNameNew){
                this.goodsName = goodsNameNew;
            },
            setGoodsType(goodsTypeNew){
                this.goodsType = goodsTypeNew;
            },
            setGoodsSingleTypesId(goodsSingleTypesIdNew){
                this.goodsSingleTypesId = goodsSingleTypesIdNew;
            },
            setSellerId(sellerIdNew){
                this.sellerId = sellerIdNew;
            },
        }
    }
)