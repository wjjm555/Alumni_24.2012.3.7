var Card = require('./card');
var Enum = require('./enum');

class KitCard extends Card {
    constructor(suit, count) {
        super(Enum.Type_Card.Kit, suit, count);
    }
}

module.exports = KitCard;