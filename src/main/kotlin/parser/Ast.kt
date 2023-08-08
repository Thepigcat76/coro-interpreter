package parser

import token.Token

sealed interface Node {
    fun tokenLiteral(): String
}

sealed interface Statement : Node {
    fun statementNode()
}

sealed interface Expression : Node {
    fun expressionNode()
}

class Program(var statements: List<Statement>?) {
    fun tokenLiteral(): String {
        return if (this.statements!!.isNotEmpty()) {
            this.statements[0].tokenLiteral()
        } else {
            ""
        }
    }
}

class Identifier : Expression {
    lateinit var token: Token
    lateinit var value: String
    override fun expressionNode() {
    }

    override fun tokenLiteral(): String {
        return token.literal
    }
}

class IfStatement {
    lateinit var token: Token
    lateinit var condition: Identifier
    lateinit var consequence: BlockStatement
    lateinit var alternative: BlockStatement
}

class BlockStatement {
    lateinit var token: Token
    lateinit var statements: Array<Statement>
}
