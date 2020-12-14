var express = require('express');
var neo4j = require('neo4j-driver').v1;
var driver = neo4j.driver('bolt://localhost', neo4j.auth.basic('neo4j', 'cjm123..'));
var router = express.Router();
var caches = require('../appcache');
const SMSClient = require('@alicloud/sms-sdk')
const accessKeyId = ''
const secretAccessKey = ''


/* GET users listing. */
router.get('/getName', function(req, res, next) {

    var phone = req.query.phone;
    var verifyCode = caches.addVerifyCode(phone);

    // var session = driver.session();
    // var result = session.run("MATCH (n:Account) WHERE n.phone='18640488253' SET n.nickname=$nickname RETURN n", { nickname: req.query.user }).subscribe({
    //     onNext: function(record) {
    //         // records.forEach(function(record) {
    //         var account = record.get('n');
    //         console.log(account.properties);
    //         console.log(account.properties.lastSigninTime.toNumber());
    //         // });
    //     },
    //     onCompleted: function() {
    //         console.log('onCompleted');
    //         session.close();
    //     },
    //     onError: function(error) {
    //         console.log(error);
    //     }
    // });
    // .then(function(result) {
    //     result.records.forEach(function(record) {
    //         var account = record.get('n');
    //         console.log(account.properties);
    //         console.log(account.properties.lastSigninTime.toNumber());
    //     });

    //     session.close();


    // }).catch(function(error) {
    //     console.log(error);
    // });

    // console.log(req.query.name);

    let smsClient = new SMSClient({ accessKeyId, secretAccessKey })
    var params = { code: verifyCode }
    smsClient.sendSMS({
        PhoneNumbers: phone,
        SignName: '陈建铭',
        TemplateCode: 'SMS_139520124',
        TemplateParam: JSON.stringify(params)
    }).then(function(res) {
        let { Code } = res
        if (Code === 'OK') {
            //处理返回参数
            console.log(res)
        }
    }, function(err) {
        console.log(err)
    })
    res.send(getSuccessMessage());

});



/* POST users listing. */
router.post('/getUser', function(req, res, next) {

    console.log(req.headers['accesskey']);


    res.send(getSuccessMessage());

});

router.get('/sendVerifyCode', function(req, res, next) {
    const phone = req.query.phone;
    if (notNull(phone)) {
        var verifyCode = caches.addVerifyCode(phone);
        console.log("verifyCode:" + verifyCode);
        //TODO
    }
    res.send(getSuccessMessage());
});
router.get('/checkVerifyCode', function(req, res, next) {
    const phone = req.query.phone;
    var verifyCode = req.query.verifyCode;
    if (notNull(phone)) {
        if (caches.checkVerifyCode(phone, verifyCode)) {
            var accessKey = caches.addAccessKey(phone);
            res.send(getSuccessMessage({ accesskey: accessKey }));
        }
    }

    res.send(getFailMessage("error"));
});

router.get('/signOut', function(req, res, next) {
    const phone = req.query.phone;
    if (notNull(phone)) {
        caches.removeAccessKey(phone);
    }
    res.send(getSuccessMessage());
});

router.post('/signIn', function(req, res, next) {
    var phone = req.body.phone;
    var accessKey = req.headers['accesskey'];
    if (caches.checkAccessKeyTimeout(phone, accessKey)) {
        if (notNull(phone)) {
            var account;
            var session = driver.session();
            var result = session.run("MERGE (a:Account {phone:$phone}) SET a.lastSigninTime=$lastSigninTime RETURN a", { phone: phone, lastSigninTime: parseInt(new Date().getTime()) }).subscribe({
                onNext: function(record) {
                    console.log("onNext");
                    account = record.get('a');
                },
                onCompleted: function(summary) {
                    console.log('onCompleted');
                    res.send(getSuccessMessage(parseAccountWithAccessKey(account.properties)));
                    session.close();
                },
                onError: function(error) {
                    console.log(error);
                    session.close();
                    res.send(getFailMessage("error"));
                }
            });
        } else {
            res.send(getFailMessage("error"));
        }
    } else {
        res.send(getFailMessage("timeout"));
    }
    // res.send('respond with a resource:' + JSON.stringify(req.body.user));
});

