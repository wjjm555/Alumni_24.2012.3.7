var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/getName', function(req, res, next) {

    // console.debug("req:");
    // res.render('chat');
    res.send('respond with a resource:' + req.query.user);
});

router.get('/:filename', function(req, res, next) {

    // console.debug("req:");
    // res.render('chat');
    res.send('respond with a resource:' + req.params.filename);
});

/* POST users listing. */
router.post('/getUser', function(req, res, next) {

    // console.debug("req:");

    res.send('respond with a resource:' + JSON.stringify(req.body.user));

    // res.sendFile();
});

module.exports = router;