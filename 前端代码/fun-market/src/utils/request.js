import axios from 'axios';
import { error } from './notification.js'
//公共的域名
const unificationURL = 'http://localhost:8080/fun-market';

//创建一个axios实例
const axiosInstance = axios.create({
    baseURL:unificationURL,
    timeout:10000
})

//请求拦截器
axiosInstance.interceptors.request.use(
    config => {
        let token = window.sessionStorage.getItem('token');
        config.headers.token = token;
        return config;
    },
    errors => {
        console.log(errors);
        error('请求','用户未登录');
    }
)

export{
    unificationURL,
    axiosInstance
}