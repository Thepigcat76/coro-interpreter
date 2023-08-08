package parser

import lexer.Lexer
import token.Token
import token.TokenType

class Parser(private var lexer: Lexer) {
    private lateinit var curToken: Token
    private lateinit var peekToken: Token
    val tokenStream = lexer.tokenize()
    private var currentPos = 0

    private fun nextToken() {
        currentPos++
        curToken = peekToken
        if (currentPos+1 < tokenStream.size) {
            peekToken = tokenStream[currentPos+1]
        }
    }

    fun parseProgram(): Program {
        val program = Program(mutableListOf())
        while (curToken.type != TokenType.EOF) {
            val statement: Statement? = parseStatement()
            if (statement != null) {
                program.statements?.plus(statement)
            }
            nextToken()
        }
        return program
    }

    fun parseStatement(): Statement {
    }
}