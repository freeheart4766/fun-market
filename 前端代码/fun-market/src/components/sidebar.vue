<script setup>
//逐渐滚动到页面顶部
let toTop = () => {
    window.scrollTo({  
    top: 0,  
    behavior: "smooth"  
  });  
}
</script>

<template>
    <div id="sidebar-area">
        <div class="siberar-out" id="out-top">
            <router-link to="/personal/message" class="siberar-to">
                <el-icon class="siber-icon"><ChatDotRound /></el-icon>
                <span class="icon-message">消息</span>
            </router-link>
        </div>
        <div class="siberar-out">
            <router-link to="" class="siberar-to">
                <el-icon class="siber-icon"><Service /></el-icon>
                <span class="icon-message">客服</span>
            </router-link>
        </div>
        <div class="siberar-out" id="out-bottom">
            <a class="siberar-to" @click="toTop()">
                <el-icon class="siber-icon"><ArrowUp /></el-icon>
                <span class="icon-message">顶部</span>
            </a>
        </div>
    </div>
</template>

<style scoped>
#sidebar-area {
    width: 70px;
    height: 240px;
    position: fixed;
    right: 0px;
    top: 300px;
    border-top-left-radius: 15px;
    border-bottom-left-radius: 15px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
.siberar-out {
    background-color: white;
    width: 70px;
    height: 80px;
    border: none;
    cursor: pointer;
}
.siberar-out:hover {
    background-color: rgb(245, 245, 245);
}
.siberar-out:active {
    background-color: rgb(212, 209, 209);
}
.siberar-to{
    text-decoration: none;
}
.siber-icon {
    font-size: 30px;
    width: 70px;
    height: 20px;
    padding-top: 15px;
    color: black;
}
.icon-message {
    font-size: 15px;
    padding-left: 18px;
    color: black;
}
#out-top {
    border-top-left-radius: 15px;
}
#out-bottom {
    border-bottom-left-radius: 15px;
}
</style>