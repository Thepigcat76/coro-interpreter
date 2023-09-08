package parser

import lexer.Lexer
import lexer.TokenType
import kotlin.system.exitProcess

class Parser(lexer: Lexer) {
    private val tokenStream = lexer.tokenize()
    private var currentPos = 0
    private var curToken = tokenStream[0]
    private var peekToken = tokenStream[1]

    private fun nextToken() {
        currentPos += 1;
        curToken = peekToken;
        if (currentPos + 1 < tokenStream.size) {
            peekToken = tokenStream[currentPos + 1];
        }
    }

    fun parseProgram(): Program {
        val program = Program(emptyList())
        program.statements = listOf(parseStatement())
        return program
    }

    private fun parseCallStatement(): Statement? {
        val stmt = CallStatement(Identifier(curToken.literal), emptyList())
        if (peekToken.type != TokenType.LPARENT) {
            return null
        }

        nextToken()

        stmt.args = parseRawList(TokenType.RPARENT)
        return stmt
    }

    private fun parseIntStatement(): Statement? {
        return IntegerLiteral(value = curToken.literal.toLong())
    }

    private fun parseStatement(): Statement? {
        return when (curToken.type) {
            TokenType.IDENTIFIER -> {
                parseCallStatement()
            }
            TokenType.INTEGER -> {
                parseIntStatement()
            }

            else -> {
                println("no parse statement function for ${curToken.type} found")
                null
            }
        }
    }

    /**
     * entry point is list entry visualizer like `(` or `[`
     */
    private fun parseRawList(endTokenType: TokenType): List<Statement?> {
        val list = emptyList<Statement?>().toMutableList()
        println("entry token: $curToken")
        nextToken()
        while (curToken.type != endTokenType) {
            list += parseStatement()
            nextToken()
            if (curToken.type == endTokenType) {
                break
            } else if (curToken.type == TokenType.COMMA) {
                nextToken()
            }
        }
        println("the list: $list")
        return list
    }

    private fun expectPeek(expectedTokenType: TokenType): Boolean {
        return if (peekToken.type != expectedTokenType) {
            println("Next token supposed to be $expectedTokenType found ${peekToken.type} instead")
            exitProcess(1)
        } else {
            true
        }
    }
}