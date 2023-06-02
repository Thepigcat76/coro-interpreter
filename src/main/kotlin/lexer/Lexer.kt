package lexer

import token.Token
import token.TokenType

class Lexer {
    fun tokenize(input: String): List<Token> {
        val tokens = mutableListOf<Token>()
        val currentPos = 0
        while (currentPos < input.length) {
            val char = input[currentPos]
        }
        return tokens
    }
}

fun main() {
    val tokens = Lexer().tokenize("mogus op ")
    println(tokens)
}