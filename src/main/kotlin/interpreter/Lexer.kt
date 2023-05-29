package interpreter

import java.io.BufferedReader
import java.io.FileReader

class Lexer {
    private var currentFile = "example.coro"
    private val fileReader = FileReader(currentFile)
    private val bufferedReader = BufferedReader(fileReader)
    private val rawLines = bufferedReader.readLines()

    init {
        for (rawLine in rawLines) {
            val splitLine = rawLine.split(Regex("[  \"]"))
            for (line in splitLine) {
                    println(line)
                }
            }
        }
    }
fun main() {
    Lexer()
}