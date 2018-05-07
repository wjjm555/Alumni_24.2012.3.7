var Card = require('./card');
var Enum = require('./enum');

class StandardCard extends Card {
    constructor(suit, count) {
        super(Enum.Type_Card.Standard, suit, count);
    }
}

module.exports = StandardCard;