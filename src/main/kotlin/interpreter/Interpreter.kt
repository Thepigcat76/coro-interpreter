package interpreter

import lexer.Lexer
import parser.Parser
import parser.Program
import token.*

class Interpreter(input: String, private val languages: TokenLanguages) {
    private val lexer = Lexer(input, languages)
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
