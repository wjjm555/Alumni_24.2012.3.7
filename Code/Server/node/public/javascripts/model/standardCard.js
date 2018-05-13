var Card = require('./card');
var Enum = require('./enum');
// var sleep = require('sleep');
// var tagg2 = require('tagg');

class StandardCard extends Card {
    constructor(suit, count) {
        super(Enum.Type_Card.Standard, suit, count);
    }

    testThread() {
        // while (true) {
        //     console.debug("testThread..");
        //     // sleep.sleep(1);
        // }
    }
}

module.exports = StandardCard;