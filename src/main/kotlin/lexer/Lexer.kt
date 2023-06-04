package lexer

import token.Token
import token.TokenType
import token.keywords

class Lexer {
    fun tokenize(input: String): List<Token> {
        val tokens = mutableListOf<Token>()
        var currentPos = 0
        while (currentPos < input.length) {
            val char = input[currentPos]
            when {
                char.isWhitespace() -> {
                    currentPos++
                }

                char.isLetter() -> {
                    val identifier = StringBuilder()
                    while (currentPos < input.length && char.isLetterOrDigit() && !input[currentPos].isWhitespace()) {
                        identifier.append(input[currentPos])
                        currentPos++
                    }
                    when (identifier.toString()) {
                        "not" -> {
                            tokens.add(Token(TokenType.NOT))
                        }
                        "if" -> {
                            tokens.add(Token(TokenType.IF))
                        }
                    }
                }

                else -> {
                    currentPos++
                }
            }
        }
        return tokens
    }
}

fun main() {
    val tokens = Lexer().tokenize("if not")
    println(tokens)
}