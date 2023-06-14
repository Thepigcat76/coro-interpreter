enum class TokenType {
    IF,
    ELSE,
    LEFT_BRACE,
    NUMBER,
    RIGHT_BRACE
    // Add other token types as needed
}

data class Token(val type: TokenType)

class Parser(private val tokens: List<Token>) {
    private var currentTokenIndex = 0
    private val currentToken: Token get() = tokens[currentTokenIndex]

    fun parse(): Int {
        return expression()
    }

    private fun expression(): Int {
        return ifStatement()
    }

    private fun ifStatement(): Int {
        consume(TokenType.IF, "Expect 'if' keyword")

        // Parse the condition expression
        val condition = expression()

        consume(TokenType.LEFT_BRACE, "Expect '{' after if condition")

        // Parse the code block inside the if statement
        val ifBlock = expression()

        consume(TokenType.RIGHT_BRACE, "Expect '}' after if block")

        // Check if there is an else clause
        if (match(TokenType.ELSE)) {
            consume(TokenType.LEFT_BRACE, "Expect '{' after else keyword")

            // Parse the code block inside the else statement
            val elseBlock = expression()

            consume(TokenType.RIGHT_BRACE, "Expect '}' after else block")

            // Return the appropriate value based on the condition
            return if (condition != 0) ifBlock else elseBlock
        }

        // Return the value of the if statement
        return ifBlock
    }

    private fun consume(expectedType: TokenType, errorMessage: String) {
        if (check(expectedType)) {
            advance()
        } else {
            throw IllegalArgumentException(errorMessage)
        }
    }

    private fun match(vararg types: TokenType): Boolean {
        for (type in types) {
            if (check(type)) {
                advance()
                return true
            }
        }
        return false
    }

    private fun check(expectedType: TokenType): Boolean {
        if (isAtEnd()) {
            return false
        }
        return currentToken.type == expectedType
    }

    private fun advance() {
        if (!isAtEnd()) {
            currentTokenIndex++
        }
    }

    private fun isAtEnd(): Boolean {
        return currentTokenIndex >= tokens.size
    }

    // Other parser functions...

}

fun main() {
    val tokens = listOf(
        Token(TokenType.IF),              // Expect 'if' keyword
        Token(TokenType.LEFT_BRACE),      // Expect '{' after if condition
        Token(TokenType.NUMBER),          // Example expression (can be replaced with other tokens or logic)
        Token(TokenType.RIGHT_BRACE)      // Expect '}' after if block
    )

    val parser = Parser(tokens).parse()
    println("Parsing result: $parser")
}