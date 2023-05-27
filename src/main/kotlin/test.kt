fun main() {
    val input = readLine() ?: return // Read user input
    val command = "print"

    if (input.startsWith(command)) {
        val quotedPart = input.substringAfter("\"").substringBeforeLast("\"")
        println(quotedPart)
    }
}