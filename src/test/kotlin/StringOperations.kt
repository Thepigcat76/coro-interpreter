import kotlin.random.Random

fun main() {
    if (test() == 'H') {
        println(true)
    } else {
        println(false)
    }
}

fun test(): Char {
    val testString = "Hello"
    println(testString[2])
    return testString[2]
}