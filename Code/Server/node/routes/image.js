var express = require('express');
var router = express.Router();
var caches = require('../appcache');
var multiparty = require('multiparty');

router.get('/test', function(req, res, next) {
    console.debug("Query:" + req.query.phone);

    res.send((caches.accessKeyPool));

});
router.post('/upload', function(req, res, next) {

    var form = new multiparty.Form();
    form.encoding = 'utf-8';
    form.uploadDir = "D:/03.Cache/images";
    form.maxFilesSize = 100 * 1024 * 1024;

    form.parse(req, function(err, fields, files) {
        if (err) {
            console.log('错误');
            res.send(getFailMessage('上传失败'));
            return;
        }

        var headImage = files.file[0].path.split('\\')[3];
        console.log(headImage + "*" + req.query.phone);

        res.send(getSuccessMessage({ headImage: headImage }));
    });

});

router.get('/get/:filename', function(req, res, next) {

    console.debug("image:" + req.params.filename);

    res.sendfile('D:/03.Cache/images/' + req.params.filename, function(err) {
        console.debug("err:" + err);
        // res.send("err:" + err);
        res.end();
    });

});

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