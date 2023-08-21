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
        return when (curToken.type) {
            TokenType.IF -> {
                parseIfStatement()
            }

            TokenType.IDENTIFIER -> {
                if (this.peekToken.type == TokenType.LPARENT) {
                    ExpressionStatement(parseFunctionCall())
                } else {
                    null
                }
            }

            else -> {
                null
                // ExpressionStatement(Identifier(this.curToken.literal))
            }
        }
    }

    private fun parseIfStatement(): IfStatement {
        val statement = IfStatement(null, emptyList(), emptyList())
        if (peekToken.type == TokenType.IDENTIFIER) {
            nextToken()
            statement.condition = parseFunctionCall()
            val ifBlock = parseIfBlock()
            statement.consequence = ifBlock.first
            statement.alternative = ifBlock.second
        } else {
            throw Exception("Not a valid if statement")
        }
        return statement
    }

    private fun parseIfBlock(): Pair<List<Statement>, List<Statement>> {
        val consequence = emptyList<Statement>().toMutableList()
        val alternative = emptyList<Statement>().toMutableList()
        while (true) {
            if (curToken.type != TokenType.ELSE){
                val stmt = parseStatement()
                if (stmt != null) {
                    consequence.add(stmt)
                }
                nextToken()
            } else {
                while (curToken.type != TokenType.END){
                    val stmt = parseStatement()
                    println(stmt)
                    if (stmt != null) {
                        alternative.add(stmt)
                    }
                    nextToken()
                    println(alternative)
                    println(curToken)
                }
            }
            if (curToken.type == TokenType.END) {
                break
            }
        }
        return Pair(consequence, alternative)
    }

    private fun parseFunctionCall(): FunctionCallExpression {
        val statement = FunctionCallExpression(Identifier(""), null)
        if (curToken.type == TokenType.IDENTIFIER) {
            statement.name.value = curToken.literal
            when (peekToken.type) {
                TokenType.LPARENT -> {
                    nextToken()
                    when (peekToken.type) {
                        TokenType.INTEGER -> {
                            nextToken()
                            statement.argument = curToken.literal.toInt()
                            if (peekToken.type == TokenType.RPARENT) {
                                // Do this so it leaves the function
                                nextToken()
                                nextToken() // eol
                            } else {
                                throw Exception("Not a valid function call")
                            }
                        }

                        TokenType.RPARENT -> {
                            nextToken()
                        }

                        else -> {
                            throw Exception("Not a valid function call")
                        }
                    }
                }

                TokenType.EOL -> {
                    nextToken()
                }

                else -> {
                    throw Exception("Not a valid function call")
                }
            }
        } else {
            throw Exception("Not a valid function call")
        }
        return statement
    }
}