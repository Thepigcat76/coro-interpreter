package parser

interface Statement
interface Expression
data class Identifier(val value: String) : Expression

data class IfStatement(
    override var statements: List<Statement>,
    var condition: Expression?,
    var consequence: List<Statement>,
    var alternative: List<Statement>,
) : BlockStatement(statements)

/*
Will take the first token in the block as input and stop when it hits the 'end' keyword
 */
open class BlockStatement(open var statements: List<Statement>) : Statement

data class Program(var statements: List<Statement>)
