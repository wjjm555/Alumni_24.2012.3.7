class Base {

    set setName(name) {
        this.name = name
    }

    set setDescribe(describe) {
        this.describe = describe
    }

    get getName() {
        return this.name
    }

    get getDescribe() {
        return this.describe
    }
}

module.exports = Base;