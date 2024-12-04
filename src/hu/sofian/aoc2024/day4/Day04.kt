package hu.sofian.aoc2024.day4

import hu.sofian.aoc2024.println
import hu.sofian.aoc2024.readInput
import kotlin.math.max
import kotlin.math.min

val lookingFor = arrayOf('X','M','A','S')

fun main() {

    fun parseInput(input: List<String>): Array<CharArray> = Array(input.size) { columnIndex ->
        CharArray(input[columnIndex].length) { input[columnIndex][it] }
    }

    tailrec fun findWords(row: Int, column: Int, currentChar: Char, charMatrix: Array<CharArray>) : Int {
        if(lookingFor.indexOf(currentChar) == lookingFor.size -1) {
            var sum = 0
            if(charMatrix[row][max(column -1, 0)] == currentChar) {
                sum++
            }
            if(charMatrix[max(column - 1, 0)][max(column - 1, 0)] == currentChar) {
                sum++
            }
            if(charMatrix[row + 1][column] == currentChar) {
                sum++
            }
            if(charMatrix[row + 1][column] == currentChar) {
                sum++
            }

        }
    }

    fun part1(input: List<String>): Int {
        val charMatrix = parseInput(input)
        val startingPoints = mutableListOf<Pair<Int, Int>>()
        charMatrix.forEachIndexed { row, chars ->
            chars.forEachIndexed { column, c ->
                if (c == lookingFor[0]) {
                    startingPoints.add(Pair(row, column))
                }
            }
        }
        val wordCount = startingPoints.sumOf { (row, column) ->
            findWords(row, column,  lookingFor[1], charMatrix)
        }

        return wordCount
    }

    fun part2(input: List<String>): Int {

        return 0
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(readInput(("Day04_test"))) == 18)

    // Test if implementation meets criteria from the description, like:
    check(part2(readInput(("Day04_test"))) == 0)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
