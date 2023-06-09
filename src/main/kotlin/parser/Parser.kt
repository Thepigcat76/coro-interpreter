package parser

import lexer.tokenStream
import token.Token
import token.TokenType

class Parser(val tokenStream: List<Token>) {

    private var currentTokenIndex = 0
    private var currentToken: Token = tokenStream[currentTokenIndex]
    fun check(expectedTokenType: TokenType): Boolean {
        if (isAtEnd()) {
            return false
        }

        return currentToken.type == expectedTokenType
    }

    private fun isAtEnd(): Boolean {
        return currentTokenIndex >= tokenStream.size
    }
}

fun main() {
    val parsedStream = Parser(tokenStream).check(TokenType.IF)
    println(parsedStream)
}