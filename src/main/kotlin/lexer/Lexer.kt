package lexer

import token.Token
import token.TokenType
import java.io.BufferedReader
import java.io.File

class Lexer {
    fun tokenize(input: String): List<Token> {
        val tokens = mutableListOf<Token>()
        var currentPos = 0
        while (currentPos < input.length) {
            val char = input[currentPos]
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
                            tokens.add(Token(TokenType.WHILE))
                        }

                        "EOL" -> {
                            tokens.add(Token(TokenType.EOL, "EOL"))
                        }

                        else -> {
                            tokens.add(Token(TokenType.FUNCTION, "$identifier"))
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

class ReadFile {
    fun read(): String? {
        val filename = "example.coro"
        val file = File(filename)
        var lines: Array<String?> = emptyArray()

        try {
            val reader = BufferedReader(file.reader())
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                lines += line
                lines += "EOL"
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertLinesToString(lines)
    }

    private fun convertLinesToString(lines: Array<String?>): String? {
        val words = lines.flatMap { line -> line?.split("\\s+".toRegex()) ?: emptyList() }
        return words.joinToString(" ")
    }
}

val tokenStream = Lexer().tokenize(ReadFile().read().toString())

fun main() {
    println(tokenStream)
}