package interpreter

import lexer.Lexer
import parser.Parser
import token.*

class Interpreter {
    fun interpret(input: String, languages: TokenLanguages): List<Token> {
        val lexer = Lexer(input)
        val parser = Parser(lexer)
        parser.parseProgram()
        keywords = when (languages) {
            TokenLanguages.GERMAN -> {
                keywords_de
            }

            TokenLanguages.ENGLISH -> {
                keywords_en
            }
        }
        return Lexer(input).tokenize()
    }
}
