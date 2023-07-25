package interpreter

import lexer.Lexer
import token.*

class Interpreter {
    fun interpret(input: String, languages: TokenLanguages): List<Token> {
        keywords = when (languages) {
            TokenLanguages.GERMAN -> {
                keywords_de
            }

            TokenLanguages.ENGLISH -> {
                keywords_en
            }
        }
        return Lexer().tokenize(input)
    }
}