router.post('/setAccountInfo', function(req, res, next) {
    var phone = req.body.phone;
    var accessKey = req.headers['accesskey'];
    if (notNull(phone) && caches.checkAccessKey(phone, accessKey)) {
        var params = { phone: phone };
        var cql = 'MERGE (a:Account {phone:$phone}) SET ';

        if (notNull(req.body.realName)) {
            cql += 'a.realName=$realName ';
            params.realName = req.body.realName.trim();
        }
        if (notNull(req.body.nickName)) {
            cql += 'a.nickName=$nickName ';
            params.nickName = req.body.nickName.trim();
        }
        if (notNull(req.body.headImage)) {
            cql += 'a.headImage=$headImage ';
            params.headImage = req.body.headImage.trim();
        }
        if (notNull(req.body.brief)) {
            cql += 'a.brief=$brief ';
            params.brief = req.body.brief.trim();
        }
        if (notNull(req.body.gendar) && !isNaN(req.body.gendar)) {
            cql += 'a.gendar=$gendar ';
            params.gendar = parseInt(req.body.gendar);
        }
        cql += 'RETURN a';
        var account;
        var session = driver.session();
        var result = session.run(cql, params).subscribe({
            onNext: function(record) {
                console.log("onNext");
                account = record.get('a');
            },
            onCompleted: function(summary) {
                console.log('onCompleted');
                res.send(getSuccessMessage(parseAccount(account.properties)));
                session.close();
            },
            onError: function(error) {
                console.log(error);
                session.close();
                res.send(getFailMessage("database error"));
            }
        });
    } else {
        res.send(getFailMessage("parameter error"));
    }

});

router.post('/getAccounts', function(req, res, next) {
    var phone = req.body.phone;
    var accessKey = req.headers['accesskey'];
    if (notNull(phone) && caches.checkAccessKey(phone, accessKey)) {
        var accounts = [];
        var session = driver.session();
        var result = session.run("MATCH (a:Account) WHERE a.phone<>$phone RETURN a ORDER BY a.lastSigninTime DESC", { phone: phone }).subscribe({
            onNext: function(record) {
                console.log("onNext");
                var account = record.get('a');
                accounts.push(parseAccount(account.properties));
            },
            onCompleted: function(summary) {
                console.log('onCompleted');
                res.send(getSuccessMessage(accounts));
                session.close();
            },
            onError: function(error) {
                console.log(error);
                session.close();
                res.send(getFailMessage("error"));
            }
        });

    } else {
        res.send(getFailMessage("parameter error"));
    }

});

router.post('/concernAccount', function(req, res, next) {

    res.send('respond with a resource:' + JSON.stringify(req.body.user));

});

function parseAccount(properties) {
    var account = {};
    account.phone = properties.phone;
    account.realName = properties.realName;
    account.nickName = properties.nickName;
    account.headImage = properties.headImage;
    account.brief = properties.brief;
    account.lastSigninTime = properties.lastSigninTime;
    account.gendar = properties.gendar;
    account.online = isOnline(account.phone);
    return account;
}

function parseAccountWithAccessKey(properties) {
    var account = parseAccount(properties);
    account.accessKey = caches.getAccessKey(properties.phone);
    return account;
}

function isOnline(phone) {
    return caches.isOnline(phone)
}

function notNull(obj) {
    return obj != undefined && obj != null && obj !== ''
}

function getSuccessMessage(data) {
    var respond = { code: 0, msg: 'success' }
    respond.data = data;
    return JSON.stringify(respond);
}

function getFailMessage(msg) {
    var respond = { code: -1, msg: msg }
    return JSON.stringify(respond);
}
module.exports = router;
