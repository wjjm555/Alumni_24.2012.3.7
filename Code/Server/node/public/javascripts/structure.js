class Skill {
    constructor(name) {
        this.name = name
    }
}
class Role {
    constructor(name, skills) {
        this.name = name
        this.skills = skills
    }
}

class Round {
    constructor() {
        this.roles = [new Role('a', [new Skill('qaz')]), new Role('b'), new Role('c'), new Role('d'), new Role('e')]
    }

    toString() {
        return JSON.stringify(this);
    }
}
class Player {

}
class Board {

}

function test() {
    let i = JSON.stringify(structure.round);
    let j = JSON.parse(i);
    console.debug('structure.js:' + i + "\n");
    console.info(j);
}


// structure.test = test;
// structure.round = new Round();
module.exports = Round;
// module.exports = structure;