// 导入路由创建的相关方法
import {createRouter,createWebHashHistory} from 'vue-router'

//导入需要路由的组件
import HomePage from '../pages/homePage.vue'
import LoginPage from '../pages/loginPage.vue'
import RegisterPage from '../pages/registerPage.vue'
import ResetPage from '../pages/resetPage.vue'
import PersonalPage from '../pages/personalPage.vue'
import GoodsPage from '../pages/goodsPage.vue'
import StorePage from '../pages/storePage.vue'
import OrderPage from '../pages/orderPage.vue'
import SearchPage from '../pages/searchPage.vue'
import SellerLoginPage from '../pages/sellerLoginPage.vue'
import SellerRegisterPage from '../pages/sellerRegisterPage.vue'
import AdminLoginPage from '../pages/adminLoginPage.vue'
import AdminRegisterPage from '../pages/adminRegisterPage.vue'
import SellerHomePage from '../pages/sellerHomePage.vue'
import AdminHomePage from '../pages/adminHomePage.vue'

import PersonalData from '../layouts/personalPage/personalData/personalData.vue'
import ShoppingCart from '../layouts/personalPage/shoppingCart/shoppingCart.vue'
import MyOrder from '../layouts/personalPage/myOrder/myOrder.vue'
import GoodsCollection from '../layouts/personalPage/goodsCollection/goodsCollection.vue'
import StoreFollow from '../layouts/personalPage/storeFollow/storeFollow.vue'
import Message from '../layouts/personalPage/message/message.vue'
import History from '../layouts/personalPage/history/history.vue'
import Evaluation from '../layouts/personalPage/evaluation/evaluation.vue'
import Refund from '../layouts/personalPage/refund/refund.vue'
import Address from '../layouts/personalPage/address/address.vue'

import BasicInfo from '../layouts/personalPage/personalData/basicInfo.vue'
import ResetPassword from '../layouts/personalPage/personalData/resetPassword.vue'
import PayPassword from '../layouts/personalPage/personalData/payPassword.vue'
import Authentication from '../layouts/personalPage/personalData/authentication.vue'
import MyEvaluation from '../layouts/personalPage/evaluation/myEvaluation.vue'
import WriteEvaluation from '../layouts/personalPage/myOrder/writeEvaluation.vue'
import AddAddress from '../layouts/personalPage/address/addAddress.vue'
import RefundInfo from '../layouts/personalPage/refund/refundInfo.vue'
import NeedRefund from '../layouts/personalPage/myOrder/needRefund.vue'
import UpdateAddress from '../layouts/personalPage/address/updateAddress.vue'

import Store from '../layouts/sellerHomePage/store/store.vue'
import Goods from '../layouts/sellerHomePage/goods/goods.vue'
import MessageSeller from '../layouts/sellerHomePage/message/message.vue'
import Order from '../layouts/sellerHomePage/order/order.vue'
import RefundSeller from '../layouts/sellerHomePage/refund/refund.vue'

import AddGoods from '../layouts/sellerHomePage/goods/addGoods.vue'
import UpdateGoods from '../layouts/sellerHomePage/goods/updateGoods.vue'
import GoodsSingleType from '../layouts/sellerHomePage/goods/goodsSingleType.vue'
import AddGoodsSingleType from '../layouts/sellerHomePage/goods/addGoodsSingleType.vue'
import UpdateGoodsSingleType from '../layouts/sellerHomePage/goods/updateGoodsSingleType.vue'

//创建路由对象，声明路由规则
const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:'/',
            component:HomePage
        },
        {
            path:'/login',
            component:LoginPage
        },
        {
            path:'/register',
            component:RegisterPage
        },
        {
            path:'/reset',
            component:ResetPage
        },
        {
            path:'/goods',
            component:GoodsPage
        },
        {
            path:'/store',
            component:StorePage
        },
        {
            path:'/order',
            component:OrderPage
        },
        {
            path:'/search',
            component:SearchPage
        },
        {
            path:'/sellerLogin',
            component:SellerLoginPage
        },
        {
            path:'/sellerRegister',
            component:SellerRegisterPage
        },
        {
            path:'/adminLogin',
            component:AdminLoginPage
        },
        {
            path:'/adminRegister',
            component:AdminRegisterPage
        },
        {
            path:'/personal',
            component:PersonalPage,
            //子路由
            children:[
                {
                    path:'',
                    component:PersonalData,
                },
                {
                    path:'shoppingCart',
                    component:ShoppingCart,
                },
                {
                    path:'myOrder',
                    component:MyOrder,
                },
                {
                    path:'goodsCollection',
                    component:GoodsCollection,
                },
                {
                    path:'storeFollow',
                    component:StoreFollow,
                },
                {
                    path:'message',
                    component:Message,
                },
                {
                    path:'history',
                    component:History,
                },
                {
                    path:'evaluation',
                    component:Evaluation,
                },
                {
                    path:'refund',
                    component:Refund,
                },
                {
                    path:'address',
                    component:Address,
                },


                {
                    path:'basicInfo',
                    component:BasicInfo,
                },
                {
                    path:'resetPassword',
                    component:ResetPassword,
                },
                {
                    path:'payPassword',
                    component:PayPassword,
                },
                {
                    path:'authentication',
                    component:Authentication,
                },
                {
                    path:'myEvaluation',
                    component:MyEvaluation,
                },
                {
                    path:'writeEvaluation',
                    component:WriteEvaluation,
                },
                {
                    path:'addAddress',
                    component:AddAddress,
                },
                {
                    path:'refundInfo',
                    component:RefundInfo,
                },
                {
                    path:'needRefund',
                    component:NeedRefund,
                },
                {
                    path:'updateAddress',
                    component:UpdateAddress,
                },
            ]
        },
        {
            path:'/sellerHome',
            component:SellerHomePage,
            children:[
                {
                    path:'',
                    component:Store,
                },
                {
                    path:'goods',
                    component:Goods,
                },
                {
                    path:'message',
                    component:MessageSeller,
                },
                {
                    path:'order',
                    component:Order,
                },
                {
                    path:'refund',
                    component:RefundSeller,
                },


                {
                    path:'addGoods',
                    component:AddGoods,
                },
                {
                    path:'updateGoods',
                    component:UpdateGoods,
                },
                {
                    path:'goodsSingleType',
                    component:GoodsSingleType,
                },
                {
                    path:'addGoodsSingleType',
                    component:AddGoodsSingleType,
                },
                {
                    path:'updateGoodsSingleType',
                    component:UpdateGoodsSingleType,
                },
            ]
        },
        {
            path:'/adminHome',
            component:AdminHomePage
        }
    ]
})

//对外暴露路由对象
export default router;
