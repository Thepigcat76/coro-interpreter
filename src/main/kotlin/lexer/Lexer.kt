package lexer

class Lexer(private val input: String, private val languages: TokenLanguages) {
    init {
        keywords = when (languages) {
            TokenLanguages.GERMAN -> {
                keywords_de
            }
            TokenLanguages.ENGLISH -> {
                keywords_en
            }
        }
    }
    private val tokens = mutableListOf<Token>()
    fun tokenize(): List<Token> {
        var currentPos = 0
        while (currentPos < input.length) {
            val char = input[currentPos]
            when {
                char.isWhitespace() -> {
                    currentPos++
                }
                char.isLetter() -> {
                    val identifier = StringBuilder()
                    while (currentPos < input.length && input[currentPos].isLetterOrDigit() && !input[currentPos].isWhitespace()) {
                        identifier.append(input[currentPos])
                        currentPos++
                    }
                    val x = keywords.entries.find { it.value == identifier.toString() }
                    if (x != null) {
                        tokens.add(Token(x.key, x.value))
                    } else {
                        tokens.add(Token(TokenType.IDENTIFIER, identifier.toString()))
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

                else -> {
                    when (char) {
                        keywords[TokenType.LPARENT]!!.toCharArray()[0] -> {
                            tokens.add(Token(TokenType.LPARENT))
                        }
                        keywords[TokenType.RPARENT]!!.toCharArray()[0] -> {
                            tokens.add(Token(TokenType.RPARENT))
                        }
                    }
                    currentPos++
                }
            }
        }
        tokens.add(Token(TokenType.EOF))
        return tokens
    }
}
