import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JTextArea

fun main() {
    val screen = JFrame("lexer-test")
    val textArea = JTextArea()
    val button = JButton()

    screen.isVisible = true
    screen.add(textArea).setBounds(100, 200, 100, 100)
    screen.add(button).setBounds(100, 100, 100, 100)
    screen.layout = null
    screen.setSize(800, 800)
    screen.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    button.addActionListener() {
        /*if (textArea.text == "print") {
            println("uwu")
        }*/
        val tokens = lexer(textArea.text)
        parser(tokens)
    }

    val isEmpty = textArea.text.isEmpty()
    println("Is the JTextArea empty? $isEmpty")
}

fun lexer(input: String): List<String> {
    val tokens = input.split(" ".toRegex())
    return tokens
}

fun parser(tokens: List<String>) {
    for (token in tokens) {
        println(token)
    }
}