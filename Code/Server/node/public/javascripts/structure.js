var uuidMap = new Map();
var codeMap = new Map();

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