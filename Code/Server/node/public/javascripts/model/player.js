var Base = require('./base');

class Player extends Base {

    constructor(id, identity) {
        super();
        this.id = id
        this.identity = identity
        this.handBrand = []
        this.equipmentBrand = []
        this.determineBrand = []
        this.role
    }
}


module.exports = Player;