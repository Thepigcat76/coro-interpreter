package parser

import lexer.Lexer
import token.Token
import token.TokenType

class Parser(lexer: Lexer) {
    private val tokenStream = lexer.tokenize()

    // might cause issues
    private var curToken: Token = tokenStream[0]
    private var peekToken: Token = tokenStream[1]
    private var currentPos = 0

    private fun nextToken() {
        currentPos++
        curToken = peekToken
        if (currentPos + 1 < tokenStream.size) {
            peekToken = tokenStream[currentPos + 1]
        }
    }

    fun parseProgram(): Program {
        val program = Program(mutableListOf())
        while (curToken.type != TokenType.EOF) {
            val statement: Statement? = parseStatement()
            if (statement != null) {
                program.statements += statement
            }
            nextToken()
        }
        return program
    }

    private fun parseStatement(): Statement? {
        return when (this.curToken.type) {
            TokenType.IF -> {
                println(this.curToken.type)
                parseIfStatement()
            }

            else -> {
                null
            }
        }
    }

    private fun parseIfStatement(): IfStatement? {
        val statement = IfStatement(emptyList(), null, emptyList(), emptyList())
        if (this.peekToken.type != TokenType.IDENTIFIER) {
            return null
        }
        statement.condition = Identifier(this.peekToken.literal)
        return statement
    }
}