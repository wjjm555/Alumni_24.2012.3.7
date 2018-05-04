class Skill { //技能
    constructor(name) {
        this.name = name
    }
}
class Role { //人物
    constructor(name, skills) {
        this.name = name
        this.skills = skills
    }
}
class JMC extends Role {
    constructor(name, skills) {
        super(name, skills);
    }
}
class Round { //局
    constructor() {
        this.roles = [new Role('a', [new Skill('qaz')]), new JMC('b'), new JMC('c'), new Role('d'), new Role('e')]
    }
}
var structure = {};

function test() {
    let i = JSON.stringify(structure.round);
    let j = JSON.parse(i);
    console.debug('structure.js:' + i + "\n");
    console.info(j);
}


structure.test = test;
structure.round = new Round();
module.exports = structure;