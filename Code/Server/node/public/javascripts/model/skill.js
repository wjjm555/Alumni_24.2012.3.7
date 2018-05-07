var Base = require('./base');

class Skill extends Base {

    constructor(type, mode) {
        super();
        this.type = type
        this.mode = mode
    }

}


module.exports = Skill;