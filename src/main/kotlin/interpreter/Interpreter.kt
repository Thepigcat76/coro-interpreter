package interpreter

import lexer.Lexer
import parser.Parser
import parser.Program
import token.*

class Interpreter(input: String, val languages: TokenLanguages) {
    val lexer = Lexer(input)
    val parser = Parser(lexer)
    fun lex(): List<Token> {
        keywords = when (languages) {
            TokenLanguages.GERMAN -> {
                keywords_de
            }

            TokenLanguages.ENGLISH -> {
                keywords_en
            }
        }
        return lexer.tokenize()
    }

    fun parse(): Program {
        return parser.parseProgram()
    }
}
