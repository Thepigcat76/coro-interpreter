package parser

interface Statement

data class Program(var statements: List<Statement?>)

data class Identifier(val value: String) : Statement
data class IntegerLiteral(val value: Long) : Statement
data class CallStatement(val identifier: Identifier, var args: List<Statement?>) : Statement
