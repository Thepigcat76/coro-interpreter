package lexer

//Define the possible keywords and symbols
enum class TokenType {
    NOT,
    WHILE,
    TIMES,
    ALWAYS,

    INTEGER, // Any integer
    IDENTIFIER, // A variable, function, condition... something to be identified by the interpreter :P

    IF,
    ELSE,
    REPEAT,
    DEFINE,
    END,

    LPARENT, // (
    RPARENT, // )
    COMMA,   // ,

    EOL, // End of line
    EOF, // End of file
    ILLEGAL,
}

// Data class to determine TokenType and value
data class Token(val type: TokenType, val literal: String) {

    // adds a second alternate constructor
    // by automatically assigning a value to
    // the literal parameter based on the TokenType
    constructor(type: TokenType) : this(type, keywords.entries.find { it.key == type }?.value ?: "")

    // Overrides the default
    // String conversion behaviour
    override fun toString(): String {
        return "TokenType(type=$type, literal='$literal')"
    }

}

enum class TokenLanguages {
    GERMAN,
    ENGLISH,
}

// Hash map for storing
// string values of keywords
val keywords_en = mapOf(
    TokenType.DEFINE to "define",
    TokenType.ALWAYS to "always",
    TokenType.TIMES to "times",
    TokenType.REPEAT to "repeat",
    TokenType.END to "end",
    TokenType.IF to "if",
    TokenType.ELSE to "else",
    TokenType.NOT to "not",
    TokenType.WHILE to "while",

    // Always the same
    TokenType.EOL to "eol",
    TokenType.EOF to "eof",
    TokenType.LPARENT to "(",
    TokenType.RPARENT to ")",
    TokenType.COMMA to ",",
    TokenType.ILLEGAL to "ILLEGAL",
)

val keywords_de = mapOf(
    TokenType.DEFINE to "definiere",
    TokenType.ALWAYS to "endlos",
    TokenType.TIMES to "mal",
    TokenType.REPEAT to "wiederhole",
    TokenType.END to "ende",
    TokenType.IF to "wenn",
    TokenType.ELSE to "sonst",
    TokenType.NOT to "nicht",
    TokenType.WHILE to "solange",

    // Always the same
    TokenType.EOL to "eol",
    TokenType.EOF to "eof",
    TokenType.LPARENT to "(",
    TokenType.RPARENT to ")",
    TokenType.COMMA to ",",
    TokenType.ILLEGAL to "ILLEGAL",
)

var keywords = keywords_en
