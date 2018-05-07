var Card = require('./card');
var Enum = require('./enum');

class EquipCard extends Card {
    constructor(suit, count) {
        super(Enum.Type_Card.Equip, suit, count);
    }
}

module.exports = EquipCard;