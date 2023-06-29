 package parser

import token.Token
import token.TokenType

class Parser(val tokenStream: List<Token>) {

    private var currentTokenIndex = 0
    private var currentToken: Token = tokenStream[currentTokenIndex]

    fun parse(): Int {
        return expression()
    }

    private fun expression(): Int {
        return ifStatement()
    }

    private fun ifStatement(): Int {
        consume(TokenType.IF, "expected 'if'")

        if (match(TokenType.NOT)) {
            advance()
            //invert()
        } else {
            advance()
        }

        consume(TokenType.IDENTIFIER, "expected condition")

        val condition = expression()

        consume(TokenType.EOL, "expected end of line")

        val ifBlock = expression()

        while (!match(TokenType.ELSE) || !match(TokenType.ELIF) || !match(TokenType.END)) {
            advance()
        }

        if (match(TokenType.ELIF)) {
            val elseBlock = expression()

            return if (condition != 0) ifBlock else elseBlock
        }

        return ifBlock
    }

    private fun consume(expectedTokenType: TokenType, errorMessage: String) {
        if (check(expectedTokenType)) {
            advance()
        } else {
            throw IllegalArgumentException(errorMessage)
        }
    }

    private fun advance() {
        if (!isAtEnd()) {
            currentTokenIndex++
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

    private fun check(expectedTokenType: TokenType): Boolean {
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
   // val parsedStream = Parser(tokenStream).check(TokenType.IF)
   // println(parsedStream)
}