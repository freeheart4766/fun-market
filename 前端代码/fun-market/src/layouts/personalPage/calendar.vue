<script setup>
import { axiosInstance } from '../../utils/request.js'
import { error } from '../../utils/notification.js'
import { onMounted, reactive } from 'vue';

let date = reactive({
    year : '',
    month : '',
    week : '',
    day : '',
})

let getTime = () => {
    axiosInstance.get('/system/getTime')
    .then((response) => {
        date.year = response.data.data.year;
        date.month = response.data.data.month;
        date.week = response.data.data.week;
        date.day = response.data.data.day;
    })
    .catch((errors) => {
        console.log(errors);
        error('日期获取','请检查网络连接');
    })
}

onMounted(() => {
    getTime();
})
</script>

<template>
    <div id="calendar-area">
        <div id="calendar-title">我的日历</div>
        <div id="calendar-context">
            <p id="day">{{ date.day }}</p>
            <p id="week">{{ date.week }}</p>
            <p id="year-month">{{ date.year + ' . ' + date.month }}</p>
        </div>
    </div>
</template>

<style scoped>
#calendar-area {
    width: 250px;
    position: fixed;
    top: 65px;
    left: 1120px;
}
#calendar-title {
    height: 40px;
    background-color: rgb(19, 197, 84);
    text-align: center;
    font-family: KaiTi;
    font-size: 20px;
    color: white;
    padding-top: 15px;
}
#calendar-context {
    height: 170px;
    background-color: rgb(220, 252, 222);
}
#day {
    font-size: 60px;
    text-align: center;
    padding-top: 20px;
    color: rgb(19, 197, 84);
}
#week {
    text-align: center;
    font-family: KaiTi;
    font-size: 20px;
    padding-top: 5px;
    color: rgb(19, 197, 84);
}
#year-month {
    text-align: center;
    font-size: 15px;
    padding-top: 5px;
    color: rgb(19, 197, 84);
}
</style>