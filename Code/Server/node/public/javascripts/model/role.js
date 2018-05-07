var Base = require('./base');

class Role extends Base {

    constructor(group, gender, blood) {
        super();
        this.group = group
        this.gender = gender
        this.blood = blood
        this.bloodupper = blood
        this.skills = []
    }

    addSkill(skill) {
        skills.push(skill)
    }

}


module.exports = Role;