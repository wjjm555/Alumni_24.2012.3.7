var Base = require('./base');

class Board extends Base {
    //Math.floor(Math.random()*(max-min+1)+min)
    constructor() {
        super();
        this.cardsCount = 50
        this.allCards = new Array(this.cardsCount)
        this.standardCards = this.allCards.concat()
    }

    shuffle() {
        this.standardCards = this.allCards.concat()
    }
    perflop() {
        return this.standardCards.splice(Math.floor(Math.random() * (this.standardCards.length + 1)), 1)
    }
}


module.exports = Board;