import java.io.BufferedReader
import java.io.FileReader

class Lexer {
    private var currentFile = "example.coro"
    private val fileReader = FileReader(currentFile)
    private val bufferedReader = BufferedReader(fileReader)
    private val rawLines = bufferedReader.readLines()
    private val tokenStream = ArrayList<String>()

    init {
        for (rawLine in rawLines) {
            val splitLine = rawLine.split(Regex("[ ]"))
            for (line in splitLine) {
                if (line.isNotEmpty()) {
                    tokenStream.add(line)
                }
            }
            bufferedReader.close()
        }
        println(tokenStream)
    }
}
fun main() {
    Lexer()
}