import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JTextArea

fun main() {
    val frame = JFrame("Interpreter-test")
    val textArea = JTextArea()
    val button = JButton("Run")
    val output = JTextArea()
    val outputDesc = JLabel("Output:")
    val inputDesc = JLabel("Input:")

    frame.layout = null
    frame.add(textArea)
    frame.add(output)
    frame.add(button)
    frame.add(outputDesc)
    frame.add(inputDesc)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setSize(800, 800)
    frame.isVisible = true

    button.setBounds(100, 0, 200, 100)
    inputDesc.setBounds(100, 75, 200, 100)
    textArea.setBounds(100, 150, 200, 100)
    outputDesc.setBounds(100, 225, 200, 100)
    output.setBounds(100, 300, 200, 100)
}