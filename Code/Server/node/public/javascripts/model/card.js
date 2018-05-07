var Base = require('./base');

class Card extends Base {

    constructor(type, suit, count) {
        super();
        this.type = type
        this.suit = suit
        this.count = count
    }

}


module.exports = Card;