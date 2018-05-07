if (typeof Count_Card == "undefined") {
    var Count_Card = {
        _A: { num: 1, name: 'A' },
        _2: { num: 2, name: '2' },
        _3: { num: 3, name: '3' },
        _4: { num: 4, name: '4' },
        _5: { num: 5, name: '5' },
        _6: { num: 6, name: '6' },
        _7: { num: 7, name: '7' },
        _8: { num: 8, name: '8' },
        _9: { num: 9, name: '9' },
        _10: { num: 10, name: '10' },
        _J: { num: 11, name: 'J' },
        _Q: { num: 12, name: 'Q' },
        _K: { num: 13, name: 'K' }
    }
}

if (typeof Suit_Card == "undefined") {
    var Suit_Card = {
        Spades: { num: 3, name: 'spades' },
        Hearts: { num: 2, name: 'hearts' },
        Diamonds: { num: 1, name: 'diamonds' },
        Clubs: { num: 0, name: 'clubs' }
    }
}

if (typeof Type_Card == "undefined") {
    var Type_Card = {
        Standard: { num: 0, name: 'standard' },
        Equip: { num: 1, name: 'equip' },
        Kit: { num: 2, name: 'kit' }
    }
}

if (typeof Mode_Skill == "undefined") {
    var Mode_Skill = {
        Initiative: { num: 0, name: 'initiative', bool: true },
        Passive: { num: 1, name: 'passive', bool: false }
    }
}

if (typeof Type_Skill == "undefined") {
    var Type_Skill = {
        Common: { num: 0, name: 'common' },
        Master: { num: 1, name: 'master' },
        Awaken: { num: 2, name: 'awaken' },
        Lock: { num: 3, name: 'lock' }
    }
}

if (typeof Gender_Role == "undefined") {
    var Gender_Role = {
        Male: { num: 1, name: 'male' },
        Female: { num: 0, name: 'female' },
        Other: { num: -1, name: 'other' }
    }
}

if (typeof Group_Role == "undefined") {
    var Group_Role = {
        Group1: { num: 0, name: '1' },
        Group2: { num: 1, name: '2' },
        Group3: { num: 2, name: '3' },
        Group4: { num: 3, name: '4' },
        Independent: { num: 4, name: 'independent' }
    }
}

if (typeof Identity_Player == "undefined") {
    var Identity_Player = {
        Master: { num: 3, name: 'master' },
        Loyal: { num: 2, name: 'loyal' },
        Spy: { num: 1, name: 'spy' },
        Thief: { num: 0, name: 'thief' }
    }
}

if (typeof Stage == "undefined") {
    var Stage = {
        Prepare_Initiative: { num: 5, name: 'prepare_Initiative' },
        Determine_Initiative: { num: 4, name: 'determine_Initiative' },
        Scratch_Initiative: { num: 3, name: 'scratch_Initiative' },
        Play_Initiative: { num: 2, name: 'play_Initiative' },
        Discard_Initiative: { num: 1, name: 'discard_Initiative' },
        Finish_Initiative: { num: 0, name: 'finish_Initiative' },
        Prepare_Passive: { num: -5, name: 'prepare_Passive' },
        Determine_Passive: { num: -4, name: 'determine_Passive' },
        Scratch_Passive: { num: -3, name: 'scratch_Passive' },
        Play_Passive: { num: -2, name: 'play_Passive' },
        Discard_Passive: { num: -1, name: 'discard_Passive' },
        Finish_Passive: { num: 0, name: 'finish_Passive' }
    }
}

var Enum = {
    Stage: Stage,
    Type_Card: Type_Card,
    Count_Card: Count_Card,
    Suit_Card: Suit_Card,
    Mode_Skill: Mode_Skill,
    Type_Skill: Type_Skill,
    Gender_Role: Gender_Role,
    Group_Role: Group_Role,
    Identity_Player: Identity_Player
}

module.exports = Enum