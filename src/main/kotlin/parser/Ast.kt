package parser

sealed interface Node {
    fun tokenLiteral(): String
}

sealed interface Statement : Node {
    fun statementNode()
}

sealed interface Expression : Node {
    fun expressionNode()
}

class Program {
    val statements: Array<Statement> = emptyArray()
}

fun tokenLiteral(): String {
    return if (Program().statements.isNotEmpty()) {
        Program().statements[0].tokenLiteral()
    } else {
        ""
    }
}