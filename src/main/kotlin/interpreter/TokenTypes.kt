package interpreter

data class Token(
    val tokenType: TokenTypes,
    val literal: String,
)
enum class TokenTypes(val tokenType: String) {
    IF("if"),
    REPEAT_WHILE("repeat while"),
    REPEAT_TIMES("repeat times"),
    DEFINE("define")
}