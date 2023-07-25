import lexer.Lexer
import token.TokenType
import token.keywords_en
import java.io.BufferedReader
import java.io.File

class ReadFile {
    fun read(): String {
        val filename = "src/main/resources/example.coro"
        val file = File(filename)
        var lines: Array<String?> = emptyArray()

        try {
            val reader = BufferedReader(file.reader())
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                lines += line
                if (line != "") {
                    lines += "EOL"
                }
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertLinesToString(lines)
    }

    private fun convertLinesToString(lines: Array<String?>): String {
        val words = lines.flatMap { line -> line?.split("\\s+".toRegex()) ?: emptyList() }
        return words.joinToString(" ")
    }
}

fun main() {
    val tokenStream = Lexer().tokenize(ReadFile().read())
    println(tokenStream)
}
