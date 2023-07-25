import interpreter.Interpreter
import lexer.Lexer
import token.TokenLanguages
import java.io.BufferedReader
import java.io.File

class ReadFile {
    fun read(): String {
        val filename = "src/main/resources/example_de.coro"
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
    val tokenStream = Interpreter().interpret(ReadFile().read(), TokenLanguages.GERMAN); // use this enum parameter to change the language the interpreter will use
    println(tokenStream)
}