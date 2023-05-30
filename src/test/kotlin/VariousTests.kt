data class Token(val type: TokenType, val lexeme: String)

enum class TokenType {
    IDENTIFIER,
    INTEGER_LITERAL,
    OPERATOR,
    // Other token types...
}

fun tokenize(input: String): List<Token> {
    val tokens = mutableListOf<Token>()
    var currentPosition = 0

    while (currentPosition < input.length) {
        val char = input[currentPosition]

        when {
            char.isWhitespace() -> {
                // Ignore whitespace
                currentPosition++
            }
            char.isLetter() || char == '_' -> {
                // Handle identifiers
                val identifier = StringBuilder()
                while (currentPosition < input.length && (input[currentPosition].isLetterOrDigit() || input[currentPosition] == '_')) {
                    identifier.append(input[currentPosition])
                    currentPosition++
                }
                tokens.add(Token(TokenType.IDENTIFIER, identifier.toString()))
                println("Identifier: " + identifier)
            }
            char.isDigit() -> {
                // Handle integer literals
                val number = StringBuilder()
                while (currentPosition < input.length && input[currentPosition].isDigit()) {
                    number.append(input[currentPosition])
                    currentPosition++
                }
                tokens.add(Token(TokenType.INTEGER_LITERAL, number.toString()))
            }
            // Handle other token types...
            else -> {
                // Handle operators, punctuation, etc.
                tokens.add(Token(TokenType.OPERATOR, char.toString()))
                currentPosition++
            }
        }
    }

    return tokens
}

fun filterTokens(tokens: List<Token>, desiredTokenType: TokenType): List<Token> {
    return tokens.filter { it.type == desiredTokenType }
}

fun main() {
    val input = "x = 42 + y"

    // Tokenize the input
    val tokens = tokenize(input)

    // Filter for identifier tokens
    val filteredTokens = filterTokens(tokens, TokenType.IDENTIFIER)

    // Print the filtered tokens
    for (token in filteredTokens) {
        println(token.lexeme)
    }
}