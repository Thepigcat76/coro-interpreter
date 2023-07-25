package lexer

import token.*

class Lexer {
    private val tokens = mutableListOf<Token>()
    fun tokenize(input: String): List<Token> {
        var currentPos = 0
        while (currentPos < input.length) {
            val char = input[currentPos]
            when {
                char.isWhitespace() -> {
                    currentPos++
                }

                char.isLetter() && char != '(' && char != ')' -> {
                    val identifier = StringBuilder()
                    while (currentPos < input.length && char.isLetterOrDigit() && !input[currentPos].isWhitespace()) {
                        identifier.append(input[currentPos])
                        currentPos++
                    }
                    when (identifier.toString().lowercase()) {
                        keywords[TokenType.NOT] -> {
                            tokens.add(Token(TokenType.NOT))
                        }

                        keywords[TokenType.IF] -> {
                            tokens.add(Token(TokenType.IF))
                        }

                        keywords[TokenType.REPEAT] -> {
                            tokens.add(Token(TokenType.REPEAT))
                        }

                        keywords[TokenType.END] -> {
                            tokens.add(Token(TokenType.END))
                        }

                        keywords[TokenType.ELSE] -> {
                            tokens.add(Token(TokenType.ELSE))
                        }

                        keywords[TokenType.ALWAYS] -> {
                            tokens.add(Token(TokenType.ALWAYS))
                        }

                        keywords[TokenType.TIMES] -> {
                            tokens.add((Token(TokenType.TIMES)))
                        }

                        keywords[TokenType.DEFINE] -> {
                            tokens.add(Token(TokenType.DEFINE))
                        }

                        keywords[TokenType.WHILE] -> {
                            tokens.add(Token(TokenType.WHILE))
                        }

                        keywords[TokenType.EOL] -> {
                            tokens.add(Token(TokenType.EOL, "eol"))
                        }

                        else -> {
                            tokens.add(Token(TokenType.IDENTIFIER, "$identifier"))
                        }
                    }
                }

                char.isDigit() -> {
                    val identifier = StringBuilder()
                    while (currentPos < input.length && input[currentPos].isDigit() && !input[currentPos].isWhitespace()) {
                        identifier.append(input[currentPos])
                        currentPos++
                    }
                    tokens.add(Token(TokenType.INTEGER, identifier.toString()))
                }
            }
        }
        return tokens
    }
}
