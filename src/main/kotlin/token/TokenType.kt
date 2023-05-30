package token

enum class TokenType(val token: String) {
    NOT("not"),
    WHILE("while"),
    TIMES("times"),
    ALWAYS("always"),

    COLON(":"),
    QUOT("\""),

    IF("if"),
    REPEAT("repeat"),
    DEFINE("define"),

    ENDIF("endIf"),
    END_REPEAT_WHILE("endRepeatWhile"),
    END_REPEAT_TIMES("endRepeatTimes"),
    END_REPEAT_ALWAYS("endRepeatAlways"),
    END_DEFINE("endDefine")
}

data class Token(val type: TokenType, val value: String)