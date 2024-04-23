import { warning } from '../utils/notification.js'
import { testUsername, testPassword, testTelephone, testNickname, testDescription, testEmail, testIDNumber, testTrueName, testSearchContent } from '../utils/dataCheck.js'

//检测用户名格式是否正确，若不正确，弹出警告提示框
let getUsernameResult = (data) => {
    if(!testUsername(data)){
        warning('用户名格式有误');
        return false;
    }
    return true;
}
//检测密码格式是否正确，若不正确，弹出警告提示框
let getPasswordResult = (data) => {
    if(!testPassword(data)){
        warning('密码格式有误');
        return false;
    }
    return true;
}
//检测两次密码输入是否相同，若不相同，弹出警告提示框
let getTwicePasswordResult = (data1,data2) => {
    if(data1 != data2){
        warning('两次密码输入不一致');
    }
}
//检测手机号格式是否正确，若不正确，弹出警告提示框
let getTelephoneResult = (data) => {
    if(!testTelephone(data)){
        warning('手机号格式有误');
        return false;
    }
    return true;
}
//提交时检测用户名和密码格式是否正确，若不正确，弹出警告提示框
let submit = (data1,data2) => {
    if(!testUsername(data1) || !testPassword(data2)){
        warning('用户名或密码格式有误');
        return false;
    }
    return true;
}
//检测昵称格式是否正确，若不正确，弹出警告提示框
let getNicknameResult = (nickname) => {
    if(!testNickname(nickname)){
        warning('昵称格式有误');
        return false;
    }
    return true;
}
//检测个性签名格式是否正确，若不正确，弹出警告提示框
let getDescriptionResult = (description) => {
    if(!testDescription(description)){
        warning('个性签名格式有误');
        return false;
    }
    return true;
}
//检测邮箱格式是否正确，若不正确，弹出警告提示框
let getEmailResult = (email) => {
    if(!testEmail(email)){
        warning('邮箱格式有误');
        return false;
    }
    return true;
}
//检测身份证号格式是否正确，若不正确，弹出警告提示框
let getIDNumberResult = (IDNumber) => {
    if(!testIDNumber(IDNumber)){
        warning('身份证号格式有误');
        return false;
    }
    return true;
}
//检测真实姓名格式是否正确，若不正确，弹出警告提示框
let getTrueNameResult = (trueName) => {
    if(!testTrueName(trueName)){
        warning('真实姓名格式有误');
        return false;
    }
    return true;
}
//检测搜索框内容格式是否正确，若不正确，弹出警告提示框
let getSearchContentResult = (searchContent) => {
    if(!testSearchContent(searchContent)){
        warning('请输入正确的搜索内容');
        return false;
    }
    return true;
}

export {
    getUsernameResult,
    getPasswordResult,
    getTwicePasswordResult,
    getTelephoneResult,
    submit,
    getNicknameResult,
    getDescriptionResult,
    getEmailResult,
    getIDNumberResult,
    getTrueNameResult,
    getSearchContentResult,
}