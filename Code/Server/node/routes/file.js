var express = require('express');
var router = express.Router();
var fs = require('fs');

router.get('/:filename', function(req, res, next) {

    console.debug("down req:" + req.get("Range") + "      " + req.params.filename);
    var range = req.get("Range");
    var startPoi = parseInt(isNaN(range) ? 0 : range);
    var filePath = 'D:/03.Cache/downloads/' + req.params.filename;
    console.debug("step 2");
    fs.stat(filePath, function(err, stats) {
        console.debug("down err:" + err);
        if (stats.isFile()) {
            var readStream = fs.createReadStream(filePath, { start: startPoi, end: stats.size });
            res.set({ 'Content-Length': stats.size - startPoi });
            console.debug("createReadStream:");
            readStream.on('data', function(chunk) {
                res.write(chunk);
            });
            readStream.on('end', function() {
                console.debug("end:");
                res.end();
            });
        } else {
            console.debug("stats.is not File");
            res.end();
        }
    });

});
module.exports = router;