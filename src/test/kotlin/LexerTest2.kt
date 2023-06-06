import java.io.BufferedReader
import java.io.File

fun main() {
    val filename = "src/test/resources/test.txt"
    val file = File(filename)

    try {
        val reader = BufferedReader(file.reader())
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            println(line)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}