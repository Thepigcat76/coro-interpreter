package parser

import lexer.Lexer
import token.Token
import token.TokenType
import kotlin.collections.plus

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
                ExpressionStatement(Identifier(this.curToken.literal))
            }
        }
    }

    private fun parseIfStatement(): IfStatement? {
        val statement = IfStatement(null, emptyList(), emptyList())
        if (this.peekToken.type != TokenType.IDENTIFIER) {
            // Here go error messages
            return null
        } else {
            statement.condition = Identifier(this.peekToken.literal)
        }
        nextToken()
        nextToken()
        if (this.curToken.type != TokenType.EOL) {
            return null
        } else {
            nextToken()
            statement.consequence = parseBlockStatement().statements
        }
        return statement
    }

    private fun parseBlockStatement(): BlockStatement {
        val statement = BlockStatement(emptyList())
        while (this.curToken.type != TokenType.END) {
            statement.statements += parseStatement()
            println("Cur statement: ${statement.statements}, curToken: ${this.curToken}")
            nextToken()
        }
        return statement
    }
}