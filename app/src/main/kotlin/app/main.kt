package app

import java.io.File

fun main() {
    val inputFile = File("input.txt")
    val outputFile = File("output.txt")
    val output = convert(inputFile.readText())
    outputFile.writeText(output)
}