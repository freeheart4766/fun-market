//检测用户名的正则表达式，4到10位的字母
let regUsername = /^[a-zA-Z]{4,10}$/;
//检测密码的正则表达式，7到16位的字母或数字
let regPassword = /^[a-zA-Z0-9]{7,16}$/;
//检测手机号的正则表达式
let regTelephone = /^1[3-9]\d{9}$/;
//检测昵称的正则表达式
let regNickname = /^[\u4e00-\u9fa5a-zA-Z0-9]{2,14}$/;
//检测个性签名的正则表达式
let regDescription = /^[\u4e00-\u9fa5a-zA-Z，。]{1,40}$/;
//检测邮箱的正则表达式
let regEmail = /^[0-9]{5,12}@[0-9a-z]{2,3}.com$/;
//检测身份证号的正则表达式
let regIDNumber = /^\d{17}(\d|X|x)$/;
//检测真实姓名的正则表达式
let regTrueName = /^[\u4e00-\u9fa5]{2,4}$/;
//检测搜索框内容的正则表达式
let regSearchContent = /^[\u4e00-\u9fa50-9]{1,50}$/;

//校验用户名是否合法
let testUsername = (username) =>{
    let a = regUsername.test(username);
    return a;
}
//校验密码是否合法
let testPassword = (password) =>{
    let b = regPassword.test(password);
    return b;
}
//校验手机号是否合法
let testTelephone = (telephone) => {
    let c = regTelephone.test(telephone);
    return c;
}
//校验昵称是否合法
let testNickname = (nickname) => {
    let d = regNickname.test(nickname);
    return d;
}
//校验个性签名是否合法
let testDescription = (description) => {
    let e = regDescription.test(description);
    return e;
}
//校验邮箱是否合法
let testEmail = (email) => {
    let f = regEmail.test(email);
    return f;
}
//校验身份证号是否合法
let testIDNumber = (IDNumber) => {
    let g = regIDNumber.test(IDNumber);
    return g;
}
//校验真实姓名是否合法
let testTrueName = (trueName) => {
    let h = regTrueName.test(trueName);
    return h;
}
//校验搜索框内容是否合法
let testSearchContent = (searchContent) => {
    let i = regSearchContent.test(searchContent);
    return i;
}

export {
    testUsername,
    testPassword,
    testTelephone,
    testNickname,
    testDescription,
    testEmail,
    testIDNumber,
    testTrueName,
    testSearchContent,
}