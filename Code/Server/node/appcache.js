var caches = {}
var accessKeyPool = {};
var verifyCodePool = {};

const uuidv4 = require('uuid/v4');


caches.addAccessKey = function(phone) {
    var uuid = uuidv4();
    accessKeyPool[phone] = { key: uuid, time: parseInt(new Date().getTime()) }
    return uuid;
}
caches.getAccessKey = function(phone) {
    return accessKeyPool[phone].key;
}
caches.checkAccessKey = function(phone, uuid) {
    var key = accessKeyPool[phone].key
    if (notNull(uuid)) {
        return uuid == key;
    }
    return false;
}
caches.checkAccessKeyTimeout = function(phone, uuid) {
    var key = accessKeyPool[phone].key
    var time = accessKeyPool[phone].time;
    if (notNull(uuid) && notNull(time)) {
        return uuid == key && parseInt(new Date().getTime() - time < 30 * 24 * 60 * 60 * 1000);
    }
    return false;
}
caches.isOnline = function(phone) {
    return notNull(accessKeyPool[phone]);
}
caches.removeAccessKey = function(phone) {
    accessKeyPool[phone] = null;
}

caches.addVerifyCode = function(phone) {
    var uuid = uuidv4();
    var verifyCode = uuid.split('-')[2];
    verifyCodePool[phone] = verifyCode;
    return verifyCode;
}
caches.checkVerifyCode = function(phone, code) {
    var verifyCode = verifyCodePool[phone]
    if (notNull(code)) {
        return code == verifyCode;
    }
    return false;
}
caches.removeVerifyCode = function(phone) {
    verifyCodePool[phone] = null;
}

function notNull(obj) {
    return obj != undefined && obj != null && obj !== ''
}

module.exports = caches;