<script setup>
import PersonalTop from '../layouts/personalPage/personalTop.vue';
import Menu from '../layouts/personalPage/menu.vue';
import Calendar from '../layouts/personalPage/calendar.vue';

</script>

<template>
    <div id="all">
        <PersonalTop></PersonalTop>
        <div id="main-body">
            <Menu></Menu>
            <div id="date-area">
                <router-view></router-view>
            </div>
            <Calendar></Calendar>
        </div>
    </div>
</template>

<style scoped>
#main-body {
    width: 1220px;
    padding-bottom: 25px;
    margin: auto;
}
#date-area {
    width: 870px;
    margin-left: 100px;
    padding-top: 65px;
}
#all {
    background-color: rgb(241, 239, 239);
}
</style>