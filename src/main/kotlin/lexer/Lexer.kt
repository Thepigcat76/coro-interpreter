package lexer

import token.Token
import token.TokenType
import token.keywords

class Lexer {
    fun tokenize(input: String): List<Token> {
        val tokens = mutableListOf<Token>()
        var currentPos = 0
        while (currentPos < input.length) {
            var char = input[currentPos]
            when {
                char.isWhitespace() -> {
                    currentPos++
                }

                char.isLetter() -> {
                    val identifier = StringBuilder()
                    while (currentPos < input.length && char.isLetterOrDigit() && !input[currentPos].isWhitespace()) {
                        identifier.append(input[currentPos])
                        currentPos++
                    }
                    when (identifier.toString()) {
                        "not" -> {
                            tokens.add(Token(TokenType.NOT))
                        }

                        "if" -> {
                            tokens.add(Token(TokenType.IF))
                        }

                        "repeat" -> {
                            tokens.add(Token(TokenType.REPEAT))
                        }

                        "end" -> {
                            tokens.add(Token(TokenType.END))
                        }

                        "else" -> {
                            tokens.add(Token(TokenType.ELSE))
                        }

                        "always" -> {
                            tokens.add(Token(TokenType.ALWAYS))
                        }

                        "times" -> {
                            tokens.add((Token(TokenType.TIMES)))
                        }

                        "define" -> {
                            tokens.add(Token(TokenType.DEFINE))
                        }

                        "while" -> {
                            tokens.add(Token(TokenType.WHILE, "while"))
                        }
                    }
                }

                char.isDigit() -> {
                    val identifier = StringBuilder()
                    while (currentPos < input.length && input[currentPos].isDigit() && !input[currentPos].isWhitespace()) {
                        identifier.append(input[currentPos])
                        currentPos++
                        if (currentPos < input.length) {
                            char = input[currentPos]
                        }
                    }
                    tokens.add(Token(TokenType.INTEGER, identifier.toString()))
                }
            }
        }
        return tokens
    }
}


fun main() {
    val tokens = Lexer().tokenize("if not while repeat 908")
    println(tokens)
}