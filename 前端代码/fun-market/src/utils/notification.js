import { ElNotification } from 'element-plus'
//弹出一个成功的提示框
const success = (message1,message2,fun) => {
    ElNotification({
        title: `${message1}成功`,
        message: `即将跳转到${message2}`,
        type: 'success',
        duration: 2000,
        showClose: false,
        onClose: fun
    })
}

//弹出一个警告的提示框
const warning = (message3) => {
    ElNotification({
        title: '警告',
        message: message3,
        type: 'warning',
        duration: 3000,
        showClose: false
    })
}
 
//弹出一个错误的提示框
const error = (message4,message5) => {
    ElNotification({
        title: `${message4}失败`,
        message: message5,
        type: 'error',
        duration: 3000,
        showClose: false
    })
}

//弹出一个简易的成功提示框
const successEasy = (message6,message7) => {
    ElNotification({
        title: `${message6}成功`,
        message: message7,
        type: 'success',
        duration: 2000,
        showClose: false,
    })
}

//统一对外暴露
export {
    success,
    warning,
    error,
    successEasy
}