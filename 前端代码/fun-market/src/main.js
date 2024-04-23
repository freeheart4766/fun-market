import { createApp } from 'vue'

//导入element-plus
import ElementPlus from 'element-plus'
//导入element-plus的样式文件
import 'element-plus/dist/index.css'
//导入element-plus的图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

//导入路由模块
import Router from './router/router.js'

//导入Pinia
import { createPinia } from 'pinia'

import App from './App.vue'


let app = createApp(App)
let pinia = createPinia()
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(Router)
app.use(pinia)
app.mount('#app')