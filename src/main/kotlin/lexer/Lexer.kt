package lexer

import token.Token
import token.TokenType

class Lexer {
    fun tokenize(input: String): List<Token> {
        var currentPos = 0
        val tokens = mutableListOf<Token>()
        while (input.length < currentPos) {
            val char = input[currentPos]
            var whitespaceCounter = 0
            when {
                char.isWhitespace() -> {
                    whitespaceCounter++
                    currentPos++
                }
                //Test if this breaks the lexer
                //Probably won't but might be the
                //reason for issues
                !char.isWhitespace() -> {
                    whitespaceCounter = 0
                }
                char.isLetter() || whitespaceCounter <= 1 || char == '_' -> {
                    val identifier = StringBuilder()
                    while (currentPos < input.length && (input[currentPos].isLetterOrDigit() || input[currentPos] == '_' || whitespaceCounter <= 1)) {
                        identifier.append(input[currentPos])
                        currentPos++
                    }
                }
            }
        }
        return tokens
    }
}