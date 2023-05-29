import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JTextField

fun main() {
    val screen = JFrame("lexer-test")
    val textArea = JTextField()
    val button = JButton()

    screen.isVisible = true
    screen.add(textArea).setBounds(100, 200, 100, 100)
    screen.add(button).setBounds(100, 100, 100, 100)
    screen.layout = null
    screen.setSize(800, 800)
    screen.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    val lines = textArea.text.lines()

    button.addActionListener() {
        for (line in lines) {
            val tokens = lexer(line)
            parser(tokens)
        }
    }
}

fun lexer(input: String): MatchResult? {
    val regex = "\"([^\"]*)\"".toRegex()
    val tokens = input.split(Regex("( )(\")"))
    val matchResult = regex.find(input)
    return matchResult
}

fun parser(tokens: MatchResult?) {
    /*for (token in tokens.indices) {
        if (tokens[token] == "print") {
            println(tokens[token + 1])
        }
     */
    if (tokens != null) {
        val quotedPart = tokens.groupValues[1]
        println(quotedPart)
    } else {
        println("No quoted part found in the string")
    }
}