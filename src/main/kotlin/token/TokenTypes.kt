package token

enum class TokenTypes(val token: String) {
    NOT("not"),
    COLON(":"),

    IF("if"),
    REPEAT_WHILE("repeat while"),
    REPEAT_TIMES("repeat times"),
    REPEAT_ALWAYS("repeat always"),
    DEFINE("define"),

    ENDIF("endIf"),
    END_REPEAT_WHILE("endRepeatWhile"),
    END_REPEAT_TIMES("endRepeatTimes"),
    END_REPEAT_ALWAYS("endRepeatAlways"),
    END_DEFINE("endDefine")
}

data class Token(val type: TokenTypes, val name: String)