package parser

interface Statement
interface Expression
data class Identifier(var value: String) : Expression

data class IfStatement(
    var condition: Expression?,
    var consequence: List<Statement?>,
    var alternative: List<Statement?>,
) : Statement

data class FunctionCallExpression(var name: Identifier, var argument: Int?) : Expression

data class Program(var statements: List<Statement>)

data class ExpressionStatement(var expression: Expression) : Statement
