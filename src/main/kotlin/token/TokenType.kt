package token

//Define the possible keywords and symbols
enum class TokenType(val tokenType: String) {
    NOT("NOT"),
    WHILE("WHILE"),
    TIMES("TIMES"),
    ALWAYS("ALWAYS"),

    COLON(":"),
    QUOT("\""),
    INTEGER("INTEGER"),

    IF("IF"),
    ELSE("ELSE"),
    REPEAT("REPEAT"),
    DEFINE("DEFINE"),

    END("END")
}

// Data class to determine TokenType and value
data class Token(val type: TokenType, val literal: String) {

    // adds a second alternate constructor
    // by automatically assigning a value to
    // the literal parameter based on the TokenType
    constructor(type: TokenType) : this(type, keywords.entries.find { it.value == type }?.key ?: "")

    // Overrides the default
    // String conversion behaviour
    override fun toString(): String {
        return "TokenType(type==$type, literal='$literal')"
    }
}

// Hash map for storing
// string values of keywords
val keywords = mapOf(
    "define" to TokenType.DEFINE,
    "always" to TokenType.ALWAYS,
    "times" to TokenType.TIMES,
    "repeat" to TokenType.REPEAT,
    "end" to TokenType.END,
    "if" to TokenType.IF,
    "else" to TokenType.ELSE,
    "not" to TokenType.NOT,
)